package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.taskCardView.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_taskFragment)
        }
        binding.statisticsCardView.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_statisticsFragment)
        }
        binding.weatherCardView.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_weatherFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}