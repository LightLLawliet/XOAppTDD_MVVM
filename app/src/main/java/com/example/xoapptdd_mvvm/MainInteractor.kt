package com.example.xoapptdd_mvvm

interface MainInteractor {

    fun handle(cellId: CellId): ResultUi

    fun reset(): ResultUi

    fun init(): ResultUi

    class Base : MainInteractor {

        private var currentPlayerIsFirstPlayer = true
        private val dataMap = mutableMapOf<CellId, Int>()
        private var gameOver = false

        //1 is X, -1 is O, 0 is null
        init {
            CellId.values().forEach {
                dataMap[it] = 0
            }
        }

        override fun handle(cellId: CellId): ResultUi {
            return if (gameOver) {
                ResultUi("", CellUi.Empty)
            } else {
                if (dataMap[cellId]!! == 0) {
                    dataMap[cellId] = if (currentPlayerIsFirstPlayer) 1 else 0

                    var message = if (currentPlayerIsFirstPlayer) PLAYER_2_GO else PLAYER_1_GO

                    val upRow = dataMap[CellId.UpLeft] == dataMap[CellId.UpMiddle] &&
                            dataMap[CellId.UpMiddle] == dataMap[CellId.UpRight] && dataMap[CellId.UpRight] != 0
                    val middleRow = dataMap[CellId.MiddleLeft] == dataMap[CellId.MiddleMiddle] &&
                            dataMap[CellId.MiddleMiddle] == dataMap[CellId.MiddleRight] && dataMap[CellId.MiddleRight] != 0
                    val downRow = dataMap[CellId.BottomLeft] == dataMap[CellId.BottomMiddle] &&
                            dataMap[CellId.BottomMiddle] == dataMap[CellId.BottomRight] && dataMap[CellId.BottomRight] != 0
                    val diagonalLeft = dataMap[CellId.UpLeft] == dataMap[CellId.MiddleMiddle] &&
                            dataMap[CellId.MiddleMiddle] == dataMap[CellId.BottomRight] && dataMap[CellId.BottomRight] != 0
                    val diagonalRight = dataMap[CellId.UpRight] == dataMap[CellId.MiddleMiddle] &&
                            dataMap[CellId.MiddleMiddle] == dataMap[CellId.BottomLeft] && dataMap[CellId.BottomLeft] != 0

                    if (upRow ||
                        middleRow ||
                        downRow ||
                        diagonalLeft ||
                        diagonalRight
                    ) {
                        var firstWins = false
                        if (diagonalLeft || diagonalRight) {
                            firstWins = dataMap[CellId.MiddleMiddle] == 1
                        }
                        message = if (firstWins) PLAYER_1_WINS else PLAYER_2_WINS
                        gameOver = true
                    }
                    val cell =
                        if (currentPlayerIsFirstPlayer) CellUi.X(cellId) else CellUi.O(cellId)
                    currentPlayerIsFirstPlayer = !currentPlayerIsFirstPlayer
                    ResultUi(
                        message,
                        cell
                    )
                } else {
                    ResultUi("", CellUi.Empty)
                }
            }
        }

        override fun reset(): ResultUi {
            gameOver = false
            currentPlayerIsFirstPlayer = true
            CellId.values().forEach {
                dataMap[it] = 0
            }
            return ResultUi(PLAYER_1_GO, CellUi.Reset())
        }

        override fun init(): ResultUi {
            return ResultUi(PLAYER_1_GO, CellUi.Reset())
        }

        companion object {
            private const val PLAYER_1_GO = "Player A, go!"
            private const val PLAYER_2_GO = "Player B, go!"
            private const val PLAYER_1_WINS = "Player A wins!"
            private const val PLAYER_2_WINS = "Player B wins!"
        }
    }
}

data class ResultUi(
    private val message: String,
    private val cell: CellUi
) {

    fun map(
        communicationResult: ResultCommunication,
        updateCommunication: UpdateCommunication
    ) {
        cell.map(communicationResult, updateCommunication, message)
    }
}