package inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

public class Inventory {

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partIDNumber = 100000;
    private static int productIDNumber = 1000;

    public void addPart(Part newPart){
        this.allParts.add(newPart);
    }

    public void addProduct(Product newProduct){
        this.allProducts.add(newProduct);
    }

    public Part lookupPart(int partId){
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

    public Product lookupProduct(int productId){
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

    public ObservableList<Part> lookupPart(String partName) {
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

    public ObservableList<Product> lookupProduct(String productName) {
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

    public void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    public boolean deletePart(Part selectedPart){
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

    public boolean deleteProduct(Product selectedProduct){
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

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static int nextPartID(){
        partIDNumber++;
        return partIDNumber;
    }

    public int nextProductID(){
        productIDNumber++;
        return productIDNumber;
    }
}
