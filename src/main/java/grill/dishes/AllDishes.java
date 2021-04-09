package grill.dishes;

import grill.products.breads.BreadType;
import grill.products.meat.MeatType;
import grill.products.salads.ProductType;
import grill.products.salads.SaladType;

import java.util.ArrayList;
import java.util.HashMap;

public class AllDishes {
    private HashMap<ProductType, ArrayList<IDish>> dishes;
    //private ArrayList<Pot> pots;
    //private ArrayList<Bowl> bowls;
    //private ArrayList<Paner> paners;

    public AllDishes(){
        this.dishes = new HashMap<>();
        this.dishes.put(ProductType.MEAT, new ArrayList<>());
        ArrayList<IDish> pots = this.dishes.get(ProductType.MEAT);
        pots.add(new Pot(MeatType.MEATBALL));
        pots.add(new Pot(MeatType.PLESKAVICA));
        pots.add(new Pot(MeatType.STEAK));

        this.dishes.put(ProductType.BREAD, new ArrayList<>());
        ArrayList<IDish> paners = this.dishes.get(ProductType.BREAD);
        paners.add(new Paner(BreadType.WHITE_BREAD));
        paners.add(new Paner(BreadType.WHOLE_GRAIN_BREAD));

        this.dishes.put(ProductType.SALAD, new ArrayList<>());
        ArrayList<IDish> bowls = this.dishes.get(ProductType.SALAD);
        bowls.add(new Bowl(SaladType.RUSSIAN_SALAD));
        bowls.add(new Bowl(SaladType.CABBAGE_AND_CARROTS));
        bowls.add(new Bowl(SaladType.LUTENICA));
        bowls.add(new Bowl(SaladType.SNEJANKA));
        bowls.add(new Bowl(SaladType.TOMATOES_AND_CUCUMBERS));

        /*
        this.bowls = new ArrayList<>();
        this.bowls.add(new Bowl(SaladType.RUSSIAN_SALAD));
        this.bowls.add(new Bowl(SaladType.CABBAGE_AND_CARROTS));
        this.bowls.add(new Bowl(SaladType.LUTENICA));
        this.bowls.add(new Bowl(SaladType.SNEJANKA));
        this.bowls.add(new Bowl(SaladType.TOMATOES_AND_CUCUMBERS));

         */

        /*
        this.paners = new ArrayList<>();
        this.paners.add(new Paner(BreadType.WHITE_BREAD));
        this.paners.add(new Paner(BreadType.WHOLE_GRAIN_BREAD));
*/

        /*
        this.pots = new ArrayList<>();
        this.pots.add(new Pot(MeatType.MEATBALL));
        this.pots.add(new Pot(MeatType.PLESKAVICA));
        this.pots.add(new Pot(MeatType.STEAK));

         */
    }

    public IDish getNotFullDish(ProductType productType){
        for(ProductType product : this.dishes.keySet()){
            if(product == productType){
                //System.out.println("-----" + product);
                ArrayList<IDish> dishes = this.dishes.get(product);
                for(IDish dish : dishes){
                    if(!dish.isFull()){
                        return dish;
                    }
                }
            }
        }

        return null;
    }

    public IDish getDish(ProductType productType, IProductName productName){
        ArrayList<IDish> dishes = this.dishes.get(productType);
        //System.out.println("searched product: " + productName);
        for(IDish dish : dishes){
            //System.out.println("crr dish product: " + dish.getProductName());
            if(dish.getProductName() == productName){
                return dish;
            }
        }

        return null;
    }
}