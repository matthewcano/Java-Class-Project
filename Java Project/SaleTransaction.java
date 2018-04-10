/*
 * Car Dealership Managment Software v0.1 
 * Developed for CS3354: Object Oriented Design and Programming.
 * Copyright: Vangelis Metsis (vmetsis@txstate.edu)
 */

 

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents a sale transaction in the Car Dealership Software.
 * @author vangelis
 */
public class SaleTransaction implements Serializable {
    private int customerId;
    private int employeeId;
    private String vin;
    private Date date;
    private float salePrice;

    /**
     * Constructor initializes a SaleTransaction object with the provided values.
     * @param customerId
     * @param employeeId
     * @param vin
     * @param date
     * @param salePrice
     */
    public SaleTransaction(int customerId, int employeeId, String vin, 
            Date date, float salePrice) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.vin = vin;
        this.date = date;
        this.salePrice = salePrice;
    }

    /**
     * Get the customer ID of this transaction.
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Set the customer ID of this transaction.
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Get the employee ID of this transaction.
     * @return
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Set the employee ID of this transaction.
     * @param employeeId
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Get the vehicle VIN of this transaction.
     * @return
     */
    public String getVin() {
        return vin;
    }

    /**
     * Set the vehicle VIN of this transaction.
     * @param vin
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Get the transaction date of this transaction.
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the transaction date of this transaction.
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the sale price of this transaction.
     * @return
     */
    public float getSalePrice() {
        return salePrice;
    }

    /**
     * Set the sale price of this transaction.
     * @param salePrice
     */
    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return "SaleTransaction{" + "customerId=" + customerId + ", employeeId=" 
                + employeeId + ", vin=" + vin + ", date=" + date + ", salePrice=" 
                + salePrice + '}';
    }
}
