package com.example.dioinovationmvvm.fragment.listtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.dioinovationmvvm.R
import com.example.dioinovationmvvm.adapter.TaskListAdapter
import com.example.dioinovationmvvm.databinding.FragmentListBinding
import com.example.dioinovationmvvm.model.Task
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private val adapter by lazy { TaskListAdapter {
        updateTaskOtherWay(it)
    } }
    private val binding by lazy { FragmentListBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<TaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpRecycleView()
        setObserver()
        insertListeners()
        return binding.root
    }

    private fun updateTaskOtherWay(it: Task) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToEditFragment(it))
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
        viewModel.listTask().observe(requireActivity()) { task ->
            adapter.submitList(task)
        }
    }

    private fun setUpRecycleView() {
        binding.recycleView.adapter = adapter
    }
}