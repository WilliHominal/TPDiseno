package isi.dds.tp.hibernate;

import isi.dds.tp.enums.EnumTipoCobertura;
import isi.dds.tp.gestor.GestorCliente;
import isi.dds.tp.gestor.GestorDomicilio;
import isi.dds.tp.gestor.GestorParametrosVehiculo;
import isi.dds.tp.gestor.GestorSubsistemaSiniestros;
import isi.dds.tp.gestor.GestorTipoCobertura;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Marca;
import isi.dds.tp.modelo.Modelo;
import isi.dds.tp.modelo.TipoCobertura;

public class CargarBase {
	
	public static void load() {
    	/*Marca marca = new Marca("Volkswagen");
		List<Modelo> m = marca.getModelos(); 
		
		m.add(new Modelo(marca, "Amarok", 0.8f));
		new AnioModelo(m.get(0), 2014, 45800f);
		new AnioModelo(m.get(0), 2015, 51222f);
		new AnioModelo(m.get(0), 2016, 58120f);

		Modelo modelo2 = new Modelo(marca, "Bora", 0.8f);

		Modelo modelo3 = new Modelo(marca, "Caddy", 0.8f);

		Modelo modelo4 = new Modelo(marca, "Fox", 0.8f);

		Modelo modelo5 = new Modelo(marca, "Gol", 0.8f);

		Modelo modelo6 = new Modelo(marca, "Golf", 0.8f);
		
		Modelo modelo7 = new Modelo(marca, "Multivan", 0.8f);

		Modelo modelo8 = new Modelo(marca, "New Beetle", 0.8f);

		Modelo modelo9 = new Modelo(marca, "Passat", 0.8f);

		Modelo modelo10 = new Modelo(marca, "Polo", 0.8f);

		Modelo modelo11 = new Modelo(marca, "Santana", 0.8f);

		Modelo modelo12 = new Modelo(marca, "Saveiro", 0.8f);
		
		Modelo modelo13 = new Modelo(marca, "Voyage", 0.8f);

		Modelo modelo14 = new Modelo(marca, "Vento", 0.8f);

		Modelo modelo15 = new Modelo(marca, "Up", 0.8f);

		Modelo modelo16 = new Modelo(marca, "Transporter", 0.8f);

		Modelo modelo17 = new Modelo(marca, "The Beetle", 0.8f);

		Modelo modelo18 = new Modelo(marca, "T-Cross", 0.8f);

		Modelo modelo19 = new Modelo(marca, "Suran", 0.8f);

		Modelo modelo20 = new Modelo(marca, "Sharan", 0.8f);

		Modelo modelo21 = new Modelo(marca, "Scirocco", 0.8f);

	*/
				
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
			
		/*Marca marca4 = new Marca("Alfa Romeo");
		
		Marca marca5 = new Marca("Audi");
		
		Marca marca6 = new Marca("MBW");
		
		Marca marca7 = new Marca("Chery");
		
		Marca marca8 = new Marca("Chevrolet");
		
		Marca marca9 = new Marca("Chrysler");
		
		Marca marca10 = new Marca("Citroen");
		
		Marca marca11 = new Marca("Dodge");
		
		Marca marca12 = new Marca("Ferrari");
		
		Marca marca13 = new Marca("Honda");
		
		Marca marca14 = new Marca("Hyundai");
		
		Marca marca15 = new Marca("Jaguar");
		
		Marca marca16 = new Marca("Jeep");
		
		Marca marca17 = new Marca("Kia");
				
		Marca marca18 = new Marca("Land Rover");
		
		Marca marca19 = new Marca("Mercedes Benz");
		
		Marca marca20 = new Marca("Mitsubishi");
		
		Marca marca21 = new Marca("Nissan");
		
		Marca marca22 = new Marca("Peugeot");
		
		Marca marca23 = new Marca("Porsche");
		
		Marca marca24 = new Marca("Seat");
		
		Marca marca25 = new Marca("Susuki");
		
		Marca marca26 = new Marca("Toyota");
		
		Marca marca27 = new Marca("Volvo");*/
		
		//Usuario usuario = new Usuario("Milto");
						
		TipoCobertura cobertura = new TipoCobertura(EnumTipoCobertura.RESPONSABILIDAD_CIVIL, "Responsabilidad civil", "Descripcion uno", 0.23f);
		TipoCobertura cobertura1 = new TipoCobertura(EnumTipoCobertura.RESP_CIVIL_ROBO_O_INCENDIO_TOTAL, "Responsabilidad civil, robo o incendio total", "Descripcion dos", 0.39f);
		TipoCobertura cobertura2 = new TipoCobertura(EnumTipoCobertura.TODO_TOTAL, "Todo total", "Descripcion tres", 0.65f);
		TipoCobertura cobertura3 = new TipoCobertura(EnumTipoCobertura.TERCEROS_COMPLETOS, "Terceros completos", "Descripcion seis", 0.51f);
		TipoCobertura cobertura4 = new TipoCobertura(EnumTipoCobertura.TODO_RIESGO_CON_FRANQUICIA, "Todo riesgo con franquiia", "Descripcion cinco", 0.48f);
		

		//---------------------------------------------------------------------------

		HibernateUtil.shutdown();
		HibernateUtil.getSessionFactoryCreate();

		//GestorParametrosVehiculo.get().addMarca(marca);
		GestorParametrosVehiculo.get().addMarca(marca1);
		GestorParametrosVehiculo.get().addMarca(marca2);
		GestorParametrosVehiculo.get().addMarca(marca3);

		//GestorUsuario.get().addUsuario(usuario);
		
		GestorTipoCobertura.get().addTipoCobertura(cobertura);
		GestorTipoCobertura.get().addTipoCobertura(cobertura1);
		GestorTipoCobertura.get().addTipoCobertura(cobertura2);
		GestorTipoCobertura.get().addTipoCobertura(cobertura3);
		GestorTipoCobertura.get().addTipoCobertura(cobertura4);

		GestorDomicilio.get().cargarUbicaciones();
		GestorCliente.get().cargarClientes();
		GestorSubsistemaSiniestros.get().cargarSiniestros();
		//GestorTipoCobertura.get().cargarTiposCoberturas();
		//GestorParametrosVehiculo.get().cargarParametrosVehiculos();
		//GestorParametrosPoliza.get().cargarParametrosPoliza();
		//GestorPoliza.get().cargarPolizas();
    }
}
