package fr.u_paris.gla.crazytrip.gui.view;

import java.util.Scanner;

/**
 * A simple console gui view.
 * 
 * This class is a simple implementation of the View interface that uses the
 * console to display messages and get input from the user.
 * 
 * This class is used as a fallback when no other view is available.
 * 
 * @see View
 */
public class ConsoleView implements View {
    /** The scanner used to get input from the user. */
    private Scanner scanner;
    
    @Override
    public void start() {
        scanner = new Scanner(System.in);
    }

    /**
     * Display a message to the user.
     * 
     * @param message the message to display
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Get input from the user.
     * 
     * @param prompt the prompt to display to the user
     * @return the input from the user
     */
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
