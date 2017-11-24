/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import acq.IGame;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import gui.GUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kasper
 */
public class SearchWindowController extends Controller implements Initializable {
    
    /**
     * A ListView for the items string-names
     */
    @FXML
    private ListView<String> listView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    /**
     * Method for the "Pick up"-button.
     * @param event 
     */
    @FXML
    private void onPickupClicked(ActionEvent event) {
        game.command("pickup", listView.getSelectionModel().getSelectedItem().replace(" ", "-"));
        
        addItemsToViewList();
    }

    @FXML
    private void onUsePressed(ActionEvent event) {
        if(listView.getSelectionModel().getSelectedItem().replace(" ", "-") != "Computer"){
            game.command("use", listView.getSelectionModel().getSelectedItem().replace(" ", "-"));
        } else {
            gui.getStages().get("Computer").show();
        }
        
        addItemsToViewList();
    }
        
    /**
     * Adds the item from the players currentroom to the viewlist
     */
    public void addItemsToViewList(){
        ObservableList<String> data = FXCollections.observableArrayList(game.getPlayer().getCurrentRoom().getNameOfItems());
        for (int i = 0; i < data.size(); i++) {
            data.set(i, data.get(i).replace("-", " "));
        }
        listView.setItems(data);
    }
}
