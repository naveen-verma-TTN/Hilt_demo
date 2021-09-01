package com.example.hilt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Naveen Verma on 01/09/21.
 * To The New
 * naveen.verma@tothenew.com
 */

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideSomeString(): String {
        return "Fragment Dependency"
    }
}