Create database QLKhachSan
go

use QLKhachSan
go

create table NhanVien(
	MaNV nvarchar(10) not null,
	TenNV nvarchar(30) not null,
	SoDT nvarchar(12) not null,
	ChucVu bit not null,
	MatKhau nvarchar(20) not null,
	Email nvarchar (30) not null,
	GioiTinh bit ,
	HinhAnh varchar(50),
	constraint pk_nhanvien primary key (MaNV)
)
go

insert into  NhanVien values
('NV01',N'Phạm Thị Thúy Huyền','0364899497',1,'Thuyhuyen','Huyenpttps35245@fpteduvn',1,''),
('NV02',N'Huỳnh Thị Yến Nhi','0386706328',0,'Yennhi','Nhihtyps32797@fpteduvn',1,''),
('NV03',N'Nguyễn Thanh Ngân','0346753850',0,'Thanhngan','Thanhnganps35194@fpteduvn',1,''),
('NV04',N'Lê Minh Đức','0382006372',0,'Minhduc','Duclmps26504@fpteduvn',0,''),
('NV05',N'Lê Phúc Duy','0352065377',0,'Phucduy','Duylpps36206@fpteduvn',0,''),
('NV06',N'Nguyễn Tuấn Anh','0367248305',0,'Tuananh','Anhntps36379@fpteduvn',0,''),
('NV07',N'Đỗ Tấn Phát','0367582092',1,'Tanphat','Phatdtps23240@fpteduvn',0,'')
go

create table DichVu(
	MaDV nvarchar(10) not null,
	TenDV nvarchar(225) not null,
	Gia float not null,
	constraint pk_dichvu primary key (MaDV)
)
go

insert into Dichvu values
('DV01',N'Dịch vụ Spa',199000),
('DV02',N'Dịch vụ trông trẻ', 100000),
('DV03',N'Dịch vụ cho thuê xe máy tự lái', 60000),
('DV04',N'Dịch vụ giặt ủi quần áo',50000),
('DV05',N'Dịch vụ đưa đón sân bay',40000),
('DV06',N'Dịch vụ nhà hàng', 179000),
('DV07',N'Dịch vụ karaoke',250000),
('DV08',N'Dịch vụ cho thuê xe đạp', 35000),
('DV09',N'Dịch vụ tắm suối',40000),
('DV10',N'Yêu cầu dịch vụ ở ngoài', 350000)
go

create table LoaiPhong(
	MaLoaiPhong nvarchar(10) not null,
	TenLoaiPhong nvarchar(50) not null,
	constraint pk_loaiphong primary key(MaLoaiPhong)
)
go
insert into LoaiPhong values
('TH',N'Phòng thường'),
('VIP',N'Phòng VIP')
go

create table DSPhong(
	MaPhong nvarchar(10) not null,
	TenPhong nvarchar(50) not null,
	MaLoaiPhong nvarchar(10) not null,
	TrangThai nvarchar(50) not null,
	constraint pk_phong primary key (MaPhong),
	CONSTRAINT fk_loaiphong_phong foreign key (MaLoaiPhong) references LoaiPhong (MaLoaiPhong) ON DELETE CASCADE ON UPDATE CASCADE
)
go

insert into DSPhong values
('PT101',N'Phòng giường đơn','TH', N'Trống'),
('PT102',N'Phòng giường đôi','TH',N'Trống'),
('PT103',N'Phòng giường đơn','TH',N'Đang sử dụng'),
('PT104',N'Phòng giường đôi','TH',N'Trống'),
('PT205',N'Phòng giường đôi','TH',N'Đang sử dụng'),
('PT206',N'Phòng giường đôi','VIP',N'Trống'),
('PT207',N'Phòng giường đơn','TH',N'Đang sử dụng'),
('PT208',N'Phòng giường đôi','TH',N'Trống'),
('PT309',N'Phòng giường đơn','TH',N'Trống'),
('PT310',N'Phòng giường đôi','VIP',N'Trống'),
('PT311',N'Phòng giường đơn','VIP',N'Đang sử dụng'),
('PT312',N'Phòng giường đôi','VIP',N'Trống'),
('PT413',N'Phòng giường đơn','VIP',N'Đang sử dụng'),
('PT414',N'Phòng giường đôi','TH',N'Đang sử dụng'),
('PT415',N'Phòng giường đôi','VIP',N'Trống'),
('PT416',N'Phòng giường đơn','VIP',N'Đang sử dụng')
go

