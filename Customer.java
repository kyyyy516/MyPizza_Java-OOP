// Customer.java

import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends Person implements Serializable {
    private String customerID;
    private String custAddress;
    private ArrayList<Order> orderHistory; 

    public Customer() {
        super(); 
        this.customerID = "";
        this.custAddress = "";
        this.orderHistory = new ArrayList<>();
    }

    public Customer(String name, String email, String phone, String customerID, String custAddress) {
        super(name, email, phone);
        this.customerID = customerID;
        this.custAddress = custAddress;
        this.orderHistory = new ArrayList<>(); 
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustomerDetails() {
        return "\nName: " + getName() + "\nEmail: " + getEmail() + "\nPhone: " + getPhone() + "\nCustomer ID: " + customerID + "\nAddress: " + custAddress;
    }

    public ArrayList<Order> viewOrderHistory() { 
        return orderHistory;
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }
}