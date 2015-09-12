import java.io.*;
import java.util.*;
import gnu.io.*;

public class SendToArduino {
static Enumeration portList;
static CommPortIdentifier portId;
static SerialPort serialPort;
static OutputStream outputStream;

public static void sendData(String messageString){
  portList = CommPortIdentifier.getPortIdentifiers();
  
  
  while (portList.hasMoreElements()) {
    
    portId = (CommPortIdentifier) portList.nextElement();
    if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
      
      if (portId.getName().equals("COM3")) {
        
        try {
          serialPort = (SerialPort)
            portId.open("SimpleWriteApp", 2000);
        } catch (PortInUseException e) {System.out.println("err");}
        try{
          Thread.sleep(4000);
        }catch(InterruptedException e){
        }
        try {
          outputStream = serialPort.getOutputStream();
        } catch (IOException e) {System.out.println("err1");}
        try {
          serialPort.setSerialPortParams(9600,
                                         SerialPort.DATABITS_8,
                                         SerialPort.STOPBITS_1,
                                         SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {System.out.println("err2");}
        try {
          outputStream.write(messageString.getBytes());
          System.out.println(messageString);
          
          outputStream.close();
          serialPort.close();
          
        } catch (IOException e) {System.out.println("err3");}
      }
    }
  }
}
public static void main(String[] args){
  sendData("Food is any substance[1] consumed to provide nutritional support for the body. It is usually of plant or animal origin, and contains essential nutrients, such as fats, proteins, vitamins, or minerals. The substance is ingested by an organism and assimilated by the organism's cells to provide energy, maintain life, or stimulate growth.Historically, people secured food through two methods: hunting and gathering and agriculture. Today, the majority of the food energy required by the ever increasing population of the world is supplied by the food industry.");
}
}