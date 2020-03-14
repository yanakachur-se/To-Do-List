package edu.sda.parser;

import java.util.Scanner;

/**
 * Parser reads user input and interprets it as a
 * command. Every time it is called, it reads a line from the terminal and
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

    static void mapStringToTask(){

    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}