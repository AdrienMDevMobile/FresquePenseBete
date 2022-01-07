package com.micheladrien.fresquerappel.di

import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import com.micheladrien.fresquerappel.Data.managers.CollageDataManagerMain
import com.micheladrien.fresquerappel.Data.managers.SingleDataManager
import com.micheladrien.fresquerappel.Data.managers.SingleDataManagerMain
import com.micheladrien.fresquerappel.Data.providers.*
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
    abstract fun bindCollageDataManager(
        dataManager : CollageDataManagerMain
    ): CollageDataManager

    @Singleton
    @Binds
    abstract fun bindCollageDataProvider(
        dataProvider : CollageDataProviderJson
    ): CollageDataProvider

    @Singleton
    @Binds
    abstract fun bindSingleDataManager(
        dataManager : SingleDataManagerMain
    ) : SingleDataManager

    @Singleton
    @Binds
    abstract fun bindSingleDataProvider(
        dataProvider: SingleDataProviderJson
    ) : SingleDataProvider

    @Singleton
    @Binds
    abstract fun bindImageProvider(
        imageProviderRaw: ImageProviderRaw
    ) : ImageProvider


}