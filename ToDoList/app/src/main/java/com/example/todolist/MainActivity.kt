package com.example.todolist


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskInput: EditText
    private lateinit var addButton: Button
    private lateinit var taskList: LinearLayout
    private val tasks = mutableListOf<Task>()
    private val prefsKey = "task_list"
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskInput = findViewById(R.id.edit_task)
        addButton = findViewById(R.id.btn_add)
        taskList = findViewById(R.id.task_list)

        loadTasks()
        renderTasks()

        addButton.setOnClickListener {
            val text = taskInput.text.toString().trim()
            if (text.isNotEmpty()) {
                val newTask = Task(UUID.randomUUID().toString(), text, false)
                tasks.add(0, newTask)
                saveTasks()
                renderTasks()
                taskInput.text.clear()
            } else {
                Toast.makeText(this, "Enter a task", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun renderTasks() {
        taskList.removeAllViews()
        if (tasks.isEmpty()) {
            val emptyText = TextView(this).apply {
                text = "No tasks yet. Add one above!"
                setPadding(16, 32, 16, 32)
                gravity = android.view.Gravity.CENTER
                setTextColor(resources.getColor(android.R.color.darker_gray, null))
            }
            taskList.addView(emptyText)
        } else {
            for (task in tasks) {
                val view = LayoutInflater.from(this).inflate(R.layout.task_item, null)
                val checkbox = view.findViewById<CheckBox>(R.id.task_checkbox)
                val deleteBtn = view.findViewById<ImageButton>(R.id.btn_delete)

                checkbox.text = task.text
                checkbox.isChecked = task.completed
                checkbox.setOnCheckedChangeListener { _, isChecked ->
                    task.completed = isChecked
                    saveTasks()
                }

                deleteBtn.setOnClickListener {
                    tasks.remove(task)
                    saveTasks()
                    renderTasks()
                }

                taskList.addView(view)
            }
        }
    }

    private fun saveTasks() {
        val json = gson.toJson(tasks)
        getPreferences(Context.MODE_PRIVATE).edit().putString(prefsKey, json).apply()
    }

    private fun loadTasks() {
        val json = getPreferences(Context.MODE_PRIVATE).getString(prefsKey, null)
        if (!json.isNullOrEmpty()) {
            try {
                val type = object : TypeToken<MutableList<Task>>() {}.type
                val loadedTasks: MutableList<Task> = gson.fromJson(json, type)
                tasks.clear()
                tasks.addAll(loadedTasks)
            } catch (e: Exception) {
                e.printStackTrace()
                tasks.clear()
            }
        }
    }
}
