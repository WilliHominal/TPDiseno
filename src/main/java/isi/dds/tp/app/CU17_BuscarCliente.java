package isi.dds.tp.app;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
		new CU17_BuscarCliente(frame);
		frame.setVisible(true);		
	}
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private GestorTema tema = GestorTema.get();
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
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
	
	public CU17_BuscarCliente(JFrame ventana) {
		this.ventana = ventana;
		
		try {			
			panelAnterior = (JPanel) ventana.getContentPane();
			
		}catch(Exception ex) {
		    panelAnterior = null;
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
		
		
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, 0) {
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
		tema.panel(this);
		
		tema.label(lnumeroCliente);
		tema.label(ltipoDocumento);
		tema.label(lnumeroDocumento);
		tema.label(lapellido);
		tema.label(lnombre);
		tema.label(ltotalFilas);
		
		tema.campo(campoNumeroCliente, true);
		tema.campo(campoApellido, true);
		tema.campo(campoNumeroDocumento, true);
		tema.campo(campoNombre, true);
		tema.campo(campoTotalFilas, true);
		
		tema.boton(btnBuscar);
		tema.boton(btnCancelar);
		
		tema.seleccion(seleccionTipoDocumento, true);

		tema.tabla(tablaClientes, true);
		tema.tablaScroll(tablaClientesScroll, true);
	}

	public void comportamientos() {
		btnBuscar.addActionListener(a -> {
			try {
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
	private void cargarTabla(List<Cliente> clientes) {
		int tamanioTablaActual = clientes.size();
		
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
		
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(130);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(85);
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(85);
		
		tablaClientes.getColumnModel().getColumn(0).setHeaderValue("Hijo");
		tablaClientes.getColumnModel().getColumn(1).setHeaderValue("Fecha nacimiento");
		tablaClientes.getColumnModel().getColumn(2).setHeaderValue("Sexo");
		tablaClientes.getColumnModel().getColumn(3).setHeaderValue("Estado civil");
		
		for(int fila = 0; fila < tamanioTablaActual; fila++) {
			
			model.setValueAt(fila+1, fila, 0);

		}
		
	}	
}


