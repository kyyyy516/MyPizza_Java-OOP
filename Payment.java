// Payment.java

import java.io.Serializable;

public abstract class Payment implements Serializable, PaymentProcessor {

    private String paymentID;
    private String orderID;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;

    public Payment(String paymentID, String orderID, double amount, String paymentMethod) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = "Pending";
    }

    public abstract void processPayment();

    public void confirmPayment() {
        paymentStatus = "Confirmed";
    }

    public String getPaymentID() {
        return paymentID;
    }

    public String getOrderID() {
        return orderID;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
