package com.example.xoapptdd_mvvm.presentation

import android.content.Context
import androidx.annotation.StringRes

interface ManageResources {

    fun string(@StringRes id: Int): String

    class Base(private val context: Context) : ManageResources {
        override fun string(@StringRes id: Int) = context.getString(id)
    }
}