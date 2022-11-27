/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.dao;

import java.util.ArrayList;


public interface CrudDAO<T, ID> extends SuperDAO{

    public boolean add(T entity);

    public boolean delete(ID id);

    public boolean update(T entity);

    public T Serch(ID id);

    public ArrayList<T> getAll();
}
