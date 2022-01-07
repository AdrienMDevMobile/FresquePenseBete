package com.micheladrien.android.fresquerappel.diTest.testClasses

import com.micheladrien.fresquerappel.di.TimerModule
import com.micheladrien.fresquerappel.Data.providers.TimerProvider
import com.micheladrien.fresquerappel.View.tools.notification.MainTimerSExecutor
import com.micheladrien.fresquerappel.View.tools.notification.TimerSExecutor
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
        components = [ViewModelComponent::class],
        replaces = [TimerModule::class]
)
abstract class TestTimerModule {

    @ViewModelScoped
    @Binds
    abstract fun bindTimerSExecutor(
            timerExecutor : MainTimerSExecutor
    ) : TimerSExecutor

    @ViewModelScoped
    @Binds
    abstract fun bindTimerProvider(
            timerProvider: TimerProviderTest
    ) : TimerProvider
}