package inventory.gui;

import inventory.InHouse;
import inventory.Inventory;
import inventory.Outsourced;
import inventory.Part;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * class ModifyPartFormController.java
 * Acts as controller and validation for the Modify Part Form UI when editing a part from the inventory.
 */
public class ModifyPartFormController {
    /**
     * Stage to display Modify Part Form.
     */
    private Stage modStage;
    /**
     * Index of part being modified.
     */
    private int modIndex;

    /**
     * Indicates part is object type InHouse class.
     */
    @FXML
    private RadioButton radio_ModInHouse;
    /**
     * Indicates part is object type Outsourced class.
     */
    @FXML
    private RadioButton radio_ModOutsourced;
    /**
     * Toggle Group for the InHouse and Outsourced radio buttons.
     */
    @FXML
    private ToggleGroup toggleGroup_ModPart;
    /**
     * Field to input Part ID.
     */
    @FXML
    private TextField textField_ModId;
    /**
     * Field to input Part Name.
     */
    @FXML
    private TextField textField_ModName;
    /**
     * Field to input Part Inventory.
     */
    @FXML
    private TextField textField_ModInv;
    /**
     * Field to input Part Price.
     */
    @FXML
    private TextField textField_ModPrice;
    /**
     * Field to input Part Maximum stock.
     */
    @FXML
    private TextField textField_ModMax;
    /**
     * Field to input Part Minimum stock.
     */
    @FXML
    private TextField textField_ModMin;
    /**
     * Label showing Machine ID or Company Name.
     */
    @FXML
    private Label label_ModMachineCompany;
    /**
     * Field to input Machine ID or Company Name.
     */
    @FXML
    private TextField textField_ModMachineCompany;

    /**
     * Creates stage to host Add Product UI.
     * @param modStage Stage to be created
     */
    public void createModPartStage (Stage modStage){
        this.modStage = modStage;
    }

    /**
     * Initializes fxml form by setting radio buttons to radio group and getting index of the part being modified.
     */
    @FXML
    private void initialize() {
        modIndex = MainFormController.getIndex();
        toggleGroup_ModPart = new ToggleGroup();
        this.radio_ModInHouse.setToggleGroup(toggleGroup_ModPart);
        this.radio_ModOutsourced.setToggleGroup(toggleGroup_ModPart);
    }

    /**
     * Sets property values to those of the part being modified and identifies selected radio button.
     * @param selectedPart Part to be modified
     */
    public void setModPart(Part selectedPart){
        if (selectedPart instanceof InHouse){
            this.radio_ModInHouse.setSelected(true);
            textField_ModMachineCompany.setText(Integer.toString(((InHouse) selectedPart).getMachineId()));
        }
        if (selectedPart instanceof Outsourced){
            this.radio_ModOutsourced.setSelected(true);
            textField_ModMachineCompany.setText(((Outsourced) selectedPart).getCompanyName());
        }
        modPartToggle();

        textField_ModId.setText(Integer.toString(selectedPart.getId()));
        textField_ModName.setText(selectedPart.getName());
        textField_ModPrice.setText(Double.toString(selectedPart.getPrice()));
        textField_ModInv.setText(Integer.toString(selectedPart.getStock()));
        textField_ModMax.setText(Integer.toString(selectedPart.getMax()));
        textField_ModMin.setText(Integer.toString(selectedPart.getMin()));
    }

    /**
     * Changes the label for Machine ID and Company Name based on which radio button is selected.
     */
    public void modPartToggle(){
        if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModInHouse)){
            this.label_ModMachineCompany.setText("Machine ID");
        }
        if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModOutsourced)){
            this. label_ModMachineCompany.setText("Company Name");
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
    private boolean validModPart(){
        boolean valid = true;
        boolean stockSet = true;
        String errorList = "";

        if (textField_ModName.getText().isEmpty()){
            errorList += "* Name field is not populated\n";
            valid = false;
        }
        if (textField_ModPrice.getText().isEmpty()){
            errorList += "* Price field is not populated\n";
            valid = false;
        } else {
            try{
                Double price = Double.parseDouble(textField_ModPrice.getText());
            } catch (NumberFormatException e){
                errorList += "* Price field needs to be in a number format\n";
                valid = false;
            }
        }
        if (textField_ModInv.getText().isEmpty()){
            errorList += "* Inventory field is not populated\n";
            valid = false;
            stockSet = false;
        } else {
            try{
                int inv = Integer.parseInt(textField_ModInv.getText());
            } catch (NumberFormatException e){
                errorList += "* Inventory field needs to be in a whole number format\n";
                valid = false;
                stockSet = false;
            }
        }
        if (textField_ModMax.getText().isEmpty()){
            errorList += "* Maximum stock field is not populated\n";
            valid = false;
            stockSet = false;
        } else {
            try{
                int max = Integer.parseInt(textField_ModMax.getText());
            } catch (NumberFormatException e){
                errorList += "* Maximum stock field needs to be in a whole number format\n";
                valid = false;
                stockSet = false;
            }
        }
        if (textField_ModMin.getText().isEmpty()){
            errorList += "* Minimum stock field is not populated\n";
            valid = false;
            stockSet = false;
        } else {
            try{
                int min = Integer.parseInt(textField_ModMax.getText());
            } catch (NumberFormatException e){
                errorList += "* Minimum stock field needs to be in a whole number format\n";
                valid = false;
                stockSet = false;
            }
        }
        if (stockSet){
            try{
                int tempInv = Integer.parseInt(textField_ModInv.getText());
                int tempMax = Integer.parseInt(textField_ModMax.getText());
                int tempMin = Integer.parseInt(textField_ModMin.getText());
                if (tempMin >= tempMax){
                    errorList += "* Minimum stock value must be less than the maximum\n";
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
        if (textField_ModMachineCompany.getText().isEmpty()){
            if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModInHouse)){
                errorList += "* Machine ID field is not populated\n";
            } else if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModOutsourced)){
                errorList += "* Company Name field is not populated\n";
            }
            valid = false;
        } else {
            if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModInHouse)){
                try {
                    int inH = Integer.parseInt(textField_ModMachineCompany.getText());
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
     * Creates a new part object to replace the one being modified, with the supplied values when save button is clicked.
     * Part is then added to the inventory part list at the index of the original part and the modify part screen is closed.
     */
    @FXML
    private void handleModSave(){
        if (validModPart()){
            try {
                int modId = Integer.parseInt(textField_ModId.getText());
                String modName = textField_ModName.getText();
                double modPrice = Double.parseDouble(textField_ModPrice.getText());
                int modInv = Integer.parseInt(textField_ModInv.getText());
                int modMin = Integer.parseInt(textField_ModMin.getText());
                int modMax = Integer.parseInt(textField_ModMax.getText());
                if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModInHouse)){
                    int modMachineId = Integer.parseInt(textField_ModMachineCompany.getText());
                    InHouse inHouse = new InHouse
                            (modId, modName, modPrice, modInv, modMin, modMax, modMachineId);
                    Inventory.updatePart(modIndex, inHouse);
                }
                if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModOutsourced)){
                    String modCompany = textField_ModMachineCompany.getText();
                    Outsourced outsourced = new Outsourced
                            (modId, modName, modPrice, modInv, modMax, modMin, modCompany);
                    Inventory.updatePart(modIndex, outsourced);
                }
                modStage.close();
            } catch (NumberFormatException e){
                System.out.println("How did this happen?");
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the Add Part Form when the cancel button is clicked.
     */
    @FXML
    private void handleCancelMod(){
        modStage.close();
    }
}
