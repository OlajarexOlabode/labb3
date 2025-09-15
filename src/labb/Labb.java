/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labb;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author 6233415
 */
public class Labb extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        // TODO code application logic here
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        root.setCenter(gridPane);
        
        //first name
        Label firstName = new Label("First Name:");
        gridPane.add(firstName, 0, 1);
        TextField firstNameField = new TextField();
        gridPane.add(firstNameField, 1, 1);
        
        //last name
        Label lastName = new Label("Last Name:");
        gridPane.add(lastName, 0, 2);
        TextField lastNameField = new TextField();
        gridPane.add(lastNameField, 1, 2);
        
        //email
        Label email = new Label("Email:");
        gridPane.add(email, 0, 3);
        TextField emailField = new TextField();
        gridPane.add(emailField, 1, 3);
        
        //password
        Label pw = new Label("Password:");
        gridPane.add(pw, 0, 4);
        PasswordField pwBox = new PasswordField();
        gridPane.add(pwBox, 1, 4);
        
        //button1
        Button registerButton = new Button("Register");
        registerButton.setDisable(true);
        HBox hbRegisterBtn = new HBox(10); //spaces horizontally by 10
        hbRegisterBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbRegisterBtn.getChildren().add(registerButton);
        gridPane.add(hbRegisterBtn, 0, 5);
        
        //button2
        Button clearButton = new Button("Clear");
        HBox hbClearBtn = new HBox(10); //spaces horizontally by 10
        hbClearBtn.getChildren().add(clearButton);
        gridPane.add(hbClearBtn, 1, 5);
        
        //Label message
        Label messageLabel = new Label();
        root.setBottom(messageLabel);
        BorderPane.setAlignment(messageLabel, Pos.CENTER);
        
        //Register Button EventHandlers
        firstNameField.setOnKeyTyped(event -> updateButtonState(registerButton, firstNameField, lastNameField, emailField, pwBox));
        lastNameField.setOnKeyTyped(event -> updateButtonState(registerButton, firstNameField, lastNameField, emailField, pwBox));
        emailField.setOnKeyTyped(event -> updateButtonState(registerButton, firstNameField, lastNameField, emailField, pwBox));
        pwBox.setOnKeyTyped(event -> updateButtonState(registerButton, firstNameField, lastNameField, emailField, pwBox));

        registerButton.setOnAction(event -> {
            registerButton.setDisable(true);
            String enteredEmail = emailField.getText();
            String enteredPassword = pwBox.getText();

            //email validation
            boolean emailValid = enteredEmail.contains("@vaniercollege.qc.ca");

            // Password validation
            boolean passwordHasLetter = false;
            boolean passwordHasDigit = false;
            for (char c : enteredPassword.toCharArray()) {
                if (Character.isLetter(c)) {
                    passwordHasLetter = true;
                }
                if (Character.isDigit(c)) {
                    passwordHasDigit = true;
                }
            }
           
            boolean passwordValid = passwordHasLetter && passwordHasDigit;
 
            if (emailValid && passwordValid) {
            messageLabel.setText("Welcome!");
            } else {
            messageLabel.setText("Error: Invalid email or password.");
            }
        });
        
        //Clear Button EventHandler
        clearButton.setOnAction(event -> {
            firstNameField.clear();
            lastNameField.clear();
            emailField.clear();
            pwBox.clear();
        });
        
        Scene scene = new Scene(root, 300, 285);
        stage.setTitle("User Registration Form");
        stage.setScene(scene);
        stage.show();
    }   
    
    private void updateButtonState (Button registerButton, TextField firstNameField, TextField lastNameField, TextField emailField, PasswordField pwBox) {
        boolean allFieldsFilled = !firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !emailField.getText().isEmpty() && !pwBox.getText().isEmpty();
        registerButton.setDisable(!allFieldsFilled);
    }
}
