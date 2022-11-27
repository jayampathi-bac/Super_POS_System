/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.service.custom.impl;

import java.util.ArrayList;
import lk.ijse.superpos.service.custom.CustomerBO;
import lk.ijse.superpos.dao.DAOFactory;
import lk.ijse.superpos.dao.custom.CustomerDAO;
import lk.ijse.superpos.dao.custom.QueryDAO;
import lk.ijse.superpos.entity.CustomEntity;
import lk.ijse.superpos.entity.Customer;
import lk.ijse.superpos.model.CustomDTO;
import lk.ijse.superpos.model.CustomerDTO;


public class CustomerBOImpl implements CustomerBO {

    CustomerDAO dao;
    QueryDAO qdao;

    public CustomerBOImpl() {
        dao = (CustomerDAO) DAOFactory.getDAOfac().getDAO(DAOFactory.DAOFactypes.CUSTOMER);
        qdao = (QueryDAO) DAOFactory.getDAOfac().getDAO(DAOFactory.DAOFactypes.QUERYDAO);
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws Exception {
        Customer customer = new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary());
        return dao.add(customer);
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public CustomerDTO serchCustomer(String id) throws Exception {
        Customer Serch = dao.Serch(id);
        CustomerDTO dto = new CustomerDTO(Serch.getId_PK(), Serch.getName(), Serch.getAddress(), Serch.getSalary());
        return dto;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<Customer> all = dao.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer customer : all) {
            CustomerDTO dto = new CustomerDTO(customer.getId_PK(), customer.getName(), customer.getAddress(), customer.getSalary());
            allCustomers.add(dto);
        }
        return allCustomers;
    }

    @Override
    public ArrayList<CustomDTO> getAllOrdersAndCustomers() throws Exception {
        ArrayList<CustomEntity> custoemrsOrders = qdao.getCustoemrsOrders();
        ArrayList<CustomDTO> allCustomerAndOrders = new ArrayList<>();
        for (CustomEntity custoemrsOrder : custoemrsOrders) {
            CustomDTO dto = new CustomDTO(custoemrsOrder.getId(), custoemrsOrder.getName(), custoemrsOrder.getAddress(), custoemrsOrder.getSalary(), custoemrsOrder.getDate(), custoemrsOrder.getOrderid());
            allCustomerAndOrders.add(dto);
        }
        return allCustomerAndOrders;

    }

}
