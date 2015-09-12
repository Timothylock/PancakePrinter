import java.awt.*; 
import javax.swing.*; 
import java.awt.image.*; 
import java.io.IOException; 
import javax.imageio.*; 
import java.io.File; 


public class DrawingPanel extends JPanel{ 
  //properties
  //ImageIcon background = new ImageIcon(getClass().getResource("pictures/mainBackground.jpg"));
  
  //Variables
  boolean reset = true;
  int plotX = -100;
  int plotY = -100;
  
  //Methods 
  //overides JPanel 
  public void paintComponent(Graphics g){ 
    Graphics2D g2d = (Graphics2D)g; 
    g2d.setFont(new Font("Myriad Hebrew", Font.PLAIN, 25));
    g2d.setColor(Color.WHITE);
    
    if (reset == true){
      g2d.clearRect(0,0,1280,800);
      g2d.fillRect(30, 70, 450, 360);
      reset = false;
    }
    
    g2d.setColor(Color.BLACK);
    g2d.fillOval(plotX, plotY, 6, 6);
    //background.paintIcon(this, g2d, 0, 0);
  }
  
  //Constructors 
  public DrawingPanel(){ 
    super();  
  }   
}