package mvc.models;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Connection;
//import mvc.views.AddSaleView;
import mvc.views.PrintSalesView;
import java.sql.Connection;
import javax.swing.DefaultListModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wangari
 */
public class PrintSalesModel extends javax.swing.JFrame{
  public  PrintSalesView printedSales;
  //DefaultListModel  salesList;
  Connection conn=null;
  ActionListener actionlistener;
  
  
    public PrintSalesModel() {
    
      printedSales = new PrintSalesView (); 
      //printedSales.setVisible(true);
     //salesList = new DefaultListModel();
    }
    public void printSales(){
               try {
 Class.forName("com.mysql.jdbc.Driver").newInstance();
 conn = java.sql.DriverManager.getConnection(
 "jdbc:mysql://localhost/Sales?user=root&password=");
  System.out.println("its not connected");

   java.sql.Statement s = conn.createStatement();
   java.sql.ResultSet  r = s.executeQuery("SELECT saleId,productName,productPrice,productQuantity FROM AllSales");

  while(r.next()) {
  printedSales.gettheList().addElement( " "+ r.getString("saleId") + " " + r.getString("productName") + " " +
    r.getString("productPrice") + " " +
    r.getString("productQuantity") );
 //System.out.println("its not connected");
  }
  
 }
 catch (Exception e) {
  System.out.println(e);
  System.exit(0);
 }
    }
    
    public void exit(){
     printedSales.setVisible(false);
    }
    
  public void  controlller(){
      actionlistener = new ActionListener () {

          @Override
          public void actionPerformed(ActionEvent e) {
              if(e.getSource() == printedSales.getList() ){
                  printSales();
                  }
              else if(e.getSource()== printedSales.closingForm()){
                exit();  
              }
          }
      };
    printedSales.getList().addActionListener(actionlistener);
    printedSales.closingForm().addActionListener(actionlistener);
  }
//     public static void main(String[]args){
//         PrintSalesModel salesModel = new PrintSalesModel();
//         salesModel.controlller();
//     }   
    }
    
//
