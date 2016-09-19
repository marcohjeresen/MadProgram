/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.VareController;
import Handler.KurvHandler;
import Handler.VareHandler;
import Model.Ret;
import Model.RetType;
import Model.Vare;
import Model.VareGruppe;
import Model.VareLine;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import util.Listeners;
import util.numberutil;

/**
 *
 * @author markh_000
 */
public class view extends javax.swing.JFrame implements ActionListener {

    private VareHandler vareHandler;
    private KurvHandler kurvHandler;
    private numberutil nm;
    private Listeners listeners;

    /**
     * Creates new form view
     */
    public view() {
        vareHandler = VareHandler.getInstance();
        kurvHandler = KurvHandler.getInstance();
        nm = new numberutil();
        listeners = Listeners.getList();
        listeners.addListener(this);
        initComponents();
        this.setSize(1320, 755);
        jP_produkter.setPreferredSize(new Dimension(50, 50));
        jP_RetTyper.setPreferredSize(new Dimension(50, 50));
        jP_retter.setPreferredSize(new Dimension(50, 50));
        jP_Kurv.setPreferredSize(new Dimension(50, 50));
        jP_retterTilføjet.setPreferredSize(new Dimension(50, 50));
        jSlider1.setMaximum(kurvHandler.getAntalAllRettersDage()-1);
        jLabel2.setText(jSlider1.getValue() + "");
        jL_AntalVare.setText("");
        jL_PrisIalt.setText("");
        jL_fejl.setText("");
        jL_AntalDAge.setText("");
        jL_SøgFejl.setText("");
        jB_Rediger.setEnabled(true);
        InsætProduktGrupper();
        InsætRetTyper();
    }

    public void InsætProduktGrupper() {
        ProduktGruppe pg;
        jP_ProduktGrup.removeAll();
        for (int i = 0; i < vareHandler.getVareGruppe().size(); i++) {
            pg = new ProduktGruppe(vareHandler.getVareGruppe().get(i).getId(), vareHandler.getVareGruppe().get(i).getNavn());
            jP_ProduktGrup.add(pg);
            pg.setLocation(6, ((5*i)+(pg.getHeight()*i)));
            pg.setVisible(true);
            int count = i+1;
            jP_ProduktGrup.setPreferredSize(new Dimension(pg.getWidth(), ((5*count)+(pg.getHeight()*count))));
        }
        jP_ProduktGrup.revalidate();
        jP_ProduktGrup.repaint();
    }
    
    public void InsætProdukter(){
        ProduktKnap pk;
        jP_produkter.removeAll();
        for (int i = 0; i < vareHandler.getChoosenVare().size(); i++) {
            pk = new ProduktKnap(vareHandler.getChoosenVare().get(i));
            jP_produkter.add(pk);
            pk.setLocation(0, ((5*i)+(pk.getHeight()*i)));
            pk.setVisible(true);
            int count = i+1;
            jP_produkter.setPreferredSize(new Dimension(pk.getWidth(), ((5*count)+(pk.getHeight()*count))));
        }
        jP_produkter.revalidate();
        jP_produkter.repaint();
    }
    
    public void InsætRetTyper(){
        RetTypeKnap rt;
        jP_RetTyper.removeAll();
        for (int i = 0; i < vareHandler.getRetType().size(); i++) {
            rt = new RetTypeKnap(vareHandler.getRetType().get(i));
            jP_RetTyper.add(rt);
            rt.setLocation(0, ((5*i)+(rt.getHeight()*i)));
            rt.setVisible(true);
            int count = i+1;
            jP_RetTyper.setPreferredSize(new Dimension(rt.getWidth(), ((5*count)+(rt.getHeight()*count))));
        }
        jP_RetTyper.revalidate();
        jP_RetTyper.repaint();
    }
    
    public void IndsætRetter(){
        RetKnap rk;
        jP_retter.removeAll();
        for (int i = 0; i < vareHandler.getChoosenRetList().size(); i++) {
            rk = new RetKnap(vareHandler.getChoosenRetList().get(i));
            jP_retter.add(rk);
            rk.setLocation(0, ((5*i)+(rk.getHeight()*i)));
            rk.setVisible(true);
            int count = i+1;
            jP_retter.setPreferredSize(new Dimension(rk.getWidth(), ((5*count)+(rk.getHeight()*count))));
        }
        jP_retter.revalidate();
        jP_retter.repaint();
    }
    
