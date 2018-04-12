
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.logging.*;

/**
 * This class is the main program that runs the Jframe GUI with the rest of the Dealership Database.
 * 
 * @author Christopher Henry, Matthew Cano
 * @version 11/20/2016
 */
public class main extends JFrame {
    //dealership class creation
    Dealership deal = Dealership.readDatabase();
    
    //data logger
    private static final Logger logger = Logger.getLogger("log");
    private static final FileHandler fh = initFh();
    //file writer for data logger
    private static FileHandler initFh() {
        FileHandler fh = null;
        try {
            fh = new FileHandler("mylog.txt");
            fh.setFormatter(new SimpleFormatter());
            // Send logger output to our FileHandler.
            logger.addHandler(fh);
            // Request that every detail gets logged.
            logger.setLevel(Level.ALL);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fh;
    }
    
    /**
     * formatter for data logger
     * @param parameter needed for formatter change
     * @return returns new format
     */
    public class MyFormatter extends Formatter {
            @Override
            public String format(LogRecord record) {
                return record.getLevel() + ":" + record.getMessage();
            }
        }
        
    /**
     * Constructor for main class.
     */    
    public main() {
        // If you could not read from the file, create a new database.
        if (deal == null) {
            System.out.println("Creating a new database.");
            deal = new Dealership();
        }
        mainMenu();
    }
    
    /**
     * Method to call main menu jFrame.
     * Its creates a main menu with radio buttons for selecting.
     * Selecting a choice calls other methods and hides main menu visibility.
     */
    public void mainMenu() {
        
        JButton buttonOK = new JButton("OK");
        JRadioButton a = new JRadioButton("Show all existing vehicles in the database. ");
        JRadioButton b = new JRadioButton("Add a new vehicle to the database.");
        JRadioButton c = new JRadioButton("Delete a vehicle from a database (given its VIN).");
        JRadioButton d = new JRadioButton("Search for a vehicle (given its VIN)");
        JRadioButton e = new JRadioButton("Show a list of vehicles within a given price range.");
        JRadioButton f = new JRadioButton("Show list of users.");
        JRadioButton g = new JRadioButton("Add a new user to the database.");
        JRadioButton h = new JRadioButton("Update user info (given their id).");
        JRadioButton i = new JRadioButton("Sell a vehicle.");
        JRadioButton j = new JRadioButton("Show a list of completed sale transactions.");
        JRadioButton k = new JRadioButton("Exit program.");

        ButtonGroup group = new ButtonGroup();
        group.add(a);
        group.add(b);
        group.add(c);
        group.add(d);
        group.add(e);
        group.add(f);
        group.add(g);
        group.add(h);
        group.add(i);
        group.add(j);
        group.add(k);
        
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
       
        add(a, constraints);
        constraints.gridy = 1;
        add(b, constraints);
        constraints.gridy = 2;
        add(c, constraints);
        constraints.gridy = 3;
        add(d, constraints);
        constraints.gridy = 4;
        add(e, constraints);
        constraints.gridy = 5;
        add(f, constraints);
        constraints.gridy = 6;
        add(g, constraints);
        constraints.gridy = 7;
        add(h, constraints);
        constraints.gridy = 8;
        add(i, constraints);
        constraints.gridy = 9;
        add(j, constraints);
        constraints.gridy = 10;
        add(k, constraints);

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridy = 11;
        add(buttonOK, constraints);
        
        //Action listener when user presses OK button.
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)  {
                
                if (a.isSelected()) {
                    setVisible(false);
                    showAllVehicles();
                    logger.log(Level.INFO, ("Starting Show All Vehicles!"));
                } else if (b.isSelected()) {
                    setVisible(false);
                    addVehicle();
                    logger.log(Level.INFO, ("Starting Add Vehicle!"));
                } else if (c.isSelected()) {
                    setVisible(false);
                    deleteVehicle();
                    logger.log(Level.INFO, ("Starting Delete Vehicle!"));
                } else if (d.isSelected()) {
                    setVisible(false);
                    searchVehicle();
                    logger.log(Level.INFO, ("Starting Search Vehicle!"));
                } else if (e.isSelected()) {
                    setVisible(false);
                    showVehiclesByPrice();
                    logger.log(Level.INFO, ("Starting Search Vehicle By Price!"));
                } else if (f.isSelected()) {
                    setVisible(false);
                    showAllUsers();
                    logger.log(Level.INFO, ("Starting Show All Users!"));
                } else if (g.isSelected()) {
                    setVisible(false);
                    addUser();
                    logger.log(Level.INFO, ("Starting Add User!"));
                } else if (h.isSelected()) {
                    setVisible(false);
                    updateUser();
                    logger.log(Level.INFO, ("Starting Update User!"));
                } else if (i.isSelected()) {
                    setVisible(false);
                    sellVehicle();
                    logger.log(Level.INFO, ("Starting Sell Vehicle!"));
                } else if (j.isSelected()) {
                    setVisible(false);
                    showAllSales();
                    logger.log(Level.INFO, ("Starting Show All Sales!"));
                } else if (k.isSelected()) {
                    deal.writeDatabase();
                    setVisible(false);
                    logger.log(Level.INFO, ("Exiting Program!"));
                    dispose();
                }
            }
        }
        );
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    
    /**
     * Method used to check if string input can be converted to integer.
     * @param String input.
     * @return returns boolean true or false.
     */
    public static boolean isParsable(String input){
        boolean parsable = true;
        try{
            Integer.parseInt(input);
        }catch(NumberFormatException e){
            parsable = false;
        }
        return parsable;
    }
    
    /**
     * Method used to check if string input can be converted to float.
     * @param String input.
     * @return returns boolean true or false.
     */
    public static boolean isParsableF(String input){
        boolean parsable = true;
        try{
            Float.parseFloat(input);
        }catch(NumberFormatException e){
            parsable = false;
        }
        return parsable;
    }
    
    /**
     * Method for calling show all vehicles.
     */
    public void showAllVehicles() {
        showVehicles(deal.vehicleInventory);
    }
    
    /**
     * Method that creates a jFrame to show all vehicles in dealership.
     * Creates jframe and fills with string calls from vehicles in dealership..
     * @param the Vehicle List from when Dealership was created.
     */
    private void showVehicles(List<Vehicle> vehicles) {
        JFrame frame = new JFrame("Show Vehicles");
        JPanel panel = new JPanel();
        JButton buttonOK = new JButton("OK");
        // Y_AXIS means each component added will be added vertically
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        String data = ("---------------------------------------------------"
                + "-------------------------------------------------------");
        String data1 =  ("VEHICLE TYPE VIN MAKE MODEL YEAR MILEAGE PRICE EXTRA DETAILS");
        JLabel d = new JLabel(data);
        panel.add(d);
        JLabel d1 = new JLabel(data1);
        panel.add(d1);
        JLabel d2 = new JLabel(data);
        panel.add(d2);
        for (Vehicle v : vehicles) {
            JLabel j1 = new JLabel(v.getFormattedText());
            panel.add(j1);
        }
        JLabel d3 = new JLabel(data);
        panel.add(d3);
        panel.add(buttonOK);
        frame.add(panel);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //actionListener to head back to main menu when ok pressed.
        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                setVisible(true);
                logger.log(Level.INFO, ("Show All Vehiles Successful!"));
                frame.dispose();
            }
        });
    }
    
    /**
     * Method that creates Jframe for adding a vehicle to dealership.
     * Creates fields for user entry.
     * Checks user's entry for accuracy.
     * adds vehicle to database if successful.
     */    
    public void addVehicle() {
        JFrame frame = new JFrame("title");
        JPanel panel = new JPanel();
        JLabel a = new JLabel("VIN ");
        JTextField a1 = new JTextField("", 10);
        JLabel b = new JLabel("Make ");
        JTextField b1 = new JTextField("", 10);
        JLabel c = new JLabel("Model ");
        JTextField c1 = new JTextField("", 10);
        JLabel d = new JLabel("Year ");
        JTextField d1 = new JTextField("", 10);
        JLabel e = new JLabel("Mileage ");
        JTextField e1 = new JTextField("", 10);
        JLabel f = new JLabel("Price ");
        JTextField f1 = new JTextField("", 10);
        JRadioButton g = new JRadioButton("Car");
        JRadioButton h = new JRadioButton("Truck");
        JRadioButton i = new JRadioButton("Motorcycle");
        JLabel j = new JLabel("Body Type ");
        JTextField j1 = new JTextField("", 10);
        JLabel k = new JLabel("Max Weight ");
        JTextField k1 = new JTextField("", 10);
        JLabel l = new JLabel("Length ");
        JTextField l1 = new JTextField("", 10);
        JLabel m = new JLabel("Type ");
        JTextField m1 = new JTextField("", 10);
        JLabel n = new JLabel("Engine Displacement ");
        JTextField n1 = new JTextField("", 10);
        
        ButtonGroup group = new ButtonGroup();
        group.add(g);
        group.add(h);
        group.add(i);
        
        JButton buttonOK = new JButton("OK");
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
   
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(a, constraints);
        constraints.gridy = 1;
        panel.add(b, constraints);
        constraints.gridy = 2;
        panel.add(c, constraints);
        constraints.gridy = 3;
        panel.add(d, constraints);
        constraints.gridy = 4;
        panel.add(e, constraints);
        constraints.gridy = 5;
        panel.add(f, constraints);
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(g, constraints);
        constraints.gridx = 5;
        constraints.gridy = 6;
        panel.add(h, constraints);
        constraints.gridx = 8;
        constraints.gridy = 6;
        panel.add(i, constraints);
        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(j, constraints);
        constraints.gridy = 8;
        panel.add(k, constraints);
        constraints.gridy = 9;
        panel.add(l, constraints);
        constraints.gridy = 10;
        panel.add(m, constraints);
        constraints.gridy = 11;
        panel.add(n, constraints);
        
        constraints.gridx = 5;
        constraints.gridy = 0;
        panel.add(a1, constraints);
        constraints.gridy = 1;
        panel.add(b1, constraints);
        constraints.gridy = 2;
        panel.add(c1, constraints);
        constraints.gridy = 3;
        panel.add(d1, constraints);
        constraints.gridy = 4;
        panel.add(e1, constraints);
        constraints.gridy = 5;
        panel.add(f1, constraints);
        constraints.gridy = 7;
        panel.add(j1, constraints);
        constraints.gridy = 8;
        panel.add(k1, constraints);
        constraints.gridy = 9;
        panel.add(l1, constraints);
        constraints.gridy = 10;
        panel.add(m1, constraints);
        constraints.gridy = 11;
        panel.add(n1, constraints);
        
        g.setSelected(true);
        j1.setEnabled(true);
        k1.setEnabled(false);
        l1.setEnabled(false);
        m1.setEnabled(false);
        n1.setEnabled(false);

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 5;
        constraints.gridy = 12;
        panel.add(buttonOK, constraints);
        
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        //actionListener that changes if user picks different radio button.
        class RadioButtonActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                JRadioButton button = (JRadioButton) event.getSource();
                if (g.isSelected()) {
                    j1.setEnabled(true);
                    k1.setEnabled(false);
                    l1.setEnabled(false);
                    m1.setEnabled(false);
                    n1.setEnabled(false);
                    }         
                if (h.isSelected()) {
                    j1.setEnabled(false);
                    k1.setEnabled(true);
                    l1.setEnabled(true);  
                    m1.setEnabled(false);  
                    n1.setEnabled(false);
                    }
                if (i.isSelected()) {
                    j1.setEnabled(false);
                    k1.setEnabled(false);
                    l1.setEnabled(false);
                    m1.setEnabled(true);
                    n1.setEnabled(true);
                    }
                }
            }  
            
        RadioButtonActionListener actionListener = new RadioButtonActionListener();
        g.addActionListener(actionListener);
        h.addActionListener(actionListener);
        i.addActionListener(actionListener);
        //actionListener that checks everything user inputs before attempting to add vehicle.
        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String vin = a1.getText();
                
                if (vin.length() != 5) {
                    JOptionPane.showMessageDialog(panel, 
                    "VIN must be 5 characters long, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("VIN: (" + vin + ") was not proper character length"));
                    return;
                }
                
                for (Vehicle v : deal.vehicleInventory) {
                    if (v.getVin().equals(vin)) {
                        JOptionPane.showMessageDialog(panel, 
                        "VIN cannot be a duplicate already in list, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("VIN: (" + vin + ") was a duplcate in database."));
                        return;
                    }
                }
   
                String make = b1.getText();
                
                String model = c1.getText();
                
                String temp = d1.getText();
                temp.replaceAll("\\s+","");
                boolean d = isParsable(temp);
                if ( d == false) {
                    JOptionPane.showMessageDialog(panel, 
                    "Year is not an Integer, exiting add vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Year: (" + temp + ") was not an integer."));
                    return;
                }
                int year = Integer.parseInt(temp);
                
                if (year < 1900) {
                    JOptionPane.showMessageDialog(panel, 
                    "Year cannot be lower than 1900, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Year: (" + year + ") must be between 1900 - 2017."));
                    return;
                }
                
                if (year > 2017) {
                    JOptionPane.showMessageDialog(panel, 
                    "Year cannot be greater than 2017, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Year: (" + year + ") must be between 1900 - 2017."));
                    return;
                }
                
                String temp1 = e1.getText();
                temp1.replaceAll("\\s+","");
                boolean o = isParsable(temp1);
                if ( o == false) {
                    JOptionPane.showMessageDialog(panel, 
                    "Mileage is not an Integer, exiting add vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Mileage: (" + temp1 + ") was not an integer."));
                    return;
                }
                int mileage = Integer.parseInt(temp1);
                
                if (mileage < 0) {
                    JOptionPane.showMessageDialog(panel, 
                    "Mileage cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Mileage: (" + mileage + ") cannot be negative."));
                    return;
                }
                
                String temp2 = f1.getText();
                temp2.replaceAll("\\s+","");
                boolean f = isParsableF(temp2);
                if ( f == false) {
                    JOptionPane.showMessageDialog(panel, 
                    "Price is not a float, exiting add vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Price: (" + temp2 + ") was not a float."));
                    return;
                }
                float price = Float.parseFloat(temp2);
                
                if (price < 0) {
                    JOptionPane.showMessageDialog(panel, 
                    "Price cannot be negative, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Price: (" + price + ") cannot be negative."));
                    return;
                }
                
                if (g.isSelected()) {
                    String bodyStyle = j1.getText();
                    
                    PassengerCar car = new PassengerCar(vin, make, model, year, mileage, 
                    price, bodyStyle);
                    deal.vehicleInventory.add(car);
                    }         
                if (h.isSelected()) {
                    String temp3 = k1.getText();
                    temp3.replaceAll("\\s+","");
                    boolean k = isParsableF(temp3);
                    if ( k == false) {
                        JOptionPane.showMessageDialog(panel, 
                        "Max Load is not a float, exiting add vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Max Load: (" + temp3 + ") was not a float."));
                        return;
                    }
                    float maxLoad = Float.parseFloat(temp3);
                    
                    if (maxLoad < 0) {
                        JOptionPane.showMessageDialog(panel, 
                        "Max Load cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Max Load: (" + maxLoad + ") cannot be negative."));
                        return;
                    }
                    
                    String temp4 = l1.getText();
                    temp4.replaceAll("\\s+","");
                    boolean l = isParsableF(temp4);
                    if ( l == false) {
                        JOptionPane.showMessageDialog(panel, 
                        "Truck Length is not a float, exiting add vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Truck Length: (" + temp4 + ") was not a float."));
                        return;
                    }
                    float tLength = Float.parseFloat(temp4);
                    
                    if (tLength < 0) {
                        JOptionPane.showMessageDialog(panel, 
                        "Truck Length cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Truck Length: (" + tLength + ") cannot be negative."));
                        return;
                    }
                    
                    Truck tr = new Truck(vin, make, model, year, mileage, price, 
                    maxLoad, tLength);
                    deal.vehicleInventory.add(tr);
                    }
                if (i.isSelected()) {
                    String type = m1.getText();
                    
                    String temp5 = n1.getText();
                    temp5.replaceAll("\\s+","");
                    boolean n = isParsable(temp5);
                    if ( n == false) {
                        JOptionPane.showMessageDialog(panel, 
                        "Engine Displacment is not a integer, exiting add vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Engine Displacement: (" + temp5 + ") is not an integer."));
                        return;
                    }
                    int displacement = Integer.parseInt(temp5);
                    
                    if (displacement < 0) {
                        JOptionPane.showMessageDialog(panel, 
                        "Engine Displacement cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Engine Displacement: (" + displacement + ") cannot be negative."));
                        return;
                    }
                    
                    Motorcycle mc = new Motorcycle(vin, make, model, year, mileage, 
                    price, type, displacement);
                    deal.vehicleInventory.add(mc);
                    }
                frame.setVisible(false);
                setVisible(true);
                frame.dispose();
                logger.log(Level.INFO, ("Add Vehicle Successful!"));
            }
        });
    }
    
    /**
     * Method to create jFrame for deleting vehicle in Dealership.
     * Asks user to input VIN to match vehicle record in database.
     * Checks for accuracy and deletes if record exists.
     */
    public void deleteVehicle() {
        JFrame frame = new JFrame("Search Vehicle");
        JPanel panel = new JPanel();
        JLabel a = new JLabel("VIN ");
        JTextField a1 = new JTextField("", 10);
        
        JButton buttonOK = new JButton("OK");
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
   
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(a, constraints);
       
        constraints.gridx = 5;
        constraints.gridy = 0;
        panel.add(a1, constraints);
        constraints.gridy = 1;
        
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 5;
        constraints.gridy = 12;
        panel.add(buttonOK, constraints);
        
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //actionListener for when user presses OK button.
        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            String vin = a1.getText();
            if (vin.length() != 5) {
                JOptionPane.showMessageDialog(panel, 
                "VIN must be 5 characters long, exiting Delete Vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(false);
                setVisible(true);
                frame.dispose();
                logger.log(Level.WARNING, ("VIN: (" + vin + ") must be 5 characters long."));
                return;
            }
            List<Vehicle> matchingVehicle = new ArrayList<Vehicle>();
            for (Vehicle v : deal.vehicleInventory) {
                if (v.getVin().equals(vin)) {
                    deal.vehicleInventory.remove(v);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.INFO, ("Delete Vehicle Successful!"));
                    return;
                }
            }
            JOptionPane.showMessageDialog(panel, 
            "Vehicle with VIN " + vin + " not found in the database", "Error", JOptionPane.ERROR_MESSAGE);
            frame.setVisible(false);
            setVisible(true);
            logger.log(Level.WARNING, ("VIN: (" + vin + ") did not exist."));
            frame.dispose();
            }
        });
    }
    
    /**
     * Method to create jFrame to search for vehicle in dealership
     * User inputs VIN of a vehicle for it to search for.
     * Checks for match and called showVehicles(param) method.
     */
    public void searchVehicle() {
        JFrame frame = new JFrame("Search Vehicle");
        JPanel panel = new JPanel();
        JLabel a = new JLabel("VIN ");
        JTextField a1 = new JTextField("", 10);
        
        JButton buttonOK = new JButton("OK");
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
   
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(a, constraints);
       
        constraints.gridx = 5;
        constraints.gridy = 0;
        panel.add(a1, constraints);
        
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 5;
        constraints.gridy = 12;
        panel.add(buttonOK, constraints);
        
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //actionListener for when user presses ok button.
        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            String vin = a1.getText();
            if (vin.length() != 5) {
                JOptionPane.showMessageDialog(panel, 
                "VIN must be 5 characters long, exiting Search Vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(false);
                setVisible(true);
                frame.dispose();
                logger.log(Level.WARNING, ("VIN: (" + vin + ") must be 5 characters long."));
                return;
            }
            
            List<Vehicle> matchingVehicle = new ArrayList<Vehicle>();
            for (Vehicle v : deal.vehicleInventory) {
                if (v.getVin().equals(vin)) {
                    matchingVehicle.add(v);
                    showVehicles(matchingVehicle);
                    frame.setVisible(false);
                    frame.dispose();
                    logger.log(Level.INFO, ("Search Vehicle Successful!"));
                    return;
                }
            }
            JOptionPane.showMessageDialog(panel, 
            "Vehicle with VIN " + vin + " not found in the database", "Error", JOptionPane.ERROR_MESSAGE);
            frame.setVisible(false);
            setVisible(true);
            logger.log(Level.WARNING, ("VIN: (" + vin + ") did not exist."));
            frame.dispose();
            }
        });
    }
    
    /**
     * Method to creat jFrame to search vehicle by price range in Dealership.
     * User inputs high and low values and also selects from type of vehicle.
     * If succesful it calls showVehicle(param) method and display withing price range and vehicle type.
     */
    public void showVehiclesByPrice() {
        JFrame frame = new JFrame("Search Vehicle By Price Range");
        JPanel panel = new JPanel();
        JLabel a = new JLabel("Price Low ");
        JTextField a1 = new JTextField("", 10);
        JLabel b = new JLabel("Price High ");
        JTextField b1 = new JTextField("", 10);
        JRadioButton c = new JRadioButton("Car");
        JRadioButton d = new JRadioButton("Truck");
        JRadioButton e = new JRadioButton("Motorcycle");

        ButtonGroup group = new ButtonGroup();
        group.add(c);
        group.add(d);
        group.add(e);

        JButton buttonOK = new JButton("OK");

        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
   
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(a, constraints);
        constraints.gridy = 1;
        panel.add(b, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(c, constraints);
        constraints.gridx = 5;
        constraints.gridy = 3;
        panel.add(d, constraints);
        constraints.gridx = 8;
        constraints.gridy = 3;
        panel.add(e, constraints);
       
        constraints.gridx = 5;
        constraints.gridy = 0;
        panel.add(a1, constraints);
        constraints.gridy = 1;
        panel.add(b1, constraints);
        
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 5;
        constraints.gridy = 12;
        panel.add(buttonOK, constraints);
        
        c.setSelected(true);

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //actionListener for when user presses ok button.
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)  {
                String temp = a1.getText();
                temp.replaceAll("\\s+","");
                boolean a = isParsableF(temp);
                if ( a == false) {
                    JOptionPane.showMessageDialog(panel, 
                    "Low Price is not a float, exiting Search vehicle By Price!", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Price Low: (" + temp + ") was not a float."));
                    return;
                }
                float lowValue = Float.parseFloat(temp);
                
                if (lowValue < 0) {
                    JOptionPane.showMessageDialog(panel, 
                    "The Lowest Value cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Price Low: (" + lowValue + ") cannot be negative."));
                    return;
                }
                
                String temp1 = b1.getText();
                temp1.replaceAll("\\s+","");
                boolean b = isParsableF(temp1);
                if ( b == false) {
                    JOptionPane.showMessageDialog(panel, 
                    "High Price is not a float, exiting Search Vehicle By Price!", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Price High: (" + temp1 + ") was not a float."));
                    return;
                }
                float highValue = Float.parseFloat(temp1);
                
                if (highValue < 0) {
                    JOptionPane.showMessageDialog(panel, 
                    "The Highest Value cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Price High: (" + highValue + ") cannot be negative."));
                    return;
                }
                
                if (highValue < lowValue) {
                    JOptionPane.showMessageDialog(panel, 
                    "High Price cannot be less than Low Price, exiting Search Vehicle By Price!", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("High value cannot be less than Low Value."));
                    return;
                }
                
                ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
                if (c.isSelected()) {
                    for (Vehicle v : deal.vehicleInventory) {
                        if (v.getPrice() >= lowValue && v.getPrice() <= highValue) {
                        if (v instanceof PassengerCar)
                        matchingVehicles.add(v);
                    }
                    }
                } else if (d.isSelected()) {
                    for (Vehicle v : deal.vehicleInventory) {
                        if (v.getPrice() >= lowValue && v.getPrice() <= highValue) {
                        if (v instanceof Truck)
                        matchingVehicles.add(v);
                    }
                    }
                } else if (e.isSelected()) {
                    for (Vehicle v : deal.vehicleInventory) {
                        if (v.getPrice() >= lowValue && v.getPrice() <= highValue) {
                        if (v instanceof Motorcycle)
                        matchingVehicles.add(v);
                    }
                    }
                }
                if (matchingVehicles.size() == 0) {
                    JOptionPane.showMessageDialog(panel, 
                    "No matching vehicles found.", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("No Vehicles Found."));
                    return;
                } else {
                    frame.setVisible(false);
                    frame.dispose();
                    showVehicles(matchingVehicles);
                    logger.log(Level.INFO, ("Search Vehicles By Price Successful!"));
                }
            }
        }
        );
    }
    
    /**
     * Method to create jFrame that shows all users in Dealership.
     * 
     */
    public void showAllUsers() {
        JFrame frame = new JFrame("Show All Users");
        JPanel panel = new JPanel();
        JButton buttonOK = new JButton("OK");
        // Y_AXIS means each component added will be added vertically
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        String data = ("---------------------------------------------------"
                + "------------------------------------------");
        String data1 =  ("USER TYPE USER ID FIST NAME LAST NAME OTHER DETAILS");
        JLabel d = new JLabel(data);
        panel.add(d);
        JLabel d1 = new JLabel(data1);
        panel.add(d1);
        JLabel d2 = new JLabel(data);
        panel.add(d2);
        for (User u : deal.users) {
            JLabel j1 = new JLabel(u.getFormattedText());
            panel.add(j1);
        }
       
        JLabel d3 = new JLabel(data);
        panel.add(d3);
        panel.add(buttonOK);
        frame.add(panel);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //actionListener for when user presses OK button, goes back to main menu.
        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                setVisible(true);
                logger.log(Level.INFO, ("Show All Users Successful!"));
                frame.dispose();
            }
        });
    }
    
    /**
     * Method to create jFrame that adds a new user to Dealership.
     * user fills textfield and selections radio button for customer or employee.
     * Depending on which radio button is picked certain questions become available.
     * checks all inputs before adding new user.
     */
    public void addUser() {
        JFrame frame = new JFrame("Add User");
        JPanel panel = new JPanel();
        JLabel a = new JLabel("First Name ");
        JTextField a1 = new JTextField("", 10);
        JLabel b = new JLabel("Last Name ");
        JTextField b1 = new JTextField("", 10);
        JLabel c = new JLabel("Phone # ");
        JTextField c1 = new JTextField("", 10);
        JLabel d = new JLabel("driver license # ");
        JTextField d1 = new JTextField("", 10);
        JLabel e = new JLabel("Monthly Salary ");
        JTextField e1 = new JTextField("", 10);
        JLabel f = new JLabel("Bank Account ");
        JTextField f1 = new JTextField("", 10);
        JRadioButton g = new JRadioButton("Customer");
        JRadioButton h = new JRadioButton("Employee");
        
        ButtonGroup group = new ButtonGroup();
        group.add(g);
        group.add(h);
        
        JButton buttonOK = new JButton("OK");
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
   
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(a, constraints);
        constraints.gridy = 1;
        panel.add(b, constraints);
        constraints.gridy = 3;
        panel.add(c, constraints);
        constraints.gridy = 4;
        panel.add(d, constraints);
        constraints.gridy = 5;
        panel.add(e, constraints);
        constraints.gridy = 6;
        panel.add(f, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(g, constraints);
        constraints.gridx = 5;
        constraints.gridy = 2;
        panel.add(h, constraints);
        
        constraints.gridx = 5;
        constraints.gridy = 0;
        panel.add(a1, constraints);
        constraints.gridy = 1;
        panel.add(b1, constraints);
        constraints.gridy = 3;
        panel.add(c1, constraints);
        constraints.gridy = 4;
        panel.add(d1, constraints);
        constraints.gridy = 5;
        panel.add(e1, constraints);
        constraints.gridy = 6;
        panel.add(f1, constraints);
        
        g.setSelected(true);
        c1.setEnabled(true);
        d1.setEnabled(true);
        e1.setEnabled(false);
        f1.setEnabled(false);
        
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 5;
        constraints.gridy = 12;
        panel.add(buttonOK, constraints);
        
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //actionListener to changes based on which radio button user picks.
        class RadioButtonActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                JRadioButton button = (JRadioButton) event.getSource();
                if (g.isSelected()) {
                    c1.setEnabled(true);
                    d1.setEnabled(true);
                    e1.setEnabled(false);
                    f1.setEnabled(false);
                    }         
                if (h.isSelected()) {
                    c1.setEnabled(false);
                    d1.setEnabled(false);
                    e1.setEnabled(true);
                    f1.setEnabled(true);
                    }
                }
            }  
            
        RadioButtonActionListener actionListener = new RadioButtonActionListener();
        g.addActionListener(actionListener);
        h.addActionListener(actionListener);
        //actionListener for when user presses OK button.
        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = a1.getText();
                String lastName = b1.getText();
                if (g.isSelected()) {
                    String phoneNumber = c1.getText();
                    
                    String temp = d1.getText();
                    temp.replaceAll("\\s+","");
                    boolean d = isParsable(temp);
                    if ( d == false) {
                        JOptionPane.showMessageDialog(panel, 
                        "Driver's License is not an integer, exiting add user!", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Driver's License: (" + temp + ") was not an integer."));
                        return;
                    }
                    int dlnumber = Integer.parseInt(temp);
                    
                    if (dlnumber < 0) {
                        JOptionPane.showMessageDialog(panel, 
                        "Driver's License cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Driver's License: (" + dlnumber + ") cannot be negative."));
                        return;
                    }
                    
                    deal.users.add(new Customer((deal.users.size() + 1), firstName, lastName, 
                    phoneNumber, dlnumber));
                    }         
                if (h.isSelected()) {
                    String temp1 = e1.getText();
                    temp1.replaceAll("\\s+","");
                    boolean f = isParsableF(temp1);
                    if ( f == false) {
                        JOptionPane.showMessageDialog(panel, 
                        "Monthly Salary is not an float, exiting add user!", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Monthly Salary: (" + temp1 + ") was not a float."));
                        return;
                    }
                    float monthlySalary = Float.parseFloat(temp1);
                    
                    if (monthlySalary < 0) {
                        JOptionPane.showMessageDialog(panel, 
                        "Monthly Salary cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Monthly Salary: (" + monthlySalary + ") cannot be negative."));
                        return;
                    }
                    
                    String temp2 = f1.getText();
                    temp2.replaceAll("\\s+","");
                    boolean g = isParsable(temp2);
                    if ( g == false) {
                        JOptionPane.showMessageDialog(panel, 
                        "Bank Account Number is not an integer, exiting add user!", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Bank Account Number: (" + temp2 + ") was not an integer."));
                        return;
                    }
                    int bankAccNumber = Integer.parseInt(temp2);
                    
                    if (bankAccNumber < 0) {
                        JOptionPane.showMessageDialog(panel, 
                        "Bank Account Number cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(false);
                        setVisible(true);
                        frame.dispose();
                        logger.log(Level.WARNING, ("Bank Account Number: (" + bankAccNumber + ") cannot be negative."));
                        return;
                    }
                    
                    deal.users.add(new Employee((deal.users.size() + 1), firstName, lastName, 
                    monthlySalary, bankAccNumber));
                    }
                
                frame.setVisible(false);
                setVisible(true);
                logger.log(Level.INFO, ("Add User Successful!"));
                frame.dispose();
            }
        });
    }
    
    /**
     * Methos to create a jFrame for updating a user in Dealership.
     * User inputs UserID.
     * Checks if UserID exists
     * If so a new jframe pops up with questions based on if its a customer or employee.
     * checks inputs before updating that specific user.
     */
    public void updateUser() {
        JFrame frame = new JFrame("Update User");
        JPanel panel = new JPanel();
        JLabel m = new JLabel("User ID ");
        JTextField m1 = new JTextField("", 10);
        
        JButton buttonOK = new JButton("OK");
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
   
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(m, constraints);
       
        constraints.gridx = 5;
        constraints.gridy = 0;
        panel.add(m1, constraints);
        
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 5;
        constraints.gridy = 12;
        panel.add(buttonOK, constraints);
        
        frame.add(panel);
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //actionListener for when user press ok button.
        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ex) {
                
        String temp = m1.getText();
        temp.replaceAll("\\s+","");
        boolean m = isParsable(temp);
        if ( m == false) {
            JOptionPane.showMessageDialog(panel, 
            "User ID is not an integer, exiting update user!", "Error", JOptionPane.ERROR_MESSAGE);
            frame.setVisible(false);
            setVisible(true);
            frame.dispose();
            logger.log(Level.WARNING, ("User ID: (" + temp + ") was not an integer."));
            return;
                        }    
        int userID = Integer.parseInt(temp);
        
        User user = null;
        for (User u : deal.users) {
            if (u.getId() == userID)
            user = u;
        }
        
        if (user == null) {
                JOptionPane.showMessageDialog(panel, 
                    "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(false);
                setVisible(true);
                frame.dispose();
                logger.log(Level.WARNING, ("User ID: (" + userID + ") was not found."));
                return;
        }
           
        frame.setVisible(false);
        frame.dispose();
        if (user.getId() == userID) {
            JFrame frame = new JFrame("Add User");
            JPanel panel = new JPanel();
            JLabel a = new JLabel("First Name ");
            JTextField a1 = new JTextField("", 10);
            JLabel b = new JLabel("Last Name ");
            JTextField b1 = new JTextField("", 10);
            JLabel c = new JLabel("Phone # ");
            JTextField c1 = new JTextField("", 10);
            JLabel d = new JLabel("driver license # ");
            JTextField d1 = new JTextField("", 10);
            JLabel e = new JLabel("Monthly Salary ");
            JTextField e1 = new JTextField("", 10);
            JLabel f = new JLabel("Bank Account ");
            JTextField f1 = new JTextField("", 10);
        
            JButton buttonOK = new JButton("OK");
        
            panel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
        
            constraints.anchor = GridBagConstraints.CENTER;
   
            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(a, constraints);
            constraints.gridy = 1;
            panel.add(b, constraints);
            constraints.gridy = 3;
            panel.add(c, constraints);
            constraints.gridy = 4;
            panel.add(d, constraints);
            constraints.gridy = 5;
            panel.add(e, constraints);
            constraints.gridy = 6;
            panel.add(f, constraints);
        
            constraints.gridx = 5;
            constraints.gridy = 0;
            panel.add(a1, constraints);
            constraints.gridy = 1;
            panel.add(b1, constraints);
            constraints.gridy = 3;
            panel.add(c1, constraints);
            constraints.gridy = 4;
            panel.add(d1, constraints);
            constraints.gridy = 5;
            panel.add(e1, constraints);
            constraints.gridy = 6;
            panel.add(f1, constraints);
            if (user instanceof Customer) {
                c1.setEnabled(true);
                d1.setEnabled(true);
                e1.setEnabled(false);
                f1.setEnabled(false);
            } else {
                c1.setEnabled(false);
                d1.setEnabled(false);
                e1.setEnabled(true);
                f1.setEnabled(true);
            }
            
            constraints.anchor = GridBagConstraints.SOUTH;
            constraints.gridx = 5;
            constraints.gridy = 12;
            panel.add(buttonOK, constraints);
        
            frame.add(panel);
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            //actionListener that when user presses ok button updates user if successful.
            buttonOK.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    User user = null;
                    for (User u : deal.users) {
                        if (u.getId() == userID)
                        user = u;
                    }
                    String firstName = a1.getText();
                    String lastName = b1.getText();
                    if (user instanceof Customer) {
                        String phoneNumber = c1.getText();
                        
                        String temp = d1.getText();
                        temp.replaceAll("\\s+","");
                        boolean d = isParsable(temp);
                        if ( d == false) {
                            JOptionPane.showMessageDialog(panel, 
                            "Driver's License is not an integer, exiting update user!", "Error", JOptionPane.ERROR_MESSAGE);
                            frame.setVisible(false);
                            setVisible(true);
                            frame.dispose();
                            logger.log(Level.WARNING, ("Driver's License: (" + temp + ") was not an integer."));
                            return;
                        }
                        int dlnumber = Integer.parseInt(temp);
                        
                        if (dlnumber < 0) {
                            JOptionPane.showMessageDialog(panel, 
                            "Driver's License cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                            frame.setVisible(false);
                            setVisible(true);
                            frame.dispose();
                            logger.log(Level.WARNING, ("Driver's License: (" + dlnumber + ") cannot be negative."));
                            return;
                        }
                        
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        ((Customer)user).setPhoneNumber(phoneNumber);
                        ((Customer)user).setDriverLicenceNumber(dlnumber);
                    }         
                    else {
                        String temp1 = e1.getText();
                        temp1.replaceAll("\\s+","");
                        boolean f = isParsableF(temp1);
                        if ( f == false) {
                            JOptionPane.showMessageDialog(panel, 
                            "Monthly Salary is not an float, exiting update user!", "Error", JOptionPane.ERROR_MESSAGE);
                            frame.setVisible(false);
                            setVisible(true);
                            frame.dispose();
                            logger.log(Level.WARNING, ("Monthly Salary: (" + temp1 + ") was not a float."));
                            return;
                        }
                        float monthlySalary = Float.parseFloat(temp1);
                        
                        if (monthlySalary < 0) {
                            JOptionPane.showMessageDialog(panel, 
                            "Monthly Salary cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                            frame.setVisible(false);
                            setVisible(true);
                            frame.dispose();
                            logger.log(Level.WARNING, ("Monthly Salary: (" + monthlySalary + ") cannot be negative."));
                            return;
                        }
                        
                        String temp2 = f1.getText();
                        temp2.replaceAll("\\s+","");
                        boolean g = isParsable(temp2);
                        if ( g == false) {
                            JOptionPane.showMessageDialog(panel, 
                            "Bank Account Number is not an integer, exiting update user!", "Error", JOptionPane.ERROR_MESSAGE);
                            frame.setVisible(false);
                            setVisible(true);
                            frame.dispose();
                            logger.log(Level.WARNING, ("Back Account Number: (" + temp2 + ") was not an integer."));
                            return;
                        }
                        int bankAccNumber = Integer.parseInt(temp2);
                        
                        if (bankAccNumber < 0) {
                            JOptionPane.showMessageDialog(panel, 
                            "Bank Account Number cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                            frame.setVisible(false);
                            setVisible(true);
                            frame.dispose();
                            logger.log(Level.WARNING, ("Bank Account Number: (" + bankAccNumber + ") cannot be negative."));
                            return;
                        }
                        
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        ((Employee)user).setMonthlySalary(monthlySalary);
                        ((Employee)user).setBankAccountNumber(bankAccNumber);
                    }
                
                    frame.setVisible(false);
                    setVisible(true);
                    logger.log(Level.INFO, ("Update User Successful!"));
                    frame.dispose();
                }
            });    
        }
            }
        });
    }
    
    /**
     * Method that creats jFrame to sell a vehicle in Dealership.
     * User inputs in each Textfield.
     * method checks if customer, employee, and vehicle actually exists.
     * method checks other inputs before submitting sell
     * method deletes vehicle in Dealership that matches VIN.
     */
    public void sellVehicle() {
        JFrame frame = new JFrame("Sell Vehicle");
        JPanel panel = new JPanel();
        JLabel a = new JLabel("Customer ID ");
        JTextField a1 = new JTextField("", 10);
        JLabel b = new JLabel("Employee ID ");
        JTextField b1 = new JTextField("", 10);
        JLabel c = new JLabel("Vin ");
        JTextField c1 = new JTextField("", 10);
        JLabel d = new JLabel("Price ");
        JTextField d1 = new JTextField("", 10);
            
        JButton buttonOK = new JButton("OK");
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
   
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(a, constraints);
        constraints.gridy = 1;
        panel.add(b, constraints);
        constraints.gridy = 3;
        panel.add(c, constraints);
        constraints.gridy = 4;
        panel.add(d, constraints);
            
        constraints.gridx = 5;
        constraints.gridy = 0;
        panel.add(a1, constraints);
        constraints.gridy = 1;
        panel.add(b1, constraints);
        constraints.gridy = 3;
        panel.add(c1, constraints);
        constraints.gridy = 4;
        panel.add(d1, constraints);
           
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 5;
        constraints.gridy = 12;
        panel.add(buttonOK, constraints);
        
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //actionListener for when user presses OK button.
        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = a1.getText();
                temp.replaceAll("\\s+","");
                boolean a = isParsable(temp);
                if ( a == false) {
                    JOptionPane.showMessageDialog(panel, 
                    "Customer ID is not an integer, exiting Sell Vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Customer ID: (" + temp + ") was not an integer."));
                    return;
                }
                int customerId = Integer.parseInt(temp);
                
                String temp1 = b1.getText();
                temp1.replaceAll("\\s+","");
                boolean b = isParsable(temp1);
                if ( b == false) {
                    JOptionPane.showMessageDialog(panel, 
                    "Employee ID is not an integer, exiting Sell Vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Employee ID: (" + temp1 + ") was not an integer."));
                    return;
                }
                int employeeId = Integer.parseInt(temp1);
                
                String vin = c1.getText();
                
                String temp2 = d1.getText();
                temp2.replaceAll("\\s+","");
                boolean d = isParsableF(temp2);
                if ( d == false) {
                    JOptionPane.showMessageDialog(panel, 
                    "Driver's License is not an integer, exiting Sell Vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Final Price: (" + temp2 + ") was not a float."));
                    return;
                }
                float price = Float.parseFloat(temp2);
                
                if (price < 0) {
                    JOptionPane.showMessageDialog(panel, 
                    "Final Price cannot be negatve, exiting add vehicle", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.setVisible(false);
                    setVisible(true);
                    frame.dispose();
                    logger.log(Level.WARNING, ("Final Price: (" + price + ") cannot be negative."));
                    return;
                }
                    
                Date currentDate = new Date(System.currentTimeMillis());
                    
                boolean customerExists = false;
                for (User u : deal.users) {
                    if (u.getId() == customerId)
                    customerExists = true;
                }
                if (!customerExists) {
                    JOptionPane.showMessageDialog(panel, 
                    "The customer ID you have entered does not exist in the database.\n"
                    + "Please add the customer to the database first and then try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    logger.log(Level.WARNING, ("Customer ID: (" + customerId + ") did not exist."));
                    return;
                }
                    
                boolean employeeExists = false;
                for (User u : deal.users) {
                    if (u.getId() == employeeId)
                    employeeExists = true;
                }
                if (!employeeExists) {
                    JOptionPane.showMessageDialog(panel, 
                    "The Employee ID you have entered does not exist in the database.\n"
                    + "Please add the Employee to the database first and then try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    logger.log(Level.WARNING, ("Employee ID: (" + employeeId + ") did not exist."));
                    return;
                }    
                    
                Vehicle v = deal.findVehicle(vin);
                if (v == null) {
                    JOptionPane.showMessageDialog(panel, 
                    "The vehicle with the VIN you are trying to sell "
                    + "does not exist in the database. Aborting transaction.", "Error", JOptionPane.ERROR_MESSAGE);
                    logger.log(Level.WARNING, ("VIN: (" + vin + ") did not exist."));
                    return;
                }
                    
                    
                SaleTransaction trans = new SaleTransaction(customerId, employeeId, vin, 
                currentDate, price);
                deal.transactions.add(trans);
                deal.vehicleInventory.remove(v);
                    
                frame.setVisible(false);
                setVisible(true);
                logger.log(Level.INFO, ("Sell Vehicle Successful!"));
                frame.dispose();
                }
        });    
    }
    
    /**
     * Method to create jFrame to display all sales in Dealership.
     */
    public void showAllSales() {
        JFrame frame = new JFrame("Show All Sales");
        JPanel panel = new JPanel();
        JButton buttonOK = new JButton("OK");
        // Y_AXIS means each component added will be added vertically
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (SaleTransaction sale : deal.transactions) {
            JLabel j1 = new JLabel(sale.toString());
            panel.add(j1);
        }
       
        panel.add(buttonOK);
        frame.add(panel);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //actionListener for when user presses ok button.
        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                setVisible(true);
                logger.log(Level.INFO, ("Show All Sales Successful!"));
                frame.dispose();
            }
        });
    }
    
    /**
     * Main Method to Swing Call the main menu and start the program.
     */
    public static void main(String[] args) throws BadInputException {
        logger.log(Level.INFO, ("Starting Program!"));
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new main().setVisible(true);
            }
        });
    }
}
