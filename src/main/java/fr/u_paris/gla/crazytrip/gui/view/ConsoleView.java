package fr.u_paris.gla.crazytrip.gui.view;

import java.util.Scanner;

public class ConsoleView implements View {
    private Scanner scanner;
    
    @Override
    public void start() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