    public void IndsætPåKurv(){
        KurvIndhold ki;
        jP_Kurv.removeAll();
        for (int i = 0; i < kurvHandler.getVareLine().size(); i++) {
            ki = new KurvIndhold(kurvHandler.getVareLine().get(i));
            jP_Kurv.add(ki);
            ki.setLocation(0, ((5*i)+(ki.getHeight()*i)));
            ki.setVisible(true);
            int count = i+1;
            jP_Kurv.setPreferredSize(new Dimension(ki.getWidth(), ((5*count)+(ki.getHeight()*count))));
        }
        jP_Kurv.revalidate();
        jP_Kurv.repaint();
    }
    
    public void IndsætPåRetTilføjet(){
        TilføjetRetter tr;
        jP_retterTilføjet.removeAll();
        for (int i = 0; i < kurvHandler.getRetList().size(); i++) {
            tr = new TilføjetRetter(kurvHandler.getRetList().get(i));
            jP_retterTilføjet.add(tr);
            tr.setLocation(0, ((5*i)+(tr.getHeight()*i)));
            tr.setVisible(true);
            int count = i +1;
            jP_retterTilføjet.setPreferredSize(new Dimension(tr.getWidth(), ((5*count)+(tr.getHeight()*count))));
        }
        jP_retterTilføjet.revalidate();
        jP_retterTilføjet.repaint();
    }
    
    public void IndsætSøgVare(){
        ProduktKnap pk;
        jP_produkter.removeAll();
        ArrayList<Vare> SøgList = vareHandler.søgVare(jT_SøgProdukt.getText());
        for (int i = 0; i < SøgList.size(); i++) {
            pk = new ProduktKnap(SøgList.get(i));
            jP_produkter.add(pk);
            pk.setLocation(0, ((5*i)+(pk.getHeight()*i)));
            pk.setVisible(true);
            int count = i +1;
            jP_produkter.setPreferredSize(new Dimension(pk.getWidth(), ((5*count)+(pk.getHeight()*count))));
        }
        jP_produkter.revalidate();
        jP_produkter.repaint();
        if (SøgList.size() == 0) {
            jL_SøgFejl.setText("Ingen Produkter Fundet");
        }else{
            jL_SøgFejl.setText("");
        }
    }
    
    public void CreateAlterRet(boolean editRet){
        JFrame jf = new JFrame("Tilføj Ret");
        TilføjNyRet tr = new TilføjNyRet(editRet);
        jf.add(tr);
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tr.setVisible(true);
        jf.setVisible(true);
        jf.setSize(530, 440);
        
        int højde = ((this.getHeight()-jf.getHeight())/2);
        int brede = ((this.getWidth()-jf.getWidth())/2);
        jf.setLocation(brede, højde);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jL_fejl = new javax.swing.JLabel();
        jL_PrisIalt = new javax.swing.JLabel();
        jL_AntalVare = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jB_Rediger = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jP_ProduktGrup = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jP_produkter = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jP_RetTyper = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jP_retter = new javax.swing.JPanel();
        jSc_Kurv = new javax.swing.JScrollPane();
        jP_Kurv = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_retterTilføjet = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jL_AntalDAge = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jT_SøgProdukt = new javax.swing.JTextField();
        jB_søg = new javax.swing.JButton();
        jL_SøgFejl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1130, 660, 160, 50);

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(970, 660, 160, 50);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Bland Retter"));

        jLabel1.setText("Antal Dage:");

        jSlider1.setMaximum(30);
        jSlider1.setMinimum(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setValue(15);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel2.setText("0");

        jButton3.setText("Find");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jL_fejl.setText("jLabel4");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jL_fejl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_fejl)
                .addGap(0, 128, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel7);
        jPanel7.setBounds(460, 440, 200, 270);

        jL_PrisIalt.setText("jLabel3");
        getContentPane().add(jL_PrisIalt);
        jL_PrisIalt.setBounds(970, 630, 100, 30);

        jL_AntalVare.setText("jLabel4");
        getContentPane().add(jL_AntalVare);
        jL_AntalVare.setBounds(1070, 630, 90, 30);

