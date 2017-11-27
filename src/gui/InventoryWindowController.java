/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author ViktoriaNadarajah
 */
public class InventoryWindowController extends Controller implements Initializable {
    
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
     * Method for the use button 
     * @param event 
     */
    @FXML
    private void onUseClicked(ActionEvent event) {
        game.command("use", listView.getSelectionModel().getSelectedItem().replace(" ", "-"));
        addItemsToViewList();
    }
    
    /**
     * Method for the drop button
     * @param event 
     */
    @FXML
    private void onDropClicked(ActionEvent event) {
        game.command("drop", listView.getSelectionModel().getSelectedItem().replace(" ", "-"));
        addItemsToViewList();
    }
    
    /**
     * Adds the item from the players currentroom to the viewlist
     */
    public void addItemsToViewList(){
        ObservableList<String> data = FXCollections.observableArrayList(game.getPlayer().getNameOfItems());
        for (int i = 0; i < data.size(); i++) {
            data.set(i, data.get(i).replace("-", " "));
        }
        listView.setItems(data);
    }    
}