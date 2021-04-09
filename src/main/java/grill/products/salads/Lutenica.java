package grill.products.salads;

import grill.products.ProductName;

public class Lutenica extends Salad{
    @Override
    public double getPrice() {
        return 1.1;
    }

    @Override
    public long getCookingTime() {
        return 8000;
    }

    @Override
    public ProductName getName() {
        return ProductName.LUTENICA;
    }
}
