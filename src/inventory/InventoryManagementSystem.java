package inventory;

import inventory.gui.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * class InventoryManagementSystem.java
 * Simulates interconnected system of inventory and hosts Stages for UI to be called.
 *
 * FUTURE ENHANCEMENT ModifyPartFormController and ModifyProductFormController are both very similar to their add form counterparts,
 * in the future some of the methods could be moved to the add form controller classes and remove the modify controller class completely.
 *
 * FUTURE ENHANCEMENT The search functionality is currently case sensitive, this could be changed in the future
 * to allow searches of parts that are different letter case.
 */
public class InventoryManagementSystem extends Application {

    private static Stage mainStage;

    /**
     * Sets stage to host UI after program launch.
     * @param primaryStage Stage to be set
     * @throws IOException Failed to load MainForm.fxml to AnchorPane mainWindow
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        mainStage = primaryStage;
        mainStage.setTitle("Inventory Management System");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("/inventory/gui/MainForm.fxml")));
        AnchorPane mainWindow = loader.load();
        Scene mainScene = new Scene(mainWindow);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    /**
     * Main method that starts program.
     * @param args Arguments for program launch
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Launches AddPartForm.fxml to display UI.
     */
    public static void openAddParts() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(InventoryManagementSystem.class.getResource("/inventory/gui/AddPartForm.fxml")));
            AnchorPane addPartWindow = loader.load();

            Stage addPartStage = new Stage();
            addPartStage.setTitle("Add Part");
            addPartStage.initModality(Modality.WINDOW_MODAL);
            addPartStage.initOwner(mainStage);

            Scene partScene = new Scene(addPartWindow);
            addPartStage.setScene(partScene);
            AddPartFormController stageControl = loader.getController();
            stageControl.createAddPartStage(addPartStage);
            addPartStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches ModifyPartForm.fxml to display UI.
     * @param modPart Part to be modified
     */
    public static void openModParts(Part modPart) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(InventoryManagementSystem.class.getResource("/inventory/gui/ModifyPartForm.fxml")));
            AnchorPane modPartWindow = loader.load();

            Stage modPartStage = new Stage();
            modPartStage.setTitle("Modify Part");
            modPartStage.initModality(Modality.WINDOW_MODAL);
            modPartStage.initOwner(mainStage);

            Scene partScene = new Scene(modPartWindow);
            modPartStage.setScene(partScene);
            ModifyPartFormController stageControl = loader.getController();
            stageControl.createModPartStage(modPartStage);
            stageControl.setModPart(modPart);
            modPartStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Launches AddProductForm.fxml to display UI.
     */
    public static void openAddPro() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(InventoryManagementSystem.class.getResource("/inventory/gui/AddProductForm.fxml")));
            AnchorPane addProductWindow = loader.load();

            Stage addProductStage = new Stage();
            addProductStage.setTitle("Add Product");
            addProductStage.initModality(Modality.WINDOW_MODAL);
            addProductStage.initOwner(mainStage);

            Scene productScene = new Scene(addProductWindow);
            addProductStage.setScene(productScene);
            AddProductFormController stageControl = loader.getController();
            stageControl.createAddProduct(addProductStage);
            addProductStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches ModifyProductForm.fxml to display UI.
     * @param modPro Product to be modified
     */
    public static void openModPro(Product modPro) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(InventoryManagementSystem.class.getResource("/inventory/gui/ModifyProductForm.fxml")));
            AnchorPane modProductWindow = loader.load();

            Stage modProductStage = new Stage();
            modProductStage.setTitle("Modify Product");
            modProductStage.initModality(Modality.WINDOW_MODAL);
            modProductStage.initOwner(mainStage);

            Scene modProScene = new Scene(modProductWindow);
            modProductStage.setScene(modProScene);
            ModifyProductFormController stageControl = loader.getController();
            stageControl.createModProStage(modProductStage);

            stageControl.setModPro(modPro);
            modProductStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
