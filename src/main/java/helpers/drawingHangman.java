package helpers;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Klasa odpowiadająca za rysowanie wisielca w zależności od błędnych zgadnięć
 *
 * @author Tomasz Gajos
 */
public class drawingHangman extends JPanel {

    private int incorrectGuesses = 0;

    /**
     * zwiększa ilość niepoprawnych zgadnięć
     */
    public void incrementIncorrectGuesses() {
        incorrectGuesses++;
    }

    /**
     * Ustawia ilość błędnych zgadnięć na konretną ilość
     *
     * @param i ilość błędnych zgadnięć
     */
    public void setIncorrectGuesses(int i) {
        incorrectGuesses = i;
    }

    /**
     * Metoda odpowiadająca za wyświetlanie rysuku
     */
    public void drawing() {
        repaint();
    }

    /**
     * Metoda służąca do rysowania wisielca w zależnośći od ilości popełnionych
     * błędów
     *
     * @param g obiekt Graphics służący do rysowania
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        if (incorrectGuesses > 0) {
            // podstawa
            g.drawLine(130, 450, 320, 450);

        }
        // belka pionowa
        if (incorrectGuesses > 1) {
            g.drawLine(225, 450, 225, 50);

        }
        if (incorrectGuesses > 2) {
            // belka pozioma
            g.drawLine(225, 50, 375, 50);

        }
        if (incorrectGuesses > 3) {
            // sznur
            g.drawLine(375, 50, 375, 125);

        }
        if (incorrectGuesses > 4) {
            // głowa
            g.drawOval(343, 125, 64, 64);

        }
        if (incorrectGuesses > 5) {
            // ciało
            g.drawLine(375, 189, 375, 350);

        }
        if (incorrectGuesses > 6) {
            // lewa ręka
            g.drawLine(375, 210, 300, 270);

        }
        if (incorrectGuesses > 7) {
            // prawa ręka
            g.drawLine(375, 210, 450, 270);

        }
        if (incorrectGuesses > 8) {
            // lewa noga
            g.drawLine(375, 350, 300, 410);

        }
        if (incorrectGuesses > 9) {
            // prawa noga
            g.drawLine(375, 350, 450, 410);
        }
    }
}
