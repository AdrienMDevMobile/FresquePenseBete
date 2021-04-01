package com.micheladrien.fresquerappel.di

import com.micheladrien.fresquerappel.managers.RawTimerProvider
import com.micheladrien.fresquerappel.managers.TimerProvider
import com.micheladrien.fresquerappel.tools.notification.MainTimerSExecutor
import com.micheladrien.fresquerappel.tools.notification.TimerSExecutor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


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
            timerProvider: RawTimerProvider
    ) : TimerProvider
}