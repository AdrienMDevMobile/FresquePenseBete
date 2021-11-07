package com.micheladrien.fresquerappel.di

import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import com.micheladrien.fresquerappel.Data.managers.DataProvider
import com.micheladrien.fresquerappel.Data.managers.DataProviderJson
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
        dataProvider : DataProviderJson
    ): DataProvider


}