package com.example.xoapptdd_mvvm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ResultCommunication : Communication<String> {

    class Base : Communication.Base<String>(), ResultCommunication {
        override fun map(data: String) = Unit

        override fun observe(owner: LifecycleOwner, observer: Observer<String>) = Unit
    }
}