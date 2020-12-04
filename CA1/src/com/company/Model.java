package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    // we only want one instance of the Model - ie. one instance of the data, we don't want to create lots of Model object like we would the Programmer object.
    // So this is a special method that insures only one instance of the Model ever exists, and all handles to this object refer to the same model
    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    // This model is has hardcoded wearables in the array, this data will eventually be retrieved from the database and we will delete this hardcoded data
    private List<Wearable> wearables;
    private WearableTableGateway gateway;

    // Model constructor, at present it sets up the arraylist of wearables by hardcoding the data into the ArrayList, later we will set up connections to the database here
    private Model() {
        try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new WearableTableGateway(conn);

            // this is commented out until we implement it later on
           // this.wearables = gateway.getWearables();

        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "Problem Connecting to the Database, have you added your JDBC Driver .jar file?", ex);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

//        this.wearables = new ArrayList<>();

        // Hard code a few progrmmers into the array list until we have hooked up to the database.
 //       this.wearables.add(
 //               new Wearable(1,"Apple Watch Series 5", 450.00, "18 hours", "USB Magnetic Charging Pad", 44, true  ));

 //       this.wearables.add(
  //              new Wearable(2,"FitBit Sense", 329.95, "6+ Days", "USB Magnetic Charging Pad", 40, true));

 //       this.wearables.add(
  //              new Wearable(3,"Amazfit Band 5", 44.99, "15 Days", "2-pins POGO pin", 47, true));

  //      this.wearables.add(
  //              new Wearable(4,"Garmin Vivofit Jr 2", 71.00, "1 year", "Replacable Battery ", 11, true));
    }


    // returns the array list of wearables to the calling program.
    public List<Wearable> getWearables() {
      //  return new ArrayList<>(this.wearables);
        return gateway.getWearables();

    }

    // adds the programmer object that is passed in to the array list - note this is temporary while the program is running.
    // later this method will call database code to add the programmer to the database.
    public boolean addWearable(Wearable w) {

        return (gateway.insertWearable(w));
    }

    public boolean deleteWearable(int id){
        return (gateway.deleteWearable(id));
    }


}
