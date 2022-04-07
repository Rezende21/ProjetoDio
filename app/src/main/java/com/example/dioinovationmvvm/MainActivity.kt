package com.example.dioinovationmvvm
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.dioinovationmvvm.adapter.TaskListAdapter
import com.example.dioinovationmvvm.application.TaskApplication
import com.example.dioinovationmvvm.databinding.ActivityMainBinding
import com.example.dioinovationmvvm.viewmodel.TaskViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment : NavHostFragment
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initFragment()

    }

    private fun initFragment() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentListView) as NavHostFragment
        navHostFragment.navController

    }

}

