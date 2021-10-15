package com.example.cvssample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvssample.data.models.FlickrImage
import com.example.cvssample.data.repository.FlickrImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class FlickrViewState {
    object IdleState: FlickrViewState()
    object LoadingState : FlickrViewState()
    class FlickrImagesList(val listOfImages: List<FlickrImage>): FlickrViewState()
    class ErrorState(val errorMessage:String): FlickrViewState()
}

class FlickrImageViewModel(private val flickrImageRepository: FlickrImageRepository): ViewModel() {

    var selectedFlickrImage: FlickrImage? = null
    private val _flickrImagesListLiveData = MutableLiveData<FlickrViewState>(FlickrViewState.IdleState)
    val flickrImagesListLiveData: LiveData<FlickrViewState> = _flickrImagesListLiveData
    val searchTagLiveData = flickrImageRepository.getSearchTagsLiveData()

    fun getImages(tag:String) {
        if(tag.isEmpty()) {
            _flickrImagesListLiveData.postValue(FlickrViewState.IdleState)
        } else {
            _flickrImagesListLiveData.postValue(FlickrViewState.LoadingState)
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    saveRecentSearchTag(tag)
                    _flickrImagesListLiveData.postValue(
                        FlickrViewState.FlickrImagesList(
                            flickrImageRepository.getImagesByTag(tag = tag).flickrImages
                        )
                    )
                } catch (e: Exception) {
                    _flickrImagesListLiveData.postValue(
                        FlickrViewState.ErrorState(
                            e.localizedMessage ?: ""
                        )
                    )
                }
            }
        }
    }

    private fun saveRecentSearchTag(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            flickrImageRepository.saveSearchTag(tag = tag)
        }
    }
}