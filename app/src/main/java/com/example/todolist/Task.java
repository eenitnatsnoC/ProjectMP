package com.example.todolist;

public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false; // Default to not done
    }

    public String getname() {
        return name;
    } //retrieve the name of the task.

    public boolean isDone() {
        return isDone;
    } //check if the task is done or not.

    public void setDone(boolean done) {
        isDone = done;
    } //set the done status for the task

}
