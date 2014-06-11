package controladores;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


//la clase contiene el metodo que captura la pantalla y la guarda como una foto
public class Pantalla {
	
public void capturar(){
		
		try {
			Toolkit tool = Toolkit.getDefaultToolkit();
			Dimension d = tool.getScreenSize();
			Rectangle rect = new Rectangle(d);
			Robot robot = new Robot();
			Thread.sleep(500);
			long epoch = System.currentTimeMillis()/1000;
		    String imageName = String.valueOf(epoch);
			File f = new File("C:/Users/telsat/Pictures/"+imageName+".png");
			BufferedImage img = robot.createScreenCapture(rect);
			ImageIO.write(img,"png",f);
			tool.beep();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
