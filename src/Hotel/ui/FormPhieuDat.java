package Hotel.ui;

import Hotel.dao.DSPhongDAo;
import Hotel.dao.DatPhongDAO;
import Hotel.dao.DichVuDAO;
import Hotel.dao.DichVuDaThueDAO;
import Hotel.dao.KhachHangDAO;
import Hotel.dao.identityDAO;
import Hotel.entity.DSPhong;
import Hotel.entity.DatPhong;
import Hotel.entity.DichVu;
import Hotel.entity.DichVuDaThue;
import Hotel.entity.KhachHang;
import Hotel.entity.indentity;
import Hotel.utils.DialogHelper;
import Hotel.utils.ShareHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormPhieuDat extends javax.swing.JFrame {

    private Main mainForm; // Tham chiếu của form chính

    DefaultTableModel tblModel;

    DatPhongDAO dao = new DatPhongDAO();
    KhachHangDAO daoKH = new KhachHangDAO();
    DatPhongDAO daoDP = new DatPhongDAO();
    DichVuDAO daoDV = new DichVuDAO();
    DSPhongDAo daoDSPhong = new DSPhongDAo();
    DichVuDaThueDAO daoDVDT = new DichVuDaThueDAO();
    identityDAO daoIND = new identityDAO();
    private List<KhachHang> listKH = daoKH.select();
    private List<DatPhong> list = dao.select();
    private List<DichVu> listDV = daoDV.select();
    String user = ShareHelper.USER.getMaNV();
    private String MaPhong;
    private String LoaiPhong;

    public FormPhieuDat(String maphong, String loaiphong, Main main) {
        initComponents();
        setTitle("Đặt phòng");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.mainForm = main;
        this.MaPhong = maphong;
        this.LoaiPhong = loaiphong;
        initTable();
        initData();
        DefaultTableModel model = (DefaultTableModel) tblThueDV.getModel();
        model.setRowCount(0);
        cboKieuThue.setSelectedIndex(0);

    }

    public void insertDV() {
        DefaultTableModel modeltbl = (DefaultTableModel) tblThueDV.getModel();
        for (int i = 0; i < modeltbl.getRowCount(); i++) {
            DichVuDaThue model = getModelDichVu(i);
            try {
                daoDVDT.insert(model);
            } catch (Exception e) {
                System.out.println("dich vu loi: " + e);

            }
        }

    }

    DichVuDaThue getModelDichVu(int i) {
        DichVuDaThue model = new DichVuDaThue();
        model.setMaDP(Integer.parseInt(lblMaPhieu.getText()));
        DefaultTableModel modeltbl = (DefaultTableModel) tblThueDV.getModel();
        Object maDV = tblThueDV.getValueAt(i, 0); // Lấy giá trị từ cột 1 (index 0)
        Object tenDV = tblThueDV.getValueAt(i, 1);
        Object donGia = tblThueDV.getValueAt(i, 2);
        model.setMaDV(String.valueOf(maDV));
        model.setTenDV(String.valueOf(tenDV));
        model.setDonGia(Float.valueOf(String.valueOf(donGia)));
        return model;
    }

    public void insertDatPhong() {
        DatPhong model = getModelDatPhong();
        try {
            daoDP.insert(model);
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public void insertKH() {

        KhachHang model = getModelKH();
        try {
            daoKH.insert(model);
        } catch (Exception e) {
            System.out.println("Khach hang loi: " + e);
        }

    }

    DatPhong getModelDatPhong() {
        String kieuthue = "";
        DatPhong model = new DatPhong();
        model.setMaDP(Integer.parseInt(lblMaPhieu.getText()));

        SimpleDateFormat ngay = new SimpleDateFormat("yyyy/MM/dd");
        Date getDate = new Date();
        String ngayden = ngay.format(getDate);
        SimpleDateFormat dinhdang = new SimpleDateFormat("HH:mm:ss");
        String gio = dinhdang.format(getDate);
        model.setNgayDen(ngayden + " " + gio);

        Date ngaydi = txtNgayDi.getDate();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd"); // Định dạng bạn muốn chuyển đổi
        if (ngaydi != null) {
            String ngaydiFormatted = outputFormat.format(ngaydi); // Chuyển đổi Date thành chuỗi theo định dạng mới
            model.setNgayDi(ngaydiFormatted);
        } else {
            System.out.println("Ngày không được chọn.");
        }

        model.setMaNV(txtMaNV.getText());
        model.setMaKH(txtMaKH2.getText());
        model.setMaPhong(lblMaPhong.getText());
        if (cboKieuThue.getSelectedItem().equals("Ngày")) {
            kieuthue = "K001";
        } else if (cboKieuThue.getSelectedItem().equals("Giờ")) {
            kieuthue = "K002";
        }
        model.setMaKieuThue(kieuthue);
        return model;
    }

    public void updateDSPhong() {
        DSPhong model = getModelDSPhong();
        try {
            daoDSPhong.update(model);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    DSPhong getModelDSPhong() {
        DSPhong model = new DSPhong();
        model.setTrangThai("Đang sử dụng");
        model.setMaPhong(lblMaPhong.getText());
        return model;
    }

    KhachHang getModelKH() {
        boolean gioiTinh;
        if (rdoNam.isSelected()) {
            gioiTinh = false;
        } else {
            gioiTinh = true;

        }
        KhachHang model = new KhachHang();
        model.setMaKH(txtMaKH1.getText());
        model.setTenKH(txtHoTen.getText());
        model.setSoDT(txtSDT.getText());
        model.setCCCD(txtCCCD.getText());
        model.setGioiTinh(gioiTinh);
        model.setDiaChi(txtDiaChi.getText());
        return model;
    }

    public void initData() {
        lblMaPhong.setText(MaPhong);
        lblLoai.setText(LoaiPhong);
        txtMaNV.setText(user);
        txtNgayDen.setEnabled(false);
        if (list == null || list.isEmpty()) {
            indentity inden = daoIND.indentity();
            lblMaPhieu.setText(String.valueOf(inden.getIndentity()));

        } else {
            String listSize = String.valueOf(list.get(list.size() - 1).getMaDP() + 1);
            lblMaPhieu.setText(listSize);
        }

        int size = listKH.size() + 1;
        String maKhachHang = "KH";
        if (size < 100) {
            maKhachHang = "KH0";
        }
        txtMaKH1.setText(String.valueOf(maKhachHang + size));
        txtMaKH2.setText(txtMaKH1.getText());
        rdoNam.setSelected(true);
        cboKieuThue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboKieuThue.getSelectedItem().equals("Ngày")) {
                    updateTime();
                    txtNgayDi.setEnabled(true);

                } else if (cboKieuThue.getSelectedItem().equals("Giờ")) {
                    updateTime();
                    txtNgayDi.setEnabled(false);
                }
            }
        });
        cboTenDV.removeAllItems();
        for (DichVu dv : listDV) {
            cboTenDV.addItem(dv.getTenDV());
        }

    }

    public void initTable() {
        tblModel = (DefaultTableModel) tblThueDV.getModel();
        tblModel.setColumnIdentifiers(new String[]{"MÃ DV", "Tên DV", "Đơn giá", "Số luọng"});

    }

    public void checkDate() {
        String ngayden = txtNgayDen.getText();
        Date ngaydi = txtNgayDi.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date ngayden1 = dateFormat.parse(ngayden);

            if (ngayden1.compareTo(ngaydi) > 0) {
                JOptionPane.showMessageDialog(null, "Ngày đến không được lớn hơn ngày đi");
            } else if (ngayden1.compareTo(ngaydi) < 0) {
            } else {
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public void addDichVu(String tenDichVu) {
        DefaultTableModel model = (DefaultTableModel) tblThueDV.getModel();
        try {
            DichVu listdv1 = daoDV.findById(tenDichVu);
            Object[] row = {
                listdv1.getMaDV(),
                listdv1.getTenDV(),
                listdv1.getDonGia()};
            model.addRow(row);
        } catch (Exception e) {
        }

    }

    public void xoaDichVu() {
        DefaultTableModel model = (DefaultTableModel) tblThueDV.getModel();
        int selectedRow = tblThueDV.getSelectedRow();
        if (selectedRow != -1) {
            ((DefaultTableModel) tblThueDV.getModel()).removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Chọn dịch vụ để xóa!");
        }
    }

    public void tongTienDV() {
        int columnIndex = 2; // Cột thứ ba có index là 2 vì index bắt đầu từ 0
        int rowCount = tblThueDV.getRowCount(); // Số lượng hàng trong JTable
        float tongTienDV = 0;

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Object value = tblThueDV.getValueAt(rowIndex, columnIndex);
            if (value instanceof Float) {
                float cellValue = (Float) value;
                tongTienDV += cellValue;
            } else if (value instanceof String) {
                try {
                    float cellValue = Float.parseFloat((String) value);
                    tongTienDV += cellValue;
                } catch (NumberFormatException e) {
                    // Xử lý nếu giá trị không thể chuyển đổi thành số thực
                    e.printStackTrace();
                }
            }
        }

        lblTiendichvu.setText("" + tongTienDV);
    }

    // thiết lập cái ngày giờ
    private void updateTime() {
        // chuyển định dạng time
        SimpleDateFormat dinhdang = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat ngay = new SimpleDateFormat("dd-MM-yyyy");
        Date getDate = new Date();
        txtNgayDen.setText(ngay.format(getDate) + " " + dinhdang.format(getDate));
    }

    private boolean valid() {
        if (txtHoTen.getText().isEmpty()) {
            txtHoTen.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên khách hàng", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtSDT.getText().isEmpty()) {
            txtSDT.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String phoneNumber = txtSDT.getText();
        if (!validatePhoneNumber(phoneNumber)) {
            txtSDT.requestFocus();
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtCCCD.getText().isEmpty()) {
            txtCCCD.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số CCCD", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String idCardNumber = txtCCCD.getText();
        if (!validateIdCardNumber(idCardNumber)) {
            txtCCCD.requestFocus();
            JOptionPane.showMessageDialog(this, "Số căn cước không hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtDiaChi.getText().isEmpty()) {
            txtDiaChi.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (cboKieuThue.getSelectedIndex() == 0) {
            cboKieuThue.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn kiểu thuê", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtMaNV.getText().isEmpty()) {
            txtMaNV.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        // Biểu thức chính quy kiểm tra định dạng số điện thoại
        String regex = "^[0-9]{10}$";

        return Pattern.matches(regex, phoneNumber);
    }

    private boolean validateIdCardNumber(String idCardNumber) {
        // Biểu thức chính quy kiểm tra định dạng số căn cước
        String regex = "^[0-9]{12,15}$";

        return Pattern.matches(regex, idCardNumber);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaKH2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgayDen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblLoai = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboKieuThue = new javax.swing.JComboBox<>();
        txtMaNV = new javax.swing.JTextField();
        txtNgayDi = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtMaKH1 = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cboTenDV = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThueDV = new javax.swing.JTable();
        btnThemDV = new javax.swing.JButton();
        btnXoaDV = new javax.swing.JButton();
        lblTiendichvu = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblMaPhieu = new javax.swing.JLabel();
        lblMaPhong = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin đặt phòng")));

        jLabel3.setText("Mã khách hàng:");

        txtMaKH2.setEditable(false);

        jLabel4.setText("Ngày đến:");

        jLabel5.setText("Ngày đi dự kiến:");

        jLabel6.setText("Mã NV:");

        jLabel7.setText("Loại phòng");

        lblLoai.setText("jLabel13");

        jLabel8.setText("Kiểu thuê:");

        cboKieuThue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- chọn --", "Ngày", "Giờ" }));
        cboKieuThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKieuThueActionPerformed(evt);
            }
        });

        txtNgayDi.setDateFormatString("dd-MM-yyyy");
        txtNgayDi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayDiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaKH2)
                                    .addComponent(txtNgayDen, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(50, 50, 50)
                                .addComponent(cboKieuThue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(txtMaNV))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNgayDi, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblLoai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboKieuThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jLabel15.setText("Mã khách hàng:");

        jLabel16.setText("Họ tên khách hàng:");

        jLabel17.setText("SỐ CCCD:");

        jLabel18.setText("Số điện thoại:");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel19.setText("Giới tính:");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        txtMaKH1.setEditable(false);
        txtMaKH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKH1ActionPerformed(evt);
            }
        });

        jLabel20.setText("Địa Chỉ:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaKH1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNu))
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtMaKH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thuê dịch vụ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel21.setText("Tên dịch vụ");

        cboTenDV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblThueDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã DV", "Tên DV", "Số Lượng", "Đơn Giá"
            }
        ));
        jScrollPane3.setViewportView(tblThueDV);

        btnThemDV.setText("Thêm dịch vụ");
        btnThemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVActionPerformed(evt);
            }
        });

        btnXoaDV.setText("Xóa dịch vụ");
        btnXoaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDVActionPerformed(evt);
            }
        });

        lblTiendichvu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTiendichvu.setText("0");

        jLabel9.setText("Tổng tiền dịch vụ:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(cboTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemDV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaDV)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTiendichvu)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cboTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemDV)
                    .addComponent(btnXoaDV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTiendichvu)
                    .addComponent(jLabel9))
                .addGap(21, 21, 21))
        );

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Add.png"))); // NOI18N
        btnThem.setText("Đặt Phòng");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Delete.png"))); // NOI18N
        btnXoa.setText("Hủy");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã phòng:");

        jLabel1.setText("Mã phiếu:");

        lblMaPhieu.setText("jLabel11");

        lblMaPhong.setText("jLabel9");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lblMaPhong)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblMaPhieu)
                    .addComponent(jLabel2)
                    .addComponent(lblMaPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 257, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnXoa))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboKieuThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKieuThueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboKieuThueActionPerformed

    private void txtMaKH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKH1ActionPerformed

    private void txtNgayDiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayDiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayDiMouseClicked

    private void btnThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVActionPerformed
        // TODO add your handling code here:
        String tenDV = String.valueOf(cboTenDV.getSelectedItem());
        addDichVu(tenDV);
        tongTienDV();

    }//GEN-LAST:event_btnThemDVActionPerformed

    private void btnXoaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDVActionPerformed
        // TODO add your handling code here:
        xoaDichVu();
        tongTienDV();
    }//GEN-LAST:event_btnXoaDVActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        tblModel = (DefaultTableModel) tblThueDV.getModel();
        if (valid()) {
            if (tblModel.getRowCount() == 0) {
                this.btnThemDVActionPerformed(evt);
            }
            insertKH();
            insertDatPhong();
            updateDSPhong();
            insertDV();
            DialogHelper.alert(this, "Đặt phòng thành công! ");
            this.dispose();
            mainForm.showPanel(new JpanelDatPhong(mainForm));
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

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
            java.util.logging.Logger.getLogger(FormPhieuDat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPhieuDat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPhieuDat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPhieuDat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemDV;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaDV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboKieuThue;
    private javax.swing.JComboBox<String> cboTenDV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblLoai;
    private javax.swing.JLabel lblMaPhieu;
    private javax.swing.JLabel lblMaPhong;
    private javax.swing.JLabel lblTiendichvu;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblThueDV;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKH1;
    private javax.swing.JTextField txtMaKH2;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayDen;
    private com.toedter.calendar.JDateChooser txtNgayDi;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
