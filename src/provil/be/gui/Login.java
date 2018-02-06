package provil.be.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by robin on 23/11/2017.
 */

public class Login extends Application {

    Stage window;

    //<editor-fold desc="Canvas information">
    /*/

    Entire window ->                    Stage
    The content inside the stage ->     Scene
    The way everything is layed out ->  Layout

     */
    //</editor-fold>

    /**
     * Methode van de application class die override is
     * @param args Empty
     */

    public static void startGUI(String[] args){
        launch(args);
    }

    /**
     * Methode om te checken ofdat de ingegeven data correct is, en wordt gekoppeld aan een MySQL database waarbij
     * hij de data gaat ophalen, gaat vergelijken met de ingegeven data en dan een boolean returnen met success of failure.
     * @param name Naam van de gebruiker
     * @param password paasswoord van de gebruiker.
     * @return boolean die aangeeft of de login succesvol is of niet.
     */

    public static boolean checkCredentials(String name, String password){

        return name.equals("Robin") && (password.equals("password"));
    }

    /**
     * Overriden methode van de application class om de window te laten zien.
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
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Password & username input fields
        TextField usernameInput = FXUtils.createTextfield("Insert username here");
        PasswordField passwordInput = FXUtils.createPasswordField("Insert password here");

        // Login & Activate buttons
        Button loginbutton = FXUtils.createButton("Log in");
        Button ActivateButton = FXUtils.createButton("Activate");

        // Set positions of the components
        GridPane.setConstraints(FXUtils.createLabel("Username"), 0, 0);
        GridPane.setConstraints(FXUtils.createLabel("Password"), 0,1);
        GridPane.setConstraints(usernameInput, 1,0);
        GridPane.setConstraints(passwordInput, 1, 1);
        GridPane.setConstraints(loginbutton, 1, 2);
        GridPane.setConstraints(ActivateButton, 2, 2);
        //</editor-fold>

        //<editor-fold desc="Object listener">
        loginbutton.setOnAction(e -> {
            // If credentials are correct, open program.
            if(checkCredentials(usernameInput.getText(), passwordInput.getText())){
                window.close();
                Workspace.display();
            }else{
                // If credentials are not valid, alert the user.
                AlertBox.display("Wrong credentials!", "You inserted the wrong credentials!");
            }
        });
        //</editor-fold>

        //<editor-fold desc="Window properties">
        // Add all components to the grid
        grid.getChildren().addAll(usernameInput, passwordInput, loginbutton);
        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();
        //</editor-fold>

    }
}
