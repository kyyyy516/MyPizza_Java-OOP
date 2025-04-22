// MenuItem.java

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String itemID;
    private String name;
    private String description;
    private double price;

    public MenuItem(String itemID, String name, String description, double price) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setItemDetails(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getItemDetails() {
        return "ID: " + itemID + "\nName: " + name + "\nDescription: " + description + "\nPrice: $" + price;
    }
}
