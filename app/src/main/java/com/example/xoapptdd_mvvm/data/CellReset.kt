package com.example.xoapptdd_mvvm.data

import android.widget.ImageButton

abstract class CellReset(private val dataMap: Map<CellId, Int>) : CellUi() {

    private val xResource = CellImageResource.XStyle()
    private val oResource = CellImageResource.OStyle()
    private val emptyResource = CellImageResource.Empty()

    override fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication,
        message: String
    ) {
        communicationResult.map(message)
        updateCommunication.map(this)
    }

    override fun show(viewsList: Map<CellId, ImageButton>) {
        viewsList.forEach {
            val resource = when (dataMap[it.key]) {
                0 -> emptyResource
                1 -> xResource
                else -> oResource
            }
            resource.apply(it.value)
        }
    }
}