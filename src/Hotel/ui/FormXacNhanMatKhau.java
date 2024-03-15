package Hotel.ui;

import Hotel.dao.NhanVienDAO;
import Hotel.entity.NhanVien;
import Hotel.utils.DialogHelper;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FormXacNhanMatKhau extends javax.swing.JFrame {

    NhanVienDAO dao = new NhanVienDAO();
    private String maNVNew;

    public FormXacNhanMatKhau(String maNV) {
        setUndecorated(true);
        this.maNVNew = maNV;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Đăng nhập");
        setResizable(false);
        txtMatKhau1.setText("admin");
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        setIcon();

    }

    public void resetPass() {
        String matKhauCu = txtMatKhau1.getText();
        String matKhauMoi = txtNhaplai.getText();
        if (matKhauCu.equals(matKhauMoi)) {
            NhanVien model = getModelResetPass();
            dao.updatePassword(model);
            DialogHelper.alert(this, "Đổi mật khẩu mới thành công cho nhân viên: "+maNVNew);
            this.dispose();

        }
    }

    NhanVien getModelResetPass() {
        String user = txtMatKhau1.getText();
        String newpass = txtNhaplai.getText();
        NhanVien model = new NhanVien();
        model.setMatKhau(newpass);
        model.setMaNV(maNVNew);
        return model;
    }

    public void backgroundForm() { //background cho form
        ImageIcon bgForm = new ImageIcon("src/images/room.jpg");
        Image image = bgForm.getImage();
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        });
    }

    public void setIcon() {
        // Tạo một ImageIcon từ tệp hình ảnh

        ImageIcon originalIcon3 = new ImageIcon("src//Hotel/icon/close.png");

        Image originalImage3 = originalIcon3.getImage();

        // Giảm kích thước hình ảnh
        int newWidth = 22; // Kích thước mới
        int newHeight = 22;

        Image scaledImage3 = originalImage3.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);

        lblClose.setIcon(scaledIcon3);

        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Hành động khi chuột vào JLabel
                // Hành động khi chuột vào JLabel
                lblClose.setToolTipText("Thoát");

                ImageIcon originalIcon3 = new ImageIcon("src/Hotel/icon/close2.png");
                Image originalImage3 = originalIcon3.getImage();

                int newWidth = 25; // Kích thước mới
                int newHeight = 25;

                Image scaledImage3 = originalImage3.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);

                lblClose.setIcon(scaledIcon3);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                int newWidth = 22; // Kích thước mới
                int newHeight = 22;

                Image scaledImage3 = originalImage3.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);

                lblClose.setIcon(scaledIcon3);
            }
        });

    }

    public static String convertToAsterisks(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append('*');
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        JpaneBack = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMatKhau1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogin = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNhaplai = new javax.swing.JTextField();
        lblClose = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 0, 153));

        JpaneBack.setBackground(new java.awt.Color(255, 255, 255));
        JpaneBack.setForeground(new java.awt.Color(0, 153, 204));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khôi Phục Tài Khoản", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(204, 51, 255));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Mật khẩu mới");

        txtMatKhau1.setBackground(new java.awt.Color(255, 255, 255));
        txtMatKhau1.setForeground(new java.awt.Color(0, 0, 0));
        txtMatKhau1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhau1ActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(255, 0, 0));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Xác nhận");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLoginMousePressed(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nhập lại mật khẩu");

        txtNhaplai.setBackground(new java.awt.Color(255, 255, 255));
        txtNhaplai.setForeground(new java.awt.Color(0, 0, 0));
        txtNhaplai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNhaplaiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMatKhau1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 186, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addComponent(txtNhaplai))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNhaplai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblClose.setBackground(new java.awt.Color(255, 0, 0));
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Alarm.png"))); // NOI18N
        lblClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JpaneBackLayout = new javax.swing.GroupLayout(JpaneBack);
        JpaneBack.setLayout(JpaneBackLayout);
        JpaneBackLayout.setHorizontalGroup(
            JpaneBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpaneBackLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JpaneBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpaneBackLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpaneBackLayout.createSequentialGroup()
                        .addComponent(lblClose)
                        .addGap(14, 14, 14))))
        );
        JpaneBackLayout.setVerticalGroup(
            JpaneBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpaneBackLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpaneBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpaneBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        resetPass();

    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginMousePressed

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        // TODO add your handling code here:
        boolean chose = DialogHelper.confirm(jPanel1, "Mật khẩu vẫn chưa được khôi phục, bạn có thực sự muốn thoát?");
        if (chose) {
            this.dispose();
        } else {
            return;
        }
    }//GEN-LAST:event_lblCloseMouseClicked

    private void txtMatKhau1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhau1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhau1ActionPerformed

    private void txtNhaplaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNhaplaiMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtNhaplaiMouseClicked

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormXacNhanMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormXacNhanMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormXacNhanMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormXacNhanMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpaneBack;
    private javax.swing.JButton btnLogin;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JTextField txtMatKhau1;
    private javax.swing.JTextField txtNhaplai;
    // End of variables declaration//GEN-END:variables
}
