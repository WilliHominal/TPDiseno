package isi.dds.tp.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.modelo.Cliente;


@SuppressWarnings("serial")
public class CU17_BuscarCliente extends JPanel {
		
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CU17_BuscarCliente();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CU17_BuscarCliente() {
		Color colorBoton = new Color(0, 128, 128);
		Color colorFondoPantalla = new Color(204,204,204);
		Color colorFondoTexto = new Color(204, 204, 153); 
		Color borde = Color.BLACK;
		Color colorLetra = Color.BLACK;
		Font letra = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
		Color colorErroneo = new Color(255,102,102);
		Object[] tema = {colorBoton, colorFondoPantalla, colorFondoTexto, borde, colorLetra, letra, colorErroneo};
		
		JFrame frame = new JFrame();
		frame.pack();
		frame.setBounds(0,0,1024,600);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
		new CU17_BuscarCliente(frame, tema);
		frame.setVisible(true);
		
	}
	
	private JFrame ventana;
	private AppMenu menu;
	private Object[] tema;
	private Color colorBoton, colorFondoPantalla, colorFondoTexto, borde, colorLetra, colorErroneo;
	private Font letra;
	
	public Cliente cliente = null;
	
	private JLabel lnumeroCliente = new JLabel("Número cliente:");
	private JLabel lapellido = new JLabel("Apellido:");
	private JLabel lnombre = new JLabel("Nombres:");
	private JLabel ltipoDocumento = new JLabel("Tipo documento:");
	private JLabel lnumeroDocumento = new JLabel("Documento:");
	private JLabel ltotalFilas = new JLabel("Total de filas:");
		
	private JTextField campoNumeroCliente = new JTextField(18);
	private JTextField campoNumeroDocumento = new JTextField(18);
	private JTextField campoNombre = new JTextField(18);
	private JTextField campoApellido = new JTextField(18);
	private JTextField campoTotalFilas = new JTextField(3);
	
	private JButton btnBuscar = new JButton("BUSCAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	
	private JComboBox<String> seleccionTipoDocumento = new JComboBox<String>();
	
	private JTable tablaClientes = new JTable();
	private JScrollPane tablaClientesScroll = new JScrollPane(tablaClientes);
	private Object[][] datosTabla = {{""},{""},{""},{""},{""}};
	private DefaultTableModel model;
	
	public CU17_BuscarCliente(JFrame ventana, Object[] tema) {
		this.ventana = ventana;
		this.tema = tema;
		
		try {			
			menu = (AppMenu) ventana.getContentPane();
			
		}catch(Exception ex) {
		    menu = null;
		}

		ventana.setContentPane(this);
		
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();
		
		comportamientos();

		ventana.setTitle("Buscar cliente");		
	}

	private void inicializarComponentes() {
		setSize(1024,600);
		
		campoTotalFilas.setEnabled(false);
		tablaClientes.setEnabled(false);

		btnBuscar.setPreferredSize(new Dimension(105, 25));
		btnCancelar.setPreferredSize(new Dimension(105, 25));
		seleccionTipoDocumento.setPreferredSize(new Dimension(180, 25));

		seleccionTipoDocumento.addItem("Seleccionar tipo documento");
		seleccionTipoDocumento.addItem("DNI");
		seleccionTipoDocumento.addItem("Pasaporte");
		seleccionTipoDocumento.addItem("Libreta civil");
		seleccionTipoDocumento.addItem("Libreta de enrolamiento");
		
		
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, 15) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		tablaClientes.setModel(tableModel);
		
		tablaClientes.setFillsViewportHeight(true);
		tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaClientes.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(3).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(4).setCellRenderer(centrado);
		
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		tablaClientes.getColumnModel().getColumn(0).setHeaderValue("Número cliente");
		tablaClientes.getColumnModel().getColumn(1).setHeaderValue("Apellido");
		tablaClientes.getColumnModel().getColumn(2).setHeaderValue("Nombre");
		tablaClientes.getColumnModel().getColumn(3).setHeaderValue("Tipo documento");
		tablaClientes.getColumnModel().getColumn(4).setHeaderValue("Número documento");
		
		tablaClientesScroll.setPreferredSize(new Dimension(600, 500));
		
		model = (DefaultTableModel) tablaClientes.getModel();
	}
	
