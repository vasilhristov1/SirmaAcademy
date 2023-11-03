import java.io.Serializable;

public class Payment implements Serializable {
    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment Amount: $" + amount;
    }
}
