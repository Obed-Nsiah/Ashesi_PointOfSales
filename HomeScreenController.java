import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import mvc.views.*;
import mvc.models.*;
import mvc.controllers.*;

public class HomeScreenController{
  
  private HomeScreen myHomeScreen;
  private ActionListener actionListener;
  private StockTableView stockTableView;
  
  public HomeScreenController(HomeScreen hs){
    myHomeScreen = hs;  
  }
  
  public void control(){    
    actionListener = new ActionListener() {
      public void actionPerformed (ActionEvent e){
        if (e.getSource() == myHomeScreen.getJBSales()){
          PrintSalesModel   view2= new  PrintSalesModel() ;
          SalesForm   view3= new SalesForm() ; 
          AddSalesController nm = new AddSalesController();
          EditSalesController mycontrol = new  EditSalesController();
          DeleteSalesModel   myModel = new DeleteSalesModel();
          SalesController x = new SalesController(nm,view2,view3,mycontrol,myModel);
          
          x.control();
        }
        else if (e.getSource() == myHomeScreen.getJBStock()) {
          StockTableView tableView = new StockTableView();
          StockTableController tableController = new StockTableController(tableView);
          tableController.control();
        }
      }
    };
    
    myHomeScreen.getJBSales().addActionListener(actionListener);
    myHomeScreen.getJBExit().addActionListener(actionListener);
    myHomeScreen.getJBStock().addActionListener(actionListener);
    myHomeScreen.getJMItemStock().addActionListener(actionListener);
    myHomeScreen.getJMItemSales().addActionListener(actionListener);
  }
  
  public static void main (String args[]){
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run(){
        HomeScreen hs = new HomeScreen();
        hs.setVisible(true);
        HomeScreenController hsc= new HomeScreenController(hs);
        hsc.control();
      }
    });
  }
}