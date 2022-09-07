package com.example.dioinovationmvvm.fragment.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.dioinovationmvvm.databinding.FragmentEditBinding
import com.example.dioinovationmvvm.extencions.format
import com.example.dioinovationmvvm.extencions.text
import com.example.dioinovationmvvm.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EditFragment : Fragment() {

    private val args : EditFragmentArgs by navArgs()
    private lateinit var task: Task
    private val binding by lazy { FragmentEditBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<EditViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        task = args.dadosTask
        initListenes()
        getthetext()
        return binding.root
    }

    private fun getthetext() {
        binding.editNomeEditTask.text = task.title
        binding.editDataEditTask.text = task.date
        binding.editHoraEditTask.text = task.hour
    }

    private fun initListenes() {
        binding.editDataEditTask.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.editDataEditTask.text = Date(it + offset).format()
            }
            datePicker.show(parentFragmentManager, "DATE_PICKER_TAG")
        }

        binding.editHoraEditTask.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePicker.addOnPositiveButtonClickListener{
                val hora = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val minute = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                binding.editHoraEditTask.text = "${hora}:${minute}"
            }
            timePicker.show(parentFragmentManager, null)
        }

        binding.btCriarEditTask.setOnClickListener {
            updateTask()
        }

        binding.toolbar.setNavigationOnClickListener{
            activity?.onBackPressed()
        }

        binding.btCancelarEditTask.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun updateTask() {
        val id = task.id
        val task = Task(
            id = id,
            title = binding.editNomeEditTask.text,
            date = binding.editDataEditTask.text,
            hour = binding.editHoraEditTask.text
        )
        viewModel.updateTask(task)
        activity?.onBackPressed()
    }
}