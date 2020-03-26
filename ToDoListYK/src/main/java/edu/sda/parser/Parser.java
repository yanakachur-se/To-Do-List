package edu.sda.parser;

import edu.sda.data.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Parser reads user input and interprets it as a
 * command and transfers it to objects. Every time it is called, it reads a line from the terminal and
 * tries to interpret the line as a two-word command. It returns the command
 * as an object of class Command.
 *
 */
public class Parser {
    private CommandWords commands;
    private Scanner reader;


    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String words = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                words = tokenizer.next();
                while (tokenizer.hasNext()){
                    words = words + " " + tokenizer.next();
                }
            }
        }

        return new Command(commands.getCommandWord(word1), words);
    }

    /**
     * Method to map string from input to a task object.
     * @param wordTask
     * @return and object of task
     */
    public Task mapStringToTask(String wordTask){

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