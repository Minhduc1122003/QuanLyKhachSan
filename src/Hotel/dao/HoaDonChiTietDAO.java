/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.dao;

import Hotel.entity.HoaDonChiTiet;
import Hotel.utils.JdbcHelPer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietDAO {

    public void insert(HoaDonChiTiet model) {
        String sql = "INSERT INTO HoaDonChiTiet (MaHD, MaDV, Ngaythue, PhuThu) VALUES (?,?,?,?)";
        JdbcHelPer.executeUpdate(sql,
                model.getMaHD(),
                model.getMaDV(),
                model.getNgaythue(),
                model.getPhuThu()
        );
    }

    public List<HoaDonChiTiet> select() {
        String sql = "SELECT * FROM HoaDonChiTiet";
        return select(sql);
    }

    public HoaDonChiTiet findById(String maHD) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHD=?";
        List<HoaDonChiTiet> list = select(sql, maHD);
        return !list.isEmpty() ? list.get(0) : null;

    }

    public void delete(String MaHD) {
        String sql = "DELETE FROM HoaDonChiTiet WHERE MaHD=?";
        JdbcHelPer.executeUpdate(sql, MaHD);
    }

    private List<HoaDonChiTiet> select(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelPer.executeQuery(sql, args);
                while (rs.next()) {
                    HoaDonChiTiet model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private HoaDonChiTiet readFromResultSet(ResultSet rs) throws SQLException {
        HoaDonChiTiet model = new HoaDonChiTiet();
        model.setMaHD(rs.getString("MaHD"));
        model.setMaDV(rs.getString("MaDV"));
        model.setNgaythue(rs.getString("Ngaythue"));
        model.setPhuThu(rs.getFloat("PhuThu"));

        return model;

    }

}
