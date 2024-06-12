
package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> tasks;

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTaskName;
        public CheckBox checkBoxDone;

        public TaskViewHolder(View itemView) {
            super(itemView);
            textViewTaskName = itemView.findViewById(R.id.textViewTaskName);
            checkBoxDone = itemView.findViewById(R.id.checkBoxDone);
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.textViewTaskName.setText(task.getname());
        holder.checkBoxDone.setChecked(task.isDone());
        holder.checkBoxDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setDone(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyItemInserted(tasks.size() - 1);
    }

    public void clearTasks() {
        tasks.clear();
        notifyDataSetChanged();
    }

    public void removeTask(int position) {
        tasks.remove(position);
        notifyItemRemoved(position);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void removeDoneTasks() {
        for (int i = tasks.size() - 1; i >= 0; i--) {
            if (tasks.get(i).isDone()) {
                tasks.remove(i);
                notifyItemRemoved(i);
            }
        }
    }
}