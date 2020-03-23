package edu.sda.dao;

import edu.sda.data.Status;
import edu.sda.data.Task;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * Represents a class of tasks collection.
 */

public class TaskDAO {

    static final String DATE_PATTERN = "dd/mm/yyyy";
    final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
    private HashMap<String, Task> storage = new HashMap<>();

    /**
     * Adds a task into the collection
     *
     * @param task
     */
    public void create(Task task) {
        storage.put(task.getTitle(), task);
    }

    /**
     * Updates a task in the collection
     *
     * @param title, task
     */
    public void update(String title, Task task) {
        storage.put(title, task);
    }

    public Task getTask(String title) {
        return storage.get(title);
    }

    public Collection<Task> getAllTasks() {
        return storage.values();
    }

    public void saveTasksToFile() {
        String text = formatTasks(storage.values());
        writeTextToFile(text);
    }

    public void removeTask(String title){
        storage.remove(title);
    }

    private void writeTextToFile(String tasks) {
        try {
            FileWriter myWriter = new FileWriter("1.txt");
            myWriter.write(tasks);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred during saving");
            e.printStackTrace();
        }
    }

    /**
     * Method to format collection of tasks for writing them into the file after quitting.
     *
     * @param tasks
     * @return string of the tasks collection
     */
    private String formatTasks(Collection<Task> tasks) {
        String result = "";
        for (Task task : tasks) {
            result += formatTask(task) + "\n";
        }
        return result;
    }

    /**
     * Formats a single task to its string representation in the file.
     *
     * @param task
     * @return string task
     */
    private String formatTask(Task task) {
        return task.getTitle() + ";" + task.getStatus() + ";" + task.getProject() + ";" + DATE_FORMAT.format(task.getDueDate());
    }

    /**
     * Method to retrieve task list from the file.
     */
    public void readFromFile() {
        try {
            String fileName = "1.txt";
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;

            while ((line = br.readLine()) != null) {
                processTaskLine(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Unable to read tasks from storage");
        }
    }

    /**
     * Method to parse a line from a file and map it to a task object and place it in the list of tasks.
     *
     * @param ln
     */
    private void processTaskLine(String ln) {
        String[] parsedLine = ln.split(";");
        String title = parsedLine[0];
        Status status = Status.valueOf(parsedLine[1]);
        String project = parsedLine[2];
        Date date = mapStringToDate(parsedLine[3]);

        Task task = new Task(title, date, project, status);

        storage.put(title, task);
    }

    Date mapStringToDate(String sDate) {
        Date taskDate = null;
        try {
            taskDate = new SimpleDateFormat(DATE_PATTERN).parse(sDate);
        } catch (ParseException e) {
            System.out.println("Incorrect date format");
        }
        return taskDate;
    }
}