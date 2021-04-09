package grill.dishes;

import grill.products.Product;

public interface IDish {
    boolean isEmpty();
    boolean isFull();
    void addProduct(Product product);
    Product getProduct();
    IProductName getProductName();
}
