package edu.sda.dao;

import edu.sda.data.Task;

import java.util.Collection;
import java.util.HashMap;

public class TaskDAO {

    private HashMap <String, Task> storage = new HashMap<>();

    public void create(Task task){
        storage.put(task.getTitle(), task);
    }

    public void update(String title, Task task){
        storage.put(title, task);
    }

    public Collection <Task> getAllTasks(){
        return storage.values();
    }

}
