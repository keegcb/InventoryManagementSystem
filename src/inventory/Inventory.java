package inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partIDNumber = 100000;
    private static int productIDNumber = 1000;

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

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

    public static ObservableList<Part> lookupPart(String partName) {
        boolean partMatch = false;

        for (Part currentPart : allParts) {
            if (currentPart.getName().equalsIgnoreCase(partName)) {
                partMatch = true;
                break;
            }
        }
        if (partMatch) {
            return allParts;
        } else {
            System.out.printf("A part with the name %s could not be found in the list.\n", partName);
            return null;
        }
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        boolean productMatch = false;

        for (Product currentProduct : allProducts) {
            if (currentProduct.getName().equalsIgnoreCase(productName)) {
                productMatch = true;
                break;
            }
        }
        if (productMatch) {
            return allProducts;
        } else {
            System.out.printf("A Product with the name %s could not be found in the list.\n", productName);
            return null;
        }
    }

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

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

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static int nextPartID(){
        partIDNumber++;
        return partIDNumber;
    }

    public static int nextProductID(){
        productIDNumber++;
        return productIDNumber;
    }
}
