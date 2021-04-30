package inventory.gui;

import inventory.InHouse;
import inventory.Inventory;
import inventory.Outsourced;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * class AddPartFormController.java
 * Acts as controller and validation for the Add Part Form UI when creating a new part.
 */
public class AddPartFormController {

    /**
     * Part ID for the new part.
     */
    private int addPartId;
    /**
     * Stage for add part form.
     */
    private Stage partStage;

    /**
     * Indicates part is object type InHouse class.
     */
    @FXML
    private RadioButton radio_AddInHouse;
    /**
     * Indicates part is object type Outsourced class.
     */
    @FXML
    private RadioButton radio_AddOutsourced;
    /**
     * Toggle Group for the InHouse and Outsourced radio buttons.
     */
    @FXML
    private ToggleGroup toggleGroup_AddPart;
    /**
     * Field to input Part ID.
     */
    @FXML
    private TextField textField_AddPartId;
    /**
     * Field to input Part Name.
     */
    @FXML
    private TextField textField_AddPartName;
    /**
     * Field to input Part Inventory.
     */
    @FXML
    private TextField textField_AddPartInv;
    /**
     * Field to input Part Price.
     */
    @FXML
    private TextField textField_AddPartPrice;
    /**
     * Field to input Part Maximum stock.
     */
    @FXML
    private TextField textField_AddPartMax;
    /**
     * Field to input Part Minimum stock.
     */
    @FXML
    private TextField textField_AddPartMin;
    /**
     * Label showing Machine ID or Company Name.
     */
    @FXML
    private Label label_AddPartMachineCompany;
    /**
     * Field to input Machine ID or Company Name.
     */
    @FXML
    private TextField textField_AddPartMachineCompany;

    /**
     * Initializes fxml form by setting radio buttons to radio group and generating new Part ID.
     */
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

    /**
     * Creates Stage to host Add Part UI.
     * @param partStage Stage to be created
     */
    public void createAddPartStage(Stage partStage){
        this.partStage = partStage;
    }

    /**
     * Changes the label for Machine ID and Company Name based on which radio button is selected.
     */
    public void addPartToggle(){
        if (this.toggleGroup_AddPart.getSelectedToggle().equals(this.radio_AddInHouse)){
            this.label_AddPartMachineCompany.setText("Machine ID");
        }
        if (this.toggleGroup_AddPart.getSelectedToggle().equals(this.radio_AddOutsourced)){
            this. label_AddPartMachineCompany.setText("Company Name");
        }
    }

    /**
     * Makes multiple validation checks and displays any issues in alert message before part creation.
     * Verifies the following:
     * None of the required fields are empty
     * Price field is in a number format parsable to Double
     * Inventory field is in a number format parsable to Integer
     * Max field is in a number format parsable to Integer
     * Min field is in a number format parsable to Integer
     * Inventory value is not greater than maximum
     * Inventory value is not less than minimum
     * Minimum value is less than maximum
     * Machine ID field is in a number format parsable to Integer
     * @return True if no issues are found, false if issues are found
     */
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

    /**
     * Creates a new part object with the supplied values when save button is clicked.
     * Part is then added to the inventory part list and the add part screen is closed.
     */
    @FXML
    private void handleSave(){
        if (validPart()){
            String addPartName = textField_AddPartName.getText();
            double addPartPrice = Double.parseDouble(textField_AddPartPrice.getText());
            int addPartInv = Integer.parseInt(textField_AddPartInv.getText());
            int addPartMin = Integer.parseInt(textField_AddPartMin.getText());
            int addPartMax = Integer.parseInt(textField_AddPartMax.getText());
            if (this.toggleGroup_AddPart.getSelectedToggle().equals(radio_AddInHouse)){
                int addMachineID = Integer.parseInt(textField_AddPartMachineCompany.getText());
                InHouse inHouse = new InHouse
                        (addPartId, addPartName, addPartPrice, addPartInv, addPartMin, addPartMax, addMachineID);
                Inventory.addPart(inHouse);
            }
            if (this.toggleGroup_AddPart.getSelectedToggle().equals(radio_AddOutsourced)){
                String addCompany = textField_AddPartMachineCompany.getText();
                Outsourced outsourced = new Outsourced
                        (addPartId, addPartName, addPartPrice, addPartInv, addPartMax, addPartMin, addCompany);
                Inventory.addPart(outsourced);
            }
            partStage.close();
        }
    }

    /**
     * Closes the Add Part Form when the cancel button is clicked.
     */
    @FXML
    public void handleCancel(){
        partStage.close();
    }
}
