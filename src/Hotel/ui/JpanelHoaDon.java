/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Hotel.ui;

import Hotel.dao.HoaDonThanhToanDAO;
import Hotel.entity.HoaDonThanhToan;
import Hotel.entity.KhachHang;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Admin
 */
public final class JpanelHoaDon extends javax.swing.JPanel {

    DefaultTableModel tblModel;

    HoaDonThanhToanDAO daoHD = new HoaDonThanhToanDAO();

    private final List<HoaDonThanhToan> listHD = daoHD.select();
    DecimalFormat decimalFormat = new DecimalFormat("#,##0");
    private final int index = -1;
    private final Main mainform;

    /**
     * Creates new form JpanelHoaDon
     * @param main
     */
    public JpanelHoaDon(Main main) {
        this.mainform = main;
                mainform.Title("Quản lí khách sạn - Quản lí hóa đơn");

        initComponents();
        initTable();
        initHoaDon();
        txtTim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
        });
    }

    private void search() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        String keyword = txtTim.getText();
        List<HoaDonThanhToan> list = daoHD.select();

        for (HoaDonThanhToan hd : list) {
            String tongTienFormatted = decimalFormat.format(hd.getThanhTien());

            // contains: kiểm tra xem từ khóa có trùng khớp với list hay ko
            if (hd.getMaHD().contains(keyword)
                    || hd.getMaNV().contains(keyword)
                    || hd.getMaKH().contains(keyword)
                    || hd.getMaHD().contains(keyword)) {
                Object[] row = {
                    hd.getMaHD(),
                    hd.getMaDP(),
                    hd.getMaNV(),
                    hd.getMaKH(),
                    hd.getNgayThue(),
                    hd.getNgayTraD(),
                    tongTienFormatted,
                    hd.getNgayLap()};

                model.addRow(row);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnTimKiem = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Mã KH", "Mã Phòng", "Mã DV", "Tổng tiền"
            }
        ));
        tblHoaDon.setRowHeight(30);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Search.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTim)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Danh sách", jPanel3);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH SÁCH HÓA ĐƠN THANH TOÁN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int selectedRow = tblHoaDon.getSelectedRow();
            if (selectedRow != -1) {
                String maHD = (String) tblHoaDon.getValueAt(selectedRow, 0);
                System.out.println(maHD);
                FormHoaDonChiTiet hdct = new FormHoaDonChiTiet(maHD, mainform);
                hdct.setVisible(true);
            }
        }


    }//GEN-LAST:event_tblHoaDonMousePressed
    public void initHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        for (HoaDonThanhToan hd : listHD) {
            String tongTienFormatted = decimalFormat.format(hd.getThanhTien());

            // contains: kiểm tra xem từ khóa có trùng khớp với list hay ko
            Object[] row = {
                hd.getMaHD(),
                hd.getMaDP(),
                hd.getMaNV(),
                hd.getMaKH(),
                hd.getNgayThue(),
                hd.getNgayTraD(),
                tongTienFormatted,
                hd.getNgayLap()
            };
            model.addRow(row);
        }

    }

    public void initTable() {
        tblModel = (DefaultTableModel) tblHoaDon.getModel();
        tblModel.setColumnIdentifiers(new String[]{"MÃ HĐ", "Mã ĐP", "Mã NV", "Mã KH", "Ngày BĐ", "Ngày Trả", "Thành Tiền", "Ngày Lập"});
        tblHoaDon.getColumnModel();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
