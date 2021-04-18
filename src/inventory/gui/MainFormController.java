package inventory.gui;

import inventory.Part;
import inventory.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
}
