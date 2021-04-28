package inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Supplied class Product.java
 */

public class Product {

    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
    @param id the product id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name the product name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price the product price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @param stock the product stock value to be set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     *
     * @param min the product minimum to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     *
     * @param max the product max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *
     * @return the product id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @return the product stock level
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @return the product minimum
     */
    public int getMin() {
        return min;
    }

    /**
     *
     * @return the product maximum
     */
    public int getMax() {
        return max;
    }

    /**
     * Adds a part to the list of parts for the product
     * @param part to be added to the product
     */
    public void addAssociatedPart(Part part){

        this.associatedParts.add(part);

    }

    /**
     * Deletes a part from the part list of a product
     * @param selectedAssociatedPart the part identified in the product part list for deletion
     * @return boolean if the part was successfully deleted
     */
    public boolean deleteAssociatePart(Part selectedAssociatedPart){
        boolean isDeleted = false;

        for (int i=0; i<associatedParts.size(); i++){
            if (this.associatedParts.get(i).equals(selectedAssociatedPart)){
                this.associatedParts.remove(i);
                isDeleted = true;
            }
        }

        return isDeleted;
    }

    /**
     *
     * @return the part list of a product
     */
    public ObservableList<Part> getAllAssociatedParts(){

        return this.associatedParts;

    }
}
