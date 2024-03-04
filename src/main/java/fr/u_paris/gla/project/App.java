package fr.u_paris.gla.project;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import fr.u_paris.gla.project.idfm.IDFMNetworkExtractor;

/** Simple application model.
 *
 * @author Emmanuel Bigeon */
public class App {
    /**
     * 
     */
    private static final String UNSPECIFIED = "Unspecified";         //$NON-NLS-1$
    /**
     * String constants for command line arguments.
     */
    private static final String INFOCMD = "--info";
    private static final String GUICMD = "--gui";
    private static final String EXTRACTORCMD = "--extractor";

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
                if (INFOCMD.equals(string)) { //$NON-NLS-1$
                    printAppInfos(System.out);
                    return;
                }
                if (GUICMD.equals(string)) { //$NON-NLS-1$
                    launch();
                }
                if(EXTRACTORCMD.equals(string)) {
                    extraction();
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
        //extraction();

        Properties props = readApplicationProperties();
        String title = props.getProperty("app.name");

        EventQueue.invokeLater(() -> {
            window = new AppWindow(title);
            window.setVisible(true);
            latch.countDown();
        });
    }

    public static void extraction() {
        IDFMNetworkExtractor.extract();
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
