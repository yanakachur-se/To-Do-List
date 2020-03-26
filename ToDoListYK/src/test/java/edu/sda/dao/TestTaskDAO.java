package edu.sda.dao;

import edu.sda.data.Task;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestTaskDAO {
    @Test
    public void testCreateTask() {
        TaskDAO taskDAO = new TaskDAO();
        Task task1 = new Task("Task 1", new Date(), "project 1");
        taskDAO.create(task1);
        Task result = taskDAO.getTask("Task 1");
        assertEquals(task1, result);
    }

    @Test
    public void testUpdateExistingTask() {
        TaskDAO taskDAO = new TaskDAO();
        Task task1 = new Task("Task 1", new Date(), "project 1");
        Task task2 = new Task("Task 1", new Date(), "project 2");
        taskDAO.create(task1);
        taskDAO.update("Task 1", task2);
        Task result = taskDAO.getTask("Task 1");
        assertEquals(task2, result);
    }

    @Test
    public void testUpdateTaskInvalidTitle() {
        TaskDAO taskDAO = new TaskDAO();
        Task task1 = new Task("Task 1", new Date(), "project 1");
        Task task2 = new Task("Task 2", new Date(), "project 2");
        taskDAO.create(task1);
        taskDAO.update("Task 2", task2);
        Task result = taskDAO.getTask("Task 1");
        int result2 = taskDAO.getAllTasks().size();
        assertEquals(task1, result);
        assertEquals(1, result2);
    }

    @Test
    public void testIfTaskExistsPositive() {
        TaskDAO taskDAO = new TaskDAO();
        Task task1 = new Task("Task 1", new Date(), "project 1");
        Task task2 = new Task("Task 2", new Date(), "project 2");
        taskDAO.create(task1);
        taskDAO.create(task2);
        boolean result = taskDAO.ifTaskExists("Task 2");
        assertTrue(result);
    }

    @Test
    public void testIfTaskExistsNegative() {
        TaskDAO taskDAO = new TaskDAO();
        Task task1 = new Task("Task 1", new Date(), "project 1");
        Task task2 = new Task("Task 2", new Date(), "project 2");
        taskDAO.create(task1);
        taskDAO.create(task2);
        boolean result = taskDAO.ifTaskExists("Task 3");
        assertFalse(result);
    }

}
