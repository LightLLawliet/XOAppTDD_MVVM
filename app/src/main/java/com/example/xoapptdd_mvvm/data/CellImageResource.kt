package com.example.xoapptdd_mvvm.data

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.example.xoapptdd_mvvm.R

interface CellImageResource {

    fun apply(imageView: ImageView)

    abstract class Base(@DrawableRes private val id: Int) : CellImageResource {
        override fun apply(imageView: ImageView) = imageView.setImageResource(id)
    }

    class X : Base(R.drawable.xstyle)
    class O : Base(R.drawable.ostyle)
    class Empty : Base(0)
}