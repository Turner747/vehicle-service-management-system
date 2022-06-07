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

import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    void initialize() {
        
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
        
    }

    @FXML
    void addServiceBtnClicked(ActionEvent event) {

        Service newServ = MessageView.displayNewServiceDialog();
        
        if(newServ.getServiceID() != 0)
            VSMSModel.addServiceToDB(newServ);
    }

    @FXML
    void addVehicleBtnClicked(ActionEvent event) {

        Vehicle newVeh = MessageView.displayNewVehicleDialog();
        
        if(newVeh.getVehicleID() != 0)
            VSMSModel.addVehicleToDB(newVeh);
    }

    @FXML
    void editCustomerBtnClicked(ActionEvent event) {
        
        // Customer selectedCust = customerTableView.getSelectionModel().getSelectedItem(); // live code
        Customer selectedCust = VSMSModel.getCustomerFromDB(2); // testing purposes
        
        Customer updatedCust = MessageView.displayUpdateCustomerDialog(selectedCust);
        
        if(updatedCust.getCustomerID() != 0)
            VSMSModel.updateCustomerInDB(updatedCust);

    }

    @FXML
    void editServiceBtnClicked(ActionEvent event) {

        // Service selectedServ = serviceTableView.getSelectionModel().getSelectedItem(); // live code
        Service selectedServ = VSMSModel.getServiceFromDB(2); // testing purposes
        
        Service updatedServ = MessageView.displayUpdateServiceDialog(selectedServ);
        
        if(updatedServ.getServiceID() != 0)
            VSMSModel.updateServiceInDB(updatedServ);
        
    }
    
    @FXML
    void cancelServiceBtnClicked(ActionEvent event) {

        // Service selectedServ = serviceTableView.getSelectionModel().getSelectedItem(); // live code
        Service selectedServ = VSMSModel.getServiceFromDB(2); // testing purposes
        
        boolean confirmed = MessageView.displayConfirmDialog(event,
                "Are you sure you want to cancel this service?");
        
        if(confirmed)
        {
            selectedServ.setRecordStatus(0);

            VSMSModel.updateServiceInDB(selectedServ);
        }
        
        
    }

    @FXML
    void editVehicleBtnClicked(ActionEvent event) {

        //Vehicle selectedVeh = vehicleTableView.getSelectionModel().getSelectedItem(); // live code
        Vehicle selectedVeh = VSMSModel.getVehicleFromDB(4); //testing purposes
        
        Vehicle updatedVeh = MessageView.displayUpdateVehicleDialog(selectedVeh);
        
        if(updatedVeh.getVehicleID() != 0)
            VSMSModel.updateVehicleInDB(updatedVeh);
        
    }

    @FXML
    void quitBtnClicked(ActionEvent event) {
        MessageView.displayExitDialog(event);
    }

    @FXML
    void searchCustomerBtnClicked(ActionEvent event) {

    }

    @FXML
    void searchServiceBtnClicked(ActionEvent event) {

    }

    @FXML
    void searchVehicleBtnClicked(ActionEvent event) {

    }

    @FXML
    void serviceReportBtnClicked(ActionEvent event) {

    }

    private static void refreshReportsTab(){
        
    }
    
}
