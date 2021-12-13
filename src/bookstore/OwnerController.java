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
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class OwnerController {

    @FXML
    private GridPane addBookGP;
    
    @FXML
    private Button addBookButton;
    
    @FXML
    private GridPane addPubGP;
    
    @FXML
    private Button addPubButton;
    
    public void initialize(){
    	
    	addBookButton.setOnMouseClicked( (evt) -> {
	    	ObservableList<Node> addBookChildren = addBookGP.getChildren();
	    	ArrayList<String> bookInputs = new ArrayList<String>(7);
	    	for (int i=0; i<14; ++i) {
				if(addBookChildren.get(i) instanceof TextField) {
					bookInputs.add( ((TextField)addBookChildren.get(i)).getText() );
				}
	    	}
			if(bookInputs.contains("") || bookInputs.contains(null)) return;
			
			SQL s = new SQL();
			boolean success = s.insertBook(bookInputs);
			
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
			a.show();
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
			
			SQL s = new SQL();
			boolean success = s.insertPublisher(pubInputs);
			
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