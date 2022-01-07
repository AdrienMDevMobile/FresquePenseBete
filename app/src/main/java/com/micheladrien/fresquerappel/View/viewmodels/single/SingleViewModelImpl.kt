package com.micheladrien.fresquerappel.View.viewmodels.single

import android.app.Application
import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import javax.inject.Inject

class SingleViewModelImpl@Inject constructor (collageDataManager: CollageDataManager, application: Application) :
    SingleViewModel(collageDataManager, application) {

}