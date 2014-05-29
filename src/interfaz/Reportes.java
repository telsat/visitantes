package interfaz;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;

import controladores.ControladorPersona;
import controladores.Pantalla;
import controladores.controladorRegistro;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


@SuppressWarnings("serial")
public class Reportes extends JFrame {

	private JPanel contentPane;
	private JTextField nombreTxt;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reportes frame = new Reportes();
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
	public Reportes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 1362, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(10, 47, 89, 20);
		contentPane.add(nombreTxt);
		nombreTxt.setColumns(10);
		
		JLabel lblNombre = new JLabel("nombre ");
		lblNombre.setBounds(10, 29, 89, 14);
		contentPane.add(lblNombre);
		
		JPanel fechaPanel = new JPanel();
		fechaPanel.setBounds(242, 49, 242, 33);
		
		fechaPanel.add(datePicker);
		
		JPanel fechafPanel = new JPanel();
		fechafPanel.setBounds(526, 49, 242, 33);
		fechafPanel.add(finalDate);
		
		contentPane.add(fechafPanel);
		
		
		contentPane.add(fechaPanel);
		
		lblNewLabel = new JLabel("Hora");
		lblNewLabel.setBounds(265, 99, 46, 14);
		contentPane.add(lblNewLabel);
		
		horaCbx = new JComboBox<Integer>();
		horaCbx.setBounds(265, 124, 46, 20);
		contentPane.add(horaCbx);
		
		lblMinuto = new JLabel("Minuto");
		lblMinuto.setBounds(321, 99, 46, 14);
		contentPane.add(lblMinuto);
		
		minutoCbx = new JComboBox<Integer>();
		minutoCbx.setBounds(321, 124, 46, 20);
		contentPane.add(minutoCbx);
		
		lblSegundo = new JLabel("Segundo");
		lblSegundo.setBounds(377, 99, 70, 14);
		contentPane.add(lblSegundo);
		
		segundoCbx = new JComboBox<Integer>();
		segundoCbx.setBounds(377, 124, 46, 20);
		contentPane.add(segundoCbx);
		
		

		horafCbx = new JComboBox<Integer>();
		horafCbx.setBounds(546, 124, 47, 20);
		contentPane.add(horafCbx);
		
		minutofCbx = new JComboBox<Integer>();
		minutofCbx.setBounds(603, 124, 46, 20);
		contentPane.add(minutofCbx);
		
		segundofCbx = new JComboBox<Integer>();
		segundofCbx.setBounds(655, 124, 46, 20);
		contentPane.add(segundofCbx);
		
		
		
		lblFechaInicia = new JLabel("Fecha Inicial");
		lblFechaInicia.setBounds(264, 26, 123, 20);
		contentPane.add(lblFechaInicia);
		
		lblFechaFinal = new JLabel("Fecha Final");
		lblFechaFinal.setBounds(546, 26, 123, 20);
		contentPane.add(lblFechaFinal);
		
		
		
		
		
		JLabel horafLbl = new JLabel("Hora");
		horafLbl.setBounds(547, 99, 46, 14);
		contentPane.add(horafLbl);
		
		JLabel minutofLbl = new JLabel("Minuto");
		minutofLbl.setBounds(603, 99, 46, 14);
		contentPane.add(minutofLbl);
		
		JLabel segundofLbl = new JLabel("Segundo");
		segundofLbl.setBounds(655, 99, 70, 14);
		contentPane.add(segundofLbl);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int fila = table.getSelectedRow();
				String ced = (String) table.getValueAt(fila, 1);				
				int cedula = Integer.parseInt(ced);
				ControladorPersona cp = new ControladorPersona();
				cp.consulatrpersona(cedula);
				
				
			}
		});
		table.setBounds(10, 190, 1316, 206);
		contentPane.add(table);
		
		final String[] columnas = {"nombre","cedula","persona visitada","fecha ingreso","fecha salida"};
		
		final controladorRegistro cr = new controladorRegistro();
		
		JButton btnConsultarPorPersona = new JButton("Consultar por persona o fecha");
		btnConsultarPorPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombre = nombreTxt.getText();
				
				/*se toma la fecha inicia se convierte a string luego se agrega 
				  la hora al string y se convierte a Date */
				Date selectedDate = (Date) datePicker.getModel().getValue();
				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				String s = formatter.format(selectedDate);
				Object h = horaCbx.getSelectedItem();
				String hora = h.toString();
				Object m = minutoCbx.getSelectedItem();
				String minuto = m.toString();
				Object seg = segundoCbx.getSelectedItem();
				String segundo = seg.toString();
				
				s = s+" "+hora+":"+minuto+":"+segundo;
				Date fecha = null;
							
				SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					 fecha = form.parse(s);
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				
				
				/*se toma la fecha final se convierte a string luego se agrega 
				  la hora al string y se convierte a Date	*/					
				
				Date fechaSelect = (Date) finalDate.getModel().getValue();
				String ff = formatter.format(fechaSelect);
				Object hf = horafCbx.getSelectedItem();
				String horaf = hf.toString();
				Object mf = minutofCbx.getSelectedItem();
				String minutof = mf.toString();
				Object sf = segundofCbx.getSelectedItem();
				String segundof = sf.toString();
				
				ff = ff +" "+horaf+":"+minutof+":"+segundof;
				
				Date fechaf = null;
				
				try {
					fechaf = form.parse(ff);
					
				} catch (ParseException e) {
					System.out.println(e.getMessage());
					
				}
				
				
				
				Object [][] datos;
				
				datos = cr.ConsultarRegistro(fecha, fechaf, nombre);
				table.setModel(new DefaultTableModel(datos,columnas));
				
				
			}
		});
		btnConsultarPorPersona.setBounds(10, 428, 197, 23);
		contentPane.add(btnConsultarPorPersona);
		
		btnCapturarPantalla = new JButton("Capturar Pantalla");
		btnCapturarPantalla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				Pantalla p = new Pantalla();
				p.capturar();				
				
			}
		});
		btnCapturarPantalla.setBounds(242, 428, 145, 23);
		contentPane.add(btnCapturarPantalla);
		
		
		for(int i=1;i<=24;i++){
			horaCbx.addItem(i);
			horafCbx.addItem(i);
		}
		
		for(int i=1;i<=59;i++){
			minutoCbx.addItem(i);
			minutofCbx.addItem(i);
		}
		for(int i=1;i<=59;i++){
			segundoCbx.addItem(i);
			segundofCbx.addItem(i);
		}
		
		
	}
	
	UtilDateModel model = new UtilDateModel();
	UtilDateModel fin = new UtilDateModel();
	
	JDatePanelImpl datePanel = new JDatePanelImpl(model);	
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	
	JDatePanelImpl finalPanel = new JDatePanelImpl(fin);
	
	JDatePickerImpl finalDate = new JDatePickerImpl(finalPanel);
	
	private JLabel lblNewLabel;
	private JComboBox<Integer> horaCbx;
	private JLabel lblMinuto;
	private JComboBox<Integer> minutoCbx;
	private JLabel lblSegundo;
	private JComboBox<Integer> segundoCbx;
	private JLabel lblFechaInicia;
	private JLabel lblFechaFinal;
	
	private JComboBox<Integer> horafCbx;
	private JComboBox<Integer> minutofCbx;
	private JComboBox<Integer> segundofCbx;
	private JTable table;
	private JButton btnCapturarPantalla;
}	
	

