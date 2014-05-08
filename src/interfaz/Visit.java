package interfaz;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import controladores.ControladorPersona;
import controladores.controladorRegistro;
import to.PersonaTO;
import to.RegistroTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JComboBox;




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

	/**
	 * Create the frame.
	 */
	public Visit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel personaLbl = new JLabel("Persona que Visita");
		personaLbl.setBounds(26, 248, 116, 14);
		contentPane.add(personaLbl);
		
		JLabel equiposLbl = new JLabel("Equipos");
		equiposLbl.setBounds(26, 288, 46, 14);
		contentPane.add(equiposLbl);
		
		personaTxt = new JTextField();
		personaTxt.setBounds(168, 245, 196, 20);
		contentPane.add(personaTxt);
		personaTxt.setColumns(10);
		
		equipoTxt = new JTextField();
		equipoTxt.setBounds(168, 285, 196, 20);
		contentPane.add(equipoTxt);
		equipoTxt.setColumns(10);
		
		final JCheckBox ausente = new JCheckBox("persona no disponible");
		ausente.setBounds(435, 329, 154, 23);
		contentPane.add(ausente);
		
		final JComboBox<String> asuntoCbx = new JComboBox();
		asuntoCbx.setBounds(396, 245, 175, 20);
		contentPane.add(asuntoCbx);
		
		JButton guardarBtn = new JButton("Registrar Ingreso");
		guardarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				String equipos = equipoTxt.getText();									
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
				personaTO.setApellido(apellido);
				personaTO.setCedula(cedula);
				personaTO.setFechanacimiento(fecha);
				personaTO.setSexo(sexo);
				personaTO.setTipoSangre(tiposangre);
				ControladorPersona controlP = new ControladorPersona();
				controlP.guardarPersona(personaTO);
				controlP.mostrarPersona();
				
				/*se llenan los campos de la tabla registro para luego llamar el metodo
				  que los guarda
				*/
				
				RegistroTO registroTO = new RegistroTO();
				
				registroTO.setCedulapersona(cedula);
				registroTO.setEquipos(equipos);
				registroTO.setPersonavisitada(pers);
				registroTO.setFechaingreso(date);	
				registroTO.setAsunto(asunto);
				registroTO.setVisitadaausente(ausent);
				
				controladorRegistro controlReg = new controladorRegistro();
				controlReg.guardarRegistro(registroTO);
				
				
				
				
			}
		});
		guardarBtn.setBounds(26, 329, 140, 23);
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
		salidaBtn.setBounds(233, 329, 126, 23);
		contentPane.add(salidaBtn);
		
		JLabel lblDatosPersona = new JLabel("Datos Persona");
		lblDatosPersona.setBounds(10, 11, 88, 14);
		contentPane.add(lblDatosPersona);
		
		cedulaTxt = new JTextField();
		cedulaTxt.setBounds(12, 74, 125, 20);
		contentPane.add(cedulaTxt);
		cedulaTxt.setColumns(10);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setBounds(163, 74, 124, 20);
		contentPane.add(apellidoTxt);
		apellidoTxt.setColumns(10);
		
		sapellidoTxt = new JTextField();
		sapellidoTxt.setBounds(322, 74, 117, 20);
		contentPane.add(sapellidoTxt);
		sapellidoTxt.setColumns(10);
		
		pnombreTxt = new JTextField();
		pnombreTxt.setBounds(10, 138, 127, 20);
		contentPane.add(pnombreTxt);
		pnombreTxt.setColumns(10);
		
		snombreTxt = new JTextField();
		snombreTxt.setBounds(163, 138, 116, 20);
		contentPane.add(snombreTxt);
		snombreTxt.setColumns(10);
		
		sexoTxt = new JTextField();
		sexoTxt.setBounds(323, 138, 116, 20);
		contentPane.add(sexoTxt);
		sexoTxt.setColumns(10);
		
		nacimientoTxt = new JTextField();
		nacimientoTxt.setBounds(10, 201, 127, 20);
		contentPane.add(nacimientoTxt);
		nacimientoTxt.setColumns(10);
		
		sangreTxt = new JTextField();
		sangreTxt.setBounds(163, 201, 116, 20);
		contentPane.add(sangreTxt);
		sangreTxt.setColumns(10);
		
		fabricacion1Txt = new JTextField();
		fabricacion1Txt.setBounds(297, 201, 89, 20);
		contentPane.add(fabricacion1Txt);
		fabricacion1Txt.setColumns(10);
		
		fabricacion2txt = new JTextField();
		fabricacion2txt.setBounds(396, 201, 86, 20);
		contentPane.add(fabricacion2txt);
		fabricacion2txt.setColumns(10);
		
		
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(10, 54, 46, 14);
		contentPane.add(lblCedula);
		
		JLabel lblNewLabel = new JLabel("Primer Apellido");
		lblNewLabel.setBounds(163, 54, 126, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Segundo Apellido");
		lblNewLabel_1.setBounds(322, 54, 117, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblSegundoNombre = new JLabel("Primer Nombre");
		lblSegundoNombre.setBounds(10, 113, 114, 14);
		contentPane.add(lblSegundoNombre);
		
		JLabel lblSegundoNombre_1 = new JLabel("Segundo Nombre");
		lblSegundoNombre_1.setBounds(163, 113, 116, 14);
		contentPane.add(lblSegundoNombre_1);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(10, 185, 127, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		JLabel lblTipoDeSangre = new JLabel("Tipo de sangre");
		lblTipoDeSangre.setBounds(163, 185, 116, 14);
		contentPane.add(lblTipoDeSangre);
		
		JLabel lblSexo = new JLabel("sexo");
		lblSexo.setBounds(322, 113, 46, 14);
		contentPane.add(lblSexo);
		
		
		asuntoCbx.addItem("Cita");
		asuntoCbx.addItem("recibir mensajeria");
		asuntoCbx.addItem("entregar mensajeria");
		asuntoCbx.addItem("entrevista");
		
		
		
		
	}
}
