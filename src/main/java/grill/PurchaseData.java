package grill;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PurchaseData {
    private String breadName;
    private String meatName;
    private String saladName;
    private double price;
    private LocalDateTime localDateTime;

    public PurchaseData(String breadName, String meatName, String saladName, double price, LocalDateTime localDateTime) {
        this.breadName = breadName;
        this.meatName = meatName;
        this.saladName = saladName;
        this.price = price;
        this.localDateTime = localDateTime;
    }

    public String getBreadName() {
        return breadName;
    }

    public String getMeatName() {
        return meatName;
    }

    public String getSaladName() {
        return saladName;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}