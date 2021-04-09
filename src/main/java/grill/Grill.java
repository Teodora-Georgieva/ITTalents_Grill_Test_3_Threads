package grill;

import com.google.gson.Gson;
import grill.dishes.AllDishes;
import grill.dishes.IDish;
import grill.dishes.IProductName;
import grill.people.*;
import grill.products.Product;
import grill.products.ProductName;
import grill.products.salads.ProductType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Grill {
    private AllDishes allDishes;
    private GrillMaker grillMaker;
    private BreadMaker breadMaker;
    private SaladMaker saladMaker;
    private SalesWoman salesWoman;
    private Queue<Client> clients;
    private double money;
    private ArrayList<PurchaseData> purchases; //?
    private File notebook;
    private Connection connection = DBConnector.getInstance().getConnection();

    public Grill(){
        Person.grill = this;
        this.allDishes = new AllDishes();
        Staff.allDishes = this.allDishes;
        this.breadMaker = new BreadMaker();
        this.grillMaker = new GrillMaker();
        this.saladMaker = new SaladMaker();
        this.salesWoman = new SalesWoman();
        this.notebook = new File("purchases.json");
        this.clients = new LinkedList<>();
        this.purchases = new ArrayList<>();
        try {
            this.notebook.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startWork(){
        new Thread(this.grillMaker).start();
        new Thread(this.breadMaker).start();
        new Thread(this.saladMaker).start();
        new Thread(this.salesWoman).start();
        Thread dbInserter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    insertIntoDB();
                }
            }
        });
        dbInserter.setDaemon(true);
        dbInserter.start();

        Thread inquiryMaker = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(50000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    generateInquiries();
                }
            }
        });
        inquiryMaker.setDaemon(true);
        inquiryMaker.start();
    }

    public synchronized ArrayList<Product> receivePurchase(ArrayList<ProductName> purchase, Client client){
        IProductName breadName = purchase.get(0);
        IProductName meatName = purchase.get(1);
        IProductName saladName = purchase.get(2);

        ArrayList<Product> meal = new ArrayList<>();

        IDish panner = allDishes.getDish(ProductType.BREAD, breadName);
        while (panner.isEmpty()){
            try {
                System.out.println("SalesWoman waits because there is no " + breadName);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Product bread = panner.getProduct();
        meal.add(bread);

        IDish pot = allDishes.getDish(ProductType.MEAT, meatName);
        while(pot.isEmpty()){
            try {
                System.out.println("SalesWoman waits because there is no " + meatName);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Product meat = pot.getProduct();
        meal.add(meat);

        IDish bowl = allDishes.getDish(ProductType.SALAD, saladName);
        while(bowl.isEmpty()){
            try {
                System.out.println("SalesWoman waits because there is no " + saladName);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Product salad = bowl.getProduct();
        meal.add(salad);
        double price = bread.getPrice() + meat.getPrice() + salad.getPrice();
        client.payBill(price);
        this.receiveMoney(price);
        PurchaseData purchaseData = new PurchaseData(breadName.toString(), meatName.toString(), saladName.toString(),
                                                      price, LocalDateTime.now());
        this.purchases.add(purchaseData); //?

        Gson gson = new Gson();
        String json = gson.toJson(purchaseData);

        if(this.notebook == null){
            try {
                this.notebook.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(PrintStream ps = new PrintStream(this.notebook)){
            ps.println(json);
            /*
            ps.println(breadName + ", " + meatName + ", " + saladName + " - " + price + " leva, " +
                        LocalDate.now() + " " + LocalTime.now());

             */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return meal;
    }

    public synchronized void notifySalesWoman(){
        notifyAll();
    }

    public synchronized void addClient(Client client){
        this.clients.offer(client);
        notifyAll(); //????
    }

    public boolean hasClients() {
        return !this.clients.isEmpty();
    }

    public Client getNextClient() {
        return this.clients.peek();
    }

    public void removeClient(){
        this.clients.poll();
    }

    private void receiveMoney(double money){
        this.money += money;
    }

    private void insertIntoDB(){
        Gson gson = new Gson();

        try(Scanner scanner = new Scanner(this.notebook)){
            while (scanner.hasNextLine()){
                String crrLine = scanner.nextLine();
                PurchaseData purchaseData = gson.fromJson(crrLine, PurchaseData.class);
                this.insert(purchaseData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.notebook.delete();
    }

    private void insert(PurchaseData purchaseData){
        String breadName = purchaseData.getBreadName();
        String meatName = purchaseData.getMeatName();
        String saladName = purchaseData.getSaladName();
        //double price = purchaseData.getPrice();
        LocalDateTime localDateTime = purchaseData.getLocalDateTime();

        int shopId = 22;
        int breadTypeId = breadName.equals("WHITE_BREAD") ? 1 : 2;
        int meatTypeId = 0;
        switch (meatName){
            case "MEATBALL": meatTypeId = 1; break;
            case "STEAK": meatTypeId = 3; break;
            case "PLESKAVICA": meatTypeId = 2; break;
        }

        int garnishTypeId = 0;
        switch (saladName){
            case "RUSSIAN_SALAD": garnishTypeId = 1; break;
            case "TOMATOES_AND_CUCUMBERS": garnishTypeId = 5; break;
            case "LUTENICA": garnishTypeId = 2; break;
            case "SNEJANKA": garnishTypeId = 3; break;
            case "CABBAGE_AND_CARROTS": garnishTypeId = 4; break;
        }

        String insertStmt = "INSERT INTO sales (shop_id, bread_type_id, meat_type_id, garnish_type_id, date_created) " +
                "VALUES(?, ?, ?, ?, ?)";

        try(PreparedStatement ps = this.connection.prepareStatement(insertStmt);) {
            ps.setInt(1, shopId);
            ps.setInt(2, breadTypeId);
            ps.setInt(3, meatTypeId);
            ps.setInt(4, garnishTypeId);
            ps.setTimestamp(5, Timestamp.valueOf(localDateTime));
            ps.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void generateInquiries() {
        System.out.println("--------------- GENERATING INQUIRIES -----------------");
        this.generateTotalCountOfMyPurchases();
        this.selectMostPurchasedMeat();
        this.selectTotalCountOfSoldPleskavica();
        //..............
        this.selectLeastSoldGarnishOnMyGrill(); //the last task
        System.out.println("------------------------------------------------------");
    }

    private void selectTotalCountOfSoldPleskavica() {
        //.....
    }

    private void generateTotalCountOfMyPurchases() {
        String selectCountOfPurchases = "SELECT COUNT(*) FROM sales WHERE id = ?";
        int myID = 22;

        try(PreparedStatement ps = this.connection.prepareStatement(selectCountOfPurchases);){
            ps.setInt(1, myID);
            ResultSet row = ps.executeQuery();
            row.next();
            int totalCount = row.getInt(1);
            System.out.println("Total count of purchases: " + totalCount);
            System.out.println();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void selectMostPurchasedMeat() {
        String selectMostPurchasedMeat = "SELECT mt.name, s.COUNT(meat_type_id) AS count" +
                                          "FROM sales s JOIN meat_types mt "+
                                          "ON s.meat_type_id = mt.id " +
                                           "WHERE s.id = ? " +
                                          "GROUP BY meat_type_id " +
                                          "ORDER BY count DESC " +
                                          "LIMIT 1;";
        int myID = 22;

        try(PreparedStatement ps = this.connection.prepareStatement(selectMostPurchasedMeat);){
            ps.setInt(1, myID);
            ResultSet row = ps.executeQuery();
            row.next();
            String meat = row.getString("name");
            System.out.println("Most purchased meat: " + meat);
            System.out.println();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void selectLeastSoldGarnishOnMyGrill() {
        String selectLeastSoldGarnish = "SELECT gt.name, s.COUNT(garnish_type_id) AS count" +
                "FROM sales s JOIN garnish_types gt "+
                "ON s.garnish_type_id = gt.id " +
                "WHERE s.id = ? " +
                "GROUP BY garnish_type_id " +
                "ORDER BY count " +
                "LIMIT 1;";

        int myID = 22;

        try(PreparedStatement ps = this.connection.prepareStatement(selectLeastSoldGarnish);){
            ps.setInt(1, myID);
            ResultSet row = ps.executeQuery();
            row.next();
            String garnish = row.getString("name");
            System.out.println("Least purchased garnish: " + garnish);
            System.out.println();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}