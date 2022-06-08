package controller;

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

import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import view.*;

public class VSMSViewController {
    
    //common buttons
    @FXML
    private Button aboutBtn;

    @FXML
    private Button quitBtn;
    
    @FXML
    private Button serviceReportBtn;

    
    //customer tab
    @FXML
    private Button addCustomerBtn;
    
    @FXML
    private Button editCustomerBtn;
    
    @FXML
    private Button searchCustomerBtn;

    @FXML
    private TextField searchCustomerTxtField;
    
    @FXML
    private Button refreshCustomerBtn;
    
    
    // customer table
    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, Integer> customerIdCol;
    
    @FXML
    private TableColumn<Customer, String> firstNameCol;

    @FXML
    private TableColumn<Customer, String> lastNameCol;
    
    @FXML
    private TableColumn<Customer, String> addressCol;
    
    @FXML
    private TableColumn<Customer, String> suburbCol;
    
    @FXML
    private TableColumn<Customer, String> stateCol;
    
    @FXML
    private TableColumn<Customer, String> postcodeCol;
    
    @FXML
    private TableColumn<Customer, String> phoneCol;
    
    
    //service tab
    @FXML
    private Button addServiceBtn;
    
    @FXML
    private Button editServiceBtn;
    
    @FXML
    private Button cancelServiceBtn;
    
    @FXML
    private Button searchServiceBtn;

    @FXML
    private TextField searchServiceTxtField;
    
    @FXML
    private Button refreshServiceBtn;
    
    
    //service table
    @FXML
    private TableView<Service> serviceTableView;
    
    @FXML
    private TableColumn<Service, Integer> serviceIdCol;
    
    @FXML
    private TableColumn<Service, Date> serviceDateCol;

    @FXML
    private TableColumn<Service, String> serviceVehicleCol;

    @FXML
    private TableColumn<Service, String> descriptionCol;

    @FXML
    private TableColumn<Service, Float> priceCol;

    
    //vehicle tab
     @FXML
    private Button addVehicleBtn;
    
    @FXML
    private Button editVehicleBtn;

    @FXML
    private Button searchVehicleBtn;

    @FXML
    private TextField searchVehicleTxtField;
    
    @FXML
    private Button refreshVehicleBtn;

    
    //vehicle table
    @FXML
    private TableView<Vehicle> vehicleTableView;
    
    @FXML
    private TableColumn<Vehicle, Integer> vehicleIdCol;
    
    @FXML
    private TableColumn<Vehicle, String> licencePlateCol;

    @FXML
    private TableColumn<Vehicle, String> ownerCol;    
    
    @FXML
    private TableColumn<Vehicle, String> makeCol;

    @FXML
    private TableColumn<Vehicle, String> modelCol;
    
    @FXML
    private TableColumn<Vehicle, String> yearCol;

    @FXML
    private TableColumn<Vehicle, Integer> odometerCol;

    // manage report tab
    
    @FXML
    private TextField minStatTxtField;
    
    @FXML
    private TextField maxStatTxtField;
    
    @FXML
    private TextField avgStatTxtField;
    
    @FXML
    private BarChart<String, Number> brandBarChart;
    
    @FXML
    private TableView<MakeStatTableItem> serviceMakeStatTable;
    
    @FXML
    private TableColumn<MakeStatTableItem, String> makeStatCol;

    @FXML
    private TableColumn<MakeStatTableItem, Integer> serviceStatCol;
    

    @FXML
    void initialize() 
    {
        // construct customer table view
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        suburbCol.setCellValueFactory(new PropertyValueFactory<>("suburb"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        postcodeCol.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        
        // construct vehicle table view
        vehicleIdCol.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        licencePlateCol.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));
        ownerCol.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        makeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        odometerCol.setCellValueFactory(new PropertyValueFactory<>("odometer"));
        
        
        // construct vehicle table view
        serviceIdCol.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
        serviceDateCol.setCellValueFactory(new PropertyValueFactory<>("serviceDate"));
        serviceVehicleCol.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        // construct report stat table view
        makeStatCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        serviceStatCol.setCellValueFactory(new PropertyValueFactory<>("nbrOfServices"));
        
        // construct report stat bar chart        
        brandBarChart.setLegendVisible(false);
                
        // load table data
        refreshCustomerTable();
        refreshVehicleTable();
        refreshServiceTable();
        refreshReportsTab();
                
    }
    
