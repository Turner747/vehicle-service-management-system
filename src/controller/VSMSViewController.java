package controller;

/*
 * Joshua Turner 
 * S0258441
 * Isabel Rigby
 * S0077632
 * Assignment 3
 * Software Design & Development
 * COIT12200
 */

import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TableColumn<Vehicle, String> ownerCol;    
    
    @FXML
    private TableColumn<Vehicle, String> makeCol;

    @FXML
    private TableColumn<Vehicle, String> modelCol;
    
    @FXML
    private TableColumn<Vehicle, String> yearCol;

    @FXML
    private TableColumn<Vehicle, Integer> odometerCol;

    


    @FXML
    void aboutBtnClicked(ActionEvent event) {
        MessageView.displayAboutDialog();
    }

    @FXML
    void addCustomerBtnClicked(ActionEvent event) {

    }

    @FXML
    void addServiceBtnClicked(ActionEvent event) {

    }

    @FXML
    void addVehicleBtnClicked(ActionEvent event) {

    }

    @FXML
    void editCustomerBtnClicked(ActionEvent event) {

    }

    @FXML
    void editServiceBtnClicked(ActionEvent event) {

    }

    @FXML
    void editVehicleBtnClicked(ActionEvent event) {

    }

    @FXML
    void quitBtnClicked(ActionEvent event) {

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

}