        jLabel5.setText("Pris Alt I Alt:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(970, 604, 100, 30);

        jLabel6.setText("Antal Vare:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(1070, 610, 80, 20);

        jButton4.setText("Tilføj Produkter:");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(10, 510, 170, 40);

        jButton5.setText("Tilføj Retter:");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(10, 610, 170, 40);

        jButton6.setText("Rediger Produkt:");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(10, 560, 170, 40);

        jB_Rediger.setText("Rediger Ret:");
        jB_Rediger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_RedigerActionPerformed(evt);
            }
        });
        getContentPane().add(jB_Rediger);
        jB_Rediger.setBounds(10, 660, 170, 40);

        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produkt Grupper"));

        jP_ProduktGrup.setPreferredSize(new java.awt.Dimension(50, 437));

        javax.swing.GroupLayout jP_ProduktGrupLayout = new javax.swing.GroupLayout(jP_ProduktGrup);
        jP_ProduktGrup.setLayout(jP_ProduktGrupLayout);
        jP_ProduktGrupLayout.setHorizontalGroup(
            jP_ProduktGrupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );
        jP_ProduktGrupLayout.setVerticalGroup(
            jP_ProduktGrupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        jScrollPane7.setViewportView(jP_ProduktGrup);

        getContentPane().add(jScrollPane7);
        jScrollPane7.setBounds(0, 0, 190, 500);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder("Produkter"));

        javax.swing.GroupLayout jP_produkterLayout = new javax.swing.GroupLayout(jP_produkter);
        jP_produkter.setLayout(jP_produkterLayout);
        jP_produkterLayout.setHorizontalGroup(
            jP_produkterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );
        jP_produkterLayout.setVerticalGroup(
            jP_produkterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );

        jScrollPane8.setViewportView(jP_produkter);

        getContentPane().add(jScrollPane8);
        jScrollPane8.setBounds(190, 0, 270, 500);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Ret Typer"));

        javax.swing.GroupLayout jP_RetTyperLayout = new javax.swing.GroupLayout(jP_RetTyper);
        jP_RetTyper.setLayout(jP_RetTyperLayout);
        jP_RetTyperLayout.setHorizontalGroup(
            jP_RetTyperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        jP_RetTyperLayout.setVerticalGroup(
            jP_RetTyperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        jScrollPane6.setViewportView(jP_RetTyper);

        getContentPane().add(jScrollPane6);
        jScrollPane6.setBounds(460, 0, 200, 440);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Retter"));

        javax.swing.GroupLayout jP_retterLayout = new javax.swing.GroupLayout(jP_retter);
        jP_retter.setLayout(jP_retterLayout);
        jP_retterLayout.setHorizontalGroup(
            jP_retterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        jP_retterLayout.setVerticalGroup(
            jP_retterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(jP_retter);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(660, 0, 300, 440);

        jSc_Kurv.setBorder(javax.swing.BorderFactory.createTitledBorder("Kurv"));

        javax.swing.GroupLayout jP_KurvLayout = new javax.swing.GroupLayout(jP_Kurv);
        jP_Kurv.setLayout(jP_KurvLayout);
        jP_KurvLayout.setHorizontalGroup(
            jP_KurvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );
        jP_KurvLayout.setVerticalGroup(
            jP_KurvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
        );

        jSc_Kurv.setViewportView(jP_Kurv);

        getContentPane().add(jSc_Kurv);
        jSc_Kurv.setBounds(960, 0, 340, 610);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Retter Tilføjet"));

        javax.swing.GroupLayout jP_retterTilføjetLayout = new javax.swing.GroupLayout(jP_retterTilføjet);
        jP_retterTilføjet.setLayout(jP_retterTilføjetLayout);
        jP_retterTilføjetLayout.setHorizontalGroup(
            jP_retterTilføjetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        jP_retterTilføjetLayout.setVerticalGroup(
            jP_retterTilføjetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jP_retterTilføjet);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(660, 440, 300, 270);

        jLabel3.setText("Antal Dage:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(1170, 610, 120, 14);

        jL_AntalDAge.setText("jLabel4");
        getContentPane().add(jL_AntalDAge);
        jL_AntalDAge.setBounds(1170, 630, 100, 30);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Søg Efter Produkt"));

        jLabel4.setText("Produkt Navn");

        jB_søg.setText("Søg");
        jB_søg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_søgActionPerformed(evt);
            }
        });

        jL_SøgFejl.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
            .addComponent(jT_SøgProdukt)
            .addComponent(jB_søg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jL_SøgFejl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jT_SøgProdukt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jB_søg, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_SøgFejl)
                .addGap(0, 81, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(190, 500, 270, 210);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        jLabel2.setText(jSlider1.getValue() + "");
    }//GEN-LAST:event_jSlider1StateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        kurvHandler.removeAll();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int total = kurvHandler.getAntalDageRetter() + jSlider1.getValue();
        if (total < kurvHandler.getAntalAllRettersDage()) {
            kurvHandler.FindRandomRetter(jSlider1.getValue());
            jL_fejl.setText("");
        }else{
            jL_fejl.setText("SLET FRA LISTEN FØRST");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        JFrame jf = new JFrame("Rediger Vare");
        RedigerProdukt rd = new RedigerProdukt();
        jf.add(rd);
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        rd.setVisible(true);
        jf.setVisible(true);
        jf.setSize(250, 200);
        int højde = ((this.getHeight()-jf.getHeight())/2);
        int brede = ((this.getWidth()-jf.getWidth())/2);
        jf.setLocation(brede, højde);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jB_søgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_søgActionPerformed
        IndsætSøgVare();
    }//GEN-LAST:event_jB_søgActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFrame jf = new JFrame("Tilføj Vare");
        TilføjProdukt tp = new TilføjProdukt();
        jf.add(tp);
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tp.setVisible(true);
        jf.setVisible(true);
        jf.setSize(190, 250);
        int højde = ((this.getHeight()-jf.getHeight())/2);
        int brede = ((this.getWidth()-jf.getWidth())/2);
        jf.setLocation(brede, højde);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CreateAlterRet(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame jf = new JFrame("Se Liste");
        Print tr = new Print();
        jf.add(tr);
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tr.setVisible(true);
        jf.setVisible(true);
        jf.setSize(500, 745);
        
        int højde = ((this.getHeight()-jf.getHeight())/2);
        int brede = ((this.getWidth()-jf.getWidth())/2);
        jf.setLocation(brede, højde);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jB_RedigerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_RedigerActionPerformed
        CreateAlterRet(true);
    }//GEN-LAST:event_jB_RedigerActionPerformed

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
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_Rediger;
    private javax.swing.JButton jB_søg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jL_AntalDAge;
    private javax.swing.JLabel jL_AntalVare;
    private javax.swing.JLabel jL_PrisIalt;
    private javax.swing.JLabel jL_SøgFejl;
    private javax.swing.JLabel jL_fejl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jP_Kurv;
    private javax.swing.JPanel jP_ProduktGrup;
    private javax.swing.JPanel jP_RetTyper;
    private javax.swing.JPanel jP_produkter;
    private javax.swing.JPanel jP_retter;
    private javax.swing.JPanel jP_retterTilføjet;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jSc_Kurv;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jT_SøgProdukt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()){
            case "Produkt Gruppe Valgt":
                InsætProdukter();
                break;
            case "Ret Type Valgt":
                IndsætRetter();
                break;
            case "Update":
                IndsætPåKurv();
                IndsætPåRetTilføjet();
                jL_AntalVare.setText(""+kurvHandler.getAntalProdukter());
                jL_PrisIalt.setText(""+ nm.getpris(kurvHandler.getTotalPris()) + " Kr.");
                jL_AntalDAge.setText(""+ kurvHandler.getAntalDageRetter());
                break;
            case "UpdateAll":
                InsætProdukter();
                IndsætRetter();
                IndsætPåKurv();
                IndsætPåRetTilføjet();
                jL_AntalVare.setText(""+kurvHandler.getAntalProdukter());
                jL_PrisIalt.setText(""+ nm.getpris(kurvHandler.getTotalPris()) + " Kr.");
                jL_AntalDAge.setText(""+ kurvHandler.getAntalDageRetter());
                break;
            case "Update Fucking All":
                InsætProduktGrupper();
                InsætProdukter();
                InsætRetTyper();
                IndsætRetter();
                IndsætPåKurv();
                IndsætPåRetTilføjet();
                jL_AntalVare.setText(""+kurvHandler.getAntalProdukter());
                jL_PrisIalt.setText(""+ nm.getpris(kurvHandler.getTotalPris()) + " Kr.");
                jL_AntalDAge.setText(""+ kurvHandler.getAntalDageRetter());
                break;
                
        }

    }
}
