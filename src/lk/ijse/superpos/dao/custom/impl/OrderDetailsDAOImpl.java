/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.dao.custom.impl;

import lk.ijse.superpos.dao.CrudUtil;
import lk.ijse.superpos.dao.custom.OrderDetailsDAO;
import lk.ijse.superpos.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean add(OrderDetails entity) {
        try {
            String sql = "INSERT INTO OrderDetails VALUES (?,?,?,?)";
            int i = CrudUtil.executeUpdate(sql, entity.getOrderID_PK(), entity.getItemCode_PK(), entity.getQty(), entity.getUnitPrice());
            if (i > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(OrderDetails entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderDetails Serch(String id) {
        try {
            ResultSet rst = CrudUtil.executeQuery("select * from OrderDetails where orderId=?", Integer.parseInt(id));
            OrderDetails od = null;
            while (rst.next()) {
                od = new OrderDetails(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDouble(4));
            }
            return od;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() {
        try {
            ResultSet rst = CrudUtil.executeQuery("select * from OrderDetails");
            ArrayList<OrderDetails> odAll = new ArrayList<>();
            while (rst.next()) {
                OrderDetails od = new OrderDetails(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDouble(4));
                odAll.add(od);
            }
            return odAll;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
