/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.business.custom;

import java.util.ArrayList;
import lk.ijse.superpos.model.OrderDTO;
import lk.ijse.superpos.model.OrderDetailsDTO;


public interface PlaceOrderBO {
    public boolean purchaseOrder(OrderDTO dto,ArrayList<OrderDetailsDTO> allOrders)throws Exception;
}
