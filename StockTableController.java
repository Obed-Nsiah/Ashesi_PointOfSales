import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.io.*;
import java.sql.*;

public class StockTableController{
  
  private Stock myStock;
  private HashMap myHashMap;
  private StockTableView myStockTableView;
  private ActionListener actionListener;
  private TableModelListener tableModelListener;
  
  
  public StockTableController(StockTableView stockView){
    myStockTableView = stockView; 
  }
  
  public void control(){
    
    tableModelListener = new TableModelListener(){
      public void tableChanged (TableModelEvent e){
        
      }
    };
    
    actionListener = new ActionListener() {
      public void actionPerformed (ActionEvent e){
        
        if (e.getSource() == myStockTableView.getJBAddStock()){
          myStockTableView.getTableModel().addRow(new Object[]{"","","",""});
        }
        else if (e.getSource() == myStockTableView.getJBDeleteStock() && myStockTableView.getStockTable().getRowCount() > 0) {
          if (myStockTableView.getStockTable().getSelectedRow() < myStockTableView.getStockTable().getRowCount() 
                && myStockTableView.getStockTable().getSelectedRow() >= 0)
            myStockTableView.getTableModel().removeRow(myStockTableView.getStockTable().getSelectedRow() );
          else
            myStockTableView.getTableModel().removeRow(myStockTableView.getStockTable().getRowCount() - 1 );
        }
        else if (e.getSource() == myStockTableView.getJBSaveToFile()){
          saveToFile();
        }
        else if (e.getSource() == myStockTableView.getJBLoadFromFile()){
          loadFromFile();
        }
        else if (e.getSource() == myStockTableView.getJBSaveToDatabase()){
          //saveToDB();
        }
        else if (e.getSource() == myStockTableView.getJBLoadFromDatabase()){
          loadFromDB();
        }
      }
    };
    
    myStockTableView.getJBAddStock().addActionListener(actionListener);
    myStockTableView.getJBDeleteStock().addActionListener(actionListener);
    myStockTableView.getJBSaveToFile().addActionListener(actionListener);
    myStockTableView.getJBLoadFromFile().addActionListener(actionListener);
    myStockTableView.getJBLoadFromDatabase().addActionListener(actionListener);
    myStockTableView.getJBSaveToDatabase().addActionListener(actionListener);
  }
  
  public void saveToFile() {
    String productName, productID, price, quantity, totalRevenue;
    JOptionPane myPane = new JOptionPane("Notifier");
    
    File file;
    PrintWriter outputStream;
    JFileChooser myFileChooser = new JFileChooser();
    myFileChooser.setDialogTitle("Save Stock to File");
    myFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
    int result = myFileChooser.showSaveDialog( null );
    
    if ( result == JFileChooser.CANCEL_OPTION )
      return;
    
    file = myFileChooser.getSelectedFile();
    
    if ( file == null || file.getName().equals("") ) {
      JOptionPane.showMessageDialog(myPane, "Invalid File");
    }
    
    else {
      try {        
        if (file.getName().contains(".csv"))
          outputStream = new PrintWriter(file);
        else
          outputStream = new PrintWriter(file + ".csv");
        
        outputStream.println("ProductID \t ProductName \t Price \t Quantity");
        for (int rowNum = 0; rowNum < myStockTableView.getStockTable().getRowCount(); rowNum++) {
          
          productID = String.valueOf(myStockTableView.getStockTable().getValueAt(rowNum, 0));
          productName = String.valueOf(myStockTableView.getStockTable().getValueAt(rowNum, 1));
          price = String.valueOf(myStockTableView.getStockTable().getValueAt(rowNum, 2));
          quantity = String.valueOf(myStockTableView.getStockTable().getValueAt(rowNum, 3));
          totalRevenue = String.valueOf(myStockTableView.getStockTable().getValueAt(rowNum, 4));
          
          outputStream.println(productID + "\t" + productName + "\t" + price + "\t" + quantity);
        }
        
        outputStream.close();
      }
      catch (IOException e) {
        JOptionPane.showMessageDialog(myPane, "Failed to read or write to file");
      }
    }
  }
  
  public void loadFromFile() {
    myStockTableView.getTableModel().setRowCount(0);
    JOptionPane myPane = new JOptionPane("Notifier");
    
    Scanner inputStream;
    String[] data = new String[4];
    String heading;
    File file;
    
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
    
    int result = fileChooser.showOpenDialog(myStockTableView);
    if (result == JFileChooser.CANCEL_OPTION) {
      return;
    }
    
    file = fileChooser.getSelectedFile();
    if ( file == null || file.getName().equals("") ) {
      JOptionPane.showMessageDialog(myPane, "Invalid File");
    }    
    else {
      try {
        inputStream = new Scanner(file);
        
        if (inputStream.hasNextLine())
          heading = inputStream.nextLine();
        
        while (inputStream.hasNextLine()){
          
          for (int i = 0; i < data.length; i++)
            data[i] = inputStream.next();
          myStockTableView.getTableModel().addRow(new String[]{data[0],data[1],data[2],data[3]});
        }
      }
      catch(IOException e) {
        JOptionPane.showMessageDialog(myPane, e.toString());
      }
    }
  }
  
  public static void main (String args[]){
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run(){
        StockTableView view = new StockTableView();
        StockTableController controller = new StockTableController(view);
        controller.control();
      }
    });
  }
  
  public void loadFromDB(){
    
    java.sql.Connection conn = null;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = java.sql.DriverManager.getConnection(
                                                  "jdbc:mysql://localhost/StockDB?user=root&password=");
      
    }
    catch (Exception e) {
      System.out.println(e);
      System.exit(0);
    } 
    System.out.println("Connection established");
    
    try {
      java.sql.Statement s = conn.createStatement();
      java.sql.ResultSet r = s.executeQuery("SELECT * FROM stockDB");
      while(r.next()) {
        myStockTableView.getTableModel().addRow(new String[]{r.getString("productId"),r.getString("productName"), 
          r.getString("productPrice"),r.getString("productQuantity")});
      }
    }
    catch (Exception e) {
      System.out.println(e);
      System.exit(0);
    }
  }
  
//  public void savToDB(){
//    Scanner in=new Scanner(System.in);
// System.out.println("enter a Product Code");
// String code=in.nextLine();
// System.out.println("enter a Name");
// String name=in.nextLine();
// System.out.println("enter a Price");
// String price=in.nextLine(); 
// System.out.println("enter a Manufacturer");
// String manu=in.nextLine();
// 
// try{
//    PreparedStatement p=conn.prepareStatement("Insert Into stockDB set productId=?, productName=?, productPrice=? ,productQuantity =?");
//    p.setString(1, productId);
//    p.setString(2, productName);
//    p.setString(3, productPrice);
//    p.setString(4, productQuantity);
//    p.execute();  //use execute if no results expected back
//    }catch(Exception e){
//        System.out.println("Error"+e.toString());
//        return;        
//    } 
//  }
  
  
  
}
