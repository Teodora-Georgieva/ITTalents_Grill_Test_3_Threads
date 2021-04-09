package grill.products.meat;

import grill.products.ProductName;

public class Steak extends Meat{
    @Override
    public double getPrice() {
        return 3;
    }

    @Override
    public long getCookingTime() {
        return 4000;
    }

    @Override
    public ProductName getName() {
        return ProductName.STEAK;
    }
}