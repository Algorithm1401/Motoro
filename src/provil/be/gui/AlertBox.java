package provil.be.gui;

import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Created by robin on 24/11/2017.
 */
public class AlertBox {

    public static void display(String title, String message){

        //<editor-fold desc="All defined items">
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setAlwaysOnTop(true);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = FXUtils.createLabel(message);
        Button closeButton = FXUtils.createButton("Close window");
        closeButton.setOnAction(e -> window.close());
        //</editor-fold>

        //<editor-fold desc="Window properties">
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.show();
        //</editor-fold>

    }

}
