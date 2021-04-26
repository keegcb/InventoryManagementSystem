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

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

import static inventory.Inventory.*;

public class MainFormController {

    private static int index;
    private ObservableList<Part> partSearch = FXCollections.observableArrayList();
    private ObservableList<Product> productSearch = FXCollections.observableArrayList();

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

    @FXML
    private void initialize(){
        col_PartID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        col_PartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        col_PartInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        col_PartPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        tableView_Parts.setItems(Inventory.getAllParts());

        col_ProductID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        col_ProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        col_ProductInv.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        col_ProductPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        tableView_Products.setItems(Inventory.getAllProducts());
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
                } else if (idPart == null) {
                    MainFormController.displayMessage();
                }
            } catch (NumberFormatException e) {
                if (Inventory.lookupPart(searchParts.getText()) != null) {
                    tableView_Parts.setItems(Inventory.lookupPart(searchParts.getText()));
                } else {
                    MainFormController.displayMessage();
                }
            }
        }
    }

    @FXML
    void handleModPart(ActionEvent click) throws IOException{
        Part modPart = tableView_Parts.getSelectionModel().getSelectedItem();
        index = getAllParts().indexOf(modPart);
        if (modPart != null){
            InventoryManagementSystem.openModParts(modPart);
        } else {
            displayMessage();
        }
    }

    @FXML
    void handleDeletePart(ActionEvent click) throws IOException {
        Part selectedPart = tableView_Parts.getSelectionModel().getSelectedItem();
        if (selectedPart != null){
            Alert deleteWarn = new Alert(Alert.AlertType.CONFIRMATION);
            deleteWarn.setTitle("Delete Part");
            deleteWarn.setHeaderText("You are about to delete " + selectedPart.getName() + ".\n" +
                    "Click OK if you would like to proceed.");
            Optional<ButtonType> selected = deleteWarn.showAndWait();
            if (selected.get() == ButtonType.OK) {
                deletePart(selectedPart);
            } else {
                deleteWarn.close();
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
                } else if (idPro == null) {
                    MainFormController.displayMessage();
                }
            } catch (NumberFormatException e) {
                if (Inventory.lookupProduct(searchProducts.getText()) != null) {
                    tableView_Products.setItems(Inventory.lookupProduct(searchProducts.getText()));
                } else {
                    MainFormController.displayMessage();
                }
            }
        }
    }

    @FXML
    void handleModPro(ActionEvent click) throws IOException{
        Product modPro = tableView_Products.getSelectionModel().getSelectedItem();
        index = getAllProducts().indexOf(modPro);
        if (modPro != null){
            InventoryManagementSystem.openModPro(modPro);
        } else {
            displayMessage();
        }
    }

    @FXML
    void handleDeleteProduct(ActionEvent click) throws IOException {
        Product selectedPro = tableView_Products.getSelectionModel().getSelectedItem();
        if (selectedPro != null){
            Alert deleteWarn = new Alert(Alert.AlertType.CONFIRMATION);
            deleteWarn.setTitle("Delete Product");
            deleteWarn.setHeaderText("You are about to delete " + selectedPro.getName() + ".\n" +
                    "Click OK if you would like to proceed.");
            Optional<ButtonType> selected = deleteWarn.showAndWait();
            if (selected.get() == ButtonType.OK) {
                deleteProduct(selectedPro);
            } else {
                deleteWarn.close();
            }
        }
    }

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
            noMatch.setContentText("A part could not be found matching the ID or Name provided.\n" +
                    "Please make sure you have entered a valid Part ID or Name.");
            noMatch.showAndWait();
        }
        if (methodName.equals("deletePart")){
            Alert delete = new Alert(Alert.AlertType.INFORMATION);
            delete.setTitle("Notification");
            delete.setHeaderText("Part Deleted");
            delete.setContentText("The selected part has been removed from the inventory parts list");
            delete.showAndWait();
        }
        if (methodName.equals("handleModPart")){
            Alert openMod = new Alert(Alert.AlertType.ERROR);
            openMod.setTitle("Error");
            openMod.setHeaderText("Could Not Complete Action");
            openMod.setContentText("The part modification screen could not be opened.\n" +
                    "Please verify that you have a valid part selected before clicking the modify button.");
        }
    }
}
