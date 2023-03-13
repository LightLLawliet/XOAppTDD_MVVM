package com.example.xoapptdd_mvvm

abstract class CellUi {

    data class X(private val id: CellId) : CellUi() {

    }

    data class O(private val id: CellId) : CellUi() {

    }

    object Empty : CellUi() {

    }
}