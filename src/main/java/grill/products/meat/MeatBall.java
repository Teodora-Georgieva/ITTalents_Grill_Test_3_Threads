package grill.products.meat;

import grill.products.ProductName;

public class MeatBall extends Meat{
    @Override
    public double getPrice() {
        return 1;
    }

    @Override
    public long getCookingTime() {
        return 2000;
    }

    @Override
    public ProductName getName() {
        return ProductName.MEATBALL;
    }
}
