package grill.people;

import grill.Grill;
import grill.products.Product;

import java.time.Period;
import java.util.ArrayList;

public abstract class Person {
    public enum PersonType{
        BREAD_MAKER, CLIENT, GRILL_MAKER, SALAD_MAKER, SALES_WOMAN
    }

    //private static int id = 1;
    //private int personID;
    private String name;
    public static Grill grill;

    public Person(){
        //this.personID = id++;
        this.name = this.getPersonType().toString();// + "_" + this.personID;
    }

    public abstract PersonType getPersonType();

    public String getName() {
        return name;
    }
}