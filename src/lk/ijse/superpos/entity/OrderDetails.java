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

    public OrderDetails(String orderID_PK, String itemCode_PK, int qty, double unitPrice) {
        this.orderID_PK = orderID_PK;
        this.itemCode_PK = itemCode_PK;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOrderID_PK() {
        return orderID_PK;
    }

    public void setOrderID_PK(String orderID_PK) {
        this.orderID_PK = orderID_PK;
    }

    public String getItemCode_PK() {
        return itemCode_PK;
    }

    public void setItemCode_PK(String itemCode_PK) {
        this.itemCode_PK = itemCode_PK;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderID_PK='" + orderID_PK + '\'' +
                ", itemCode_PK='" + itemCode_PK + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
