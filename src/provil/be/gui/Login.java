package provil.be.gui;

import javafx.application.Application;
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
import javafx.stage.Stage;
import provil.be.Main;
import provil.be.functions.MySQL;

/**
 * Created by robin on 23/11/2017.
 */

public class Login extends Application {

    Stage window;
    private Button loginbutton, activatebutton;

    //<editor-fold desc="Canvas information">
    /*/

    Entire window ->                    Stage
    The content inside the stage ->     Scene
    The way everything is layed out ->  Layout

     */
    //</editor-fold>

    /**
     * Methode van de application class die override is
     *
     * @param args Empty
     */

    public static void startGUI(String[] args) {
        launch(args);
    }

    public static boolean checkCredentials(String username, String password) {

        boolean userValid = false;

        for (Object s : (MySQL.getKeyValues(2))) {
            if (s instanceof String) {
                System.out.println(s);
                if (((String) s).equalsIgnoreCase(username)) {
                    userValid = true;
                }
            }
        }

        if (userValid) {
            for (Object s : (MySQL.getKeyValues(3))) {
                if (s instanceof String) {
                    System.out.println(s);
                    if (s.equals(password)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    /**
     * Overriden methode van de application class om de window te laten zien.
     *
     * @param primaryStage
     * @throws Exception
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        //<editor-fold desc="All defined items">
        // Login window
        window = primaryStage;
        window.setTitle("Motoro - Login");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Password & username input fields
        Label emailLabel = FXUtils.createLabel("Email:");
        Label passwordLabel = FXUtils.createLabel("Password:");

        emailLabel.setTextFill(Color.grayRgb(255));
        passwordLabel.setTextFill(Color.grayRgb(255));

        TextField usernameInput = FXUtils.createTextfield("Insert username here");
        usernameInput.setStyle("-fx-text-inner-color: blue;");
        usernameInput.setStyle("-fx-text-inner-color: black;");

        PasswordField passwordInput = FXUtils.createPasswordField("Insert password here");

        // TESTING PURPOSES, DELETE AFTER
        Button testbutton = FXUtils.createButton("Test");
        testbutton.setBackground(new Background(new BackgroundFill(Color.grayRgb(125), CornerRadii.EMPTY, Insets.EMPTY)));
        GridPane.setConstraints(testbutton, 3, 1);
        testbutton.setOnAction(e -> {
            window.close();
            Workspace.display();
        });
        //

        // Login & Activate buttons
        loginbutton = FXUtils.createButton("Log in");
        activatebutton = FXUtils.createButton("Activate");

        // Change the distance between the login and activate button
        GridPane.setMargin(activatebutton, new Insets(0, 0, 0, -100));

        // Set positions of the components
        GridPane.setConstraints(emailLabel, 0, 0);
        GridPane.setConstraints(passwordLabel, 0, 1);
        GridPane.setConstraints(usernameInput, 1, 0);
        GridPane.setConstraints(passwordInput, 1, 1);
        GridPane.setConstraints(loginbutton, 1, 2);
        GridPane.setConstraints(activatebutton, 2, 2);
        //</editor-fold>

        //<editor-fold desc="Object listener">
        loginbutton.setOnAction(e -> {

            if (checkCredentials(usernameInput.getText(), passwordInput.getText())) {

                window.close();
                Workspace.display();

            } else {
                // If credentials are not valid, alert the user.
                AlertBox.display("Motoro - Error", "Invalid credentials!");
            }
        });
        //</editor-fold>

        activatebutton.setOnAction(e -> {

            System.out.println(Main.propertiesConfig.getPath());

            Register.display();

        });

        //<editor-fold desc="Window properties">
        // Add all components to the grid
        loginbutton.setBackground(new Background(new BackgroundFill(Color.grayRgb(125), CornerRadii.EMPTY, Insets.EMPTY)));
        activatebutton.setBackground(new Background(new BackgroundFill(Color.grayRgb(125), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.getChildren().addAll(emailLabel, usernameInput, passwordLabel, passwordInput, loginbutton, activatebutton, testbutton);
        grid.setBackground(new Background(new BackgroundFill(Color.grayRgb(32), CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(grid, 300, 200);

        window.setScene(scene);
        window.show();
        //</editor-fold>

    }
}
