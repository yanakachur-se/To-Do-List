package edu.sda.parser;

import edu.sda.service.TaskService;
import edu.sda.data.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu {
    /**
     *  Main program routine.  Loops until end of program.
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

        printWelcome();
        taskService.initializeTaskStorage();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

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
        System.out.println("You have X tasks to do and Y tasks done.");
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
                System.out.println("Please enter a valid task...");
                break;

            case MENU:
                taskService.printMenu();
                break;

            case CREATE:
                Task task = mapToTask(command.getSecondWord());
                taskService.create(task);
                break;

            case UPDATE:
                Task taskToUpdate = mapToTask(command.getSecondWord());
                taskService.update(taskToUpdate.getTitle(), taskToUpdate);
                break;

            case DONE:
                taskService.done(command.getSecondWord());
                break;

            case REMOVE:
                taskService.remove();
                break;

            case BY_DATE:
                taskService.getTasksByDate();
                break;

            case BY_PROJECT:
                taskService.getTasksByProject();
                break;

            case LIST:
                taskService.printTasksToConsole(taskService.getAllTasks());
                break;

            case QUIT:
                taskService.saveAll();
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    private boolean quit(Command command) {
        return true;
    }

    Task mapToTask(String wordTask){

        String[] parsedTaskString = wordTask.split(";");

        String taskTitle = parsedTaskString[0];

        Date taskDate = mapStringToDate(parsedTaskString[1]) ;

        String project = parsedTaskString[2];

        return new Task(taskTitle, taskDate, project);
    }

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
