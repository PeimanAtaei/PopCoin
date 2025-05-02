package com.peiman.popcoin.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.peiman.popcoin.databinding.ActivityMainBinding

import com.peiman.popcoin.presentation.viewModel.MainViewModel
import com.peiman.popcoin.presentation.viewModel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * MainActivity serves as the root container for the application's UI.
 * Responsibilities:
 * - Hosts NavHostFragment for navigation.
 * - Initializes shared ViewModel (MainViewModel) for fragments.
 * - Sets up base UI components.
 *
 * @property binding View binding reference for activity_main.xml.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivity"
    }

    // View binding reference for activity_main.xml
    private lateinit var binding: ActivityMainBinding

    // ViewModel for this activity, injected via Hilt
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel using the custom factory
        mainViewModel = ViewModelProvider(this,mainViewModelFactory).get(MainViewModel::class.java)
    }
}