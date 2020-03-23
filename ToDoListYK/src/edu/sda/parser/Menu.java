package edu.sda.parser;

import edu.sda.service.TaskService;
import edu.sda.data.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu {
    /**
     *  Main program routine. Loops until end of program.
     */

    private Parser parser;
    private TaskService taskService = new TaskService();

    public Menu()
    {
        parser = new Parser();
    }

    public static void main(String[] args){
        (new Menu()).runProgram();
    }

    public void runProgram()
    {
        taskService.initializeTaskStorage();
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for using to do list!  Good bye");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to our to do list!");
        System.out.println("It can store your tasks with due dates and projects assigned.");
        System.out.println("You can also track if the task is done, update and remove your tasks.");
        System.out.println("You have " + taskService.countTasksToDo() + " tasks to do and " + taskService.countTasksDone() + " tasks done.");
        System.out.println("Type " + CommandWord.MENU + " to see the instructions.");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("Please enter a valid command...");
                break;

            case MENU:
                taskService.printMenu();
                break;

            case CREATE:
                Task task = mapStringToTask(command.getSecondWord());
                taskService.create(task);
                break;

            case UPDATE:
                Task taskToUpdate = mapStringToTask(command.getSecondWord());
                taskService.update(taskToUpdate.getTitle(), taskToUpdate);
                break;

            case DONE:
                taskService.done(command.getSecondWord());
                break;

            case REMOVE:
                taskService.remove(command.getSecondWord());
                break;

            case BY_DATE:
                taskService.printTasksToConsole(taskService.sortByDate());
                break;

            case BY_PROJECT:
                String project = command.getSecondWord();
                taskService.printTasksToConsole(taskService.filterByProject(project));
                break;

            case LIST:
                taskService.printTasksToConsole(taskService.getAllTasks());
                break;

            case QUIT:
                taskService.saveAll();
                wantToQuit = quit();
                break;
        }
        return wantToQuit;
    }

    private boolean quit() {
        return true;
    }

    /**
     * Method to map string from input to a task object.
     * @param wordTask
     * @return and object of task
     */
    Task mapStringToTask(String wordTask){

        String[] parsedTaskString = wordTask.split(";");

        String taskTitle = parsedTaskString[0];

        Date taskDate = mapStringToDate(parsedTaskString[1]) ;

        String project = parsedTaskString[2];

        return new Task(taskTitle, taskDate, project);
    }

    /**
     * Method to  map string to a date object for a task.
     * @param sDate string date
     * @return date
     */
    Date mapStringToDate(String sDate){
        Date taskDate = null;
        try {
             taskDate = new SimpleDateFormat("dd/mm/yyyy").parse(sDate);
        } catch (ParseException e){
            System.out.println("Incorrect date format");
        }
        return taskDate;
    }
}
