// CreditCardPayment.java

public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;

    public CreditCardPayment(String paymentID, String orderID, double amount, String paymentMethod, String cardNumber, String cardHolder, String expiryDate) {
        super(paymentID, orderID, amount, paymentMethod);
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
    }

    @Override
    public void processPayment() {
        // Implementation specific to credit card payment processing
        confirmPayment();
        System.out.println("Credit card payment processed for order: " + getOrderID());
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
