import java.util.ArrayList;

public class ShippingService {
    public static void Shipment(ArrayList<Pair> shippableProducts){
        System.out.println("** Shipment notice **");
        double total = 0;
        for(Pair p: shippableProducts){
            ShippableProduct s = (ShippableProduct)p.product;
            System.out.printf("%dX %s\t%.1fg\n",p.quantity,s.getName(),s.getWeight()*p.quantity);
            total+=s.getWeight()*p.quantity;
        }
        total/=1000;
        System.out.printf("Total package weight %.1fKg\n",total);
    }
}
