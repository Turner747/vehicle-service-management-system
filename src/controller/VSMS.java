package controller;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.MessageView;

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

public class VSMS extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        
        try
        {
            Parent root = FXMLLoader.load(view.MessageView.class.getResource("VSMSView.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Vehicle Service Management System");
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                MessageView.displayExitDialog(e);
            }
        });
            primaryStage.show();
        } catch (IOException ex)
        {
            Logger.getLogger(VSMS.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
