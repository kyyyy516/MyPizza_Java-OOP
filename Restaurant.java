// Restaurant.java

import java.io.*;
import java.util.ArrayList;

public class Restaurant {
    private int restaurantID;
    private String name;
    private String location;
    private ArrayList<MenuItem> menu;
    private ArrayList<Order> orders;
    private ArrayList<Customer> customers;

    public Restaurant(int restaurantID, String name, String location) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.location = location;
        this.menu = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void loadMenuFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("menu.txt"))) {
            menu = (ArrayList < MenuItem>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Menu file not found. Starting with an empty menu.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveMenuToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("menu.txt"))) {
            oos.writeObject(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadOrdersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("orders.txt"))) {
            orders = (ArrayList<Order>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Orders file not found. Starting with an empty orders list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveOrdersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("orders.txt"))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCustomersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customers.txt"))) {
            customers = (ArrayList<Customer>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Customers file not found. Starting with an empty customers list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveCustomersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customers.txt"))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Customer findCustomerByID(String customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public MenuItem findMenuItemByID(String itemID) {
        for (MenuItem menuItem : menu) {
            if (menuItem.getItemID().equals(itemID)) {
                return menuItem;
            }
        }
        return null; 
    }    

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public void removeMenuItem(MenuItem item) {
        menu.remove(item);
    }

    public void updateMenuItem(MenuItem oldItem, MenuItem newItem) {
        int index = menu.indexOf(oldItem);
        if (index != -1) {
            menu.set(index, newItem);
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.getCustomer().addOrderToHistory(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}