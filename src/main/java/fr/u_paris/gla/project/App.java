package fr.u_paris.gla.project;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/** Simple application model.
 *
 * @author Emmanuel Bigeon */
public class App {
    /**
     * 
     */
    private static final String UNSPECIFIED = "Unspecified";         //$NON-NLS-1$
    /** Latch to wait for the window to be created. */
    private static CountDownLatch latch = new CountDownLatch(1);
    /**
     * Window of the application.
     */
    private static AppWindow window;

    /** Application entry point.
     *
     * @param args launching arguments */
    public static void main(String[] args) {
        if (args.length > 0) {
            for (String string : args) {
                if ("--info".equals(string)) { //$NON-NLS-1$
                    printAppInfos(System.out);
                    return;
                }
                if ("--gui".equals(string)) { //$NON-NLS-1$
                    launch();
                }
            }
        }
    }

    /** @param out */
    public static void printAppInfos(PrintStream out) {
        Properties props = readApplicationProperties();

        out.println("Application: " + props.getProperty("app.name", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
        out.println("Version: " + props.getProperty("app.version", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
        out.println("By: " + props.getProperty("app.team", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private static Properties readApplicationProperties() {
        Properties props = new Properties();
        try (InputStream is = App.class.getResourceAsStream("application.properties")) { //$NON-NLS-1$
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read application informations", e); //$NON-NLS-1$
        }
        return props;
    }

    /** Launch the gui version of the application */
    public static void launch() {
        Properties props = readApplicationProperties();
        String title = props.getProperty("app.name");

        java.awt.EventQueue.invokeLater(() -> {
            window = new AppWindow(title);
            window.setVisible(true);
            latch.countDown();
        });
    }

    /** @return the window */
    public static AppWindow getWindow() {
        return window;
    }

    /** @return the latch */
    public static CountDownLatch getLatch() {
        return latch;
    }
}
