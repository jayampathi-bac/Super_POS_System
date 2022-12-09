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
import lk.ijse.superpos.entity.CustomEntity;
import lk.ijse.superpos.dao.custom.QueryDAO;


public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomEntity> getCustoemrsOrders() {
        try {
            String sql = "select customer.id,"
                    + "customer.name,"
                    + "customer.address,"
                    + "customer.salary,"
                    + "orders.id,"
                    + "orders.date from customer"
                    + " inner join orders"
                    + " on customer.id=orders.customerID;";
            ResultSet rst = CrudUtil.executeQuery(sql);
            ArrayList<CustomEntity> allCustomers = new ArrayList<>();
            while (rst.next()) {
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
                double salary = Double.parseDouble(rst.getString(4));
                String orderid = rst.getString(5);
                String date = rst.getString(6);
                CustomEntity dto = new CustomEntity(id, name, address, salary, orderid, date);
                allCustomers.add(dto);
            }

            return allCustomers;
        } catch (SQLException ex) {
            Logger.getLogger(QueryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
