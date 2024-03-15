/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Hotel.ui;

import Hotel.dao.NhanVienDAO;
import Hotel.entity.NhanVien;
import Hotel.utils.DialogHelper;
import Hotel.utils.ShareHelper;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Admin
 */
public final class JpanelNhanVien extends javax.swing.JPanel {

    int index = -1; // vị trí của nhân viên đang hiển thị trên form
    private String matKhau = "";
    private boolean show = false;
    NhanVienDAO dao = new NhanVienDAO();
    DefaultTableModel tblModel;
    List<NhanVien> list = dao.select();
    private Main mainform;
    private String linkImg;

    public JpanelNhanVien(Main main) {

        initComponents();
        this.mainform = main;
        mainform.Title("Quản lí khách sạn - Quản lí nhân viên");

        initTable();
        initData();
        clear();

        if (ShareHelper.authenticatedNV()) {
            txtMaNV.setEnabled(false);
            txtHoTen.setEnabled(false);
            txtMatKhau.setEnabled(false);
            txtEmail.setEnabled(false);
            txtSDT.setEnabled(false);
            btnThem.setEnabled(false);
            btnMoi.setEnabled(false);
            btnCapNhat.setEnabled(false);
            btnXoa.setEnabled(false);
            jButton1.setEnabled(false);
            ViewNVTrue(ShareHelper.USER.getMaNV());
            lblshowPass.setEnabled(false);
            rdoNam.setEnabled(false);
            rdoNu.setEnabled(false);
            rdoQuanLi.setEnabled(false);
            rdoNhanVien.setEnabled(false);

        } else {
            ImageIcon icon = new ImageIcon("src/Hotel/icon/profile.png");
            lblimgEdit.setIcon(icon);
            lblimgNV.setIcon(icon);
            btnXoa.setEnabled(false);
            btnCapNhat.setEnabled(false);
            btnThem.setEnabled(true);
            ViewNVTrue(ShareHelper.USER.getMaNV());

        }

    }

    public void initTable() {
        tblModel = (DefaultTableModel) tblList.getModel();
        tblModel.setColumnIdentifiers(new String[]{"MÃ NV", "TenNV", "Số Điện Thoại", "Chức Vụ", "Mật Khẩu", "Email", "Giới Tính"});
        TableColumnModel columnModel = tblList.getColumnModel();
    }

