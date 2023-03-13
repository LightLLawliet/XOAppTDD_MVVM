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

        viewModel.tap(CellId.upLeft)
        assertEquals("Player B, go!", communicationResult.text)
        assertEquals(
            CellUi.X(CellId.upLeft),
            communicationUpdate.value
        )

        viewModel.tap(CellId.upMiddle)
        assertEquals("Player A, go!", communicationResult.text)
        assertEquals(
            CellUi.O(CellId.upMiddle),
            communicationUpdate.value
        )

        viewModel.tap(CellId.middleMiddle)
        assertEquals("Player B, go!", communicationResult.text)
        assertEquals(
            CellUi.X(CellId.middleMiddle),
            communicationUpdate.value
        )

        viewModel.tap(CellId.middleRight)
        assertEquals("Player A, go!", communicationResult.text)
        assertEquals(
            CellUi.O(CellId.middleRight),
            communicationUpdate.value
        )

        viewModel.tap(CellId.bottomRight)
        assertEquals("Player A wins!", communicationResult.text)
        assertEquals(
            CellUi.X(CellId.bottomRight),
            communicationUpdate.value
        )
    }

    @Test
    fun `test first player taps twice`() {
        val communicationResult = TestCommunication()
        val communicationUpdate = TestUpdateCommunication()
        val interactor = MainInteractor.Base()
        val viewModel = MainViewModel(communicationResult, communicationUpdate, interactor)

        viewModel.tap(CellId.upLeft)
        assertEquals("Player B, go!", communicationResult.text)
        assertEquals(1, communicationResult.count)
        assertEquals(
            CellUi.X(CellId.upLeft),
            communicationUpdate.value
        )
        assertEquals(1, communicationUpdate.count)

        viewModel.tap(CellId.upLeft)
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