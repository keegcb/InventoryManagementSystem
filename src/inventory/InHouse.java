package inventory;

/**
 * class InHouse.java
 * Simulates a part object of type InHouse.
 */
public class InHouse extends Part{

    /**
     * Machine ID for part.
     */
    private int machineId;

    /**
     * Constructor method for Part object specific to InHouse parts.
     * @param id ID of the InHouse Part
     * @param name Name of the InHouse Part
     * @param price Price/Cost per unit of InHouse Part
     * @param stock Stock/Inventory of InHouse Part
     * @param min Minimum stock value of InHouse Part
     * @param max Maximum stock value of InHouse Part
     * @param machineId Machine ID of InHouse Part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Sets the Machine ID of InHouse Part.
     * @param machineId Machine ID value to be set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * Gets the Machine ID of InHouse Part.
     * @return Integer value of Machine ID for InHouse Part
     */
    public int getMachineId() {
        return machineId;
    }
}
