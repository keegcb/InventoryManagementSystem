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
import java.util.ArrayList;
import java.util.Optional;

/**
 * class ModifyProductFormController.java
 * Acts as controller and validation for the Modify Product Form UI when editing a product from the inventory.
 */
public class ModifyProductFormController {

    /**
     * Selected Product.
     */
    private Product selectedProduct;
    /**
     * Index of device being modified.
     */
    private int modIndex;
    /**
     * Stage to display Modify Product Form.
     */
    private Stage modStage;
    /**
     * List of parts searched.
     */
    private ObservableList<Part> searchPart = FXCollections.observableArrayList();
    /**
     * List of parts associated with product being modified.
     */
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();
    /**
     * Indicates if valid part is selected.
     */
    private static boolean validPart = false;

    /**
     * Search field for parts table.
     */
    @FXML
    private TextField searchField_Parts;
    /**
     * Parts Table.
     */
    @FXML
    private TableView<Part> tableView_PartData;
    /**
     * Part ID column.
     */
    @FXML
    private TableColumn<Part, Integer> col_PartId;
    /**
     * Part Name column.
     */
    @FXML
    private TableColumn<Part, String> col_PartName;
    /**
     * Part Inventory column.
     */
    @FXML
    private TableColumn<Part, Integer> col_PartInv;
    /**
     * Part Price column.
     */
    @FXML
    private TableColumn<Part, Double> col_PartPrice;
    /**
     * Associated Parts table.
     */
    @FXML
    private TableView<Part> tableView_AssociatedPart;
    /**
     * Associated Part ID column.
     */
    @FXML
    private TableColumn<Part, Integer> col_AssPartId;
    /**
     * Associated Part Name column.
     */
    @FXML
    private TableColumn<Part, String> col_AssPartName;
    /**
     * Associated Part Inventory column.
     */
    @FXML
    private TableColumn<Part, Integer> col_AssPartInv;
    /**
     * Associated Part Price column.
     */
    @FXML
    private TableColumn<Part, Double> col_AssPartPrice;
    /**
     * Field for Product ID.
     */
    @FXML
    private TextField text_ProductId;
    /**
     * Field for Product Name.
     */
    @FXML
    private TextField text_ProductName;
    /**
     * Field for Product Inventory.
     */
    @FXML
    private TextField text_ProductInv;
    /**
     * Field for Product Price.
     */
    @FXML
    private TextField text_ProductPrice;
    /**
     * Field for Product Maximum stock.
     */
    @FXML
    private TextField text_ProductMax;
    /**
     * Field for Product Minimum stock.
     */
    @FXML
    private TextField text_ProductMin;

    /**
     * Creates stage to host Add Product UI.
     * @param modStage Stage to be created
     */
    public void createModProStage (Stage modStage){
        this.modStage = modStage;
    }

    /**
     * Initializes fxml form by identifying column cell properties for parts table and populating, and getting index of product being modified.
     */
    @FXML
    private void initialize(){
        modIndex = MainFormController.getIndex();
        col_PartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_PartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView_PartData.setItems(Inventory.getAllParts());

    }

    /**
     * Sets the property values of product to be modified and populated the associated parts table.
     * @param selectedPro Product to be modified
     */
    public void setModPro(Product selectedPro){
        col_AssPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_AssPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_AssPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_AssPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPart = selectedPro.getAllAssociatedParts();
        tableView_AssociatedPart.setItems(selectedPro.getAllAssociatedParts());

        text_ProductId.setText(Integer.toString(selectedPro.getId()));
        text_ProductName.setText(selectedPro.getName());
        text_ProductInv.setText(Integer.toString(selectedPro.getStock()));
        text_ProductPrice.setText(Double.toString(selectedPro.getPrice()));
        text_ProductMax.setText(Integer.toString(selectedPro.getMax()));
        text_ProductMin.setText(Integer.toString(selectedPro.getMin()));
    }

