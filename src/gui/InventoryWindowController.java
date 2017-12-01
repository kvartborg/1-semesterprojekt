
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
        
    }  
    
    /**
     * Method for the use button 
     * @param event 
     */
    @FXML
    private void onUseClicked(ActionEvent event) {
            game.command("use", listViewSelection(listView));
            addItemsToViewList();
    }
    
    /**
     * Method for the drop button
     * @param event 
     */
    @FXML
    private void onDropClicked(ActionEvent event) {
            game.command("drop", listViewSelection(listView));
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