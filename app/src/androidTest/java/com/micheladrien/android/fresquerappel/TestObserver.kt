package com.micheladrien.android.fresquerappel

import androidx.lifecycle.*

//https://medium.com/microsoft-mobile-engineering/testing-viewmodels-the-journey-c6d7747b316b


fun <T> LiveData<T>.observeForTesting(block: (ValueObserver<T>) -> Unit) {
    val observer = ValueObserver<T>()
    try {
        observeForever(observer)
        block(observer)
    } finally {
        removeObserver(observer)
    }
}


class ValueObserver<T> : Observer<T> {
    val values = mutableListOf<T>()

    override fun onChanged(t: T) {
        values.add(t)
    }
}


//Another LiveData testing method I found :
//https://proandroiddev.com/how-to-easily-test-a-viewmodel-with-livedata-and-coroutines-230c74416047
//My issue with it is that if no value are given to the livedata when it should. No issue is detected.