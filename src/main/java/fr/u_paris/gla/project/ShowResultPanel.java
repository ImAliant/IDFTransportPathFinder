package fr.u_paris.gla.project;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import fr.u_paris.gla.project.idfnetwork.Itinerary;
import fr.u_paris.gla.project.observer.ItineraryObserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class ShowResultPanel extends JPanel implements ItineraryObserver {
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    private static final int WIDTH = 250;

    private JLabel resultLabel;

    public ShowResultPanel() {
        init();

        JLabel rechercheText = new JLabel("Résultat de la recherche:");
        // set the position of the text to the top center
        rechercheText.setHorizontalAlignment(SwingConstants.CENTER);

        JButton closeButton = new JButton("Fermer");
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

    @Override
    public void showItinerary(Itinerary itinerary) {
        StringBuilder builder = new StringBuilder("<html>");
        builder.append("Itinéraire trouvé :<br>")
            .append("Durée : ").append(itinerary.getTotalDuration()).append(" minutes<br>")
            .append("Distance : ").append(itinerary.getTotalDistance()).append(" mètres<br>")
            .append("Itinéraire :<br>")
            .append("<ul>")
            .append("<li>")
            .append("Prendre la ligne ")
            .append(itinerary.getLines().get(0))
            .append(" à l'arrêt ")
            .append(itinerary.getStops().get(0).getStopName())
            .append("<br>");
        for (int i = 1; i < itinerary.getStops().size()-1; i++) {
            if (i < itinerary.getLines().size()) {
                if (!itinerary.getLines().get(i).equals(itinerary.getLines().get(i - 1))) {
                    builder.append(itinerary.getLines().get(i))
                        .append(" à l'arrêt ")
                        .append(itinerary.getStops().get(i).getStopName())
                        .append("<br>");
                } else {
                    builder.append("Poursuivre jusqu'à l'arrêt ")
                        .append(itinerary.getStops().get(i).getStopName())
                        .append("<br>");
                }
            } else {
                builder.append("Arrivée à destination à l'arrêt ")
                    .append(itinerary.getStops().get(itinerary.getStops().size()-1).getStopName())
                    .append("<br>");
            }
        }

        builder.append("</ul>")
            .append("</html>");
        
        resultLabel.setText(builder.toString());

        setVisible(true);
    }
}
