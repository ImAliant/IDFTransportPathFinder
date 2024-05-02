package fr.u_paris.gla.project.view.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import fr.u_paris.gla.project.algo.Itinerary;
import fr.u_paris.gla.project.idfnetwork.Stop;
import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.observer.ItineraryObserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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
    /** Button color. */
    private static final Color BUTTON_COLOR = new Color(1, 121, 111);

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
        rechercheText.setForeground(new Color(255, 255, 255)); // Couleur blanche

        closeButton = new JButton("Fermer");
        // set the position of the button to the bottom center
        closeButton.setHorizontalAlignment(SwingConstants.CENTER);
        styleButton(closeButton);
        closeButton.addActionListener(e -> SwingUtilities.invokeLater(() -> setVisible(false)));

        resultLabel = new JLabel();
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(rechercheText, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);
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

    private void styleButton(JButton button) {
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
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
                "<span style='color: %s;'>Prenez la ligne %s</span> de <span style='color: black;'>%s</span>", lineColor,
                line.getLineName(), startStop.getStopName()));
    }

    private void appendSegmentEnd(StringBuilder builder, Stop startStop, Stop endStop, String lineColor) {
        builder.append(String.format(" à <span style='color: black;'>%s</span><br>", endStop.getStopName()));
    }

}
