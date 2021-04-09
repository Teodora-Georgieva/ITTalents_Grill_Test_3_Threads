package grill.people;

import grill.products.Product;
import grill.products.ProductName;
import grill.utils.Randomizer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Client extends Person{
    private ArrayList<ProductName> purchase;
    private double money = 200;
    private ArrayList<Product> meal;

    @Override
    public PersonType getPersonType() {
        return PersonType.CLIENT;
    }

    public ArrayList<ProductName> makePurchase(){
        ProductName bread = new Random().nextBoolean() ? ProductName.WHITE_BREAD : ProductName.WHOLE_GRAIN_BREAD;
        int num = Randomizer.getRandomNumber(1, 3);
        ProductName meat = null;
        switch (num){
            case 1: meat  = ProductName.MEATBALL; break;
            case 2: meat = ProductName.PLESKAVICA; break;
            case 3: meat = ProductName.STEAK; break;
        }

        ProductName salad = null;
        num = Randomizer.getRandomNumber(1, 5);
        switch (num){
            case 1: salad = ProductName.RUSSIAN_SALAD; break;
            case 2: salad = ProductName.CABBAGE_AND_CARROTS; break;
            case 3: salad = ProductName.LUTENICA; break;
            case 4: salad = ProductName.SNEJANKA; break;
            case 5: salad = ProductName.TOMATOES_AND_CUCUMBERS;
        }

        this.purchase = new ArrayList<>();
        this.purchase.add(bread);
        this.purchase.add(meat);
        this.purchase.add(salad);
        return this.purchase;
    }

    public void payBill(double money){
        this.money -= money;
    }

    protected void receiveMeal(ArrayList<Product> meal){
        this.meal = new ArrayList<>();
        this.meal.addAll(meal);
    }
}