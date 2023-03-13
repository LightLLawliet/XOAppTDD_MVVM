package com.example.xoapptdd_mvvm

import android.widget.ImageButton

abstract class CellUi {
    abstract fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication,
        message: String
    )

    abstract fun show(viewsList: MutableMap<CellId, ImageButton>)

    data class X(private val id: CellId) : CellUi() {
        override fun map(
            communicationResult: ResultCommunication,
            updateCommunication: UpdateCommunication,
            message: String
        ) {
            communicationResult.map(message)
            updateCommunication.map(this)
        }

        override fun show(viewsList: MutableMap<CellId, ImageButton>) {
            val imageView = viewsList[id]
            imageView?.setImageResource(R.drawable.xstyle)
        }
    }

    data class O(private val id: CellId) : CellUi() {
        override fun map(
            communicationResult: ResultCommunication,
            updateCommunication: UpdateCommunication,
            message: String
        ) {
            communicationResult.map(message)
            updateCommunication.map(this)
        }

        override fun show(viewsList: MutableMap<CellId, ImageButton>) {
            val imageView = viewsList[id]
            imageView?.setImageResource(R.drawable.ostyle)
        }
    }

    object Empty : CellUi() {
        override fun map(
            communicationResult: ResultCommunication,
            updateCommunication: UpdateCommunication,
            message: String
        ) = Unit

        override fun show(viewsList: MutableMap<CellId, ImageButton>) = Unit
    }

    class Reset : CellUi() {
        override fun map(
            communicationResult: ResultCommunication,
            updateCommunication: UpdateCommunication,
            message: String
        ) {
            communicationResult.map(message)
            updateCommunication.map(this)
        }

        override fun show(viewsList: MutableMap<CellId, ImageButton>) {
            viewsList.forEach {
                it.value.setImageResource(0)
            }
        }
    }
}