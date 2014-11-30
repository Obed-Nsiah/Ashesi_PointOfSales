
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class StockController{
  
  // private Stock myStock;
  private StockView myStockView;
  //private StockView myStockView;
  private ActionListener actionListener;
 // private TableModelListener tableModelListener;

//  
  public StockController(StockView stockView){
    //myStock = stock;
    myStockView = stockView; 
    //myStockView = stockview;
  }
//  
  public void control(){
//    
//    System.out.println("Control");
//    tableModelListener = new TableModelListener(){
//      public void tableChanged (TableModelEvent e){
//        
//      }
//    };
//    
    actionListener = new ActionListener() {
//      //System.out.println("Shout out");
      public void actionPerformed (ActionEvent e){
        
        if (e.getSource() == myStockView.getJBStockTable()){
          StockTableView tableView = new StockTableView();
          StockTableController tableController = new StockTableController(tableView);
          tableController.control();
        }
        else if (e.getSource() == myStockView.getJBClose()){
          System.exit(0);
        }
        else if (e.getSource() == myStockView.getJBAdd()){
          AddStockView myAdd = new AddStockView();
          myAdd.setVisible(true);
        }
      }
    };
    
    myStockView.getJBStockTable().addActionListener(actionListener);
  }
}
//        System.out.println("Hello");
//        // int row = myStockTableView.getStockTable().getSelectedRow();
//        //int column = myStockTableView.getStockTable().getSelectedColumn();
//        
//        if (e.getSource() == myStockTableView.getJBAddStock()){
//          myStockTableView.getTableModel().addRow(new Object[]{"","","",""});
//          System.out.println("Clicked");
//        }
//        else if (e.getSource() == myStockTableView.getJBDeleteStock() && myStockTableView.getStockTable().getRowCount() > 0) {
//          if (myStockTableView.getStockTable().getSelectedRow() < myStockTableView.getStockTable().getRowCount() 
//                && myStockTableView.getStockTable().getSelectedRow() >= 0)
//            myStockTableView.getTableModel().removeRow(myStockTableView.getStockTable().getSelectedRow() );
//          else
//            myStockTableView.getTableModel().removeRow(myStockTableView.getStockTable().getRowCount() - 1 );
//        }
//        else if (e.getSource() == myStockTableView.getJBSaveToFile()){
//          saveToFile();
//        }
//        else if (e.getSource() == myStockTableView.getJBLoadFromFile()){
//          loadFromFile();
//        }
//      }
//    };
//    
//    myStockTableView.getJBAddStock().addActionListener(actionListener);
//    myStockTableView.getJBDeleteStock().addActionListener(actionListener);
//    myStockTableView.getJBSaveToFile().addActionListener(actionListener);
//    myStockTableView.getJBLoadFromFile().addActionListener(actionListener);
//  }
//  
//  public void saveToFile() {
//    String productName, productID, price, quantity, totalRevenue;
//    JOptionPane myPane = new JOptionPane("Notifier");
//    
//    File file;
//    PrintWriter outputStream;
//    JFileChooser myFileChooser = new JFileChooser();
//    myFileChooser.setDialogTitle("Save Stock to File");
//    myFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
//    int result = myFileChooser.showSaveDialog( null );
//    
//    if ( result == JFileChooser.CANCEL_OPTION )
//      return;
//    
//    file = myFileChooser.getSelectedFile();
//    
//    if ( file == null || file.getName().equals("") ) {
//      JOptionPane.showMessageDialog(myPane, "Invalid File");
//    }
//    
//    else {
//      try {        
//        if (file.getName().contains(".csv"))
//               outputStream = new PrintWriter(file);
//          else
//               outputStream = new PrintWriter(file + ".csv");
//        
//        outputStream.println("ProductID \t ProductName \t Price \t Quantity");
//        for (int rowNum = 0; rowNum < myStockTableView.getStockTable().getRowCount(); rowNum++) {
//          
//          productID = String.valueOf(myStockTableView.getStockTable().getValueAt(rowNum, 0));
//          productName = String.valueOf(myStockTableView.getStockTable().getValueAt(rowNum, 1));
//          price = String.valueOf(myStockTableView.getStockTable().getValueAt(rowNum, 2));
//          quantity = String.valueOf(myStockTableView.getStockTable().getValueAt(rowNum, 3));
//          totalRevenue = String.valueOf(myStockTableView.getStockTable().getValueAt(rowNum, 4));
//          
//          outputStream.println(productID + "\t" + productName + "\t" + price + "\t" + quantity);
//        }
//        
//        outputStream.close();
//      }
//      catch (IOException e) {
//        JOptionPane.showMessageDialog(myPane, "Failed to read or write to file");
//      }
//    }
//  }
//  
//  public void loadFromFile() {
//    myStockTableView.getTableModel().setRowCount(0);
//    JOptionPane myPane = new JOptionPane("Notifier");
//      
//    Scanner inputStream;
//    String[] data = new String[4];
//    String heading;
//    File file;
//    
//    JFileChooser fileChooser = new JFileChooser();
//    fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
//    
//    int result = fileChooser.showOpenDialog(myStockTableView);
//    if (result == JFileChooser.CANCEL_OPTION) {
//      System.out.println("Returning");
//      return;
//    }
//    
//    file = fileChooser.getSelectedFile();
//    if ( file == null || file.getName().equals("") ) {
//            System.out.println("Nothing");
//      JOptionPane.showMessageDialog(myPane, "Invalid File");
//    }    
//    else {
//     try {
//        inputStream = new Scanner(file);
//              System.out.println("Right here");
//        
//       if (inputStream.hasNextLine())
//         heading = inputStream.nextLine();
//        
//       while (inputStream.hasNextLine()){
//          
//          for (int i = 0; i < data.length; i++)
//            data[i] = inputStream.next();
//          myStockTableView.getTableModel().addRow(new String[]{data[0],data[1],data[2],data[3]});
//        }
//      }
//      catch(IOException e) {
//        JOptionPane.showMessageDialog(myPane, e.toString());
//      }
//    }
//  }
//  
//  public static void main (String args[]){
//    SwingUtilities.invokeLater(new Runnable() {
//      @Override
//      public void run(){
//        StockTableView view = new StockTableView();
//       // StockView v = new StockView();
//        StockTableController controller = new StockTableController(view);
//        controller.control();
//      }
//    });
//  }
//}