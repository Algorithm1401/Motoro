package provil.be.gui;

import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import provil.be.Main;
import provil.be.functions.stl.STL;

import java.io.File;

public class Workspace {

    private static final Color lightColor = Color.rgb(255, 255, 149);

    //</editor-fold>
    private static final Color stlColor = Color.rgb(116, 138, 255);
    //<editor-fold desc="Menu items">
    // File menu items
    static MenuItem newitem, openitem, saveitem, saveasitem, settingsitem, generateitem, exititem,

    // Edit menu items
    undoitem, redoitem, cutitem, copyitem, pasteitem, finditem,

    // View menu items
    zoomin, zoomout;
    /**
     * Instellingen voor de stl viewer scene.
     */

    private static File currentFile;
    private static double MODEL_SCALE_FACTOR = 100;
    private static double MODEL_X_OFFSET = 0;
    private static double MODEL_Y_OFFSET = 0;
    private static int VIEWPORT_SIZE = 800;
    private static Scene stlScene;
    private static Group stlView;
    private static PointLight pointLight;
    private static BorderPane pane = new BorderPane();

    /**
     * @param node Node to add listener to
     */

    public static void addMouseScrolling(Node node) {
        node.setOnScroll((ScrollEvent event) -> {
            // Adjust the zoom factor as per your requirement
            System.out.println(event.getDeltaX() + " Mutliplier X");
            System.out.println(event.getDeltaY() + " Multiplier Y");
            double zoomFactor = 1.25;
            double deltaY = event.getDeltaY();
            if (deltaY < 0){
                zoomFactor = 0.75;
            }
            MODEL_SCALE_FACTOR *= zoomFactor;
            pane.setCenter(stlViewer());
        });
    }

    /**
     * @return oppervlakte met de bijhorende 3D mesh data
     */

    static MeshView[] loadMeshViews() {
        StlMeshImporter importer = new StlMeshImporter();
        importer.read(currentFile);
        Mesh mesh = importer.getImport();

        return new MeshView[]{new MeshView(mesh)};
    }

    /**
     * @return 'Applet' met de 3D mesh viewer in
     */

    private static Group buildScene() {
        MeshView[] meshViews = loadMeshViews();
        for (int i = 0; i < meshViews.length; i++) {
            meshViews[i].setTranslateX(VIEWPORT_SIZE / 2 + MODEL_X_OFFSET);
            meshViews[i].setTranslateY(VIEWPORT_SIZE / 2 + MODEL_Y_OFFSET);
            meshViews[i].setTranslateZ(VIEWPORT_SIZE / 2);
            meshViews[i].setScaleX(MODEL_SCALE_FACTOR);
            meshViews[i].setScaleY(MODEL_SCALE_FACTOR);
            meshViews[i].setScaleZ(MODEL_SCALE_FACTOR);

            PhongMaterial sample = new PhongMaterial(stlColor);
            sample.setSpecularColor(lightColor);
            sample.setSpecularPower(16);
            meshViews[i].setMaterial(sample);

            meshViews[i].getTransforms().setAll(new Rotate(38, Rotate.Z_AXIS), new Rotate(20, Rotate.X_AXIS));
        }

        pointLight = new PointLight(lightColor);
        pointLight.setTranslateX(VIEWPORT_SIZE * 3 / 4);
        pointLight.setTranslateY(VIEWPORT_SIZE / 2);
        pointLight.setTranslateZ(VIEWPORT_SIZE / 2);
        PointLight pointLight2 = new PointLight(lightColor);
        pointLight2.setTranslateX(VIEWPORT_SIZE * 1 / 4);
        pointLight2.setTranslateY(VIEWPORT_SIZE * 3 / 4);
        pointLight2.setTranslateZ(VIEWPORT_SIZE * 3 / 4);
        PointLight pointLight3 = new PointLight(lightColor);
        pointLight3.setTranslateX(VIEWPORT_SIZE * 5 / 8);
        pointLight3.setTranslateY(VIEWPORT_SIZE / 2);
        pointLight3.setTranslateZ(0);

        Color ambientColor = Color.rgb(80, 80, 80, 0);
        AmbientLight ambient = new AmbientLight(ambientColor);

        stlView = new Group(meshViews);
        stlView.getChildren().add(pointLight);
        stlView.getChildren().add(pointLight2);
        stlView.getChildren().add(pointLight3);
        stlView.getChildren().add(ambient);

        return stlView;
    }

