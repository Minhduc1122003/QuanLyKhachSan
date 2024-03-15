/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Hotel.ui;

import Hotel.dao.DSBanBeDAO;
import Hotel.entity.DSBanBe;
import Hotel.entity.NhanVien;
import Hotel.utils.ShareHelper;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.HierarchyEvent;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class JpanelTrangChu extends javax.swing.JPanel {

    /**
     * Creates new form JpanelTrangChu
     */
    private Main mainForm; // Tham chiếu của form chính
    DSBanBeDAO Dsdao = new DSBanBeDAO();
    private boolean check = false;

    public JpanelTrangChu(Main main) {
        this.mainForm = main;
        initComponents();
        mainForm.Title("Quản lí khách sạn - Trang chủ");
        jScrollPane1.setVerticalScrollBarPolicy(jScrollPane1.VERTICAL_SCROLLBAR_ALWAYS); // Hiển thị thanh cuộn dọc);
        jScrollPane1.setHorizontalScrollBarPolicy(jScrollPane1.HORIZONTAL_SCROLLBAR_NEVER); // Ẩn thanh cuộn ngang
        txtNhap.setLineWrap(true);
        txtNhap.setWrapStyleWord(true); // Đảm bảo từ không bị cắt ở giữa
        lblGui.setEnabled(true);
        initData();
    }

    public void checkText() {
        if (txtNhap.getText().equals("")) {
            lblGui.setEnabled(false);
        } else {
            lblGui.setEnabled(true);

        }

    }

    public void showThongtinTinNhan(String MaNVKB) {
        JpanelThongTinTinNhan tt = new JpanelThongTinTinNhan(MaNVKB);
        JpanelinfoTinNhan.removeAll();
        JpanelinfoTinNhan.setLayout(new BoxLayout(JpanelinfoTinNhan, BoxLayout.Y_AXIS));
        JpanelinfoTinNhan.add(tt);

    }

    public void initData() {
        String manv = ShareHelper.USER.getMaNV();
        List<DSBanBe> list = Dsdao.findById(manv);
        JpanelList1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Đặt vị trí anchor là phía trên bên trái
        for (DSBanBe ds : list) {
            System.out.println(ds.getMaNVKB());
            JpanelDanhSachBanBe ds1 = new JpanelDanhSachBanBe(this, ds.getMaNVKB());
            JpanelList1.add(ds1, gbc);
            gbc.gridy++; // Di chuyển sang hàng tiếp theo
            gbc.insets = new Insets(10, 0, 0, 0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpanelView1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblGui = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNhap = new javax.swing.JTextArea();
        JpanelinfoTinNhan = new javax.swing.JPanel();
        JpanelList = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JpanelList1 = new javax.swing.JPanel();

        JpanelView1.setBackground(new java.awt.Color(102, 0, 102));

        lblGui.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGui.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGui.setText("Gửi");
        lblGui.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtNhap.setColumns(20);
        txtNhap.setRows(5);
        txtNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNhapKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtNhap);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGui, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGui, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout JpanelinfoTinNhanLayout = new javax.swing.GroupLayout(JpanelinfoTinNhan);
        JpanelinfoTinNhan.setLayout(JpanelinfoTinNhanLayout);
        JpanelinfoTinNhanLayout.setHorizontalGroup(
            JpanelinfoTinNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JpanelinfoTinNhanLayout.setVerticalGroup(
            JpanelinfoTinNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JpanelView1Layout = new javax.swing.GroupLayout(JpanelView1);
        JpanelView1.setLayout(JpanelView1Layout);
        JpanelView1Layout.setHorizontalGroup(
            JpanelView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JpanelinfoTinNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JpanelView1Layout.setVerticalGroup(
            JpanelView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanelView1Layout.createSequentialGroup()
                .addComponent(JpanelinfoTinNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 387, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JpanelList.setBackground(new java.awt.Color(205, 205, 255));
        JpanelList.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Danh sách bạn bè");

        JpanelList1.setBackground(new java.awt.Color(205, 205, 255));

        javax.swing.GroupLayout JpanelList1Layout = new javax.swing.GroupLayout(JpanelList1);
        JpanelList1.setLayout(JpanelList1Layout);
        JpanelList1Layout.setHorizontalGroup(
            JpanelList1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        JpanelList1Layout.setVerticalGroup(
            JpanelList1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(JpanelList1);

        javax.swing.GroupLayout JpanelListLayout = new javax.swing.GroupLayout(JpanelList);
        JpanelList.setLayout(JpanelListLayout);
        JpanelListLayout.setHorizontalGroup(
            JpanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelListLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        JpanelListLayout.setVerticalGroup(
            JpanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpanelList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JpanelView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JpanelList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JpanelView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNhapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhapKeyReleased
        // TODO add your handling code here:
        checkText();
    }//GEN-LAST:event_txtNhapKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelList;
    private javax.swing.JPanel JpanelList1;
    private javax.swing.JPanel JpanelView1;
    private javax.swing.JPanel JpanelinfoTinNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGui;
    private javax.swing.JTextArea txtNhap;
    // End of variables declaration//GEN-END:variables
}
