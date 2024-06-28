package client;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Widok uruchamiany po wyjściu drugiego gracza po ukończeniu gry
 *
 * @author Tomasz Gajos
 */
public class PlayerLeftView extends javax.swing.JFrame {

    /**
     * Tworzy instancję PlayerLeftView i inicjalizuje jej składniki
     *
     * @param result wynik gry
     * @param phrase hasło
     * @param guessCount ilość ruchów
     */
    public PlayerLeftView(String result, String phrase, int guessCount) {
        this.result = result;
        this.phrase = phrase;
        this.guessCount = guessCount;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        zamknijButton = new javax.swing.JButton();
        zapiszButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Drugi gracz opuścił rozgrywkę");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nie można kontynuować");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(zapiszButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(zamknijButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zapiszButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zamknijButton))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Akcja wywołana po naciśnięciu przycisku Zamknij Wyłącza aplikację
     *
     * @param evt
     */
    private void zamknijButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zamknijButtonActionPerformed
        dispose();
    }//GEN-LAST:event_zamknijButtonActionPerformed

    /**
     * Akcja wywołana po naciśnięciu przyisku Zapisz wynik Zapisuje wynik gry w
     * pliku tekstowym na pulpicie użytkownika
     *
     * @param evt
     */
    private void zapiszButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zapiszButtonActionPerformed
        String wynik = "Wynik: " + result + "\nHasło: " + phrase + "\nIlość ruchów: " + guessCount;
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
            java.util.logging.Logger.getLogger(PlayerLeftView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerLeftView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerLeftView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerLeftView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    private final String result;
    private final String phrase;
    private final int guessCount;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton zamknijButton;
    private javax.swing.JButton zapiszButton;
    // End of variables declaration//GEN-END:variables
}
