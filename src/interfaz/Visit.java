package interfaz;


import huella.GuardarHuella;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controladores.ControladorEmpleado;
import controladores.ControladorEquipo;
import controladores.ControladorPersona;
import controladores.controladorRegistro;
import to.EquipoTO;
import to.PersonaTO;
import to.RegistroTO;
import webcam.Fotos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.JTable;

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
import com.github.sarxos.webcam.Webcam;


import com.github.sarxos.webcam.WebcamPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTextArea;




@SuppressWarnings("serial")
public class Visit extends JFrame {

	private JPanel contentPane;
	private JTextField personaTxt;
	private JTextField equipoTxt;
	private JTextField cedulaTxt;
	private JTextField apellidoTxt;
	private JTextField sapellidoTxt;
	private JTextField pnombreTxt;
	private JTextField snombreTxt;
	private JTextField sexoTxt;
	private JTextField nacimientoTxt;
	private JTextField sangreTxt;
	private JTextField fabricacion1Txt;
	private JTextField fabricacion2txt;
	private JTextField serialEquipoTxt;
	private JTable table;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visit frame = new Visit();
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
	JLabel huellaLbl = new JLabel("");
	

	
	public DPFPTemplate getTemplate() {
		return template;
	}




	public void setTemplate(DPFPTemplate template) {
		this.template = template;
	}
	
	/*este es el metodo que se llama cuando se inicia el lector de huellas*/
	public void iniciar(){
		Lector.addDataListener(new DPFPDataAdapter() {	
			
			public void dataAcquired(final DPFPDataEvent e) {
				SwingUtilities.invokeLater(new Runnable() {					
					public void run() {
												
						procesarCaptura(e.getSample());
						
						
					}
				});											
				
			}
		});
		
		Lector.addSensorListener(new DPFPSensorAdapter(){
			
			
			
			
		});		
		
	}




