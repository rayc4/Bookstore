package bookstore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CheckoutController {

	@FXML
	private Button orderButton;
	
	@FXML
	private GridPane inputGP;
	
	public void initialize() {
		orderButton.setOnMouseClicked((evt)->{
			ArrayList<String> al = new ArrayList<String>(6);
			ObservableList<Node> children = inputGP.getChildren();
			for (int i=0; i<4; ++i) {	
				if(children.get(i) instanceof TextField) {
					al.add( ((TextField)children.get(i)).getText() );
				}
	    	}
			SQL.insertOrder(al);
			
			HashMap<String, Integer> order = CustomerController.order;
			for(String book : order.keySet()) {
				SQL.insertBookOrder(SQL.getMaxOrderId(), book, order.get(book).intValue());
			}
			
			
			Alert a = new Alert(AlertType.INFORMATION, "order made");
			a.showAndWait();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("customer.fxml"));
				Scene scene = new Scene(root, 600, 400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    		Stage stage = (Stage) orderButton.getScene().getWindow();
	    		stage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
