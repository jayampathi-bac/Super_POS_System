/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.entity;


public class OrderDetails {
    private String orderID_PK;
    private String itemCode_PK;
    private int qty;
    private double unitPrice;

    public OrderDetails() {
    }

    /**
     * @return the orderID_PK
     */
    public String getOrderID_PK() {
        return orderID_PK;
    }

    /**
     * @param orderID_PK the orderID_PK to set
     */
    public void setOrderID_PK(String orderID_PK) {
        this.orderID_PK = orderID_PK;
    }

    /**
     * @return the itemCode_PK
     */
    public String getItemCode_PK() {
        return itemCode_PK;
    }

    /**
     * @param itemCode_PK the itemCode_PK to set
     */
    public void setItemCode_PK(String itemCode_PK) {
        this.itemCode_PK = itemCode_PK;
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
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
