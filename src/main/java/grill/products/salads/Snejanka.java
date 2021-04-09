package grill.products.salads;

import grill.products.ProductName;

public class Snejanka extends Salad{
    @Override
    public double getPrice() {
        return 1.2;
    }

    @Override
    public long getCookingTime() {
        return 4000;
    }

    @Override
    public ProductName getName() {
        return ProductName.SNEJANKA;
    }
}
