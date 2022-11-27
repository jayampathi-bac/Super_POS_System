/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.entity;


public class Item {
    private String code_PK;
    private String description;
    private int qtyOnhand;
    private double unitPrice;

    public Item() {
    }

    /**
     * @return the code_PK
     */
    public String getCode_PK() {
        return code_PK;
    }

    /**
     * @param code_PK the code_PK to set
     */
    public void setCode_PK(String code_PK) {
        this.code_PK = code_PK;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the qtyOnhand
     */
    public int getQtyOnhand() {
        return qtyOnhand;
    }

    /**
     * @param qtyOnhand the qtyOnhand to set
     */
    public void setQtyOnhand(int qtyOnhand) {
        this.qtyOnhand = qtyOnhand;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

   
}
