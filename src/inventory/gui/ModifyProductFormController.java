package inventory.gui;

import inventory.Inventory;
import inventory.Part;
import inventory.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ModifyProductFormController {

    private int proId;
    private String proName;
    private Double proPrice;
    private int proInv;
    private int proMin;
    private int proMax;
    private Stage modStage;
    private ObservableList<Part> searchPart = FXCollections.observableArrayList();
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();
    private Product modPro;

    @FXML
    private TextField searchField_Parts;
    @FXML
    private TableView<Part> tableView_PartData;
    @FXML
    private TableColumn<Part, Integer> col_PartId;
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
    private TableColumn<Part, Integer> col_AssPartId;
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
    private TextField text_ProductId;
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

    public void createModProStage (Stage modStage){
        this.modStage = modStage;
    }

    @FXML
    private void initialize(){
        col_PartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_PartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView_PartData.setItems(Inventory.getAllParts());

        col_AssPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_AssPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_AssPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_AssPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void setModPro(Product selectedPro){
        modPro = selectedPro;
        associatedPart = modPro.getAllAssociatedParts();
        tableView_AssociatedPart.setItems(associatedPart);

        text_ProductId.setText(Integer.toString(modPro.getId()));
        text_ProductName.setText(modPro.getName());
        text_ProductInv.setText(Integer.toString(modPro.getStock()));
        text_ProductPrice.setText(Double.toString(modPro.getPrice()));
        text_ProductMax.setText(Integer.toString(modPro.getMax()));
        text_ProductMin.setText(Integer.toString(modPro.getMin()));
    }

}
