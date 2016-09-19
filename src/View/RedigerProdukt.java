/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Handler.VareHandler;
import Model.Vare;

/**
 *
 * @author Mark
 */
public class RedigerProdukt extends javax.swing.JPanel {

    private VareHandler vh;
    private int id;
    /**
     * Creates new form RedigerProdukt
     */
    public RedigerProdukt() {
        this.setSize(190, 120);
        vh = VareHandler.getInstance();
        id = -1;
        initComponents();
        for (Vare v : vh.getVareList()) {
            jComboBox1.addItem(v.getVareNavn());
        }
    }
    
    public void gemDet(){
        if (id != -1) {
            vh.safeVare(id, jT_Navn.getText(), Integer.parseInt(jT_Pris.getText()));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jT_Navn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jT_Pris = new javax.swing.JTextField();

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Navn");

        jT_Navn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_NavnFocusLost(evt);
            }
        });

        jLabel2.setText("Pris i hele tal 10 kr = 1000;");

        jT_Pris.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_PrisFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jT_Navn)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
            .addComponent(jT_Pris)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jT_Navn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jT_Pris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        for (Vare v : vh.getVareList()) {
            if (v.getVareNavn().equals(jComboBox1.getSelectedItem().toString())) {
                id = v.getVareNummer();
                jT_Navn.setText(v.getVareNavn());
                jT_Pris.setText(""+v.getVarePris());
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jT_NavnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_NavnFocusLost
        gemDet();
    }//GEN-LAST:event_jT_NavnFocusLost

    private void jT_PrisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_PrisFocusLost
       gemDet();
    }//GEN-LAST:event_jT_PrisFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jT_Navn;
    private javax.swing.JTextField jT_Pris;
    // End of variables declaration//GEN-END:variables
}
