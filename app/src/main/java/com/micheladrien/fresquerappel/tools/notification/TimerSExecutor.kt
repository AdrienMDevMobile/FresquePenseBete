package com.micheladrien.fresquerappel.tools.notification

import android.content.Context
import com.micheladrien.fresquerappel.datas.TimerModel
import java.util.ArrayList

//Timer Service Executor, will plan the timers to trigger in the future
interface TimerSExecutor {
    suspend fun executeTimers(context: Context, timerArrayList : ArrayList<TimerModel>?) : Boolean
    suspend fun stopAllTimers(context: Context, timerArrayList : ArrayList<TimerModel>?) : Boolean
}