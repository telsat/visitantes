package interfaz;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class Pestañas extends JFrame {

	private JPanel contentPane;
	private JTextField cedulaTxt;
	private JTextField apellidoTxt;
	private JTextField sapellidoTxt;
	private JTextField pnombreTxt;
	private JTextField snombreTxt;
	private JTextField sexoTxt;
	private JTextField nacimientoTxt;
	private JTextField sangreTxt;
	private JTextField fabricacionTxt;
	private JTextField sfabricacionTxt;
	private JButton salidaBtn;
	private JLabel lblMotivoVisita;
	private JTextField serialTxt;
	private JTextField equipoTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pestañas frame = new Pestañas();
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
	public Pestañas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 763, 319);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Registro", null, panel, null);
		panel.setLayout(null);
		
		cedulaTxt = new JTextField();
		cedulaTxt.setBounds(10, 36, 123, 20);
		panel.add(cedulaTxt);
		cedulaTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cedula");
		lblNewLabel.setBounds(10, 21, 123, 14);
		panel.add(lblNewLabel);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setBounds(143, 36, 123, 20);
		panel.add(apellidoTxt);
		apellidoTxt.setColumns(10);
		
		sapellidoTxt = new JTextField();
		sapellidoTxt.setBounds(276, 36, 130, 20);
		panel.add(sapellidoTxt);
		sapellidoTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Primer Apellido");
		lblNewLabel_1.setBounds(143, 21, 123, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Segundo Apellido");
		lblNewLabel_2.setBounds(276, 21, 123, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Primer Nombre");
		lblNewLabel_3.setBounds(10, 76, 123, 14);
		panel.add(lblNewLabel_3);
		
		pnombreTxt = new JTextField();
		pnombreTxt.setBounds(10, 91, 123, 20);
		panel.add(pnombreTxt);
		pnombreTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Segundo Nombre");
		lblNewLabel_4.setBounds(143, 76, 123, 14);
		panel.add(lblNewLabel_4);
		
		snombreTxt = new JTextField();
		snombreTxt.setBounds(143, 91, 123, 20);
		panel.add(snombreTxt);
		snombreTxt.setColumns(10);
		
		sexoTxt = new JTextField();
		sexoTxt.setBounds(276, 91, 130, 20);
		panel.add(sexoTxt);
		sexoTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("sexo");
		lblNewLabel_5.setBounds(276, 76, 123, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(10, 128, 123, 14);
		panel.add(lblFechaDeNacimiento);
		
		nacimientoTxt = new JTextField();
		nacimientoTxt.setBounds(10, 144, 123, 20);
		panel.add(nacimientoTxt);
		nacimientoTxt.setColumns(10);
		
		sangreTxt = new JTextField();
		sangreTxt.setBounds(143, 144, 123, 20);
		panel.add(sangreTxt);
		sangreTxt.setColumns(10);
		
		fabricacionTxt = new JTextField();
		fabricacionTxt.setBounds(276, 144, 60, 20);
		panel.add(fabricacionTxt);
		fabricacionTxt.setColumns(10);
		
		sfabricacionTxt = new JTextField();
		sfabricacionTxt.setBounds(346, 144, 60, 20);
		panel.add(sfabricacionTxt);
		sfabricacionTxt.setColumns(10);
		
		JButton IngresoBtn = new JButton("Registrar Ingreso");
		IngresoBtn.setBounds(10, 257, 145, 23);
		panel.add(IngresoBtn);
		
		salidaBtn = new JButton("Registrar Salida");
		salidaBtn.setBounds(190, 257, 145, 23);
		panel.add(salidaBtn);
		
		JComboBox<Object> asuntoCbx = new JComboBox<Object>();
		asuntoCbx.setBounds(143, 186, 193, 20);
		panel.add(asuntoCbx);
		
		lblMotivoVisita = new JLabel("Motivo Visita");
		lblMotivoVisita.setBounds(10, 189, 123, 14);
		panel.add(lblMotivoVisita);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(455, 36, 250, 190);
		panel.add(panel_2);
		
		JLabel lblTipoDeSangre = new JLabel("Tipo de Sangre");
		lblTipoDeSangre.setBounds(143, 128, 123, 14);
		panel.add(lblTipoDeSangre);
		
		JCheckBox ausenteCk = new JCheckBox("Persona No Disponible");
		ausenteCk.setBounds(6, 213, 200, 20);
		panel.add(ausenteCk);
		
		JButton nuevaFotoBtn = new JButton("Tomar Nueva foto");
		nuevaFotoBtn.setBounds(454, 257, 175, 23);
		panel.add(nuevaFotoBtn);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Equipos", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(389, 11, 250, 190);
		panel_1.add(panel_3);
		
		serialTxt = new JTextField();
		serialTxt.setBounds(10, 55, 113, 20);
		panel_1.add(serialTxt);
		serialTxt.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Serial");
		lblNewLabel_6.setBounds(10, 36, 113, 14);
		panel_1.add(lblNewLabel_6);
		
		equipoTxt = new JTextField();
		equipoTxt.setBounds(133, 55, 113, 20);
		panel_1.add(equipoTxt);
		equipoTxt.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(133, 36, 46, 14);
		panel_1.add(lblNombre);
		
		JButton ingresoEquipoBtn = new JButton("Registrar Equipo");
		ingresoEquipoBtn.setBounds(10, 172, 144, 29);
		panel_1.add(ingresoEquipoBtn);
	}
}
