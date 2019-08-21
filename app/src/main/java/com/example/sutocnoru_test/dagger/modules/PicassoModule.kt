package com.example.sutocnoru_test.dagger.modules

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import okhttp3.OkHttpClient
import dagger.Provides
import javax.inject.Inject


@Module(includes = [OkHttpClientModule::class])
class PicassoModule {

    @Provides
    @Inject
    fun picasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context).downloader(okHttp3Downloader).build()
    }

    @Provides
    fun okHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }
}