    @FXML
    void aboutBtnClicked(ActionEvent event) {
        MessageView.displayAboutDialog();
    }

    @FXML
    void addCustomerBtnClicked(ActionEvent event) {
        
        Customer newCust = MessageView.displayNewCustomerDialog();
        
        if(newCust.getCustomerID() != 0)
            VSMSModel.addCustomerToDB(newCust);
        
        refreshCustomerTable();
        
    }
    
    @FXML
    void refreshCustomerBtnClicked(ActionEvent event) {
        
        refreshCustomerTable();
        
    }

    @FXML
    void addServiceBtnClicked(ActionEvent event) {

        Service newServ = MessageView.displayNewServiceDialog();
        
        if(newServ.getServiceID() != 0)
            VSMSModel.addServiceToDB(newServ);
        
        refreshServiceTable();
    }
    
    @FXML
    void refreshServiceBtnClicked(ActionEvent event) {

        refreshServiceTable();
    }

    @FXML
    void addVehicleBtnClicked(ActionEvent event) {

        Vehicle newVeh = MessageView.displayNewVehicleDialog();
        
        if(newVeh.getVehicleID() != 0)
            VSMSModel.addVehicleToDB(newVeh);
        
        refreshVehicleTable();
    }

    @FXML
    void editCustomerBtnClicked(ActionEvent event) {
        
        Customer selectedCust = customerTableView.getSelectionModel().getSelectedItem(); // live code
        //Customer selectedCust = VSMSModel.getCustomerFromDB(2); // testing purposes
        
        Customer updatedCust = MessageView.displayUpdateCustomerDialog(selectedCust);
        
        if(updatedCust.getCustomerID() != 0)
            VSMSModel.updateCustomerInDB(updatedCust);
        
        refreshCustomerTable();

    }

    @FXML
    void editServiceBtnClicked(ActionEvent event) {

        Service selectedServ = serviceTableView.getSelectionModel().getSelectedItem(); // live code
        //Service selectedServ = VSMSModel.getServiceFromDB(1); // testing purposes
        
        Service updatedServ = MessageView.displayUpdateServiceDialog(selectedServ);
        
        if(updatedServ.getServiceID() != 0)
            VSMSModel.updateServiceInDB(updatedServ);
        
        refreshServiceTable();
        
    }
    
    @FXML
    void cancelServiceBtnClicked(ActionEvent event) {

        Service selectedServ = serviceTableView.getSelectionModel().getSelectedItem(); // live code
        //Service selectedServ = VSMSModel.getServiceFromDB(2); // testing purposes
        
        boolean confirmed = MessageView.displayConfirmDialog(event,
                "Are you sure you want to cancel this service?");
        
        if(confirmed)
        {
            selectedServ.setRecordStatus(0);

            VSMSModel.updateServiceInDB(selectedServ);
        }
        
        refreshServiceTable();
    }

    @FXML
    void editVehicleBtnClicked(ActionEvent event) {

        Vehicle selectedVeh = vehicleTableView.getSelectionModel().getSelectedItem(); // live code
        //Vehicle selectedVeh = VSMSModel.getVehicleFromDB(4); //testing purposes
        
        Vehicle updatedVeh = MessageView.displayUpdateVehicleDialog(selectedVeh);
        
        if(updatedVeh.getVehicleID() != 0)
            VSMSModel.updateVehicleInDB(updatedVeh);
        
        refreshVehicleTable();
        
    }
    
    @FXML
    void refreshVehicleBtnClicked(ActionEvent event) 
    {

        refreshVehicleTable();
        
    }

    @FXML
    void quitBtnClicked(ActionEvent event) {
        MessageView.displayExitDialog(event);
    }

