package mvc.controllers;

import mvc.views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Obed Kobina Nsiah
 */
public class EditStockController extends javax.swing.JFrame {
Connection conn=null;
public EditStockView myStockView ;

private ActionListener actionlistener;
 
    public EditStockController() {
    
      myStockView = new EditStockView(); 
      //myStockView.setVisible(true);
    }


    public void controller(){
         int  saleId=Integer.parseInt(myStockView.getEditId().getText());
         try {
             
       Class.forName("com.mysql.jdbc.Driver").newInstance();
 conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost/Sales?user=root&password=");
 System.out.println("connected to database");
        PreparedStatement p =conn.prepareStatement("SELECT * FROM AllSales WHERE saleId = "+saleId+";");


  java.sql.ResultSet r = p.executeQuery ();
  while(r.next()) {
    myStockView.getEditId().setText(r.getString("saleId")) ;
    myStockView.getProductName().setText(r.getString("productName"));
   myStockView.getProductPrice().setText( r.getString("productPrice")) ;
   myStockView.getProductQuantity().setText( r.getString("productQuantity")) ;
   
  }
}
       catch (Exception e) {
  JOptionPane.showMessageDialog(null,e);
        }
    }
    
 public void controlSave(){

        int  saleId =Integer.parseInt(myStockView.getEditId().getText());      
        String productName = myStockView.getProductName().getText();
        int productPrice = Integer.parseInt(myStockView.getProductPrice().getText());
        int productQuantity = Integer.parseInt(myStockView.getProductQuantity().getText());
  try{
       Class.forName("com.mysql.jdbc.Driver").newInstance();
 conn = java.sql.DriverManager.getConnection(
 "jdbc:mysql://localhost/Sales?user=root&password=");
 
    PreparedStatement p=conn.prepareStatement("UPDATE AllSales set saleId=?, productName=? ,productPrice =?,productQuantity=? where saleId like '%"+saleId+ "%';");
    p.setInt(1, saleId);
    p.setString(2, productName);
    p.setInt(3,productPrice );
    p.setInt(4,productQuantity);
    p.execute(); 
    p.close();
    //use execute if no results expected back
    }catch(Exception e){
        System.out.println("Error"+e.toString());
        //return;        
    }
         }
 public void closeForm(){
     //System.exit(0);
     myStockView.setVisible(false);
 }
   
   public void theController(){
       actionlistener = new ActionListener (){
         @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==myStockView.savingData()   ){
            controlSave();
        }
       else  if(e.getSource()==myStockView.theEdit()){
       controller();
        }
       else  if(e.getSource()==myStockView.closingForm()){
       closeForm();
        }
         
    }
       };
        myStockView.theEdit().addActionListener(actionlistener);
     myStockView.savingData().addActionListener(actionlistener);
        
        
    }
public static void main(String[]args){
    EditStockController mycontrol = new EditStockController();
    mycontrol.theController();
}
       
   }

  
         
  //  }
//}