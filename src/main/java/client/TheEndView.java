package client;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Widok końca gry w wisielca kiedy hasło zostało zgadnięte
 *
 * @author Tomasz Gajos
 */
public class TheEndView extends javax.swing.JFrame {

    /**
     * Tworzy instancję TheEndView i inicjalizuje jej składniki
     *
     * @param phrase hasło
     * @param clientWorker wątek odpowiadający za zarządzanie stanem gry
     */
    public TheEndView(String phrase, ClientWorker clientWorker) {
        initComponents();
        phraseLabel.setText(phrase);
        this.clientWorker = clientWorker;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        phraseLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        zaczniButton = new javax.swing.JButton();
        zamknijButton = new javax.swing.JButton();
        zapiszButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Koniec gry, gracz zgadnął hasło:");

        phraseLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        phraseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phraseLabel.setText("haslo");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Czy chcesz zacząć nową grę?");

        zaczniButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        zaczniButton.setText("Zacznij nową grę");
        zaczniButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zaczniButtonActionPerformed(evt);
            }
        });

        zamknijButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        zamknijButton.setText("Zamknij");
        zamknijButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zamknijButtonActionPerformed(evt);
            }
        });

        zapiszButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        zapiszButton.setText("Zapisz wynik");
        zapiszButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zapiszButtonActionPerformed(evt);
            }
        });

        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(zaczniButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(zapiszButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32)
                .addComponent(zamknijButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(phraseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(messageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phraseLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zaczniButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zamknijButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zapiszButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Akcja wywołana po naciśnięciu przycisku Zacznij nową grę Zaczyna nową grę
     * inicjalizując metodę newGame w klasie {link ClientWorker}
     *
     * @param evt
     */
    private void zaczniButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zaczniButtonActionPerformed
        clientWorker.newGame();
        zamknijButton.setEnabled(false);
        messageLabel.setText("Czekanie na drugiego gracza. Nie rozłączaj się.");
    }//GEN-LAST:event_zaczniButtonActionPerformed

    /**
     * Akcja wywołana po naciśnięciu przyisku Zamknij Uruchamia metodę endGame w
     * klasie {@link ClientWorker} i zamyka aplikację
     *
     * @param evt
     */
    private void zamknijButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zamknijButtonActionPerformed
        clientWorker.endGame();
    }//GEN-LAST:event_zamknijButtonActionPerformed

    /**
     * Akcja wywołana po naciśnięciu przyisku Zapisz wynik Zapisuje wynik gry w
     * pliku tekstowym na pulpicie użytkownika
     *
     * @param evt
     */
    private void zapiszButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zapiszButtonActionPerformed
        String wynik = "Wynik: " + clientWorker.getResult() + "\nHasło: " + clientWorker.getPhrase() + "\nIlość ruchów: " + clientWorker.getGuessCount();
        try {
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            try (FileWriter plik = new FileWriter(desktopPath + "/wynik.txt")) {
                plik.write(wynik);
            }
            System.out.println("Zapisano wynik do pliku");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd przy tworzeniu pliku");
        }
    }//GEN-LAST:event_zapiszButtonActionPerformed

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
            java.util.logging.Logger.getLogger(TheEndView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TheEndView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TheEndView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TheEndView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }
    ClientWorker clientWorker;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel phraseLabel;
    private javax.swing.JButton zaczniButton;
    private javax.swing.JButton zamknijButton;
    private javax.swing.JButton zapiszButton;
    // End of variables declaration//GEN-END:variables
}
