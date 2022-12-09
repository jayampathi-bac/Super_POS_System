/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.business.custom;

import java.util.ArrayList;
import lk.ijse.superpos.business.SuperBO;
import lk.ijse.superpos.model.ItemDTO;


public interface ItemBO extends SuperBO{

    public boolean addItem(ItemDTO dto) throws Exception;

    public boolean deleteItem(String id) throws Exception;

    public ItemDTO serchItem(String id) throws Exception;

    public ArrayList<ItemDTO> getAllItem() throws Exception;
    
  
}
