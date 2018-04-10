

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class contains the remaining methods needed to tie all the Dealership Database wit the GUI main
 * 
 * @author Christopher Henry (C_H255), Matthew Cano (MTC54)
 * @version 11/20/2016
 */
public class Dealership {
    public List<Vehicle> vehicleInventory; 
    public List<User> users;
    public List<SaleTransaction> transactions;
    
    private int userIdCounter = 1;
    private final Scanner sc; // Used to read from System's standard input

    /**
     * Default constructor. Initializes the inventory, users, and transactions
     * tables.
     */
    public Dealership() {
        this.vehicleInventory = new ArrayList<Vehicle>();
        this.users = new ArrayList<User>();
        this.transactions = new ArrayList<SaleTransaction>();
        this.sc = new Scanner(System.in);
    }
    
        /**
     * Constructor. Initializes the inventory, users, and transactions to given values.
     */
    public Dealership(List<Vehicle> vehicleInventory, List<User> users, List<SaleTransaction> transactions) {
        this.vehicleInventory = vehicleInventory;
        this.users = users;
        this.transactions = transactions;
        this.sc = new Scanner(System.in);
    }
    
    /**
     * Auxiliary method used to find a vehicle in the database, given its
     * VIN number.
     * @param vin
     * @return The vehicle found, or otherwise null.
     */
    public Vehicle findVehicle(String vin) {
        for (Vehicle v : vehicleInventory) {
            if (v.getVin().equals(vin))
                return v;
        }
        return null;
    }
    
    /**
     * This method is used to read the database from a file, serializable objects.
     * @return A new Dealership object.
     */
    @SuppressWarnings("unchecked") // This will prevent Java unchecked operation warning when
    // convering from serialized Object to Arraylist<>
    public static Dealership readDatabase() {
        
        Dealership cds = null;

        // Try to read existing dealership database from a file
        InputStream file = null;
        InputStream buffer = null;
        ObjectInput input = null;
        try {
            file = new FileInputStream("Dealership.ser");
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);
            
            // Read serilized data
            List<Vehicle> vehicleInventory = (ArrayList<Vehicle>) input.readObject();
            List<User> users = (ArrayList<User>) input.readObject();
            List<SaleTransaction> transactions = (ArrayList<SaleTransaction>) input.readObject();
            cds = new Dealership(vehicleInventory, users, transactions);
            cds.userIdCounter = input.readInt();
            
            input.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.toString());
        } catch (FileNotFoundException ex) {
            System.err.println("Database file not found.");
        } catch (IOException ex) {
            System.err.println(ex.toString());
        } finally {
            close(file);
        }
        
        return cds;
    }
    
    /**
     * This method is used to save the Dealership database as a 
     * serializable object.
     * @param cds
     */
    public void writeDatabase() {
        
        //serialize the database
        OutputStream file = null;
        OutputStream buffer = null;
        ObjectOutput output = null;
        try {
            file = new FileOutputStream("Dealership.ser");
            buffer = new BufferedOutputStream(file);
            output = new ObjectOutputStream(buffer);
            
            output.writeObject(vehicleInventory);
            output.writeObject(users);
            output.writeObject(transactions);
            output.writeInt(userIdCounter);
            
            output.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        } finally {
            close(file);
        }
    }
    
    /**
     * Auxiliary convenience method used to close a file and handle possible
     * exceptions that may occur.
     * @param c
     */
    public static void close(Closeable c) {
        if (c == null) {
            return;
        }
        try {
            c.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }
    
}