	private void ubicarComponentes() {
		
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//FILA 1
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lnumeroCliente, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoNumeroCliente, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lapellido, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoApellido, constraints);	

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lnombre, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoNombre, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(ltipoDocumento, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(seleccionTipoDocumento, constraints);	

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lnumeroDocumento, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoNumeroDocumento, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(50, 5, 5, 150);
		add(btnBuscar, constraints);
		constraints.insets.set(50, 150, 5, 5);
		add(btnCancelar, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridheight = 7;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 50, 5, 5);
		add(tablaClientesScroll, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 7;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 5, 5);
		add(campoTotalFilas, constraints);	
		constraints.insets.set(5, 5, 5, 44);
		add(ltotalFilas, constraints);	

		

	}
		
	private void inicializarTema() {	
		
		colorBoton = (Color) tema[0];
		colorFondoPantalla = (Color) tema[1];
		colorFondoTexto = (Color)tema[2];
		borde = (Color)tema[3];
		colorLetra = (Color) tema[4];
		letra = (Font) tema[5];
		colorErroneo = (Color) tema[6];
		
		setFont(letra);
		setBackground(colorFondoPantalla);
		setForeground(colorLetra);
		
		lnumeroCliente.setFont(letra);
		lnumeroCliente.setForeground(colorLetra);
		ltipoDocumento.setFont(letra);
		ltipoDocumento.setForeground(colorLetra);
		lnumeroDocumento.setFont(letra);
		lnumeroDocumento.setForeground(colorLetra);
		lapellido .setFont(letra);
		lapellido.setForeground(colorLetra);
		lnombre.setFont(letra);
		lnombre.setForeground(colorLetra);
		ltotalFilas.setFont(letra);
		ltotalFilas.setForeground(colorLetra);
			
		campoNumeroCliente.setFont(letra);
		campoNumeroCliente.setBackground(colorFondoTexto);
		campoNumeroCliente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoNumeroCliente.setDisabledTextColor(colorLetra);
		campoNumeroDocumento.setFont(letra);
		campoNumeroDocumento.setBackground(colorFondoTexto);
		campoNumeroDocumento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoNumeroDocumento.setDisabledTextColor(colorLetra);
		campoApellido.setFont(letra);
		campoApellido.setBackground(colorFondoTexto);
		campoApellido.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoApellido.setDisabledTextColor(colorLetra);
		campoNombre.setFont(letra);
		campoNombre.setBackground(colorFondoTexto);
		campoNombre.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoNombre.setDisabledTextColor(colorLetra);
		campoApellido.setFont(letra);
		campoApellido.setBackground(colorFondoTexto);
		campoApellido.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		campoApellido.setDisabledTextColor(colorLetra);
		
		btnBuscar.setBackground(colorBoton);
		btnBuscar.setFont(letra);
		btnBuscar.setForeground(colorLetra);
		btnCancelar.setBackground(colorBoton);
		btnCancelar.setFont(letra);
		btnCancelar.setForeground(colorLetra);
		
		UIManager.put( "ComboBox.disabledBackground", colorFondoPantalla );
		UIManager.put( "ComboBox.disabledForeground", colorLetra );
		seleccionTipoDocumento.setBackground(colorFondoPantalla);
		seleccionTipoDocumento.setFont(letra);
		seleccionTipoDocumento.setForeground(colorLetra);
		
		tablaClientes.setBackground(colorFondoTexto);
		tablaClientes.setFont(letra);
		tablaClientes.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		tablaClientes.setForeground(colorLetra);
		tablaClientesScroll.getViewport().setBackground(colorFondoTexto);
	}

	public void comportamientos() {
		btnBuscar.addActionListener(a -> {
			try {
				//GestorCliente.get().getClientes() o consultaClientes
				cliente = GestorCliente.get().getCliente(123456l);
						
			}catch(Exception ex) {
            	JOptionPane.showMessageDialog(null, "No se pudo obtener el cliente desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE);       	
			}
		});
		 
		btnCancelar.addActionListener(a -> {
			try {			
				this.setVisible(false);
				ventana.setContentPane(menu);				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		campoApellido.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if(Character.isLetter(caracter) && campoApellido.getText().length() < 8){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
			}
		}); 

	    campoNombre.addKeyListener(new KeyAdapter(){ 
	    	public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if(Character.isLetter(caracter) && campoNombre.getText().length() < 8){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
	    	}
	    });

	    campoNumeroDocumento.addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if(Character.isDigit(caracter) && campoNumeroDocumento.getText().length() < 7){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
	    	}
	    }); 
	    
		seleccionTipoDocumento.addActionListener (a -> {
			
		});		
	}


	@SuppressWarnings("unused")
	private void cargarTabla() {
		model.setValueAt(1, 1, 0);
		model.setValueAt("aa", 1, 1);
		model.setValueAt("aa", 1, 2);
	}
}


