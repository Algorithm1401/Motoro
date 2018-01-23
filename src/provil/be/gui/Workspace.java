package provil.be.gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import provil.be.Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Workspace {

    //<editor-fold desc="Menu items">
    // File menu items
    static MenuItem newitem, openitem, saveitem, saveasitem, settingsitem, generateitem, exititem,

    // Edit menu items
    undoitem, redoitem, cutitem, copyitem, pasteitem, finditem;
    //</editor-fold>

    public static void display(){

        //<editor-fold desc="All defined items">
        Stage window = new Stage();
        window.setTitle("Motoro - no propertiesConfig selected!");

        // Define propertiesConfig menu items

        newitem = new MenuItem("New");
        openitem = new MenuItem("Open");
        saveitem = new MenuItem("Save");
        saveasitem = new MenuItem("Save as");
        settingsitem = new MenuItem("Settings");
        generateitem = new MenuItem("Generate files");
        exititem = new MenuItem("Exit");

        // Define edit menu items

        undoitem = new MenuItem("Undo");
        redoitem = new MenuItem("Redo");
        cutitem = new MenuItem("Cut");
        copyitem = new MenuItem("Copy");
        pasteitem = new MenuItem("Paste");
        finditem = new MenuItem("Find");

        BorderPane pane = new BorderPane();
        MenuBar menubar = new MenuBar();

        Menu fileMenu = new Menu("File");
        //</editor-fold>

        //<editor-fold desc="First dropdown menu items">
        fileMenu.getItems().addAll(

                newitem,
                openitem,
                saveitem,
                saveasitem,
                new SeparatorMenuItem(),
                generateitem,
                settingsitem,
                new SeparatorMenuItem(),
                exititem

        );
        //</editor-fold>

        //<editor-fold desc="Second dropdown menu items">
        Menu editMenu = new Menu("Edit");

        editMenu.getItems().addAll(

                undoitem,
                redoitem,
                cutitem,
                copyitem,
                pasteitem,
                finditem

        );
        //</editor-fold>

        //<editor-fold desc="Menu items listeners">
        // Set listeners for File menu buttons
        newitem.setOnAction(e -> System.out.println("creating new propertiesConfig.."));

        openitem.setOnAction(e -> {
            File selectedFile;
            selectedFile = FXUtils.getFileFromBrowser("OBJ and STL files", "user.home", "Open a OBJ or STL propertiesConfig to edit"
                    , "obj", "stl");
                TreeView treeView = new TreeView();
                TreeItem<String> root = new TreeItem<>(selectedFile.getName());
                treeView.setRoot(root);
                pane.setLeft(treeView);

                List<String> filePieces = new ArrayList<>();

            Main.setPropertyValue("saved path", selectedFile.getPath());

                // REMOVE LATER, TESTING PURPOSES
                filePieces = Arrays.asList("Banden", "Dak", "Motor");

                for (String s : filePieces) {
                    TreeItem<String> piece = new TreeItem<>(s);
                    root.getChildren().add(piece);
                }

                window.setTitle("Motoro: " + selectedFile.getName() + " - " + selectedFile.getPath());

        });

        settingsitem.setOnAction(e -> Settings.display());

        generateitem.setOnAction(e -> Main.generateRobotFiles());

        exititem.setOnAction(e -> {
            if(ExitProgram.display("Exit program?", "Are you sure you want to exit the program?")){
                window.close();
            }
        });
        //</editor-fold>

        //<editor-fold desc="Window settings">
        // Set listeners for Edit menu buttons

        menubar.getMenus().addAll(fileMenu, editMenu);

        Group root = new Group();
        Canvas canvas = new Canvas(1200, 1200);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

        root.getChildren().add(canvas);

        pane.setTop(menubar);
        pane.setCenter(root);

        Scene scene = new Scene(pane);
        window.setMaximized(true);
        window.setScene(scene);
        window.show();
        //</editor-fold>

    }

}
