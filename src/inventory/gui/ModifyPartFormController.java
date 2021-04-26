package inventory.gui;

import inventory.InHouse;
import inventory.Inventory;
import inventory.Outsourced;
import inventory.Part;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ModifyPartFormController {
    private int modId;
    private String modName;
    private double modPrice;
    private int modInv;
    private int modMin;
    private int modMax;
    private int modMachineId;
    private String modCompany;
    private Stage modStage;
    private int modIndex;

    @FXML
    private RadioButton radio_ModInHouse;
    @FXML
    private RadioButton radio_ModOutsourced;
    @FXML
    private ToggleGroup toggleGroup_ModPart;
    @FXML
    private TextField textField_ModId;
    @FXML
    private TextField textField_ModName;
    @FXML
    private TextField textField_ModInv;
    @FXML
    private TextField textField_ModPrice;
    @FXML
    private TextField textField_ModMax;
    @FXML
    private TextField textField_ModMin;
    @FXML
    private Label label_ModMachineCompany;
    @FXML
    private TextField textField_ModMachineCompany;
    @FXML
    private Button button_SaveMod;
    @FXML
    private Button button_Cancel;

    public void createModPartStage (Stage modStage){
        this.modStage = modStage;
    }

    @FXML
    private void initialize() {
        modIndex = MainFormController.getIndex();
        toggleGroup_ModPart = new ToggleGroup();
        this.radio_ModInHouse.setToggleGroup(toggleGroup_ModPart);
        this.radio_ModOutsourced.setToggleGroup(toggleGroup_ModPart);
    }

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

    public void modPartToggle(){
        if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModInHouse)){
            this.label_ModMachineCompany.setText("Machine ID");
        }
        if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModOutsourced)){
            this. label_ModMachineCompany.setText("Company Name");
        }
    }

    @FXML
    private void handleModSave(){
        modId = Integer.parseInt(textField_ModId.getText());
        modName = textField_ModName.getText();
        modPrice = Double.parseDouble(textField_ModPrice.getText());
        modInv = Integer.parseInt(textField_ModInv.getText());
        modMin = Integer.parseInt(textField_ModMin.getText());
        modMax = Integer.parseInt(textField_ModMax.getText());
        if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModInHouse)){
            modMachineId = Integer.parseInt(textField_ModMachineCompany.getText());
            InHouse inHouse = new InHouse
                    (modId, modName, modPrice, modInv, modMin, modMax, modMachineId);
            Inventory.updatePart(modIndex, inHouse);
        }
        if (this.toggleGroup_ModPart.getSelectedToggle().equals(this.radio_ModOutsourced)){
            modCompany = textField_ModMachineCompany.getText();
            Outsourced outsourced = new Outsourced
                    (modId, modName, modPrice, modInv, modMax, modMin, modCompany);
            Inventory.updatePart(modIndex, outsourced);
        }
        modStage.close();
    }

    @FXML
    private void handleCancelMod(){
        modStage.close();
    }
}
