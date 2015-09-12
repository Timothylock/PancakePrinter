import java.awt.*; 
import javax.swing.*; 
import java.awt.image.*; 
import java.io.IOException; 
import javax.imageio.*; 
import java.io.File; 



public class drawingPanel extends JPanel{ 
  //properties
  ImageIcon background = new ImageIcon(getClass().getResource("pictures/mainBackground.jpg"));
  
  
  //Methods 
  //overides JPanel 
  public void paintComponent(Graphics g){ 
    Graphics2D g2d = (Graphics2D)g; 
    g2d.setFont(new Font("Myriad Hebrew", Font.PLAIN, 25));
    g2d.setColor(Color.BLACK);
    g2d.clearRect(0,0,1280,800);
    background.paintIcon(this, g2d, 0, 0);
  }
  
  //Constructors 
  public drawingPanel(){ 
    super();  
  }   
}