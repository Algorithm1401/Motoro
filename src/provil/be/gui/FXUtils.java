package provil.be.gui;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FXUtils {

    /**
     * Simpelere methodes om makkelijke objecten aan te maken in een JavaFX scene.
     *
     * @return JavaFX objects.
     */

    //<editor-fold desc="JavaFX objects creators">
    public static Button createButton(String title){
        Button button = new Button(title);
        return button;
    }

    public static Label createLabel(String text){
        Label labelToReturn  = new Label(text);
        return labelToReturn;
    }

    public static TextField createTextfield(String defaultText){
        TextField textField = new TextField();
        textField.setPromptText(defaultText);
        return textField;
    }

    public static PasswordField createPasswordField(String defaultText){
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(defaultText);
        return passwordField;
    }

    public static File getFileFromBrowser(String filetypes, String defaultDirectory, String title, String... fileExtensions){

        File fileToReturn = null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        fileChooser.setFileFilter(new FileNameExtensionFilter(filetypes, fileExtensions));
        fileChooser.setCurrentDirectory(new File(System.getProperty(defaultDirectory)));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileToReturn = fileChooser.getSelectedFile();
            System.out.println("Selected propertiesConfig: " + fileToReturn.getAbsolutePath());
        }

        return fileToReturn;

    }
    //</editor-fold>

}
