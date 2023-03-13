package com.example.xoapptdd_mvvm.data

import android.widget.ImageButton

abstract class EmptyCell : CellUi() {
    override fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication,
        message: String
    ) = Unit

    override fun show(viewsList: Map<CellId, ImageButton>) = Unit
}