package grill.products.salads;

import grill.products.ProductName;

public class RuskaSalad extends Salad{
    @Override
    public double getPrice() {
        return 1.5;
    }

    @Override
    public long getCookingTime() {
        return 10000;
    }

    @Override
    public ProductName getName() {
        return ProductName.RUSSIAN_SALAD;
    }
}