create table KhachHang(
	MaKH nvarchar(10) not null,
	TenKH nvarchar(30) not null,
	SoDT nvarchar(12) not null,
	CCCD nvarchar(12) not null,
	Gioitinh bit not null,
	DiaChi nvarchar(100),
	constraint pk_khachhang primary key (MaKH)
)
go

insert into Khachhang values
('KH001',N'Nguyễn Hồng Ngọc',0346753850,042306774452,1,N'Bình Định'),
('KH002',N'Trần Thanh Thanh', 0378549422, 048572197380,0,N'Thái Bình'),
('KH003',N'Lê Ngọc An', 0983728233, 043542177449, 1, N'Tp Hồ Chí Mình'),
('KH004',N'Phan Thị Thanh Nhàn', 0967409403, 048421689445,1,N'Vũng Tàu'),
('KH005',N'Nguyễn Văn Thông', 0768737836, 078945632154,0,N'Tp Hồ Chí Minh'),
('KH006',N'Nguyễn Hồng Nhung',0347096417,039854274381,1,N'Cà Mau'),
('KH007',N'Lý Anh Thư', 0898648738,042300043577,1,N'Quy Nhơn'),
('KH008',N'Đặng Thành Tuấn',0392302056,044452379573,0,N'Tân Bình, Hồ Chí Minh'),
('KH009',N'Võ Nguyễn Duy Tân',0862798607, 035194342457,0,N'Mỹ Tho'),
('KH010',N'Lê Đại Nghĩa', 0363636590, 046744564329, 0,N'Quận 7, Hồ Chí Mình'),
('KH011',N'Nguyễn Khánh Huyền', 0394175076, 0233441865732,1,N'Bình Giang, Hải Dương'),
('KH012',N'Nguyễn Thu Hậu', 0377492354, 042376000665,1,N'Bắc Từ Liêm, Hà Nội'),
('KH013',N'Lương Phạm Hữu Hậu', 0924163508,042446000775,0,N'Eakar, Đắk Lắk'),
('KH014',N'Nguyễn Thị Liên', 0789222053, 048880224241,1,N'Thanh Miện, Hải Dương'),
('KH015',N'Hà Thị Phượng', 0788242053, 048850224243,1,N'Triệu Sơn, Thanh Hoá')
go

create table KieuThue(
	MaKieuThue nvarchar(10) not null,
	KieuThue nvarchar(50),
	DonGia float not null,
	constraint pk_kieuthue primary key (MaKieuThue)
)
go

 insert into KieuThue values
 ('K001',N'Ngày', 400000),
 ('K002',N'Giờ',90000)
 go

 CREATE TABLE DatPhong (
    MaDP INT IDENTITY(1, 1) PRIMARY KEY,
    NgayDen DATETIME DEFAULT CURRENT_TIMESTAMP,
    NgayDi DATE,
    MaNV NVARCHAR(10),
    MaKH NVARCHAR(10),
    MaPhong NVARCHAR(10),
    MaKieuThue NVARCHAR(10),
    CONSTRAINT fk_nhanvien_datphong FOREIGN KEY (MaNV) REFERENCES NhanVien (MaNV) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_khachhang_datphong FOREIGN KEY (MaKH) REFERENCES KhachHang (MaKH) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_phong_datphong FOREIGN KEY (MaPhong) REFERENCES DSPhong (MaPhong) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_kieuthue_datphong FOREIGN KEY (MaKieuThue) REFERENCES KieuThue (MaKieuThue)
);
GO

