
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class HelpWindowController extends Controller implements Initializable {
    
    /**
     * New instance of stage
     */
    private Stage stage;
    
    /**
     * Button to close help window
     */
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    /**
     * This method closes the window
     * @param event 
     */
    @FXML
    private void closeClicked(ActionEvent event) {  
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
