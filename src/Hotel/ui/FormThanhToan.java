/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Hotel.ui;

import Hotel.dao.DSPhongDAo;
import Hotel.dao.DatPhongDAO;
import Hotel.dao.DichVuDAO;
import Hotel.dao.DichVuDaThueDAO;
import Hotel.dao.HoaDonChiTietDAO;
import Hotel.dao.HoaDonThanhToanDAO;
import Hotel.dao.KhachHangDAO;
import Hotel.entity.DSPhong;
import Hotel.entity.DatPhong;
import Hotel.entity.DichVu;
import Hotel.entity.DichVuDaThue;
import Hotel.entity.HoaDonChiTiet;
import Hotel.entity.HoaDonThanhToan;
import Hotel.utils.DialogHelper;
import Hotel.utils.ShareHelper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;

import com.google.zxing.common.HybridBinarizer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author LENOVO
 */
public class FormThanhToan extends javax.swing.JFrame {

    DatPhongDAO daoDP = new DatPhongDAO();
    KhachHangDAO daoKH = new KhachHangDAO();
    DichVuDAO daoDV = new DichVuDAO();
    DSPhongDAo daoDSP = new DSPhongDAo();
    DichVuDaThueDAO daoDVDT = new DichVuDaThueDAO();
    HoaDonChiTietDAO daoHDCT = new HoaDonChiTietDAO();
    HoaDonThanhToanDAO daoHD = new HoaDonThanhToanDAO();
    private List<HoaDonThanhToan> listHD = daoHD.select();
    private Main mainForm; // Tham chiếu của form chính
    DecimalFormat decimalFormat = new DecimalFormat("#,##0");
    private String MaPhong = "";
    private final float tienGioDau = 90000;
    private float tienGioSau = 50000;
    private float tienNgay = 400000;
    private int soNgayKhachO;
    private int soGioKhachO;
    private float tongTienKhachO = 0;
    private float tienDichVu = 0;
    private float phuThu = 0;
    private float Tongtien;
    private float sodu = 0;

    public FormThanhToan(String maphong, Main main) {
        initComponents();
        this.mainForm = main;
        this.MaPhong = maphong;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initData();
        lblQR.setVisible(false);
        btnXuatHD.setBackground(Color.red);

    }

