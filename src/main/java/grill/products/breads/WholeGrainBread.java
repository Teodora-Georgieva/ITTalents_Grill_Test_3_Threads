package grill.products.breads;

import grill.products.ProductName;

public class WholeGrainBread extends Bread{
    @Override
    public double getPrice() {
        return 1.4;
    }

    @Override
    public long getCookingTime() {
        return 5000;
    }

    @Override
    public ProductName getName() {
        return ProductName.WHOLE_GRAIN_BREAD;
    }
}