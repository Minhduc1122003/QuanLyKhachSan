/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Hotel.ui;

import Hotel.dao.DatPhongDAO;
import Hotel.entity.DatPhong;
import Hotel.entity.KhachHang;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Admin
 */
public class JpanelDatPhong extends javax.swing.JPanel {

    DatPhongDAO dao = new DatPhongDAO();
    DefaultTableModel tblModel;
    private Main mainForm; // Tham chiếu của form chính

    public JpanelDatPhong(Main main) {
        this.mainForm = main;
        mainForm.Title("Quản lí khách sạn - Đặt phòng");
        initComponents();
        initPhong();
        initTable();
        initData();
        txttimKiem1.getDocument().addDocumentListener(new DocumentListener() {
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

    public void find() {
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        try {
            String keyword = txttimKiem1.getText();
            List<DatPhong> list = dao.selectByKeyword(keyword);
            for (DatPhong dp : list) {
                String kieuthue = "";
                if (dp.getMaKieuThue().equals("K001")) {
                    kieuthue = "Ngày";
                } else {
                    kieuthue = "Giờ";

                }
                Object[] row = {
                    dp.getMaDP(),
                    dp.getMaKH(),
                    dp.getMaPhong(),
                    dp.getNgayDen(),
                    dp.getNgayDi(),
                    kieuthue,
                    dp.getMaNV()
                };

                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void initData() {
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        try {
            List<DatPhong> datphong = dao.select();
            for (DatPhong dp : datphong) {
                String kieuthue = "";
                if (dp.getMaKieuThue().equals("K001")) {
                    kieuthue = "Ngày";
                } else {
                    kieuthue = "Giờ";

                }
                Object[] row = {
                    dp.getMaDP(),
                    dp.getMaKH(),
                    dp.getMaPhong(),
                    dp.getNgayDen(),
                    dp.getNgayDi(),
                    kieuthue,
                    dp.getMaNV()
                };

                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void search() {
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        String keyword = txttimKiem1.getText();
        List<DatPhong> list = dao.select();

        for (DatPhong dp : list) {
            // contains: kiểm tra xem từ khóa có trùng khớp với list hay ko
            if (String.valueOf(dp.getMaDP()).contains(keyword)
                    || dp.getMaNV().contains(keyword)
                    || dp.getMaKH().contains(keyword)
                    || dp.getMaKH().contains(keyword)) {
                String kieuthue = "";
                if (dp.getMaKieuThue().equals("K001")) {
                    kieuthue = "Ngày";
                } else {
                    kieuthue = "Giờ";

                }
                Object[] row = {
                    dp.getMaDP(),
                    dp.getMaKH(),
                    dp.getMaPhong(),
                    dp.getNgayDen(),
                    dp.getNgayDi(),
                    kieuthue,
                    dp.getMaNV()
                };

                model.addRow(row);
            }

        }
    }

    public void initTable() {
        tblModel = (DefaultTableModel) tblDanhSach.getModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã DP", "Mã KH", "Mã Phòng", "Ngày Đến", "Ngày đi", "Kiểu Thuê", "Mã NV"});
        TableColumnModel columnModel = tblDanhSach.getColumnModel();

    }

    public void initPhong() {
        showPanel(new JpanelChiTietPhong("PT101",mainForm), JpanelPhong1);
        showPanel(new JpanelChiTietPhong("PT102",mainForm), JpanelPhong2);
        showPanel(new JpanelChiTietPhong("PT103",mainForm), JpanelPhong3);
        showPanel(new JpanelChiTietPhong("PT104",mainForm), JpanelPhong4);
        showPanel(new JpanelChiTietPhong("PT205",mainForm), JpanelPhong5);
        showPanel(new JpanelChiTietPhong("PT206",mainForm), JpanelPhong6);
        showPanel(new JpanelChiTietPhong("PT207",mainForm), JpanelPhong7);
        showPanel(new JpanelChiTietPhong("PT208",mainForm), JpanelPhong8);
        showPanel(new JpanelChiTietPhong("PT309",mainForm), JpanelPhong9);
        showPanel(new JpanelChiTietPhong("PT310",mainForm), JpanelPhong10);
        showPanel(new JpanelChiTietPhong("PT311",mainForm), JpanelPhong11);
        showPanel(new JpanelChiTietPhong("PT312",mainForm), JpanelPhong12);
        showPanel(new JpanelChiTietPhong("PT413",mainForm), JpanelPhong13);
        showPanel(new JpanelChiTietPhong("PT414",mainForm), JpanelPhong14);
        showPanel(new JpanelChiTietPhong("PT415",mainForm), JpanelPhong15);
        showPanel(new JpanelChiTietPhong("PT416",mainForm), JpanelPhong16);

    }

    public void showPanel(JPanel panel, JPanel jpane2) {
        jpane2.removeAll();
        jpane2.setLayout(new BoxLayout(jpane2, BoxLayout.Y_AXIS)); // Đặt layout thành BoxLayout theo chiều dọc
        jpane2.add(panel);
        jpane2.validate();
        jpane2.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        JpanelPhong1 = new javax.swing.JPanel();
        JpanelPhong3 = new javax.swing.JPanel();
        JpanelPhong2 = new javax.swing.JPanel();
        JpanelPhong4 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        JpanelPhong5 = new javax.swing.JPanel();
        JpanelPhong7 = new javax.swing.JPanel();
        JpanelPhong6 = new javax.swing.JPanel();
        JpanelPhong8 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        JpanelPhong9 = new javax.swing.JPanel();
        JpanelPhong11 = new javax.swing.JPanel();
        JpanelPhong10 = new javax.swing.JPanel();
        JpanelPhong12 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        JpanelPhong13 = new javax.swing.JPanel();
        JpanelPhong15 = new javax.swing.JPanel();
        JpanelPhong14 = new javax.swing.JPanel();
        JpanelPhong16 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        txttimKiem1 = new javax.swing.JTextField();
        btnTimKiem1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Lầu 1");

        JpanelPhong1.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong1.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong1Layout = new javax.swing.GroupLayout(JpanelPhong1);
        JpanelPhong1.setLayout(JpanelPhong1Layout);
        JpanelPhong1Layout.setHorizontalGroup(
            JpanelPhong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong1Layout.setVerticalGroup(
            JpanelPhong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        JpanelPhong3.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong3.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong3Layout = new javax.swing.GroupLayout(JpanelPhong3);
        JpanelPhong3.setLayout(JpanelPhong3Layout);
        JpanelPhong3Layout.setHorizontalGroup(
            JpanelPhong3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong3Layout.setVerticalGroup(
            JpanelPhong3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong2.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong2.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong2Layout = new javax.swing.GroupLayout(JpanelPhong2);
        JpanelPhong2.setLayout(JpanelPhong2Layout);
        JpanelPhong2Layout.setHorizontalGroup(
            JpanelPhong2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong2Layout.setVerticalGroup(
            JpanelPhong2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong4.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong4.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong4Layout = new javax.swing.GroupLayout(JpanelPhong4);
        JpanelPhong4.setLayout(JpanelPhong4Layout);
        JpanelPhong4Layout.setHorizontalGroup(
            JpanelPhong4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong4Layout.setVerticalGroup(
            JpanelPhong4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JpanelPhong1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(JpanelPhong2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(JpanelPhong3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(JpanelPhong4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JpanelPhong4, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(JpanelPhong2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(JpanelPhong3, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JpanelPhong1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Lầu 2");

        JpanelPhong5.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong5.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong5Layout = new javax.swing.GroupLayout(JpanelPhong5);
        JpanelPhong5.setLayout(JpanelPhong5Layout);
        JpanelPhong5Layout.setHorizontalGroup(
            JpanelPhong5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong5Layout.setVerticalGroup(
            JpanelPhong5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong7.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong7.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong7Layout = new javax.swing.GroupLayout(JpanelPhong7);
        JpanelPhong7.setLayout(JpanelPhong7Layout);
        JpanelPhong7Layout.setHorizontalGroup(
            JpanelPhong7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong7Layout.setVerticalGroup(
            JpanelPhong7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong6.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong6.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong6Layout = new javax.swing.GroupLayout(JpanelPhong6);
        JpanelPhong6.setLayout(JpanelPhong6Layout);
        JpanelPhong6Layout.setHorizontalGroup(
            JpanelPhong6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong6Layout.setVerticalGroup(
            JpanelPhong6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong8.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong8.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong8Layout = new javax.swing.GroupLayout(JpanelPhong8);
        JpanelPhong8.setLayout(JpanelPhong8Layout);
        JpanelPhong8Layout.setHorizontalGroup(
            JpanelPhong8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong8Layout.setVerticalGroup(
            JpanelPhong8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(JpanelPhong5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(JpanelPhong6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(JpanelPhong7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(JpanelPhong8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JpanelPhong8, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(JpanelPhong6, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(JpanelPhong7, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(JpanelPhong5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Lầu 3");

        JpanelPhong9.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong9.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong9Layout = new javax.swing.GroupLayout(JpanelPhong9);
        JpanelPhong9.setLayout(JpanelPhong9Layout);
        JpanelPhong9Layout.setHorizontalGroup(
            JpanelPhong9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong9Layout.setVerticalGroup(
            JpanelPhong9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong11.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong11.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong11Layout = new javax.swing.GroupLayout(JpanelPhong11);
        JpanelPhong11.setLayout(JpanelPhong11Layout);
        JpanelPhong11Layout.setHorizontalGroup(
            JpanelPhong11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong11Layout.setVerticalGroup(
            JpanelPhong11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong10.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong10.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong10Layout = new javax.swing.GroupLayout(JpanelPhong10);
        JpanelPhong10.setLayout(JpanelPhong10Layout);
        JpanelPhong10Layout.setHorizontalGroup(
            JpanelPhong10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );
        JpanelPhong10Layout.setVerticalGroup(
            JpanelPhong10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong12.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong12.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong12Layout = new javax.swing.GroupLayout(JpanelPhong12);
        JpanelPhong12.setLayout(JpanelPhong12Layout);
        JpanelPhong12Layout.setHorizontalGroup(
            JpanelPhong12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong12Layout.setVerticalGroup(
            JpanelPhong12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(JpanelPhong9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JpanelPhong10, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(JpanelPhong11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(JpanelPhong12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JpanelPhong12, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(JpanelPhong10, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(JpanelPhong11, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(JpanelPhong9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Lầu 4");

        JpanelPhong13.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong13.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong13Layout = new javax.swing.GroupLayout(JpanelPhong13);
        JpanelPhong13.setLayout(JpanelPhong13Layout);
        JpanelPhong13Layout.setHorizontalGroup(
            JpanelPhong13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong13Layout.setVerticalGroup(
            JpanelPhong13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong15.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong15.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong15Layout = new javax.swing.GroupLayout(JpanelPhong15);
        JpanelPhong15.setLayout(JpanelPhong15Layout);
        JpanelPhong15Layout.setHorizontalGroup(
            JpanelPhong15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong15Layout.setVerticalGroup(
            JpanelPhong15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong14.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong14.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong14Layout = new javax.swing.GroupLayout(JpanelPhong14);
        JpanelPhong14.setLayout(JpanelPhong14Layout);
        JpanelPhong14Layout.setHorizontalGroup(
            JpanelPhong14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong14Layout.setVerticalGroup(
            JpanelPhong14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpanelPhong16.setBackground(new java.awt.Color(153, 255, 153));
        JpanelPhong16.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout JpanelPhong16Layout = new javax.swing.GroupLayout(JpanelPhong16);
        JpanelPhong16.setLayout(JpanelPhong16Layout);
        JpanelPhong16Layout.setHorizontalGroup(
            JpanelPhong16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        JpanelPhong16Layout.setVerticalGroup(
            JpanelPhong16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(JpanelPhong13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(JpanelPhong14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(JpanelPhong15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(JpanelPhong16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JpanelPhong16, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(JpanelPhong14, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(JpanelPhong15, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(JpanelPhong13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel14);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("QUẢN LÍ ĐẶT PHÒNG");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        tabs.addTab("Phòng", jPanel13);

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã DP", "Ngày Đến", "Ngày Đi", "Mã KH", "Mã phòng", "Số lượng", "Mã DV"
            }
        ));
        jScrollPane2.setViewportView(tblDanhSach);

        btnTimKiem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Search.png"))); // NOI18N
        btnTimKiem1.setText("Tìm kiếm");
        btnTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("DANH SÁCH CÁC PHÒNG");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(txttimKiem1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiem1)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem1))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Danh sách", jPanel6);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("THỐNG KÊ DOANH THU");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(184, 184, 184)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(185, 185, 185)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(307, 307, 307)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(729, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem1ActionPerformed
        // TODO add your handling code here:
        find();
    }//GEN-LAST:event_btnTimKiem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelPhong1;
    private javax.swing.JPanel JpanelPhong10;
    private javax.swing.JPanel JpanelPhong11;
    private javax.swing.JPanel JpanelPhong12;
    private javax.swing.JPanel JpanelPhong13;
    private javax.swing.JPanel JpanelPhong14;
    private javax.swing.JPanel JpanelPhong15;
    private javax.swing.JPanel JpanelPhong16;
    private javax.swing.JPanel JpanelPhong2;
    private javax.swing.JPanel JpanelPhong3;
    private javax.swing.JPanel JpanelPhong4;
    private javax.swing.JPanel JpanelPhong5;
    private javax.swing.JPanel JpanelPhong6;
    private javax.swing.JPanel JpanelPhong7;
    private javax.swing.JPanel JpanelPhong8;
    private javax.swing.JPanel JpanelPhong9;
    private javax.swing.JButton btnTimKiem1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txttimKiem1;
    // End of variables declaration//GEN-END:variables
}
