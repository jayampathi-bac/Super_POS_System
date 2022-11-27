/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.service;

import lk.ijse.superpos.service.custom.impl.CustomerBOImpl;
import lk.ijse.superpos.service.custom.impl.ItemBOImpl;
import lk.ijse.superpos.service.custom.impl.OrderBOImpl;
import lk.ijse.superpos.service.custom.impl.OrderDetailsBOImpl;


public class BOFactory {

    private static BOFactory boFactory;

    public enum BOFacTypes {
        CUSTOMER, ITEM, ORDERS, ORDERDETAILS;
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOFacTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDERS:
                return new OrderBOImpl();
            case ORDERDETAILS:
                return new OrderDetailsBOImpl();
            default:
                return null;
        }
    }
}
