package helpers;

import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Klasa służąca do ustawienia limitu długości JTextField
 *
 * @author Tomasz Gajos
 */
public class JTextFieldLimit extends PlainDocument {

    private int limit;

    /**
     * Konstruktor klasy JTextFieldLimit
     *
     * @param limit limit długości
     */
    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    /**
     * @param offset
     * @param str
     * @param attr
     * @throws BadLocationException
     */
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