INSERT INTO DatPhong VALUES
('2021/09/01 13:15:00', '2021/09/07',  'NV02', 'KH004', 'PT101', 'K001'),
('2022/09/02 08:00:05', '2022/09/07',  'NV02', 'KH001', 'PT414', 'K001'),
('2022/09/12 20:30:00', '2022/09/12',  'NV06', 'KH011', 'PT104', 'K002'),
('2021/09/17 10:50:00', '2021/09/18', 'NV06', 'KH012', 'PT414', 'K001'),
('2022/09/17 21:12:32', '2022/09/17',  'NV03', 'KH015', 'PT207', 'K002'),
('2022/09/02 17:45:32', '2022/09/04',  'NV03', 'KH006', 'PT311', 'K001'),
('2023/10/20 22:00:00', '2023/09/20',  'NV06', 'KH008', 'PT104', 'K002'),
('2022/11/25 14:00:12', '2022/11/27', 'NV03', 'KH005', 'PT102', 'K001'),
('2022/11/29 12:00:00', '2022/11/29',  'NV04', 'KH011', 'PT207', 'K002'),
('2021/12/12 08:00:19', '2021/09/14',  'NV04', 'KH014', 'PT413', 'K001'),
('2021/12/29 09:30:00', '2021/12/30',  'NV03', 'KH014', 'PT416', 'K001'),
('2022/09/12 20:22:12', '2021/09/12', 'NV06', 'KH011', 'PT104', 'K002'),
('2022/01/01 13:37:43', '2022/01/01', 'NV01', 'KH015', 'PT103', 'K002'),
('2020/01/05 12:29:11', '2020/01/07',  'NV07', 'KH002', 'PT205', 'K001'),
('2021/02/14 22:12:33', '2021/02/15', 'NV07', 'KH013', 'PT104', 'K001'),
('2023/03/08 23:33:21', '2023/03/10',  'NV05', 'KH007', 'PT101', 'K001'),
('2021/09/08 11:33:12', '2021/09/15',  'NV05', 'KH009', 'PT207', 'K001'),
('2022/04/05 07:32:38', '2022/04/05',  'NV01', 'KH002', 'PT208', 'K002'),
('2021/09/17 23:12:43', '2021/09/17',  'NV07', 'KH005', 'PT309', 'K002')
go

create table HoaDonThanhToan(
	MaHD nvarchar(10) not null,
	MaDP int not null,
	MaNV nvarchar(10) not null,
	MaKH nvarchar(10) not null,
	NgayBD datetime not null,
	NgayTra datetime DEFAULT CURRENT_TIMESTAMP not null,
	ThanhTien float not null,
	NgayLap datetime not null,
	constraint pk_hoadon primary key (MaHD),
	constraint fk_nhanvien_hoadon foreign key (MaNV) references NhanVien (MaNV) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint fk_datphong_hoadon foreign key (MaDP) references DatPhong (MaDP),
	constraint fk_khachhang_hoadon foreign key (MaKH) references KhachHang (MaKH) ON DELETE CASCADE ON UPDATE CASCADE,
)
go

INSERT INTO HoaDonThanhToan VALUES 
('HD001', 1,'NV02', 'KH004', '2021/09/01 13:15:00', '2021/09/07 12:40:45', 1898000, '2021/09/07'),
('HD002', 2, 'NV02', 'KH001', '2022/09/02 08:00:05','2022/09/07 07:55:00', 2640000, '2022/09/07'),
('HD003', 3, 'NV06', 'KH011', '2022/09/12 20:30:00', '2022/09/12 22:30:00', 280000, '2022/09/12'),
('HD004', 4, 'NV06', 'KH012', '2021/09/17 10:50:00', '2021/09/18 10:35:12', 400000, '2021/09/18'),
('HD005', 5, 'NV03', 'KH015', '2022/09/17 21:12:32', '2022/09/17 22:15:55', 90000, '2022/09/17'),
('HD006', 6, 'NV03', 'KH006', '2022/09/02 17:45:32', '2022/09/04 17:30:00', 1658000, '2022/09/05'),
('HD007', 7, 'NV06', 'KH008', '2023/10/20 22:00:00', '2023/09/20 23:03:00', 578000, '2023/09/20'),
('HD008', 8, 'NV03', 'KH005', '2022/11/25 14:00:12',  '2022/11/27 14:10:00', 860000, '2022/11/27'),
('HD009', 9, 'NV04', 'KH011', '2022/11/29 12:00:00', '2022/11/29 14:03:23', 380000, '2022/11/29'),
('HD010', 10, 'NV04', 'KH014', '2021/12/12 08:00:19', '2021/12/14 08:12:43', 758000, '2021/09/14'),
('HD011', 11, 'NV03', 'KH014', '2021/12/29 09:30:00', '2021/12/30 09:33:33', 400000, '2021/12/30'),
('HD012', 12, 'NV06', 'KH011', '2022/09/12 20:22:12', '2022/09/12 21:20:22', 400000, '2022/09/12'),
('HD013', 13, 'NV01', 'KH015', '2022/01/01 13:37:43', '2022/01/01 17:33:54', 360000, '2022/01/01'),
('HD014', 14, 'NV07', 'KH002', '2020/01/05 12:29:11', '2020/01/07 12:11:12', 700000, '2020/01/07'),
('HD015', 15, 'NV07', 'KH013', '2021/02/14 22:12:33', '2021/02/15 00:32:33', 400000, '2021/02/15'),
('HD016', 16, 'NV05', 'KH007', '2023/03/08 23:33:21', '2023/03/10 22:43:22', 1440000, '2023/03/10'),
('HD017', 17, 'NV05', 'KH009', '2021/09/08 11:33:12', '2021/09/15 10:11:43',  3100000, '2021/09/15'),
('HD018', 18, 'NV01', 'KH002', '2022/04/05 07:32:38', '2022/04/05 09:32:09', 550000, '2022/03/05'),
('HD019', 19, 'NV07', 'KH005', '2021/09/17 23:12:43', '2021/09/17 01:11:11',  370000, '2021/09/17')
 go

 create table HoaDonChiTiet(
	MaHD nvarchar(10),
	MaDV nvarchar(10),
	Ngaythue datetime,
	PhuThu float,
	constraint fk_hoadon_hoadonchitiet foreign key (MaHD) references HoaDonThanhToan (MaHD)ON DELETE CASCADE ON UPDATE CASCADE
)
go

