package com.example.dioinovationmvvm.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dioinovationmvvm.R
import com.example.dioinovationmvvm.adapter.TaskListAdapter
import com.example.dioinovationmvvm.databinding.FragmentListBinding
import com.example.dioinovationmvvm.data.local.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val adapter by lazy { TaskListAdapter {
        updateTaskOtherWay(it)
    } }
    private val binding by lazy { FragmentListBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<TaskViewModel>()

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

    private fun updateTaskOtherWay(it: Task) {
        val action = ListFragmentDirections.actionListFragmentToEditFragment(it)
            findNavController().navigate(action)
    }

    private fun insertListeners() {
        binding.btFloat.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addTaskFragment)
        }

        adapter.listenerDelete = { task ->
            viewModel.deleteTask(task)
            Toast.makeText(requireContext(), "Tarefa deletada", Toast.LENGTH_LONG).show()
        }
   }

    private fun setObserver() {
        viewModel.showAllTasks().observe(requireActivity()) { task ->
            adapter.submitList(task)
        }
    }

    private fun setipRecycleView() {
        binding.recycleView.adapter = adapter
    }
}