    @FXML
    void searchCustomerBtnClicked(ActionEvent event) {
        try{
            ObservableList<Customer> customerList = 
                VSMSModel.getCustomerListFromDB(searchCustomerTxtField.getText());      // search database with string from search field


            if(customerList.isEmpty())
               customerTableView.setPlaceholder(new Label("No customers found"));       // if the return list is empty display message in table
                
            customerTableView.setItems(customerList);   // update table with search results
            

        }catch(Exception e){
            MessageView.displayException(e, "Error loading search results");        // display is error occurs when searchihg database
        }
    }

    @FXML
    void searchServiceBtnClicked(ActionEvent event) {
        try{
            ObservableList<Service> serviceList = 
                VSMSModel.getServiceListFromDB(searchServiceTxtField.getText());      // search database with string from search field


            if(serviceList.isEmpty())
               serviceTableView.setPlaceholder(new Label("No services found"));       // if the return list is empty display message in table
                
            serviceTableView.setItems(serviceList);   // update table with search results
            

        }catch(Exception e){
            MessageView.displayException(e, "Error loading search results");        // display is error occurs when searchihg database
        }
    }

    @FXML
    void searchVehicleBtnClicked(ActionEvent event) {
        try{
            ObservableList<Vehicle> vehicleList = 
                VSMSModel.getVehicleListFromDB(searchVehicleTxtField.getText());      // search database with string from search field


            if(vehicleList.isEmpty())
               vehicleTableView.setPlaceholder(new Label("No vehicles found"));       // if the return list is empty display message in table
                
            vehicleTableView.setItems(vehicleList);   // update table with search results
            

        }catch(Exception e){
            MessageView.displayException(e, "Error loading search results");        // display is error occurs when searchihg database
        }
    }

    private void refreshReportsTab(){
        
        // service prices statistics
        minStatTxtField.setText(VSMSModel.serviceReportStats().get(0).toString());
        maxStatTxtField.setText(VSMSModel.serviceReportStats().get(1).toString());
        avgStatTxtField.setText(VSMSModel.serviceReportStats().get(2).toString());
        
        // number of services by make statistics
        ObservableList<MakeStatTableItem> statList = VSMSModel.serviceReportByMake(); // get make stats for table from database
        serviceMakeStatTable.setItems(statList);
        
        // top 3 brands by make statistics
        ObservableList<MakeStatTableItem> statChart = VSMSModel.serviceReportTopMakes(); // get make stats for bar chart from database
        
        XYChart.Series sr = new XYChart.Series();
        
        int max = 3;
        
        for (int i=0; i < max; i++) {        
        
        sr.getData().add(new XYChart.Data(statChart.get(i).getMake(),statChart.get(i).getNbrOfServices()));
        
        }
        brandBarChart.getData().addAll(sr);
        
    }
    
    private void refreshCustomerTable(){

        try{
            ObservableList<Customer> customerList = VSMSModel.getCustomerListFromDB(); // get customers from database

            if(customerList.isEmpty())     // if the return list is empty display message in table
               customerTableView.setPlaceholder(new Label("No customers have been entered into the database"));

            customerTableView.setItems(customerList); // display customers in table

        }catch(Exception e){
            MessageView.displayException(e, "Error loading customer table"); // show error message
        }
    }
    
    private void refreshVehicleTable(){

        try{
            ObservableList<Vehicle> vehicleList = VSMSModel.getVehicleListFromDB(); // get vehicles from database

            if(vehicleList.isEmpty())     // if the return list is empty display message in table
               vehicleTableView.setPlaceholder(new Label("No vehicles have been entered into the database"));

            vehicleTableView.setItems(vehicleList); // display vehicles in table

        }catch(Exception e){
            MessageView.displayException(e, "Error loading vehicle table"); // show error message
        }
    }
    
    private void refreshServiceTable(){

        try{
            ObservableList<Service> serviceList = VSMSModel.getServiceListFromDB(); // get services from database

            if(serviceList.isEmpty())     // if the return list is empty display message in table
               serviceTableView.setPlaceholder(new Label("No services have been entered into the database"));

            serviceTableView.setItems(serviceList); // display services in table

        }catch(Exception e){
            MessageView.displayException(e, "Error loading services table"); // show error message
        }
    }
    
}
