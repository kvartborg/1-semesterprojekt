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

/**
 * FXML Controller class
 *
 * @author ViktoriaNadarajah
 */
public class InventoryWindowController implements Initializable {
    private IGame game;

    @FXML
    private ListView<String> listView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onUseClicked(ActionEvent event) {
        game.command("use", listView.getSelectionModel().getSelectedItem().replace(" ", "-"));
        addItemsToViewList();
    }

    @FXML
    private void onDropClicked(ActionEvent event) {
        game.command("drop", listView.getSelectionModel().getSelectedItem().replace(" ", "-"));
        addItemsToViewList();
    }
    
    /**
     * Injects the game into the gui
     * @param game
     */
    public void injectGame(IGame game) {
        this.game = game; 
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
