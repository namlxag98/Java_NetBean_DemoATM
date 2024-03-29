
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MrNam
 */
public class NapTien extends javax.swing.JFrame {
    public static String stk;
    /**
     * Creates new form NapTien
     */
    public NapTien() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSoTienNap = new javax.swing.JLabel();
        txtSoTienNap = new javax.swing.JTextField();
        btnDongY = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblSoTienNap.setText("Nhập vào số tiền đã nạp:");

        btnDongY.setText("Đồng Ý");
        btnDongY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongYActionPerformed(evt);
            }
        });

        btnQuayLai.setText("Quay Lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblSoTienNap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSoTienNap)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(btnDongY, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnQuayLai)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoTienNap)
                    .addComponent(txtSoTienNap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQuayLai)
                    .addComponent(btnDongY))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDongYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongYActionPerformed
        try {
            // TODO add your handling code here:
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:///qltk","root","");
            String sql= "update taikhoan set sodu=sodu+? where sothe=?";
            PreparedStatement pst=con.prepareStatement (sql);
            pst.setInt(1, Integer.parseInt(txtSoTienNap.getText()));
            pst.setString(2, stk);
            pst.executeUpdate();
            con.close();
            
             //Lich su giao dich
                    File file = new File("LichSuGiaoDich.txt"); 
                    java.util.Date date=new java.util.Date();
                    SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    try(FileWriter fw =new FileWriter(file,true);
                        BufferedWriter bf= new BufferedWriter(fw);
                        PrintWriter pw= new PrintWriter(bf)
                            ) {
                        pw.println("\t\t\t\t\tGIAO DỊCH NẠP TIỀN");
                        pw.println("Số tài khoản: "+stk);
                        pw.println("Số tiền đã nạp: "+txtSoTienNap.getText());
                        pw.println("Thời gian giao dịch: "+formatter.format(date));
                    } catch (IOException ex) {
                Logger.getLogger(NapTien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                    
            JOptionPane.showMessageDialog(null, "Nạp tiền thành công");
            txtSoTienNap.setText("");
            txtSoTienNap.requestFocus();
                } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NapTien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnDongYActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        new  GiaoDien().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnQuayLaiActionPerformed

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
            java.util.logging.Logger.getLogger(NapTien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NapTien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NapTien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NapTien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NapTien().setVisible(true);
            }
        });
    }
    public static void sotaikhoan(String args[]){
        stk=args[0];
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDongY;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JLabel lblSoTienNap;
    private javax.swing.JTextField txtSoTienNap;
    // End of variables declaration//GEN-END:variables
}
