package com.example.xoapptdd_mvvm.data

interface UpdateCommunication : Communication<CellUi> {

    class Base : Communication.Base<CellUi>(), UpdateCommunication
}