    public static BufferedImage generateQRCode(String content, int width, int height) throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            throw new WriterException(e.getMessage());
        }

        int matrixWidth = bitMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < matrixWidth; x++) {
            for (int y = 0; y < matrixWidth; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    public void initData() {
        lblPhong.setText(MaPhong);
        try {
            List<DatPhong> listDP = daoDP.findByMaPhong(MaPhong);
            int maxMaDP = listDP.get(0).getMaDP(); // Lấy mã đặt phòng đầu tiên làm giá trị lớn nhất

            // Duyệt qua danh sách để tìm mã đặt phòng lớn nhất
            for (DatPhong dp : listDP) {
                int maDP = dp.getMaDP();
                if (maDP > maxMaDP) {
                    maxMaDP = maDP;
                }
            }

            // tìm được giá trị lớn nhất
            lblMaDP.setText(String.valueOf(maxMaDP));
            //truy xuất thông qua mã đặt phòng lớn nhất
            DatPhong listDP2 = daoDP.findByMaDP(maxMaDP);
            txtMaKH.setText(listDP2.getMaKH());
            txtNgayDen.setText(listDP2.getNgayDen());
            SimpleDateFormat ngay = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dinhdang = new SimpleDateFormat("HH:mm:ss");
            Date getDate = new Date();
            txtNgayTra.setText(ngay.format(getDate) + " " + dinhdang.format(getDate));

            String ngayden = txtNgayDen.getText();
            String ngaydi = txtNgayTra.getText();

            // =================================================================
            // tính tiền
            if (listDP2.getMaKieuThue().equals("K001")) {
                lblKieuThue.setText("Khách thuê theo ngày");
                TinhNgay(ngayden, ngaydi);
                tongTienKhachO = tongTienKhachO + 400000 + soNgayKhachO * tienNgay;
                String tongTienFormatted = decimalFormat.format(tongTienKhachO);
                lblTienGio.setText(String.valueOf(tongTienFormatted));
            } else {
                lblKieuThue.setText("Khách thuê theo giờ");
                TinhGio(ngayden, ngaydi);
                float tongGio = (float) (tienGioDau + (soGioKhachO - 1) * tienGioSau); // Trừ đi 1 vì giờ đầu đã được tính
                tongTienKhachO += tongGio;
                String tongTienFormatted = decimalFormat.format(tongTienKhachO);
                lblTienGio.setText(String.valueOf(tongTienFormatted));
            }
            try {
                DSPhong ds = daoDSP.findById(MaPhong);
                String loaiPhong = ds.getMaLoaiPhong();
                lblLoaiPhong.setText(loaiPhong);
                if (loaiPhong.equals("VIP")) {
                    phuThu = 100000;
                    lblPhuThu.setText(String.valueOf(phuThu));
                    Tongtien += 100000;
                } else {
                    phuThu = 0;
                    lblPhuThu.setText(String.valueOf(phuThu));
                    Tongtien += 0;
                }
                String tongTienFormatted = decimalFormat.format(phuThu);
                lblPhuThu.setText(String.valueOf(tongTienFormatted));

            } catch (Exception e) {
                System.out.println(e);
            }

            lblNgayLap.setText(ngay.format(getDate));
            lblMaNV.setText(ShareHelper.USER.getMaNV());

            //=========================================================================
            // remove dịch vụ
            cboDichVu.removeAllItems();
            int madp;
            madp = Integer.parseInt(lblMaDP.getText());
            try {
                List<DichVuDaThue> listDVDT = new ArrayList<>();
                listDVDT = (List<DichVuDaThue>) daoDVDT.findById(madp);
                for (DichVuDaThue dv : listDVDT) {
                    cboDichVu.addItem(dv.getTenDV());
                    float dongia = dv.getDonGia();
                    tienDichVu += dongia;
                }
                String tongTienFormatted = decimalFormat.format(tienDichVu);
                lblTienDichVu.setText(String.valueOf(tongTienFormatted));
            } catch (Exception e) {
                System.out.println(e);
            }
            // truy vấn lấy mã hóa đơn lớn nhất

            if (listHD == null || listHD.isEmpty()) {
                lblMaHD.setText("HD01");
            } else {
                int size = listHD.size() + 1;
                String maHD = "HD";
                if (size < 10) {
                    maHD = "HD0";
                }

                lblMaHD.setText(maHD + "" + size);

            }

            Tongtien = tongTienKhachO + tienDichVu + phuThu;
            String tongTienFormatted = decimalFormat.format(Tongtien);

            lblTongTien.setText(String.valueOf(tongTienFormatted));

        } catch (Exception e) {
            System.out.println(e);
        }
        //==========================
        // set cbo hình thức thanh toán
        cboHinhThuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboHinhThuc.getSelectedItem().equals("Tiền mặt")) {
                    txtTienKhachDua.setEnabled(true);
                    txtSoDu.setEnabled(true);
                    lblQR.setVisible(false);

                    float tongTienTT = tongTienKhachO + tienDichVu + phuThu;
                    checkSodu();
                } else if (cboHinhThuc.getSelectedItem().equals("Chuyển khoản")) {
                    try {
                        BufferedImage qrCodeImage = generateQRCode(String.valueOf("Số tiền phải thanh toán là: " + lblTongTien.getText()), 125, 125);
                        ImageIcon icon = new ImageIcon(qrCodeImage);
                        lblQR.setIcon(icon);
                    } catch (WriterException ex) {
                        Logger.getLogger(FormThanhToan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    btnXuatHD.setBackground(Color.GREEN);

                    txtTienKhachDua.setEnabled(false);
                    txtSoDu.setEnabled(false);
                    lblQR.setVisible(true);

                }
            }
        });
        //============================

    }

    public void checkSodu() {
        if (txtTienKhachDua.getText().equals("")) {
            btnXuatHD.setBackground(Color.red);
            return;
        }
        try {
            float tienTra = Float.parseFloat(txtTienKhachDua.getText());
            float tongTienTT = tongTienKhachO + tienDichVu + phuThu;
            float rs = tienTra - tongTienTT;
            sodu = tienTra - tongTienTT;
            String tongTienFormatted = decimalFormat.format(rs);
            txtSoDu.setText(String.valueOf(tongTienFormatted));
            if (rs < 0) {
                btnXuatHD.setBackground(Color.red);
            } else {
                btnXuatHD.setBackground(Color.GREEN);

            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

    }

    public void insertHoaDonChiTiet() {

        for (int i = 0; i < cboDichVu.getItemCount(); i++) {

            HoaDonChiTiet model = getModelHoaDonChiTiet(i);
            try {
                daoHDCT.insert(model);
                DSPhong model2 = getModelDSPhong();
                daoDSP.update(model2);
                Main main = new Main();
                main.showPanel(new JpanelDatPhong(mainForm));
            } catch (Exception e) {
                System.out.println("Khach hang loi: " + e);
            }
        }

    }

    DSPhong getModelDSPhong() {
        DSPhong model = new DSPhong();
        model.setTrangThai("Trống");
        model.setMaPhong(lblPhong.getText());
        return model;
    }

    HoaDonChiTiet getModelHoaDonChiTiet(int i) {
        HoaDonChiTiet model = new HoaDonChiTiet();
        model.setMaHD(lblMaHD.getText());

        Object nameDV = cboDichVu.getItemAt(i);

        DichVu dv = daoDV.findById(String.valueOf(nameDV));
        model.setMaDV(dv.getMaDV());
        model.setNgaythue(txtNgayDen.getText());
        model.setPhuThu(phuThu);
        return model;
    }

    public void TinhNgay(String ngayDen, String ngayDi) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // Chuyển đổi chuỗi ngày đến và ngày đi thành đối tượng Date
            Date dateDen = dateFormat.parse(ngayDen);
            Date dateDi = dateFormat.parse(ngayDi);
            // Tính số ngày giữa hai ngày
            long diffInMillies = Math.abs(dateDi.getTime() - dateDen.getTime());
            long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillies);
            // Tính số giờ và số phút giữa hai ngày
            long diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillies) % 24;
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies) % 60;
            if (diffInMinutes >= 1) {
                diffInHours += 1;
            }
            // Hiển thị kết quả
            lblNgayO.setText(String.valueOf(diffInDays + " Ngày " + diffInHours + " Giờ"));
            soNgayKhachO = (int) diffInDays;
            soGioKhachO = (int) diffInHours;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void TinhGio(String ngayDen, String ngayDi) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // Chuyển đổi chuỗi ngày đến và ngày đi thành đối tượng Date
            Date dateDen = dateFormat.parse(ngayDen);
            Date dateDi = dateFormat.parse(ngayDi);
            // Tính số ngày giữa hai ngày
            long diffInMillies = Math.abs(dateDi.getTime() - dateDen.getTime());
            long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillies);
            // Tính số giờ và số phút giữa hai ngày
            long diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillies);
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies) % 60;
            if (diffInMinutes > 30) {
                diffInHours += 1;
            }
            // Hiển thị kết quả
            if (diffInHours == 0) {
                diffInHours = 1;
                lblNgayO.setText(String.valueOf(diffInHours + " Giờ"));
                soGioKhachO = (int) diffInHours;
            } else {
                lblNgayO.setText(String.valueOf(diffInHours + " Giờ"));
                soGioKhachO = (int) diffInHours;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void insertHoaDonThanhToan() {
        HoaDonThanhToan model = getModelHoaDonTT();
        try {
            daoHD.insert(model);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    HoaDonThanhToan getModelHoaDonTT() {
        HoaDonThanhToan model = new HoaDonThanhToan();
        model.setMaHD(lblMaHD.getText());
        model.setMaDP(Integer.parseInt(lblMaDP.getText()));
        model.setMaNV(lblMaNV.getText());
        model.setMaKH(txtMaKH.getText());
        model.setNgayThue(txtNgayDen.getText());
        model.setNgayTraD(txtNgayTra.getText());
        Tongtien = tongTienKhachO + tienDichVu + phuThu;
        model.setThanhTien(Tongtien);
        model.setNgayLap(txtNgayTra.getText());

        return model;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel183 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel185 = new javax.swing.JLabel();
        txtNgayDen = new javax.swing.JTextField();
        jLabel186 = new javax.swing.JLabel();
        txtNgayTra = new javax.swing.JTextField();
        jLabel187 = new javax.swing.JLabel();
        cboDichVu = new javax.swing.JComboBox<>();
        jLabel188 = new javax.swing.JLabel();
        lblTienDichVu = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        lblNgayLap = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        lblPhuThu = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        lblMaDP = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel193 = new javax.swing.JLabel();
        lblNgayO = new javax.swing.JLabel();
        lblKieuThue = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        lblLoaiPhong = new javax.swing.JLabel();
        lblTienGio = new javax.swing.JLabel();
        lblTienGio1 = new javax.swing.JLabel();
        btnXuatHD = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboHinhThuc = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSoDu = new javax.swing.JTextField();
        lblQR = new javax.swing.JLabel();
        lblPhong = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel64.setText("Mã phòng:");

        jLabel65.setBackground(new java.awt.Color(0, 0, 0));
        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("HOÁ ĐƠN THANH TOÁN");

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel183.setText("Mã hoá đơn");

        lblMaHD.setText("jLabel9");

        jLabel184.setText("Khách hàng:");

        txtMaKH.setEditable(false);

        jLabel185.setText("Ngày đến:");

        txtNgayDen.setEditable(false);

        jLabel186.setText("Ngày trả:");

        txtNgayTra.setEditable(false);

        jLabel187.setText("Tiền dịch vụ");

        cboDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel188.setText("Phụ thu");

        lblTienDichVu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTienDichVu.setText("Tiền dịch vụ");

        jLabel189.setText("Ngày lập hoá đơn");

        lblNgayLap.setText("jLabel10");

        jLabel190.setText("Mã nhân viên");

        lblMaNV.setText("jLabel11");

        jLabel191.setText("Dịch vụ đã thuê");

        lblPhuThu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPhuThu.setText("Phu thu");

        jLabel192.setText("Mã đặt phòng");

        lblMaDP.setText("jLabel9");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel193.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel193.setText("Số ngày/giờ khách đã ở");

        lblNgayO.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNgayO.setForeground(new java.awt.Color(0, 0, 255));
        lblNgayO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayO.setText("Số ngày/giờ khách đã ở");

        lblKieuThue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKieuThue.setForeground(new java.awt.Color(255, 0, 51));
        lblKieuThue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKieuThue.setText("Kiểu thuê");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel193, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNgayO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblKieuThue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel193)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblNgayO)
                .addGap(18, 18, 18)
                .addComponent(lblKieuThue)
                .addContainerGap())
        );

        jLabel194.setText("Loại phòng");

        lblLoaiPhong.setText("Vip");

        lblTienGio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTienGio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTienGio.setText("Tiền giờ");

        lblTienGio1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTienGio1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTienGio1.setText("Tổng");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel183)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMaHD)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel192)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMaDP)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel194)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblLoaiPhong)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel189)
                                    .addComponent(jLabel190)
                                    .addComponent(jLabel188))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPhuThu, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                        .addComponent(lblMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(36, 36, 36))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel187)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(lblTienGio1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTienGio))))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel186, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel191)
                            .addComponent(jLabel185, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel184))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboDichVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayTra)
                            .addComponent(txtNgayDen)
                            .addComponent(txtMaKH))))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel183)
                    .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel192)
                    .addComponent(lblMaDP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel194)
                    .addComponent(lblLoaiPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel184)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel185, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel186)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel191)
                    .addComponent(cboDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel187)
                            .addComponent(lblTienDichVu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel188)
                            .addComponent(lblPhuThu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel189)
                            .addComponent(lblNgayLap))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel190)
                            .addComponent(lblMaNV)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTienGio)
                            .addComponent(lblTienGio1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnXuatHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatHD.setForeground(new java.awt.Color(0, 0, 0));
        btnXuatHD.setText("Xuất hoá đơn");
        btnXuatHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHDActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Phương thức thanh toán");

        jLabel2.setText("Tổng tiền:");

        lblTongTien.setBackground(new java.awt.Color(51, 0, 255));
        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongTien.setText("jLabel3");

        jLabel4.setText("Hình thức thanh toán:");

        cboHinhThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));

        jLabel6.setText("Khách đưa");

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel7.setText("Tiền dư:");

        txtSoDu.setEditable(false);

        lblQR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/qr.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboHinhThuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongTien))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(txtSoDu))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSoDu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblPhong.setText("jLabel8");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPhong)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXuatHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(lblPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHDActionPerformed
        // TODO add your handling code here:
        if (cboHinhThuc.getSelectedIndex() == 1) {
            insertHoaDonThanhToan();
            insertHoaDonChiTiet();
            DialogHelper.alert(this, "Thanh toán thanh công !");
            this.dispose();
            mainForm.showPanel(new JpanelDatPhong(mainForm));

            return;
        }
        if (cboHinhThuc.getSelectedIndex() == 0 && txtTienKhachDua.getText().equals("")) {
            DialogHelper.alert(this, "Chưa trả tiền mà đòi có hóa đơn !?");
            txtTienKhachDua.requestFocus();
            return;
        }
        if (cboHinhThuc.getSelectedIndex() == 0 && sodu < 0) {
            DialogHelper.alert(this, "Chưa trả đủ tiền !");
            txtSoDu.requestFocus();
            return;
        }
        insertHoaDonThanhToan();
        insertHoaDonChiTiet();
        DialogHelper.alert(this, "Thanh toán thanh công !");
        this.dispose();
        mainForm.showPanel(new JpanelDatPhong(mainForm));


    }//GEN-LAST:event_btnXuatHDActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:

        checkSodu();
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

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
            java.util.logging.Logger.getLogger(FormThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatHD;
    private javax.swing.JComboBox<String> cboDichVu;
    private javax.swing.JComboBox<String> cboHinhThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblKieuThue;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaDP;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblNgayLap;
    private javax.swing.JLabel lblNgayO;
    private javax.swing.JLabel lblPhong;
    private javax.swing.JLabel lblPhuThu;
    private javax.swing.JLabel lblQR;
    private javax.swing.JLabel lblTienDichVu;
    private javax.swing.JLabel lblTienGio;
    private javax.swing.JLabel lblTienGio1;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNgayDen;
    private javax.swing.JTextField txtNgayTra;
    private javax.swing.JTextField txtSoDu;
    private javax.swing.JTextField txtTienKhachDua;
    // End of variables declaration//GEN-END:variables
}
