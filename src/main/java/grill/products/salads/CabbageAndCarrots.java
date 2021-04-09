package grill.products.salads;

import grill.products.ProductName;

public class CabbageAndCarrots extends Salad{
    @Override
    public double getPrice() {
        return 0.8;
    }

    @Override
    public long getCookingTime() {
        return 2000;
    }

    @Override
    public ProductName getName() {
        return ProductName.CABBAGE_AND_CARROTS;
    }
}