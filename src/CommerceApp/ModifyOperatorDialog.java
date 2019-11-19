/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommerceApp;

import Adapters.JDBCAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import objects.Validation;
import util.FileProcess;
import util.Operation;

/**
 *
 * @author DELL
 */
public class ModifyOperatorDialog extends OperatorDialog implements Validation{
    Operation operation;
    JDBCAdapter operator;
    int operatorId;
    String operatorName;
    String query;
    String update = "UPDATE ";
    /**
     * Creates new form ModifyOperatorDialog
     */
    
    public ModifyOperatorDialog(JFrame frm, Operation op, int i){
        super(frm,op,FileProcess.MODIFY);
        operatorId = i;
        operation = op;
        init();
    }

    public ModifyOperatorDialog(JFrame frm, Operation op, String s){
        super(frm, op,FileProcess.MODIFY);
        operatorName = s;
        operation = op;
        initComponents();
        query = "SELECT * FROM " + op.getTableName() +
                " WHERE NOM ='" + operatorName + "'";
        displayOperatorInformation();
    }
    public ModifyOperatorDialog(JDialog dlg, Operation op, int i){
        super(dlg,op,FileProcess.MODIFY);
        operatorId = i;
        operation = op;
        init();
    }
    
    private void init(){
        initComponents();
        query = "SELECT * FROM " + operation.getTableName() +
                " WHERE ID =" + operatorId;
        displayOperatorInformation();
        okButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        okButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                okButtonKeyPressed(evt);
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    private void okButtonKeyPressed(KeyEvent evt) {
                int keyCode = evt.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_ENTER:
                record();
                dispose();
            break;            
            case KeyEvent.VK_ESCAPE:
                dispose();
            break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                okButton.requestFocusInWindow();
            break;
        }
    }
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        record();
        dispose();
    } 
    private void displayOperatorInformation() {
        operator = JDBCAdapter.connect();
        operator.executeQuery(query);
        if (operator.getRowCount() != 0){
            id.setText(Integer.toString((int)operator.getValueAt(0, 0)));
            designation.setText((String)operator.getValueAt(0, 1));
            adresse.setText((String)operator.getValueAt(0, 2));
            wilaya.setText(Integer.toString((int)operator.getValueAt(0, 3)));
            nrc.setText((String)operator.getValueAt(0, 4));
            nfi.setText((String)operator.getValueAt(0, 5));
            nar.setText((String)operator.getValueAt(0, 6));
            tel1.setText((String)operator.getValueAt(0,7));
            tel2.setText((String)operator.getValueAt(0, 8));
            tel3.setText((String)operator.getValueAt(0, 9));
            fax.setText((String)operator.getValueAt(0, 10));
            email.setText((String)operator.getValueAt(0, 11));
            web.setText((String)operator.getValueAt(0, 12));
            BigDecimal bd =(BigDecimal)operator.getValueAt(0, 13);
            solde.setText(bd.toString());
            obs.setText((String)operator.getValueAt(0, 14));
        }else{
            JOptionPane.showMessageDialog(this, "la requête ne peut aboutir la recherche a échoué");
            System.out.println("this line is reached");
            dispose();
        }
    }

    @Override
    public void record() {
        String sets="SET ";
        //Verify wether there are modification.
        boolean anyModification = false;
        if (!designation.getText().equals(operator.getValueAt(0, 1))){
            anyModification = true;
            sets += "NOM='" + designation.getText() +"' ";
        }
        if (!adresse.getText().equals(operator.getValueAt(0, 2))){
            if (anyModification){
                sets += ", "+ "ADR=" + "'" + adresse.getText() + "' ";
            }else{
                anyModification = true;
                sets += "ADR=" + "'" + adresse.getText() + "' ";
            }
        }
        int w = Integer.parseInt(wilaya.getText());
        int ww = (int)operator.getValueAt(0, 3);
        if (w != ww){
            if (anyModification){
                sets += ", WILAYA=" + wilaya.getText();
            }else{
                anyModification = true;
                sets += "WILAYA=" + wilaya.getText();
            }
        }
        
        if (!nrc.getText().equals(operator.getValueAt(0, 4))){
            if (anyModification){
                sets += ", NRC='" + nrc.getText() + "' ";
            }else{
                anyModification = true;
                sets += "NRC='" + nrc.getText() + "' ";
            }
        }
        if (!nfi.getText().equals(operator.getValueAt(0, 5))){
            if (anyModification){
                sets += ", NFI='" + nfi.getText() + "' ";
            }else{
                anyModification = true;
                sets += "NFI='" + nfi.getText() + "' ";
            }
        }
        if (!nar.getText().equals(operator.getValueAt(0, 6))){
            if (anyModification){
                sets += ", NAR='" + nar.getText() + "' ";
            }else{
                anyModification = true;
                sets += "NAR='" + nar.getText() + "' ";
            }
        }        
        if (!tel1.getText().equals(operator.getValueAt(0, 7))){
            if (anyModification){
                sets += ", TEL1='" + tel1.getText() + "' ";
            }else{
                anyModification = true;
                sets += "TEL1='" + tel1.getText() + "' ";
            }
        }
        if (!tel2.getText().equals(operator.getValueAt(0, 8))){
            if (anyModification){
                sets += ", TEL2='" + tel2.getText() + "' ";
            }else{
                anyModification = true;
                sets += "TEL2='" + tel2.getText() + "' ";
            }
        }
        if (!tel3.getText().equals(operator.getValueAt(0, 9))){
            if (anyModification){
                sets += ", TEL3='" + tel1.getText() + "' ";
            }else{
                anyModification = true;
                sets += "TEL3='" + tel1.getText() + "' ";
            }
        }
        if (!fax.getText().equals(operator.getValueAt(0, 10))){
            if (anyModification){
                sets += ", FAX='" + fax.getText() + "' ";
            }else{
                anyModification = true;
                sets += "FAX='" + fax.getText() + "' ";
            }
        }
        if (!email.getText().equals(operator.getValueAt(0, 11))){
            if (anyModification){
                sets += ", EMAIL='" + email.getText() + "' ";
            }else{
                anyModification = true;
                sets += "EMAIL='" + email.getText() + "' ";
            }
        }
        if (!web.getText().equals(operator.getValueAt(0, 12))){
            if (anyModification){
                sets += ", WEB='" + web.getText() + "' ";
            }else{
                anyModification = true;
                sets += "WEB='" + web.getText() + "' ";
            }
        }
        BigDecimal s = new BigDecimal(solde.getText());
        BigDecimal ss = (BigDecimal)operator.getValueAt(0, 13);
        if (!s.equals(ss)){
            if (anyModification){
                sets += ", SOLDE=" + solde.getText();
            }else{
                anyModification = true;
                sets += "SOLDE=" + solde.getText();
            }
        }
        if (!obs.getText().equals(operator.getValueAt(0, 14))){
            if (anyModification){
                sets += ", OBS='" + obs.getText() + "' ";
            }else{
                anyModification = true;
                sets += "OBS='" + obs.getText() + "' ";
            }
        }

        if (anyModification){
            update += operation.getTableName() + " ";
            update += sets;
            update += " WHERE ID=" + operatorId;
            //if any process updating modified fields.
            System.out.println("enregistrement des modifications " + operation.getFrameTitle());
            System.out.println(update);
            JDBCAdapter table = JDBCAdapter.connect();
            table.executeUpdate(update);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
