/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import maga.Game;

/**
 *
 * @author Rasmus
 */
public class GameController implements Initializable {

    @FXML
    private Label stepsLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label leftHandLabel;
    @FXML
    private Label rightHandLabel;
    @FXML
    private Button helpButton;
    @FXML
    private ImageView player;
    @FXML
    private ImageView cook;
    @FXML
    private ImageView trump;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onHelpClicked(ActionEvent event) {
    }
    
}
