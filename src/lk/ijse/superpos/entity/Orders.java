/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.entity;


public class Orders {
    private String id_PK;
    private String date;
    private String customer_ID_FK;

    public Orders() {
    }

    public Orders(String id_PK, String date, String customer_ID_FK) {
        this.id_PK = id_PK;
        this.date = date;
        this.customer_ID_FK = customer_ID_FK;
    }

    public String getId_PK() {
        return id_PK;
    }

    public void setId_PK(String id_PK) {
        this.id_PK = id_PK;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer_ID_FK() {
        return customer_ID_FK;
    }

    public void setCustomer_ID_FK(String customer_ID_FK) {
        this.customer_ID_FK = customer_ID_FK;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id_PK='" + id_PK + '\'' +
                ", date='" + date + '\'' +
                ", customer_ID_FK='" + customer_ID_FK + '\'' +
                '}';
    }
}
