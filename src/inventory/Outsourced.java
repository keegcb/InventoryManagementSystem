package inventory;

/**
 * class Outsourced.java
 * Simulates a part object of type Outsourced.
 */
public class Outsourced extends Part{

    /**
     * Company Name.
     */
    private String companyName;

    /**
     * Constructor method for Part object specific to Outsourced parts.
     * @param id ID of the Outsourced Part
     * @param name Name of the Outsourced Part
     * @param price Price/Cost per unit of Outsourced Part
     * @param stock Stock/Inventory of Outsourced Part
     * @param min Minimum stock value of Outsourced Part
     * @param max Maximum stock value of Outsourced Part
     * @param companyName Company Name of Outsourced part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Sets the Company Name of specified Outsourced part.
     * @param companyName Company Name to be set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets the Company Name of specified Outsourced part.
     * @return Company Name as String value for Outsourced part
     */
    public String getCompanyName() {
        return companyName;
    }
}
