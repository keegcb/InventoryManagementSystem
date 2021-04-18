package inventory.gui;

import inventory.Inventory;
import inventory.Part;
import inventory.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProductFormController {

    private int proId;
    private String proName;
    private Double proPrice;
    private int proInv;
    private int proMin;
    private int proMax;
    private Stage proStage;
    private ObservableList<Part> searchPart = FXCollections.observableArrayList();
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();

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

    @FXML
    private void initialize(){
        text_ProductId.setText(Integer.toString(Inventory.nextProductID()));

        col_PartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_PartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView_PartData.setItems(Inventory.getAllParts());
    }

    @FXML
    private void searchParts(){
        if (searchField_Parts.getText().isEmpty()){
            tableView_PartData.setItems(Inventory.getAllParts());
        } else {
            try {
                Part idPart = Inventory.lookupPart(Integer.parseInt(searchField_Parts.getText()));
                if (idPart != null) {
                    searchPart.clear();
                    searchPart.add(idPart);
                    tableView_PartData.setItems(searchPart);
                } else if (idPart == null) {
                    MainFormController.displayMessage();
                }
            } catch (NumberFormatException e) {
                if (Inventory.lookupPart(searchField_Parts.getText()) != null) {
                    tableView_PartData.setItems(Inventory.lookupPart(searchField_Parts.getText()));
                } else {
                    MainFormController.displayMessage();
                }
            }
        }
    }

}