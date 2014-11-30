
/**
 *
 * @author Kojo Gyinaye
 */
public class AddStockModel {
    String prodName;
    double sellPrice;
    double unitPrice;
    int quant;
    
    /**
     * Add a product to stock 
     * @param name the name of new product
     * @param sPrice the selling price of product
     * @param uPrice the unit price of product
     * @param qty the current quantity of product
     */
    
      public AddStockModel(String name,double sPrice, double uPrice,int qty){
        prodName = name;
        sellPrice = sPrice;
        unitPrice = uPrice;
        quant = qty;
}
      /**
       * 
       * @return the details of a product
       */
      public String displayStock(){
          String result = (prodName +" "+ sellPrice +" "+ unitPrice +" "+ quant);
               return result;
      }
    
}
