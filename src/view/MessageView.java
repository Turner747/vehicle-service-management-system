package view;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Customer;
import model.Service;
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
    
    
    
    
    public static void displayInfoMessage(String message)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        a.setTitle(WINDOW_TITLE);
        a.setContentText(message);
        a.show();
    }
    
    
    public static void displayExitDialog(Event e){
        

        
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
        Customer cust = new Customer();
        
        
        
        return cust;
    }

    
    public static Customer displayUpdateCustomerDialog(Customer cust)
    {
        
        
        
        
        return cust;
    }
    
    
   // vehicle windows
    
    public static Vehicle displayNewVehicleDialog()
    {
        Vehicle veh = new Vehicle();
        
        
        
        return veh;
    }
    
    public static Vehicle displayUpdateVehicleDialog(Vehicle veh)
    {
        
        
        
        
        return veh;
    }
    
    // service windows
    
    public static Service displayNewServiceDialog()
    {
        Service serv = new Service();
        
        
        
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
    
    
}
