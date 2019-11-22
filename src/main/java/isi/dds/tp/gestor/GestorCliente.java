package isi.dds.tp.gestor;

import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Poliza;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import isi.dds.tp.dao.DAOCliente;
import isi.dds.tp.dao.DAOPoliza;
import isi.dds.tp.enums.EnumCondicion;
import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.enums.EnumTipoDocumento;
import isi.dds.tp.hibernate.HibernateUtil;

public class GestorCliente {
	private static GestorCliente instanciaGestor = null;
	
	private static GestorPoliza gestorPoliza = GestorPoliza.get();
	 
    private GestorCliente() { }

    public static GestorCliente get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorCliente();
        }   
        return instanciaGestor;
    }
    
    public Cliente getCliente(Long numeroCliente) {
    	return DAOCliente.getDAO().getCliente(numeroCliente);
    }
    
    public void actualizarCliente(Cliente cliente, Poliza poliza) { 
    	if(gestorPoliza.getPolizas(cliente.getNumeroCliente()).size() <= 1) {
    		//la cantidad es uno, porque se actualiza al cliente luego de añadirle la póliza, con lo cuál ya tendría al menos una póliza
    		cliente.setCondicion(EnumCondicion.NORMAL);
    	}else {
    		Boolean polizasVigentes = gestorPoliza.vigenciaPolizas(cliente.getNumeroCliente());
    		if(!polizasVigentes) {
    			cliente.setCondicion(EnumCondicion.NORMAL);
    		}
    		else {
    			Boolean tieneSiniestros = !poliza.getNumerosSiniestrosUltimoAnios().equals(EnumSiniestros.NINGUNO);
    			Boolean cuotasImpagas = gestorPoliza.omisionPago(cliente.getNumeroCliente());
    			Boolean esClienteActivo = clienteActivo(cliente.getNumeroCliente());
    			if(tieneSiniestros || cuotasImpagas || !esClienteActivo) {
    				cliente.setCondicion(EnumCondicion.NORMAL);
    			}
    			else{
    				cliente.setCondicion(EnumCondicion.PLATA);
    			}
    		}
    	}
    	
    	HibernateUtil.shutdown();
    	DAOCliente.getDAO().updateCliente(cliente);
    }

	public Boolean clienteActivo(Long numeroCliente) {
		List<Poliza> listaPolizasActivas = DAOPoliza.getDAO().getPolizasActivas(numeroCliente);
		String numeroRenovacion = "";
		listaPolizasActivas.sort((Poliza p1, Poliza p2) -> p1.getInicioVigencia().compareTo(p2.getInicioVigencia()));
		Iterator<Poliza> iteratorPolizas = listaPolizasActivas.iterator();
		//TODO CU01 creo que ahi estaria esto
		
		//si tiene una poliza con 4 renovaciones
		while(iteratorPolizas.hasNext()) {
			Poliza poliza = iteratorPolizas.next();
			numeroRenovacion = poliza.getNumeroPoliza().toString().substring(11, 13);
			if(Integer.parseInt(numeroRenovacion)>=4) {
				System.out.println("POLIZA CON 4 RENOVACIONES");
				return true;
			}
		}
		
		//si pasan 2 años sin interrupciones
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaHaceDosAnios = fechaActual.minusYears(2);
		List<Poliza> polizasVigentesHaceDosAnios = new ArrayList<Poliza>();
		Poliza ultimaPolizaVigenteHaceDosAnios;
		List<Poliza> listaPolizasAuxiliar = DAOPoliza.getDAO().getPolizas(numeroCliente);
		listaPolizasAuxiliar.sort((Poliza p1, Poliza p2) -> p1.getInicioVigencia().compareTo(p2.getInicioVigencia()));
		Boolean activoDosAnios = false;
		
		//cargo la ultima poliza que estaba hace 2 años
		for (Poliza poliza : listaPolizasAuxiliar) {
			if ((poliza.getInicioVigencia().isBefore(fechaHaceDosAnios) || poliza.getInicioVigencia().equals(fechaHaceDosAnios)) && poliza.getFinVigencia().isAfter(fechaHaceDosAnios)) {
				polizasVigentesHaceDosAnios.add(poliza);
			}
		}
		System.out.println("LISTA POLIZAS DEL CLIENTE:");
		for (Poliza pol:listaPolizasAuxiliar)
			 System.out.println(pol.getNumeroPoliza());
		
		System.out.println("LISTAPOLIZAS VIGHACE2AÑOS: ");
		for (Poliza pol:polizasVigentesHaceDosAnios)
		System.out.println(pol.getNumeroPoliza());
		
		//si no habia polizas vigentes hace 2 años
		if (polizasVigentesHaceDosAnios.isEmpty()) {
			return false;
		} else {
			//cargo la ultima vigente de hace dos anios
			ultimaPolizaVigenteHaceDosAnios = polizasVigentesHaceDosAnios.get(polizasVigentesHaceDosAnios.size()-1);
			
			System.out.println("POLIZA SELECCIONADA: " + ultimaPolizaVigenteHaceDosAnios.getNumeroPoliza());
			
			//borro las anteriores en la lista auxiliar
			int contador = 0;
			while (!listaPolizasAuxiliar.get(contador).equals(ultimaPolizaVigenteHaceDosAnios))
				listaPolizasAuxiliar.remove(contador);
			
			System.out.println("LISTA POLIZAS RESTANTES: ");
			for (Poliza pol : listaPolizasAuxiliar)
			System.out.println(pol.getNumeroPoliza());
			
			//veo si tiene minimo 3 polizas mas (para que llegue con el minimo a los 2 años)
			if (listaPolizasAuxiliar.isEmpty() || listaPolizasAuxiliar.size() < 4)
				return false;
			
			//mientras que el contador no llegue a la anteultima poliza cargada en la lista auxiliar y no hayan pasado los dos años
			contador = 1; //empiezo de 1 porque la 0 es la ultimapolizavigentehacedosanios
			LocalDate ultimaFechaVigente = ultimaPolizaVigenteHaceDosAnios.getFinVigencia();
			while (contador < listaPolizasAuxiliar.size() && activoDosAnios == false) {
				ultimaFechaVigente = ultimaFechaVigente.plusDays(1);
				//si no es continua retorno falso
				if (!( (listaPolizasAuxiliar.get(contador).getInicioVigencia().isBefore(ultimaFechaVigente) || listaPolizasAuxiliar.get(contador).getInicioVigencia().equals(ultimaFechaVigente))
						&& (listaPolizasAuxiliar.get(contador).getFinVigencia().isAfter(ultimaFechaVigente) || listaPolizasAuxiliar.get(contador).getInicioVigencia().equals(ultimaFechaVigente)) )) {
					System.out.println("NOPE");
					return false;
				} else {
					ultimaFechaVigente = listaPolizasAuxiliar.get(contador).getFinVigencia();
					System.out.println("ULTIMA FECHA VIGENTE: "+ultimaFechaVigente.toString());
				}
				
				//si la poliza abarca la fecha actual, se hace verdadero activoDosAnios
				if ((listaPolizasAuxiliar.get(contador).getInicioVigencia().isBefore(fechaActual) || listaPolizasAuxiliar.get(contador).getInicioVigencia().equals(fechaActual)) &&
					(listaPolizasAuxiliar.get(contador).getFinVigencia().isAfter(fechaActual) || listaPolizasAuxiliar.get(contador).getFinVigencia().equals(fechaActual))	) {
					activoDosAnios = true;
					System.out.println("ACTIVOHACEDOSAÑOS");
				}
				
				contador++;
				
				System.out.println("CONTADOR "+ contador + " - TAMLISTAPOLIZAUX: "+ (listaPolizasAuxiliar.size()));
			}
			
			if (activoDosAnios == true) {
				System.out.println("ACTIVOHACEDOSAÑOS");
				return true;
			} else
				return false;
		}
	}

	public List<Cliente> buscarClientes(Long numeroCliente, String apellido, String nombre, EnumTipoDocumento tipoDocumento, String numeroDocumento) {
		String condicionesConsulta = "";
		Boolean primerConsulta = true;
    	if(numeroCliente != null) {
    		condicionesConsulta += "where numero_cliente="+numeroCliente;
    		primerConsulta = false;
    	}
    	
    	if(!apellido.isEmpty()) {
    		if(primerConsulta) {
    			primerConsulta = false;
    			condicionesConsulta += " where ";
      		}
    		else {
    			condicionesConsulta += " and ";
    		}
    		condicionesConsulta += " to_ascii(apellido, 'latin1') ilike  to_ascii('"+apellido+"%', 'latin1') ";
    	}
    	
    	if(!nombre.isEmpty()) {
    		if(primerConsulta) {
    			primerConsulta = false;
    			condicionesConsulta += " where ";
      		}
    		else {
    			condicionesConsulta += " and ";
    		}
   			condicionesConsulta += " to_ascii(nombre, 'LATIN1') ilike TO_ASCII('"+nombre+"%', 'latin1') ";
    	}
    	
    	if(tipoDocumento != null) {
    		if(primerConsulta) {
    			primerConsulta = false;
    			condicionesConsulta += " where ";
      		}
    		else {
    			condicionesConsulta += " and ";
    		}
    		condicionesConsulta += " tipo_documento = '"+tipoDocumento.name()+"' ";
    	}
    	
    	if(!numeroDocumento.isEmpty()) {
    		if(!primerConsulta) {
    			condicionesConsulta += " and ";
    			condicionesConsulta += " where ";
    		}
    		condicionesConsulta += " documento = '"+numeroDocumento+"' ";
    	}
    	condicionesConsulta += " order by numero_cliente asc";
    	return DAOCliente.getDAO().getClientes(condicionesConsulta);
    }
}
