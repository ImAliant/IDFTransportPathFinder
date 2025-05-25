package fr.u_paris.gla.crazytrip.command;

import java.io.PrintStream;
import java.util.Properties;

import fr.u_paris.gla.crazytrip.utils.ApplicationUtils;

/**
 * Command that prints information about the application.
 */
public class InfoCommand implements Command {
    private static final String UNSPECIFIED = "Unspecified"; //$NON-NLS-1$

    /**
     * Prints information about the application.
     * 
     * @param args The arguments of the command. It is not used.
     */
    @Override
    public void execute(String[] args) {
        printAppInfos(System.out);
    }

    /**
     * Prints information about the application.
     * 
     * @param out The output stream where the information will be printed.
     */
    private static void printAppInfos(PrintStream out) {
		Properties props = ApplicationUtils.readApplicationProperties();

		out.println("Application: " + props.getProperty("app.name", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
		out.println("Version: " + props.getProperty("app.version", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
		out.println("By: " + props.getProperty("app.team", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
