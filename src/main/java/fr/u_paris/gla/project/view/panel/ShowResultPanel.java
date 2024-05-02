package fr.u_paris.gla.project.view.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import fr.u_paris.gla.project.algo.Itinerary;
import fr.u_paris.gla.project.observer.ItineraryObserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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

        closeButton = new JButton("Fermer");
        // set the position of the button to the bottom center
        closeButton.setHorizontalAlignment(SwingConstants.CENTER);
        closeButton.addActionListener(e ->
            SwingUtilities.invokeLater(() -> setVisible(false))
        );

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

    @Override
    public void showItinerary(Itinerary itinerary) {
        if (itinerary == null || itinerary.getStops().isEmpty() || itinerary.getLines().isEmpty() ) {
            resultLabel.setText("Aucun itinéraire trouvé");
            revalidate();
            setVisible(true);
            return;
        }

        String itineraryString = itinerary.toString();
        StringBuilder builder = new StringBuilder();
        builder.append(itineraryString);
        
        resultLabel.setText("<html>"+builder.toString().replace("\n", "<br>")+"</html>");
        revalidate();
        setVisible(true);
    }

    /**
     * Getter of the close button.
     * 
     * @return the close button
     */
    public JButton getCloseButton() {
        return closeButton;
    }
}
