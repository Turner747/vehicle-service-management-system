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

public class Customer
{
    private int customerID;
    private String firstName;
    private String lastName;
    private String address;
    private String suburb;
    private String state;
    private String postcode;
    private String phoneNo;
    
    public Customer() {
        //no-argument constructor
    }
    
    // full contructor to be used when getting values for database
    public Customer(int customerID, String firstName, String lastName, String address, String suburb, String state, String postcode, String phoneNo)
    {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        this.phoneNo = phoneNo;
    }
    
    
    //constructor with no Customer ID, add to database, let database assign Customer ID
    public Customer(String firstName, String lastName, String address, String suburb, String state, String postcode, String phoneNo)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        this.phoneNo = phoneNo;
    }
    
    //set and get methods
    public void setCustomerID(int customerID) 
    {
        this.customerID = customerID;
    }
    
    public int getCustomerID() 
    {
        return customerID;
    }
    
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }
    
    public String getFirstName() 
    {
        return firstName;
    }
    
     public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }
    
    public String getLastName() 
    {
        return lastName;
    }
    
     public void setAddress(String address) 
    {
        this.address = address;
    }
    
    public String getAddress() 
    {
        return address;
    }
    
     public void setSuburb (String suburb) 
    {
        this.suburb = suburb;
    }
    
    public String getSuburb() 
    {
        return suburb;
    }
    
     public void setState(String state) 
    {
        this.state = state;
    }
    
    public String getState() 
    {
        return state;
    }
    
     public void setPostcode(String postcode) 
    {
        this.postcode = postcode;
    }
    
    public String getPostcode() 
    {
        return postcode;
    }
    
     public void setPhoneNo(String phoneNo) 
    {
        this.phoneNo = phoneNo;
    }
    
    public String getPhoneNo() 
    {
        return phoneNo;
    }
     
}
