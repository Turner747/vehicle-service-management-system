package view;

import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Customer;
import model.Service;
import model.VSMSModel;
import model.Vehicle;

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


public class MessageView
{
    private static final String WINDOW_TITLE = "Vehicle Service Management System";
    
    // required for vehicle creation
    private static Customer owner; // temp object used for selecting over of vehicles
    private static TextField ownerTextField; // display owner name
    private static TableView<Customer> ownerTableView; // used for display owner in owner selection
    
    // required for service creation and updating
    private static Vehicle serviceVeh; // temp object used for selecting over of vehicles
    private static TextField vehicleTextField; // display owner name
    private static TableView<Vehicle> vehicleTableView; // used for display owner in owner selection
    
    
    public static void displayInfoMessage(String message)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        a.setTitle(WINDOW_TITLE);
        a.setContentText(message);
        a.show();
    }
    
    
    public static void displayExitDialog(Event e){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, 
                    "Are you sure you want to quit?", 
                    ButtonType.YES,
                    ButtonType.NO);
        a.setTitle(WINDOW_TITLE);
        a.setContentText("Are you sure you want to quit the application?");
        
        ButtonType closeAllButton = new ButtonType("Quit application");
        
        a.getButtonTypes().setAll(closeAllButton, ButtonType.CANCEL);
        
        Optional<ButtonType> confirm = a.showAndWait();
        if (confirm.isPresent() && confirm.get() == closeAllButton){
            
            System.exit(0);
        }
        else {
            e.consume();
        }
        
    }

    
    public static void displayError(String message){
        Alert a = new Alert(Alert.AlertType.ERROR, message);
        
        a.setTitle(WINDOW_TITLE);
        a.setHeaderText("Error");
        a.show();
    }

    public static void displayException(Throwable throwable, String message) 
    {
        // print the stack trace to the console
        //throwable.printStackTrace();

        // create aleart window and set titles
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(WINDOW_TITLE);
        alert.setHeaderText("Exception Thrown");
        alert.setContentText(message);

        // create stack trace string
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Stacktrace details:");

        // create text area and add stacktrace string
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        
        // set size and behaviour of text area
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        // create new gridpane and add the label and text area
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // add the above gridpane to the alert window
        alert.getDialogPane().setExpandableContent(expContent);

        // display the alert
        alert.showAndWait();
    }
    
    // customer windows

    public static Customer displayNewCustomerDialog()
    {
        Customer cust = null;
        
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle(WINDOW_TITLE + " - Add Customer");      // add title
        dialog.setHeaderText("Enter Customer Details");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Add", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("Enter first name");

        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("Enter last name");

        TextField addressTextField = new TextField();
        addressTextField.setPromptText("Enter address");
        
        TextField suburbTextField = new TextField();
        suburbTextField.setPromptText("Enter suburb");
        
        TextField stateTextField = new TextField();
        stateTextField.setPromptText("Enter state");
        
        TextField postcodeTextField = new TextField();
        postcodeTextField.setPromptText("Enter postcode");
        
        TextField phoneTextField = new TextField();
        phoneTextField.setPromptText("Enter phone number");
        
        
        
        // add labels and textfields to the gridpane
        grid.add(new Label("First name"), 0, 0);
        grid.add(firstNameTextField, 1, 0);
        grid.add(new Label("*"), 2, 0);
        
        grid.add(new Label("Last name"), 0, 1);
        grid.add(lastNameTextField, 1, 1);
        grid.add(new Label("*"), 2, 1);        
        
        grid.add(new Label("Address"), 0, 2);
        grid.add(addressTextField, 1, 2);
        grid.add(new Label("*"), 2, 2);
        
        grid.add(new Label("Suburb"), 0, 3);
        grid.add(suburbTextField, 1, 3);
        grid.add(new Label("*"), 2, 3);
        
        grid.add(new Label("State"), 0, 4);
        grid.add(stateTextField, 1, 4);
        grid.add(new Label("*"), 2, 4);
        
        grid.add(new Label("Postcode"), 0, 5);
        grid.add(postcodeTextField, 1, 5);
        grid.add(new Label("*"), 2, 5);
        
        grid.add(new Label("Phone"), 0, 6);
        grid.add(phoneTextField, 1, 6);
        grid.add(new Label("*"), 2, 6);

        grid.add(new Label("* Required fields"), 1, 7);

        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);

        add.disableProperty().bind(
                firstNameTextField.textProperty().isEmpty().
                or(lastNameTextField.textProperty().isEmpty()).
                or(addressTextField.textProperty().isEmpty()).
                or(suburbTextField.textProperty().isEmpty()).
                or(stateTextField.textProperty().isEmpty()).
                or(postcodeTextField.textProperty().isEmpty()).
                or(phoneTextField.textProperty().isEmpty()));
        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //add input validation

            //event.consume();
        });
        
        // Request focus on the date field by default.
        Platform.runLater(() -> firstNameTextField.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            // attempt to create object
            try{
                // instantiate temporary object
                cust = new Customer(firstNameTextField.getText(),
                            lastNameTextField.getText(),
                            addressTextField.getText(),
                            suburbTextField.getText(),
                            stateTextField.getText(),
                            postcodeTextField.getText(),
                            phoneTextField.getText());

            } // if incorrect data type has been entered an exception will be thrown
            catch(Exception ex)
            {
                // create error message
                String message = "Add customer error";

                // display the exception error window
                displayException(ex, message);
            }
            
        }
        else 
        {
            cust = new Customer();
        }
        
        return cust;
    }

    
    public static Customer displayUpdateCustomerDialog(Customer cust)
    {
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle(WINDOW_TITLE + " - Update Customer");      // add title
        dialog.setHeaderText("Update Customer Details");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Update", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        TextField firstNameTextField = new TextField();
        firstNameTextField.setText(cust.getFirstName());

        TextField lastNameTextField = new TextField();
        lastNameTextField.setText(cust.getLastName());

        TextField addressTextField = new TextField();
        addressTextField.setText(cust.getAddress());
        
        TextField suburbTextField = new TextField();
        suburbTextField.setText(cust.getSuburb());
        
        TextField stateTextField = new TextField();
        stateTextField.setText(cust.getState());
        
        TextField postcodeTextField = new TextField();
        postcodeTextField.setText(cust.getPostcode());
        
        TextField phoneTextField = new TextField();
        phoneTextField.setText(cust.getPhoneNo());
        
        
        
        // add labels and textfields to the gridpane
        grid.add(new Label("First name"), 0, 0);
        grid.add(firstNameTextField, 1, 0);
        grid.add(new Label("*"), 2, 0);
        
        grid.add(new Label("Last name"), 0, 1);
        grid.add(lastNameTextField, 1, 1);
        grid.add(new Label("*"), 2, 1);        
        
        grid.add(new Label("Address"), 0, 2);
        grid.add(addressTextField, 1, 2);
        grid.add(new Label("*"), 2, 2);
        
        grid.add(new Label("Suburb"), 0, 3);
        grid.add(suburbTextField, 1, 3);
        grid.add(new Label("*"), 2, 3);
        
        grid.add(new Label("State"), 0, 4);
        grid.add(stateTextField, 1, 4);
        grid.add(new Label("*"), 2, 4);
        
        grid.add(new Label("Postcode"), 0, 5);
        grid.add(postcodeTextField, 1, 5);
        grid.add(new Label("*"), 2, 5);
        
        grid.add(new Label("Phone"), 0, 6);
        grid.add(phoneTextField, 1, 6);
        grid.add(new Label("*"), 2, 6);

        grid.add(new Label("* Required fields"), 1, 7);

        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);

        add.disableProperty().bind(
                firstNameTextField.textProperty().isEmpty().
                or(lastNameTextField.textProperty().isEmpty()).
                or(addressTextField.textProperty().isEmpty()).
                or(suburbTextField.textProperty().isEmpty()).
                or(stateTextField.textProperty().isEmpty()).
                or(postcodeTextField.textProperty().isEmpty()).
                or(phoneTextField.textProperty().isEmpty()));
        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //add input validation

            //event.consume();
        });
        
        // Request focus on the date field by default.
        Platform.runLater(() -> firstNameTextField.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            // attempt to create object
            try{
                // instantiate temporary object
                cust.setFirstName(firstNameTextField.getText());
                cust.setLastName(lastNameTextField.getText());
                cust.setAddress(addressTextField.getText());
                cust.setSuburb(suburbTextField.getText());
                cust.setState(stateTextField.getText());
                cust.setPostcode(postcodeTextField.getText());
                cust.setPhoneNo(phoneTextField.getText());

            } // if incorrect data type has been entered an exception will be thrown
            catch(Exception e)
            {
                // create error message
                String message = "update customer error";

                // display the exception error window
                displayException(e, message);
            }
        }
        else 
        {
            cust = new Customer();
        }
        return cust;
    }
    
    
   // vehicle windows
    
    public static Vehicle displayNewVehicleDialog()
    {
        Vehicle veh = new Vehicle();
        
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle(WINDOW_TITLE + " - Add Vehicle");      // add title
        dialog.setHeaderText("Enter Vehicle Details");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Add", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        
        ownerTextField = new TextField();
        ownerTextField.setDisable(true);
        Button selectOwnerBtn = new Button("Select");
        
        EventHandler<ActionEvent> selectOwnerBtnClicked = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e)
            {
                owner = displayOwnerSelectDialog();
                
                if(owner.getCustomerID() != 0)
                    ownerTextField.setText(owner.getFirstName() + " " +
                                        owner.getLastName());
            }
        };
        
        selectOwnerBtn.setOnAction(selectOwnerBtnClicked);
        
        TextField licenceTextField = new TextField();
        licenceTextField.setPromptText("Enter licence plate number");
           
        TextField makeTextField = new TextField();
        makeTextField.setPromptText("Enter vehicle make");

        TextField modelTextField = new TextField();
        modelTextField.setPromptText("Enter vehicle model");

        TextField yearTextField = new TextField();
        yearTextField.setPromptText("Enter vehicle year of manufacture");
        
        TextField odometerTextField = new TextField();
        odometerTextField.setPromptText("Enter current odometer");
        
        
        
        // add labels and textfields to the gridpane
        grid.add(new Label("Owner"), 0, 0);
        grid.add(ownerTextField, 1, 0);
        grid.add(selectOwnerBtn, 2, 0);
        
        grid.add(new Label("Licence plate"), 0, 1);
        grid.add(licenceTextField, 1, 1);
        grid.add(new Label("*"), 2, 1); 
        
        grid.add(new Label("Make"), 0, 2);
        grid.add(makeTextField, 1, 2);
        grid.add(new Label("*"), 2, 2);        
        
        grid.add(new Label("Model"), 0, 3);
        grid.add(modelTextField, 1, 3);
        grid.add(new Label("*"), 2, 3);
        
        grid.add(new Label("Year"), 0, 4);
        grid.add(yearTextField, 1, 4);
        grid.add(new Label("*"), 2, 4);
        
        grid.add(new Label("Odometer"), 0, 5);
        grid.add(odometerTextField, 1, 5);
        grid.add(new Label("*"), 2, 5);
        
        grid.add(new Label("* Required fields"), 1, 6, 2, 1);

        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);

        add.disableProperty().bind(
                ownerTextField.textProperty().isEmpty().
                or(licenceTextField.textProperty().isEmpty()).
                or(makeTextField.textProperty().isEmpty()).
                or(modelTextField.textProperty().isEmpty()).
                or(yearTextField.textProperty().isEmpty()).
                or(odometerTextField.textProperty().isEmpty()));
        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //add input validation

            //event.consume();
        });
        
        // Request focus on the date field by default.
        Platform.runLater(() -> selectOwnerBtn.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            // attempt to create object
            try{
                // instantiate temporary object
                veh.setOwnerID(owner.getCustomerID());
                veh.setLicencePlate(licenceTextField.getText());
                veh.setMake(makeTextField.getText());
                veh.setModel(modelTextField.getText());
                veh.setYear(yearTextField.getText());
                veh.setOdometer(Integer.parseInt(odometerTextField.getText()));

            } // if incorrect data type has been entered an exception will be thrown
            catch(Exception e)
            {
                // create error message
                String message = "create vehicle object error";

                // display the exception error window
                displayException(e, message);
            }
        }
        
        owner = null;
        return veh;
    }
    
    public static Vehicle displayUpdateVehicleDialog(Vehicle veh)
    {
        owner = VSMSModel.getCustomerFromDB(veh.getOwnerID());
        
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle(WINDOW_TITLE + " - Update Vehicle");      // add title
        dialog.setHeaderText("Update Vehicle Details");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Update", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        
        ownerTextField = new TextField();
        ownerTextField.setDisable(true);
        Button selectOwnerBtn = new Button("Select");
        ownerTextField.setText(owner.getFirstName() + " " +
                                    owner.getLastName());
        
        EventHandler<ActionEvent> selectOwnerBtnClicked = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e)
            {
                owner = displayOwnerSelectDialog();
                ownerTextField.setText(owner.getFirstName() + " " +
                                    owner.getLastName());
            }
        };
        
        selectOwnerBtn.setOnAction(selectOwnerBtnClicked);
                
        TextField licenceTextField = new TextField();
        licenceTextField.setText(veh.getLicencePlate());
        
        TextField makeTextField = new TextField();
        makeTextField.setText(veh.getMake());

        TextField modelTextField = new TextField();
        modelTextField.setText(veh.getModel());

        TextField yearTextField = new TextField();
        yearTextField.setText(veh.getYear());
        
        TextField odometerTextField = new TextField();
        odometerTextField.setText(veh.getOdometer()+"");
        
        
        
        // add labels and textfields to the gridpane
        grid.add(new Label("Owner"), 0, 0);
        grid.add(ownerTextField, 1, 0);
        grid.add(selectOwnerBtn, 2, 0);
        
        grid.add(new Label("Licence plate"), 0, 1);
        grid.add(licenceTextField, 1, 1);
        grid.add(new Label("*"), 2, 1); 
        
        grid.add(new Label("Make"), 0, 2);
        grid.add(makeTextField, 1, 2);
        grid.add(new Label("*"), 2, 2);        
        
        grid.add(new Label("Model"), 0, 3);
        grid.add(modelTextField, 1, 3);
        grid.add(new Label("*"), 2, 3);
        
        grid.add(new Label("Year"), 0, 4);
        grid.add(yearTextField, 1, 4);
        grid.add(new Label("*"), 2, 4);
        
        grid.add(new Label("Odometer"), 0, 5);
        grid.add(odometerTextField, 1, 5);
        grid.add(new Label("*"), 2, 5);
        
        grid.add(new Label("* Required fields"), 1, 6, 2, 1);

        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);

        add.disableProperty().bind(
                ownerTextField.textProperty().isEmpty().
                or(licenceTextField.textProperty().isEmpty()).
                or(makeTextField.textProperty().isEmpty()).
                or(modelTextField.textProperty().isEmpty()).
                or(yearTextField.textProperty().isEmpty()).
                or(odometerTextField.textProperty().isEmpty()));
        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //add input validation

            //event.consume();
        });
        
        // Request focus on the date field by default.
        Platform.runLater(() -> selectOwnerBtn.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            // attempt to create object
            try{
                // instantiate temporary object
                veh.setOwnerID(owner.getCustomerID());
                veh.setLicencePlate(licenceTextField.getText());
                veh.setMake(makeTextField.getText());
                veh.setModel(modelTextField.getText());
                veh.setYear(yearTextField.getText());
                veh.setOdometer(Integer.parseInt(odometerTextField.getText()));

            } // if incorrect data type has been entered an exception will be thrown
            catch(Exception e)
            {
                // create error message
                String message = "update vehicle object error";

                // display the exception error window
                displayException(e, message);
            }
        }
        
        owner = null;
        return veh;
    }
    
    // service windows
    
    public static Service displayNewServiceDialog()
    {
        Service serv = new Service();
        
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle(WINDOW_TITLE + " - Add Service");      // add title
        dialog.setHeaderText("Enter Services Details");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Add", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        
        vehicleTextField = new TextField();
        vehicleTextField.setDisable(true);
        Button selectVehicleBtn = new Button("Select");
        
        EventHandler<ActionEvent> selectVehicleBtnClicked = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e)
            {
                serviceVeh = displayVehicleSelectDialog();
                
                if(serviceVeh.getVehicleID() != 0)
                    vehicleTextField.setText(serviceVeh.getLicencePlate() + ", " +
                                        serviceVeh.getMake() + " " + serviceVeh.getModel());
            }
        };
        
        selectVehicleBtn.setOnAction(selectVehicleBtnClicked);
           
        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.setPromptText("Enter service description");

        DatePicker serviceDatePicker = new DatePicker();
        serviceDatePicker.setPromptText("Select date");

        TextField priceTextField = new TextField();
        priceTextField.setPromptText("Enter service price");
        
        
        
        // add labels and textfields to the gridpane
        grid.add(new Label("Owner"), 0, 0);
        grid.add(vehicleTextField, 1, 0);
        grid.add(selectVehicleBtn, 2, 0);
        
        grid.add(new Label("Description"), 0, 1);
        grid.add(descriptionTextArea, 1, 1);
        grid.add(new Label("*"), 2, 1);        
        
        grid.add(new Label("Service date"), 0, 2);
        grid.add(serviceDatePicker, 1, 2);
        grid.add(new Label("*"), 2, 2);
        
        grid.add(new Label("Price"), 0, 3);
        grid.add(priceTextField, 1, 3);
        grid.add(new Label("*"), 2, 3);
        
        grid.add(new Label("* Required fields"), 1, 4, 2, 1);

        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);

        add.disableProperty().bind(
                vehicleTextField.textProperty().isEmpty().
                or(descriptionTextArea.textProperty().isEmpty()).
                or(serviceDatePicker.valueProperty().isNull()).
                or(priceTextField.textProperty().isEmpty()));
        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //add input validation

            //event.consume();
        });
        
        // Request focus on the date field by default.
        Platform.runLater(() -> selectVehicleBtn.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            // attempt to create object
            try{
                // instantiate temporary object
                serv.setVehicleID(serviceVeh.getVehicleID());
                serv.setDescription(descriptionTextArea.getText());
                serv.setServiceDate(serviceDatePicker.getValue());
                serv.setPrice(Float.parseFloat(priceTextField.getText()));

            } // if incorrect data type has been entered an exception will be thrown
            catch(Exception e)
            {
                // create error message
                String message = "create service object error";

                // display the exception error window
                displayException(e, message);
            }
        }
        
        serviceVeh = null;
        return serv;
    }
    
    public static Service displayUpdateServiceDialog(Service serv)
    {
        
        
        
        
        return serv;
    }
    
    public static void displayServiceReportDialog()
    {
        
        
        
    }
    

    /**
     * Display about window
     */
    public static void displayAboutDialog()
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        a.setTitle(WINDOW_TITLE);
        a.setHeaderText("About");
        
        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 20, 30));
        
        // add labels and textfields to the gridpane
        grid.add(new Label("Developed by:"), 0, 0);
        grid.add(new Label("Joshua Turner"), 1, 0);
        grid.add(new Label("s0258441"), 2, 0);     
        
        grid.add(new Label("Isabel Rigby"), 1, 1);
        grid.add(new Label("S0077632"), 2, 1); 
        
        grid.add(new Label("Joshua Robertson"), 1, 2);
        grid.add(new Label("12071409"), 2, 2); 
        
        grid.add(new Label("Version Number:"), 0, 3);
        grid.add(new Label("TBA"), 1, 3);
        
        grid.add(new Label("Version Date:"), 0, 4);
        grid.add(new Label("10 June 2022"), 1, 4);
        
        a.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        a.show();
    }
    
    private static Customer displayOwnerSelectDialog()
    {
        
        Customer selectedOwner = null;
        
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle(WINDOW_TITLE + " - Select Owner");      // add title
        dialog.setHeaderText("Owner Selection");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Select", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        
        TextField searchTextField = new TextField();
        Button searchBtn = new Button("Search");
        
        EventHandler<ActionEvent> searchBtnClicked = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e)
            {
                String search = searchTextField.getText();
                
                ObservableList<Customer> results = VSMSModel.getCustomerListFromDB(search);
                
                if(results.size() < 1)
                    ownerTableView.setPlaceholder(
                            new Label("No customers found for " + search));
                
                ownerTableView.setItems(results);
            }
        };
        
        searchBtn.setOnAction(searchBtnClicked);
        
        
        ownerTableView = new TableView();
        TableColumn<Customer, Integer> customerIdCol = new TableColumn<>("ID");
        TableColumn<Customer, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Customer, String> lastNameCol = new TableColumn<>("Last Name");
        TableColumn<Customer, String> phoneCol = new TableColumn<>("Phone");
        
        ownerTableView.setPrefHeight(200);
        ownerTableView.setPrefWidth(350);
        ownerTableView.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        
        ownerTableView.getColumns().add(customerIdCol);
        ownerTableView.getColumns().add(firstNameCol);
        ownerTableView.getColumns().add(lastNameCol);
        ownerTableView.getColumns().add(phoneCol);
        
        
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        
        ownerTableView.setPlaceholder(new Label("Search for a customer to link vehicle to."));
        
        // add labels and textfields to the gridpane
        grid.add(searchTextField, 0, 0, 2, 1);
        grid.add(searchBtn, 2, 0);
        
        grid.add(ownerTableView, 0, 1, 2, 1);
        
        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);

        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //if(ownerTableView.getSelectionModel().getSelectedItem() == null)
            //    displayError("Please select an Owner");
                
            //event.consume();
        });
        
        // Request focus on the date field by default.
        Platform.runLater(() -> searchTextField.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            selectedOwner = ownerTableView.getSelectionModel().getSelectedItem();
        }
        else
        {
            selectedOwner = new Customer();
        }
        
        return selectedOwner;
        
    }
    
    private static Vehicle displayVehicleSelectDialog()
    {
        
        Vehicle selectedVeh = null;
        
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle(WINDOW_TITLE + " - Select Vehicle");      // add title
        dialog.setHeaderText("Vehicle Selection");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Select", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        
        TextField searchTextField = new TextField();
        Button searchBtn = new Button("Search");
        
        EventHandler<ActionEvent> searchBtnClicked = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e)
            {
                String search = searchTextField.getText();
                
                ObservableList<Vehicle> results = VSMSModel.getVehicleListFromDB(search);
                
                if(results.size() < 1)
                    vehicleTableView.setPlaceholder(
                            new Label("No vehicles found with licence plate " + search));
                
                vehicleTableView.setItems(results);
            }
        };
        
        searchBtn.setOnAction(searchBtnClicked);
        
        
        vehicleTableView = new TableView();
        
        TableColumn<Vehicle, Integer> vehicleIdCol = new TableColumn<>("ID");
        TableColumn<Vehicle, String> licencePlateCol = new TableColumn<>("Licence plate");   
        TableColumn<Vehicle, String> makeCol = new TableColumn<>("Make");
        TableColumn<Vehicle, String> modelCol = new TableColumn<>("Model");
        
        vehicleTableView.setPrefHeight(200);
        vehicleTableView.setPrefWidth(350);
        vehicleTableView.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        
        vehicleTableView.getColumns().add(vehicleIdCol);
        vehicleTableView.getColumns().add(licencePlateCol);
        vehicleTableView.getColumns().add(makeCol);
        vehicleTableView.getColumns().add(modelCol);
        
        vehicleIdCol.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        licencePlateCol.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));
        makeCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        
        vehicleTableView.setPlaceholder(new Label("Search for a vehicle"));
        
        // add labels and textfields to the gridpane
        grid.add(searchTextField, 0, 0, 2, 1);
        grid.add(searchBtn, 2, 0);
        
        grid.add(vehicleTableView, 0, 1, 2, 1);
        
        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);

        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //if(ownerTableView.getSelectionModel().getSelectedItem() == null)
            //    displayError("Please select an Owner");
                
            //event.consume();
        });
        
        // Request focus on the date field by default.
        Platform.runLater(() -> searchTextField.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            selectedVeh = vehicleTableView.getSelectionModel().getSelectedItem();
        }
        else
        {
            selectedVeh = new Vehicle();
        }
        
        return selectedVeh;
        
    }
}