    public void initData() {
        DefaultTableModel model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        try {
            for (NhanVien nv : list) {
                String matKhau = nv.getMatKhau();
                int matKhauLength = matKhau.length();
                StringBuilder matKhauMasked = new StringBuilder();
                for (int i = 0; i < matKhauLength; i++) {
                    matKhauMasked.append("*");
                }
                Object[] row = {
                    nv.getMaNV(),
                    nv.getTenNV(),
                    nv.getSoDT(),
                    nv.isChucVu() ? "Admin" : "Nhân viên",
                    matKhauMasked,
                    nv.getEmail(),
                    nv.isGioiTinh() ? "Nữ" : "Nam",
                    nv.getImg()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //tạo mới bảng nhập
    public void clear() {
        //set title mới cho Jpanel nhân viên
        lblMaNV.setText(txtMaNV.getText());

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Thêm mới nhân viên");
        jPanel8.setBorder(titledBorder);
        //get mã nhân viên mới trong databse
        if (list == null || list.isEmpty()) {
            txtMaNV.setText("" + 0);
        } else {
            String path = String.valueOf(list.get(list.size() - 1).getMaNV());
            String[] parts = path.split("NV");
            //đang sửa
            if (parts.length > 1) {
                int result = Integer.parseInt(parts[1]); // Lấy phần sau chuỗi "icon/"
                System.out.println(result);
                int size = result + 1;
                String maNV = "NV";
                if (size < 10) {
                    maNV = "NV0";
                }
                txtMaNV.setText(String.valueOf(maNV + size));

            } else {
                System.out.println("Không tìm thấy phần sau chuỗi");
            }
        }
        //set hình ảnh cho lbl
        ImageIcon icon = new ImageIcon("src/Hotel/icon/profile.png");

        Image originalImage1 = icon.getImage();
        // Giảm kích thước hình ảnh
        int newWidth = 120; // Kích thước mới
        int newHeight = 120;
        Image scaledImage1 = originalImage1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Tạo ImageIcon mới từ hình ảnh đã giảm kích thước
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);

        // Đặt biểu tượng mới vào JLabel
        lblMaNV.setText("");
        lblHoTen.setText("");
        lblEmail.setText("");
        lblMatKhau.setText("");
        lblSoDT.setText("");
        lblGioiTinh.setText("");
        lblChucVu.setText("");
        lblimgEdit.setIcon(scaledIcon1);
        lblimgNV.setIcon(scaledIcon1);

        txtHoTen.setText("");
        txtEmail.setText("");
        txtMatKhau.setText("");
        txtSDT.setText("");
        rdoNam.setSelected(true);
        rdoNhanVien.setSelected(true);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnCapNhat.setEnabled(false);
        matKhau = "";
        showPass();
    }

    public void showPass() {
        txtMatKhau.setText(matKhau);
        show = true;
        ImageIcon icon = new ImageIcon("src/Hotel/icon/visible.png");
        lblshowPass.setIcon(icon);

    }

    public void hiddentPass() {
        matKhau = txtMatKhau.getText();
        int matKhauLength = matKhau.length();
        StringBuilder matKhauMasked = new StringBuilder();
        for (int i = 0; i < matKhauLength; i++) {
            matKhauMasked.append("*");
        }
        ImageIcon icon = new ImageIcon("src/Hotel/icon/hidden.png");
        lblshowPass.setIcon(icon);
        txtMatKhau.setText(String.valueOf(matKhauMasked));

        show = false;

    }

    public void deleteNV() {
        String name = txtHoTen.getText();
        String maNV = txtMaNV.getText();
        if (DialogHelper.confirm(this, "Bạn có muốn xóa nhân viên " + name)) {
            try {
                dao.delete(maNV);
                DialogHelper.alert(this, "Đã xóa nhân viên " + maNV + ": " + name);
                mainform.showPanel(new JpanelNhanVien(mainform));

            } catch (Exception e) {
                DialogHelper.alert(this, "Lỗi truy xuất ");

            }

        }

    }

    public void insertNV() {
        NhanVien model = getModel();
        try {
            dao.insert(model);
            clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
            mainform.showPanel(new JpanelNhanVien(mainform));
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }

    }

    public void updateNV() {
        String name = txtHoTen.getText();
        String maNV = txtMaNV.getText();
        if (DialogHelper.confirm(this, "Bạn có muốn cập nhật thông tin cho nhân viên " + name + "?")) {
            NhanVien model = getModel();
            try {
                dao.update(model);
                clear();
                DialogHelper.alert(this, "Cập nhật thông tin thành công!");
                mainform.showPanel(new JpanelNhanVien(mainform));
            } catch (Exception e) {
                System.out.println(e);
                DialogHelper.alert(this, "Thêm mới thất bại!");
            }
        }

    }

    public void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        String defaultPath = "src/Hotel/icon"; // Đường dẫn mặc định
        fileChooser.setCurrentDirectory(new File(defaultPath));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            ImageIcon icon = new ImageIcon(filePath); // lấy link ảnh
            linkImg = filePath;
            System.out.println(linkImg);
            Image originalImage1 = icon.getImage();
            // Giảm kích thước hình ảnh
            int newWidth = 120; // Kích thước mới
            int newHeight = 120;
            Image scaledImage1 = originalImage1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ hình ảnh đã giảm kích thước
            ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);

            // Đặt biểu tượng mới vào JLabel
            lblimgEdit.setIcon(scaledIcon1);
            lblimgNV.setIcon(scaledIcon1);
        }
    }

