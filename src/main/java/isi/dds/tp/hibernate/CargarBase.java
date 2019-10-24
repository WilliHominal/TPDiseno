package isi.dds.tp.hibernate;

import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.gestor.GestorSubsistemaSiniestros;
import isi.dds.tp.gestor.GestorTipoCobertura;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;

public class CargarBase {
	
	public static void load() {
			
		Marca marca1 = new Marca("Fiat");
			Modelo modelo3 = new Modelo(marca1, "Argo", 0.21f);
				new AnioModelo(modelo3, 2014, 45800f);
				new AnioModelo(modelo3, 2015, 51222f);
				new AnioModelo(modelo3, 2016, 58120f);
			Modelo modelo4 = new Modelo(marca1, "Cronos", 0.73f);
				new AnioModelo(modelo4, 2017, 184010f);
				new AnioModelo(modelo4, 2018, 199800f);
				new AnioModelo(modelo4, 2015, 149800f);
			Modelo modelo5 = new Modelo(marca1, "500X", 0.52f);
				new AnioModelo(modelo5, 2017, 101928f);
				new AnioModelo(modelo5, 2019, 142000f);
			Modelo modelo6 = new Modelo(marca1, "Uno",  0.11f);
				new AnioModelo(modelo6, 2010, 28200f);
				new AnioModelo(modelo6, 2012, 34500f);
				new AnioModelo(modelo6, 2013, 35712f);
					
		Marca marca2 = new Marca("Ford");
			Modelo modelo7 = new Modelo(marca2, "Focus", 0.24f);
				new AnioModelo(modelo7, 2016, 58213f);
				new AnioModelo(modelo7, 2019, 69123f);
			Modelo modelo8 = new Modelo(marca2, "Mondeo", 0.65f);
				new AnioModelo(modelo8, 2015, 167521f);
				new AnioModelo(modelo8, 2016, 172921f);
				new AnioModelo(modelo8, 2017, 173552f);
				new AnioModelo(modelo8, 2018, 182083f);
			Modelo modelo9 = new Modelo(marca2, "Ka", 0.08f);
				new AnioModelo(modelo9, 2014, 29100f);
				new AnioModelo(modelo9, 2015, 23182f);
			Modelo modelo14 = new Modelo(marca2, "Ecosport", 0.571f);
				new AnioModelo(modelo14, 2018, 134020f);
				new AnioModelo(modelo14, 2019, 146818f);					

		Marca marca3 = new Marca("Reanult");
			Modelo modelo10 = new Modelo(marca3, "Kwid", 0.52f);
				new AnioModelo(modelo10, 2017, 89267f);
				new AnioModelo(modelo10, 2018, 94566f);
				new AnioModelo(modelo10, 2019, 99350f);
			Modelo modelo11 = new Modelo(marca3, "Duster", 0.87f);
				new AnioModelo(modelo11, 2017, 267991f);
				new AnioModelo(modelo11, 2019, 289312f);
			Modelo modelo12 = new Modelo(marca3, "Koleos", 0.81f);
				new AnioModelo(modelo12, 2018, 216737f);
				new AnioModelo(modelo12, 2019, 230093f);
			Modelo modelo13 = new Modelo(marca3, "Kangoo II", 0.47f);
				new AnioModelo(modelo13, 2019, 92630f);

		HibernateUtil.shutdown();
		HibernateUtil.getSessionFactoryCreate();
		GestorParametrosVehiculo.get().addMarca(marca1);
		GestorParametrosVehiculo.get().addMarca(marca2);
		GestorParametrosVehiculo.get().addMarca(marca3);

		
		//-------- TODO: BORRAR LAS CARGAS DE ARRIBA
		
		GestorDomicilio.get().cargarUbicaciones();
		GestorCliente.get().cargarClientes();
		GestorSubsistemaSiniestros.get().cargarSiniestros();
		GestorTipoCobertura.get().cargarTiposCoberturas();
	//	GestorParametrosVehiculo.get().cargarParametrosVehiculos();
	//	GestorParametrosPoliza.get().cargarParametrosPoliza();
	//	GestorPoliza.get().cargarPolizas();
    }
}
