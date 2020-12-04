package com.company;

public class Wearable {
    private int id;
    private String name;
    private double price;
    private String batteryLife;
    private String chargingType;
    private double dimensions;
    private boolean bluetoothConnectivity;


    public Wearable(String n, double p, String bl, String ct,  double d, boolean bc) {
        this.name = n;
        this.price = p;
        this.batteryLife = bl;
        this.chargingType = ct;
        this.dimensions = d;
        this.bluetoothConnectivity = bc;
    }


    public Wearable(int id, String n, double p, String bl, String ct,  double d, boolean bc) {
        this.id = id;
        this.name = n;
        this.price =p;
        this.batteryLife = bl;
        this.chargingType = ct;
        this.dimensions = d;
        this.bluetoothConnectivity = bc;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() { return price;}

    public void setPrice(double price){ this.price = price;}

    public String getBatteryLife() {return batteryLife; }

    public void setBatteryLife(String batteryLife) { this.batteryLife = batteryLife; }

    public String getChargingType() { return chargingType; }

    public void setChargingType(String chargingType) { this.chargingType = chargingType; }

    public double getDimensions() {return dimensions;}

    public void setDimensions(double dimensions) { this.dimensions = dimensions;}

    public boolean isBluetoothConnectivity() { return bluetoothConnectivity; }

    public boolean getBluetoothConnectivity() {return  bluetoothConnectivity;}

    @Override
    public String toString()
    {
        return "Wearable ID : " + id + "\n" + "Wearable name : " + name + "\n" ;
    }

}
