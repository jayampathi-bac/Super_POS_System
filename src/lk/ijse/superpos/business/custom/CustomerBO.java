/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.business.custom;

import java.util.ArrayList;
import lk.ijse.superpos.business.SuperBO;
import lk.ijse.superpos.model.CustomDTO;
import lk.ijse.superpos.model.CustomerDTO;


public interface CustomerBO extends SuperBO{
    public boolean addCustomer(CustomerDTO dto)throws Exception;
    public boolean deleteCustomer(String id)throws Exception;
    public CustomerDTO serchCustomer(String id)throws Exception;
    public ArrayList<CustomerDTO> getAllCustomers()throws Exception;
    
    public ArrayList<CustomDTO> getAllOrdersAndCustomers()throws Exception;
    
}
