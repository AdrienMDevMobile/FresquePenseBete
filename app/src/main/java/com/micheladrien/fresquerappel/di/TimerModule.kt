package com.micheladrien.fresquerappel.di

import com.micheladrien.fresquerappel.Data.managers.TimerProviderRaw
import com.micheladrien.fresquerappel.Data.managers.TimerProvider
import com.micheladrien.fresquerappel.View.tools.notification.MainTimerSExecutor
import com.micheladrien.fresquerappel.View.tools.notification.TimerSExecutor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class TimerModule {

    @ViewModelScoped
    @Binds
    abstract fun bindTimerSExecutor(
            timerExecutor : MainTimerSExecutor
    ) : TimerSExecutor

    @ViewModelScoped
    @Binds
    abstract fun bindTimerProvider(
            timerProvider: TimerProviderRaw
    ) : TimerProvider
}