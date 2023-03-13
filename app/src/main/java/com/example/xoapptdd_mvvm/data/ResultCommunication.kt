package com.example.xoapptdd_mvvm.data

interface ResultCommunication : Communication<String> {
    class Base : Communication.Base<String>(), ResultCommunication
}