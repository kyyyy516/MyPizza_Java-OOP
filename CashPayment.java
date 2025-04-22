// CashPayment.java

public class CashPayment extends Payment {

    public CashPayment(String paymentID, String orderID, double amount, String paymentMethod) {
        super(paymentID, orderID, amount, paymentMethod);
    }

    @Override
    public void processPayment() {
        // Implementation specific to cash payment processing
        confirmPayment();
        System.out.println("Cash payment processed for order: " + getOrderID());
    }
}
