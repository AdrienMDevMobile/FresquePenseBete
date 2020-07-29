package com.micheladrien.fresquerappel.ui.recherche

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.micheladrien.fresquerappel.absMainVM

class MainVMSingleton {

    companion object {
        private var  instance: absMainVM? = null
    }

    fun getInstance(owner: ViewModelStoreOwner, context: Context): absMainVM? {
        if (instance == null) {
            synchronized(MainViewModel()::class.java) {
                if (instance == null) {

                    //Partie dépendante du type de mainViewModel
                    instance = ViewModelProvider(owner).get(
                        MainViewModel::class.java)
                    (instance as MainViewModel).setContext(context)

                    RelationModel.initialize(context)
                }
            }
        }
        return instance
    }
}