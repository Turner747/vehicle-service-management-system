
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.MessageView;

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
    
    //Add new customer to database
    public static void addCustomerToDB(Customer cust)
    {
        //SQL statement
        //String sql = "INSERT INTO CUSTOMER (" + cust.getFirstName() + "," + cust.getLastName() + "," + cust.getAddress() + "," + cust.getSuburb() + 
                    // "," + cust.getState() + "," + cust.getPostcode() + "," + cust.getPhoneNo() + ") values (?,?,?,?,?,?,?)";
        String sql = "INSERT INTO CUSTOMER (FIRSTNAME, LASTNAME, ADDRESS, SUBURB, STATE, POSTCODE, PHONENBR) values (?,?,?,?,?,?,?,?)";
        
        //run statement        
        try {
               
                PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                        
                ps.setString(2,cust.getFirstName());
                ps.setString(3, cust.getLastName());
                ps.setString(4, cust.getAddress());
                ps.setString(5, cust.getSuburb());
                ps.setString(6, cust.getState());
                ps.setString(7, cust.getPostcode());
                ps.setString(8, cust.getPhoneNo());
                
                ps.executeUpdate();
                MessageView.displayInfoMessage("Customer added");
        }
        
        catch (Exception e) {
            MessageView.displayError("Customer not added");
        }
    }
    
    //Edit customer and update in database
    public static void updateCustomerInDB(Customer cust)
    {
        //SQL statement
        //String sql = "UPDATE CUSTOMER SET FIRSTNAME=" + cust.getFirstName() + ", LASTNAME=" + cust.getLastName() + ", ADDRESS=" + cust.getAddress() + ", SUBURB=" + cust.getSuburb() + 
                    // ", STATE=" + cust.getState() + ", POSTCODE=" + cust.getPostcode() + ", PHONE=" + cust.getPhoneNo() + "WHERE CUSTOMERID=" + cust.getCustomerID();
        String sql = "UPDATE CUSTOMER SET FIRSTNAME=?, LASTNAME=?, ADDRESS=?, SUBURB=?, STATE=?, POSTCODE=?, PHONENBR=? WHERE CUSTOMERID=?";
        
        //run statement
        try {
            
            PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                
                ps.setString(1,cust.getFirstName());
                ps.setString(2, cust.getLastName());
                ps.setString(3, cust.getAddress());
                ps.setString(4, cust.getSuburb());
                ps.setString(5, cust.getState());
                ps.setString(6, cust.getPostcode());
                ps.setString(7, cust.getPhoneNo());
                ps.setInt(8, cust.getCustomerID());
                
                ps.executeUpdate();
                MessageView.displayInfoMessage("Customer details updated");
        }
        
        catch (Exception e) {
            MessageView.displayError("Customer details not updated");
        }
    }
    
    public static Customer getCustomerFromDB(int CustomerID)
    {
        Customer cust = new Customer();
        
        return cust;
    }
    
    //Get all customer details from database
    public static ObservableList<Customer> getCustomerListFromDB()
    {
        ArrayList<Customer> list = new ArrayList<>();
        
        //SQL statement
        String sql = "SELECT * FROM CUSTOMER;";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                Customer customer = new Customer(); 
                
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setAddress(rs.getString("Address"));
                customer.setSuburb(rs.getString("Suburb"));
                customer.setState(rs.getString("State"));
                customer.setPostcode(rs.getString("Postcode"));
                customer.setPhoneNo(rs.getString("PhoneNbr"));
                
                list.add(customer);
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayError("No customers found");
        }
                
        // covert array list to observable list
        ObservableList<Customer> customers = FXCollections.observableArrayList(list);
        return customers;
    }
    
    //Search for a customer in database by name or phone
    public static ObservableList<Customer> getCustomerListFromDB(String search)
    {
        ArrayList<Customer> list = new ArrayList<>();
        
        //SQL statement
        String sql = "SELECT * FROM CUSTOMER WHERE FIRSTNAME LIKE '%" + search + "%' OR LASTNAME LIKE '%" + search + "%' OR FIRSTNAME LIKE '%" + search + "%';";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                Customer customer = new Customer(); 
                
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setAddress(rs.getString("Address"));
                customer.setSuburb(rs.getString("Suburb"));
                customer.setState(rs.getString("State"));
                customer.setPostcode(rs.getString("Postcode"));
                customer.setPhoneNo(rs.getString("PhoneNbr"));
                
                list.add(customer);
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayError("No customer result found for " + search);
        }
        
        // covert array list to observable list
        ObservableList<Customer> customers = FXCollections.observableArrayList(list);
        return customers;
    }
    
    
    // vehicle methods
    
    //Add vehicle to database
    public static void addVehicleToDB(Vehicle veh)
    {
        //SQL statement
        //String sql = "INSERT INTO VEHICLE (" + veh.getOwnerID() + "," + veh.getMake() + "," + veh.getModel() + "," + veh.getYear() + 
                    // "," + veh.getOdometer() + ") values (?,?,?,?,?)";//need to include auto inc for vehicleID - possibly removing auto inc*****************
        String sql = "INSERT  INTO VEHICLE (OWNERID, MAKE, MODEL, YEAR, ODOMETER) VALUES (?,?,?,?,?)"; //INCLUDE VEHICLEID AS ABOVE^^^
        
        //run statement
        try {
            
            PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                
                ps.setInt(2, veh.getOwnerID());
                ps.setString(3, veh.getMake());
                ps.setString(4, veh.getModel());
                ps.setString(5, veh.getYear());
                ps.setInt(6, veh.getOdometer());
                
                ps.executeUpdate();
                MessageView.displayInfoMessage("Vehicle added");
        }
        
        catch (Exception e) {
            MessageView.displayError("Vehicle not added");
        }
    }
    
    public static void updateVehicleInDB(Vehicle veh)
    {
        //SQL statement
        //String sql = "UPDATE VEHICLE SET OWNERID=" + veh.getOwnerID() + ", MAKE=" + veh.getMake() + ", MODEL=" + veh.getModel() + ", YEAR=" + veh.getYear() + 
                     //", ODOMETER=" + veh.getOdometer() + "WHERE VEHICLEID=" + veh.getVehicleID();
        String sql = "UPDATE VEHICLE SET OWNERID=?, MAKE=?, MODEL=?, YEAR=?, ODOMETER=? WHERE VEHICLEID=?"; //INCLUDE VEHICLEID AS ABOVE^^^
        
        //run statement
        try {
            
            PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                
                ps.setInt(1, veh.getOwnerID());
                ps.setString(2, veh.getMake());
                ps.setString(3, veh.getModel());
                ps.setString(4, veh.getYear());
                ps.setInt(5, veh.getOdometer());
                ps.setInt(6, veh.getVehicleID());
                
                ps.executeUpdate();
                MessageView.displayInfoMessage("Vehicle added");
        }
        
        catch (Exception e) {
            MessageView.displayError("Vehicle not added");
        }
    }
    
    //
    public static Vehicle getVehicleFromDB(int VehicleID)
    {
        Vehicle veh = new Vehicle();
        
        return veh;
    }
    
    //Get all vehicles from database
    public static ObservableList<Vehicle> getVehicleListFromDB()
    {
        ArrayList<Vehicle> list = new ArrayList<>();
        
        //SQL statement
        String sql = "SELECT * FROM VEHICLE;";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                Vehicle vehicle = new Vehicle(); 
                
                vehicle.setVehicleID(rs.getInt("VehicleID"));
                vehicle.setOwnerID(rs.getInt("OwnerID"));
                vehicle.setMake(rs.getString("Make"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setYear(rs.getString("Year"));
                vehicle.setOdometer(rs.getInt("Odometer"));
                
                list.add(vehicle);
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayError("No vehicles found");
        }
                
        // covert array list to observable list
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(list);
        return vehicles;
    }
    
    //what are we searching for???
    public static ObservableList<Vehicle> getVehicleListFromDB(String search)
    {
        ArrayList<Vehicle> list = new ArrayList<>();
        
        //SQL statement
        String sql = "SELECT * FROM VEHICLE WHERE ";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                Vehicle vehicle = new Vehicle(); 
                
                vehicle.setVehicleID(rs.getInt("VehicleID"));
                vehicle.setOwnerID(rs.getInt("OwnerID"));
                vehicle.setMake(rs.getString("Make"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setYear(rs.getString("Year"));
                vehicle.setOdometer(rs.getInt("Odometer"));
                
                list.add(vehicle);
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayError("Error message");
        }
                
        // covert array list to observable list
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(list);
        return vehicles;
    }
    
    // service methods
    
    public static void addServiceToDB(Service serv)
    {
        //SQL statement
        //String sql = "INSERT INTO SERVICE (" + serv.getVehicleID() + "," + serv.getDescription() + "," + serv.getServiceDate() + "," + serv.getPrice() + 
                     //") values (?,?,?,?)"; //need to include auto inc for serviceID******************
        String sql = "INSERT  INTO SERVICE (VEHICLEID, DESCRIPTION, SERVICEDATE, PRICE) VALUES (?,?,?,?,?)"; //auto in for serviceID AS ABOVE^^^
        
        //run statement
        try {
            
            PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                
                ps.setInt(2, serv.getVehicleID());
                ps.setString(3, serv.getDescription());
                ps.setDate(4, (java.sql.Date)serv.getServiceDate());
                ps.setFloat(5, serv.getPrice());
                              
                ps.executeUpdate();
                MessageView.displayInfoMessage("Service added for registration " + serv.getVehicleID());
        }
        
        catch (Exception e) {
            MessageView.displayError("Service not added for registration " + serv.getVehicleID());
        }
    }
    
    public static void updateServiceInDB(Service serv)
    {
        //SQL statement
        //String sql = "UPDATE SERVICE SET VEHICLEID=" + serv.getVehicleID() + ", DESCRIPTION=" + serv.getDescription() + ", SERVICEDATE=" + serv.getServiceDate() + ", PRICE=" + serv.getPrice() + 
                     //"WHERE SERVICEID=" + serv.getServiceID();
        String sql = "UPDATE SERVICE SET VEHICLEID=?, DESCRIPTION=?, SERVICEDATE=?, PRICE=? WHERE SERVICEID=?"; //auto in for serviceID AS ABOVE^^^
        
        //run statement
        try {
            
            PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                
                ps.setInt(1, serv.getVehicleID());
                ps.setString(2, serv.getDescription());
                ps.setDate(3, (java.sql.Date)serv.getServiceDate());
                ps.setFloat(4, serv.getPrice());
                ps.setInt(4, serv.getServiceID());
                
                ps.executeUpdate();
                MessageView.displayInfoMessage("Service updated");
        }
        
        catch (Exception e) {
            MessageView.displayError("Service not updated");
        }
    }
    
    public static void deleteServiceInDB(Service serv)
    {
        //SQL statement
        //String sql = "DELETE FROM SERVICE WHERE SERVICEID=" + serv.getServiceID();
        String sql = "DELETE FROM SERVICE WHERE SERVICEID=?";
        
        try {
            
            PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                
                ps.setInt(1, serv.getVehicleID());
                
                ps.executeUpdate();
                MessageView.displayInfoMessage("Service deleted for registration " + serv.getVehicleID());
        }
        
        catch (Exception e) {
            MessageView.displayError("Service not deleted for registration " + serv.getVehicleID());
        }
        
        
    }
    
    public static Service getServiceFromDB(int ServiceID)
    {
        Service serv = new Service();
                
        return serv;
    }
    
    //Get all services from database
    public static ObservableList<Service> getServiceListFromDB()
    {
        ArrayList<Service> list = new ArrayList<>();
        
         //SQL statement
        String sql = "SELECT * FROM SERVICE;";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                Service service = new Service();
                
                service.setServiceID(rs.getInt("ServiceID"));
                service.setVehicleID(rs.getInt("VehicleID"));
                service.setDescription(rs.getString("Description"));
                service.setServiceDate(rs.getDate("ServiceDate"));
                service.setPrice(rs.getFloat("Price")); //IS THIS THE CORRECT TYPE - SQL DB is decimal
                                
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayError("No services found");
        }
                       
        // covert array list to observable list
        ObservableList<Service> services = FXCollections.observableArrayList(list);
        return services;
    }
   
    //Search for service by vehicleID - REGO??
    public static ObservableList<Service> getServiceListFromDB(String search)
    {
        ArrayList<Service> list = new ArrayList<>();
        
        //SQL statement
        String sql = "SELECT * FROM SERVICE;";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                Service service = new Service(); 
                
                service.setServiceID(rs.getInt("ServiceID"));
                service.setVehicleID(rs.getInt("VehicleID"));
                service.setDescription(rs.getString("Description"));
                service.setServiceDate(rs.getDate("ServiceDate"));
                service.setPrice(rs.getFloat("Price")); //IS THIS THE CORRECT TYPE - SQL DB is decimal
                
                list.add(service);
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayError("No services found for " + search);
        }
                
        // covert array list to observable list
        ObservableList<Service> services = FXCollections.observableArrayList(list);
        return services;
    }
    
    
}