    /**
     * Searches parts list for a part with a matching numerical ID value, or for a matching/partially matching name.
     * Results of the search are displayed in the parts table, or a message displays if a matching part is not found.
     */
    @FXML
    private void handleModProSearchPart(){
        try{
            searchPart.clear();
        } catch (NullPointerException e){
            System.out.println("couldn't clear the list since it doesn't exist");
        }

        if (searchField_Parts.getText().isEmpty()) {
            if (!tableView_PartData.getItems().equals(Inventory.getAllParts())){
                tableView_PartData.setItems(Inventory.getAllParts());
            }
        } else {
            try {
                Part idPart = Inventory.lookupPart(Integer.parseInt(searchField_Parts.getText()));
                if (idPart != null) {
                    searchField_Parts.clear();
                    searchPart.add(idPart);
                    tableView_PartData.setItems(searchPart);
                } else {
                    tableView_PartData.setItems(Inventory.getAllParts());
                }
            } catch (NumberFormatException e) {
                String searchName = searchField_Parts.getText();
                searchPart = Inventory.lookupPart(searchName);
                searchField_Parts.clear();

                if (searchPart == null){
                    Alert noPart = new Alert(Alert.AlertType.INFORMATION);
                    noPart.setTitle("No Part Found");
                    noPart.setHeaderText("No Part Found");
                    noPart.setContentText("A part with the name " + searchName + " could not be found in the list.\n" +
                            "Please make sure that the name you have entered is a valid name from the parts list.");
                    noPart.showAndWait();
                    tableView_PartData.setItems(Inventory.getAllParts());
                } else {
                    tableView_PartData.setItems(searchPart);
                }
            }
        }
    }

    /**
     * Verifies if a valid part is selected from either part table before committing add or remove action.
     * Warning message displays if there are no valid parts selected from the tables.
     * @param option Integer value for switch statement to determine the calling method
     * @return True if there is a valid selected part, false if there is no valid selection
     */
    private boolean isValidSelection(int option){
        switch (option) {
            case 1 -> {
                Part addAttempt = tableView_PartData.getSelectionModel().getSelectedItem();
                if (addAttempt != null) {
                    validPart = true;
                } else {
                    Alert notValid = new Alert(Alert.AlertType.WARNING);
                    notValid.setTitle("Warning");
                    notValid.setHeaderText("A valid part has not been selected.");
                    notValid.setContentText("Please select a valid part from the list to be added.");
                    notValid.showAndWait();
                    validPart = false;
                }
            }
            case 2 -> {
                Part removeAttempt = tableView_AssociatedPart.getSelectionModel().getSelectedItem();
                if (removeAttempt != null) {
                    validPart = true;
                } else {
                    Alert notValid = new Alert(Alert.AlertType.WARNING);
                    notValid.setTitle("Warning");
                    notValid.setHeaderText("A valid part has not been selected.");
                    notValid.setContentText("Please select a valid part from the list to be removed.");
                    notValid.showAndWait();
                    validPart = false;
                }
            }
        }
        return validPart;
    }

    /**
     * Adds selected part from the parts list to the associated parts list of the product.
     */
    @FXML
    private void handleModProAddPart(){
        if (isValidSelection(1)){
            Part selectedPart = tableView_PartData.getSelectionModel().getSelectedItem();
            associatedPart.add(selectedPart);
            tableView_AssociatedPart.setItems(associatedPart);
        }
    }

    /**
     * Removes selected part from the associated parts list of the product.
     */
    @FXML
    private void handleDeleteModAssPart() {
        if (isValidSelection(2)){
            Part selectedPart = tableView_AssociatedPart.getSelectionModel().getSelectedItem();
            if (selectedPart != null){
                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.setTitle("Delete Associated Part?");
                deleteAlert.setHeaderText("You are about to remove the part " + selectedPart.getName() + (" from the list of associated parts.\n" +
                        "If you would like to proceed, click OK."));
                Optional<ButtonType> selected = deleteAlert.showAndWait();
                if (selected.get() == ButtonType.OK){
                    associatedPart.remove(selectedPart);
                    tableView_AssociatedPart.setItems(associatedPart);
                } else {
                    deleteAlert.close();
                }
            }
        }
    }

