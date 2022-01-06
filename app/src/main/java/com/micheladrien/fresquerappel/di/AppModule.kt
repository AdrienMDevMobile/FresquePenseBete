package com.micheladrien.fresquerappel.di

import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import com.micheladrien.fresquerappel.Data.managers.CollageDataProvider
import com.micheladrien.fresquerappel.Data.managers.CollageDataProviderJson
import com.micheladrien.fresquerappel.Data.managers.CollageDataManagerMain
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {


    @Singleton
    @Binds
    abstract fun bindDataManager(
        dataManager : CollageDataManagerMain
    ): CollageDataManager

    @Singleton
    @Binds
    abstract fun bindDataProvider(
        dataProvider : CollageDataProviderJson
    ): CollageDataProvider


}