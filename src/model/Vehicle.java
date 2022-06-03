package model;

/*
 * Joshua Turner 
 * S0258441
 * Isabel Rigby
 * S0077632
 * Joshua Robertson
 * 12071409 
 * Assignment 3
 * Software Design & Development
 * COIT12200
 */

public class Vehicle
{
    private int vehicleID;
    private int ownerID;
    private String ownerName; //required for vehicle table
    private String make;
    private String model;
    private String year;
    private int odometer;
            
    public Vehicle()
    {
        //no-argument constructor
    }
    
    // constructor for Vehicles with no owner name
    public Vehicle(int vehicleID, int ownerID, String make, String model, String year, int odometer)
    {
        this.vehicleID = vehicleID;
        this.ownerID = ownerID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometer = odometer;
    }
    
    // constructor for Vehicles with no owner name, required for vehicle table
    public Vehicle(int vehicleID, int ownerID, String OwnerName, String make, String model, String year, int odometer)
    {
        this.vehicleID = vehicleID;
        this.ownerID = ownerID;
        this.ownerName = ownerName;
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometer = odometer;
    }

    //constructor with no vehicle id, let database create id
    public Vehicle(int ownerID, String make, String model, String year, int odometer)
    {
        this.ownerID = ownerID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometer = odometer;
    }
   
    //set and get methods
    public void setVehicleID(int vehicleID)
    {
        this.vehicleID = vehicleID;
    }
    
    public int getVehicleID()
    {
        return vehicleID;
    }

    public int getOwnerID()
    {
        return ownerID;
    }

    public void setOwnerID(int ownerID)
    {
        this.ownerID = ownerID;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }
    
    public void setMake(String make)
    {
        this.make = make;
    }
    
    public String getMake()
    {
        return make;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public String getModel()
    {
        return model;
    }
    
    public void setYear(String year)
    {
        this.year = year;
    }
    
    public String getYear()
    {
        return year;
    }
    
    public void setOdometer(int odometer)
    {
        this.odometer = odometer;
    }
    
    public int getOdometer()
    {
        return odometer;
    }
    
}
