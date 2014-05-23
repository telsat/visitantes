package huella;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GuardarHuella {
	
	public String guardarguella(Image img,int cedula) throws IOException{
		File outputfile = new File("C:/Users/telsat/Pictures/"+cedula+".jpg");
	    ImageIO.write((RenderedImage) img, "jpg", outputfile);
	    String ruta = "C:/Users/telsat/Pictures/"+cedula+".jpg";
	    
		return ruta;
		
	}

}
