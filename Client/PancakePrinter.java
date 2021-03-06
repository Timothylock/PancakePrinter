import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.Font.*;
import java.awt.Cursor.*;
import java.awt.image.*; 
import java.util.*;
import java.io.File;
import java.io.PrintWriter;

public class PancakePrinter implements ActionListener, MouseListener, MouseMotionListener{
 //////////////////////////////////
 //   Properties and Variables   //
 //////////////////////////////////

 // Main window
 Timer frameTimer;
 JFrame mainWindow;
 DrawingPanel mainPanel;   // Make Drawing Panel
 PrintingPanel printingPanel; // Printing Panel

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
 int i;

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
         dataString += s + ",";
       }
       dataString += "SWITCH,";
       for (String s : drawDataLight){
         dataString += s + ",";
       }
       dataString = dataString.substring(0, dataString.length() - 1);
       //System.out.println(dataString);
       mainPanel.printing = true;
       mainPanel.repaint();
       try{
         PrintWriter writer = new PrintWriter("data.txt", "UTF-8");
         writer.println(dataString);
         writer.close();
         Process p = Runtime.getRuntime().exec("cmd.exe /c start python comm/ProcessAndSend.py "); // Use Python to comm
         System.out.println(dataString);
       }catch (Exception e){
         JOptionPane.showMessageDialog(mainWindow, "Error.");
       }
       File varTmpDir = new File("finish.f");  // Check if its done by looking for a file. Then delete.
       boolean exists = varTmpDir.exists();
       while (exists == false){
         varTmpDir = new File("finish.f");
         exists = varTmpDir.exists();
       }
       System.out.println("done");
       varTmpDir.delete();
       mainPanel.reset = true;
       mainPanel.repaint();
     }
     if (evt.getSource() == frameTimer){
       mainPanel.repaint();
     }
 }

 public void mouseDragged(MouseEvent evt){
   if ((evt.getX() >= 30 && evt.getX() <=475) && (evt.getY() >= 70 && evt.getY() <= 425)){
     mainPanel.shade = this.currentShade;
     mainPanel.plotX = evt.getX();
     mainPanel.plotY = evt.getY();
     mainPanel.repaint(); // refresh the screen after plotting new points
     if (currentShade == 2){
       drawDataDark.add(((evt.getX()-30)*100/475 + "|" + (evt.getY() -70)*250/425 + "|1"));
     }else if (currentShade == 1){
       drawDataLight.add(((evt.getX()-30)*100/475 + "|" + (evt.getY()-70)*250/425 + "|1"));
     }else{
       JOptionPane.showMessageDialog(mainWindow, "Please select a shade first!");
       mainPanel.plotX = -100;
       mainPanel.plotY = -100;  // Move position off screen then reset
       mainPanel.reset = true;
       mainPanel.repaint();
       System.out.println("No shade selected");
     }
     System.out.println(((evt.getX()-30)*100/475 + "|" + (evt.getY()-70)*250/425 + "|1"));
   }
 }

 public void mouseMoved(MouseEvent evt){
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
   if (currentShade == 2){
     for(i=drawDataDark.size() - 20; i<drawDataDark.size(); i++){
       drawDataDark.set (i, drawDataDark.get(i).substring(0, drawDataDark.get(i).length() - 1) + "0");
     }
   }else if (currentShade == 1){
     for(i=drawDataLight.size() - 20; i<drawDataLight.size(); i++){
       drawDataLight.set (i, drawDataLight.get(i).substring(0, drawDataLight.get(i).length() - 1) + "0");
     }
   }
   System.out.println("released");
 }

 public PancakePrinter(){
  // Setup Main Window
  mainWindow = new JFrame("Pancake Printer");
  mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  mainWindow.setSize(760, 480);
  mainWindow.setResizable(false);
  mainWindow.setAlwaysOnTop( true );
  mainWindow.setVisible(true);
  
  // Main Panel
  mainPanel = new DrawingPanel();
  mainPanel.setLayout(null);
  mainPanel.addMouseListener(this);
  mainPanel.addMouseMotionListener(this);
  
  // Setup the buttons
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
  mainPanel.repaint();
  
  frameTimer = new Timer(1, this);
  frameTimer.start();
 }

 public static void main(String[] args){
     PancakePrinter pancake = new PancakePrinter(); 
   }
}