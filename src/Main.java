import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static  void Checkout(Customer customer, ArrayList<Pair> cart){
        ArrayList<Pair> shippableProducts = new ArrayList<Pair>();
        if(cart.isEmpty()){
            throw new EmptyCardException("card is empty");
        }
        for(Pair p:cart){
            if(p.product instanceof ExpirableProduct){
                if(((ExpirableProduct) p.product).isExpired()){
                    throw new ProductEpiredException("Product is expired");
                }
            }
            if(p.product instanceof ExpirableShippableProduct){
                if(((ExpirableShippableProduct) p.product).isExpired()){
                    throw new ProductEpiredException("Product is expired");
                }
            }
            if(p.product.getQuantity() < p.quantity){
                throw new InsufficientQuantityException("product quantity is out of stock");
            }
            if(p.product instanceof ShippableProduct){
                shippableProducts.add(p);
            }
            p.product.updateQuantity(-p.quantity);
        }
        ShippingService.Shipment(shippableProducts);
        System.out.println();
        System.out.println("** Checkout receipt **");
        double shippingfees = 0,subtotal = 0;
        for(Pair p:cart){
            System.out.printf("%dX %s\t%.1f\n",p.quantity,p.product.getName(),p.quantity*p.product.getPrice());
            subtotal+=p.product.getPrice()*p.quantity;
            if(p.product instanceof ShippableProduct){
                shippingfees+=((ShippableProduct)p.product).getFees()*p.quantity;
            }
        }
        System.out.println("Subtotal\t"+subtotal);
        System.out.println("Shipping\t"+shippingfees);
        double amount = shippingfees+subtotal;
        System.out.printf("Amount %.1f\t",amount);
    }
    public static void main(String[] args) {
        Customer customer = new Customer(100);
        ArrayList<Pair> cart = new ArrayList<>();
        LocalDate now = LocalDate.now();
        ShippableProduct Chesse = new ExpirableShippableProduct("Cheese",100,3,now,200,10);
        ShippableProduct Biscuits = new ShippableProduct("Biscuits",150,3,700,10);
        cart.add(new Pair(Chesse,2));
        cart.add(new Pair(Biscuits,1));
        Checkout(customer,cart);
    }
}