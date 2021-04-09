package grill.products.meat;

import grill.products.ProductName;

public class Pleskavica extends Meat{
    @Override
    public double getPrice() {
        return 2;
    }

    @Override
    public long getCookingTime() {
        return 3000;
    }

    @Override
    public ProductName getName() {
        return ProductName.PLESKAVICA;
    }
}
