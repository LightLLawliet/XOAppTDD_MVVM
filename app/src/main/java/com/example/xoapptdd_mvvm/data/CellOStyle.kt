package com.example.xoapptdd_mvvm.data

import android.widget.ImageButton

abstract class CellOStyle(private val id: CellId) : CellUi() {
    private val oImageResource = CellImageResource.O()

    override fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication,
        message: String
    ) {
        communicationResult.map(message)
        updateCommunication.map(this)
    }

    override fun show(viewsList: Map<CellId, ImageButton>) {
        viewsList[id]?.let { oImageResource.apply(it) }
    }
}