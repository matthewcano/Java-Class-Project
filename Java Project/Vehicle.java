/*
 * Car Dealership Managment Software v0.1 
 * Developed for CS3354: Object Oriented Design and Programming.
 * Copyright: Vangelis Metsis (vmetsis@txstate.edu)
 */

 

import java.io.Serializable;

/**
 * Class Vehicle is an abstract entity class that represents a Vehicle in the 
 * dealership inventory. This class is expected to be inherited by subclasses
 * that specify the vehicle type. No instance of this class can be created.
 * 
 * @author vangelis
 */
public abstract class Vehicle implements Serializable {

    /**
     * The Vehicle Identification Number (VIN) of the vehicle.
     */
    protected String vin;

    /**
     * The make of the vehicle.
     */
    protected String make;

    /**
     * The model of the vehicle.
     */
    protected String model;
    
    /**
     * The production year of the vehicle.
     */
    protected int year;

    /**
     * The mileage counter of the vehicle.
     */
    protected int mileage;

    /**
     * The sale price of the vehicle.
     */
    protected float price;

    /**
     * Default constructor used to initialize the class fields of the class. 
     * Since this is an abstract class, the constructor cannot be used to 
     * instantiate objects object of the class.
     */
    protected Vehicle() {
        this.vin = "";
        this.make = "";
        this.model = "";
        this.year = 0;
        this.mileage = 0;
        this.price = 0.0f;
    }

    /**
     * Constructor used to initialize the class fields of the class. Since this
     * is an abstract class, the constructor cannot be used to instantiate 
     * objects object of the class.
     * @param vin The Vehicle Identification Number (VIN) of the vehicle.
     * @param make The make of the vehicle.
     * @param model The model of the vehicle.
     * @param year The production year of the vehicle.
     * @param mileage The mileage counter of the vehicle.
     * @param price The sale price of the vehicle.
     */
    protected Vehicle(String vin, String make, String model, int year, 
            int mileage, float price) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    /**
     * Get the VIN of the vehicle.
     * @return vin The VIN of the vehicle.
     */
    public String getVin() {
        return vin;
    }

    /**
     * Set the VIN of the vehicle.
     * @param vin
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Get the make of the vehicle.
     * @return
     */
    public String getMake() {
        return make;
    }

    /**
     * Set the make of the vehicle.
     * @param make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Get the model of the vehicle.
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the model of the vehicle.
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get the year of the vehicle.
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year of the vehicle.
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get the mileage of the vehicle.
     * @return
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * Set the mileage of the vehicle.
     * @param mileage
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * Get the price of the vehicle.
     * @return
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set the price of the vehicle.
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }
    
    /**
     * Abstract method, to be implemented by subclasses of class Vehicle.
     */
    public abstract String getFormattedText();
}
