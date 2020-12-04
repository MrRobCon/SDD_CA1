package com.company;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner keyboard = new Scanner(System.in);
    // model is a special class called a singleton. It is only instanticated once.
    // get the model once here at the start of your application.
    // Then use model to ask it to model.addXXX(), model.removeXXX(), get etc.
    static Model model = Model.getInstance();

    public static void main(String[] args){

        Wearable w;

        int opt;
        do {
            System.out.println("\n\n********* MAIN MENU ********");
            System.out.println("1. Create new Wearable");
            System.out.println("2. View all Wearables");
            System.out.println("3. Delete a Wearable by ID");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("*****Enter option: *******\n\n");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);
            // NOTE For above - slight different way of reading in a number, read the line as a String, then convert the String to an integer


            switch (opt) {

                // if the user choses option 1 create a programmer from user input and add it to the array list in Model.
                case 1: {
                    // ask the user for the programmer details, then create a programmer object p
                    w = readWearable();

                    boolean created = model.addWearable(w);

                    if (created) System.out.println("***** Wearable Added to the Database *****");
                    else
                        System.out.println("***** Wearable NOT Added to the Database *****");

                    break;
                }

                // if the user choses option 2 get the array list of programmers from the model and display them all on screen.
                case 2: {
                    // this method will call the model to get the list of programmers and display them to the screen.
                    viewWearables();
                    break;
                }

                case 3: {
                    deleteWearable();
                }

            }
        }
        while (opt != 5);
        System.out.println("Goodbye");
    }

    // reads the details from the user, creates a Programmer object and returns this object to the calling program
    private static Wearable readWearable() {
        String name, batteryLife, chargingType;

        double dimensions, price;

        boolean bluetoothConnectivity;
        String line;

        // GetSting is a helper method created below to make the code a bit neater.
        // It is the similar to the two lines of code - System.out.println("Enter XXX")...readLine()

        System.out.print("Enter name : ");
        name = keyboard.nextLine();

        System.out.print("Enter Battery Life : ");
        batteryLife = keyboard.nextLine();

        System.out.print("Enter Charging Type : ");
        chargingType = keyboard.nextLine();


        System.out.print("Enter Dimensions in millimeters: ");
        // this line reads in the double variable - salary which the user types, however the "ENTER" is still sitting in the buffer
        dimensions = keyboard.nextDouble();
        // this nextLine() swallows up the carraige return (ENTER) that is sitting in the buffer from when the user typed in the salary then hits enter.
        keyboard.nextLine();

        System.out.print("Enter Price : ");
        // this line reads in the double variable - salary which the user types, however the "ENTER" is still sitting in the buffer
        price = keyboard.nextDouble();
        // this nextLine() swallows up the carraige return (ENTER) that is sitting in the buffer from when the user typed in the salary then hits enter.
        keyboard.nextLine();

        System.out.print("Bluetooth Connectivity? (true/false) ");
        bluetoothConnectivity = keyboard.nextBoolean();
        keyboard.nextLine();



        // Create the Programmer object p
        Wearable w =
                new Wearable(name, price, batteryLife, chargingType, dimensions, bluetoothConnectivity);

        //return the programmer object p to the calling method
        return w;
    }


    // gets the programmers array list from the model and displays it.
    private static void viewWearables(){

        // ask the model for the list of programmers
        List<Wearable> wearables = model.getWearables();

        System.out.println("***** Printing All Wearables *****");
        // display the list of programmers
        for (Wearable pr : wearables) {
            System.out.println(pr);
        }

        System.out.println("***** Finished Printing All Wearables *****");
    }

    private static void deleteWearable() {
        System.out.print("Enter the ID of the wearable to delete:");
        int id = Integer.parseInt(keyboard.nextLine());

        // tip for extra functionality - maybe you want to got the the database retrieve the programmer,
        // display it to the user and ask "Is this the programmer you want to delete?
        // if they say - Yes then delete the programmer if they say no .. do something else
        if (model.deleteWearable(id)) {
            System.out.println("\nWearable deleted");
        }
        else {
            System.out.println("\nWearable not deleted, check your database to see if a wearable with this ID actually exists");
        }
    }

}
