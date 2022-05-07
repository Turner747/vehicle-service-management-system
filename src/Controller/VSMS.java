package Controller;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import View.MessageView;

/**
 *
 * @author josht
 */
public class VSMS extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        
        try
        {
            Parent root = FXMLLoader.load(View.MessageView.class.getResource("VSMSView.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
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
