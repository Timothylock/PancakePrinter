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
	DrawingPanel drawingPanel;   // Make Drawing Panel
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

	//////////////////////////
	//   Action Listeners   //
	//////////////////////////
	public void actionPerformed(ActionEvent evt){
	    if(evt.getSource() == clearButton){  // Clear the Screen
	    	// CLEAR THE SCREEN
	    	drawData = null; // Clear the data storage
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
		mainWindow.setSize(1280, 800);
		mainWindow.setResizable(false);
	}

	public static void main(String[] args){
    	PancakePrinter pancake = new PancakePrinter(); 
  	}
}