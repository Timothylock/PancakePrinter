import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font.*;
import javax.swing.text.*;
import java.net.*;
import java.awt.Cursor.*;
import java.awt.image.*; 
import java.util.*;

public class PancakePrinter implements ActionListener, MouseListener, MouseMotionListener{
 //////////////////////////////////
 //   Properties and Variables   //
 //////////////////////////////////

 // Main window
 JFrame mainWindow;
 DrawingPanel mainPanel;   // Make Drawing Panel

 // Drawing panel components
 JButton pencilButton;
 JButton clearButton;
 JTextField serverIP;
 JTextField serverPort;
 JTextField namePort;
 JButton lightShadeButton;
 JButton darkShadeButton;
 JButton finishButton;
 
 // Menu
 JMenuBar menuBar;

 // Variables
 ArrayList<String> drawDataDark = new ArrayList<String>();
 ArrayList<String> drawDataLight = new ArrayList<String>();
 String dataString = "";
 int currentShade = 0;

 //////////////////////////
 //   Action Listeners   //
 //////////////////////////
 public void actionPerformed(ActionEvent evt){
     if(evt.getSource() == clearButton){  // Clear the Screen
       mainPanel.plotX = -100;
       mainPanel.plotY = -100;  // Move position off screen then reset
       mainPanel.reset = true;
       mainPanel.repaint();
       drawDataLight.clear(); // Clear the data storage
       drawDataDark.clear(); // Clear the data storage
       System.out.println("clearing screen + data");
     }else if(evt.getSource() == lightShadeButton){
       currentShade = 1;
       System.out.println("setting shading to light");
     }else if(evt.getSource() == darkShadeButton){
       currentShade = 2;
       System.out.println("setting shading to dark");
     }else if(evt.getSource() == finishButton){
       // SEND DATA TO ARDUINO
       System.out.println("sending data to Arduino");
       dataString = ""; // Clear String
       for (String s : drawDataDark){
         dataString += s + " ";
       }
       for (String s : drawDataLight){
         dataString += s + " ";
       }
       System.out.println(dataString);
       SendToArduino.sendData(dataString);
       JOptionPane.showMessageDialog(mainWindow, "Arduino is now printing. PLACEFILLER FOR LIVE VIEW");

     }
 }

 public void mouseDragged(MouseEvent evt){
   if ((evt.getX() >= 30 && evt.getX() <=475) && (evt.getY() >= 70 && evt.getY() <= 425)){
     mainPanel.shade = this.currentShade;
     mainPanel.plotX = evt.getX();
     mainPanel.plotY = evt.getY();
     mainPanel.repaint(); // refresh the screen after plotting new points
     if (currentShade == 2){
       drawDataDark.add((evt.getX() + "|" + evt.getY() + "|" + currentShade));
     }else if (currentShade == 1){
       drawDataLight.add((evt.getX() + "|" + evt.getY() + "|" + currentShade));
     }else{
       JOptionPane.showMessageDialog(mainWindow, "Please select a shade first!");
       mainPanel.plotX = -100;
       mainPanel.plotY = -100;  // Move position off screen then reset
       mainPanel.reset = true;
       mainPanel.repaint();
       System.out.println("No shade selected");
     }
     System.out.println("mouse dragged " + evt.getX() + "x" + evt.getY());
   }
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
  mainWindow.setSize(760, 480);
  mainWindow.setResizable(false);
  mainWindow.setVisible(true);
  
  // Prestart Panel
  mainPanel = new DrawingPanel();
  mainPanel.setLayout(null);
  mainPanel.addMouseListener(this);
  mainPanel.addMouseMotionListener(this);
  
  // Setup the buttons
  pencilButton = new JButton("Pencil");
  pencilButton.addActionListener(this);
  pencilButton.setSize(300, 50);
  pencilButton.setLocation(400, 110);
  //mainPanel.add(pencilButton);
  
  clearButton = new JButton();
  clearButton.addActionListener(this);
  clearButton.setSize(220, 50);
  clearButton.setLocation(500, 107);
  clearButton.setOpaque(false);
  clearButton.setContentAreaFilled(false);
  clearButton.setBorderPainted(false);
  mainPanel.add(clearButton);
  
  lightShadeButton = new JButton();
  lightShadeButton.addActionListener(this);
  lightShadeButton.setSize(100, 50);
  lightShadeButton.setLocation(500, 231);
  lightShadeButton.setOpaque(false);
  lightShadeButton.setContentAreaFilled(false);
  lightShadeButton.setBorderPainted(false);
  mainPanel.add(lightShadeButton);
  
  darkShadeButton = new JButton();
  darkShadeButton.addActionListener(this);
  darkShadeButton.setSize(100, 50);
  darkShadeButton.setLocation(620, 231);
  darkShadeButton.setOpaque(false);
  darkShadeButton.setContentAreaFilled(false);
  darkShadeButton.setBorderPainted(false);
  mainPanel.add(darkShadeButton);
  
  finishButton = new JButton();
  finishButton.addActionListener(this);
  finishButton.setSize(220, 100);
  finishButton.setLocation(500, 330);
  finishButton.setOpaque(false);
  finishButton.setContentAreaFilled(false);
  finishButton.setBorderPainted(false);
  mainPanel.add(finishButton);
  
  // Set the start Panel
  mainWindow.setContentPane(mainPanel);
  mainPanel.reset = true;
  mainPanel.repaint();
 }

 public static void main(String[] args){
     PancakePrinter pancake = new PancakePrinter(); 
   }
}