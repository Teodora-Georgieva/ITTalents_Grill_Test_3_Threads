package grill.dishes;

import grill.products.Product;
import grill.products.meat.Meat;
import grill.products.meat.MeatType;

import java.util.ArrayList;

public class Pot implements IDish{
    private MeatType meatType;
    private ArrayList<Meat> meats;
    //private int crrQuantity;
    private static int capacity = 20;

    public Pot(MeatType meatType){
        this.meatType = meatType;
        this.meats = new ArrayList<>();
    }

    /*
    public MeatType getMeatType() {
        return meatType;
    }

     */

    public boolean isFull(){
        return this.meats.size() == capacity;
    }

    @Override
    public void addProduct(Product product) {
        this.meats.add((Meat) product);
    }

    @Override
    public Product getProduct() {
        return this.meats.remove(0);
    }

    @Override
    public IProductName getProductName() {
        return this.meatType;
    }

    public boolean isEmpty(){
        return this.meats.isEmpty();
    }
}