package fr.u_paris.gla.crazytrip.gui;

import fr.u_paris.gla.crazytrip.controller.ConsoleController;
import fr.u_paris.gla.crazytrip.controller.Controller;
import fr.u_paris.gla.crazytrip.controller.OnlineGUIController;
import fr.u_paris.gla.crazytrip.gui.view.ConsoleView;
import fr.u_paris.gla.crazytrip.gui.view.OnlineGUIView;
import fr.u_paris.gla.crazytrip.gui.view.View;

public class UserInterface {
    private Controller controller;

    private UserInterface(Controller controller) {
        this.controller = controller;
    }

    public static UserInterface create(String type, String title) {
        View view = createView(type, title);
        Controller controller = createController(type, view);
        return new UserInterface(controller);
    }

    private static View createView(String type, String title) {
        switch (type) {
            case "CONSOLE":
                return new ConsoleView();
            case "ONLINE":
                return new OnlineGUIView(title);
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    private static Controller createController(String type, View view) {
        switch (type) {
            case "CONSOLE":
                return new ConsoleController((ConsoleView) view);
            case "ONLINE":
                return new OnlineGUIController((OnlineGUIView) view);
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    public void start() {
        controller.start();
    }
}
