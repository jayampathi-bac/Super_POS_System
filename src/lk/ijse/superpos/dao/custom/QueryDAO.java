/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.dao.custom;

import java.util.ArrayList;
import lk.ijse.superpos.dao.SuperDAO;
import lk.ijse.superpos.entity.CustomEntity;


public interface QueryDAO extends SuperDAO{
      public ArrayList<CustomEntity> getCustoemrsOrders();
}
