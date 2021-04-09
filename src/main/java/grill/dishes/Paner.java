package grill.dishes;

import grill.products.Product;
import grill.products.breads.Bread;
import grill.products.breads.BreadType;

import java.util.ArrayList;

public class Paner implements IDish{
    private BreadType breadType;
    private ArrayList<Bread> breads;
    //private int crrQuantity;
    private static final int capacity = 30;

    public Paner(BreadType breadType){
        this.breadType = breadType;
        this.breads = new ArrayList<>();
    }

    /*
    public BreadType getBreadType() {
        return breadType;
    }

     */

    public boolean isFull(){
        return this.breads.size() == capacity;
    }

    @Override
    public void addProduct(Product product) {
        this.breads.add((Bread) product);
    }

    @Override
    public Product getProduct() {
        return this.breads.remove(0);
    }

    @Override
    public IProductName getProductName() {
        return this.breadType;
    }

    public boolean isEmpty(){
        return this.breads.isEmpty();
    }
}