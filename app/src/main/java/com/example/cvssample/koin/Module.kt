package com.example.cvssample.koin

import android.content.Context
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cvssample.data.repository.FlickrApi
import com.example.cvssample.data.repository.FlickrImageRepository
import com.example.cvssample.data.room.FlickrDatabase
import com.example.cvssample.viewmodel.FlickrImageViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single {
        FlickrImageRepository(get())
    }
}

val viewModelModule = module {
   viewModel {
       FlickrImageViewModel(get())
   }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): FlickrApi {
        return retrofit.create(FlickrApi::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}

val dataBaseModule = module {
    fun provideRoomDataBase(context: Context): FlickrDatabase {
       return Room.databaseBuilder(
            context,
            FlickrDatabase::class.java, "search-db"
        ).build()
    }

    single {
        provideRoomDataBase(androidContext())
    }
}