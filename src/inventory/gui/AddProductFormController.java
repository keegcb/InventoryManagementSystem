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
    private TableView<Part> tableView_PartData;
    @FXML
    private TableColumn<Part, Integer> col_PartID;
    @FXML
    private TableColumn<Part, String> col_PartName;
    @FXML
    private TableColumn<Part, Integer> col_PartInv;
    @FXML
    private TableColumn<Part, Double> col_PartPrice;
    @FXML
    private Button button_AddPart;
    @FXML
    private TableView<Part> tableView_AssociatedPart;
    @FXML
    private TableColumn<Part, Integer> col_AssPartID;
    @FXML
    private TableColumn<Part, String> col_AssPartName;
    @FXML
    private TableColumn<Part, Integer> col_AssPartInv;
    @FXML
    private TableColumn<Part, Double> col_AssPartPrice;
    @FXML
    private Button button_RemovePart;
    @FXML
    private Button button_AddProduct;
    @FXML
    private Button button_Cancel;
    @FXML
    private TextField text_ProductID;
    @FXML
    private TextField text_ProductName;
    @FXML
    private TextField text_ProductInv;
    @FXML
    private TextField text_ProductPrice;
    @FXML
    private TextField text_ProductMax;
    @FXML
    private TextField text_ProductMin;

}