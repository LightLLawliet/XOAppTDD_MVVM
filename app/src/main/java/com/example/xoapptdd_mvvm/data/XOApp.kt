package com.example.xoapptdd_mvvm.data

import android.app.Application
import com.example.xoapptdd_mvvm.presentation.MainViewModel
import com.example.xoapptdd_mvvm.presentation.ManageResources

class XOApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        mainViewModel = MainViewModel(
            ResultCommunication.Base(),
            UpdateCommunication.Base(),
            MainInteractor.Base(MainRepository.Base(ManageResources.Base(this)))
        )
    }
}