package com.micheladrien.fresquerappel.View.tools

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.micheladrien.fresquerappel.Data.managers.CollageDataManager

abstract class iCollageVM (protected val collageDataManager: CollageDataManager, application: Application) : AndroidViewModel(application) {
    fun getCurrentCollage(): String {
        return collageDataManager.getCurrentCollage()
    }
}