package com.example.dioinovationmvvm
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dioinovationmvvm.adapter.TaskListAdapter
import com.example.dioinovationmvvm.application.TaskApplication
import com.example.dioinovationmvvm.databinding.ActivityMainBinding
import com.example.dioinovationmvvm.viewmodel.TaskViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }
    private val viewModel by lazy { ViewModelProvider(this, TaskViewModel.Factory(
        TaskApplication.getInstance()
    )).get(TaskViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycleView.adapter = adapter

        insertListeners()
        updateList()
    }

    private fun updateList() {
        viewModel.Tasks.observe(this, Observer { task ->
            adapter.submitList(task)
        })
    }

    private fun insertListeners() {
        binding.btFloat.setOnClickListener {
           startActivityForResult(Intent(this, AddTaskActivity::class.java), CREATE_NEW_TASK)
        }

        adapter.listenerDelete = { task ->
            viewModel.deleteTask(task)

        }

        adapter.listenerEdit = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {
        }
    }

    companion object {
        private const val CREATE_NEW_TASK = 1000
    }


}

