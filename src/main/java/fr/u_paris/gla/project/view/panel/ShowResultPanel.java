package fr.u_paris.gla.project.view.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import fr.u_paris.gla.project.algo.Itinerary;
import fr.u_paris.gla.project.idfnetwork.Stop;
import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.observer.ItineraryObserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

public class ShowResultPanel extends JPanel implements ItineraryObserver {
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    private static final int WIDTH = 250;

    private JLabel resultLabel;
    private JButton closeButton;

    public ShowResultPanel() {
        init();

        JLabel rechercheText = new JLabel("Résultat de la recherche:");
        // set the position of the text to the top center
        rechercheText.setHorizontalAlignment(SwingConstants.CENTER);

        closeButton = new JButton("Fermer");
        // set the position of the button to the bottom center
        closeButton.setHorizontalAlignment(SwingConstants.CENTER);
        closeButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> setVisible(false));
        });

        resultLabel = new JLabel();
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(rechercheText, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }

    private void init() {
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(WIDTH, getHeight()));
        setLayout(new BorderLayout());

        setVisible(false);
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    @Override
    public void showItinerary(Itinerary itinerary) {
        if (itineraryInvalid(itinerary)) {
            displayErrorMessage();
            return;
        }

        List<Stop> stops = itinerary.getStops();
        List<Line> lines = itinerary.getLines();

        StringBuilder builder = new StringBuilder("<html>");
        processItinerary(stops, lines, builder);
        builder.append("</html>");

        resultLabel.setText(builder.toString());
        revalidate();
        setVisible(true);
    }

    private boolean itineraryInvalid(Itinerary itinerary) {
        return itinerary == null || itinerary.getStops().isEmpty() || itinerary.getLines().isEmpty();
    }

    private void displayErrorMessage() {
        resultLabel.setText("Aucun itinéraire trouvé");
        revalidate();
        setVisible(true);
    }

    private void processItinerary(List<Stop> stops, List<Line> lines, StringBuilder builder) {
        Line previousLine = null;
        Stop startStop = null;
        int numberOfStops = stops.size();
        int numberOfLines = lines.size();

        for (int i = 0; i < numberOfStops; i++) {
            Line currentLine = i < numberOfLines ? lines.get(i) : null; // Gestion si pas de ligne correspondante
            Stop currentStop = stops.get(i);

            if (currentLine == null || !currentLine.equals(previousLine)) {
                if (previousLine != null) {
                    appendSegmentEnd(builder, startStop, stops.get(i));
                }
                if (currentLine != null) { // Commence seulement si une ligne est disponible
                    startStop = currentStop;
                    appendSegmentStart(builder, currentLine, startStop);
                }
            }

            previousLine = currentLine;
        }

        // Ajout du dernier segment si nécessaire
        if (startStop != null && previousLine != null) {
            appendSegmentEnd(builder, startStop, stops.get(numberOfStops - 1));
        }
    }

    private void appendSegmentStart(StringBuilder builder, Line line, Stop startStop) {
        if (builder.length() > 6) { // Ajoute un saut de ligne si ce n'est pas la première entrée
            builder.append("<br>");
        }
        builder.append(String.format("Prenez la ligne %s de %s", line.getLineName(), startStop.getStopName()));
    }
    
    private void appendSegmentEnd(StringBuilder builder, Stop startStop, Stop endStop) {
        builder.append(String.format(" à %s<br>", endStop.getStopName()));
    }

}
