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

    /**
     * @return the id_PK
     */
    public String getId_PK() {
        return id_PK;
    }

    /**
     * @param id_PK the id_PK to set
     */
    public void setId_PK(String id_PK) {
        this.id_PK = id_PK;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the customer_ID_FK
     */
    public String getCustomer_ID_FK() {
        return customer_ID_FK;
    }

    /**
     * @param customer_ID_FK the customer_ID_FK to set
     */
    public void setCustomer_ID_FK(String customer_ID_FK) {
        this.customer_ID_FK = customer_ID_FK;
    }

    
    
}
