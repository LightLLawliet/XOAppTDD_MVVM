package com.example.xoapptdd_mvvm.presentation

import com.example.xoapptdd_mvvm.data.CellUi
import com.example.xoapptdd_mvvm.data.ResultCommunication
import com.example.xoapptdd_mvvm.data.UpdateCommunication

data class ResultUi(
    private val message: String,
    private val cell: CellUi
) {
    fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication
    ) = cell.map(communicationResult, updateCommunication, message)
}