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
public class ExitProgram {

    static boolean answer;

    public static boolean display(String title, String message){

        //<editor-fold desc="All defined items">
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setAlwaysOnTop(true);
        window.setTitle(title);
        window.setMinWidth(250);

        BorderPane pane = new BorderPane();

        // Create all components
        Label label = FXUtils.createLabel(message);
        Button button_yes = FXUtils.createButton("Yes");
        Button button_no = FXUtils.createButton("No");
        //</editor-fold>

        //<editor-fold desc="Object listeners">
        // Make the boolean to return true or false and close the current window
        button_yes.setOnAction(e -> {
            answer=true;
            window.close();
        });

        button_no.setOnAction(e -> {
            answer=false;
            window.close();
        });
        //</editor-fold>

        //<editor-fold desc="Window properties">
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);

        HBox layout2 = new HBox(10);
        layout2.getChildren().addAll(button_yes, button_no);
        layout2.setAlignment(Pos.CENTER);

        pane.setTop(layout);
        pane.setCenter(layout2);

        Scene scene = new Scene(pane);
        window.setScene(scene);

        window.showAndWait();
        //</editor-fold>

        return answer;

    }

}
