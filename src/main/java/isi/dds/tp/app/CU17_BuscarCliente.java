package isi.dds.tp.app;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
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
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorEnum;
import isi.dds.tp.gestor.GestorTema;
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
		JFrame frame = new JFrame();
		frame.pack();
		frame.setBounds(0,0,1024,600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
		new CU17_BuscarCliente(frame);
		frame.setVisible(true);		
	}
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private JPanel estePanel;
	private GestorTema tema = GestorTema.get();
	
	private Boolean esAltaPoliza = false;
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	private JLabel lnumeroCliente = new JLabel("Número cliente:");
	private JLabel lapellido = new JLabel("Apellido:");
	private JLabel lnombre = new JLabel("Nombres:");
	private JLabel ltipoDocumento = new JLabel("Tipo documento:");
	private JLabel lnumeroDocumento = new JLabel("Documento:");
	private JLabel ltotalFilas = new JLabel("Total de filas:");
	private JLabel lordenarPor = new JLabel("Ordenar por:");
		
	private JTextField campoNumeroCliente = new JTextField(18);
	private JTextField campoNumeroDocumento = new JTextField(18);
	private JTextField campoNombre = new JTextField(18);
	private JTextField campoApellido = new JTextField(18);
	private JTextField campoTotalFilas = new JTextField(3);
	
	private JButton btnBuscar = new JButton("BUSCAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	
	private JComboBox<String> seleccionTipoDocumento = new JComboBox<String>();
	private JComboBox<String> seleccionOrdenamiento = new JComboBox<String>();
	private JTable tablaClientes = new JTable();
	private JScrollPane tablaClientesScroll = new JScrollPane(tablaClientes);
	private Object[][] datosTabla = {{""},{""},{""},{""},{""}};
	private DefaultTableModel model;
	
	public CU17_BuscarCliente(JFrame ventana) {
		
		this.ventana = ventana;
		
		try {		
			if(ventana.getContentPane() instanceof CU01_AP1) {
				esAltaPoliza = true;
			}
			panelAnterior = (JPanel) ventana.getContentPane();
			
		}catch(Exception ex) {
		    panelAnterior = null;
		}

		ventana.setContentPane(this);
		
		inicializarComponentes();
		ubicarComponentes();
		inicializarTema();
		this.estePanel  = this;
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
		
		seleccionOrdenamiento.addItem("Default");
		seleccionOrdenamiento.addItem("Número cliente");
		seleccionOrdenamiento.addItem("Apellido");
		seleccionOrdenamiento.addItem("Nombre");
		seleccionOrdenamiento.addItem("Tipo documento");
		seleccionOrdenamiento.addItem("Número documento");
		
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		tablaClientes.setModel(tableModel);
		
		tablaClientes.setFillsViewportHeight(true);
		tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarTabla(null);		
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
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lordenarPor, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(seleccionOrdenamiento, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(50, 5, 5, 150);
		add(btnBuscar, constraints);
		constraints.insets.set(50, 150, 5, 5);
		add(btnCancelar, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridheight = 8;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 50, 5, 5);
		add(tablaClientesScroll, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 8;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 5, 5);
		add(campoTotalFilas, constraints);	
		constraints.insets.set(5, 5, 5, 44);
		add(ltotalFilas, constraints);	

		

	}
		
	private void inicializarTema() {	
		tema.panel(this);
		
		tema.label(lnumeroCliente);
		tema.label(ltipoDocumento);
		tema.label(lnumeroDocumento);
		tema.label(lapellido);
		tema.label(lnombre);
		tema.label(ltotalFilas);
		tema.label(lordenarPor);
		
		tema.campo(campoNumeroCliente, true);
		tema.campo(campoApellido, true);
		tema.campo(campoNumeroDocumento, true);
		tema.campo(campoNombre, true);
		tema.campo(campoTotalFilas, false);
		
		tema.boton(btnBuscar);
		tema.boton(btnCancelar);
		
		tema.seleccion(seleccionTipoDocumento, true);
		tema.seleccion(seleccionOrdenamiento, true);

		tema.tabla(tablaClientes, true);
		tema.tablaScroll(tablaClientesScroll, true);
	}

	public void comportamientos() {
		btnBuscar.addActionListener(a -> {
			try {
				Long numeroCliente = null;
				String apellido = null;
				String nombre = null;
				EnumTipoDocumento tipoDocumento= null;
				String numeroDocumento = null; 	
				
				if(!campoNumeroCliente.getText().isBlank()) {
					numeroCliente = Long.parseLong(campoNumeroCliente.getText());
				}
				
				if(!campoApellido.getText().isBlank()) {
					apellido = campoApellido.getText();
				}
				
				if(!campoNombre.getText().isBlank()) {
					nombre = campoNombre.getText();
				}
				
				if(seleccionTipoDocumento.getSelectedIndex() != 0) {
					tipoDocumento = GestorEnum.get().getEnumTipoDocumento(seleccionTipoDocumento.getItemAt(seleccionTipoDocumento.getSelectedIndex()));
				}
								
				if(!campoNumeroDocumento.getText().isBlank()) {
					numeroDocumento = campoNumeroDocumento.getText();
				}
				
				clientes = GestorCliente.get().buscarClientes(numeroCliente, apellido, nombre, tipoDocumento, numeroDocumento, seleccionOrdenamiento.getSelectedIndex());
				
				cargarTabla(clientes);
						
			}catch(Exception ex) {
            	JOptionPane.showMessageDialog(null, "No se pudo obtener el cliente desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE);       	
			}
		});
		 
		btnCancelar.addActionListener(a -> {
			try {			
				this.setVisible(false);
				ventana.setContentPane(panelAnterior);				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		tablaClientes.addMouseListener(new MouseAdapter() {
			//TODO ordenar la tabla si se hace doble click en alguna columna
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		            //TODO mostrar datos completos de cliente
		        	if(esAltaPoliza) {
		        		CU01_AP1 altaPoliza =  (CU01_AP1) panelAnterior;
		        		altaPoliza.obtenidoCliente(clientes.get(row));
			        	ventana.setContentPane(altaPoliza);
			        	estePanel.setVisible(false);
		        	}
		        	else {
			        	ventana.setContentPane(panelAnterior);
			        	estePanel.setVisible(false);
		        	}

		        }
		    }
		});
				
		campoNumeroCliente.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if(Character.isDigit(caracter) && campoApellido.getText().length() < 8){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
			}
		}); 
		
		campoApellido.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				if(Character.isLetter(caracter) && campoApellido.getText().length() < 30){
					
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
				if(Character.isLetter(caracter) && campoNombre.getText().length() < 30){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
	    	}
	    });

	  /*  campoNumeroDocumento.addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();
				//TODO poner condiciones de acuerod al tipo de documento
				if(Character.isDigit(caracter) && campoNumeroDocumento.getText().length() < 10){
					
				}
				else{
					e.consume();  // ignorar el evento de teclado
					getToolkit().beep();
				}
	    	}
	    }); */
	    
		seleccionTipoDocumento.addActionListener (a -> {
			
		});		
	}

	private void cargarTabla(List<Cliente> clientes) {
		int tamanioTablaActual = 0; 
		
		if(clientes != null) {
			tamanioTablaActual = clientes.size();
		}
		
		
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, tamanioTablaActual) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaClientes.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaClientes.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(3).setCellRenderer(centrado);
		tablaClientes.getColumnModel().getColumn(4).setCellRenderer(centrado);
		
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(70);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(110);
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(120);
		tablaClientes.getColumnModel().getColumn(4).setPreferredWidth(80);
		
		tablaClientes.getColumnModel().getColumn(0).setHeaderValue("Nro. cliente");
		tablaClientes.getColumnModel().getColumn(1).setHeaderValue("Apellido");
		tablaClientes.getColumnModel().getColumn(2).setHeaderValue("Nombre");
		tablaClientes.getColumnModel().getColumn(3).setHeaderValue("Tipo documento");
		tablaClientes.getColumnModel().getColumn(4).setHeaderValue("Nro. documento");
		
		if(clientes != null) {	
			tablaClientes.setToolTipText("Doble click para seleccionar un cliente");
			
			tablaClientes.setEnabled(true);
			campoTotalFilas.setText(String.valueOf(clientes.size()));
			//TODO ordenar clientes
			for(int fila = 0; fila < tamanioTablaActual; fila++) {
				model.setValueAt(clientes.get(fila).getNumeroCliente(), fila, 0);
				model.setValueAt(clientes.get(fila).getApellido(), fila, 1);
				model.setValueAt(clientes.get(fila).getNombre(), fila, 2);
				model.setValueAt(GestorEnum.get().getStringTipoDocumento(clientes.get(fila).getTipoDocumento()), fila, 3);
				model.setValueAt(clientes.get(fila).getNumeroDocumento(), fila, 4);

			}
			if(tamanioTablaActual == 0) {
				JOptionPane.showMessageDialog(ventana, "No se han encontrado clientes que cumplan con ese criterio de búsqueda.", "Clente no encontrado", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
		else {
			tablaClientes.setToolTipText(null);
			tablaClientes.setEnabled(false);
			campoTotalFilas.setText("");
		}
	}	
}


