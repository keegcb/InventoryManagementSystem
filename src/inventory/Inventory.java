package inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * class Inventory.java
 * Simulates an inventory/stock of parts and product objects.
 */
public class Inventory {

    /**
     * List of all parts in inventory system.
     */
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * List of all products in inventory system.
     */
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /**
     * Current ID for new part.
     */
    private static int partIDNumber = 100;
    /**
     * Current ID for new product.
     */
    private static int productIDNumber = 0;
    /**
     * List of searched parts.
     */
    private static final ObservableList<Part> searchPartName = FXCollections.observableArrayList();
    /**
     * List of searched products.
     */
    private static final ObservableList<Product> searchProName = FXCollections.observableArrayList();

    /**
     * Adds a new part object to the inventory parts list.
     * @param newPart Part to be added
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * Adds a new product object to the inventory products list.
     * @param newProduct Product to be added
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * Looks up a part in the part list using it's unique numerical ID.
     * @param partId ID of Part to be searched
     * @return The part object with matching the ID specified, or null if no ID matched
     */
    public static Part lookupPart(int partId){
        int index = 0;
        boolean partMatch = false;

        for (int i=0; i<allParts.size(); i++){
            Part currentPart = allParts.get(i);
            if (currentPart.getId() == partId){
                index = i;
                partMatch = true;
            }
        }
        if (partMatch) {
            return allParts.get(index);
        } else {
            System.out.printf("A part with ID %d could not be found.\n", partId);
            return null;
        }
    }

    /**
     * Looks up a product in the product list using it's unique numerical ID.
     * @param productId ID of product to be searched
     * @return The product object with matching ID specified, or null if no ID matched
     */
    public static Product lookupProduct(int productId){
        int index = 0;
        boolean productMatch = false;

        for (int i=0; i<allProducts.size(); i++){
            Product currentProduct = allProducts.get(i);
            if (currentProduct.getId() == productId){
                index = i;
                productMatch = true;
            }
        }
        if (productMatch) {
            return allProducts.get(index);
        } else {
            System.out.printf("A part with ID %d could not be found.\n", productId);
            return null;
        }
    }

    /**
     * Looks up part from part list using specified part name.
     * @param partName Name of part to be searched
     * @return Part object with matching name, or null if no name matched
     */
    public static ObservableList<Part> lookupPart(String partName) {
        boolean partMatch = false;

        for (Part currentPart : allParts) {
            if (currentPart.getName().contains(partName)) {
                searchPartName.add(currentPart);
                partMatch = true;
            }
        }
        if (partMatch) {
            return searchPartName;
        } else {
            return null;
        }
    }

    /**
     * Looks up product from product list using specified product name.
     * @param productName Name of product to be searched
     * @return Product object with matching name, or null if no name matched
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        boolean productMatch = false;

        for (Product currentProduct : allProducts) {
            if (currentProduct.getName().contains(productName)) {
                searchProName.add(currentProduct);
                productMatch = true;
            }
        }
        if (productMatch) {
            return allProducts;
        } else {
            return null;
        }
    }

    /**
     * Updates/replaces part object in inventory part list after part properties are changed.
     * @param index Location in part list of the part being updated
     * @param selectedPart Part to be updated
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**
     * Updates/replaces product object in inventory product list after product properties are changed.
     * @param index Location in product list of the product being updated
     * @param newProduct Product to be updated
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    /**
     * Deletes part object from the inventory part list.
     * @param selectedPart Part to be deleted
     * @return True if part was successfully deleted, false if no part was deleted
     */
    public static boolean deletePart(Part selectedPart){
        boolean isDeleted = false;
        for (Part currentPart : allParts) {
            if (currentPart.equals(selectedPart)) {
                allParts.remove(selectedPart);
                isDeleted = true;
                break;
            }
        }
        return isDeleted;
    }

    /**
     * Deletes product object from the inventory product list.
     * @param selectedProduct Product to be deleted
     * @return True if product was successfully deleted, false if no product was deleted
     */
    public static boolean deleteProduct(Product selectedProduct){
        boolean isDeleted = false;
        for (Product currentProduct : allProducts){
            if (currentProduct.equals(selectedProduct)){
                allProducts.remove(selectedProduct);
                isDeleted = true;
                break;
            }
        }
        return isDeleted;
    }

    /**
     * Gets the Inventory Parts list in observable format.
     * @return Parts list
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Gets the Inventory Products list in observable format.
     * @return Products list
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Iterates Part ID value to give each new part a unique numerical ID.
     * @return Iterated Part ID
     */
    public static int nextPartID(){
        partIDNumber++;
        return partIDNumber;
    }

    /**
     * Iterates Product ID value to give each new product a unique numerical ID.
     * @return Iterated Product ID
     */
    public static int nextProductID(){
        productIDNumber++;
        return productIDNumber;
    }
}
