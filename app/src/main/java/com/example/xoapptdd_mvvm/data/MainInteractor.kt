package com.example.xoapptdd_mvvm.data

import com.example.xoapptdd_mvvm.presentation.ResultUi

interface MainInteractor {

    fun handle(cellId: CellId): ResultUi
    fun reset(): ResultUi
    fun init(): ResultUi

    class Base(private val repository: MainRepository) : MainInteractor {

        override fun handle(cellId: CellId) =
            if (repository.isGameOver())
                ResultUi("", CellUi.Empty)
            else {
                if (repository.cellEmpty(cellId)) {
                    repository.updateCell(cellId)
                    var message = repository.updateMessage()
                    val upRow = repository.winCase(CellId.UpLeft, CellId.UpMiddle, CellId.UpRight)
                    val midRow = repository.winCase(
                        CellId.MiddleLeft,
                        CellId.MiddleMiddle,
                        CellId.MiddleRight
                    )
                    val downRow = repository.winCase(
                        CellId.BottomLeft,
                        CellId.BottomMiddle,
                        CellId.BottomRight
                    )
                    val diagonalLeft = repository.winCase(
                        CellId.UpLeft,
                        CellId.MiddleMiddle,
                        CellId.BottomRight
                    )
                    val diagonalRight = repository.winCase(
                        CellId.UpRight,
                        CellId.MiddleMiddle,
                        CellId.BottomLeft
                    )
                    val leftColumn = repository.winCase(
                        CellId.UpLeft,
                        CellId.MiddleLeft,
                        CellId.BottomLeft
                    )
                    val midColumn = repository.winCase(
                        CellId.UpMiddle,
                        CellId.MiddleMiddle,
                        CellId.BottomMiddle
                    )
                    val rightColumn = repository.winCase(
                        CellId.UpRight,
                        CellId.MiddleRight,
                        CellId.BottomRight
                    )
                    if (
                        upRow || midRow || downRow ||
                        diagonalLeft || diagonalRight ||
                        leftColumn || rightColumn || midColumn
                    ) {
                        val firstWon = when {
                            diagonalLeft || diagonalRight || midRow || midColumn ->
                                repository.firstPlayerWon(CellId.MiddleMiddle)
                            upRow || leftColumn -> repository.firstPlayerWon(CellId.UpLeft)
                            upRow || midColumn -> repository.firstPlayerWon(CellId.UpMiddle)
                            upRow || rightColumn -> repository.firstPlayerWon(CellId.UpRight)
                            midRow || leftColumn -> repository.firstPlayerWon(CellId.MiddleLeft)
                            midRow || rightColumn -> repository.firstPlayerWon(CellId.MiddleRight)
                            downRow || leftColumn -> repository.firstPlayerWon(CellId.BottomLeft)
                            downRow || midColumn -> repository.firstPlayerWon(CellId.BottomMiddle)
                            downRow || rightColumn -> repository.firstPlayerWon(CellId.BottomRight)
                            else -> false
                        }

                        message = repository.playerWinMessage(firstWon)
                    }

                    if (!repository.isGameOver()) {
                        if (repository.checkGameOver()) {
                            message = repository.gameOverMessage()
                        }
                    }

                    val cell = repository.cell(cellId)
                    repository.updateCurrentPlayer()
                    ResultUi(message, cell)
                } else
                    ResultUi("", CellUi.Empty)
            }

        override fun reset(): ResultUi {
            repository.reset()
            return init()
        }

        override fun init() = repository.init()
    }
}