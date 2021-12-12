package bookstore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class WelcomeController {

    @FXML
    private Button registerButton;
    
    
    public void initialize(){
    	registerButton.setOnMouseClicked( (evt) -> {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
				Scene scene = new Scene(root, 400, 300);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    		Stage stage = (Stage) registerButton.getScene().getWindow();
	    		stage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    }
}