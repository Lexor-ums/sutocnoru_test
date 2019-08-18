package com.example.sutocnoru_test.dagger.modules

import com.example.sutocnoru_test.data.net.retrofit.JsonApiService
import com.example.sutocnoru_test.domain.interactors.BaseInteractor
import com.example.sutocnoru_test.domain.interactors.LoadRating
import com.example.sutocnoru_test.utils.BASE_URL
import com.example.sutocnoru_test.utils.REQUEST_TIMEOUT
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor


@Module
class NetworkModule {
    private var okHttpClient: OkHttpClient = OkHttpClient()
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient =
            OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS).build()
        return okHttpClient
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): JsonApiService {
        return retrofit.create(JsonApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideInteractor(api: JsonApiService): BaseInteractor {
        return LoadRating()
    }
}