/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.dao;

import Hotel.entity.DSPhong;
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
public class DSPhongDAo {

    public List<DSPhong> select() {
        String sql = "SELECT * FROM DSPhong";
        return select(sql);
    }

    public DSPhong findById(String maphong) {
        String sql = "SELECT * FROM DSPhong WHERE MaPhong=?";
        List<DSPhong> list = select(sql, maphong);
        return list.size() > 0 ? list.get(0) : null;

    }
    public void update(DSPhong model) {
        String sql = "UPDATE DSPhong SET TrangThai=? WHERE MaPhong=?";
        JdbcHelPer.executeUpdate(sql,
                model.getTrangThai(),
                model.getMaPhong());
    }

    private List<DSPhong> select(String sql, Object... args) {
        List<DSPhong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelPer.executeQuery(sql, args);
                while (rs.next()) {
                    DSPhong model = readFromResultSet(rs);
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

    private DSPhong readFromResultSet(ResultSet rs) throws SQLException {
        DSPhong model = new DSPhong();
        model.setMaPhong(rs.getString("MaPhong"));
        model.setTenPhong(rs.getString("TenPhong"));
        model.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
        model.setTrangThai(rs.getString("TrangThai"));
        return model;

    }
}
