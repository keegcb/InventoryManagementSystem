package inventory.gui;

import inventory.Inventory;
import inventory.InventoryManagementSystem;
import inventory.Part;
import inventory.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Optional;
import static inventory.Inventory.*;

/**
 * class MainFormController.java
 * Acts as controller and validation for the Main Form UI.
 * Allows the addition/removal/modification of Parts and Products objects with their associated Inventory lists.
 */
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

    /**
     * Initializes the main form by setting cell column properties for both the parts and products table.
     * Also sets the table items using getAllParts and getAllProducts.
     */
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

    /**
     * Verifies if a valid part or product is selected from their respective tables before committing add or remove action.
     * Warning message displays if there are no valid parts or products selected from the tables.
     * @param option Integer value for switch statement to determine the calling method
     * @return True if there is a valid selected part, false if there is no valid selection
     */
    private boolean isValidSelection(int option){
        switch (option) {
            case 1 -> {
                Part modPartAttempt = tableView_Parts.getSelectionModel().getSelectedItem();
                if (modPartAttempt != null) {
                    validSelect = true;
                } else {
                    Alert notValid = new Alert(Alert.AlertType.WARNING);
                    notValid.setTitle("Warning");
                    notValid.setHeaderText("A valid part has not been selected.");
                    notValid.setContentText("Please select a valid part from the list to be modified.");
                    notValid.showAndWait();
                    validSelect = false;
                }
            }
            case 2 -> {
                Part removePartAttempt = tableView_Parts.getSelectionModel().getSelectedItem();
                if (removePartAttempt != null) {
                    validSelect = true;
                } else {
                    Alert notValid = new Alert(Alert.AlertType.WARNING);
                    notValid.setTitle("Warning");
                    notValid.setHeaderText("A valid part has not been selected.");
                    notValid.setContentText("Please select a valid part from the list to be removed.");
                    notValid.showAndWait();
                    validSelect = false;
                }
            }
            case 3 -> {
                Product modProAttempt = tableView_Products.getSelectionModel().getSelectedItem();
                if (modProAttempt != null) {
                    validSelect = true;
                } else {
                    Alert notValid = new Alert(Alert.AlertType.WARNING);
                    notValid.setTitle("Warning");
                    notValid.setHeaderText("A valid product has not been selected.");
                    notValid.setContentText("Please select a valid product from the list to be modified.");
                    notValid.showAndWait();
                    validSelect = false;
                }
            }
            case 4 -> {
                Product removeProAttempt = tableView_Products.getSelectionModel().getSelectedItem();
                if (removeProAttempt != null) {
                    validSelect = true;
                } else {
                    Alert notValid = new Alert(Alert.AlertType.WARNING);
                    notValid.setTitle("Warning");
                    notValid.setHeaderText("A valid product has not been selected.");
                    notValid.setContentText("Please select a valid product from the list to be deleted.");
                    notValid.showAndWait();
                    validSelect = false;
                }
            }
        }
        return validSelect;
    }

    /**
     * Opens the Add Part Form when the add button for the parts table is clicked.
     */
    @FXML
    void handleAddPart() {
        InventoryManagementSystem.openAddParts();
    }

    /**
     * Searches parts list for a part with a matching numerical ID value, or for a matching/partially matching name.
     * Results of the search are displayed in the parts table, or a message displays if a matching part is not found.
     *
     * RUNTIME ERROR When originally writing the search method it would display the the searched item in the table along
     * with every other item that was searched previously and the search results would just grow into a long list.
     * To resolve the problem I added partSearch.clear() to the beginning of the search so it would flush out the results
     * of any previous search, however, this caused a Null Pointer Reference exception when the user made a search for a non-matching
     * string name. The table would appear empty and due to the exception the user would no longer be able to see any of the
     * parts in the list. I resolved this by putting the partSearch.clear() in a try block so it would catch the Null Pointer error.
     */
    @FXML
    void handleSearchParts() {
        try{
            partSearch.clear();
        } catch (NullPointerException e){
            System.out.println("couldn't clear the list since it doesn't exist");
        }

        if (searchParts.getText().isEmpty()) {
            if (!tableView_Parts.getItems().equals(Inventory.getAllParts())){
                tableView_Parts.setItems(Inventory.getAllParts());
            }
        } else {
            try {
                Part idPart = Inventory.lookupPart(Integer.parseInt(searchParts.getText()));
                if (idPart != null) {
                    searchParts.clear();
                    partSearch.add(idPart);
                    tableView_Parts.setItems(partSearch);
                } else {
                    Alert noId = new Alert(Alert.AlertType.INFORMATION);
                    noId.setTitle("No Part Found");
                    noId.setHeaderText("Part ID Not Found");
                    noId.setContentText("A part with the specified ID could not be found in the list.\n" +
                            " Please make sure that the ID you have entered is a valid ID from the parts list.");
                    noId.showAndWait();
                    tableView_Parts.setItems(Inventory.getAllParts());
                }
            } catch (NumberFormatException e) {
                String searchName = searchParts.getText();
                partSearch = Inventory.lookupPart(searchName);
                searchParts.clear();

                if (partSearch == null){
                    Alert noPart = new Alert(Alert.AlertType.INFORMATION);
                    noPart.setTitle("No Part Found");
                    noPart.setHeaderText("Part Name Not Found");
                    noPart.setContentText("A part with the name " + searchName + " could not be found in the list.\n" +
                            "Please make sure that the name you have entered is a valid name from the parts list.");
                    noPart.showAndWait();
                    tableView_Parts.setItems(Inventory.getAllParts());
                } else {
                    tableView_Parts.setItems(partSearch);
                }
            }
        }
    }

    /**
     * Opens the Modify Part Form when the modify button for the parts table is clicked.
     */
    @FXML
    void handleModPart() {
        if(isValidSelection(1)){
            Part modPart = tableView_Parts.getSelectionModel().getSelectedItem();
            index = getAllParts().indexOf(modPart);
            if (modPart != null){
                InventoryManagementSystem.openModParts(modPart);
            }
        }
    }

    /**
     * Removes selected part from the parts list and parts table.
     * Confirmation message is displayed before and after the part is deleted.
     * A warning message appears if a part was not deleted.
     */
    @FXML
    void handleDeletePart() {
        if(isValidSelection(2)){
            Part selectedPart = tableView_Parts.getSelectionModel().getSelectedItem();
            if (selectedPart != null) {
                Alert deleteWarn = new Alert(Alert.AlertType.CONFIRMATION);
                deleteWarn.setTitle("Delete Part");
                deleteWarn.setHeaderText("You are about to delete " + selectedPart.getName() + ".\n" +
                        "Click OK if you would like to proceed.");
                Optional<ButtonType> selected = deleteWarn.showAndWait();
                if (selected.get() == ButtonType.OK) {
                    boolean deleted = deletePart(selectedPart);
                    Alert deleteConfirm;
                    if(deleted){
                        deleteConfirm = new Alert(Alert.AlertType.INFORMATION);
                        deleteConfirm.setTitle("Part Deleted");
                        deleteConfirm.setHeaderText("The selected part was deleted from the parts list.");
                    } else {
                        deleteConfirm = new Alert(Alert.AlertType.WARNING);
                        deleteConfirm.setTitle("Part Not Deleted");
                        deleteConfirm.setHeaderText("The selected part was not deleted from the parts list.");
                    }
                    deleteConfirm.showAndWait();
                    tableView_Parts.setItems(getAllParts());
                }
            }
        }
    }

    /**
     * Opens the Add Product Form when the add button for the products table is clicked.
     */
    @FXML
    void handleAddPro() {
        InventoryManagementSystem.openAddPro();
    }

    /**
     * Searches products list for a products with a matching numerical ID value, or for a matching/partially matching name.
     * Results of the search are displayed in the products table, or a message displays if a matching product is not found.
     */
    @FXML
    void handleSearchPro() {
        try{
            productSearch.clear();
        } catch (NullPointerException e){
            System.out.println("couldn't clear the list since it doesn't exist");
        }

        if (searchProducts.getText().isEmpty()) {
            if (!tableView_Products.getItems().equals(Inventory.getAllProducts())){
                tableView_Products.setItems(Inventory.getAllProducts());
            }
        } else {
            try {
                Product idPro = Inventory.lookupProduct(Integer.parseInt(searchProducts.getText()));
                if (idPro != null) {
                    searchProducts.clear();
                    productSearch.add(idPro);
                    tableView_Products.setItems(productSearch);
                } else {
                    Alert noId = new Alert(Alert.AlertType.INFORMATION);
                    noId.setTitle("No Product Found");
                    noId.setHeaderText("Product ID Not Found");
                    noId.setContentText("A product with the specified ID could not be found in the list.\n" +
                            "Please make sure that the ID you have entered is a valid ID from the product list.");
                    noId.showAndWait();
                    tableView_Products.setItems(Inventory.getAllProducts());
                }
            } catch (NumberFormatException e) {
                String searchName = searchProducts.getText();
                productSearch = Inventory.lookupProduct(searchName);
                searchProducts.clear();

                if (productSearch == null){
                    Alert noPro = new Alert(Alert.AlertType.INFORMATION);
                    noPro.setTitle("No Product Found");
                    noPro.setHeaderText("No Product Found");
                    noPro.setContentText("A product with matching the search term " + searchName + " was not found in the list.\n" +
                            "Please make sure that the name you have entered is a valid name from the product list.");
                    noPro.showAndWait();
                    tableView_Products.setItems(Inventory.getAllProducts());
                } else {
                    tableView_Products.setItems(productSearch);
                }
            }
        }
    }

    /**
     * Opens the Modify Products Form when the modify button for the products table is clicked.
     */
    @FXML
    void handleModPro() {
        if(isValidSelection(3)) {
            Product modPro = tableView_Products.getSelectionModel().getSelectedItem();
            index = getAllProducts().indexOf(modPro);
            if (modPro != null) {
                InventoryManagementSystem.openModPro(modPro);
            }
        }
    }

    /**
     * Removes selected product from the products list and products table.
     * Confirmation message is displayed before and after the product is deleted.
     * A warning message appears if a product was not deleted.
     */
    @FXML
    void handleDeleteProduct() {
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
                        boolean deleted = deleteProduct(selectedPro);
                        Alert deleteConfirm;
                        if(deleted){
                            deleteConfirm = new Alert(Alert.AlertType.INFORMATION);
                            deleteConfirm.setTitle("Product Deleted");
                            deleteConfirm.setHeaderText("The selected product was deleted from the products list.");
                        } else {
                            deleteConfirm = new Alert(Alert.AlertType.WARNING);
                            deleteConfirm.setTitle("Product Not Deleted");
                            deleteConfirm.setHeaderText("The selected product was not deleted from the products list.");
                        }
                        deleteConfirm.showAndWait();
                        tableView_Products.setItems(getAllProducts());
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

    /**
     * Closes the application when the Exit button is clicked.
     * A prompt is displayed asking for confirmation that the user would like to close the app.
     */
    @FXML
    void handleExit() {
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

    /**
     * Gets the index of a selected part or product to be updated/modified.
     * @return Index of the part or product
     */
    public static int getIndex() {
        return index;
    }
}
