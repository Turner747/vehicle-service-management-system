package model;

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

public class Service
{
    private int serviceID;
    private int vehicleID;
    private String licencePlate; // required for service table
    private String description;
    private Date serviceDate;
    private float price;
    
    public Service()
    {
        //no-argument constructor
    }
    
    // constructor for gettings Services from database, no licence plate
    public Service(int serviceID, int vehicleID, String description, Date serviceDate, float price)
    {
        this.serviceID = serviceID;
        this.vehicleID = vehicleID;
        this.description = description;
        this.serviceDate = serviceDate;
        this.price = price;
    }
    
    // full constructor for gettings Services from database, no licence plate
    public Service(int serviceID, int vehicleID, String licencePlate, String description, Date serviceDate, float price)
    {
        this.serviceID = serviceID;
        this.vehicleID = vehicleID;
        this.licencePlate = licencePlate;
        this.description = description;
        this.serviceDate = serviceDate;
        this.price = price;
    }

    // constructor with no service ID, add to database, let database assign service ID
    public Service(int vehicleID, String description, Date serviceDate, float price)
    {
        this.vehicleID = vehicleID;
        this.description = description;
        this.serviceDate = serviceDate;
        this.price = price;
    }
    
    
    
    //set and get methods
    public void setServiceID(int serviceID)
    {
        this.serviceID = serviceID;
    }
    
    public int getServiceID()
    {
        return serviceID;
    }

    public int getVehicleID()
    {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID)
    {
        this.vehicleID = vehicleID;
    }

    public String getLicencePlate()
    {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate)
    {
        this.licencePlate = licencePlate;
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
