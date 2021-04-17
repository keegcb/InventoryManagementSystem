package inventory.gui;

import inventory.InHouse;
import inventory.Inventory;
import inventory.Outsourced;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class AddPartFormController {

    private int addPartID;
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
    private TextField textField_AddPartID;
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
        addPartID = Inventory.nextPartID();
        textField_AddPartID.setText(Integer.toString(addPartID));
    }

    public void createStage (Stage partStage){
        this.partStage = partStage;
    }

    public void addPartToggle(){
        if (toggleGroup_AddPart.equals(radio_AddInHouse)){
            this.label_AddPartMachineCompany.setText("Machine ID");
        }
        if (toggleGroup_AddPart.equals(radio_AddOutsourced)){
            this. label_AddPartMachineCompany.setText("Company Name");
        }
    }

    @FXML
    private void handleSave(){
        addPartName = textField_AddPartName.getText();
        addPartPrice = Double.parseDouble(textField_AddPartPrice.getText());
        addPartInv = Integer.parseInt(textField_AddPartInv.getText());
        addPartMin = Integer.parseInt(textField_AddPartMin.getText());
        addPartMax = Integer.parseInt(textField_AddPartMax.getText());
        if (this.toggleGroup_AddPart.getSelectedToggle().equals(radio_AddInHouse)){
            addMachineID = Integer.parseInt(textField_AddPartMachineCompany.getText());
            InHouse inHouse = new InHouse
                    (addPartID, addPartName, addPartPrice, addPartInv, addPartMin, addPartMax, addMachineID);
            Inventory.addPart(inHouse);
        }
        if (this.toggleGroup_AddPart.getSelectedToggle().equals(radio_AddOutsourced)){
            addCompany = textField_AddPartMachineCompany.getText();
            Outsourced outsourced = new Outsourced
                    (addPartID, addPartName, addPartPrice, addPartInv, addPartMax, addPartMin, addCompany);
            Inventory.addPart(outsourced);
        }
        partStage.close();
    }

}
