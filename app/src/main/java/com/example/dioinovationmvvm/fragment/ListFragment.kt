package com.example.dioinovationmvvm.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.dioinovationmvvm.R
import com.example.dioinovationmvvm.adapter.TaskListAdapter
import com.example.dioinovationmvvm.application.TaskApplication
import com.example.dioinovationmvvm.databinding.FragmentListBinding
import com.example.dioinovationmvvm.viewmodel.TaskViewModel

class ListFragment : Fragment() {

    private val adapter by lazy { TaskListAdapter() }
    private val binding by lazy { FragmentListBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(requireActivity(), TaskViewModel.Factory(
        TaskApplication.getInstance()
    ))[TaskViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setipRecycleView()
        setObserver()
        insertListeners()
        return binding.root
    }

        private fun insertListeners() {
        binding.btFloat.setOnClickListener {
                Toast.makeText(requireContext(), "float", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_listFragment_to_addTaskFragment)
        }

        adapter.listenerDelete = { task ->
            viewModel.deleteTask(task)
        }

        adapter.listenerEdit = {
//            val intent = Intent(this, AddTaskActivity::class.java)
//            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
//            startActivityForResult(intent, CREATE_NEW_TASK)
        }
   }

    private fun setObserver() {
        viewModel.tasks.observe(requireActivity(), Observer { task ->
            adapter.submitList(task)
        })
    }



    private fun setipRecycleView() {
        binding.recycleView.adapter = adapter
    }


}