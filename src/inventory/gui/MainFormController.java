package inventory.gui;

import inventory.Inventory;
import inventory.InventoryManagementSystem;
import inventory.Part;
import inventory.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.Optional;

import static inventory.Inventory.*;

public class MainFormController {

    private static int index;
    private ObservableList<Part> partSearch = FXCollections.observableArrayList();
    private ObservableList<Product> productSearch = FXCollections.observableArrayList();
    private static boolean validSelect;

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
    private void initialize(){
        col_PartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_PartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView_Parts.setItems(Inventory.getAllParts());

        col_ProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_ProductInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_ProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView_Products.setItems(Inventory.getAllProducts());
    }

    private boolean isValidSelection(int option){
        switch (option){
            case 1: Part modPartAttempt = tableView_Parts.getSelectionModel().getSelectedItem();
                if(modPartAttempt != null){
                    validSelect = true;
                } else {
                    Alert notValid = new Alert(Alert.AlertType.WARNING);
                    notValid.setTitle("Warning");
                    notValid.setHeaderText("A valid part has not been selected.");
                    notValid.setContentText("Please select a valid part from the list to be modified.");
                    notValid.showAndWait();
                    validSelect = false;
                }
                break;
            case 2: Part removePartAttempt = tableView_Parts.getSelectionModel().getSelectedItem();
                if(removePartAttempt != null){
                    validSelect = true;
                } else {
                    Alert notValid = new Alert(Alert.AlertType.WARNING);
                    notValid.setTitle("Warning");
                    notValid.setHeaderText("A valid part has not been selected.");
                    notValid.setContentText("Please select a valid part from the list to be removed.");
                    notValid.showAndWait();
                    validSelect = false;
                }
                break;
            case 3: Product modProAttempt = tableView_Products.getSelectionModel().getSelectedItem();
                if(modProAttempt != null){
                    validSelect = true;
                } else {
                    Alert notValid = new Alert(Alert.AlertType.WARNING);
                    notValid.setTitle("Warning");
                    notValid.setHeaderText("A valid product has not been selected.");
                    notValid.setContentText("Please select a valid product from the list to be modified.");
                    notValid.showAndWait();
                    validSelect = false;
                }
                break;
            case 4: Product removeProAttempt = tableView_Products.getSelectionModel().getSelectedItem();
                if(removeProAttempt != null){
                    validSelect = true;
                } else {
                    Alert notValid = new Alert(Alert.AlertType.WARNING);
                    notValid.setTitle("Warning");
                    notValid.setHeaderText("A valid product has not been selected.");
                    notValid.setContentText("Please select a valid product from the list to be deleted.");
                    notValid.showAndWait();
                    validSelect = false;
                }
                break;
        }
        return validSelect;
    }

    @FXML
    void handleAddPart(ActionEvent click) throws IOException {
        InventoryManagementSystem.openAddParts();
    }

    @FXML
    void handleSearchParts(ActionEvent search) {
        if (searchParts.getText().isEmpty()) {
            tableView_Parts.setItems(Inventory.getAllParts());
        } else {
            try {
                Part idPart = Inventory.lookupPart(Integer.parseInt(searchParts.getText()));
                if (idPart != null) {
                    searchParts.clear();
                    partSearch.add(idPart);
                    tableView_Parts.setItems(partSearch);
                }
            } catch (NumberFormatException e) {
                if (Inventory.lookupPart(searchParts.getText()) != null) {
                    tableView_Parts.setItems(Inventory.lookupPart(searchParts.getText()));
                }
            }
        }
    }

    @FXML
    void handleModPart(ActionEvent click) throws IOException{
        if(isValidSelection(1)){
            Part modPart = tableView_Parts.getSelectionModel().getSelectedItem();
            index = getAllParts().indexOf(modPart);
            if (modPart != null){
                InventoryManagementSystem.openModParts(modPart);
            }
        }
    }

    @FXML
    void handleDeletePart(ActionEvent click) throws IOException {
        if(isValidSelection(2)){
            Part selectedPart = tableView_Parts.getSelectionModel().getSelectedItem();
            if (selectedPart != null) {
                Alert deleteWarn = new Alert(Alert.AlertType.CONFIRMATION);
                deleteWarn.setTitle("Delete Part");
                deleteWarn.setHeaderText("You are about to delete " + selectedPart.getName() + ".\n" +
                        "Click OK if you would like to proceed.");
                Optional<ButtonType> selected = deleteWarn.showAndWait();
                if (selected.get() == ButtonType.OK) {
                    deletePart(selectedPart);
                }
            }
        }
    }

    @FXML
    void handleAddPro(ActionEvent click) throws IOException {
        InventoryManagementSystem.openAddPro();
    }

    @FXML
    void handleSearchPro() {
        if (searchProducts.getText().isEmpty()) {
            tableView_Products.setItems(Inventory.getAllProducts());
        } else {
            try {
                Product idPro = Inventory.lookupProduct(Integer.parseInt(searchProducts.getText()));
                if (idPro != null) {
                    searchProducts.clear();
                    productSearch.add(idPro);
                    tableView_Products.setItems(productSearch);
                }
            } catch (NumberFormatException e) {
                if (Inventory.lookupProduct(searchProducts.getText()) != null) {
                    tableView_Products.setItems(Inventory.lookupProduct(searchProducts.getText()));
                }
            }
        }
    }

    @FXML
    void handleModPro(ActionEvent click) throws IOException{
        if(isValidSelection(3)) {
            Product modPro = tableView_Products.getSelectionModel().getSelectedItem();
            index = getAllProducts().indexOf(modPro);
            if (modPro != null) {
                InventoryManagementSystem.openModPro(modPro);
            }
        }
    }

    @FXML
    void handleDeleteProduct(ActionEvent click) throws IOException {
        if(isValidSelection(4)){
            Product selectedPro = tableView_Products.getSelectionModel().getSelectedItem();
            if (selectedPro != null){
                if (selectedPro.getAllAssociatedParts().isEmpty()){
                    Alert deleteWarn = new Alert(Alert.AlertType.CONFIRMATION);
                    deleteWarn.setTitle("Delete Product");
                    deleteWarn.setHeaderText("You are about to delete " + selectedPro.getName() + ".\n" +
                            "Click OK if you would like to proceed.");
                    Optional<ButtonType> selected = deleteWarn.showAndWait();
                    if (selected.get() == ButtonType.OK) {
                        deleteProduct(selectedPro);
                    }
                } else {
                    Alert hasParts = new Alert(Alert.AlertType.WARNING);
                    hasParts.setTitle("Warning");
                    hasParts.setHeaderText("The selected product still has associated parts");
                    hasParts.setContentText("\nPlease modify the selected product and remove all associated parts before deleting.");
                    hasParts.showAndWait();
                }
            }
        }
    }

    @FXML
    void handleExit(ActionEvent click) throws IOException {
        Alert exitWarn = new Alert(Alert.AlertType.CONFIRMATION);
        exitWarn.setTitle("Exit");
        exitWarn.setTitle("Close Inventory Management?");
        exitWarn.setContentText("You are about to close the Inventory Management application.\n " +
                "If you would like to proceed, please click OK.");
        Optional<ButtonType> selected = exitWarn.showAndWait();
        if (selected.get() == ButtonType.OK){
            System.exit(0);
        } else {
            Alert exitCancel = new Alert(Alert.AlertType.INFORMATION);
            exitCancel.setTitle("Cancel");
            exitCancel.setTitle("Application was not closed.");
            exitCancel.showAndWait();
        }
    }

    public static int getIndex() {
        return index;
    }
}
