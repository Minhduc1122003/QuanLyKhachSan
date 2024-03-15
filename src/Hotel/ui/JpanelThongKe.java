/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Hotel.ui;

import Hotel.dao.HoaDonThanhToanDAO;
import Hotel.dao.ThanhToanDAO;
import Hotel.dao.ThongKeDAO;
import Hotel.entity.HoaDonThanhToan;
import Hotel.utils.ExcelUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Admin
 */
public class JpanelThongKe extends javax.swing.JPanel {

    ThongKeDAO dao = new ThongKeDAO();
    HoaDonThanhToanDAO hoadon = new HoaDonThanhToanDAO();
    DecimalFormat decimalFormat = new DecimalFormat("#,##0");
    private Main mainForm;

    /**
     * Creates new form JpanelThongKe
     */
    public JpanelThongKe(Main main) {
        this.mainForm = main;

        mainForm.Title("Quản lí khách sạn - Quản lí hóa đơn");

        initComponents();
        init();
    }

    void init() {
        fillComboBoxNam();
        fillTableDoanhThuPhong();
        fillTableDoanhThuDV();
        formatTien();//   setSelectedIndex(0);
        //        tblPhong.getColumnModel().getColumn(0).setMaxWidth(100);
        //        tblPhong.getColumnModel().getColumn(1).setMaxWidth(70);
        //        tblPhong.getColumnModel().getColumn(2).setMaxWidth(120);
        //        tblPhong.getColumnModel().getColumn(3).setMaxWidth(120);
        //
        //        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        //        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        //        tblPhong.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        //
        //        tblDichVu.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        //        tblDichVu.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        //        tblDichVu.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        //        tblDichVu.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);

    }

