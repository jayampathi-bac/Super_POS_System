/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.dao.custom.impl;

import lk.ijse.superpos.dao.CrudUtil;
import lk.ijse.superpos.dao.custom.ItemDAO;
import lk.ijse.superpos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(Item entity) {
        try {
            String sql = "Insert into Item values(?,?,?,?)";
            int i = CrudUtil.executeUpdate(sql, entity.getCode_PK(), entity.getDescription(), entity.getQtyOnHand(), entity.getUnitPrice());
            if (i > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String code) {
        try {
            return CrudUtil.executeUpdate("DELETE FROM Item WHERE code=?", Integer.parseInt(code)) > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Item entity) {
        try {
            String sql = "update Item set description=?,qtyOnHand=?,unitPrice=? where code=?";
            int i = CrudUtil.executeUpdate(sql, entity.getDescription(), entity.getQtyOnHand(), entity.getUnitPrice(), entity.getCode_PK());
            if (i > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Item Serch(String code) {
        try {
            ResultSet rst = CrudUtil.executeQuery("select * from Item where code=?", Integer.parseInt(code));
            Item itm = null;
            while (rst.next()) {
                itm = new Item(rst.getString(1), rst.getString(2), Integer.parseInt(rst.getString(3)), Double.parseDouble(rst.getString(4)));
            }
            return itm;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAll() {
        try {
            ResultSet rst = CrudUtil.executeQuery("select * from Item");
            ArrayList<Item> itmAll = new ArrayList<>();
            while (rst.next()) {
                Item itm = new Item(rst.getString(1), rst.getString(2), Integer.parseInt(rst.getString(3)), Double.parseDouble(rst.getString(4)));
                itmAll.add(itm);
            }
            return itmAll;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean updateItemQtyOnHand(String code, int qtyOnHand) throws Exception {
        try {
            int i = CrudUtil.executeUpdate("UPDATE Item SET qtyOnHand=? WHERE code=?", qtyOnHand, code);
            if (i > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    @Override
//    public void getAllItems() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
