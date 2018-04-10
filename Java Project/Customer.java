/*
 * Car Dealership Managment Software v0.1 
 * Developed for CS3354: Object Oriented Design and Programming.
 * Copyright: Vangelis Metsis (vmetsis@txstate.edu)
 */

 

/**
 *
 * @author vangelis
 */
public class Customer extends User {
    private String phoneNumber;
    private int driverLicenceNumber;

    /**
     * Default constructor.
     */
    public Customer() {
        this.phoneNumber = "";
        this.driverLicenceNumber = 0;
    }

    /**
     * Constructor initializes a customer object with the provided values.
     * @param id
     * @param phoneNumber
     * @param driverLicenceNumber
     * @param firstName
     * @param lastName
     */
    public Customer(int id, String firstName, String lastName, String phoneNumber, int driverLicenceNumber) {
        super(id, firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.driverLicenceNumber = driverLicenceNumber;
    }

    /**
     * Get the phone number.
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number.
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the driver license number.
     * @return driverLicenceNumber
     */
    public int getDriverLicenceNumber() {
        return driverLicenceNumber;
    }

    /**
     * Set the driver license number.
     * @param driverLicenceNumber
     */
    public void setDriverLicenceNumber(int driverLicenceNumber) {
        this.driverLicenceNumber = driverLicenceNumber;
    }
    
    /**
     * Returns the attributes of the customer, in a formatted text fashion.
     * @return Formated Text.
     */
    public String getFormattedText() {
        return String.format("| %10s | %9d | %12s | %12s | Ph#: %12s, DL#:%11d | %n", 
                "Customer", id, firstName, lastName, phoneNumber, driverLicenceNumber);
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName 
                + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber 
                + ", driverLicenceNumber=" + driverLicenceNumber + '}';
    }
}
