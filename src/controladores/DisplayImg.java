package controladores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


//esta clase contiene el metodo que se encarga de desplegar la foto de una persona
public class DisplayImg {
	public void Display(String ruta) throws IOException{
		BufferedImage img = ImageIO.read(new File(ruta));
		ImageIcon icon = new ImageIcon(img);
		JLabel foto = new JLabel(icon);
		JOptionPane.showMessageDialog(null, foto);
	}

}
