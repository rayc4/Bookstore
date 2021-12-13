package bookstore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.io.IOException;

public class WelcomeController {

    @FXML
    private Button registerButton;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
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
    	
    	loginButton.setOnMouseClicked( (evt) -> {
			String username = usernameField.getText();
			String password = passwordField.getText();
			
			SQL s = new SQL();
			int userType = s.login(username, password);
			if(userType == 0) {
				Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText("username/password combination incorrect");
			}else if(userType == 1){
				System.out.println("customer");
			}else {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("owner.fxml"));
					Scene scene = new Scene(root, 400, 300);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    		Stage stage = (Stage) registerButton.getScene().getWindow();
		    		stage.setScene(scene);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    	});
    	
    }
}