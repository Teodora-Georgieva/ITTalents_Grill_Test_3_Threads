package grill.people;

import grill.Grill;
import grill.dishes.AllDishes;
import grill.products.salads.ProductType;

public abstract class Staff extends Person{
    public static AllDishes allDishes;

    public abstract ProductType getProductType();
}
