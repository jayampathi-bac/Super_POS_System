/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.dao.custom;

import lk.ijse.superpos.dao.CrudDAO;
import lk.ijse.superpos.entity.Item;


public interface ItemDAO extends CrudDAO<Item, String> {
    //    void getAllItems();
    boolean updateItemQtyOnHand(String code, int qtyOnHand) throws Exception;
}
