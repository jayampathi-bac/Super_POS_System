/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import lk.ijse.superpos.business.custom.PlaceOrderBO;
import lk.ijse.superpos.dao.DAOFactory;
import lk.ijse.superpos.dao.custom.OrderDAO;
import lk.ijse.superpos.dao.custom.OrderDetailsDAO;
import lk.ijse.superpos.db.DBConnection;
import lk.ijse.superpos.entity.OrderDetails;
import lk.ijse.superpos.entity.Orders;
import lk.ijse.superpos.model.OrderDTO;
import lk.ijse.superpos.model.OrderDetailsDTO;


public class PlaceOrderBOImpl implements PlaceOrderBO {
    
    OrderDAO order;
    OrderDetailsDAO orderDetails;
    
    public PlaceOrderBOImpl() {
        order = (OrderDAO) DAOFactory.getDAOfac().getDAO(DAOFactory.DAOFactypes.ORDER);
        orderDetails = (OrderDetailsDAO) DAOFactory.getDAOfac().getDAO(DAOFactory.DAOFactypes.ORDERDETAILS);
    }
    
    @Override
    public boolean purchaseOrder(OrderDTO dto, ArrayList<OrderDetailsDTO> allOrders) throws Exception {
        Connection connection = DBConnection.getDBConnection().getConnection();
        connection.setAutoCommit(false);
        
        Orders orderTemp = new Orders();
        orderTemp.setCustomer_ID_FK(dto.getCustomerID());
        orderTemp.setDate(dto.getDate());
        orderTemp.setId_PK(dto.getId());
        boolean add = order.add(orderTemp);
        if (add) {
            
            for (OrderDetailsDTO allOrder : allOrders) {
                
                OrderDetails tempOrderDet = new OrderDetails();
                tempOrderDet.setItemCode_PK(allOrder.getItemCode());
                tempOrderDet.setOrderID_PK(allOrder.getOrderID());
                tempOrderDet.setQty(allOrder.getQty());
                tempOrderDet.setUnitPrice(allOrder.getUnitPrice());
                boolean add1 = orderDetails.add(tempOrderDet);
                if (add1) {
                    connection.commit();
                    connection.setAutoCommit(true);
                }
                
                connection.rollback();
                connection.setAutoCommit(false);
                
            }
            
        } else {
            connection.rollback();
            connection.setAutoCommit(true);
        }
        
        return false;
    }
    
}
