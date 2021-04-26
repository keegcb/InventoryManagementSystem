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

public class InventoryManagementSystem extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        mainStage.setTitle("Inventory Management System");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("/inventory/gui/MainForm.fxml")));
        AnchorPane mainWindow = loader.load();
        Scene mainScene = new Scene(mainWindow);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void openAddParts() throws IOException {
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

    public static void openModParts(Part modPart) throws IOException {
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
            MainFormController.displayMessage();
        }
    }

    public static void openAddPro() throws IOException {
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

    public static void openModPro(Product modPro) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(InventoryManagementSystem.class.getResource("/inventory/gui/ModifyProductForm.fxml")));
            AnchorPane modProductWindow = loader.load();

            Stage modProductStage = new Stage();
            modProductStage.setTitle("Modify Product");
            modProductStage.initModality(Modality.WINDOW_MODAL);
            modProductStage.initOwner(mainStage);

            Scene productScene = new Scene(modProductWindow);
            modProductStage.setScene(productScene);
            ModifyProductFormController stageControl = loader.getController();
            stageControl.createModProStage(modProductStage);
            stageControl.setModPro(modPro);
            modProductStage.showAndWait();
        } catch (IOException e){
            MainFormController.displayMessage();
        }
    }
}