    /**
     * @param scene Scene om de camera aan toe te voegen.
     * @return Virtual camera
     */

    private static PerspectiveCamera addCamera(Scene scene) {
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera();
        System.out.println("Near Clip: " + perspectiveCamera.getNearClip());
        System.out.println("Far Clip:  " + perspectiveCamera.getFarClip());
        System.out.println("FOV:       " + perspectiveCamera.getFieldOfView());

        scene.setCamera(perspectiveCamera);
        return perspectiveCamera;
    }

    /**
     * @return STL viewer Node om toe te voegen aan een window
     */

    public static Node stlViewer() {
        Group group = buildScene();
        group.setScaleX(2);
        group.setScaleY(2);
        group.setScaleZ(2);
        group.setTranslateX(50);
        group.setTranslateY(50);

        stlScene = new Scene(group, VIEWPORT_SIZE, VIEWPORT_SIZE, true);
        stlScene.setFill(Color.rgb(116, 138, 255));
        addCamera(stlScene);

        addMouseScrolling(stlView);

        return group;
    }

    /**
     * Methode om de workspace window te openen.
     */

    public static void display() {

        //<editor-fold desc="All defined items">
        Stage window = new Stage();
        window.setTitle("Motoro - no STL file selected!");

        // Define propertiesConfig menu items

        newitem = new MenuItem("Setup");
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

        // Define view menu items

        zoomin = new MenuItem("Zoom in");
        zoomout = new MenuItem("Zoom out");

        MenuBar menubar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu viewMenu = new Menu("View");

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

        editMenu.getItems().addAll(

                undoitem,
                redoitem,
                cutitem,
                copyitem,
                pasteitem,
                finditem

        );

        viewMenu.getItems().addAll(

                zoomin,
                zoomout

        );

        //</editor-fold>

        //<editor-fold desc="Menu items listeners">
        // Set listeners for File menu buttons
        newitem.setOnAction(e -> {
            System.out.println("Creating properties file");

            File properties = new File(System.getProperty("user.home") + "/Desktop" + "/configProperties.properties");


        });

        openitem.setOnAction(e -> {
            File selectedFile;
            selectedFile = FXUtils.getFileFromBrowser("OBJ and STL files", "user.home", "Open a OBJ or STL propertiesConfig to edit"
                    , "obj", "stl");
            currentFile = selectedFile;
            TreeView treeView = new TreeView();
            TreeItem<String> root = new TreeItem<>(selectedFile.getName());
            treeView.setRoot(root);
            treeView.setBackground(new Background(new BackgroundFill(Color.grayRgb(32), CornerRadii.EMPTY, Insets.EMPTY)));
            pane.setLeft(treeView);

//            Main.setPropertyValue("saved path", selectedFile.getPath());

            window.setTitle("Motoro: " + selectedFile.getName() + " - " + selectedFile.getPath());

            STL.parseSTL(selectedFile);

            pane.setCenter(stlViewer());

            System.out.println(STL.getStlConverted().toString());

        });

        settingsitem.setOnAction(e -> {
            Settings.display();
        });

        generateitem.setOnAction(e -> {
            Main.generateRobotFiles();
        });

        exititem.setOnAction(e -> {
            if (ExitProgram.display("Exit program?", "Are you sure you want to exit the program?")) {
                window.close();
            }
        });

        zoomin.setOnAction(e -> {

            MODEL_SCALE_FACTOR *= 1.25;
            pane.setCenter(stlViewer());

        });

        zoomout.setOnAction(e -> {

            MODEL_SCALE_FACTOR /= 1.25;
            pane.setCenter(stlViewer());

        });

        //</editor-fold>

        //<editor-fold desc="Window settings">
        // Set listeners for Edit menu buttons

        menubar.getMenus().addAll(fileMenu, editMenu, viewMenu);

        menubar.setBackground(new Background(new BackgroundFill(Color.grayRgb(125), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setTop(menubar);
        pane.setBackground(new Background(new BackgroundFill(Color.grayRgb(32), CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(pane);
        window.setMaximized(true);
        window.setScene(scene);
        window.show();
        //</editor-fold>

    }


}
