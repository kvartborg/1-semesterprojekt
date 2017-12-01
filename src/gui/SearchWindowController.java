
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
      
    } 
    
    /**
     * Method for the "Pick up"-button.
     * @param event 
     */
    @FXML
    private void onPickupClicked(ActionEvent event) {
        game.command("pickup", listViewSelection(listView));
        addItemsToViewList();   
    }

    /**
     * Method to use items in the search window with the use button
     * @param event 
     */
    @FXML
    private void onUsePressed(ActionEvent event) {
        if(listViewSelection(listView).equals("Computer")){
            gui.getStages().get("Computer").show(); 
        } else {
            game.command("use", listViewSelection(listView));
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