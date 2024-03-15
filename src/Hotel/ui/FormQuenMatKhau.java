package Hotel.ui;

import Hotel.dao.NhanVienDAO;
import Hotel.entity.NhanVien;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FormQuenMatKhau extends javax.swing.JFrame {

    NhanVienDAO dao = new NhanVienDAO();

    public FormQuenMatKhau() {
        setUndecorated(true);

        initComponents();
        setLocationRelativeTo(null);
        setTitle("Đăng nhập");
        setResizable(false);
        setIcon();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        setIcon();

    }

    public void khoiphuc() {
        String taikhoan = txtMaNV.getText();
        String email = txtEmail.getText();
        boolean check = false;
        try {
            NhanVien nhanVien = dao.findById(taikhoan);
            String taikhoan2 = nhanVien.getMaNV();
            System.out.println(taikhoan2);
  
            String email2 = nhanVien.getEmail();
            System.out.println(email2);
            if (taikhoan.equals(taikhoan2) && email.equals(email2)) {
                check = true;
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhân viên " + taikhoan);
                return;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
        if (check == true) {
            String randomCodeEmail = randomCode(5);
            try {
                Properties p = new Properties();
                p.put("mail.smtp.auth", "true");
                p.put("mail.smtp.starttls.enable", "true");
                p.put("mail.smtp.host", "smtp.gmail.com");
                p.put("mail.smtp.port", 587);
                String accountName = "duclmps26504@fpt.edu.vn";
                String accountPassword = "kcbpyjpkklnopkpd";

                Session s = Session.getInstance(p, new javax.mail.Authenticator() {
                    // Định nghĩa một lớp vô danh kế thừa từ Authenticator
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        // Trả về thông tin xác thực (tên tài khoản và mật khẩu)
                        return new javax.mail.PasswordAuthentication(accountName, accountPassword);
                    }
                });
                // random code
                System.out.println("code is: " + randomCodeEmail);
                String from = accountName;
                String to = email;
                String subject = "Code Reset Password From JAVA";
                String ip = getIPAddress();
                String body = "Bạn đang đăng nhập IP: " + ip + "\nMã code đăng nhập của bạn là:  " + randomCodeEmail;
                Message msg = new MimeMessage(s);
                msg.setFrom(new InternetAddress(from));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                msg.setSubject(subject);
                msg.setText(body);
                // gửi mail
                Transport.send(msg);
                System.out.println("da gui mail");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            boolean isCodeCorrect = false;
            do {
                String code = JOptionPane.showInputDialog("Nhập mã xác nhận đã gửi qua email: ");
                if (code == null) {
                    isCodeCorrect = true;
                    break;
                }
                if (randomCodeEmail.equals(code)) {
                    System.out.println("Xác nhận code");
                    this.dispose();
                    String maNV = txtMaNV.getText();
                    FormXacNhanMatKhau xacnhan = new FormXacNhanMatKhau(maNV);
                    xacnhan.setVisible(true);
                    isCodeCorrect = true; // Kết thúc vòng lặp nếu mã đúng
                } else {
                    JOptionPane.showMessageDialog(jPanel1, "Mã xác nhận không đúng! Vui lòng nhập lại.");
                }
            } while (!isCodeCorrect);
        }

    }

    public static String getIPAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    public static String randomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return randomString.toString();
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

        ImageIcon originalIcon3 = new ImageIcon("src/Hotel/icon/close.png");

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
    private boolean valid(){
        if (txtMaNV.getText().isEmpty()) {
            txtMaNV.requestFocus();
            JOptionPane.showMessageDialog(this, "Chưa nhập mã nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            txtEmail.requestFocus();
            JOptionPane.showMessageDialog(this, "Chưa nhập Email nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        JpaneBack = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogin = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 0, 153));

        JpaneBack.setBackground(new java.awt.Color(255, 255, 255));
        JpaneBack.setForeground(new java.awt.Color(0, 153, 204));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khôi Phục Tài Khoản", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(204, 51, 255));

        jLabel1.setText("Mã nhân viên");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Bạn sẽ nhận được email xác nhận để khôi phục");

        btnLogin.setBackground(new java.awt.Color(255, 0, 0));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Gửi mã");
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

        jLabel3.setText("Email");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaNV)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblClose.setBackground(new java.awt.Color(255, 0, 0));
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
                .addContainerGap()
                .addGroup(JpaneBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpaneBackLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JpaneBackLayout.setVerticalGroup(
            JpaneBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpaneBackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpaneBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpaneBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if (valid()) {
            khoiphuc();
        }


    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginMousePressed

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_lblCloseMouseClicked

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormQuenMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuenMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuenMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuenMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuenMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpaneBack;
    private javax.swing.JButton btnLogin;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    // End of variables declaration//GEN-END:variables
}
