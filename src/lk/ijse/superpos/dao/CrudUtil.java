/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lk.ijse.superpos.db.DBConnection;


public class CrudUtil {

    public static PreparedStatement getPreaparedStatement(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
            pstm.setObject(i + 1, obj[i]);
        }
        return pstm;
    }

    public static ResultSet executeQuery(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        return getPreaparedStatement(sql, obj).executeQuery();
    }

    public static int executeUpdate(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        return getPreaparedStatement(sql, obj).executeUpdate();

    }

}