	@SuppressWarnings("null")
	public Visit() throws InterruptedException {
		
		try {
	         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	         } catch (Exception e) {
	         JOptionPane.showMessageDialog(null, "Imposible modificar el tema visual", "Lookandfeel inválido.",
	         JOptionPane.ERROR_MESSAGE);
	         }
		iniciar();
		start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1125, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel personaLbl = new JLabel("Persona que Visita");
		personaLbl.setBounds(10, 292, 116, 14);
		contentPane.add(personaLbl);
		
		JLabel equiposLbl = new JLabel("nombre Equipo");
		equiposLbl.setBounds(829, 241, 132, 14);
		contentPane.add(equiposLbl);
		
		personaTxt = new JTextField();
		personaTxt.setBounds(147, 289, 114, 20);
		contentPane.add(personaTxt);
		personaTxt.setColumns(10);
		
		equipoTxt = new JTextField();
		equipoTxt.setBounds(971, 238, 88, 20);
		contentPane.add(equipoTxt);
		equipoTxt.setColumns(10);
		
		final JCheckBox ausente = new JCheckBox("persona no disponible");
		ausente.setBounds(147, 321, 176, 23);
		contentPane.add(ausente);
		
		final JComboBox<String> asuntoCbx = new JComboBox<String>();
		asuntoCbx.setBounds(146, 227, 175, 20);
		contentPane.add(asuntoCbx);
		
		JPanel fotoPanel = new JPanel();
		fotoPanel.setBackground(Color.GRAY);
		fotoPanel.setBounds(484, 54, 230, 148);
		contentPane.add(fotoPanel);
		
		JPanel equipoPanel = new JPanel();
		equipoPanel.setBackground(Color.GRAY);
		equipoPanel.setBounds(829, 54, 230, 148);
		contentPane.add(equipoPanel);
		
		
		
		List<Webcam> lista = Webcam.getWebcams();
		
		final Webcam camEquipo;		
		WebcamPanel panelE = null;			
		camEquipo = (Webcam) lista.get(1);
		//camEquipo.setViewSize(new Dimension(640,480));			
		panelE = new WebcamPanel(camEquipo);				
		panelE.setFillArea(true);
		panelE.setPreferredSize(new Dimension(200,150));		
		equipoPanel.add(panelE);
		
		
		final Webcam camPersona;
		WebcamPanel panelP = null;
		camPersona = (Webcam) lista.get(0);	
		camPersona.setViewSize(new Dimension(640,480));		
		panelP = new WebcamPanel(camPersona);		
		panelP.setFillArea(true);
		panelP.setPreferredSize(new Dimension(200,150));
		fotoPanel.add(panelP);
		
		
		
		
		
		final Fotos fotos = new Fotos();
		
		
		JButton guardarBtn = new JButton("Registrar Ingreso");
		guardarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				GuardarHuella h = new GuardarHuella();
				
				
				//String foto = fotos.tomarfoto(camPersona);
				
				Date date = new Date();
				Object asun = asuntoCbx.getSelectedItem();
				String asunto = asun.toString();	
				boolean aus = ausente.isSelected();
				String ausent;
				if(aus==true){
					ausent = "si";
				}else{
					ausent = "no";
					
				}
				
				String pers = personaTxt.getText();												
				String nombre = pnombreTxt.getText()+" "+snombreTxt.getText();
				String apellido = apellidoTxt.getText()+" "+sapellidoTxt.getText();
				String sexo = sexoTxt.getText();
				String tiposangre = sangreTxt.getText();
				String ced = cedulaTxt.getText();
				
				int cedula = Integer.parseInt(ced);
			
				
				
				
				Date fecha = new Date();
				
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				String nacimiento = nacimientoTxt.getText();
			 
				try {
			 
					fecha = formatter.parse(nacimiento);
					
			 
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				/*se llenan los campos de la tabla persona para luego llamar el metodo
				  que los guarda
				*/
				PersonaTO personaTO = new PersonaTO();
				personaTO.setNombre(nombre);
				//personaTO.setFoto(foto);
				personaTO.setApellido(apellido);
				personaTO.setCedula(cedula);
				personaTO.setFechanacimiento(fecha);
				personaTO.setSexo(sexo);
				personaTO.setTipoSangre(tiposangre);				
				ControladorPersona controlP = new ControladorPersona();
				controlP.guardarPersona(personaTO,camPersona,image);
				controlP.mostrarPersona();
				
				/*se llenan los campos de la tabla registro para luego llamar el metodo
				  que los guarda
				*/
				
				RegistroTO registroTO = new RegistroTO();
				
				registroTO.setCedulapersona(cedula);				
				registroTO.setPersonavisitada(pers);
				registroTO.setFechaingreso(date);	
				registroTO.setAsunto(asunto);
				registroTO.setVisitadaausente(ausent);
				
				controladorRegistro controlReg = new controladorRegistro();
				controlReg.guardarRegistro(registroTO);
				
				Reclutador.clear();				
				huellaLbl.setIcon(null);
				
				
				
			}
		});
		guardarBtn.setBounds(10, 558, 140, 23);
		contentPane.add(guardarBtn);
		
		JButton salidaBtn = new JButton("Registrar Salida");
		salidaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ced = cedulaTxt.getText();
				int cedula = Integer.parseInt(ced);
				Date fecha = new Date();
				
				 
				
				controladorRegistro reg = new controladorRegistro();
				RegistroTO registroTO = new RegistroTO();
				registroTO.setFechasalida(fecha);
				registroTO.setCedulapersona(cedula);
				reg.registrarSalida(registroTO);
				
			}
		});
		salidaBtn.setBounds(197, 558, 126, 23);
		contentPane.add(salidaBtn);
		
		JLabel lblDatosPersona = new JLabel("Datos Persona");
		lblDatosPersona.setBounds(10, 11, 88, 14);
		contentPane.add(lblDatosPersona);
		
		cedulaTxt = new JTextField();
		cedulaTxt.setBounds(10, 74, 126, 20);
		contentPane.add(cedulaTxt);
		cedulaTxt.setColumns(10);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setBounds(146, 74, 126, 20);
		contentPane.add(apellidoTxt);
		apellidoTxt.setColumns(10);
		
		sapellidoTxt = new JTextField();
		sapellidoTxt.setBounds(282, 74, 126, 20);
		contentPane.add(sapellidoTxt);
		sapellidoTxt.setColumns(10);
		
		pnombreTxt = new JTextField();
		pnombreTxt.setBounds(10, 127, 127, 20);
		contentPane.add(pnombreTxt);
		pnombreTxt.setColumns(10);
		
		snombreTxt = new JTextField();
		snombreTxt.setBounds(145, 127, 127, 20);
		contentPane.add(snombreTxt);
		snombreTxt.setColumns(10);
		
		sexoTxt = new JTextField();
		sexoTxt.setBounds(282, 127, 126, 20);
		contentPane.add(sexoTxt);
		sexoTxt.setColumns(10);
		
		nacimientoTxt = new JTextField();
		nacimientoTxt.setBounds(10, 182, 127, 20);
		contentPane.add(nacimientoTxt);
		nacimientoTxt.setColumns(10);
		
		sangreTxt = new JTextField();
		sangreTxt.setBounds(145, 182, 116, 20);
		contentPane.add(sangreTxt);
		sangreTxt.setColumns(10);
		
		fabricacion1Txt = new JTextField();
		fabricacion1Txt.setBounds(285, 182, 53, 20);
		contentPane.add(fabricacion1Txt);
		fabricacion1Txt.setColumns(10);
		
		fabricacion2txt = new JTextField();
		fabricacion2txt.setBounds(355, 182, 53, 20);
		contentPane.add(fabricacion2txt);
		fabricacion2txt.setColumns(10);
		
		
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(10, 54, 46, 14);
		contentPane.add(lblCedula);
		
		JLabel lblNewLabel = new JLabel("Primer Apellido");
		lblNewLabel.setBounds(155, 54, 126, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Segundo Apellido");
		lblNewLabel_1.setBounds(282, 54, 117, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblSegundoNombre = new JLabel("Primer Nombre");
		lblSegundoNombre.setBounds(10, 113, 114, 14);
		contentPane.add(lblSegundoNombre);
		
		JLabel lblSegundoNombre_1 = new JLabel("Segundo Nombre");
		lblSegundoNombre_1.setBounds(145, 113, 116, 14);
		contentPane.add(lblSegundoNombre_1);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(10, 167, 127, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		JLabel lblTipoDeSangre = new JLabel("Tipo de sangre");
		lblTipoDeSangre.setBounds(145, 167, 116, 14);
		contentPane.add(lblTipoDeSangre);
		
		JLabel lblSexo = new JLabel("sexo");
		lblSexo.setBounds(282, 113, 46, 14);
		contentPane.add(lblSexo);
		
		
		huellaLbl.setBounds(484, 227, 230, 114);
		contentPane.add(huellaLbl);
		
		
		
		
		JButton EquipoBtn = new JButton("Registrar Equipo");
		EquipoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistroTO registroTO = new RegistroTO();
				Fotos fotos = new Fotos();
				String fotoEquipo = fotos.fotoequipo(camEquipo);
				String ced = cedulaTxt.getText();
				int cedula = Integer.parseInt(ced);
				registroTO.setCedulapersona(cedula);
				EquipoTO equipo = new EquipoTO();				
				String nombreEquipo = equipoTxt.getText();
				String serialEquipo = serialEquipoTxt.getText();
				equipo.setNombre(nombreEquipo);
				equipo.setSerial(serialEquipo);
				equipo.setFoto(fotoEquipo);
				ControladorEquipo ce = new ControladorEquipo();
				
				ce.RegistraEquipo(equipo, registroTO);
				
				
				
			}
		});
		EquipoBtn.setBounds(829, 340, 140, 23);
		contentPane.add(EquipoBtn);
		
		JLabel lblMotivoDelIngreso = new JLabel("Motivo del Ingreso");
		lblMotivoDelIngreso.setBounds(10, 230, 126, 14);
		contentPane.add(lblMotivoDelIngreso);
		
		JLabel lblSerialEquipo = new JLabel("Serial Equipo");
		lblSerialEquipo.setBounds(829, 276, 132, 14);
		contentPane.add(lblSerialEquipo);
		
		serialEquipoTxt = new JTextField();
		serialEquipoTxt.setBounds(973, 276, 86, 20);
		contentPane.add(serialEquipoTxt);
		serialEquipoTxt.setColumns(10);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int fila = table.getSelectedRow();
				String name = (String) table.getValueAt(fila, 0);				
				String lastname = (String)table.getValueAt(fila, 1);
				personaTxt.setText(name+" "+lastname);
				
			}
		});
		table.setBounds(10, 364, 432, 125);
		contentPane.add(table);
		final String[] columnas = {"nombre","apellido","cargo","extension","area"};
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControladorEmpleado ce = new ControladorEmpleado();
				String[][] datos;
				String persona = personaTxt.getText();
				datos = ce.empleados(persona);
				table.setModel(new DefaultTableModel(datos,columnas));
				
			}
		});
		btnBuscar.setBounds(282, 288, 126, 23);
		contentPane.add(btnBuscar);
		
		
		
		
		
		
		
		asuntoCbx.addItem("Cita");
		asuntoCbx.addItem("recibir mensajeria");
		asuntoCbx.addItem("entregar mensajeria");
		asuntoCbx.addItem("entrevista");	
		
		
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
		huellaLbl.setIcon(new ImageIcon(
				image.getScaledInstance(huellaLbl.getWidth(), huellaLbl.getHeight(), image.SCALE_DEFAULT)
				
				
				));
		repaint();
	}
	
	
	
	
	
	public void start(){
		Lector.startCapture();		
	}
	
	
	public void stop(){
		Lector.startCapture();
		
	}
	
	
}
