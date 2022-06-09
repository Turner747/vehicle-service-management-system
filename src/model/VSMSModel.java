
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
    private static final String DATABASE = "CarServiceDB";
    private static final String USER = "root";
    private static final String PSWRD = "mySQLpassword";
    private static final String HOST = "localhost:3306";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    // database connection
    
    private static Connection estDatabaseConnection()
    {
        Connection conn = null;
        
        final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE;
		
        try {			
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER, PSWRD);

        } catch(Exception e){
            MessageView.displayException(e, "Database connection error");
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
        String sql = "INSERT INTO CUSTOMER (FIRSTNAME, LASTNAME, ADDRESS, SUBURB, STATE, POSTCODE, PHONE) values (?,?,?,?,?,?,?)";
        
        //run statement        
        try {
            
                Connection conn = estDatabaseConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                        
                ps.setString(1,cust.getFirstName());
                ps.setString(2, cust.getLastName());
                ps.setString(3, cust.getAddress());
                ps.setString(4, cust.getSuburb());
                ps.setString(5, cust.getState());
                ps.setString(6, cust.getPostcode());
                ps.setString(7, cust.getPhoneNo());
                
                ps.executeUpdate();
                MessageView.displayInfoMessage("Customer added");
                
                //conn.close();
        }
        
        catch (Exception e) {
            MessageView.displayException(e, "Error when adding customer to database");
        }
    }
    
    //Edit customer and update in database
    public static void updateCustomerInDB(Customer cust)
    {
        //SQL statement
        //String sql = "UPDATE CUSTOMER SET FIRSTNAME=" + cust.getFirstName() + ", LASTNAME=" + cust.getLastName() + ", ADDRESS=" + cust.getAddress() + ", SUBURB=" + cust.getSuburb() + 
                    // ", STATE=" + cust.getState() + ", POSTCODE=" + cust.getPostcode() + ", PHONE=" + cust.getPhoneNo() + "WHERE CUSTOMERID=" + cust.getCustomerID();
        String sql = "UPDATE CUSTOMER SET FIRSTNAME=?, LASTNAME=?, ADDRESS=?, SUBURB=?, STATE=?, POSTCODE=?, PHONE=? WHERE CUSTOMERID=?";
        
        //run statement
        try {
               
                //Connection conn = estDatabaseConnection();
                //PreparedStatement ps = conn.prepareStatement(sql);
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
            MessageView.displayException(e,"Error occurred when updating customer in database");
        }
    }
    
    public static Customer getCustomerFromDB(int customerId)
    {
        Customer cust = new Customer();
        
        //SQL statement
        String sql = "SELECT * FROM CUSTOMER WHERE CustomerID = " + customerId + ";";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                cust.setCustomerID(rs.getInt("CustomerID"));
                cust.setFirstName(rs.getString("FirstName"));
                cust.setLastName(rs.getString("LastName"));
                cust.setAddress(rs.getString("Address"));
                cust.setSuburb(rs.getString("Suburb"));
                cust.setState(rs.getString("State"));
                cust.setPostcode(rs.getString("Postcode"));
                cust.setPhoneNo(rs.getString("Phone"));
                
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayException(e,
                    "Error searching for customer with customer id of " + customerId);
        }
                
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
                customer.setPhoneNo(rs.getString("Phone"));
                
                list.add(customer);
            }                    
        }
        catch (Exception e) {
            MessageView.displayException(e, "No error when trying to customer list from the database");
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
        String sql = "SELECT * FROM CUSTOMER WHERE FIRSTNAME LIKE '%" + search + 
                                            "%' OR LASTNAME LIKE '%" + search +
                                            "%' OR CONCAT(FIRSTNAME, ' ', LASTNAME) LIKE '%" + search +
                                            "%' OR PHONE LIKE '" + search + 
                                            "' OR REPLACE(PHONE, ' ', '') LIKE '" + search + "';";
        
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
                customer.setPhoneNo(rs.getString("Phone"));
                
                list.add(customer);
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayException(e,"Error while searching customers in the database");
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
        String sql = "INSERT  INTO VEHICLE (OWNERID, MAKE, MODEL, YEAR, ODOMETER, LICENCEPLATE) VALUES (?,?,?,?,?,?)"; //INCLUDE VEHICLEID AS ABOVE^^^
        
        //run statement
        try {
            
            PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                
                ps.setInt(1, veh.getOwnerID());
                ps.setString(2, veh.getMake());
                ps.setString(3, veh.getModel());
                ps.setString(4, veh.getYear());
                ps.setInt(5, veh.getOdometer());
                ps.setString(6, veh.getLicencePlate());
                
                ps.executeUpdate();
                MessageView.displayInfoMessage("Vehicle added");
        }
        
        catch (Exception e) {
            MessageView.displayException(e, "Error when adding vehicle to database");
        }
    }
    
    public static void updateVehicleInDB(Vehicle veh)
    {
        //SQL statement
        //String sql = "UPDATE VEHICLE SET OWNERID=" + veh.getOwnerID() + ", MAKE=" + veh.getMake() + ", MODEL=" + veh.getModel() + ", YEAR=" + veh.getYear() + 
                     //", ODOMETER=" + veh.getOdometer() + "WHERE VEHICLEID=" + veh.getVehicleID();
        String sql = "UPDATE VEHICLE SET OWNERID=?, MAKE=?, MODEL=?, YEAR=?, ODOMETER=?, LICENCEPLATE=? WHERE VEHICLEID=?"; //INCLUDE VEHICLEID AS ABOVE^^^
        
        //run statement
        try {
            
            PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                
                ps.setInt(1, veh.getOwnerID());
                ps.setString(2, veh.getMake());
                ps.setString(3, veh.getModel());
                ps.setString(4, veh.getYear());
                ps.setInt(5, veh.getOdometer());
                ps.setString(6, veh.getLicencePlate());
                ps.setInt(7, veh.getVehicleID());
                
                ps.executeUpdate();
                MessageView.displayInfoMessage("Vehicle updated");
        }
        
        catch (Exception e) {
            MessageView.displayException(e, "Error when trying to update vehicle in database");
        }
    }
    
    //
    public static Vehicle getVehicleFromDB(int vehicleID)
    {
        Vehicle veh = new Vehicle();
        
        //SQL statement
        String sql = "SELECT\n" +
                     "	  V.VehicleID,\n" +
                     "    V.LicencePlate,\n" +
                     "    V.OwnerID,\n" +
                     "    CONCAT(C.FirstName, ' ', C.LastName) AS OwnerName,\n" +
                     "    V.Make,\n" +
                     "    V.Model,\n" +
                     "    V.Year,\n" +
                     "    V.Odometer\n" +
                     "FROM\n" +
                     "	VEHICLE AS V\n" +
                     "INNER JOIN\n" +
                     "	CUSTOMER AS C\n" +
                     "		ON C.CustomerID = V.OwnerID\n" +
                     "WHERE V.VehicleID = " + vehicleID + ";";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                veh.setVehicleID(rs.getInt("VehicleID"));
                veh.setLicencePlate(rs.getString("LicencePlate"));
                veh.setOwnerID(rs.getInt("OwnerID"));
                veh.setOwnerName(rs.getString("OwnerName"));                
                veh.setMake(rs.getString("Make"));
                veh.setModel(rs.getString("Model"));
                veh.setYear(rs.getString("Year"));
                veh.setOdometer(rs.getInt("Odometer"));
                
            }       
        }
        catch (Exception e) 
        {
            MessageView.displayException(e,"Error when getting vehicle from database");
        }
        
        return veh;
    }
    
    //Get all vehicles from database
    public static ObservableList<Vehicle> getVehicleListFromDB()
    {
        ArrayList<Vehicle> list = new ArrayList<>();
        
        //SQL statement
        String sql = "SELECT\n" +
                     "	  V.VehicleID,\n" +
                     "    V.LicencePlate,\n" +
                     "    V.OwnerID,\n" +
                     "    CONCAT(C.FirstName, ' ', C.LastName) AS OwnerName,\n" +
                     "    V.Make,\n" +
                     "    V.Model,\n" +
                     "    V.Year,\n" +
                     "    V.Odometer\n" +
                     "FROM\n" +
                     "	VEHICLE AS V\n" +
                     "INNER JOIN\n" +
                     "	CUSTOMER AS C\n" +
                     "		ON C.CustomerID = V.OwnerID;";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                Vehicle vehicle = new Vehicle(); 
                
                vehicle.setVehicleID(rs.getInt("VehicleID"));
                vehicle.setLicencePlate(rs.getString("LicencePlate"));
                vehicle.setOwnerID(rs.getInt("OwnerID"));
                vehicle.setOwnerName(rs.getString("OwnerName"));                
                vehicle.setMake(rs.getString("Make"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setYear(rs.getString("Year"));
                vehicle.setOdometer(rs.getInt("Odometer"));
                
                list.add(vehicle);
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayException(e,"Error when getting vehicle list from database");
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
        String sql = "SELECT\n" +
                     "	  V.VehicleID,\n" +
                     "    V.LicencePlate,\n" +
                     "    V.OwnerID,\n" +
                     "    CONCAT(C.FirstName, ' ', C.LastName) AS OwnerName,\n" +
                     "    V.Make,\n" +
                     "    V.Model,\n" +
                     "    V.Year,\n" +
                     "    V.Odometer\n" +
                     "FROM\n" +
                     "	VEHICLE AS V\n" +
                     "INNER JOIN\n" +
                     "	CUSTOMER AS C\n" +
                     "		ON C.CustomerID = V.OwnerID\n" +
                     "WHERE V.LicencePlate LIKE '%" + search + "%';";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                Vehicle vehicle = new Vehicle(); 
                
                vehicle.setVehicleID(rs.getInt("VehicleID"));
                vehicle.setLicencePlate(rs.getString("LicencePlate"));
                vehicle.setOwnerID(rs.getInt("OwnerID"));
                vehicle.setOwnerName(rs.getString("OwnerName"));
                vehicle.setMake(rs.getString("Make"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setYear(rs.getString("Year"));
                vehicle.setOdometer(rs.getInt("Odometer"));
                
                list.add(vehicle);
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayException(e,"Error while searching vehicle in database");
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
        String sql = "INSERT  INTO SERVICE (VEHICLEID, DESCRIPTION, SERVICEDATE, PRICE) VALUES (?,?,?,?)"; //auto in for serviceID AS ABOVE^^^
        
        //run statement
        try {
            
            PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                
                ps.setInt(1, serv.getVehicleID());
                ps.setString(2, serv.getDescription());
                ps.setObject(3, serv.getServiceDate());
                ps.setFloat(4, serv.getPrice());
                              
                ps.executeUpdate();
                MessageView.displayInfoMessage("Service added");
        }
        
        catch (Exception e) {
            MessageView.displayException(e,"Error occurred adding service to database");
        }
    }
    
    public static void updateServiceInDB(Service serv)
    {
        //SQL statement
        //String sql = "UPDATE SERVICE SET VEHICLEID=" + serv.getVehicleID() + ", DESCRIPTION=" + serv.getDescription() + ", SERVICEDATE=" + serv.getServiceDate() + ", PRICE=" + serv.getPrice() + 
                     //"WHERE SERVICEID=" + serv.getServiceID();
        String sql = "UPDATE SERVICE SET VEHICLEID=?, DESCRIPTION=?, SERVICEDATE=?, PRICE=?, RECORDSTATUS=? WHERE SERVICEID=?"; //auto in for serviceID AS ABOVE^^^
        
        //run statement
        try {
            
            PreparedStatement ps = estDatabaseConnection().prepareStatement(sql);
                
                ps.setInt(1, serv.getVehicleID());
                ps.setString(2, serv.getDescription());
                ps.setObject(3, serv.getServiceDate());
                ps.setFloat(4, serv.getPrice());
                ps.setInt(5, serv.getRecordStatus());
                ps.setInt(6, serv.getServiceID());
                
                ps.executeUpdate();
                MessageView.displayInfoMessage("Service updated");
        }
        
        catch (Exception e) {
            MessageView.displayException(e,"Error occurred update service in database");
        }
    }
    
    
    public static Service getServiceFromDB(int serviceID)
    {
        Service serv = new Service();
        
         //SQL statement
        String sql =    "SELECT\n" +
                        "    S.ServiceID,\n" +
                        "    S.ServiceDate,\n" +
                        "    S.VehicleID,\n" +
                        "    V.LicencePlate,\n" +
                        "    S.Description,\n" +
                        "    S.Price\n" +
                        "FROM\n" +
                        "	SERVICE AS S\n" +
                        "INNER JOIN\n" +
                        "	VEHICLE AS V\n" +
                        "		ON V.VehicleID = S.VehicleID\n" +
                        "WHERE RecordStatus = 1\n" +
                        "AND S.ServiceID = " + serviceID + ";";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 

                serv.setServiceID(rs.getInt("ServiceID"));
                serv.setServiceDate(rs.getDate("ServiceDate").toLocalDate());
                serv.setVehicleID(rs.getInt("VehicleID"));
                serv.setLicencePlate(rs.getString("LicencePlate"));
                serv.setDescription(rs.getString("Description"));
                serv.setPrice(rs.getFloat("Price")); //IS THIS THE CORRECT TYPE - SQL DB is decimal
                             
            }
                    
        }
        catch (Exception e) {
            MessageView.displayException(e,"Error occurred getting service from database");
        }
                       
                
        return serv;
    }
    
    //Get all services from database
    public static ObservableList<Service> getServiceListFromDB()
    {
        ArrayList<Service> list = new ArrayList<>();
        
         //SQL statement
        String sql =    "SELECT\n" +
                        "    S.ServiceID,\n" +
                        "    S.ServiceDate,\n" +
                        "    S.VehicleID,\n" +
                        "    V.LicencePlate,\n" +
                        "    S.Description,\n" +
                        "    S.Price\n" +
                        "FROM\n" +
                        "	SERVICE AS S\n" +
                        "INNER JOIN\n" +
                        "	VEHICLE AS V\n" +
                        "		ON V.VehicleID = S.VehicleID\n" +
                        "WHERE RecordStatus = 1;";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                Service service = new Service();
                
                service.setServiceID(rs.getInt("ServiceID"));
                service.setServiceDate(rs.getDate("ServiceDate").toLocalDate());
                service.setVehicleID(rs.getInt("VehicleID"));
                service.setLicencePlate(rs.getString("LicencePlate"));
                service.setDescription(rs.getString("Description"));
                service.setPrice(rs.getFloat("Price")); //IS THIS THE CORRECT TYPE - SQL DB is decimal
                
                list.add(service);
                                
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayException(e,"Error occurred getting list of services from database");
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
        String sql =    "SELECT\n" +
                        "    S.ServiceID,\n" +
                        "    S.ServiceDate,\n" +
                        "    S.VehicleID,\n" +
                        "    V.LicencePlate,\n" +
                        "    S.Description,\n" +
                        "    S.Price\n" +
                        "FROM\n" +
                        "	SERVICE AS S\n" +
                        "INNER JOIN\n" +
                        "	VEHICLE AS V\n" +
                        "		ON V.VehicleID = S.VehicleID\n" +
                        "WHERE RecordStatus = 1\n" +
                        "AND V.LicencePlate LIKE '%" + search + "%';";
        
        //run statement        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            //loop through database results
            while(rs.next()) { 
                
                Service service = new Service(); 
                
                service.setServiceID(rs.getInt("ServiceID"));
                service.setServiceDate(rs.getDate("ServiceDate").toLocalDate());
                service.setVehicleID(rs.getInt("VehicleID"));
                service.setLicencePlate(rs.getString("LicencePlate"));
                service.setDescription(rs.getString("Description"));
                service.setPrice(rs.getFloat("Price")); //IS THIS THE CORRECT TYPE - SQL DB is decimal
                
                list.add(service);
            }
                    
        }
        
        catch (Exception e) {
            MessageView.displayException(e,"Error occurred while searching database for service");
        }
                
        // covert array list to observable list
        ObservableList<Service> services = FXCollections.observableArrayList(list);
        return services;
    }
    
    //Get data for service prices
    public static ArrayList<Integer> serviceReportStats()
    {
        ArrayList<Integer> listReport = new ArrayList();       
        //SQL statement
        String sql = "SELECT MIN(PRICE), MAX(PRICE), AVG(PRICE) FROM SERVICE";
        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

                //loop through database results
            while(rs.next()) { 

                listReport.add( rs.getInt("MIN(PRICE)"));
                listReport.add(rs.getInt("MAX(PRICE)"));
                listReport.add(rs.getInt("AVG(PRICE)"));
                                        
            }
                
        }
        
        catch (Exception e) {
            MessageView.displayException(e,"Error occurred while displaying service prices");
        }
        
        return listReport;        
    }
    
    //Get data for service numbers by make
    public static ObservableList<MakeStatTableItem> serviceReportByMake()
    {
        ArrayList<MakeStatTableItem> list = new ArrayList<>();        
        //SQL statement
        String sql = "SELECT V.MAKE, COUNT(V.MAKE)AS SERVICES FROM SERVICE AS S, VEHICLE AS V WHERE S.VEHICLEID = V.VEHICLEID GROUP BY V.MAKE";
        
        try {
            
                           
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            //loop through database results
            while(rs.next()) { 

                list.add(new MakeStatTableItem (
                        rs.getString("MAKE"),
                        rs.getInt("SERVICES")));
            }
                  
        }
        
        catch (Exception e) {
            MessageView.displayError("Error occurred while displaying service report for number of services by make ");
        }
        
        ObservableList<MakeStatTableItem> makeStats = FXCollections.observableArrayList(list);
        return makeStats;   
    }
    
    //Get data for top 3 makes by services
    public static ObservableList<MakeStatTableItem> serviceReportTopMakes()
    {
        //ObservableList<MakeStatTableItem>
        ArrayList<MakeStatTableItem> list = new ArrayList<>();   
        ArrayList<String> listReport = new ArrayList();
        //SQL statement
        String sql = "SELECT V.MAKE, COUNT(V.MAKE) as SERVICES FROM SERVICE AS S, VEHICLE AS V WHERE S.VEHICLEID = V.VEHICLEID GROUP BY V.MAKE ORDER BY COUNT(V.MAKE) DESC LIMIT 3";
        
        try {
            Statement st = estDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            //loop through database results
            while(rs.next()) { 

                list.add(new MakeStatTableItem (
                        rs.getString("MAKE"),
                        rs.getInt("SERVICES")));
            }

        }
                
        catch (Exception e) {
            MessageView.displayError("Error occurred while displaying top 3 makes by number of servives");
        }
        
        ObservableList<MakeStatTableItem> makeStats = FXCollections.observableArrayList(list);
        return makeStats;        
    }
    
}
