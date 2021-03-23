package inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

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

    }

    public ObservableList<Product> lookupProduct(String productName) {

    }

    public void updatePart(int index, Part selectedPart){

    }

    public void updateProduct(int index, Product newProduct){

    }

    public boolean deletePart(Part selectedPart){
        boolean isDeleted = false;
        //add logic
        return isDeleted;
    }

    public boolean deleteProduct(Product selectedProduct){
        boolean isDeleted = false;
        //add logic
        return isDeleted;
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}

}