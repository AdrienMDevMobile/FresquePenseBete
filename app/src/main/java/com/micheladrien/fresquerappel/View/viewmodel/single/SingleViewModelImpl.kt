package com.micheladrien.fresquerappel.View.viewmodel.single

import android.app.Application
import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import com.micheladrien.fresquerappel.View.viewmodel.single.SingleViewModel
import javax.inject.Inject

class SingleViewModelImpl@Inject constructor (collageDataManager: CollageDataManager, application: Application) :
    SingleViewModel(collageDataManager, application) {

}