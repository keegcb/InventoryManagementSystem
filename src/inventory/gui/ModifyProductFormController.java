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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ModifyProductFormController {

    private int modIndex;
    private Stage modStage;
    private ObservableList<Part> searchPart = FXCollections.observableArrayList();
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();
    private static boolean validPart = false;

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
        modIndex = MainFormController.getIndex();
        col_PartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_PartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView_PartData.setItems(Inventory.getAllParts());

    }

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

    @FXML
    private void handleModProAddPart(){
        if (isValidSelection(1)){
            Part selectedPart = tableView_PartData.getSelectionModel().getSelectedItem();
            associatedPart.add(selectedPart);
            tableView_AssociatedPart.setItems(associatedPart);
        }
    }

    @FXML
    private void handleDeleteModAssPart() throws IOException{
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

    @FXML
    private void handleSaveModPro() {
        if (validModPro()){
            int proId = Integer.parseInt(text_ProductId.getText());
            String proName = text_ProductName.getText();
            Double proPrice = Double.parseDouble(text_ProductPrice.getText());
            int proInv = Integer.parseInt(text_ProductInv.getText());
            int proMin = Integer.parseInt(text_ProductMin.getText());
            int proMax = Integer.parseInt(text_ProductMax.getText());

            Product addPro = new Product(proId, proName, proPrice, proInv, proMin, proMax);
            ArrayList<Part> assParts = new ArrayList<>(tableView_AssociatedPart.getItems());
            for (Part assPart : assParts) {
                addPro.addAssociatedPart(assPart);
            }

            Inventory.updateProduct(modIndex, addPro);
            modStage.close();
        }
    }

    @FXML
    private void handleCancel(){
        modStage.close();
    }
}