    NhanVien getModel() {
        String text = txtMatKhau.getText();
        boolean hasAsterisk = text.contains("*");

        if (hasAsterisk) {
            showPass(); // Gọi hàm showPass() nếu có dấu sao
        }

        boolean chucVu;
        boolean gioiTinh;
        if (rdoNhanVien.isSelected()) {
            chucVu = false;
        } else {
            chucVu = true;
        }
        if (rdoNam.isSelected()) {
            gioiTinh = false;
        } else {
            gioiTinh = true;
        }

        NhanVien model = new NhanVien();
        model.setMaNV(txtMaNV.getText());
        model.setTenNV(txtHoTen.getText());
        model.setSoDT(txtSDT.getText());
        model.setChucVu(chucVu);
        model.setMatKhau(txtMatKhau.getText());
        model.setEmail(txtEmail.getText());
        model.setGioiTinh(gioiTinh);
        String path = linkImg;
        if (path != null) {
            String[] parts = path.split("src\\\\Hotel\\\\icon\\\\");
            String imgName = "";
            if (parts.length > 1) {
                String result = parts[1]; // Lấy phần sau chuỗi "icon/"
                imgName = result;
                System.out.println(imgName);
                model.setImg(imgName);
            } else {
                System.out.println("Không tìm thấy phần sau chuỗi 'icon/'");
            }
        } else {
            String manv = txtMaNV.getText();
            try {
                NhanVien modelNV = dao.findById(manv);
                model.setImg(modelNV.getImg());

            } catch (Exception e) {
            }

        }

        return model;

    }

    public void ViewNVTrue(String manv) {
        try {
            NhanVien model = dao.findById(manv);
            if (model != null) {
                matKhau = model.getMatKhau();

                int matKhauLength = model.getMatKhau().length();
                StringBuilder matKhauMasked = new StringBuilder();
                for (int i = 0; i < matKhauLength; i++) {
                    matKhauMasked.append("*");
                }
                // insert
                lblMaNV.setText("Mã nhân viên: " + model.getMaNV());
                lblHoTen.setText(model.getTenNV());
                lblEmail.setText(model.getEmail());
                lblMatKhau.setText(String.valueOf(matKhauMasked));
                lblSoDT.setText(model.getSoDT());
                lblGioiTinh.setText(String.valueOf(model.isGioiTinh() ? "Nữ" : "Nam"));
                lblChucVu.setText(String.valueOf(model.isChucVu() ? "Quản lí" : "Nhân Viên"));
                if (model.getImg().equals("")) {
                    //set hình ảnh cho lbl
                    ImageIcon icon = new ImageIcon("src/Hotel/icon/profile.png");

                    Image originalImage1 = icon.getImage();
                    // Giảm kích thước hình ảnh
                    int newWidth = 120; // Kích thước mới
                    int newHeight = 120;
                    Image scaledImage1 = originalImage1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon mới từ hình ảnh đã giảm kích thước
                    ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);

                    // Đặt biểu tượng mới vào JLabel
                    lblimgNV.setIcon(scaledIcon1);
                } else {
                    //set hình ảnh cho lbl
                    String linkImg2 = "src\\\\Hotel\\\\icon\\\\" + model.getImg();
                    ImageIcon icon = new ImageIcon(linkImg2); // lấy link ảnh
                    Image originalImage1 = icon.getImage();
                    // Giảm kích thước hình ảnh
                    int newWidth = 120; // Kích thước mới
                    int newHeight = 120;
                    Image scaledImage1 = originalImage1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon mới từ hình ảnh đã giảm kích thước
                    ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);

                    // Đặt biểu tượng mới vào JLabel
                    lblimgNV.setIcon(scaledIcon1);
                }
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }

