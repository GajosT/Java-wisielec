package client;

import helpers.drawingHangman;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;

/**
 * Widok gracza podającego hasło w wisielcu
 *
 * @author Tomasz Gajos
 */
public class Word_GiverView extends javax.swing.JFrame {

    /**
     * Tworzy instancję Word_GiverView i inicjalizuje jej składniki
     *
     * @param clientWorker wątek odpowiadający za zarządzanie stanem gry
     */
    public Word_GiverView(ClientWorker clientWorker) {
        initComponents();
        wrongLettersList.setModel(wrongLettersListModel);
        this.clientWorker = clientWorker;
        JDialog input = new JDialog(this, "Wait", false);
        WordInputView panel = new WordInputView(phraseTextField, clientWorker, correctWord, (t) -> {
            input.dispose();
            return null;
        });
        input.setContentPane(panel);
        input.pack();
        input.setAlwaysOnTop(true);
        input.setVisible(true);
        add(object);
        object.setSize(500, 500);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        wrongLettersList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        serverMessagesTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        phraseTextField = new javax.swing.JTextField();
        phraseLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(wrongLettersList);

        jLabel1.setText("Błędne litery:");

        serverMessagesTextField.setText("Poczekaj aż drugi gracz poda słowo, aby dowiedzieć się, czy podał poprawne");
        serverMessagesTextField.setEnabled(false);

        jLabel2.setText("Hasło:");

        phraseTextField.setEditable(false);

        phraseLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        phraseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phraseLabel.setText("haslo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(serverMessagesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phraseLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(119, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(phraseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 439, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(132, 132, 132)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(phraseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phraseLabel)
                        .addGap(18, 18, 18)
                        .addComponent(serverMessagesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param letter
     */
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
     * Rysuje wisielca na oknie
     */
    public void drawHangman() {
        object.incrementIncorrectGuesses();
        object.drawing();
    }

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(Word_GiverView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Word_GiverView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Word_GiverView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Word_GiverView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }
    drawingHangman object = new drawingHangman();
    private DefaultListModel<String> wrongLettersListModel = new DefaultListModel<>();
    String correctWord;
    ClientWorker clientWorker;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel phraseLabel;
    private javax.swing.JTextField phraseTextField;
    private javax.swing.JTextField serverMessagesTextField;
    private javax.swing.JList<String> wrongLettersList;
    // End of variables declaration//GEN-END:variables
}
