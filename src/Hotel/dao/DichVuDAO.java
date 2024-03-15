/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.dao;

import Hotel.entity.DichVu;
import Hotel.entity.KhachHang;
import Hotel.entity.NhanVien;
import Hotel.utils.JdbcHelPer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DichVuDAO {

    public List<DichVu> select() {
        String sql = "SELECT * FROM DichVu";
        return select(sql);
    }

    public DichVu findById(String tenDV) {
        String sql = "SELECT * FROM DichVu WHERE TenDV=?";
        List<DichVu> list = select(sql, tenDV);
        return list.size() > 0 ? list.get(0) : null;

    }

    private List<DichVu> select(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelPer.executeQuery(sql, args);
                while (rs.next()) {
                    DichVu model = readFromResultSet(rs);
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

    private DichVu readFromResultSet(ResultSet rs) throws SQLException {
        DichVu model = new DichVu();
        model.setMaDV(rs.getString("MaDV"));
        model.setTenDV(rs.getString("TenDV"));
        model.setDonGia(rs.getFloat("Gia"));

        return model;

    }
}
