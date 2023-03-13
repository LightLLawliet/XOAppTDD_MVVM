package com.example.xoapptdd_mvvm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class MainViewModel(
    private val communicationResult: ResultCommunication,
    private val updateCommunication: UpdateCommunication,
    private val interactor: MainInteractor
) {

    init {
        val result = interactor.init()
        result.map(communicationResult, updateCommunication)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<String>) {
        communicationResult.observe(owner, observer)
    }

    fun observeResult(owner: LifecycleOwner, observer: Observer<CellUi>) {
        updateCommunication.observe(owner, observer)
    }

    fun tap(cellId: CellId) {
        val result = interactor.handle(cellId)
        result.map(communicationResult, updateCommunication)
    }

    fun newGame() {
        val result = interactor.reset()
        result.map(communicationResult, updateCommunication)
    }
}