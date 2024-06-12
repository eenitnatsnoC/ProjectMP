package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TaskAdapter taskAdapter;
    private RecyclerView recyclerViewTasks;
    private EditText editTextTask;
    private Button buttonAddTask;
    private Button buttonClearTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        buttonClearTasks = findViewById(R.id.buttonClearTasks);

        taskAdapter = new TaskAdapter(new ArrayList<>());
        recyclerViewTasks.setAdapter(taskAdapter);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // Kode dieksekusi ketika tombol "Tambah Tugas" diklik
                String taskName = editTextTask.getText().toString();
                if (!taskName.isEmpty()) {
                    Task task = new Task(taskName);
                    taskAdapter.addTask(task);
                    editTextTask.setText("");
                }
            }
        });

        buttonClearTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskAdapter.clearTasks(); // Kode yang dieksekusi ketika tombol "Clear Task" diklik
            }
        });

        // ItemTouchHelper for swipe-to-delete functionality
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) { // Kode dieksekusi ketika sebuah task digeser
                int position = viewHolder.getAdapterPosition();
                taskAdapter.removeTask(position);
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerViewTasks);
    }
}
