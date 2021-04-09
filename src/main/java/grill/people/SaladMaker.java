package grill.people;

import grill.dishes.IDish;
import grill.dishes.IProductName;
import grill.products.Product;
import grill.products.salads.*;

public class SaladMaker extends Staff implements Runnable{
    @Override
    public void run() {
        while(true){
            IDish dish = allDishes.getNotFullDish(this.getProductType());
            IProductName saladType = dish.getProductName();

            Product product = null;
            switch ((SaladType)saladType){
                case SNEJANKA:
                    product = new Snejanka(); break;
                case CABBAGE_AND_CARROTS:
                    product = new CabbageAndCarrots(); break;
                case LUTENICA:
                    product = new Lutenica(); break;
                case RUSSIAN_SALAD:
                    product = new RuskaSalad(); break;
                case TOMATOES_AND_CUCUMBERS:
                    product = new TomatoesAndCucumbers(); break;
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
        return PersonType.SALAD_MAKER;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.SALAD;
    }
}