    void fillComboBoxNam() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNgayNam.getModel();
        model.removeAllElements();
        List<String> list = hoadon.selectMonthsAndYears();
        for (String year : list) {
            model.addElement(year);
        }
    }

    void formatTien() {
//        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
//        int rowCount = tblPhong.getRowCount();
//        for (int i = 0; i < rowCount; i++) {
//            Object value = tblPhong.getValueAt(i, 2);
//            Object value2 = tblPhong.getValueAt(i, 3);
//
//            if (value instanceof Number && value2 instanceof Number) {
//                // Kiểm tra xem giá trị có phải là số không
//                float phuthu = ((Number) value).floatValue();
//                float thanhtien = ((Number) value2).floatValue();
//                String phuthuFormat = decimalFormat.format(phuthu);
//                String thanhtienFormat = decimalFormat.format(thanhtien);
//
//                // Sử dụng giá trị được định dạng ở đây
//                tblPhong.setValueAt(phuthuFormat, i, 2); // Cột 3 chứa phụ thu
//                tblPhong.setValueAt(thanhtienFormat, i, 3); // Cột 3 chứa phụ thu
//
//            } else {
//
//                String valueAsString = String.valueOf(value);
//            }
//        }
//        int rowCount2 = tblDichVu.getRowCount();
//        for (int i = 0; i < rowCount2; i++) {
//            Object value3 = tblDichVu.getValueAt(i, 4);
//            Object value4 = tblDichVu.getValueAt(i, 5);
//
//            if (value3 instanceof Number && value4 instanceof Number) {
//                // Kiểm tra xem giá trị có phải là số không
//                float dongia = ((Number) value3).floatValue();
//                float thanhtien2 = ((Number) value4).floatValue();
//                String dongiaFormat = decimalFormat.format(dongia);
//                String thanhtien2Format = decimalFormat.format(thanhtien2);
//
//                // Sử dụng giá trị được định dạng ở đây
//                tblDichVu.setValueAt(dongiaFormat, i, 4); // Cột 3 chứa phụ thu
//                tblDichVu.setValueAt(thanhtien2Format, i, 5); // Cột 3 chứa phụ thu
//
//            } else {
//
//                String valueAsString = String.valueOf(value3);
//            }
//        }

    }

    void fillTableDoanhThuPhong() {
        DefaultTableModel model = (DefaultTableModel) tblPhong.getModel();
        model.setRowCount(0);

        String selectedDate = (String) cboNgayNam.getSelectedItem();
        if (selectedDate != null) {
            String[] parts = selectedDate.split("/");
            if (parts.length == 2) {
                int thang = Integer.parseInt(parts[0]);
                int nam = Integer.parseInt(parts[1]);
                Date date = getDateFromParts(thang, nam);
                List<Object[]> list = dao.getDoanhThuPhong(thang, nam);
                for (Object[] row : list) {

                    model.addRow(row);
                }

            } else {
                // Xử lý khi không đúng định dạng tháng-năm
            }
        } else {
            // Xử lý khi không có ngày được chọn
        }
        formatTien();
    }

    private Date getDateFromParts(int thang, int nam) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, nam);
        calendar.set(Calendar.MONTH, thang - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    void fillTableDoanhThuDV() {
        DefaultTableModel model = (DefaultTableModel) tblDichVu.getModel();
        model.setRowCount(0);

        String selectedDate = (String) cboNgayNam.getSelectedItem();
        if (selectedDate != null) {
            String[] parts = selectedDate.split("/");
            if (parts.length == 2) {
                int thang = Integer.parseInt(parts[0]);
                int nam = Integer.parseInt(parts[1]);

                Date date = getDateFromParts(thang, nam);
                List<Object[]> list = dao.getDoanhThuDV(thang, nam);
                for (Object[] row : list) {
                    model.addRow(row);
                }
            } else {
                // Xử lý khi không đúng định dạng tháng-năm
            }
        } else {
            // Xử lý khi không có ngày được chọn
        }
        formatTien();
    }

    private void chooseDirectoryToSave(Workbook workbook) {
        JFileChooser choose = new JFileChooser();
        int x = choose.showSaveDialog(null);
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                String file = choose.getSelectedFile().getAbsolutePath().toString();
                FileOutputStream outFile = new FileOutputStream(file);
                BufferedOutputStream bufferedOutFile = new BufferedOutputStream(outFile);

                // Write the workbook data to the output stream
                workbook.write(bufferedOutFile);

                // Close the output stream
                bufferedOutFile.close();
                outFile.close();

                JOptionPane.showMessageDialog(this, "Xuất tệp Excel thành công!");
            } catch (IOException ex) {
                Logger.getLogger(JpanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    // Close the workbook
                    workbook.close();
                } catch (IOException ex) {
                    Logger.getLogger(JpanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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

        cboNgayNam = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhong = new javax.swing.JTable();
        btnInphong = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        btnInDV = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        cboNgayNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNgayNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNgayNamActionPerformed(evt);
            }
        });

        jLabel1.setText("Tháng/ Năm");

        tblPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã phòng", "Phụ thu", "Thanh tiền"
            }
        ));
        jScrollPane1.setViewportView(tblPhong);

        btnInphong.setText("Xuất file");
        btnInphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInphongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(btnInphong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInphong))
        );

        tabs.addTab("Doanh thu phòng", jPanel1);

        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tblDichVu);

        btnInDV.setText("Xuất file");
        btnInDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(btnInDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInDV))
        );

        tabs.addTab("Doanh thu dịch vụ", jPanel2);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THỐNG KÊ DOANH THU");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabs)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cboNgayNam, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboNgayNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabs)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboNgayNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNgayNamActionPerformed
        // TODO add your handling code here:
        fillTableDoanhThuPhong();
        fillTableDoanhThuDV();
    }//GEN-LAST:event_cboNgayNamActionPerformed

    private void btnInphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInphongActionPerformed
        try {

            Workbook workBook = ExcelUtil.printDoanhThuphongToExcel(tblPhong, cboNgayNam, dao);
            this.chooseDirectoryToSave(workBook);
            Logger.getLogger(JpanelThongKe.class.getName()).log(Level.INFO,
                    "Export Doanh thu phong to Excel file successful!");
        } catch (IOException ex) {
            Logger.getLogger(JpanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInphongActionPerformed

    private void btnInDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInDVActionPerformed
        // TODO add your handling code here:
        try {

            Workbook workBook = ExcelUtil.printDoanhThuDVToExcel(tblDichVu, cboNgayNam, dao);
            this.chooseDirectoryToSave(workBook);
            Logger.getLogger(JpanelThongKe.class.getName()).log(Level.INFO,
                    "Export Doanh thu dịch vụ to Excel file successful!");
        } catch (IOException ex) {
            Logger.getLogger(JpanelThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInDVActionPerformed
//    private HoaDonThanhToan getHoaDonFromString(String dateString) {
//        // Implement your logic to convert dateString to HoaDon object
//        // This might involve fetching from a list or database lookup based on the date string
//        // Return the HoaDon object or null if not found
//        // Example:
//        // return dao.getHoaDonByDateString(dateString);
//        return null; // Replace this with your logic
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInDV;
    private javax.swing.JButton btnInphong;
    private javax.swing.JComboBox<String> cboNgayNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblDichVu;
    private javax.swing.JTable tblPhong;
    // End of variables declaration//GEN-END:variables
}
