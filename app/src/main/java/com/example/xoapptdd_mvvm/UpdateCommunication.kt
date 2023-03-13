package com.example.xoapptdd_mvvm

interface UpdateCommunication : Communication<CellUi> {

    class Base : Communication.Base<CellUi>(), UpdateCommunication
}