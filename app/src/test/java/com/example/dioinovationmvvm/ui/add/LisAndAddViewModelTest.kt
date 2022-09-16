package com.example.dioinovationmvvm.ui.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dioinovationmvvm.MaincoroutineRule
import com.example.dioinovationmvvm.getOrAwaitValueTest
import com.example.dioinovationmvvm.others.Status
import com.example.dioinovationmvvm.repository.FakeRepository
import com.example.dioinovationmvvm.ui.list.TaskViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LisAndAddViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MaincoroutineRule()

    private lateinit var viewModel: AddViewModel

    @Before
    fun setup() {
        viewModel = AddViewModel(FakeRepository())
    }

    @Test
    fun `insert task item with empty field, return error`() {
        viewModel.verifyTaskValue("", "14:00", "14/5")
        val value = viewModel.taskStatus.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert task item with everything ok, return success`() {
        viewModel.verifyTaskValue("name","14:00","145")
        val value  = viewModel.taskStatus.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }
}