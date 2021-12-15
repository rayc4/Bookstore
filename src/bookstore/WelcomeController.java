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

	public static String username;
	
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
			username = usernameField.getText();
			String password = passwordField.getText();
			
			int userType = SQL.login(username, password);
			if(userType == 0) {
				Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText("username/password combination incorrect");
				a.show();
			}else if(userType == 1){
				try {
					Parent root = FXMLLoader.load(getClass().getResource("customer.fxml"));
					Scene scene = new Scene(root, 600, 400);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    		Stage stage = (Stage) registerButton.getScene().getWindow();
		    		stage.setScene(scene);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("owner.fxml"));
					Scene scene = new Scene(root, 800, 400);
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