package model;

import java.time.LocalDate;

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

public class Service
{
    private int serviceID;
    private int vehicleID;
    private String owner; //required for vehicle table
    private String licencePlate; // required for service table
    private String description;
    private LocalDate serviceDate;
    private float price;
    private int recordStatus;  // 1 = active, 0 = cancelled
    
    public Service()
    {
        serviceID = 0; //used for validation
        recordStatus = 1;
    }
    
    // constructor for gettings Services from database, no licence plate
    public Service(int serviceID, int vehicleID, String description, LocalDate serviceDate, float price)
    {
        this.serviceID = serviceID;
        this.vehicleID = vehicleID;
        this.description = description;
        this.serviceDate = serviceDate;
        this.price = price;
        this.recordStatus = 1;
    }
    
    // full constructor for gettings Services from database with owner name
    public Service(int serviceID, int vehicleID, String owner, String licencePlate, String description, LocalDate serviceDate, float price)
    {
        this.serviceID = serviceID;
        this.vehicleID = vehicleID;
        this.owner = owner;
        this.licencePlate = licencePlate;
        this.description = description;
        this.serviceDate = serviceDate;
        this.price = price;
        this.recordStatus = 1;
    }

    // constructor with no service ID, add to database, let database assign service ID
    public Service(int vehicleID, String description, LocalDate serviceDate, float price)
    {
        serviceID = 1; //used for validation
        this.vehicleID = vehicleID;
        this.description = description;
        this.serviceDate = serviceDate;
        this.price = price;
        this.recordStatus = 1;
    }
    
    
    
    //get and set methods
    public int getServiceID()
    {
        return serviceID;
    }

    public void setServiceID(int serviceID)
    {
        this.serviceID = serviceID;
    }

    public int getVehicleID()
    {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID)
    {
        this.vehicleID = vehicleID;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getLicencePlate()
    {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate)
    {
        this.licencePlate = licencePlate;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDate getServiceDate()
    {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate)
    {
        this.serviceDate = serviceDate;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public int getRecordStatus()
    {
        return recordStatus;
    }

    public void setRecordStatus(int recordStatus)
    {
        this.recordStatus = recordStatus;
    }
    
}
