package grill.products;

public abstract class Product {
    //private double price;
    private ProductName productName;
    //private long cookingTime;
    public abstract double getPrice();
    public abstract long getCookingTime();

    public abstract ProductName getName();
}