package com.example.dioinovationmvvm

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dioinovationmvvm.application.TaskApplication
import com.example.dioinovationmvvm.databinding.ActivityAddTaskBinding
import com.example.dioinovationmvvm.datasourse.TaskDatabase
import com.example.dioinovationmvvm.extencions.format
import com.example.dioinovationmvvm.extencions.text
import com.example.dioinovationmvvm.model.Task
import com.example.dioinovationmvvm.viewmodel.TaskViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    private val viewModel by lazy { ViewModelProvider(this, TaskViewModel.Factory(
        TaskApplication.getInstance()
    )).get(TaskViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        binding.editData.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.editData.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.editHora.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePicker.addOnPositiveButtonClickListener{
                val hora = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val minute = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                binding.editHora.text = "${hora}:${minute}"
            }
            timePicker.show(supportFragmentManager, null)
        }

        binding.btCancelar.setOnClickListener {
            finish()
        }

        binding.btCriar.setOnClickListener {
            val task = Task(
                title = binding.editNome.text,
                date = binding.editData.text,
                hour = binding.editHora.text,
                id = intent.getIntExtra(TASK_ID, 0)
            )
            viewModel.insertTask(task)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        const val TASK_ID = "task_id"
    }
}