    /**
     * Makes multiple validation checks and displays any issues in alert message before product creation.
     * Verifies the following:
     * None of the required fields are empty
     * Price field is in a number format parsable to Double
     * Inventory field is in a number format parsable to Integer
     * Max field is in a number format parsable to Integer
     * Min field is in a number format parsable to Integer
     * Inventory value is not greater than maximum
     * Inventory value is not less than minimum
     * Minimum value is less than maximum
     * @return True if no issues are found, false if issues are found
     */
    private boolean validModPro(){
        boolean valid = true;
        boolean stockSet = true;
        String errorList = "";

        if (text_ProductName.getText().isEmpty()){
            errorList += "* Name field is not populated\n";
            valid = false;
        }
        if (text_ProductPrice.getText().isEmpty()){
            errorList += "* Price field is not populated\n";
            valid = false;
        } else {
            try{
                Double nPrice = Double.parseDouble(text_ProductPrice.getText());
            } catch (NumberFormatException e){
                errorList += "* Price field needs to be in a number format\n";
                valid = false;
            }
        }
        if (text_ProductInv.getText().isEmpty()){
            errorList += "* Inventory field is not populated\n";
            valid = false;
            stockSet = false;
        } else {
            try{
                int nInv = Integer.parseInt(text_ProductInv.getText());
            } catch (NumberFormatException e){
                errorList += "* Inventory field needs to be in a whole number format\n";
                valid = false;
                stockSet = false;
            }
        }
        if (text_ProductMax.getText().isEmpty()){
            errorList += "* Maximum stock field is not populated\n";
            valid = false;
            stockSet = false;
        } else {
            try{
                int nMax = Integer.parseInt(text_ProductMax.getText());
            } catch (NumberFormatException e){
                errorList += "* Maximum stock field needs to be in a whole number format\n";
                valid = false;
                stockSet = false;
            }
        }
        if (text_ProductMin.getText().isEmpty()){
            errorList += "* Minimum stock field is not populated\n";
            valid = false;
            stockSet = false;
        } else {
            try{
                int nMin = Integer.parseInt(text_ProductMin.getText());
            } catch (NumberFormatException e){
                errorList += "* Minimum stock field needs to be in a whole number format\n";
                valid = false;
                stockSet = false;
            }
        }
        if (stockSet){
            try{
                int tempInv = Integer.parseInt(text_ProductInv.getText());
                int tempMax = Integer.parseInt(text_ProductMax.getText());
                int tempMin = Integer.parseInt(text_ProductMin.getText());
                if (tempMin > tempMax){
                    errorList += "* Minimum stock value cannot be greater than the maximum\n";
                    valid = false;
                }
                if (tempInv > tempMax || tempInv < tempMin ){
                    errorList += "* Inventory value must be within the maximum and minimum range set\n";
                    valid = false;
                }
            } catch (NumberFormatException e){
                System.out.println("How did this happen?");
                e.printStackTrace();
            }
        }
        if (!valid){
            Alert fieldError = new Alert(Alert.AlertType.ERROR);
            fieldError.setTitle("Error");
            fieldError.setHeaderText("Errors occurred when attempting to save the product.\nPlease address the following issues and try again.");
            fieldError.setContentText(errorList);
            fieldError.showAndWait();
        }
        return valid;
    }

    /**
     * Creates a new product object to replace the product being modified with the supplied values when save button is clicked.
     * Modified Product is then added to the inventory product list over the original product and the modify product screen is closed.
     */
    @FXML
    private void handleSaveModPro() {
        if (validModPro()){
            int proId = Integer.parseInt(text_ProductId.getText());
            String proName = text_ProductName.getText();
            Double proPrice = Double.parseDouble(text_ProductPrice.getText());
            int proInv = Integer.parseInt(text_ProductInv.getText());
            int proMin = Integer.parseInt(text_ProductMin.getText());
            int proMax = Integer.parseInt(text_ProductMax.getText());

            Product addPro = new Product();
            addPro.setId(proId);
            addPro.setName(proName);
            addPro.setPrice(proPrice);
            addPro.setStock(proInv);
            addPro.setMin(proMin);
            addPro.setMax(proMax);

            ArrayList<Part> assParts = new ArrayList<>(tableView_AssociatedPart.getItems());
            for (Part assPart : assParts) {
                addPro.addAssociatedPart(assPart);
            }

            Inventory.updateProduct(modIndex, addPro);
            modStage.close();
        }
    }

    /**
     * Closes the Modify Product Form when the cancel button is clicked.
     */
    @FXML
    private void handleCancel(){
        modStage.close();
    }
}
