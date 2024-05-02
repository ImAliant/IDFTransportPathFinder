package fr.u_paris.gla.project;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;

import fr.u_paris.gla.project.idfm.IDFMNetworkExtractor;
import fr.u_paris.gla.project.idfnetwork.network.loader.NetworkLoader;
import fr.u_paris.gla.project.observer.LoadingObserver;
import fr.u_paris.gla.project.view.progress_bar.LoadingProgressBar;
import fr.u_paris.gla.project.view.window.AppWindow;
import fr.u_paris.gla.project.view.window.LoadingScreen;

/**
 * Simple application model.
 *
 * @author Emmanuel Bigeon
 */
public class App {
    private static LoadingObserver loadingObserver = null;

    /**
     * 
     */
    private static final String UNSPECIFIED = "Unspecified"; //$NON-NLS-1$
    /**
     * String constants for command line arguments.
     */
    private static final String INFOCMD = "--info";
    private static final String GUICMD = "--gui";

    /** Latch to wait for the window to be created. */
    private static CountDownLatch latch = new CountDownLatch(1);
    /**
     * Window of the application.
     */
    private static AppWindow window;

    // debug variable
    protected static boolean extractionCalled;
    protected static boolean loadCalled;

    /**
     * Application entry point.
     *
     * @param args launching arguments
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            for (String string : args) {
                if (INFOCMD.equals(string)) { // $NON-NLS-1$
                    printAppInfos(System.out);
                    return;
                }
                if (GUICMD.equals(string)) { // $NON-NLS-1$
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

    /**
     * Launch the gui version of the application
     * 
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void launch() {
        Properties props = readApplicationProperties();
        String title = props.getProperty("app.name");

        launchLoadingScreen(title);

        initNetwork();

        LoadingProgressBar.getInstance().incrementProgress(10);

        SwingUtilities.invokeLater(() -> {
            window = new AppWindow(title);

            window.setVisible(true);

            LoadingProgressBar.getInstance().setValue(LoadingProgressBar.getInstance().getMaximum());

            closeLoadingScreen();

            latch.countDown();
        });
    }

    public static void initNetwork() {
        /** Check if the file output.csv exists in the target directory
            If it does, load the file
            If not, call the extraction() function
         */


        File file = new File(IDFMNetworkExtractor.PATH_TO_OUTPUT);
        if (!file.exists()) {
            try {
                extraction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        NetworkLoader.load(file);
        loadCalled = true;
    }

    public static void extraction() throws IOException {
        IDFMNetworkExtractor.extract();
        extractionCalled = true;
    }

    private static void launchLoadingScreen(String title) {
        SwingUtilities.invokeLater(() -> {
            LoadingScreen screen = new LoadingScreen(title);

            addObserver(screen);

            screen.setVisible(true);
        });
    }

    private static void closeLoadingScreen() {
        if (loadingObserver == null) {
            return;
        }

        notifyObserver();
        removeObserver();
    }

    private static void addObserver(LoadingObserver observer) {
        loadingObserver = observer;
    }

    private static void removeObserver() {
        loadingObserver = null;
    }

    private static void notifyObserver() {
        if (loadingObserver != null) {
            loadingObserver.onLoadingDone();
        }
    }

    /** @return the window */
    public static AppWindow getWindow() {
        return window;
    }

    /** @return the latch */
    public static CountDownLatch getLatch() {
        return latch;
    }

    // debug method
    public static void reset() {
        extractionCalled = false;
        loadCalled = false;
    }
}
