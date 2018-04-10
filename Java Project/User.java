/*
 * Car Dealership Managment Software v0.1 
 * Developed for CS3354: Object Oriented Design and Programming.
 * Copyright: Vangelis Metsis (vmetsis@txstate.edu)
 */

 

import java.io.Serializable;

/**
 *
 * @author vangelis
 */
public abstract class User implements Serializable {
    protected int id;
    protected String firstName;
    protected String lastName;

    /**
     * Default constructor.
     */
    protected User() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
    }

    /**
     * Constructor initializes a user object with the provided values.
     * @param id
     * @param firstName
     * @param lastName
     */
    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Get the last name.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name.
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the user ID.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the user ID.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the first name.
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Abstract print method, to be implemented by subclasses of class User.
     */
    public abstract String getFormattedText();
}
