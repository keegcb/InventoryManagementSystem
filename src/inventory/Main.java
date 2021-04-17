package inventory;

import inventory.gui.AddPartFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private Stage mainStage;
    private AnchorPane mainWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.mainStage = primaryStage;
        this.mainStage.setTitle("Inventory Management System");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("/inventory/gui/MainForm.fxml")));
        mainWindow = loader.load();
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
        stageControl.createStage(addPartStage);
        addPartStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
