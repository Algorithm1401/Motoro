package provil.be.gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import provil.be.Main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robin on 24/11/2017.
 */
public class Settings {

    /**
     * Methode om de settings window te laten zien waar je de properties kan aanpassen.
     */

    public static void display(){

        //<editor-fold desc="All defined items">
        Stage window = new Stage();
        window.setTitle("Settings");

        BorderPane pane = new BorderPane();

        VBox vBox = new VBox();
        vBox.setSpacing(0.5);

        TreeItem root = new TreeItem<>("Project settings");
        TreeItem objectSet = new TreeItem<>("Object properties");
        TreeItem toolData = new TreeItem<>("Tool data");

        // Create treeview with all the type of settings of the program
        TreeView<String> shortcutsView = new TreeView<>();
        shortcutsView.setRoot(root);
        shortcutsView.getRoot().getChildren().addAll(

                objectSet,
                toolData

        );

        root.setExpanded(true);

        vBox.getChildren().addAll(shortcutsView);

        GridPane gridpane = new GridPane();
        //</editor-fold>

        //<editor-fold desc="Window properties">
        gridpane.setHgap(5);
        gridpane.setVgap(10);

        shortcutsView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {

            gridpane.getChildren().removeAll(gridpane.getChildren());

           switch(v.getValue().getValue()){

               case "Project settings":

                   CheckBox savepathClose = new CheckBox("Save path after close");
                   savepathClose.setSelected(Boolean.valueOf(Main.getPropertyValue("save path after close")));

                   TextField field = FXUtils.createTextfield(Main.getPropertyValue("saved path"));

                   // Add all components to the grid
                   gridpane.add(savepathClose, 0, 0);
                   gridpane.add(field, 0, 1);

                   break;

               case "Object properties":

                   Label objectLabel = FXUtils.createLabel("Object dimensions and properties (mm)");
                   Label lengthL = FXUtils.createLabel("Length: ");
                   Label widthL = FXUtils.createLabel("Width: ");
                   Label heightL = FXUtils.createLabel("Height: ");
                   TextField lengthF = FXUtils.createTextfield("Insert length");
                   TextField widthF = FXUtils.createTextfield("Insert width");
                   TextField heightF = FXUtils.createTextfield("Insert heigth");

                   Button apply = FXUtils.createButton("Apply");

                   gridpane.add(objectLabel, 0, 0);
                   gridpane.add(lengthL, 0, 1);
                   gridpane.add(lengthF, 1, 1);
                   gridpane.add(widthL, 0, 2);
                   gridpane.add(widthF, 1, 2);
                   gridpane.add(heightL, 0, 3);
                   gridpane.add(heightF, 1, 3);
                   gridpane.add(apply, 0, 4);

                   apply.setOnAction(e -> {

                       Map<String, String> configValues = new HashMap<>();

                       configValues.put("Object length", lengthF.getText());
                       configValues.put("Object width", widthF.getText());
                       configValues.put("Object heigth", heightF.getText());

                       Main.setPropertyValue(configValues);

                       System.out.println("Set object properties to: " + Main.getPropertyValue("Object length") + ", " + Main.getPropertyValue("Object width")
                       + ", " + Main.getPropertyValue("Object heigth"));

                   });

                   break;

               case "Tool data":

                   Label tooldata = FXUtils.createLabel("Insert tool data (mm)");
                   Label millingDiameter = FXUtils.createLabel("Mill diameter: ");
                   Label millingDepth = FXUtils.createLabel("Mill depth");
                   TextField millingDiameterF = FXUtils.createTextfield("diameter");
                   TextField millingDepthF = FXUtils.createTextfield("depth");

                   Button applyToolData = FXUtils.createButton("Apply");

                   gridpane.add(tooldata, 0, 0);
                   gridpane.add(millingDiameter, 0, 1);
                   gridpane.add(millingDiameterF, 1, 1);
                   gridpane.add(millingDepth, 0, 2);
                   gridpane.add(millingDepthF,1, 2);
                   gridpane.add(applyToolData, 0, 3);

                   applyToolData.setOnAction(e -> {

                       Map<String, String> configValues = new HashMap<>();

                       configValues.put("Milling diameter", millingDiameterF.getText());
                       configValues.put("Milling depth", millingDepthF.getText());

                       Main.setPropertyValue(configValues);

                       System.out.println("Milling diameter set to: " + Main.getPropertyValue("Milling diameter"));

                   });

                   break;

           }

        });


        pane.setLeft(vBox);
        pane.setCenter(gridpane);

        Scene scene = new Scene(pane);
        window.setScene(scene);

        if(Main.ConfigExists()) {

            window.show();

        }else{

            Main.createDefaultConfig();

        }
        //</editor-fold>

    }

}
