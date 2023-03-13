package com.example.xoapptdd_mvvm.data

import com.example.xoapptdd_mvvm.R
import com.example.xoapptdd_mvvm.presentation.ManageResources
import com.example.xoapptdd_mvvm.presentation.ResultUi

interface MainRepository {

    fun isGameOver(): Boolean

    fun checkGameOver(): Boolean

    fun reset()

    fun init(): ResultUi

    fun cellEmpty(cellId: CellId): Boolean

    fun updateCell(cellId: CellId)

    fun cell(cellId: CellId): CellUi

    fun updateCurrentPlayer()

    fun updateMessage(): String

    fun winCase(first: CellId, second: CellId, third: CellId): Boolean

    fun firstPlayerWon(cellId: CellId): Boolean

    fun gameOverMessage(): String

    fun playerWinMessage(firstWon: Boolean): String

    class Base(private val manageResources: ManageResources) : MainRepository {

        private var currentPlayerIsFirst = true
        private val dataMap = mutableMapOf<CellId, Int>()
        private var gameOver = false
        private var lastMessage = manageResources.string(R.string.player_1_go)

        init {
            CellId.values().forEach {
                dataMap[it] = 0
            }
        }

        override fun isGameOver(): Boolean = gameOver

        override
        fun checkGameOver(): Boolean {
            var count = 0
            dataMap.forEach {
                if (it.value == 0) count++
            }
            gameOver = count == 0
            return isGameOver()
        }

        override
        fun init() = ResultUi(lastMessage, CellUi.Reset(dataMap))

        override
        fun cellEmpty(cellId: CellId) = dataMap[cellId] == 0

        override
        fun updateCell(cellId: CellId) {
            dataMap[cellId] = if (currentPlayerIsFirst) 1 else -1
        }

        override
        fun cell(cellId: CellId) =
            if (currentPlayerIsFirst) CellUi.X(cellId) else CellUi.O(cellId)

        override
        fun updateCurrentPlayer() {
            currentPlayerIsFirst = !currentPlayerIsFirst
        }

        override
        fun updateMessage(): String {
            lastMessage = manageResources.string(
                if (currentPlayerIsFirst)
                    R.string.player_2_go
                else
                    R.string.player_1_go
            )
            return lastMessage
        }

        override
        fun winCase(first: CellId, second: CellId, third: CellId) =
            dataMap[third] != 0 &&
                    dataMap[first] == dataMap[second] &&
                    dataMap[second] == dataMap[third]

        override
        fun firstPlayerWon(cellId: CellId) = dataMap[cellId] == 1

        override
        fun gameOverMessage(): String {
            lastMessage = manageResources.string(R.string.draw)
            return lastMessage
        }

        override
        fun playerWinMessage(firstWon: Boolean): String {
            lastMessage = manageResources.string(
                if (firstWon) R.string.player_1_wins else R.string.player_2_won
            )
            gameOver = true
            return lastMessage
        }

        override
        fun reset() {
            gameOver = false
            lastMessage = manageResources.string(R.string.player_1_go)
            currentPlayerIsFirst = true
            CellId.values().forEach {
                dataMap[it] = 0
            }
        }
    }
}