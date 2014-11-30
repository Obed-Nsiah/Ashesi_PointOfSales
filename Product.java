
/**
 *
 * @author Kojo Gyinaye
 */
public class Product {
    String prodName;
    double unitPrice;
    double sellPrice;    
    int quant;
    
    public Product(String name, double uPrice, double sPrice,int qty){
        this.prodName = name;
        this.sellPrice = sPrice;
        this.unitPrice = uPrice;
        this.quant = qty;
        
    }
}
