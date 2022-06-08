package model;

/*
 * Joshua Turner 
 * S0258441
 * Isabel Rigby
 * S0077632
 * Assignment 3
 * Software Design & Development
 * COIT12200
 */


public class MakeStatTableItem
{
    private String make;
    private int nbrOfServices;
         
    public MakeStatTableItem(String make, int nbrOfServices)
    {
        this.make = make;
        this.nbrOfServices = nbrOfServices;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public int getNbrOfServices()
    {
        return nbrOfServices;
    }

    public void setNbrOfServices(int nbrOfServices)
    {
        this.nbrOfServices = nbrOfServices;
    }
}
