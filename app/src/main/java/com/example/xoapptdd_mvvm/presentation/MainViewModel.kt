package com.example.xoapptdd_mvvm.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.xoapptdd_mvvm.data.*

class MainViewModel(
    private val communicationResult: ResultCommunication,
    private val updateCommunication: UpdateCommunication,
    private val interactor: MainInteractor
) {

    fun observe(owner: LifecycleOwner, observer: Observer<String>) =
        communicationResult.observe(owner, observer)

    fun observeResult(owner: LifecycleOwner, observer: Observer<CellUi>) =
        updateCommunication.observe(owner, observer)

    fun tap(cellId: CellId) =
        interactor.handle(cellId).map(communicationResult, updateCommunication)

    fun newGame() =
        interactor.reset().map(communicationResult, updateCommunication)

    fun init() =
        interactor.init().map(communicationResult, updateCommunication)
}