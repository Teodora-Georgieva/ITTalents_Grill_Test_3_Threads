package grill.people;

import grill.dishes.IDish;
import grill.dishes.IProductName;
import grill.products.Product;
import grill.products.breads.BreadType;
import grill.products.breads.WhiteBread;
import grill.products.breads.WholeGrainBread;
import grill.products.salads.ProductType;

public class BreadMaker extends Staff implements Runnable{
    @Override
    public void run() {
        while(true){
            IDish dish = allDishes.getNotFullDish(this.getProductType());
            IProductName breadType = dish.getProductName();

            Product product = null;
            switch ((BreadType) breadType){
                case WHITE_BREAD:
                    product = new WhiteBread(); break;
                case WHOLE_GRAIN_BREAD:
                    product = new WholeGrainBread(); break;
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
        return PersonType.BREAD_MAKER;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.BREAD;
    }
}
