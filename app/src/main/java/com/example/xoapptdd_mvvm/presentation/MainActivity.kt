package com.example.xoapptdd_mvvm.presentation

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.xoapptdd_mvvm.data.CellId
import com.example.xoapptdd_mvvm.R
import com.example.xoapptdd_mvvm.data.XOApp

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val viewsList = mutableMapOf<CellId, ImageButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as XOApp).mainViewModel

        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        gridLayout.children.forEachIndexed { index, view ->
            viewsList[CellId.values()[index]] = view as ImageButton
        }

        viewsList.forEach { (cellId, imageButton) ->
            imageButton.setOnClickListener { viewModel.tap(cellId) }
        }

        val textView = findViewById<TextView>(R.id.resultTextView)
        viewModel.observe(this) {
            textView.text = it
        }

        viewModel.observeResult(this) {
            it.show(viewsList)
        }

        val newGameButton = findViewById<View>(R.id.newGameButton)
        newGameButton.setOnClickListener {
            viewModel.newGame()
        }

        viewModel.init()
    }
}