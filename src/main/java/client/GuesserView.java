package client;

import helpers.JTextFieldLimit;
import helpers.drawingHangman;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;

/**
 * Widok gracza zgadującego hasło w aplikacji wisielec
 *
 * @author Tomasz Gajos
 */
public class GuesserView extends javax.swing.JFrame {

    /**
     * Tworzy instancję GuesserView i inicjalizuje jej składniki
     *
     * @param clientWorker wątek odpowiadający za zarządzanie stanem gry
     */
    public GuesserView(ClientWorker clientWorker) {
        initComponents();
        wrongLettersList.setModel(wrongLettersListModel);
        this.clientWorker = clientWorker;
        zgadnijButton.setEnabled(false);
        zgadnijRozwiazanieButton.setEnabled(false);
        wait = new JDialog(this, "Wait", false);
        WaitView panel = new WaitView();
        wait.setContentPane(panel);
        wait.pack();
        wait.setAlwaysOnTop(true);
        wait.setVisible(true);
        letterTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zgadnijButton.doClick();
            }
        });
        answerTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zgadnijRozwiazanieButton.doClick();
            }
        });
        letterTextField.setDocument(new JTextFieldLimit(1));
        add(object);
        object.setSize(500, 500);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        letterTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        wrongLettersList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        serverMessagesTextField = new javax.swing.JTextField();
        zgadnijButton = new javax.swing.JButton();
        phraseLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        answerTextField = new javax.swing.JTextField();
        zgadnijRozwiazanieButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        letterTextField.setActionCommand("<Not Set>");

        jScrollPane1.setViewportView(wrongLettersList);

        jLabel1.setText("Błędne litery:");

        jLabel2.setText("Zgadnij literę:");

        serverMessagesTextField.setText("Podaj literę, aby dowiedzieć się, czy jest poprawna");
        serverMessagesTextField.setEnabled(false);

        zgadnijButton.setText("Zgadnij");
        zgadnijButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zgadnijButtonActionPerformed(evt);
            }
        });

        phraseLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        phraseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phraseLabel.setText("haslo");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Myślisz, że znasz rozwiązanie? Wpisz je tutaj:");

        zgadnijRozwiazanieButton.setText("Zgadnij");
        zgadnijRozwiazanieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zgadnijRozwiazanieButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(624, 624, 624)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(serverMessagesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(561, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(219, 219, 219)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(letterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(zgadnijButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(answerTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(zgadnijRozwiazanieButton))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(phraseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 484, Short.MAX_VALUE)
                        .addComponent(phraseLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(letterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(zgadnijButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(zgadnijRozwiazanieButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(answerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serverMessagesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Akcja wywoływana po naciśnięciu przycisku Zgadnij obok pola do podania
     * litery Jeżeli pole nie jest pustę, zgadnięta litera jest przesyłana do
     * serwera
     *
     * @param evt
     */
    private void zgadnijButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zgadnijButtonActionPerformed
        if (!letterTextField.getText().trim().isEmpty()) {
            clientWorker.sendLetterGuess(letterTextField.getText().trim().toLowerCase());
            letterTextField.setText("");
        }
    }//GEN-LAST:event_zgadnijButtonActionPerformed

    /**
     * Akcja wywoływana po naciśnięciu przycisku Zgadnij obok pola do podania
     * odpowiedzi Jeżeli pole nie jest pustę, zgadnięta odpowiedź jest
     * przesyłana do serwera
     *
     * @param evt
     */
    private void zgadnijRozwiazanieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zgadnijRozwiazanieButtonActionPerformed
        if (!answerTextField.getText().trim().isEmpty()) {
            clientWorker.sendAnswerGuess(answerTextField.getText().trim().toLowerCase());
            answerTextField.setText("");
        }
    }//GEN-LAST:event_zgadnijRozwiazanieButtonActionPerformed

    /**
     * Dodaje błędną literę do listy
     *
     * @param letter błędna litera
     */
    public void addLetterToList(String letter) {
        wrongLettersListModel.addElement(letter);
    }

    /**
     * Wyświetla wiadomość od serwera
     *
     * @param message wiadomość od serwera
     */
    public void setServerMessage(String message) {
        serverMessagesTextField.setText(message);
    }

    /**
     * Wyświetla hasł
     *
     * @param phrase hasło
     */
    public void setPhrase(String phrase) {
        phraseLabel.setText(phrase);
    }

    /**
     * Zamyka panel closeWaitView oraz włącza przyciski do zgadywania
     */
    public void closeWaitView() {
        zgadnijButton.setEnabled(true);
        zgadnijRozwiazanieButton.setEnabled(true);
        wait.dispose();
    }

    /**
     * Rysuje wisielca na oknie
     */
    public void drawHangman() {
        object.incrementIncorrectGuesses();
        object.drawing();
    }

    /**
     * Główna metoda uruchamiająca aplikację
     *
     * @param args
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuesserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuesserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuesserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuesserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }
    drawingHangman object = new drawingHangman();
    private final DefaultListModel<String> wrongLettersListModel = new DefaultListModel<>();
    JDialog wait;
    ClientWorker clientWorker;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField answerTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField letterTextField;
    private javax.swing.JLabel phraseLabel;
    private javax.swing.JTextField serverMessagesTextField;
    private javax.swing.JList<String> wrongLettersList;
    private javax.swing.JButton zgadnijButton;
    private javax.swing.JButton zgadnijRozwiazanieButton;
    // End of variables declaration//GEN-END:variables
}
