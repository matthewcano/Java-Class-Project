/*
 * Car Dealership Managment Software v0.1 
 * Developed for CS3354: Object Oriented Design and Programming.
 * Copyright: Vangelis Metsis (vmetsis@txstate.edu)
 */

 

/**
 *
 * @author vangelis
 */
public class Truck extends Vehicle {
    /**
     * The maximum allowed load weight of the truck.
     */
    private float maxLoadWeight;
    
    /**
     * The length of the truck (in feet).
     */
    private float length;

    /**
     *
     */
    public Truck() {
        this.maxLoadWeight = 0.0f;
        this.length = 0.0f;
    }

    /**
     * Constructor that initializes a Truck object with the given params.
     * @param vin
     * @param make
     * @param model
     * @param year
     * @param mileage
     * @param price
     * @param maxLoadWeight
     * @param length
     */
    public Truck(String vin, String make, String model, int year, int mileage, 
            float price, float maxLoadWeight, float length) {
        super(vin, make, model, year, mileage, price);
        this.maxLoadWeight = maxLoadWeight;
        this.length = length;
    }

    /**
     *
     * @return
     */
    public float getMaxLoadWeight() {
        return maxLoadWeight;
    }

    /**
     *
     * @param maxLoadWeight
     */
    public void setMaxLoadWeight(float maxLoadWeight) {
        this.maxLoadWeight = maxLoadWeight;
    }

    /**
     *
     * @return
     */
    public float getLength() {
        return length;
    }

    /**
     *
     * @param length
     */
    public void setLength(float length) {
        this.length = length;
    }
    
    /**
     * Get the attributes of the truck, in a formatted text fashion.
     * @return Formatted text
     */
    @Override
    public String getFormattedText() {
        return String.format("| %12s | %12s | %8s | %8s | %6d | %9d | %10.2f | %7.2flb %5.2fft | %n", 
                "Truck", vin, make, model, year, mileage, price, maxLoadWeight, length);
    }

    @Override
    public String toString() {
        return "Truck{" + "vin=" + vin + ", make=" + make + ", model=" + model +
                ", year=" + year + ", mileage=" + mileage + ", price=" + price +
                ", maximumLoadWeight=" + maxLoadWeight + 
                ", length=" + length + '}';
    }
}
