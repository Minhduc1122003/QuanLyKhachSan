/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.dao;

import Hotel.entity.DSBanBe;
import Hotel.entity.DSPhong;
import Hotel.utils.JdbcHelPer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DSBanBeDAO {
    
    public List<DSBanBe> select() {
        String sql = "SELECT * FROM DSPhong";
        return select(sql);
    }
    
    public List<DSBanBe> findById(String MaNV) {
        String sql = "SELECT * FROM DSbanbe WHERE MaNV=?";
        return select(sql, MaNV);
        
    }
    
    private List<DSBanBe> select(String sql, Object... args) {
        List<DSBanBe> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelPer.executeQuery(sql, args);
                while (rs.next()) {
                    DSBanBe model = readFromResultSet(rs);
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
    
    private DSBanBe readFromResultSet(ResultSet rs) throws SQLException {
        DSBanBe model = new DSBanBe();
        model.setMaDS(rs.getInt("MaDS"));
        model.setMaNV(rs.getString("MaNV"));
        model.setBanBe(rs.getBoolean("BanBe"));
        model.setMaNVKB(rs.getString("MaNVKB"));
        return model;
        
    }
}
