import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font.*;
import javax.swing.text.*;
import java.net.*;
import java.awt.Cursor.*;
import java.awt.image.*; 

public class PancakePrinter implements ActionListener, MouseListener, MouseMotionListener{
 //////////////////////////////////
 //   Properties and Variables   //
 //////////////////////////////////

 // Main window
 JFrame mainWindow;
 DrawingPanel mainPanel;   // Make Drawing Panel
 Timer frameTimer;

 // Drawing panel components
 JButton pencilButton;
 JButton clearButton;
 JTextField serverIP;
 JTextField serverPort;
 JTextField namePort;
 JButton lightShadeButton;
 JButton darkShadeButton;
 JButton finishButton;

 // Variables
 String[] drawData = {""};
 int currentShade = 0;

 //////////////////////////
 //   Action Listeners   //
 //////////////////////////
 public void actionPerformed(ActionEvent evt){
     if(evt.getSource() == clearButton){  // Clear the Screen
       // CLEAR THE SCREEN
       drawData = null; // Clear the data storage
       System.out.println("clearing screen + score");
     }else if(evt.getSource() == lightShadeButton){
       currentShade = 1;
       System.out.println("setting shading to light");
     }else if(evt.getSource() == darkShadeButton){
       currentShade = 2;
       System.out.println("setting shading to dark");
     }else if(evt.getSource() == finishButton){
       // SEND DATA TO ARDUINO
       System.out.println("sending data to Arduino");
       JOptionPane.showMessageDialog(mainWindow, "Arduino is now printing. PLACEFILLER FOR LIVE VIEW");
     }
 }

 public void mouseDragged(MouseEvent evt){
 }

 public void mouseMoved(MouseEvent evt){
  /*
  thePanel.crosshairX = evt.getX() - 25;
  if(evt.getY() > 600){
      thePanel.crosshairY =  600 - 25;
  }else{
      thePanel.crosshairY = evt.getY() - 25;
     }*/
 }
   
 public void mousePressed(MouseEvent evt){
 }
    
 public void mouseExited(MouseEvent evt){
 }
    
 public void mouseEntered(MouseEvent evt){
 }
    
 public void mouseClicked(MouseEvent evt){
 }
    
 public void mouseReleased(MouseEvent evt){
 }

 public PancakePrinter(){
  // Setup Main Window
  mainWindow = new JFrame("Pancake Printer");
  mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  mainWindow.setSize(800, 500);
  mainWindow.setResizable(false);
  mainWindow.setVisible(true);
  
  // Prestart Panel
  mainPanel = new DrawingPanel();
  mainPanel.setLayout(null);
  
  // Setup the buttons
  pencilButton = new JButton("Pencil");
  pencilButton.addActionListener(this);
  pencilButton.setSize(100, 100);
  pencilButton.setLocation(500, 70);
  mainPanel.add(pencilButton);
  
  clearButton = new JButton("Clear");
  clearButton.addActionListener(this);
  clearButton.setSize(100, 100);
  clearButton.setLocation(620, 70);
  mainPanel.add(clearButton);
  
  lightShadeButton = new JButton("Light Shade");
  lightShadeButton.addActionListener(this);
  lightShadeButton.setSize(220, 50);
  lightShadeButton.setLocation(500, 200);
  mainPanel.add(lightShadeButton);
  
  darkShadeButton = new JButton("Light Shade");
  darkShadeButton.addActionListener(this);
  darkShadeButton.setSize(220, 50);
  darkShadeButton.setLocation(500, 260);
  mainPanel.add(darkShadeButton);
  
  finishButton = new JButton("Send To Printer");
  finishButton.addActionListener(this);
  finishButton.setSize(220, 100);
  finishButton.setLocation(500, 330);
  mainPanel.add(finishButton);
  
  // Set the start Panel
  mainWindow.setContentPane(mainPanel);
 }

 public static void main(String[] args){
     PancakePrinter pancake = new PancakePrinter(); 
   }
}