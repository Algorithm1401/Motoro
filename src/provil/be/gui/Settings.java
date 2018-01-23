package provil.be.gui;

import javafx.scene.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import provil.be.Main;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by robin on 24/11/2017.
 */
public class Settings {

    public static void display(){

        //<editor-fold desc="All defined items">
        Stage window = new Stage();
        window.setTitle("Settings");

        BorderPane pane = new BorderPane();

        VBox vBox = new VBox();
        vBox.setSpacing(0.5);

        // Create treeview with all the type of settings of the program
        TreeView<String> shortcutsView = new TreeView<>();
        shortcutsView.setRoot(new TreeItem<>("Project settings"));

        vBox.getChildren().addAll(shortcutsView);

        CheckBox savepathClose = new CheckBox("Save path after close");
        savepathClose.setSelected(Boolean.valueOf(Main.getPropertyValue("save path after close")));

        TextField field = FXUtils.createTextfield(Main.getPropertyValue("saved path"));

        GridPane gridpane = new GridPane();
        //</editor-fold>

        //<editor-fold desc="Window properties">
        gridpane.setHgap(5);
        gridpane.setVgap(10);

        // Add all components to the grid
        gridpane.add(savepathClose, 0, 0);
        gridpane.add(field, 0, 1);

        pane.setLeft(vBox);
        pane.setCenter(gridpane);

        Scene scene = new Scene(pane);
        window.setScene(scene);

        if(Main.getPropertyValue("save path after close") != null) {

            window.show();

        }else{
            File config = FXUtils.getFileFromBrowser(".properties", "user.home", "You need to select the path to the properties propertiesConfig", ".properties");
            Main.setConfigPath(config);
            Settings.display();
        }
        //</editor-fold>

    }

}
