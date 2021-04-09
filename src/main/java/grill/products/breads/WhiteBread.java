package grill.products.breads;

import grill.products.ProductName;

public class WhiteBread extends Bread{
    @Override
    public double getPrice() {
        return 0.9;
    }

    @Override
    public long getCookingTime() {
        return 2000;
    }

    @Override
    public ProductName getName() {
        return ProductName.WHITE_BREAD;
    }
}
