// MyPizza.java

import java.util.Scanner;
import java.util.UUID;

public class MyPizza {
    private static Restaurant restaurant;

    public static void main(String[] args) {
        restaurant = new Restaurant(1, "MyPizza", "UTM");
        restaurant.loadMenuFromFile();
        restaurant.loadOrdersFromFile();
        restaurant.loadCustomersFromFile();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Welcome to MyPizza!");
            System.out.println("1. Customer");
            System.out.println("2. Restaurant");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    customerMenu(scanner);
                    break;
                case 2:
                    restaurantMenu(scanner);
                    break;
                case 3:
                    restaurant.saveMenuToFile();
                    restaurant.saveOrdersToFile();
                    restaurant.saveCustomersToFile();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void customerMenu(Scanner scanner) {
        System.out.println();
        System.out.print("Enter your customer ID: ");
        String customerID = scanner.nextLine();
        Customer customer = restaurant.findCustomerByID(customerID);

        if (customer == null) {
            System.out.println();
            System.out.println("Customer not found. Please enter your details.");
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your phone: ");
            String phone = scanner.nextLine();
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();

            customer = new Customer(name, email, phone, customerID, address);
            restaurant.addCustomer(customer);
        }

        while (true) {
            System.out.println();
            System.out.println("Customer Menu:");
            System.out.println("1. Place Order");
            System.out.println("2. View Order History");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    placeOrder(scanner, customer);
                    break;
                case 2:
                    viewOrderHistory(customer);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void placeOrder(Scanner scanner, Customer customer) {
        String orderID = UUID.randomUUID().toString();
        Order order = new Order(orderID, customer, java.time.LocalDateTime.now());
    
        // Check if the customer is a new customer
        boolean isNewCustomer = customer.viewOrderHistory().isEmpty();

         // If the customer is old, add a free mushroom soup
         if (!isNewCustomer) {
            MenuItem mushroomSoup = restaurant.findMenuItemByID("soup001");
            if (mushroomSoup != null) {
                order.addOrderItem(mushroomSoup);
                order.setTotalAmount(order.getTotalAmount() - mushroomSoup.getPrice()); 
                System.out.println("Free Mushroom Soup added to your order.");
            }
        }
    
        while (true) {
            System.out.println();
            System.out.println("Menu:");
            for (MenuItem item : restaurant.getMenu()) {
                System.out.println(item.getItemDetails());
            }
            System.out.println();
            System.out.println("1. Add item to order");
            System.out.println("2. Remove item from order");
            System.out.println("3. Complete order");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.print("Enter item ID: ");
                    String itemID = scanner.nextLine();
                    MenuItem selectedItem = null;
                    for (MenuItem item : restaurant.getMenu()) {
                        if (item.getItemID().equals(itemID)) {
                            selectedItem = item;
                            break;
                        }
                    }
                    if (selectedItem != null) {
                        order.addOrderItem(selectedItem);
                        System.out.println("Item added to order.");
                    } else {
                        System.out.println("Invalid item ID. Please try again.");
                    }
                    break;
                case 2:
                    System.out.println();
                    System.out.print("Enter item ID: ");
                    String removeItemID = scanner.nextLine();
                    MenuItem removeItem = null;
                    for (MenuItem item : order.getOrderItems()) {
                        if (item.getItemID().equals(removeItemID)) {
                            removeItem = item;
                            break;
                        }
                    }
                    if (removeItem != null) {
                        order.removeOrderItem(removeItem);
                        System.out.println("Item removed from order.");
                    } else {
                        System.out.println("Invalid item ID. Please try again.");
                    }
                    break;
                case 3:
                    if (isNewCustomer) {
                        // 10% discount for new customer
                        double discountedAmount = order.getTotalAmount() * 0.9; 
                        order.setTotalAmount(discountedAmount);
                        System.out.println("10% discount applied.");
                    }
                    System.out.println("Total Amount: $" + order.getTotalAmount());

                    System.out.print("Enter any remark for your order: ");
                    String remark = scanner.nextLine();
                    order.setRemark(remark);

                    completeOrder(scanner, order);
                    restaurant.addOrder(order);
                    System.out.println("Order completed successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void completeOrder(Scanner scanner, Order order) {
        System.out.println();
        System.out.println("Payment Methods:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        Payment payment = null;
        switch (choice) {
            case 1:
                System.out.println();
                System.out.print("Enter card number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter card holder name: ");
                String cardHolder = scanner.nextLine();
                System.out.print("Enter expiry date: ");
                String expiryDate = scanner.nextLine();
                payment = new CreditCardPayment(UUID.randomUUID().toString(), order.getOrderID(), order.calculateTotal(), "Credit Card", cardNumber, cardHolder, expiryDate);
                break;
            case 2:
                System.out.println();
                payment = new CashPayment(UUID.randomUUID().toString(), order.getOrderID(), order.calculateTotal(), "Cash");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }
        order.processPayment(payment);
    }

    private static void viewOrderHistory(Customer customer) {
        System.out.println();
        System.out.println("Order History:");
        for (Order order : customer.viewOrderHistory()) {
            System.out.println();
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Items:");
            for (MenuItem item : order.getOrderItems()) {
                System.out.println(item.getItemDetails());
            }
            System.out.println("Remark: " + order.getRemark());
            System.out.println("Total Amount: $" + order.getTotalAmount());
            System.out.println("Payment Status: " + order.getPayment().getPaymentStatus());
            System.out.println();
        }
    }

    private static void restaurantMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("Restaurant Menu:");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Remove Menu Item");
            System.out.println("3. Update Menu Item");
            System.out.println("4. View Orders");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addMenuItem(scanner);
                    break;
                case 2:
                    removeMenuItem(scanner);
                    break;
                case 3:
                    updateMenuItem(scanner);
                    break;
                case 4:
                    viewOrders();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addMenuItem(Scanner scanner) {
        System.out.println();
        System.out.print("Enter item ID: ");
        String itemID = scanner.nextLine();
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item description: ");
        String description = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); 
        
        MenuItem newItem = new MenuItem(itemID, name, description, price);
        restaurant.addMenuItem(newItem);
        System.out.println("Menu item added successfully.");
    }
    
    private static void removeMenuItem(Scanner scanner) {
        System.out.println();
        System.out.print("Enter item ID to remove: ");
        String itemID = scanner.nextLine();
        MenuItem removedItem = restaurant.findMenuItemByID(itemID);
        if (removedItem != null) {
            restaurant.removeMenuItem(removedItem);
            System.out.println("Menu item removed successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }
    
    private static void updateMenuItem(Scanner scanner) {
        System.out.println();
        System.out.print("Enter item ID to update: ");
        String itemID = scanner.nextLine();
        MenuItem itemToUpdate = restaurant.findMenuItemByID(itemID);
        if (itemToUpdate != null) {
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new description: ");
            String newDescription = scanner.nextLine();
            System.out.print("Enter new price: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine(); 

            itemToUpdate.setItemDetails(newName, newDescription, newPrice);
            System.out.println("Menu item updated successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }
    
    private static void viewOrders() {
        System.out.println();
        System.out.println("Current Orders:");
        for (Order order : restaurant.getOrders()) {
            System.out.println();
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Customer: " + order.getCustomer().getCustomerDetails());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Items:");
            for (MenuItem item : order.getOrderItems()) {
                System.out.println(item.getItemDetails());
            }
            System.out.println("Remark: " + order.getRemark());
            System.out.println("Total Amount: $" + order.getTotalAmount());
            System.out.println("Payment Status: " + order.getPayment().getPaymentStatus());
            System.out.println();
        }
    }
}