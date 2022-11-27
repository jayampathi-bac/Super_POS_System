/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.entity;


public class Customer {

    private String id_PK;
    private String name;
    private String address;
    private double salary;

    public Customer() {
    }

    public Customer(String id_PK, String name, String address, double salary) {
        this.id_PK = id_PK;
        this.name = name;
        this.address = address;
        this.salary = salary;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    

}
