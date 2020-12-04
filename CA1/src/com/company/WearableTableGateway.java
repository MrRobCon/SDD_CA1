package com.company;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class  WearableTableGateway {
    private static final String TABLE_NAME = "wearable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_BATTERYLIFE = "batteryLife";
    private static final String COLUMN_CHARGINGTYPE = "chargingType";
    private static final String COLUMN_DIMENSIONS = "dimensions";
    private static final String COLUMN_BLUETOOTHCONNECTIVITY = "bluetoothConnectivity";

    private Connection mConnection;

    public WearableTableGateway(Connection connection) {
        mConnection = connection;
    }

    // Called from the Model when the user wants to create a new programmer in the database, the new ID is created in the database and returned here
    public boolean insertWearable(Wearable w)  {

        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // I am going to create a new Programmer object, this object will have the Id after the row is inserted into the database
        //   Programmer dbProgrammer = null;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_PRICE + ", " +
                COLUMN_BATTERYLIFE + ", " +

                COLUMN_CHARGINGTYPE + ", " +
                COLUMN_DIMENSIONS + ", " +
                COLUMN_BLUETOOTHCONNECTIVITY +
                ") VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // create a PreparedStatement object to execute the query and insert the values into the query
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, w.getName());
            stmt.setDouble(2, w.getPrice());
            stmt.setString(3, w.getBatteryLife());

            stmt.setString(4, w.getChargingType());
            stmt.setDouble(5, w.getDimensions());
            stmt.setBoolean(6,w.getBluetoothConnectivity());

            //  execute the query and make sure that one and only one row was inserted into the database
            numRowsAffected = stmt.executeUpdate();

            // if numRowsAffected is 1 - that means the SQL insert inserted one row into the table, so the ID should have been auto-incremented and sent back here.
            // so assign this new ID to the ID.
            if (numRowsAffected == 1) {
//                    // if one row was inserted, retrieve the id that was assigned to that row in the database and ret
//                    ResultSet keys = stmt.getGeneratedKeys();
//                    keys.next();
//
//                    id = keys.getInt(1);
//                    //dbProgrammer.setId(id);
                return true;
            }

        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in WearableTableGateway : insertWearable(), Check the SQL you have created to see where your error is", e);
        }

        // return the Wearable object created with the new ID, The calling program in the Model should check to ensure it is not null
        return false;
    }


    // Called from the Model to get the complete list of programmers from the programmer table in the database
    public List<Wearable> getWearables()   {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Wearable> wearables;   // the java.util.List containing the Wearable objects created for each row
        // in the result of the query the id of a wearable

        String name, batteryLife, chargingType;
        int id;
        double price, dimensions;
        boolean bluetoothConnectivity;

        Wearable w;// a Wearable object created from a row in the result of the query
        wearables = new ArrayList<Wearable>();
        try {

            // execute an SQL SELECT statement to get a java.util.ResultSet representing
            // the results of the SELECT statement
            query = "SELECT * FROM " + TABLE_NAME;
            stmt = this.mConnection.createStatement();
            // rs is a ResultSet object. It contains the rows of data from the database.
            rs = stmt.executeQuery(query);

            // loop through the result set taking out the programmer data from the DB
            // create a wearable object with this data and pop it into an arraylist
            while (rs.next()) {
                id = rs.getInt(COLUMN_ID);
                name = rs.getString(COLUMN_NAME);
                price = rs.getDouble(COLUMN_PRICE);
                batteryLife = rs.getString(COLUMN_BATTERYLIFE);

                chargingType = rs.getString(COLUMN_CHARGINGTYPE);
                dimensions = rs.getDouble(COLUMN_DIMENSIONS);
                bluetoothConnectivity = rs.getBoolean(COLUMN_BLUETOOTHCONNECTIVITY);

                w = new Wearable(id, name, price, batteryLife, chargingType, dimensions, bluetoothConnectivity);
                wearables.add(w);
            }
        }

        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Error in getClothes");
        }

        // return the arraylist of Programmer objects to the model.
        return wearables;
    }

    public boolean deleteWearable(int id)    {

        int numRowsAffected;



        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= ?";


        try {
            PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query

            // create a PreparedStatement object to execute the delete, insert the id into the ? in the prepared statement
            stmt = mConnection.prepareStatement(query);
            stmt.setInt(1, id);

            // Test to see if the Query string is correct. This can be deleted from the final submission, it's here to show you how to print out your SQL so you can manually check it.
            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");

            //  execute the query and make sure that one and only one row was inserted into the database
            numRowsAffected = stmt.executeUpdate();
            // if a row was affected in the database - the row was deleted, so return true
            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in WearableTableGateway : deleteWearable(), Check the SQL you have created to see where your error is", e);
        }


        // if you get to here the wearable was not deleted so return false
        return false;


    }


}
