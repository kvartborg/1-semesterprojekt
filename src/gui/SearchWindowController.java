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
import maga.GameFacade;
import maga.item.Item;

/**
 * FXML Controller class
 *
 * @author Kasper
 */
public class SearchWindowController implements Initializable {
    private IGame game = new GameFacade();

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
    private void onPickupClicked(ActionEvent event) {
        game.command("pickup", listView.getSelectionModel().getSelectedItem().replace(" ", "-"));
        
        ObservableList<String> data = FXCollections.observableArrayList(game.getPlayer().getCurrentRoom().getNameOfItems());
        listView.setItems(data);
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
        ObservableList<String> data = FXCollections.observableArrayList(game.getPlayer().getCurrentRoom().getNameOfItems());
        for (int i = 0; i < data.size(); i++) {
            data.set(i, data.get(i).replace("-", " "));
        }
        listView.setItems(data);
    }    
    
}
