package bookstore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {

    @FXML
    private ChoiceBox<String> userTypeCB;
    
    @FXML
    private VBox inputsVBox;
    
    @FXML
    private Button submitButton;
    
    @SuppressWarnings("unchecked")
	public void initialize(){
    	ArrayList<String> al = new ArrayList<String>();
		al.add("Customer");
		al.add("Owner");
		ObservableList<String> ol = FXCollections.observableArrayList(al);
		userTypeCB.setItems(ol);
		
		submitButton.setOnMouseClicked( (evt) -> {
			ArrayList<String> inputs = new ArrayList<String>(7);
			ObservableList<Node> children = inputsVBox.getChildren();
			for (int i=0; i<7; ++i) {
				if(children.get(i) instanceof ChoiceBox) {
					inputs.add( ((ChoiceBox<String>)children.get(i)).getSelectionModel().getSelectedItem() );
				}else if(children.get(i) instanceof TextField) {
					inputs.add( ((TextField)children.get(i)).getText() );
				}
			}
			if(inputs.contains("") || inputs.contains(null)) return;
			
			boolean success = SQL.insertUser(inputs);
			Alert a = new Alert(AlertType.INFORMATION);
			if(success) {
				a.setContentText("Account created");
			}else {
				a.setContentText("Username or email already exists");
			}
			
			a.setOnCloseRequest( (done) -> {
				if(!success) return;
				try {
					Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
					Scene scene = new Scene(root, 400, 300);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    		Stage stage = (Stage) submitButton.getScene().getWindow();
		    		stage.setScene(scene);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			a.show();
		});
    }
}
