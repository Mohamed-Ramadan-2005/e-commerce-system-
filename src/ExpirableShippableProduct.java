import java.time.LocalDate;

public class ExpirableShippableProduct extends ShippableProduct{
    private final LocalDate expiryDate;
    public ExpirableShippableProduct(String name, double price, int quantity, LocalDate expiryDate, double weight, double fees){
        super(name,price,quantity,weight,fees);
        this.expiryDate = expiryDate;
    }
    @Override
    public double getWeight() {
        return weight;
    }
    @Override
    public String getName() {
        return super.getName();
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }
}
