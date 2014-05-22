package lector;


import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;


import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JTextArea;

public class CapturaHuella extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CapturaHuella frame = new CapturaHuella();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	JTextArea mensajeTxtA = new JTextArea();
	
	
	private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
	private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
	DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

	
	private DPFPTemplate template;
	
	
	
	public DPFPTemplate getTemplate() {
		return template;
	}

	public void setTemplate(DPFPTemplate template) {
		DPFPTemplate old = this.template;
		this.template = template;
		firePropertyChange(TEMPLATE_PROPERTY, old, template);
	}

	public static String TEMPLATE_PROPERTY = "template";
	
	protected void iniciar(){
		
		Lector.addDataListener(new DPFPDataAdapter() {	
			
			public void dataAcquired(final DPFPDataEvent e) {
				SwingUtilities.invokeLater(new Runnable() {					
					public void run() {
						EnviarTexto("la huella digital ha sido capturada");
						try {
							ProcesaCaptura(e.getSample());
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						
					}
				});											
				
			}
		});
		
		Lector.addReaderStatusListener(new DPFPReaderStatusAdapter(){
			public void ReaderConnected(final DPFPReaderStatusEvent e){
				SwingUtilities.invokeLater(new Runnable() {					
					public void run() {
						EnviarTexto("el sensor de huella esta activado");
						
					}
				});
			}
			
			public void ReaderDisconnected(final DPFPReaderStatusEvent e){
				SwingUtilities.invokeLater(new Runnable() {
					
					public void run() {
						EnviarTexto("sensor de huella desactivado o desconectado");						
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
						EnviarTexto("el dedo ha sido quitado del lector");						
						
					}
				});
			}
			
		});
		
		Lector.addErrorListener(new DPFPErrorAdapter(){
			public void errorReader(final DPFPErrorEvent e){
				SwingUtilities.invokeLater(new Runnable() {
					
					public void run() {
						EnviarTexto("error "+e.getError());
						
					}
				});
			}
		});
		
	}
	
	JLabel huellaLbl = new JLabel("");
	
	
	public DPFPFeatureSet inscripcion;
	public DPFPFeatureSet verificacion;
	public Image image;
	
	public void ProcesaCaptura(DPFPSample sample) throws IOException{
		inscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
		verificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
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
	
	
	
	public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
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
		huellaLbl.setIcon(new ImageIcon(
				image.getScaledInstance(huellaLbl.getWidth(), huellaLbl.getHeight(), image.SCALE_DEFAULT)
				
				
				));
		repaint();
	}
	
	public void EstadoHuellas(){
		EnviarTexto("muestra de huellas para guardar template"+Reclutador.getFeaturesNeeded());
	}
	
	public void EnviarTexto(String texto){
		mensajeTxtA.append(texto);
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
	
	public CapturaHuella() {
		
		try {
	         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	         } catch (Exception e) {
	         JOptionPane.showMessageDialog(null, "Imposible modificar el tema visual", "Lookandfeel inválido.",
	         JOptionPane.ERROR_MESSAGE);
	         }
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		iniciar();
		start();
		
		
		JButton btnGuardarHuella = new JButton("Guardar huella");
		btnGuardarHuella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					guardarguella(image);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
									
				Reclutador.clear();				
				start();
				huellaLbl.setIcon(null);
				mensajeTxtA.setText("");
				
			}
		});
		btnGuardarHuella.setBounds(10, 159, 138, 23);
		contentPane.add(btnGuardarHuella);
		huellaLbl.setBackground(Color.GRAY);
		huellaLbl.setBounds(39, 11, 294, 119);
		contentPane.add(huellaLbl);
		
		mensajeTxtA.setBounds(392, 114, 270, 137);
		contentPane.add(mensajeTxtA);	
		
	}
}
