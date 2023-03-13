package com.example.xoapptdd_mvvm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ResultCommunication : Communication<String> {

    class Base : Communication.Base<String>(), ResultCommunication {
        override fun map(data: String) {
            TODO("Not yet implemented")
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<String>) {
            TODO("Not yet implemented")
        }
    }
}