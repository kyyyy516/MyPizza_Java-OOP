// Order.java

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order implements Serializable {
    private String orderID;
    private Customer customer;
    private LocalDateTime orderDate;
    private ArrayList<MenuItem> orderItems;
    private double totalAmount;
    private Payment payment;
    private String remark;

    public Order() {
        this.orderID = "";
        this.customer = new Customer();
        this.orderDate = LocalDateTime.now();
        this.orderItems = new ArrayList<>();
        this.totalAmount = 0.0;
        this.remark = "";
    }

    public Order(String orderID, Customer customer, LocalDateTime orderDate) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderItems = new ArrayList<>();
        this.totalAmount = 0.0;
        this.remark = "";
    }

    public void addOrderItem(MenuItem item) {
        orderItems.add(item);
        totalAmount += item.getPrice();
    }

    public void removeOrderItem(MenuItem item) {
        if (orderItems.contains(item)) {
            orderItems.remove(item);
            totalAmount -= item.getPrice();
        }
    }

    public double calculateTotal() {
        return totalAmount;
    }

    public void processPayment(Payment paymentMethod) {
        this.payment = paymentMethod;
        this.payment.processPayment();
    }

    public String getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }

    public void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Payment getPayment() {
        return payment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}