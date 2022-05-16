package Model;

/*
 * Joshua Turner 
 * S0258441
 * Isabel Rigby
 * S0077632
 * Assignment 3
 * Software Design & Development
 * COIT12200
 */

public class Vehicle extends Customer
{
    private int vehicleID;
    private Customer customer;
    private String make;
    private String model;
    private String year;
    private int odometer;
            
    public Vehicle()
    {
        //no-argument constructor
    }
    
    //constructor for Vehicles
    public Vehicle(int vid, int cid, String mak, String mod, String y, int o)
    {
        super(cid);
        setVehicleID(vid);
        setMake(mak);
        setModel(mod);
        setYear(y);
        setOdometer(o);
        
    }
    
    //constructors for Services
    public Vehicle(int vid)
    {
        setVehicleID(vid);
    }
    
    //constructor to add Vehicle -- Need to add with auto increment for VID
    
    //set and get methods
    public void setVehicleID(int vehicleID)
    {
        this.vehicleID = vehicleID;
    }
    
    public int getVehicleID()
    {
        return vehicleID;
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    
    public int getCustomer()
    {
        return customer.getCustomerID();
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
