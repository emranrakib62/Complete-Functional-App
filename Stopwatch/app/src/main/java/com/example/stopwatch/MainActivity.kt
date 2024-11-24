package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Timer state variables
    private var isRunning = false
    private var timerSeconds = 0

    // Handler and Runnable to update the timer
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            timerSeconds++
            val hours = timerSeconds / 3600
            val minutes = (timerSeconds % 3600) / 60
            val seconds = timerSeconds % 60

            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            binding.timertext.text = time

            // Schedule the next update after 1 second
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start button logic
        binding.startbtn.setOnClickListener {
            startTimer()
        }

        // Stop button logic
        binding.stopbtn.setOnClickListener {
            stopTimer()
        }

        // Reset button logic
        binding.reset.setOnClickListener {
            resetTimer()
        }

        // Initialize button states
        updateButtonStates()
    }

    private fun startTimer() {
        if (!isRunning) {
            handler.postDelayed(runnable, 1000)
            isRunning = true
            binding.startbtn.text = "Pause"
            updateButtonStates()
        } else {
            stopTimer() // Pause functionality
        }
    }

    private fun stopTimer() {
        if (isRunning) {
            handler.removeCallbacks(runnable)
            isRunning = false
            binding.startbtn.text = "Resume"
            updateButtonStates()
        }
    }

    private fun resetTimer() {
        stopTimer()
        timerSeconds = 0
        binding.timertext.text = "00:00:00"
        binding.startbtn.text = "Start"
        updateButtonStates()
    }

    private fun updateButtonStates() {
        binding.startbtn.isEnabled = true
        binding.stopbtn.isEnabled = isRunning
        binding.reset.isEnabled = timerSeconds > 0
    }
}
