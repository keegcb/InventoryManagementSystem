package inventory.gui;

import inventory.Part;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddProductFormController {

    @FXML
    private TextField searchField_AddProductPart;
    @FXML
    private TableView<Part> tableView_AddProductPartData;
    @FXML
    private TableColumn<Part, Integer> column_PartID;
    @FXML
    private TableColumn<Part, String> column_PartName;
    @FXML
    private TableColumn<Part, Integer> column_PartInventory;
    @FXML
    private TableColumn<Part, Double> column_PartPrice;
    @FXML
    private Button button_AddProductPart;
    @FXML
    private TableView<Part> tableView_AddProductAssociatedPart;
    @FXML
    private TableColumn<Part, Integer> column_AssociatedPartID;
    @FXML
    private TableColumn<Part, String> column_AssociatedPartName;
    @FXML
    private TableColumn<Part, Integer> column_AssociatedPartInventory;
    @FXML
    private TableColumn<Part, Double> column_AssociatedPartPrice;
    @FXML
    private Button button_RemoveProductPart;
    @FXML
    private Button button_AddProduct;
    @FXML
    private Button button_AddProductCancel;
    @FXML
    private TextField text_AddProductID;
    @FXML
    private TextField text_AddProductName;
    @FXML
    private TextField text_AddProductInv;
    @FXML
    private TextField text_AddProductPrice;
    @FXML
    private TextField text_AddProductMax;
    @FXML
    private TextField text_ProductMin;

}