INSERT INTO HoaDonChiTiet VALUES 
('HD001','DV01','2022/09/01',0),
('HD002','DV03','2022/09/04',100000),
('HD003','DV02','2022/09/12',0),
('HD004','DV02','2022/09/12',0),
('HD005','DV02','2022/09/12',100000),
('HD006','DV06','2022/09/18',100000),
('HD007','DV01','2023/10/20',0),
('HD008','DV01','2023/11/25',0),
('HD009','DV02','2023/11/29',0),
('HD010','DV06','2023/12/12',100000),
('HD011','DV05','2024/09/08',0),
('HD012','DV07','2024/09/12',0),
('HD013','DV02','2024/04/05',0),
('HD014','DV05','2024/09/17',0),
('HD015','DV08','2024/09/17',0),
('HD016','DV04','2024/09/17',0),
('HD017','DV09','2024/09/17',0),
('HD018','DV07','2024/09/17',0),
('HD019','DV03','2024/09/17',0)
go

 create table DichVuDaThue(
	MaDVDT int identity(1,1) primary key,
	MaDP int not null,
	MaDV nvarchar(10),
	TenDV nvarchar(50),
	DonGia float,
	constraint fk_dichvu_dichvudathue foreign key (MaDV) references DichVu (MaDV),
	constraint fk_datphong_dichvudathue foreign key (MaDP) references DatPhong (MaDP)
)
go
create table DSbanbe(
	MaDS int identity(1,1) primary key,
	MaNV nvarchar(10),
	BanBe bit,
	MaNVKB nvarchar(10)
	FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
)
INSERT INTO DSbanbe VALUES 
('NV01','1','NV02'),
('NV01','1','NV03'),
('NV01','1','NV04'),
('NV01','1','NV05'),
('NV01','1','NV06'),
('NV01','1','NV07'),
('NV01','1','NV010')



CREATE PROCEDURE sp_DoanhThuPhong
    @Thang INT,
    @Nam INT
AS
BEGIN
	SELECT ROW_NUMBER() OVER (ORDER BY COUNT(DatPhong.MaPhong)) AS STT, DatPhong.MaPhong,PHUTHU, HoaDonThanhToan.ThanhTien  FROM DatPhong 
	JOIN HoaDonThanhToan ON HoaDonThanhToan.MaDP = DatPhong.MaDP
	JOIN HoaDonChiTiet ON HoaDonChiTiet.MaHD = HoaDonThanhToan.MaHD
	where month(ngaylap) = @Thang and year(ngaylap) = @nam 
	 GROUP BY
        DatPhong.MaPhong, PhuThu, HoaDonThanhToan.ThanhTien;
end
EXEC sp_DoanhThuPhong @Thang = 09, @Nam = 2022;
drop proc sp_DoanhThuPhong

go
CREATE OR ALTER PROCEDURE sp_DoanhThuDichVu
    @Thang INT,
    @Nam INT
AS 
BEGIN
    SELECT 
        ROW_NUMBER() OVER (ORDER BY COUNT(dv.madv)) AS STT,
        dv.MaDV,
        dv.TenDV,
        Count(dvdt.MaDV) AS 'Số lượng',
        dv.Gia,
       Count(dvdt.MaDV) * dv.Gia AS 'Thành Tiền'
    FROM 
        DichVu dv
        JOIN DichVuDaThue dvdt ON dvdt.MaDV = dv.MaDV
        JOIN DatPhong dp ON dp.MaDP = dvdt.MaDP
    WHERE 
        MONTH(dp.NgayDen) = @Thang 
        AND YEAR(dp.NgayDen) = @Nam
    GROUP BY
        dv.MaDV, dv.TenDV, dv.Gia;
END
exec sp_DoanhThuDichVu 11, 2022