package grill.dishes;

import grill.products.Product;
import grill.products.salads.Salad;
import grill.products.salads.SaladType;

import java.util.ArrayList;

public class Bowl implements IDish{
    private SaladType saladType;
    //private Salad salad;
    private ArrayList<Salad> salads;
    private int crrQuantity;
    private static final int capacity = 5000;

    public Bowl(SaladType saladType){
        //this.saladType = saladType;
        this.salads = new ArrayList<>();
    }

    /*
    public SaladType getSaladType() {
        return saladType;
    }

     */

    public boolean isFull(){
        //return this.salads.size() == capacity;
        return this.crrQuantity == capacity;
    }

    @Override
    public void addProduct(Product product) {
        this.salads.add((Salad) product);
        this.crrQuantity += 500;
    }

    @Override
    public Product getProduct() {
        this.crrQuantity-=500;
        return salads.remove(0);
    }

    @Override
    public IProductName getProductName() {
        return this.saladType;
    }

    public boolean isEmpty(){
        return this.salads.isEmpty();
    }
}