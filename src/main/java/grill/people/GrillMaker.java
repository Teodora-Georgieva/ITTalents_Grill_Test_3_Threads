package grill.people;

import grill.dishes.IDish;
import grill.dishes.IProductName;
import grill.products.Product;
import grill.products.meat.MeatBall;
import grill.products.meat.MeatType;
import grill.products.meat.Pleskavica;
import grill.products.meat.Steak;
import grill.products.salads.ProductType;

public class GrillMaker extends Staff implements Runnable{
    @Override
    public void run() {
        while(true){
            IDish dish = allDishes.getNotFullDish(this.getProductType());
            IProductName meatType = dish.getProductName();

            Product product = null;
            switch ((MeatType)meatType){
                case MEATBALL:
                    product = new MeatBall(); break;
                case STEAK:
                    product = new Steak(); break;
                case PLESKAVICA:
                    product = new Pleskavica(); break;
            }

            try {
                Thread.sleep(product.getCookingTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dish.addProduct(product);
            grill.notifySalesWoman();
            System.out.println(this.getName() + " made " + product.getName());
        }
    }

    @Override
    public PersonType getPersonType() {
        return PersonType.GRILL_MAKER;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.MEAT;
    }
}