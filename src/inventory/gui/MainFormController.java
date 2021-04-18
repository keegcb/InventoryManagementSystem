package inventory.gui;

import inventory.Part;
import inventory.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class MainFormController {

    private static int index;

    @FXML
    private TextField searchParts;
    @FXML
    private TableView<Part> tableView_Parts;
    @FXML
    private TableColumn<Part, Integer> col_PartID;
    @FXML
    private TableColumn<Part, String> col_PartName;
    @FXML
    private TableColumn<Part, Integer> col_PartInv;
    @FXML
    private TableColumn<Part, Double> col_PartPrice;
    @FXML
    private Button button_PartsAdd;
    @FXML
    private Button button_PartsModify;
    @FXML
    private Button button_PartsDelete;
    @FXML
    private TextField searchProducts;
    @FXML
    private TableView<Product> tableView_Products;
    @FXML
    private TableColumn<Product, Integer> col_ProductID;
    @FXML
    private TableColumn<Product, String> col_ProductName;
    @FXML
    private TableColumn<Product, Integer> col_ProductInv;
    @FXML
    private TableColumn<Product, Double> col_ProductPrice;
    @FXML
    private Button button_ProductsAdd;
    @FXML
    private Button button_ProductsModify;
    @FXML
    private Button button_ProductsDelete;
    @FXML
    private Button button_Exit;

    public static int getIndex() {
        return index;
    }


    public static void displayMessage(){
        String methodName = null;
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        for (int i=0; i< stacktrace.length; i++){
            if (stacktrace[i].getMethodName().equals("displayMessage")){
                methodName = stacktrace[i+1].getMethodName();
                break;
            }
        }
        if (methodName.equals("searchParts")){
            Alert noMatch = new Alert(Alert.AlertType.ERROR);
            noMatch.setTitle("Error");
            noMatch.setHeaderText("No Matches");
            noMatch.setContentText("A part could not be found matching the ID or Name provided.\nPlease make sure you have entered a valid Part ID or Name.");
            noMatch.showAndWait();
        }
        if (methodName.equals("deletePart")){
            Alert delete = new Alert(Alert.AlertType.INFORMATION);
            delete.setTitle("Notification");
            delete.setHeaderText("Part Deleted");
            delete.setContentText("The selected part has been removed from the inventory parts list");
            delete.showAndWait();
        }
    }
}
