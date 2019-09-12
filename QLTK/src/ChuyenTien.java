
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ChuyenTien extends javax.swing.JFrame {
    public static String stk;
    /**
     * Creates new form ChuyenTien
     */
    public ChuyenTien() {
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

        txtSoTienGui = new javax.swing.JTextField();
        txtSoThe = new javax.swing.JTextField();
        lblSoTienGui = new javax.swing.JLabel();
        lblSoThe = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JButton();
        btnDongY = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblSoTienGui.setText("Nhập số tiền muốn gửi:");

        lblSoThe.setText("Nhập tài khoản nhận:");

        btnQuayLai.setText("Quay Lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        btnDongY.setText("Đồng Ý");
        btnDongY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSoTienGui)
                            .addComponent(lblSoThe))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoThe, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtSoTienGui)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(btnDongY, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btnQuayLai)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoTienGui)
                    .addComponent(txtSoTienGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoThe))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQuayLai)
                    .addComponent(btnDongY))
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDongYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongYActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:///qltk","root","");
            String sqlcheck= "Select * from taikhoan where sothe=?";
            PreparedStatement pst1=con.prepareStatement (sqlcheck);
            pst1.setString(1, stk);
            ResultSet rs= pst1.executeQuery();
            if(rs.next()){
                if(stk.equals(txtSoThe.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Tài khoản không hợp lệ");
                    txtSoTienGui.setText("");
                    txtSoThe.setText("");
                    txtSoTienGui.requestFocus();
                }
            else{
                if(Integer.parseInt(txtSoTienGui.getText())>(rs.getInt("sodu")-50000)||Integer.parseInt(txtSoTienGui.getText())%10000!=0){
                    JOptionPane.showMessageDialog(null, "Số tiền không hợp lệ");
                    txtSoTienGui.setText("");
                    txtSoThe.setText("");
                    txtSoTienGui.requestFocus();
                } 
                else
                {
                    String sql= "update taikhoan set sodu=sodu+? where sothe=?";
                    PreparedStatement pst=con.prepareStatement (sql);
                    pst.setInt(1, Integer.parseInt(txtSoTienGui.getText()));
                    pst.setString(2, txtSoThe.getText());
                    pst.executeUpdate();

                    pst.setInt(1, -Integer.parseInt(txtSoTienGui.getText()));
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
                        pw.println("\t\t\t\t\tGIAO DỊCH CHUYỂN TIỀN");
                        pw.println("Tài khoản thực hiện giao dịch: "+stk+" -> "+txtSoThe.getText());
                        pw.println("Số tiền đã chuyển: "+txtSoTienGui.getText());
                        pw.println("Thời gian giao dịch: "+formatter.format(date));
                    } catch (IOException ex) {
                        Logger.getLogger(ChuyenTien.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    
                    JOptionPane.showMessageDialog(null, "Chuyển tiền thành công");
                    txtSoTienGui.setText("");
                    txtSoThe.setText("");
                    txtSoTienGui.requestFocus();
                }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ChuyenTien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDongYActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        new GiaoDien().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnQuayLaiActionPerformed
    
    public static void sotaikhoan(String args[]){
        stk=args[0];
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
            java.util.logging.Logger.getLogger(ChuyenTien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChuyenTien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChuyenTien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChuyenTien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChuyenTien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDongY;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JLabel lblSoThe;
    private javax.swing.JLabel lblSoTienGui;
    private javax.swing.JTextField txtSoThe;
    private javax.swing.JTextField txtSoTienGui;
    // End of variables declaration//GEN-END:variables
}