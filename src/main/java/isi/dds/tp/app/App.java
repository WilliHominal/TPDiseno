package isi.dds.tp.app;

import java.awt.*;
import javax.swing.*;
import isi.dds.tp.dao.DomicilioDAO;
import isi.dds.tp.dao.ParametrosVehiculosDAO;
import isi.dds.tp.hibernate.HibernateUtil;
import isi.dds.tp.modelo.*;


public class App {

	private JFrame frame;
	//el color del borde deberia ser igual al del fondo
	//Color colorBoton, Color colorFondoPantalla, Color borde, Color colorFondoTexto, Color colorLetraBloqueado, Font letra, Font letraTitulo
	private Object[] tema = {new Color(0, 128, 128), new Color(192,192,192), /*new Color(192,192,192)*/new Color(222,184,135), Color.BLACK, Color.GRAY, new Font("Open Sans", Font.PLAIN, 13), new Font("Open Sans", Font.BOLD, 15)};
	private AltaClientes altaClientes;
	private AltaPoliza1 altaPoliza1;
	private AltaPoliza2 altaPoliza2;
	private BuscarCliente buscarCliente;
	private DeclararHijo declararHijo;
	private MenuPrincipal menu;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public App() {
		
		conectar();//TODO agregar condicion si da falso
		
		inicializar();
		

	}

	private void inicializar() {
		frame = new JFrame();
		
		setAltaPoliza1();
	
		frame.setVisible(true);	
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public AltaClientes getAltaClientes() {
		return altaClientes;
	}

	public void setAltaClientes() {
		this.altaClientes = new AltaClientes(frame);
		frame.setContentPane(altaClientes);
	}

	public AltaPoliza1 getAltaPoliza1() {
		return altaPoliza1;
	}

	public void setAltaPoliza1() {
		this.altaPoliza1 = new AltaPoliza1(frame, tema);
		frame.setContentPane(altaPoliza1);
	}

	public AltaPoliza2 getAltaPoliza2() {
		return altaPoliza2;
	}

	public void setAltaPoliza2( ) {
		this.altaPoliza2 = new AltaPoliza2(frame, tema, new Poliza());
		frame.setContentPane(altaPoliza2);
	}

	public BuscarCliente getBuscarCliente() {
		return buscarCliente;
	}

	public void setBuscarCliente( ) {
		this.buscarCliente = new BuscarCliente(frame);
		frame.setContentPane(buscarCliente);
	}

	public DeclararHijo getDeclararHijo() {
		return declararHijo;
	}

	public void setDeclararHijo() {
		this.declararHijo = new DeclararHijo(frame);
		frame.setContentPane(declararHijo);
	}

	public MenuPrincipal getMenu() {
		return menu;
	}

	public void setMenu() {
		this.menu = new MenuPrincipal(frame);
		frame.setContentPane(menu);
	}
	
	@SuppressWarnings("unused")
	public void cargar(){
		Pais pais1 = new Pais("Argentina");
		
		Provincia prov1 = new Provincia(pais1, "Santa Fe");
		Provincia prov2 = new Provincia(pais1, "Buenos Aires");
		Provincia prov3 = new Provincia(pais1, "Entre Rios");
		
		
		Ciudad ciudad1 = new Ciudad(prov1, "Esperanza", 1f);
		Ciudad ciudad2 = new Ciudad(prov1, "Santa Fe", 2f);
		Ciudad ciudad3 = new Ciudad(prov2, "La Plata", 3f);
		Ciudad ciudad4 = new Ciudad(prov2, "Mar del Plata", 4f);
		Ciudad ciudad5 = new Ciudad(prov3, "Paraná", 5f);
		Ciudad ciudad6 = new Ciudad(prov3, "Diamante", 6f);
		
		Marca marca1 =  new Marca("Renault");
		Marca marca2 =  new Marca("Volskwagen");
		
		Modelo modelo1 =  new Modelo(marca1, "Clio", 1f);
		Modelo modelo2 =  new Modelo(marca1, "10", 0.1f);
		Modelo modelo3 =  new Modelo(marca2, "Gol", 1.5f);
		Modelo modelo4 =  new Modelo(marca2, "Vista", 2f);
		
		AnioModelo anio1 = new AnioModelo(modelo1, 2016, 130000f);
		AnioModelo anio2 = new AnioModelo(modelo1, 2015, 120000f);
		AnioModelo anio3 = new AnioModelo(modelo2, 2011, 95000f);
		AnioModelo anio4 = new AnioModelo(modelo2, 2010, 90000f);
		AnioModelo anio5 = new AnioModelo(modelo3, 2018, 150000f);
		AnioModelo anio6 = new AnioModelo(modelo4, 2018, 250000f);
		AnioModelo anio7 = new AnioModelo(modelo4, 2019, 300000f);
		
		ParametrosVehiculosDAO.getDAO().addMarca(marca1);
		ParametrosVehiculosDAO.getDAO().addMarca(marca2);
		
		DomicilioDAO.getDAO().addPais(pais1);
	}
	
	public void conectar() {
		
		HibernateUtil.getSessionFactory();
		
	}
			
}
