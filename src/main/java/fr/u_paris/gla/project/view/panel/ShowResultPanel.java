package fr.u_paris.gla.project.view.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;

import fr.u_paris.gla.project.algo.Itinerary;
import fr.u_paris.gla.project.idfnetwork.Stop;
import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.observer.ItineraryObserver;
import fr.u_paris.gla.project.utils.StyleButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.util.List;

/**
 * Panel containing the result of the research.
 * 
 * @see JPanel
 * @see ItineraryObserver
 * 
 * @author BALEH Youcef
 * @author DIAMANT Alexandre
 */
public class ShowResultPanel extends JPanel implements ItineraryObserver {
    /** Background color of the panel. */
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);

    /** Width of the panel. */
    private static final int WIDTH = 250;
    /** Label containing the result of the research. */
    private JLabel resultLabel;
    /** Button to close the panel. */
    private JButton closeButton;

    /**
     * Constructor of the show result panel.
     */
    public ShowResultPanel() {
        init();

        JLabel rechercheText = new JLabel("Résultat de la recherche:");
        // set the position of the text to the top center
        rechercheText.setHorizontalAlignment(SwingConstants.CENTER);
        rechercheText.setForeground(new Color(255, 255, 255));

        closeButton = new JButton("Fermer");
        // set the position of the button to the bottom center
        closeButton.setHorizontalAlignment(SwingConstants.CENTER);
        StyleButton.styleButton(closeButton);
        closeButton.addActionListener(e -> SwingUtilities.invokeLater(() -> setVisible(false)));

        resultLabel = new JLabel();
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);
        JScrollPane scrollPane = new JScrollPane(resultLabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(rechercheText, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }

    /**
     * Initialize the panel.
     */
    private void init() {
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(WIDTH, getHeight()));
        setLayout(new BorderLayout());
        setVisible(false);
    }

    /**
     * Getter of the close button.
     * 
     * @return the close button
     */
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
        resultLabel.setText("<html><span style='color: red; font-size: 12px;'>Aucun itinéraire trouvé</span></html>");
        revalidate();
        setVisible(true);
    }

    private void processItinerary(List<Stop> stops, List<Line> lines, StringBuilder builder) {
        Line previousLine = null;
        Stop startStop = null;
        int numberOfStops = stops.size();
        int numberOfLines = lines.size();
        String lineColor = "black";

        for (int i = 0; i < numberOfStops; i++) {
            Line currentLine = i < numberOfLines ? lines.get(i) : null; // Gestion si pas de ligne correspondante
            Stop currentStop = stops.get(i);

            lineColor = currentLine != null ? currentLine.getColor() : "black";

            if (currentLine == null || !currentLine.equals(previousLine)) {
                if (previousLine != null) {
                    appendSegmentEnd(builder, startStop, stops.get(i), lineColor);
                }
                if (currentLine != null) { // Commence seulement si une ligne est disponible
                    startStop = currentStop;
                    appendSegmentStart(builder, currentLine, startStop, lineColor);
                }
            }

            previousLine = currentLine;
        }

        // Ajout du dernier segment si nécessaire
        if (startStop != null && previousLine != null) {
            appendSegmentEnd(builder, startStop, stops.get(numberOfStops - 1), lineColor);
        }
    }

    private void appendSegmentStart(StringBuilder builder, Line line, Stop startStop, String lineColor) {
        if (builder.length() > 6) { // Ajoute un saut de ligne si ce n'est pas la première entrée
            builder.append("<br>");
        }
        builder.append(String.format(
                "<span style='color: black;'>Prenez la ligne </span><span style='color: %s;'>%s</span> de <span style='color: black;'>%s</span>",
                line.getColor(), line.getLineName(), startStop.getStopName()));

    }

    private void appendSegmentEnd(StringBuilder builder, Stop startStop, Stop endStop, String lineColor) {
        builder.append(String.format(" à <span style='color: black;'>%s</span><br>", endStop.getStopName()));
    }

}
