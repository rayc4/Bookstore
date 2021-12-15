package bookstore;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

public class OwnerController {

    @FXML
    private GridPane addBookGP;
    
    @FXML
    private Button addBookButton;
    
    @FXML
    private GridPane addPubGP;
    
    @FXML
    private Button addPubButton;
    
    @FXML
    private VBox booksVBox;
    
    public void initialize(){
    	
    	LinkedList<String> books = SQL.getBooks();
    	for(String b : books) {
    		Text t = new Text(b);
    		t.setOnMouseClicked((evt)->{
    			LinkedList<String> book = SQL.getBook(t.getText());
    			ButtonType done = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
    			ButtonType remove = new ButtonType("Delete this book ", ButtonBar.ButtonData.CANCEL_CLOSE);	
    			Alert a = new Alert(AlertType.CONFIRMATION, 
    					"Title: "+ book.get(1) + "\n" +
    					"Pages: "+ book.get(2) + "\n" +
    					"Price: "+ book.get(3) + "\n" +
    					"Quantity: "+ book.get(4) + "\n" +
    					"Publisher: "+ book.get(5) + "\n" +
    					"Publisher Cut: "+ book.get(6) + "\n"
    					,done, remove);
    			a.setTitle("ISBN: "+book.get(0)+"\n");
    			
    			Optional<ButtonType> res = a.showAndWait();
    			if(res.get() == remove) {
    				SQL.deleteBook(b);
    				try {
	    				Parent root = FXMLLoader.load(getClass().getResource("owner.fxml"));
						Scene scene = new Scene(root, 800, 400);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			    		Stage stage = (Stage) addBookButton.getScene().getWindow();
			    		stage.setScene(scene);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}				
    		});
    		booksVBox.getChildren().add(t);
    	}
    	booksVBox.setSpacing(10);

    	addBookButton.setOnMouseClicked( (evt) -> {
	    	ObservableList<Node> addBookChildren = addBookGP.getChildren();
	    	ArrayList<String> bookInputs = new ArrayList<String>(7);
	    	for (int i=0; i<14; ++i) {
				if(addBookChildren.get(i) instanceof TextField) {
					bookInputs.add( ((TextField)addBookChildren.get(i)).getText() );
				}
	    	}
			if(bookInputs.contains("") || bookInputs.contains(null)) return;
			
			
			boolean success = SQL.insertBook(bookInputs);
			
			Alert a = new Alert(AlertType.INFORMATION);
			if(success) {
				a.setContentText("Book added");
			}else {
				a.setContentText("Fail");
			}
			
			a.setOnCloseRequest( (done) -> {
				if(!success) return;
				for (int i=0; i<7; ++i) {
					if(addBookChildren.get(i) instanceof TextField) {
						((TextField)addBookChildren.get(i)).clear();
					}
		    	}
			});
			a.showAndWait();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("owner.fxml"));
				Scene scene = new Scene(root, 800, 400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    		Stage stage = (Stage) addBookButton.getScene().getWindow();
	    		stage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    	
    	addPubButton.setOnMouseClicked( (evt) -> {
	    	ObservableList<Node> addPubChildren = addPubGP.getChildren();
	    	ArrayList<String> pubInputs = new ArrayList<String>(7);
	    	for (int i=0; i<14; ++i) {
				if(addPubChildren.get(i) instanceof TextField) {
					pubInputs.add( ((TextField)addPubChildren.get(i)).getText() );
				}
	    	}
			if(pubInputs.contains("") || pubInputs.contains(null)) return;
			
			boolean success = SQL.insertPublisher(pubInputs);
			
			Alert a = new Alert(AlertType.INFORMATION);
			if(success) {
				a.setContentText("Publisher added");
			}else {
				a.setContentText("Fail");
			}
			
			a.setOnCloseRequest( (done) -> {
				if(!success) return;
				for (int i=0; i<7; ++i) {
					if(addPubChildren.get(i) instanceof TextField) {
						((TextField)addPubChildren.get(i)).clear();
					}
		    	}
			});
			a.show();
    	});
    }
}