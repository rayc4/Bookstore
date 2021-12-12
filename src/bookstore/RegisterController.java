package bookstore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;

public class RegisterController {

    @FXML
    private ChoiceBox<String> userTypeCB;
    
    
    public void initialize(){
    	ArrayList<String> userTypes = new ArrayList<String>();
		userTypes.add("Customer");
		userTypes.add("Owner");
		ObservableList<String> ol = FXCollections.observableArrayList(userTypes);
		userTypeCB.setItems(ol);
    }
}
