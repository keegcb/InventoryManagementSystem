package inventory;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * class Product.java
 * Product class simulates product object
 */

public class Product {

    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor initializes a Product with the following parameter variables.
     * @param id ID of the Product
     * @param name Name of the Product
     * @param price Price/Cost per unit of Product
     * @param stock Stock/Inventory of Product
     * @param min Minimum stock value of Product
     * @param max Maximum stock value of Product
     */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Default Constructor of Product, used when parameters are not provided during object creation.
     */
    public Product(){
        this.id = 0;
        this.name = String.valueOf("");
        this.price = (double) 0;
        this.stock = 0;
        this.max = 0;
        this.min = 0;
    }

    /**
     * Sets the Product ID value.
     * @param id the product id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets Product Name value.
     * @param name the product name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets Product Price value.
     * @param price the product price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets Product Stock value.
     * @param stock the product stock value to be set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Sets Product Minimum value.
     * @param min the product minimum to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Sets Product Maximum value.
     * @param max the product max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Gets Product ID value.
     * @return the product id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets Product Name value.
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets Product Price value.
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets Product Stock value.
     * @return the product stock level
     */
    public int getStock() {
        return stock;
    }

    /**
     * Gets Product Minimum stock value.
     * @return the product minimum
     */
    public int getMin() {
        return min;
    }

    /**
     * Gets Product Maximum stock value.
     * @return the product maximum
     */
    public int getMax() {
        return max;
    }

    /**
     * Adds a part to the list of parts for the product.
     * @param part to be added to the product
     */
    public void addAssociatedPart(Part part){

        this.associatedParts.add(part);

    }

    /**
     * Deletes a part from the part list of a product.
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
     * Gets list of associated Parts for the Product.
     * @return the part list of a product
     */
    public ObservableList<Part> getAllAssociatedParts(){

        return this.associatedParts;

    }
}
