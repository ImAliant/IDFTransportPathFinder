package fr.u_paris.gla.crazytrip.command;

import java.io.PrintStream;
import java.util.Properties;

import fr.u_paris.gla.crazytrip.utils.ApplicationUtils;

public class InfoCommand implements Command {
    private static final String UNSPECIFIED = "Unspecified"; //$NON-NLS-1$

    @Override
    public void execute(String[] args) {
        printAppInfos(System.out);
    }

    private static void printAppInfos(PrintStream out) {
		Properties props = ApplicationUtils.readApplicationProperties();

		out.println("Application: " + props.getProperty("app.name", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
		out.println("Version: " + props.getProperty("app.version", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
		out.println("By: " + props.getProperty("app.team", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
