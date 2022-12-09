/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.dao.custom.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import lk.ijse.superpos.dao.CrudUtil;
import lk.ijse.superpos.dao.custom.OrderDAO;
import lk.ijse.superpos.entity.Item;
import lk.ijse.superpos.entity.Orders;


public class OrderDAOImpl implements OrderDAO{

    @Override
    public boolean add(Orders orders) {
        try {
            String sql = "INSERT INTO Orders VALUES (?,?,?)";
            int i = CrudUtil.executeUpdate(sql, orders.getId_PK(), orders.getDate(), orders.getCustomer_ID_FK());
            if (i > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Orders entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orders Serch(String id) {
        try {
            ResultSet rst = CrudUtil.executeQuery("select * from Orders where id=?", Integer.parseInt(id));
            Orders order = null;
            while (rst.next()) {
                order = new Orders(rst.getString(1),rst.getString(2),rst.getString(3));
            }
            return order;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Orders> getAll() {
        try {
            ResultSet rst = CrudUtil.executeQuery("select * from Orders");
            ArrayList<Orders> itmAll = new ArrayList<>();
            while (rst.next()) {
                Orders order = new Orders(rst.getString(1),rst.getString(2),rst.getString(3));
                itmAll.add(order);
            }
            return itmAll;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