    //settext lbl & settext txt
    public void ViewNV(int index) {
        showPass();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Cập nhật thông tin nhân viên");
        jPanel8.setBorder(titledBorder);
        DefaultTableModel tblModel = (DefaultTableModel) tblList.getModel();
        if (index == tblModel.getRowCount() || index < 0) {
            hiddentPass();
            return;
        } else {
            try {

                String manv = (String) tblList.getValueAt(index, 0);
                NhanVien model = dao.findById(manv);
                if (model != null) {
                    matKhau = model.getMatKhau();

                    int matKhauLength = model.getMatKhau().length();
                    StringBuilder matKhauMasked = new StringBuilder();
                    for (int i = 0; i < matKhauLength; i++) {
                        matKhauMasked.append("*");
                    }
                    // insert
                    lblMaNV.setText("Mã nhân viên: " + model.getMaNV());
                    lblHoTen.setText(model.getTenNV());
                    lblEmail.setText(model.getEmail());
                    lblMatKhau.setText(String.valueOf(matKhauMasked));
                    lblSoDT.setText(model.getSoDT());
                    lblGioiTinh.setText(String.valueOf(model.isGioiTinh() ? "Nữ" : "Nam"));
                    lblChucVu.setText(String.valueOf(model.isChucVu() ? "Quản lí" : "Nhân Viên"));
                    // settext txt
                    txtMaNV.setText(model.getMaNV());
                    txtHoTen.setText(model.getTenNV());
                    txtEmail.setText(model.getEmail());
                    txtMatKhau.setText(model.getMatKhau());
                    txtSDT.setText(model.getSoDT());
                    if (String.valueOf(model.isGioiTinh()).equals("false")) {
                        rdoNam.setSelected(true);
                    } else {
                        rdoNu.setSelected(true);

                    }
                    if (String.valueOf(model.isChucVu()).equals("true")) {
                        rdoQuanLi.setSelected(true);
                    } else {
                        rdoNhanVien.setSelected(true);

                    }
                    if (model.getImg().equals("")) {
                        //set hình ảnh cho lbl
                        ImageIcon icon = new ImageIcon("src/Hotel/icon/profile.png");

                        Image originalImage1 = icon.getImage();
                        // Giảm kích thước hình ảnh
                        int newWidth = 120; // Kích thước mới
                        int newHeight = 120;
                        Image scaledImage1 = originalImage1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                        // Tạo ImageIcon mới từ hình ảnh đã giảm kích thước
                        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);

                        // Đặt biểu tượng mới vào JLabel
                        lblimgEdit.setIcon(scaledIcon1);
                        lblimgNV.setIcon(scaledIcon1);
                    } else {
                        //set hình ảnh cho lbl
                        String linkImg2 = "src\\\\Hotel\\\\icon\\\\" + model.getImg();
                        ImageIcon icon = new ImageIcon(linkImg2); // lấy link ảnh
                        Image originalImage1 = icon.getImage();
                        // Giảm kích thước hình ảnh
                        int newWidth = 120; // Kích thước mới
                        int newHeight = 120;
                        Image scaledImage1 = originalImage1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                        // Tạo ImageIcon mới từ hình ảnh đã giảm kích thước
                        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);

                        // Đặt biểu tượng mới vào JLabel
                        lblimgEdit.setIcon(scaledIcon1);
                        lblimgNV.setIcon(scaledIcon1);
                    }

                    if (ShareHelper.authenticated()) {
                        btnXoa.setEnabled(true);
                        btnCapNhat.setEnabled(true);
                    }
                    btnThem.setEnabled(false);
                    hiddentPass();
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
                System.out.println(e);
            }
        }

    }

    private boolean valid() {
        if (txtMaNV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập mã nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtMaNV.requestFocus();
            return false;
        }
        if (txtHoTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập họ tên nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtHoTen.requestFocus();
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập email nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        if (txtSDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập số điện thoại nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtSDT.requestFocus();
            return false;
        }
        if (txtMatKhau.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập mật khẩu nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtMatKhau.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        lblSoDT1 = new javax.swing.JLabel();
        lblcv = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lblimgNV = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblSoDT = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        rdoQuanLi = new javax.swing.JRadioButton();
        jLabel30 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        rdoNhanVien = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel33 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnCapNhat = new javax.swing.JButton();
        txtMatKhau = new javax.swing.JTextField();
        btnMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lblshowPass = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblimgEdit = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÍ NHÂN VIÊN");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lbl1.setForeground(new java.awt.Color(0, 0, 0));
        lbl1.setText("Họ tên:");

        lblSoDT1.setForeground(new java.awt.Color(0, 0, 0));
        lblSoDT1.setText("Số điện thoại:");

        lblcv.setForeground(new java.awt.Color(0, 0, 0));
        lblcv.setText("Chức vụ:");

        lblMaNV.setForeground(new java.awt.Color(0, 0, 0));
        lblMaNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaNV.setText("Mã nhân viên");

        lbl2.setForeground(new java.awt.Color(0, 0, 0));
        lbl2.setText("Email:");

        lbl.setForeground(new java.awt.Color(0, 0, 0));
        lbl.setText("Giới tính:");

        lbl3.setForeground(new java.awt.Color(0, 0, 0));
        lbl3.setText("Mật khẩu:");

        lblimgNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimgNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/profile.png"))); // NOI18N

        lblHoTen.setForeground(new java.awt.Color(0, 0, 0));

        lblMatKhau.setForeground(new java.awt.Color(0, 0, 0));

        lblEmail.setForeground(new java.awt.Color(0, 0, 0));

        lblSoDT.setForeground(new java.awt.Color(0, 0, 0));

        lblGioiTinh.setForeground(new java.awt.Color(0, 0, 0));

        lblChucVu.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl3)
                    .addComponent(lbl2)
                    .addComponent(lbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcv, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblSoDT1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
            .addComponent(lblMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(lblimgNV, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimgNV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaNV)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1)
                    .addComponent(lblHoTen)
                    .addComponent(lblSoDT1)
                    .addComponent(lblSoDT))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl2)
                        .addComponent(lblEmail)
                        .addComponent(lblGioiTinh)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl3)
                    .addComponent(lblMatKhau)
                    .addComponent(lblcv)
                    .addComponent(lblChucVu))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sửa thông tin nhân viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Họ tên");

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Số điện thoại");

        txtSDT.setBackground(new java.awt.Color(255, 255, 255));
        txtSDT.setForeground(new java.awt.Color(0, 0, 0));
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Chức vụ");

        rdoQuanLi.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoQuanLi);
        rdoQuanLi.setForeground(new java.awt.Color(0, 0, 0));
        rdoQuanLi.setText("Quản lí");
        rdoQuanLi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoQuanLiMouseClicked(evt);
            }
        });
        rdoQuanLi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rdoQuanLiKeyReleased(evt);
            }
        });

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Mã nhân viên");

        txtMaNV.setEditable(false);
        txtMaNV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Email");

        rdoNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setForeground(new java.awt.Color(0, 0, 0));
        rdoNhanVien.setText("Nhân viên");
        rdoNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNhanVienMouseClicked(evt);
            }
        });
        rdoNhanVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rdoNhanVienKeyPressed(evt);
            }
        });

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Giới tính");

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoNam);
        rdoNam.setForeground(new java.awt.Color(0, 0, 0));
        rdoNam.setText("Nam");
        rdoNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNamMouseClicked(evt);
            }
        });

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoNu);
        rdoNu.setForeground(new java.awt.Color(0, 0, 0));
        rdoNu.setText("Nữ");
        rdoNu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNuMouseClicked(evt);
            }
        });
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Mật khẩu");

        txtHoTen.setBackground(new java.awt.Color(255, 255, 255));
        txtHoTen.setForeground(new java.awt.Color(0, 0, 0));
        txtHoTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoTenKeyReleased(evt);
            }
        });

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 0));
        btnCapNhat.setForeground(new java.awt.Color(0, 0, 0));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        txtMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        txtMatKhau.setForeground(new java.awt.Color(0, 0, 0));
        txtMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMatKhauMouseClicked(evt);
            }
        });
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyReleased(evt);
            }
        });

        btnMoi.setForeground(new java.awt.Color(0, 0, 0));
        btnMoi.setText("Mới");
        btnMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(51, 255, 51));
        btnThem.setForeground(new java.awt.Color(0, 0, 0));
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 0, 0));
        btnXoa.setForeground(new java.awt.Color(0, 0, 0));
        btnXoa.setText("Xóa");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lblshowPass.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblshowPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/hidden.png"))); // NOI18N
        lblshowPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblshowPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblshowPassMouseClicked(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblimgEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimgEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/eng.gif"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Chọn ảnh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimgEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimgEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel27)
                            .addComponent(jLabel31)
                            .addComponent(jLabel28)
                            .addComponent(jLabel32)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoQuanLi)
                                    .addComponent(rdoNam))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoNu)
                                    .addComponent(rdoNhanVien)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblshowPass, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnMoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhat)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblshowPass, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel33)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(rdoQuanLi)
                    .addComponent(rdoNhanVien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat)
                    .addComponent(btnMoi)
                    .addComponent(btnThem)
                    .addComponent(btnXoa))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblList.setForeground(new java.awt.Color(0, 0, 0));
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblList.setRowHeight(30);
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblListMousePressed(evt);
            }
        });
        tblList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tblListMousePressed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        updateNV();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        index = tblList.getSelectedRow();

        ViewNV(index);

    }//GEN-LAST:event_tblListMouseClicked

    private void tblListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblListKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            index = tblList.getSelectedRow() + 1;

            ViewNV(index);
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            index = tblList.getSelectedRow() - 1;
            ViewNV(index);
        }

    }//GEN-LAST:event_tblListKeyPressed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        boolean check = valid();
        if (check == true) {
            insertNV();

        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteNV();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void lblshowPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblshowPassMouseClicked
        // TODO add your handling code here:
        if (show == false && lblshowPass.isEnabled() == true) {
            showPass();

        } else {
            hiddentPass();

        }
    }//GEN-LAST:event_lblshowPassMouseClicked

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        chooseFile();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMatKhauMouseClicked
        // TODO add your handling code here:
        showPass();
    }//GEN-LAST:event_txtMatKhauMouseClicked

    private void txtHoTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoTenKeyReleased
        // TODO add your handling code here:
        lblHoTen.setText(txtHoTen.getText());
    }//GEN-LAST:event_txtHoTenKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // TODO add your handling code here:
        lblEmail.setText(txtEmail.getText());
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        // TODO add your handling code here:
        lblSoDT.setText(txtSDT.getText());
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtMatKhauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyReleased
        // TODO add your handling code here:
        lblMatKhau.setText(txtMatKhau.getText());
    }//GEN-LAST:event_txtMatKhauKeyReleased

    private void rdoQuanLiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdoQuanLiKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoQuanLiKeyReleased

    private void rdoNhanVienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdoNhanVienKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_rdoNhanVienKeyPressed

    private void rdoQuanLiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoQuanLiMouseClicked
        // TODO add your handling code here:
        lblChucVu.setText("Admin");

    }//GEN-LAST:event_rdoQuanLiMouseClicked

    private void rdoNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNamMouseClicked
        // TODO add your handling code here:
                lblGioiTinh.setText("Nam");

    }//GEN-LAST:event_rdoNamMouseClicked

    private void rdoNuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNuMouseClicked
        // TODO add your handling code here:
                lblGioiTinh.setText("Nữ");

    }//GEN-LAST:event_rdoNuMouseClicked

    private void rdoNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNhanVienMouseClicked
        // TODO add your handling code here:
                lblChucVu.setText("Nhân viên");

    }//GEN-LAST:event_rdoNhanVienMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblSoDT;
    private javax.swing.JLabel lblSoDT1;
    private javax.swing.JLabel lblcv;
    private javax.swing.JLabel lblimgEdit;
    private javax.swing.JLabel lblimgNV;
    private javax.swing.JLabel lblshowPass;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQuanLi;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
