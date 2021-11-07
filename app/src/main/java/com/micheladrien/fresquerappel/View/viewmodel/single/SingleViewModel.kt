package com.micheladrien.fresquerappel.View.viewmodel.single

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import com.micheladrien.fresquerappel.View.tools.iCollageVM
import javax.inject.Inject

abstract class SingleViewModel(collageDataManager: CollageDataManager, application: Application) :
    iCollageVM(collageDataManager, application) {


}