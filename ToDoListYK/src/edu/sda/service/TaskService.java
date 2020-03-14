package edu.sda.service;

import edu.sda.dao.TaskDAO;
import edu.sda.data.Status;
import edu.sda.data.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Date;

/**
 * Implements business logic of the program
 */

public class TaskService {

    TaskDAO taskDAO = new TaskDAO();

    public static void printMenu(){
        System.out.println("To CREATE a task, type 'create' and info title ;due date (yyyy/mm/dd); project name");
        System.out.println();
        System.out.println("To UPDATE a task, type 'update' and task title");
        System.out.println();
        System.out.println("To set task as DONE, type 'done' and a task title");
        System.out.println();
        System.out.println("To see the list of tasks filtered by date, type 'by_date'");
        System.out.println();
        System.out.println("To see the list of tasks filtered by projects, type 'by_project'");
        System.out.println();
        System.out.println("To see all the tasks in the list, type 'list'");
        System.out.println();
        System.out.println("To remove a task, type 'remove' and a task title");
        System.out.println();
        System.out.println("To quit, type 'quit'");
    }

    /**
     * To list all tasks in the current to do list
     * @return
     */
    public Collection<Task> getAllTasks(){
        return taskDAO.getAllTasks();
    }

    /**
     *
     * @param date
     * @return
     */
    public ArrayList<Task> getTasksByDate(Date date){ //TODO
        return new ArrayList<>();
    }

    public List<Task> getTasksByProject(String project){ //TODO
        return new ArrayList<>();
    }


    public void create(Task task){
        taskDAO.create(task);
    }

    public void update (String title, Task task){
        taskDAO.update(title, task);
    }

    public void saveAll(){
        taskDAO.saveTasksToFile();
    }

    public void remove(String title, Task task){ //TODO

    }

    /**
     * Changes the status of the task to done
     * @param title name of the task
     */
    public void done(String title) {
        Task task = taskDAO.getTask(title);
        task.setStatus(Status.DONE);
        taskDAO.update(title, task);
    }

    public void printTasksToConsole(Collection<Task> tasks){
        for (Task task: tasks) {
            System.out.println(task.toString());
        }
    }

    public void initializeTaskStorage(){
        taskDAO.readFromFile();
    }
}
