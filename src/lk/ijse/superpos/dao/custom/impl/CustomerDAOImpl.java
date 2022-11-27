/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.superpos.dao.CrudUtil;
import lk.ijse.superpos.entity.Customer;
import lk.ijse.superpos.dao.custom.CustomerDAO;


public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Customer getCustomerByName(String name) {
        try {
            ResultSet rst = CrudUtil.executeQuery("select * from Customer where name=?", name);
            Customer cus = null;
            while (rst.next()) {
                cus = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), Double.parseDouble(rst.getString(4)));
            }
            return cus;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean add(Customer entity) {
        try {
            String sql = "Insert into Customer values(?,?,?,?)";
            int i = CrudUtil.executeUpdate(sql, entity.getId_PK(), entity.getName(), entity.getAddress(), entity.getSalary());
            if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public boolean delete(String id) {
        try {
            return CrudUtil.executeUpdate("DELETE FROM Customer WHERE id=?", id) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Customer entity) {
        try {
            String sql = "update Customer set name=?,address=?,salary=? where id=?";
            int i = CrudUtil.executeUpdate(sql, entity.getName(), entity.getAddress(), entity.getSalary(), entity.getId_PK());
            if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Customer Serch(String id) {
        try {
            ResultSet rst = CrudUtil.executeQuery("select * from Customer where id=?", id);
            Customer cus = null;
            while (rst.next()) {
                cus = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), Double.parseDouble(rst.getString(4)));
            }
            return cus;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() {
        try {
            ResultSet rst = CrudUtil.executeQuery("select * from Customer");
            ArrayList<Customer> cusAll = new ArrayList<>();
            while (rst.next()) {
                Customer cus = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), Double.parseDouble(rst.getString(4)));
                cusAll.add(cus);
            }
            return cusAll;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
