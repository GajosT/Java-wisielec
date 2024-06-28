package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;
import javax.swing.JTextField;

/**
 * Panel do wpisywania hasła
 *
 * @author Tomasz Gajos
 */
public class WordInputView extends javax.swing.JPanel {

    /**
     * Tworzy instancję WordInputView i inicjalizuje jej składniki
     *
     * @param phraseTextField pole do wpisywania hasła
     * @param clientWorker wątek odpowiadający za zarządzanie stanem gry
     * @param phrase hasło
     * @param closeFunction funkcja zamykająca panel
     */
    public WordInputView(JTextField phraseTextField, ClientWorker clientWorker, String phrase, Function<Object, Object> closeFunction) {
        this.phraseTextField = phraseTextField;
        this.clientWorker = clientWorker;
        this.closeFunction = closeFunction;
        this.phrase = phrase;
        initComponents();
        phraseInputTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                podajButton.doClick();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        phraseInputTextField = new javax.swing.JTextField();
        podajButton = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Podaj hasło do zgadnięcia");

        podajButton.setText("Podaj");
        podajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                podajButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(phraseInputTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(podajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(podajButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(phraseInputTextField))
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Akcja wywoływana po naciśnięciu przycisku podaj Jeżeli pole nie jest
     * puste, ustawia hasło i wysyła je do serwera
     *
     * @param evt
     */
    private void podajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_podajButtonActionPerformed
        String test = phraseInputTextField.getText().trim().toLowerCase();
        if (!test.isEmpty()) {
            phrase = test;
            clientWorker.registerPhrase(test);
            phraseTextField.setText(test);
            closeFunction.apply(null);
        }
    }//GEN-LAST:event_podajButtonActionPerformed

    private JTextField phraseTextField;
    String phrase;
    ClientWorker clientWorker;
    private Function<Object, Object> closeFunction;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField phraseInputTextField;
    private javax.swing.JButton podajButton;
    // End of variables declaration//GEN-END:variables
}
