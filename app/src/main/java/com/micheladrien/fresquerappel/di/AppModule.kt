package com.micheladrien.fresquerappel.di

import com.micheladrien.fresquerappel.managers.CollageDataManager
import com.micheladrien.fresquerappel.managers.DataProvider
import com.micheladrien.fresquerappel.managers.JsonDataProvider
import com.micheladrien.fresquerappel.managers.MainCollageDataManager
import com.micheladrien.fresquerappel.tools.notification.MainTimerSExecutor
import com.micheladrien.fresquerappel.tools.notification.TimerSExecutor
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
        dataManager : MainCollageDataManager
    ): CollageDataManager

    @Singleton
    @Binds
    abstract fun bindDataProvider(
        dataProvider : JsonDataProvider
    ): DataProvider


}