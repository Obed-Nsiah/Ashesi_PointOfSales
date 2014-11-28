import java.awt.event.*;

public class AddStockController implements ActionListener{
    private AddStock stock;
    private AddStockModel model;
    
    /**
     * 
     * @param stock
     * @param model 
     */
    public AddStockController(AddStock stock, AddStockModel model){
        this.stock = stock;
        this.model = model;
    }
    
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == stock.getJBSave()){
            String prodName = stock.getProductName().getText();
            double sellPrice = Double.parseDouble(stock.getSellPrice().getText());
            double unitPrice = Double.parseDouble(stock.getUnitPrice().getText());
            int quant = Integer.parseInt(stock.getQuantity().getText());
            
            
            model.prodName = prodName;
            model.sellPrice = sellPrice;
            model.unitPrice = unitPrice;
            model.quant = quant;
        }
        
        else if (e.getSource() == stock.getJBClear()){
            stock.getProductName().setText("");
            stock.getSellPrice().setText("");
            stock.getUnitPrice().setText("");
            stock.getQuantity().setText("");
        }
        else if(e.getSource() == stock.getJBCancel()){
            stock.setVisible(false);
        }        
        
        else
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
