package inventory.gui;

import inventory.Inventory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddPartFormController {

    private int addPartID;
    private String addPartName;
    private int addPartInv;
    private double addPartPrice;
    private int addPartMax;
    private int addPartMin;
    private int addMachineID;
    private String addCompany;

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

    public void addPartToggle(){
        if (toggleGroup_AddPart.equals(radio_AddInHouse)){
            this.label_AddPartMachineCompany.setText("Machine ID");
        }
        if (toggleGroup_AddPart.equals(radio_AddOutsourced)){
            this. label_AddPartMachineCompany.setText("Company Name");
        }
    }


}
