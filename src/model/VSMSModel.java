
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VSMSModel
{
    private static final String DATABASE = "";
    private static final String USER = "";
    private static final String PSWRD = "";
    private static final String HOST = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    // database connection
    
    private static Connection estDatabaseConnection()
    {
        Connection conn = null;
        
        String myUrl = "jdbc:mysql://" + HOST + "/" + DATABASE;
		
        try {			
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(myUrl, USER, PSWRD);

        } catch(Exception e){
                // display exception message
        } 

        return conn;
    }
    
    
    // customer methods
    
    public static void addCustomerToDB(Customer cust)
    {
        
    }
    
    public static void updateCustomerInDB(Customer cust)
    {
        
    }
    
    public static Customer getCustomerFromDB(int CustomerID)
    {
        Customer cust = new Customer();
        
        
        
        return cust;
    }
    
    public static ObservableList<Customer> getCustomerListFromDB()
    {
        ArrayList<Customer> list = new ArrayList<>();
        
        
        
        
        
        
        
        // covert array list to observable list
        ObservableList<Customer> customers = FXCollections.observableArrayList(list);
        return customers;
    }
    
    public static ObservableList<Customer> getCustomerListFromDB(String search)
    {
        ArrayList<Customer> list = new ArrayList<>();
        
        
        
        
        
        
        
        // covert array list to observable list
        ObservableList<Customer> customers = FXCollections.observableArrayList(list);
        return customers;
    }
    
    
    
    // vehicle methods
    
    public static void addVehicleToDB(Vehicle veh)
    {
        
    }
    
    public static void updateVehicleInDB(Vehicle veh)
    {
        
    }
    
    public static Vehicle getVehicleFromDB(int VehicleID)
    {
        Vehicle veh = new Vehicle();
        
        
        
        return veh;
    }
    
    public static ObservableList<Vehicle> getVehicleListFromDB()
    {
        ArrayList<Vehicle> list = new ArrayList<>();
        
        
        
        
        
        
        
        // covert array list to observable list
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(list);
        return vehicles;
    }
    
    public static ObservableList<Vehicle> getVehicleListFromDB(String search)
    {
        ArrayList<Vehicle> list = new ArrayList<>();
        
        
        
        
        
        
        
        // covert array list to observable list
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(list);
        return vehicles;
    }
    
    
    
    // service methods
    
    public static void addServiceToDB(Service serv)
    {
        
    }
    
    public static void updateServiceInDB(Service serv)
    {
        
    }
    
    public static Service getServiceFromDB(int ServiceID)
    {
        Service serv = new Service();
        
        
        
        return serv;
    }
    
    public static ObservableList<Service> getServiceListFromDB()
    {
        ArrayList<Service> list = new ArrayList<>();
        
        
        
        
        
        
        
        // covert array list to observable list
        ObservableList<Service> services = FXCollections.observableArrayList(list);
        return services;
    }
    
    public static ObservableList<Service> getServiceListFromDB(String search)
    {
        ArrayList<Service> list = new ArrayList<>();
        
        
        
        
        
        
        
        // covert array list to observable list
        ObservableList<Service> services = FXCollections.observableArrayList(list);
        return services;
    }
    
    
    
    
    
    
    
    
}
