package webcam;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class Fotos {
	public String tomarfoto(Webcam select){
		
	    Webcam webcam = select;	
	   
	   
	    webcam.open();
		BufferedImage image = webcam.getImage();
	    long epoch = System.currentTimeMillis()/1000;
	    String imageName = String.valueOf(epoch);
	    String ruta = "C:/Users/telsat/Pictures/visitantes/"+imageName+".jpg";
	    try {
			ImageIO.write(image, "JPEG", new File(ruta));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    
	    
	    return ruta;
	    
	    

	}
	
public String fotoequipo(Webcam select){
		
	    Webcam webcam = select;	
	   
	   
	    webcam.open();
		BufferedImage image = webcam.getImage();
	    long epoch = System.currentTimeMillis()/1000;
	    String imageName = String.valueOf(epoch);
	    String ruta = "C:/Users/telsat/Pictures/equipos/"+imageName+".jpg";
	    try {
			ImageIO.write(image, "JPEG", new File(ruta));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    
	    
	    return ruta;
	    
	    

	}

}
