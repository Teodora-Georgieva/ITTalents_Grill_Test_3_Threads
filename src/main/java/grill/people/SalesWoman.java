package grill.people;

import grill.dishes.Bowl;
import grill.dishes.Paner;
import grill.dishes.Pot;
import grill.products.Product;
import grill.products.ProductName;
import grill.products.salads.ProductType;

import java.util.ArrayList;

public class SalesWoman extends Staff implements Runnable{
    @Override
    public void run() {
        while (grill.hasClients()){
            Client client = grill.getNextClient();
            ArrayList<ProductName> purchase = client.makePurchase();
            ArrayList<Product> meal = grill.receivePurchase(purchase, client);
            client.receiveMeal(meal);
            grill.removeClient();
        }
    }

    @Override
    public PersonType getPersonType() {
        return PersonType.SALES_WOMAN;
    }

    @Override
    public ProductType getProductType() {
        return null;
    }
}