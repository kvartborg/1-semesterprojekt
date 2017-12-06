package gui.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 */
public class InventoryController extends Controller implements Initializable {

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
        if (
            listViewSelection(listView)=="Key" &&
            game.getPlayer().getCurrentRoom().getName().equalsIgnoreCase("lobby1")
        ) {
            showUnlocked();
        }
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

    /**
     * Creates an alert box for when you unlock a door
     */
    public void showUnlocked(){
        ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        Alert doorLocked = new Alert(Alert.AlertType.INFORMATION, "", ok);
        doorLocked.setTitle("Door");
        doorLocked.setHeaderText("The door is now unlocked");
        Optional<ButtonType> result = doorLocked.showAndWait();
    }
}
