/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.dao;

import Hotel.entity.indentity;
import Hotel.utils.JdbcHelPer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class identityDAO {

    public indentity indentity() {
        String sql = "SELECT IDENT_CURRENT('DatPhong') as 'maphieu'";
        List<indentity> list = select(sql);
        return list.size() > 0 ? list.get(0) : null;

    }

    private List<indentity> select(String sql, Object... args) {
        List<indentity> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelPer.executeQuery(sql, args);
                while (rs.next()) {
                    indentity model = readFromResultSet(rs);
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

    private indentity readFromResultSet(ResultSet rs) throws SQLException {
        indentity model = new indentity();
        model.setIndentity(rs.getInt("maphieu"));

        return model;

    }

}
