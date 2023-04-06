package com.example.xoapptdd_mvvm.data

import android.widget.ImageButton

abstract class CellXStyle(private val id: CellId) : CellUi() {
    private val xResource = CellImageResource.XStyle()

    override fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication,
        message: String
    ) {
        communicationResult.map(message)
        updateCommunication.map(this)
    }

    override fun show(viewsList: Map<CellId, ImageButton>) {
        viewsList[id]?.let { xResource.apply(it) }
    }
}
