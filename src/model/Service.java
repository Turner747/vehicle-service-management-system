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
    
    // full constructor for gettings Services from database, no licence plate
    public Service(int serviceID, int vehicleID, String licencePlate, String description, LocalDate serviceDate, float price)
    {
        this.serviceID = serviceID;
        this.vehicleID = vehicleID;
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
    
    public void setServiceDate(LocalDate serviceDate)
    {
        this.serviceDate = serviceDate;
    }
    
    public LocalDate getServiceDate()
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

    public int getRecordStatus()
    {
        return recordStatus;
    }

    public void setRecordStatus(int recordStatus)
    {
        this.recordStatus = recordStatus;
    }
       
}
