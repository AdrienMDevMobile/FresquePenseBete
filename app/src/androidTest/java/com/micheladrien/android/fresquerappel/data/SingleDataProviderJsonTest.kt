package com.micheladrien.android.fresquerappel.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.micheladrien.fresquerappel.Data.datas.SingleCard
import com.micheladrien.fresquerappel.Data.providers.SingleDataProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class SingleDataProviderJsonTest {


    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var singleDataProvider : SingleDataProvider
    private lateinit var list_single : MutableList<SingleCard>

    @Before
    fun setUp(){
        hiltRule.inject()
        list_single = singleDataProvider.provideSingles()
    }

    @Test
    fun ListCorrectTypeTest(){
        Assert.assertNotNull(list_single)
    }

    @Test
    fun ListCorrectSizeTest() {
        Assert.assertTrue(list_single.size > 0)
    }

}