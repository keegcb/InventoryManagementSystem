package inventory.gui;

import inventory.InHouse;
import inventory.Inventory;
import inventory.Outsourced;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddPartFormController {

    private int addPartId;
    private String addPartName;
    private double addPartPrice;
    private int addPartInv;
    private int addPartMin;
    private int addPartMax;
    private int addMachineID;
    private String addCompany;
    private Stage partStage;

    @FXML
    private RadioButton radio_AddInHouse;
    @FXML
    private RadioButton radio_AddOutsourced;
    @FXML
    private ToggleGroup toggleGroup_AddPart;
    @FXML
    private TextField textField_AddPartId;
    @FXML
    private TextField textField_AddPartName;
    @FXML
    private TextField textField_AddPartInv;
    @FXML
    private TextField textField_AddPartPrice;
    @FXML
    private TextField textField_AddPartMax;
    @FXML
    private TextField textField_AddPartMin;
    @FXML
    private Label label_AddPartMachineCompany;
    @FXML
    private TextField textField_AddPartMachineCompany;

    @FXML
    private void initialize(){
        toggleGroup_AddPart = new ToggleGroup();
        this.radio_AddInHouse.setToggleGroup(toggleGroup_AddPart);
        this.radio_AddOutsourced.setToggleGroup(toggleGroup_AddPart);
        radio_AddInHouse.setSelected(true);
        addPartToggle();
        addPartId = Inventory.nextPartID();
        textField_AddPartId.setText(Integer.toString(addPartId));
    }

    public void createAddPartStage(Stage partStage){
        this.partStage = partStage;
    }

    public void addPartToggle(){
        if (this.toggleGroup_AddPart.getSelectedToggle().equals(this.radio_AddInHouse)){
            this.label_AddPartMachineCompany.setText("Machine ID");
        }
        if (this.toggleGroup_AddPart.getSelectedToggle().equals(this.radio_AddOutsourced)){
            this. label_AddPartMachineCompany.setText("Company Name");
        }
    }

    private boolean validPart(){
        boolean valid = true;
        boolean stockSet = true;
        String errorList = "";

        if (textField_AddPartName.getText().isEmpty()){
            errorList += "* Name field is not populated\n";
            valid = false;
        }
        if (textField_AddPartPrice.getText().isEmpty()){
            errorList += "* Price field is not populated\n";
            valid = false;
        } else {
            try{
                Double nPrice = Double.parseDouble(textField_AddPartPrice.getText());
            } catch (NumberFormatException e){
                errorList += "* Price field needs to be in a number format\n";
                valid = false;
            }
        }
        if (textField_AddPartInv.getText().isEmpty()){
            errorList += "* Inventory field is not populated\n";
            valid = false;
            stockSet = false;
        } else {
            try{
                int nInv = Integer.parseInt(textField_AddPartInv.getText());
            } catch (NumberFormatException e){
                errorList += "* Inventory field needs to be in a whole number format\n";
                valid = false;
                stockSet = false;
            }
        }
        if (textField_AddPartMax.getText().isEmpty()){
            errorList += "* Maximum stock field is not populated\n";
            valid = false;
            stockSet = false;
        } else {
            try{
                int nMax = Integer.parseInt(textField_AddPartMax.getText());
            } catch (NumberFormatException e){
                errorList += "* Maximum stock field needs to be in a whole number format\n";
                valid = false;
                stockSet = false;
            }
        }
        if (textField_AddPartMin.getText().isEmpty()){
            errorList += "* Minimum stock field is not populated\n";
            valid = false;
            stockSet = false;
        } else {
            try{
                int nMin = Integer.parseInt(textField_AddPartMin.getText());
            } catch (NumberFormatException e){
                errorList += "* Minimum stock field needs to be in a whole number format\n";
                valid = false;
                stockSet = false;
            }
        }
        if (stockSet){
            try{
                int tempInv = Integer.parseInt(textField_AddPartInv.getText());
                int tempMax = Integer.parseInt(textField_AddPartMax.getText());
                int tempMin = Integer.parseInt(textField_AddPartMin.getText());
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
        if (textField_AddPartMachineCompany.getText().isEmpty()){
            if (this.toggleGroup_AddPart.getSelectedToggle().equals(this.radio_AddInHouse)){
                errorList += "* Machine ID field is not populated\n";
            } else if (this.toggleGroup_AddPart.getSelectedToggle().equals(this.radio_AddOutsourced)){
                errorList += "* Company Name field is not populated\n";
            }
            valid = false;
        } else {
            if (this.toggleGroup_AddPart.getSelectedToggle().equals(this.radio_AddInHouse)){
                try {
                    int inH = Integer.parseInt(textField_AddPartMachineCompany.getText());
                } catch (NumberFormatException e){
                    errorList += "* Machine ID value needs to be in a whole number format\n";
                    valid = false;
                }
            }
        }
        if (!valid){
            Alert fieldError = new Alert(Alert.AlertType.ERROR);
            fieldError.setTitle("Error");
            fieldError.setHeaderText("Errors occurred when attempting to save the part.\nPlease address the following issues and try again.");
            fieldError.setContentText(errorList);
            fieldError.showAndWait();
        }
        return valid;
    }

    @FXML
    private void handleSave(){
        if (validPart()){
            addPartName = textField_AddPartName.getText();
            addPartPrice = Double.parseDouble(textField_AddPartPrice.getText());
            addPartInv = Integer.parseInt(textField_AddPartInv.getText());
            addPartMin = Integer.parseInt(textField_AddPartMin.getText());
            addPartMax = Integer.parseInt(textField_AddPartMax.getText());
            if (this.toggleGroup_AddPart.getSelectedToggle().equals(radio_AddInHouse)){
                addMachineID = Integer.parseInt(textField_AddPartMachineCompany.getText());
                InHouse inHouse = new InHouse
                        (addPartId, addPartName, addPartPrice, addPartInv, addPartMin, addPartMax, addMachineID);
                Inventory.addPart(inHouse);
            }
            if (this.toggleGroup_AddPart.getSelectedToggle().equals(radio_AddOutsourced)){
                addCompany = textField_AddPartMachineCompany.getText();
                Outsourced outsourced = new Outsourced
                        (addPartId, addPartName, addPartPrice, addPartInv, addPartMax, addPartMin, addCompany);
                Inventory.addPart(outsourced);
            }
            partStage.close();
        }
    }

    @FXML
    public void handleCancel(){
        partStage.close();
    }
}
