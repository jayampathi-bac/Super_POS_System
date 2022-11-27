/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.dao.custom.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.superpos.dao.CrudUtil;
import lk.ijse.superpos.dao.custom.ItemDAO;
import lk.ijse.superpos.entity.Item;


public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(Item entity) {
        try {
            String sql = "Insert into Item values(?,?,?,?)";
            int i = CrudUtil.executeUpdate(sql, entity.getCode_PK(), entity.getDescription(), entity.getQtyOnhand(), entity.getUnitPrice());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Item entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item Serch(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Item> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getAllItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
