package com.micheladrien.fresquerappel.di

import com.micheladrien.fresquerappel.tools.notification.MainTimerSExecutor
import com.micheladrien.fresquerappel.tools.notification.TimerSExecutor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
abstract class TimerModule {

    @Binds
    abstract fun bindTimerSExecutor(
            timerExecutor : MainTimerSExecutor
    ) : TimerSExecutor
    //@Provides
    //fun provideTimerExecutor(): TimerSExecutor = MainTimerSExecutor()
}