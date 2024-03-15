package Hotel.ui;

import Hotel.dao.DSPhongDAo;
import Hotel.utils.ShareHelper;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Phạm Thị Thuý Huyền
 */
public final class Main extends javax.swing.JFrame {

    DSPhongDAo dao = new DSPhongDAo();

    public Main() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        startProgram();
    }

    public void startProgram() {
        if (ShareHelper.authenticated()) {
            initData();
            showPanel(new JpanelTrangChu(this));
            time();
            focusInit();
        } else if (ShareHelper.authenticatedNV()) {
            initData();
            showPanel(new JpanelTrangChu(this));
            time();
            focusInit();
            btnThongKe.setEnabled(false);
        } else {
            JdialogChao chao = new JdialogChao(this, true);
            chao.setVisible(true);
            FormDangNhap dn = new FormDangNhap();
            dn.setVisible(true);
        }
    }

    //hàm gọi focus bên dưới, nhưng gọn gàng hơn
    public void focusInit() {
        focus(btnDangXuat, JpanelDangXuat);
        focus(btnHoaDon, JpanelHoaDon);
        focus(btnKhachhang, JpanelKhachHang);
        focus(btnNhanVien, JpanelNhanVien);
        focus(btnPhong, JpanelQLPhong);
        focus(btnThongKe, JpanelThongKe);
        focus(btnTrangchu, JpanelTrangChu);

    }

    public void Title(String title) {
        setTitle(title);
    }

    // khi ấn chuột vào thì tô màu vàng vàng background
    public void focus(JButton name, JPanel name2) {

        name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                name.setBackground(new Color(255, 255, 204));
                name2.setBackground(new Color(255, 255, 204));
            }

            @Override
            public void focusLost(FocusEvent e) {
                name.setBackground(Color.WHITE);
                name2.setBackground(new Color(204, 204, 255));

            }
        });
    }

    public void initData() {
        String name = ShareHelper.USER.getTenNV();
        boolean chucVu = ShareHelper.USER.isChucVu();
        String img = ShareHelper.USER.getImg();
        String manv = ShareHelper.USER.getMaNV();
        String chucvu2 = "";
        if (chucVu == true) {
            chucvu2 = "Admin";
        } else {
            chucvu2 = "Nhân viên";
        }

        lblChucvu.setText(chucvu2 + " - " + manv);
        lblTenNV.setText(name);

        if (img.equals("")) {
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
            lblimg.setIcon(scaledIcon1);
        } else {
            //set hình ảnh cho lbl
            String linkImg2 = "src\\\\Hotel\\\\icon\\\\" + img;
            ImageIcon icon = new ImageIcon(linkImg2); // lấy link ảnh
            Image originalImage1 = icon.getImage();
            // Giảm kích thước hình ảnh
            int newWidth = 120; // Kích thước mới
            int newHeight = 120;
            Image scaledImage1 = originalImage1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ hình ảnh đã giảm kích thước
            ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);

            // Đặt biểu tượng mới vào JLabel
            lblimg.setIcon(scaledIcon1);

        }
    }

    // thiết lập cái đồng hồ
    public void time() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }

    // thiết lập cái ngày giờ
    private void updateTime() {
        // chuyển định dạng time
        SimpleDateFormat dinhdang = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat ngay = new SimpleDateFormat("dd/MM/yyyy");
        Date getDate = new Date();
        lblTime.setText(dinhdang.format(getDate) + "     |    " + ngay.format(getDate));
    }

    public void showPanel(JPanel panel) {
//        chidrenpanel = panel;
        JpanelView.removeAll();
        JpanelView.setLayout(new BoxLayout(JpanelView, BoxLayout.Y_AXIS)); // Đặt layout thành BoxLayout theo chiều dọc
        JpanelView.add(panel);
        JpanelView.validate();
        JpanelView.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JpanelTrangChu = new javax.swing.JPanel();
        btnTrangchu = new javax.swing.JButton();
        JpanelNhanVien = new javax.swing.JPanel();
        btnNhanVien = new javax.swing.JButton();
        JpanelKhachHang = new javax.swing.JPanel();
        btnKhachhang = new javax.swing.JButton();
        JpanelQLPhong = new javax.swing.JPanel();
        btnPhong = new javax.swing.JButton();
        JpanelHoaDon = new javax.swing.JPanel();
        btnHoaDon = new javax.swing.JButton();
        JpanelThongKe = new javax.swing.JPanel();
        btnThongKe = new javax.swing.JButton();
        JpanelDangXuat = new javax.swing.JPanel();
        btnDangXuat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblTenNV = new javax.swing.JLabel();
        lblChucvu = new javax.swing.JLabel();
        lblimg = new javax.swing.JLabel();
        JpanelView = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        JpanelTrangChu.setBackground(new java.awt.Color(204, 204, 255));

        btnTrangchu.setBackground(new java.awt.Color(255, 255, 255));
        btnTrangchu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTrangchu.setForeground(new java.awt.Color(0, 0, 0));
        btnTrangchu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Home.png"))); // NOI18N
        btnTrangchu.setText("Trang chủ");
        btnTrangchu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangchuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpanelTrangChuLayout = new javax.swing.GroupLayout(JpanelTrangChu);
        JpanelTrangChu.setLayout(JpanelTrangChuLayout);
        JpanelTrangChuLayout.setHorizontalGroup(
            JpanelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTrangchu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JpanelTrangChuLayout.setVerticalGroup(
            JpanelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JpanelNhanVien.setBackground(new java.awt.Color(204, 204, 255));

        btnNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(0, 0, 0));
        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/User.png"))); // NOI18N
        btnNhanVien.setText(" Nhân viên");
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpanelNhanVienLayout = new javax.swing.GroupLayout(JpanelNhanVien);
        JpanelNhanVien.setLayout(JpanelNhanVienLayout);
        JpanelNhanVienLayout.setHorizontalGroup(
            JpanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JpanelNhanVienLayout.setVerticalGroup(
            JpanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JpanelKhachHang.setBackground(new java.awt.Color(204, 204, 255));

        btnKhachhang.setBackground(new java.awt.Color(255, 255, 255));
        btnKhachhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKhachhang.setForeground(new java.awt.Color(0, 0, 0));
        btnKhachhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/nhanvien.png"))); // NOI18N
        btnKhachhang.setText("Quản lí khách hàng");
        btnKhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhachhangMouseClicked(evt);
            }
        });
        btnKhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachhangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpanelKhachHangLayout = new javax.swing.GroupLayout(JpanelKhachHang);
        JpanelKhachHang.setLayout(JpanelKhachHangLayout);
        JpanelKhachHangLayout.setHorizontalGroup(
            JpanelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );
        JpanelKhachHangLayout.setVerticalGroup(
            JpanelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanelKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        JpanelQLPhong.setBackground(new java.awt.Color(204, 204, 255));

        btnPhong.setBackground(new java.awt.Color(255, 255, 255));
        btnPhong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPhong.setForeground(new java.awt.Color(0, 0, 0));
        btnPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Company.png"))); // NOI18N
        btnPhong.setText("Quản lí phòng");
        btnPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpanelQLPhongLayout = new javax.swing.GroupLayout(JpanelQLPhong);
        JpanelQLPhong.setLayout(JpanelQLPhongLayout);
        JpanelQLPhongLayout.setHorizontalGroup(
            JpanelQLPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelQLPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JpanelQLPhongLayout.setVerticalGroup(
            JpanelQLPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelQLPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JpanelHoaDon.setBackground(new java.awt.Color(204, 204, 255));

        btnHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        btnHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Coin.png"))); // NOI18N
        btnHoaDon.setText("Hoá đơn");
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpanelHoaDonLayout = new javax.swing.GroupLayout(JpanelHoaDon);
        JpanelHoaDon.setLayout(JpanelHoaDonLayout);
        JpanelHoaDonLayout.setHorizontalGroup(
            JpanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JpanelHoaDonLayout.setVerticalGroup(
            JpanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JpanelThongKe.setBackground(new java.awt.Color(204, 204, 255));

        btnThongKe.setBackground(new java.awt.Color(255, 255, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(0, 0, 0));
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Notes.png"))); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpanelThongKeLayout = new javax.swing.GroupLayout(JpanelThongKe);
        JpanelThongKe.setLayout(JpanelThongKeLayout);
        JpanelThongKeLayout.setHorizontalGroup(
            JpanelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JpanelThongKeLayout.setVerticalGroup(
            JpanelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnDangXuat.setBackground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 51, 51));
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Microsoft.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JpanelDangXuatLayout = new javax.swing.GroupLayout(JpanelDangXuat);
        JpanelDangXuat.setLayout(JpanelDangXuatLayout);
        JpanelDangXuatLayout.setHorizontalGroup(
            JpanelDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelDangXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JpanelDangXuatLayout.setVerticalGroup(
            JpanelDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelDangXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTenNV.setBackground(new java.awt.Color(204, 204, 255));
        lblTenNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenNV.setForeground(new java.awt.Color(0, 0, 0));
        lblTenNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenNV.setText("Tên nhân viên");

        lblChucvu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChucvu.setText("Xin chào");

        lblimg.setBackground(new java.awt.Color(204, 204, 255));
        lblimg.setText("jLabel6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblChucvu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChucvu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblimg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenNV)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpanelNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JpanelTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JpanelKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JpanelQLPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JpanelHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JpanelThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JpanelDangXuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(JpanelTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JpanelNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JpanelKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JpanelQLPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JpanelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JpanelThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JpanelDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 660));

        JpanelView.setBackground(new java.awt.Color(255, 255, 204));
        JpanelView.setPreferredSize(new java.awt.Dimension(730, 556));
        JpanelView.setRequestFocusEnabled(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/hotel.jpg"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("KHÁCH SẠN XIN CHÀO");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/5 sao.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Dịch vụ tốt nhất, Đúng lúc, Đúng người");

        javax.swing.GroupLayout JpanelViewLayout = new javax.swing.GroupLayout(JpanelView);
        JpanelView.setLayout(JpanelViewLayout);
        JpanelViewLayout.setHorizontalGroup(
            JpanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanelViewLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 517, Short.MAX_VALUE))
            .addGroup(JpanelViewLayout.createSequentialGroup()
                .addGroup(JpanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelViewLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JpanelViewLayout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel3))
                    .addGroup(JpanelViewLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JpanelViewLayout.setVerticalGroup(
            JpanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanelViewLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(JpanelView, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1080, 660));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Info.png"))); // NOI18N
        jLabel4.setText("Hệ thống quản lí khách sạn");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 280, 30));

        lblTime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/icon/Alarm.png"))); // NOI18N
        lblTime.setText("12:00:00          12/12/2023");
        getContentPane().add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 670, 170, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangchuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangchuActionPerformed
        // TODO add your handling code here:
        showPanel(new JpanelTrangChu(this));
    }//GEN-LAST:event_btnTrangchuActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        showPanel(new JpanelNhanVien(this));
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        showPanel(new JpanelThongKe(this));
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnKhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachhangActionPerformed
        // TODO add your handling code here:
        showPanel(new JpanelKhachHang(this));
    }//GEN-LAST:event_btnKhachhangActionPerformed

    private void btnKhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachhangMouseClicked
        // TODO add your handling code here:

        showPanel(new JpanelKhachHang(this));


    }//GEN-LAST:event_btnKhachhangMouseClicked

    private void btnPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhongActionPerformed
        // TODO add your handling code here:

        showPanel(new JpanelDatPhong(this));
    }//GEN-LAST:event_btnPhongActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
        showPanel(new JpanelHoaDon(this));
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseClicked
        // TODO add your handling code here:
        int thoat = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất", "thông báo", JOptionPane.YES_NO_OPTION);
        if (thoat == JOptionPane.YES_OPTION) {
            ShareHelper.USER = null;
            this.dispose();
            FormDangNhap dn = new FormDangNhap();
            dn.setVisible(true);

        } else {
            return;
        }
    }//GEN-LAST:event_btnDangXuatMouseClicked

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main main = new Main();

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelDangXuat;
    private javax.swing.JPanel JpanelHoaDon;
    private javax.swing.JPanel JpanelKhachHang;
    private javax.swing.JPanel JpanelNhanVien;
    private javax.swing.JPanel JpanelQLPhong;
    private javax.swing.JPanel JpanelThongKe;
    private javax.swing.JPanel JpanelTrangChu;
    private javax.swing.JPanel JpanelView;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachhang;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnPhong;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnTrangchu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblChucvu;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblimg;
    // End of variables declaration//GEN-END:variables
}
