package com.example.dioinovationmvvm.fragment.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.dioinovationmvvm.application.TaskApplication
import com.example.dioinovationmvvm.databinding.FragmentAddTaskBinding
import com.example.dioinovationmvvm.extencions.format
import com.example.dioinovationmvvm.extencions.text
import com.example.dioinovationmvvm.model.Task
import com.example.dioinovationmvvm.fragment.list.TaskViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddTaskFragment : Fragment() {

    private val binding by lazy { FragmentAddTaskBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<AddViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        insertListeners()
        return binding.root
    }

    private fun insertListeners() {
        binding.editData.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.editData.text = Date(it + offset).format()
            }
            datePicker.show(parentFragmentManager, "DATE_PICKER_TAG")
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
            timePicker.show(parentFragmentManager, null)
        }

        binding.toolbar.setNavigationOnClickListener{
            activity?.onBackPressed()
        }

        binding.btCancelar.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.btCriar.setOnClickListener {
            val task = Task(
                id = 0,
                title = binding.editNome.text,
                date = binding.editData.text,
                hour = binding.editHora.text
            )
            viewModel.insertTask(task)
            Toast.makeText(requireContext(),"Task Criada", Toast.LENGTH_LONG).show()
            activity?.onBackPressed()
        }
    }
}