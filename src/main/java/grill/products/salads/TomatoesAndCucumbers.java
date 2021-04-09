package grill.products.salads;

import grill.products.Product;
import grill.products.ProductName;

public class TomatoesAndCucumbers extends Salad{
    @Override
    public double getPrice() {
        return 1.3;
    }

    @Override
    public long getCookingTime() {
        return 3000;
    }

    @Override
    public ProductName getName() {
        return ProductName.TOMATOES_AND_CUCUMBERS;
    }
}