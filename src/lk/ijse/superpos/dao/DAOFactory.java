/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.dao;

import lk.ijse.superpos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.superpos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.superpos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.superpos.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.superpos.dao.custom.impl.QueryDAOImpl;


public class DAOFactory {

    private static DAOFactory daoFac;

    public static DAOFactory getDAOfac() {
        if (daoFac == null) {
            daoFac = new DAOFactory();
        }
        return daoFac;
    }

    public enum DAOFactypes {
        CUSTOMER, ITEM, ORDER, ORDERDETAILS, QUERYDAO;
    }

    public SuperDAO getDAO(DAOFactypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case QUERYDAO:
                return new QueryDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }
}
