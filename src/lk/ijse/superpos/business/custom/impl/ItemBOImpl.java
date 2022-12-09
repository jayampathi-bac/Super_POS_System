/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.business.custom.impl;

import lk.ijse.superpos.business.custom.ItemBO;
import lk.ijse.superpos.dao.DAOFactory;
import lk.ijse.superpos.dao.custom.ItemDAO;
import lk.ijse.superpos.dao.custom.QueryDAO;
import lk.ijse.superpos.entity.Item;
import lk.ijse.superpos.model.ItemDTO;

import java.util.ArrayList;


public class ItemBOImpl implements ItemBO {

    private final ItemDAO itemDAO;

    private final QueryDAO queryDAO;

    public ItemBOImpl() {
        itemDAO = (ItemDAO) DAOFactory.getDAOfac().getDAO(DAOFactory.DAOFactypes.ITEM);
        queryDAO = (QueryDAO) DAOFactory.getDAOfac().getDAO(DAOFactory.DAOFactypes.QUERYDAO);
    }


    @Override
    public boolean addItem(ItemDTO dto) throws Exception {
        Item item = new Item(dto.getCode(), dto.getDescription(), dto.getQty(), dto.getUnitPrice());
        return itemDAO.add(item);
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDAO.delete(id);
    }

    @Override
    public boolean updateItem(ItemDTO item) throws Exception {
        return itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getQty(), item.getUnitPrice()));
    }

    @Override
    public ItemDTO searchItem(String id) throws Exception {
        Item itm = itemDAO.Serch(id);
        return new ItemDTO(itm.getCode_PK(), itm.getDescription(), itm.getQtyOnHand(), itm.getUnitPrice());
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws Exception {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for (Item item : all) {
            ItemDTO dto = new ItemDTO(item.getCode_PK(), item.getDescription(), item.getQtyOnHand(), item.getUnitPrice());
            allItems.add(dto);
        }
        return allItems;
    }

}
