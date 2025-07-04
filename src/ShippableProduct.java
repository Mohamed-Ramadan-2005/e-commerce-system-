public class ShippableProduct extends Product implements Shippable {
    protected final double weight;
    protected final double fees;
    public ShippableProduct(String name,double price,int quantity, double weight,double fees) {
        super(name,price,quantity);
        this.weight = weight;
        this.fees = fees;
    }
    public double getFees() {
        return fees;
    }
    @Override
    public double getWeight() {
        return weight;
    }
    @Override
    public String getName() {
        return name;
    }
}
