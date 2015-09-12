import java.awt.*; 
import javax.swing.*; 
import java.awt.image.*; 
import java.io.IOException; 
import javax.imageio.*; 
import java.io.File; 


public class PrintingPanel extends JPanel{ 
  
  //Methods 
  //overides JPanel 
  public void paintComponent(Graphics g){ 
    Graphics2D g2d = (Graphics2D)g; 
    g2d.setFont(new Font("Myriad Hebrew", Font.PLAIN, 25));
    g2d.setColor(Color.WHITE);
    
      g2d.clearRect(0,0,1280,800);
      ImageIcon background = new ImageIcon(getClass().getResource("images/progress.jpg"));
      background.paintIcon(this, g2d, 0, 0); // Background
      g2d.fillRect(30, 70, 450, 360);
    
    
  }
  
  //Constructors 
  public PrintingPanel(){ 
    super();  
  }   
}