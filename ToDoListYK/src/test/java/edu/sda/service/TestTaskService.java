package edu.sda.service;

import edu.sda.dao.TaskDAO;
import edu.sda.data.Status;
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

    @Test
    public void testSortByDate() {
        TaskService taskService = new TaskService();
        Task task1 = new Task("Task 1", new Date(0), "project 1");
        Task task2 = new Task("Task 2", new Date(100), "project 1");
        Task task3 = new Task("Task 3", new Date(2), "project 3");
        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);
        Collection <Task> expResult = new ArrayList<>();
        expResult.add(task1);
        expResult.add(task3);
        expResult.add(task2);
        Collection<Task> result = taskService.sortByDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testRemove() {
        TaskService taskService = new TaskService();
        Task task1 = new Task("Task 1", new Date(), "project 1");
        Task task2 = new Task("Task 2", new Date(), "project 1");
        Task task3 = new Task("Task 3", new Date(), "project 3");
        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);
        taskService.remove("Task 1");
        int result = taskService.getAllTasks().size();
        assertEquals(2, result);
    }
    @Test
    public void testCountTasksDone() {
        TaskService taskService = new TaskService();
        Task task1 = new Task("Task 1", new Date(), "project 1", Status.TO_DO);
        Task task2 = new Task("Task 2", new Date(), "project 1", Status.DONE);
        Task task3 = new Task("Task 3", new Date(), "project 3", Status.DONE);
        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);
        int result = taskService.countTasksDone();
        assertEquals(2, result);
    }

    @Test
    public void testCountTasksDoneWhenNoTasksDone() {
        TaskService taskService = new TaskService();
        Task task1 = new Task("Task 1", new Date(), "project 1", Status.TO_DO);
        Task task2 = new Task("Task 2", new Date(), "project 1", Status.TO_DO);
        Task task3 = new Task("Task 3", new Date(), "project 3", Status.TO_DO);
        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);
        int result = taskService.countTasksDone();
        assertEquals(0, result);
    }

    @Test
    public void testCountTasksToDo() {
        TaskService taskService = new TaskService();
        Task task1 = new Task("Task 1", new Date(), "project 1", Status.TO_DO);
        Task task2 = new Task("Task 2", new Date(), "project 1", Status.DONE);
        Task task3 = new Task("Task 3", new Date(), "project 3", Status.DONE);
        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);
        int result = taskService.countTasksToDo();
        assertEquals(1, result);
    }

}
