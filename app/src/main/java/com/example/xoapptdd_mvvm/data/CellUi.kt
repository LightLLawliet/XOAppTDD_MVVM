package com.example.xoapptdd_mvvm.data

import android.widget.ImageButton

abstract class CellUi {

    abstract fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication,
        message: String
    )

    abstract fun show(viewsList: Map<CellId, ImageButton>)

    data class X(private val Id: CellId) : CellXStyle(Id)

    data class O(private val Id: CellId) : CellOStyle(Id)

    data class Reset(private val dataMap: Map<CellId, Int>) : CellReset(dataMap)

    object Empty : EmptyCell()
}