package webcam;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;


/*esta clase se encarga de las web cam y tomar fotos*/
public class Fotos {
	
	/*este metodo captura la foto de la persona y la guarda
	 como imagen con extension PNG con un nombre unico que nunca se va a repetir
	 y devuelve la ruta de la imagen para luego ser guardada en la tabla persona*/
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
	
	

/*este metodo captura la foto de un equipo y la guarda
 como imagen con extension PNG con un nombre unico que nunca se va a repetir
 y devuelve la ruta de la imagen para luego ser guardada en la tabla equipo*/	
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
