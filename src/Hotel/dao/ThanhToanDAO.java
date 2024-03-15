/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.dao;

import Hotel.entity.HoaDonThanhToan;
import Hotel.utils.JdbcHelPer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ThanhToanDAO {
    public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT YEAR(NgayLap) AS Year "
                + "FROM hoaDonThanhToan "
                + "ORDER BY Year DESC;";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelPer.executeQuery(sql);
            while (rs.next()) {
                int year = rs.getInt("Year");
                list.add(year);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<HoaDonThanhToan> selectHoaDons() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
