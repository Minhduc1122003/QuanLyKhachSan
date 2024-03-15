/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Hotel.ui;

import Hotel.dao.DSPhongDAo;
import Hotel.entity.DSPhong;
import java.awt.Color;

/**
 *
 * @author Admin
 */
public class JpanelChiTietPhong extends javax.swing.JPanel {
        private Main mainForm; // Tham chiếu của form chính


    DSPhongDAo dao = new DSPhongDAo();

    public JpanelChiTietPhong(String maphong,Main main) {
        this.mainForm = main;
        initComponents();
        String maphong1 = maphong;
        initData(maphong1);

    }

    public void initData(String maphong) {
        try {
            DSPhong danhsach = dao.findById(maphong);
            lblMaPhong.setText(danhsach.getMaPhong());
            if (danhsach.getTrangThai().equals("Đang sử dụng")) {
                JpaneView.setBackground(Color.red);
                BtnXacNhan.setText("Thanh toán");
                BtnXacNhan.setBackground(Color.BLUE);
                lblKieuPhong.setText(danhsach.getTenPhong());
                lblTrangThai.setText(danhsach.getTrangThai());
                if (danhsach.getMaLoaiPhong().equals("TH")) {
                    lblLoaiPhong.setText("Thường");
                } else {
                    lblLoaiPhong.setText("VIP");

                }

            } else {
                JpaneView.setBackground(Color.GREEN);
                BtnXacNhan.setText("Đặt phòng");
                lblKieuPhong.setText(danhsach.getTenPhong());
                lblTrangThai.setText(danhsach.getTrangThai());
                if (danhsach.getMaLoaiPhong().equals("TH")) {
                    lblLoaiPhong.setText("Thường");
                } else {
                    lblLoaiPhong.setText("VIP");

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpaneView = new javax.swing.JPanel();
        lblMaPhong = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        BtnXacNhan = new javax.swing.JButton();
        lblLoaiPhong = new javax.swing.JLabel();
        lblKieuPhong = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());

        JpaneView.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMaPhong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaPhong.setForeground(new java.awt.Color(0, 0, 0));
        lblMaPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaPhong.setText("Mã Phòng:");

        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl1.setText("Kiểu phòng:");

        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl2.setText("Loại phòng:");

        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl3.setText("Trạng thái: ");

        BtnXacNhan.setText("Đặt phòng");
        BtnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnXacNhanActionPerformed(evt);
            }
        });

        lblLoaiPhong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblLoaiPhong.setText("Loại phòng:");

        lblKieuPhong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblKieuPhong.setText("Kiểu phòng:");

        lblTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTrangThai.setText("Trạng thái: ");

        javax.swing.GroupLayout JpaneViewLayout = new javax.swing.GroupLayout(JpaneView);
        JpaneView.setLayout(JpaneViewLayout);
        JpaneViewLayout.setHorizontalGroup(
            JpaneViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpaneViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpaneViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMaPhong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(JpaneViewLayout.createSequentialGroup()
                        .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JpaneViewLayout.createSequentialGroup()
                        .addComponent(lbl1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblKieuPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                    .addGroup(JpaneViewLayout.createSequentialGroup()
                        .addComponent(lbl2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JpaneViewLayout.setVerticalGroup(
            JpaneViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpaneViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMaPhong)
                .addGap(18, 18, 18)
                .addGroup(JpaneViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1)
                    .addComponent(lblKieuPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JpaneViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl2)
                    .addComponent(lblLoaiPhong))
                .addGap(12, 12, 12)
                .addGroup(JpaneViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl3)
                    .addComponent(lblTrangThai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(BtnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpaneView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpaneView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnXacNhanActionPerformed
        // TODO add your handling code here:
        String value = BtnXacNhan.getText();
        String phong = lblMaPhong.getText();
        String loaiphong = lblLoaiPhong.getText();
        String maphong = lblMaPhong.getText();
        FormPhieuDat phieudat = new FormPhieuDat(phong, loaiphong,mainForm);
        if (value.equals("Đặt phòng")) {
            phieudat.setVisible(true);

        } else {

            FormThanhToan hoadon = new FormThanhToan(maphong,mainForm);
            hoadon.setVisible(true);
        }
    }//GEN-LAST:event_BtnXacNhanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnXacNhan;
    private javax.swing.JPanel JpaneView;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lblKieuPhong;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaPhong;
    private javax.swing.JLabel lblTrangThai;
    // End of variables declaration//GEN-END:variables
}
