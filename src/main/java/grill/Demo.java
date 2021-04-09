package grill;

import grill.people.Client;

public class Demo {
    public static void main(String[] args) {
        Grill grill = new Grill();
        for (int i = 0; i < 20; i++) {
            grill.addClient(new Client());
        }

        grill.startWork();
    }
}