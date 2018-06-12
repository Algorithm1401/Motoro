package provil.be.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import provil.be.functions.MySQL;

import java.util.Arrays;

public class Register {

    static Stage registerWindow;

    public static void display(){

        registerWindow = new Stage();
        registerWindow.setTitle("Motoro - Activate your account");

        GridPane pane = new GridPane();

        pane.setVgap(20);
        pane.setHgap(10);
        pane.setPadding(new Insets(10, 10, 10, 10));

        Label keyLabel = FXUtils.createLabel("Key:");
        Label emailLabel = FXUtils.createLabel("Email:");
        Label passwordLabel = FXUtils.createLabel("Password:");
        Label passwordAgainLabel = FXUtils.createLabel("Confirm password:");

        TextField key = FXUtils.createTextfield("Insert activation code");
        TextField email = FXUtils.createTextfield("Insert email here");
        PasswordField password = FXUtils.createPasswordField("Insert password here");
        PasswordField passwordAgain = FXUtils.createPasswordField("Insert password again");

        Button register = FXUtils.createButton("Register");
        register.setBackground(new Background(new BackgroundFill(Color.grayRgb(125), CornerRadii.EMPTY, Insets.EMPTY)));

        register.setOnAction(e -> {

            if(password.getText().equals(passwordAgain.getText())){

                boolean keyValid = false;

                for(Object o : MySQL.getKeyValues(1)){
                    if(o instanceof String){
                        if(key.getText().equals(o)){

                            keyValid = true;
                        }
                    }
                }

                if(keyValid) {
                    if(password.getText().length() >= 8) {
                        MySQL.addUser(Arrays.asList(key.getText(), email.getText(), password.getText()));
                        registerWindow.close();
                        AlertBox.display("Motoro - Account created!", "Your account has been registered, you can now log in!");
                    }else{
                        AlertBox.display("Motoro - Error", "password has to be at least 8 characters long!");
                    }

                }else{
                    AlertBox.display("Motoro - Error", "You have entered an invalid key!");
                }
            }else{
                AlertBox.display("Motoro - Error", "Passwords do not match!");
            }

        });

        int i = 0;

        for(Label l : Arrays.asList(keyLabel, emailLabel, passwordLabel, passwordAgainLabel)){
            l.setTextFill(Color.grayRgb(255));
            GridPane.setConstraints(l, 0, i);
            i++;
        }
        i = 0;
        for(TextField f : Arrays.asList(key, email, password, passwordAgain)) {
            f.setStyle("-fx-text-inner-color: black;");
            GridPane.setConstraints(f, 1, i);
            i++;
        }

        GridPane.setConstraints(register, 0, i);
        pane.getChildren().addAll(keyLabel, key, emailLabel, email, passwordLabel, password, passwordAgainLabel, passwordAgain, register);
        pane.setBackground(new Background(new BackgroundFill(Color.grayRgb(32), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane, 300, 300);

        registerWindow.setScene(scene);
        registerWindow.setAlwaysOnTop(true);
        registerWindow.initModality(Modality.APPLICATION_MODAL);
        registerWindow.setResizable(false);
        registerWindow.show();

    }

}
