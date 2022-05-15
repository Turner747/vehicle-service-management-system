package Model;

import java.util.Date;

/*
 * Joshua Turner 
 * S0258441
 * Isabel Rigby
 * S0077632
 * Assignment 3
 * Software Design & Development
 * COIT12200
 */

public class Service extends Vehicle
{
    private int serviceID;
    private Vehicle vehicle;
    private String description;
    private Date serviceDate;
    private float price;
    
    public Service()
    {
        //no-argument constructor
    }
    
    //constructor for Services
    public Service (int sid, int vid, String descr, Date servDate, float price)
    {
    super(vid);
    setServiceID(sid);
    setDescription(descr);
    setServiceDate(servDate);
    setPrice(price);
        
    }
    
    public Service (Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }
    
    //constructor to add Service -- Need to add with auto increment for SID
    
    //set and get methods
    public void setServiceID(int serviceID)
    {
        this.serviceID = serviceID;
    }
    
    public int getServiceID()
    {
        return serviceID;
    }
       
    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }
    
    public int getVehicle()
    {
        return vehicle.getVehicleID();
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setServiceDate(Date serviceDate)
    {
        this.serviceDate = serviceDate;
    }
    
    public Date getServiceDate()
    {
        return serviceDate;
    }
    
    public void setPrice(float price)
    {
        this.price = price;
    }
    
    public float getPrice()
    {
        return price;
    }
    
}
