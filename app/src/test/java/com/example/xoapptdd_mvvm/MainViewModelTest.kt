package com.example.xoapptdd_mvvm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {

    @Test
    fun `test first player wins`() {
        val communicationResult = TestCommunication()
        val communicationUpdate = TestUpdateCommunication()
        val interactor = MainInteractor.Base()
        val viewModel = MainViewModel(communicationResult, communicationUpdate, interactor)

        viewModel.tap(CellId.UpLeft)
        assertEquals("Player B, go!", communicationResult.text)
        assertEquals(
            CellUi.X(CellId.UpLeft),
            communicationUpdate.value
        )

        viewModel.tap(CellId.UpMiddle)
        assertEquals("Player A, go!", communicationResult.text)
        assertEquals(
            CellUi.O(CellId.UpMiddle),
            communicationUpdate.value
        )

        viewModel.tap(CellId.MiddleMiddle)
        assertEquals("Player B, go!", communicationResult.text)
        assertEquals(
            CellUi.X(CellId.MiddleMiddle),
            communicationUpdate.value
        )

        viewModel.tap(CellId.MiddleRight)
        assertEquals("Player A, go!", communicationResult.text)
        assertEquals(
            CellUi.O(CellId.MiddleRight),
            communicationUpdate.value
        )

        viewModel.tap(CellId.BottomRight)
        assertEquals("Player A wins!", communicationResult.text)
        assertEquals(
            CellUi.X(CellId.BottomRight),
            communicationUpdate.value
        )
        assertEquals(5, communicationResult.count)
        assertEquals(5, communicationUpdate.count)

        viewModel.tap(CellId.BottomLeft)
        assertEquals(5, communicationResult.count)
        assertEquals(5, communicationUpdate.count)
    }

    @Test
    fun `test first player taps twice`() {
        val communicationResult = TestCommunication()
        val communicationUpdate = TestUpdateCommunication()
        val interactor = MainInteractor.Base()
        val viewModel = MainViewModel(communicationResult, communicationUpdate, interactor)

        viewModel.tap(CellId.UpLeft)
        assertEquals("Player B, go!", communicationResult.text)
        assertEquals(1, communicationResult.count)
        assertEquals(
            CellUi.X(CellId.UpLeft),
            communicationUpdate.value
        )
        assertEquals(1, communicationUpdate.count)

        viewModel.tap(CellId.UpLeft)
        assertEquals(1, communicationResult.count)
        assertEquals(1, communicationUpdate.count)
    }

    class TestCommunication : ResultCommunication {

        var count = 0

        var text = ""

        override fun map(data: String) {
            text = data
            count++
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<String>) = Unit
    }

    class TestUpdateCommunication : UpdateCommunication {

        var value: CellUi = CellUi.Empty
        var count = 0

        override fun map(data: CellUi) {
            value = data
            count++
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<CellUi>) = Unit
    }
}