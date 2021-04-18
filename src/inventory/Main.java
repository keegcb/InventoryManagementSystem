package inventory;

import inventory.gui.AddPartFormController;
import inventory.gui.ModifyPartFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.mainStage = primaryStage;
        this.mainStage.setTitle("Inventory Management System");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("/inventory/gui/MainForm.fxml")));
        AnchorPane mainWindow = loader.load();
        Scene mainScene = new Scene(mainWindow);
        this.mainStage.setScene(mainScene);
        this.mainStage.show();
    }

    public void openAddParts() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("/inventory/gui/AddPartForm.fxml")));
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
    }

    public void openModParts() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("/inventory/gui/ModifyPartForm.fxml")));
        AnchorPane modPartWindow = loader.load();

        Stage modPartStage = new Stage();
        modPartStage.setTitle("Modify Part");
        modPartStage.initModality(Modality.WINDOW_MODAL);
        modPartStage.initOwner(mainStage);

        Scene partScene = new Scene(modPartWindow);
        modPartStage.setScene(partScene);
        ModifyPartFormController stageControl = loader.getController();
        stageControl.createModPartStage(modPartStage);
        modPartStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
