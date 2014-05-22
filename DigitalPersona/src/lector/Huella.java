package lector;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Huella extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Huella frame = new Huella();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
	private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
	DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
	private DPFPTemplate template;
	public static String TEMPLATE_PROPERTY = "template";
	
	
	JLabel HuellaLbl = new JLabel("");
	JTextArea mensajeArea = new JTextArea();
	
	public DPFPTemplate getTemplate() {
		return template;
	}


	public void setTemplate(DPFPTemplate template) {
		this.template = template;
	}
	
	
	public void iniciar(){
		Lector.addDataListener(new DPFPDataAdapter() {	
			
			public void dataAcquired(final DPFPDataEvent e) {
				SwingUtilities.invokeLater(new Runnable() {					
					public void run() {
						EnviarTexto("la huella digital ha sido capturada");						
						procesarCaptura(e.getSample());
						
						
					}
				});											
				
			}
		});
		
		Lector.addSensorListener(new DPFPSensorAdapter(){
			public void fingerTouched(final DPFPSensorEvent e){
				SwingUtilities.invokeLater(new Runnable() {
					
					public void run() {
						EnviarTexto("el dedo ha sido puesto en el lector");
						
					}
				});
			}
			
			public void fingerGone(final DPFPSensorEvent e){
				SwingUtilities.invokeLater(new Runnable() {
					
					public void run() {
						EnviarTexto("el dedo ha sido quitado");
						
					}
				});
			}
			
		});		
		
	}

	public Huella() {
		
		try {
	         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	         } catch (Exception e) {
	         JOptionPane.showMessageDialog(null, "Imposible modificar el tema visual", "Lookandfeel inválido.",
	         JOptionPane.ERROR_MESSAGE);
	         }
		
		iniciar();
		start();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		HuellaLbl.setBounds(10, 11, 200, 120);
		contentPane.add(HuellaLbl);
		
		JButton guardarBtn = new JButton("guardar");
		guardarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					guardarguella(image);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
									
				Reclutador.clear();				
				HuellaLbl.setIcon(null);
				mensajeArea.setText(null);
			}
		});
		guardarBtn.setBounds(10, 214, 89, 23);
		contentPane.add(guardarBtn);
		
		
		mensajeArea.setBounds(145, 142, 338, 95);
		contentPane.add(mensajeArea);
		
		
		
	}
	
	
	
	
	
	public DPFPFeatureSet inscripcion;
	public DPFPFeatureSet verificacion;
	public Image image;
	
	public void procesarCaptura(DPFPSample sample){
		inscripcion = extraercaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
		verificacion = extraercaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
		if(inscripcion!=null)
			try {
				System.out.println("caracteristicas de huella creadas");
				Reclutador.addFeatures(inscripcion);
				
				image = CrearImagenHuella(sample);				
				dibujarHuella(image);
							
				
			} catch (DPFPImageQualityException e) {
				System.err.println("error "+e.getMessage());
			}
		
		
	}
	
	
	public DPFPFeatureSet extraercaracteristicas(DPFPSample sample,DPFPDataPurpose purpose){
		DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
		try {
			return extractor.createFeatureSet(sample, purpose);
			
		} catch (DPFPImageQualityException e) {
			return null;
		}
	}
	
	public  Image CrearImagenHuella(DPFPSample sample) {
		return DPFPGlobal.getSampleConversionFactory().createImage(sample);
	}
	
	public void dibujarHuella(Image image){
		HuellaLbl.setIcon(new ImageIcon(
				image.getScaledInstance(HuellaLbl.getWidth(), HuellaLbl.getHeight(), image.SCALE_DEFAULT)
				
				
				));
		repaint();
	}
	
	public void EstadoHuellas(){
		EnviarTexto("muestra de huellas para guardar template"+Reclutador.getFeaturesNeeded());
	}
	
	public void EnviarTexto(String texto){
		mensajeArea.append(texto);
		
	}
	
	public void start(){
		Lector.startCapture();
		EnviarTexto("utilizando el lector de huella");
	}
	
	
	public void stop(){
		Lector.startCapture();
		EnviarTexto("no se esta utilizando el lector");
	}
	
	public void guardarguella(Image img) throws IOException{
		File outputfile = new File("C:/Users/telsat/Pictures/hola.png");
	    ImageIO.write((RenderedImage) img, "png", outputfile);
		
	}
	
	
}
