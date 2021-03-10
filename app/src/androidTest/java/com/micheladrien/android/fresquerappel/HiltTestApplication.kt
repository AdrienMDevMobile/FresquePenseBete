package com.micheladrien.android.fresquerappel

import com.micheladrien.fresquerappel.di.BaseApplication
import dagger.hilt.android.testing.CustomTestApplication

@CustomTestApplication(BaseApplication::class)
interface HiltTestApplication