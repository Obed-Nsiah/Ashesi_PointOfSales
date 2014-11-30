
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class StockTableView extends JFrame{
  
  private JTable stockTable;
  private JButton addStock, deleteStock, saveToFile;
  private JButton loadFromFile, saveToDatabase, jBLoadFromDatabase;
  private DefaultTableModel tableModel;
  private String[] columnNames = new String[] {"Product ID","Product Name", "Price", 
    "Quantity"};
  
  public StockTableView(){
    getContentPane().setLayout(new FlowLayout());
    tableModel = new DefaultTableModel(columnNames, 0);
    stockTable = new JTable(tableModel);
    stockTable.setRowHeight(20);   
    
    JScrollPane scrollPane = new JScrollPane(stockTable);
    stockTable.setPreferredScrollableViewportSize(new Dimension(740,360));
    getContentPane().add(scrollPane);
    
    addStock = new JButton("Add Stock");
    getContentPane().add(addStock);
    
    deleteStock = new JButton("Delete Stock");
    getContentPane().add(deleteStock);
    
    saveToFile = new JButton("Save to File");
    getContentPane().add(saveToFile);
    
    loadFromFile = new JButton("Load from File");
    getContentPane().add(loadFromFile);
        
    saveToDatabase = new JButton("Save to Database");
    getContentPane().add(saveToDatabase);
    
    jBLoadFromDatabase = new JButton("Load from Database");
    getContentPane().add(jBLoadFromDatabase);
//    
//    sort = new JButton("Sort");
//    getContentPane().add(sort);
//    
//    search = new JButton("Search");
//    getContentPane().add(search);
        
    setVisible(true); 
    setSize(780,460);
  }
  
  
  public JButton getJBAddStock(){ return addStock;}
  
  public JButton getJBDeleteStock(){ return deleteStock;}
  
  public JButton getJBSaveToFile(){ return saveToFile;}
  
  public JButton getJBLoadFromFile(){ return loadFromFile;}
  
  public JButton getJBSaveToDatabase(){ return saveToDatabase;}
  
  public JButton getJBLoadFromDatabase(){ return jBLoadFromDatabase;}
//  
//  public JButton getJBSort(){ return sort;}
//  
//  public JButton getJBSearch(){ return search;}
  
  public JTable getStockTable() { return stockTable;}
  
  public DefaultTableModel getTableModel() { return tableModel;}
  
  
  
}