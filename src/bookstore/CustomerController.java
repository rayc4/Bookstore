package bookstore;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CustomerController {
	
	public static HashMap<String, Integer> order;
	
	@FXML
	private VBox booksVBox;
	
	@FXML
	private VBox cartVBox;
	
	@FXML
	private Button checkoutButton;
	
	@FXML
	private VBox ordersVBox;
	
	public void initialize(){
		LinkedList<String> books = SQL.getBooks();
    	for(String b : books) {
    		Text t = new Text(b);
    		t.setOnMouseClicked((evt)->{
    			LinkedList<String> book = SQL.getBook(t.getText());
    			ButtonType cancel = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
    			ButtonType add = new ButtonType("Add to cart", ButtonBar.ButtonData.CANCEL_CLOSE);	
    			String alertText =  "Title: "+ book.get(1) + "\n" +
    								"Pages: "+ book.get(2) + "\n" +
    								"Price: "+ book.get(3) + "\n" +
    								"Quantity: "+ book.get(4) + "\n" +
    								"Publisher: "+ book.get(5) + "\n" +
    								"Publisher Cut: "+ book.get(6) + "\n" +
    								"Authors: \n";
    			
    			// Get authors
    			LinkedList<String> names = SQL.getAuthors(book.get(0));  			
    			for(String n : names) {
    				alertText += "\t" + n + "\n";
    			}
    			
    			// Get genres
    			LinkedList<String> genres = SQL.getGenres(book.get(0)); 
    			alertText += "Genres: \n";
    			for(String g : genres) {
    				alertText += "\t" + g + "\n";
    			}
    			
    			Alert a = new Alert(AlertType.CONFIRMATION, "",add, cancel);
    			a.setContentText(alertText);
    			a.setTitle("ISBN: "+book.get(0)+"\n");
    			
    			Optional<ButtonType> res = a.showAndWait();
    			if(res.get() == add) {
    				Text cartItem = new Text(b);
    				cartVBox.getChildren().add(cartItem);
    			}				
    		});
    		booksVBox.getChildren().add(t);
    	}
    	booksVBox.setSpacing(10);
    	
    	checkoutButton.setOnMouseClicked((evt)->{
    		order = new HashMap<String,Integer>();
    		int newVal;
    		for(Node cartItem : cartVBox.getChildren()) { 			
    			String strCartItem = ((Text)cartItem).getText();
    			newVal = order.getOrDefault(strCartItem, 0);
				order.put(strCartItem, newVal+1);
    			//System.out.println(strCartItem + ": " + String.valueOf(newVal+1));
    		}
    		
    		try {
				Parent root = FXMLLoader.load(getClass().getResource("checkout.fxml"));
				Scene scene = new Scene(root, 600, 400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    		Stage stage = (Stage) checkoutButton.getScene().getWindow();
	    		stage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    	
    	LinkedList<String> orders = SQL.getOrders(WelcomeController.username);
    	for(String orderId : orders) {
    		Text t = new Text(orderId);
    		t.setOnMouseClicked((evt)->{
    			LinkedList<String> order = SQL.getOrder(orderId);
				Alert a = new Alert(AlertType.INFORMATION,
						"status: " + order.get(1) + "\n" +
						"date: " + order.get(2) + "\n" +
						"shipping address: " + order.get(3) + "\n" +
						"billing address: " + order.get(4) + "\n" );
				a.setTitle(order.get(0));
				a.show();
    		});
    		ordersVBox.getChildren().add(t);
    	}
    	
	}
}
