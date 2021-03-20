package com.example.data.di.providers

import android.app.Application
import android.content.Context
import com.example.data.BuildConfig
import com.example.data.local.BankDatabase
import com.example.data.remote.ITransactionAPI
import com.example.data.remote.interceptors.MockInterceptor
import com.example.data.repositories.DataStoreRepository
import com.example.data.repositories.TransactionRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideOkHttpClient(mockInterceptor: Interceptor?): OkHttpClient {
    val client = OkHttpClient().newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
    if (BuildConfig.FLAVOR == "mock"){
        mockInterceptor?.let {
            client.addInterceptor(mockInterceptor)
        }
    }
    return client.build()
}

fun provideMockInterceptor(application: Application): Interceptor {
    return MockInterceptor(application)
}

fun provideGson(): Gson {
    return GsonBuilder()
        .setLenient()
        .serializeNulls()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .create()
}

fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(BuildConfig.BaseURL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

fun provideTransactionApi (retrofit: Retrofit): ITransactionAPI = retrofit
    .create(ITransactionAPI::class.java)

fun provideBankDatabase(application: Application): BankDatabase {
    return BankDatabase.getInstance(application)
}

fun provideTransactionRepository(retrofit: ITransactionAPI, bankDB: BankDatabase, dataStoreRepository: DataStoreRepository): TransactionRepository = TransactionRepository(retrofit, bankDB, dataStoreRepository )

fun provideDataStoreRepository(context: Context): DataStoreRepository = DataStoreRepository(context)