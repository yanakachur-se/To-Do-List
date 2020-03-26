package edu.sda.service;

import edu.sda.dao.TaskDAO;
import edu.sda.data.Task;
import edu.sda.parser.Parser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestTaskService {
    @Test
    public void testFilterByProject() {
        Parser parser = new Parser();
        TaskService taskService = new TaskService();
        Task task1 = new Task("Task 1", new Date(), "project 1");
        Task task2 = new Task("Task 2", new Date(), "project 1");
        Task task3 = new Task("Task 3", new Date(), "project 3");
        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);
        Collection <Task> expResult = new ArrayList<>();
        expResult.add(task2);
        expResult.add(task1);
        Collection<Task> result = taskService.filterByProject("project 1");
        assertEquals(expResult, result);
    }


}
