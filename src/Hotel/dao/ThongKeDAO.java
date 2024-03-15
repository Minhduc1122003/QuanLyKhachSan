/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.dao;

import Hotel.utils.JdbcHelPer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ThongKeDAO {
     private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list=new ArrayList<>();
            ResultSet rs = JdbcHelPer.executeQuery(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i=0; i<cols.length; i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Object[]> getDoanhThuPhong(int thang, int nam){
        String sql = "{CALL sp_DoanhThuPhong (?, ?)}";
        String[] cols = {"STT", "MaPhong", "PhuThu", "ThanhTien"};
        return this.getListOfArray(sql, cols,thang, nam);
    }
    public List<Object[]> getDoanhThuDV(int thang, int nam){
        String sql = "{CALL sp_DoanhThuDichVu (?, ?)}";
        String[] cols = {"STT", "MaDV", "TenDV",  "Số lượng", "Gia", "Thành Tiền"};
        return this.getListOfArray(sql, cols, thang, nam);
    }
}
