/**
 * 
 */
package fr.u_paris.gla.project.idfm;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVParser;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvValidationException;

/** Code of an extractor for the data from IDF mobilite.
 * 
 * @author Emmanuel Bigeon */
public class IDFMNetworkExtractor {
    /** The logger for information on the process */
    private static final Logger LOGGER = Logger
            .getLogger(IDFMNetworkExtractor.class.getName());

    // IDF mobilite API URLs
    private static final String TRACE_FILE_URL = "https://data.iledefrance-mobilites.fr/api/explore/v2.1/catalog/datasets/traces-des-lignes-de-transport-en-commun-idfm/exports/csv?lang=fr&timezone=Europe%2FBerlin&use_labels=true&delimiter=%3B";
    private static final String STOPS_FILE_URL = "https://data.iledefrance-mobilites.fr/api/explore/v2.1/catalog/datasets/arrets-lignes/exports/csv?lang=fr&timezone=Europe%2FBerlin&use_labels=true&delimiter=%3B";

    public static void readCSVFromURL(String url, Consumer<String[]> contentLineConsumer)
            throws IOException {
        ICSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try (InputStream is = new URL(url).openStream();
                Reader reader = new BufferedReader(
                        new InputStreamReader(is, StandardCharsets.UTF_8))) {
            CSVReaderBuilder csvBuilder = new CSVReaderBuilder(reader)
                    .withCSVParser(parser);
            try (CSVReader csv = csvBuilder.build()) {
                String[] line = csv.readNextSilently(); // Eliminate header
                while (csv.peek() != null) {
                    line = csv.readNext();
                    contentLineConsumer.accept(line);
                }
            }
        } catch (CsvValidationException e) {
            throw new IOException("Invalid csv file for lines", e);
        }
    }

    /** Main entry point for the extractor of IDF mobilite data into a network as
     * defined by this application.
     * 
     * @param args the arguments (expected one for the destination file) */
    public static void main(String[] args) {

        if (args.length != 1) {
            LOGGER.severe("Invalid command line. Missing target file.");
            return;
        }

        // Export content in required format
        try (FileWriter writer = new FileWriter(args[0], StandardCharsets.UTF_8)) {
            CSVWriterBuilder wBuilder = new CSVWriterBuilder(writer).withSeparator(';');
            try (ICSVWriter csv = wBuilder.build()) {
                // TODO write the content
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e, () -> "Could not write in file " + args[1]);
        }
    }
}
