package com.example.xoapptdd_mvvm

interface MainInteractor {

    fun handle(cellId: CellId): ResultUi

    class Base : MainInteractor {

        private var currentPlayerIsFirstPlayer = true

        private val dataMap = mutableMapOf<CellId, Int>()

        //1 is X, -1 is O, 0 is null

        init {
            CellId.values().forEach {
                dataMap[it] = 0
            }
        }

        override fun handle(cellId: CellId): ResultUi {

            return if (dataMap[cellId]!! == 0) {
                dataMap[cellId] = if (currentPlayerIsFirstPlayer) 1 else 0

                ResultUi(
                    if (currentPlayerIsFirstPlayer) PLAYER_2_GO else PLAYER_1_GO,
                    if (currentPlayerIsFirstPlayer) CellUi.X(cellId) else CellUi.O(cellId)
                )
            } else {
                ResultUi("", CellUi.Empty)
            }
        }

        companion object {
            private const val PLAYER_1_GO = "Player 1, go!"
            private const val PLAYER_2_GO = "Player 2, go!"
            private const val PLAYER_1_WINS = "Player 1 wins!"
            private const val PLAYER_2_WINS = "Player 2 wins!"
        }
    }
}

data class ResultUi(
    private val message: String,
    private val cellUi: CellUi
)