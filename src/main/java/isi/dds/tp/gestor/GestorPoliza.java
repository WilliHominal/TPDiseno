package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import isi.dds.tp.dao.DAOPoliza;
import isi.dds.tp.enums.EnumEstadoPoliza;
import isi.dds.tp.enums.EnumFormaPago;
import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.SolicitudPoliza;
import isi.dds.tp.modelo.TipoCobertura;
import isi.dds.tp.modelo.AnioModelo;
import isi.dds.tp.modelo.Ciudad;
import isi.dds.tp.modelo.Cliente;
import isi.dds.tp.modelo.Cuota;

public class GestorPoliza {
	
	private static GestorPoliza instanciaGestor = null;
	private GestorDomicilio gestorDomicilio = GestorDomicilio.get();
	private GestorParametrosVehiculo gestorVehiculo = GestorParametrosVehiculo.get();
	private GestorParametrosPoliza gestorParametrosPoliza  = GestorParametrosPoliza.get();
	private GestorTipoCobertura gestorTipoCobertura = GestorTipoCobertura.get();
	 
    private GestorPoliza() { }

    public static GestorPoliza get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorPoliza();
        }    
        return instanciaGestor;
    }
    
	public void cargarPolizas() {
		DAOPoliza.getDAO().cargarPolizas();
	}
    
    public void altaPoliza(Poliza poliza) {
    	generarNumeroPoliza(poliza);
    	setEstadoPoliza(poliza);
    	GestorCliente.get().actualizarCliente(poliza.getCliente(), poliza);
    	DAOPoliza.getDAO().addPoliza(poliza);
    }
    
    private void generarNumeroPoliza(Poliza poliza) {
		Random r = new Random();
		Long randomNum = r.nextLong();	
		poliza.setNumeroPoliza(randomNum);
    }
    
    private void setEstadoPoliza(Poliza poliza) {
    	//TODO implementar estado
    	poliza.setEstado(EnumEstadoPoliza.GENERADA);
    }

	public List<Cuota> getCuotas(Long numeroPoliza) {
		return DAOPoliza.getDAO().getCuotas(numeroPoliza);
    }
    
    public SolicitudPoliza getSolicitudPoliza(Long numeroPoliza) {
    	return DAOPoliza.getDAO().getSolicitudPoliza(numeroPoliza);
    }
    
	public List<HijoDeclarado> getHijosDeclarados(Long numeroPoliza) {
		return DAOPoliza.getDAO().getHijosDeclarados(numeroPoliza);
    }
    
    public void addHijo(Poliza poliza, HijoDeclarado hijo){
		hijo.setPoliza(poliza);
		poliza.getHijosDeclarado().add(hijo);
    }
    
    public void removeHijo(Poliza poliza, int indexHijo){
    	poliza.getHijosDeclarado().remove(indexHijo);
    }
    
    public void actualizarPoliza(Poliza poliza, Cliente cliente){
    	poliza.setCliente(cliente);
    	poliza.setHijosDeclarado(new ArrayList<HijoDeclarado>());
    	poliza.setCuotas(new ArrayList<Cuota>());
    }
    
	public void actualizarPoliza(Poliza poliza, Ciudad ciudad, AnioModelo anioModelo, String motor, String chasis, 
			String patente, Float sumaAsegurada, String kmRealizadosPorAnio, EnumSiniestros numerosSiniestrosUltimoAnios,
			Boolean guardaGarage, Boolean tieneAlarma, Boolean tieneRastreoVehicular, Boolean tieneTuercasAntirobo) {
		poliza.setCiudad(ciudad);
		poliza.setAnioModelo(anioModelo);
		poliza.setMotor(motor);
		poliza.setChasis(chasis);
		poliza.setPatente(patente);
		poliza.setSumaAsegurada(sumaAsegurada);
		poliza.setKmRealizadosPorAnio(kmRealizadosPorAnio);
		poliza.setNumerosSiniestrosUltimoAnios(numerosSiniestrosUltimoAnios);
		poliza.setGuardaGarage(guardaGarage);
		poliza.setTieneAlarma(tieneAlarma);
		poliza.setTieneRastreoVehicular(tieneRastreoVehicular);
		poliza.setTieneTuercasAntirobo(tieneTuercasAntirobo);
	}
	
	public void actualizarPoliza(Poliza poliza, TipoCobertura tipoCobertura, LocalDate inicioVigencia, LocalDate finVigencia, EnumFormaPago formaPago) {
		poliza.setTipoCobertura(tipoCobertura);
		poliza.setFechaEmision(LocalDate.now());
		poliza.setInicioVigencia(inicioVigencia);
		poliza.setFinVigencia(finVigencia);
		poliza.setFormaPago(formaPago);
	}
	
	public Float calcularPrima(Poliza poliza) {
		Float prima = 0f;
		Float riesgoModelo = gestorVehiculo.getUltimoRiesgoModelo(poliza.getAnioModelo().getModelo().getIdModelo());
		Float riesgoCiudad  = gestorDomicilio.getUltimoRiesgoCiudad(poliza.getCiudad().getIdCiudad());
		Float riesgoTipoCobertura = gestorTipoCobertura.getUltimoRiesgoTipoCobertura(poliza.getTipoCobertura().getTipoCobertura());
		poliza.setValorRiesgoCiudad(riesgoCiudad);
		poliza.setValorRiesgoModelo(riesgoModelo);
		poliza.setValorRiesgoTipoCobertura(riesgoTipoCobertura);
		//TODO calcular prima
		return prima;
	}

	public Float calcularPremio(Poliza poliza) {
		Float prima = calcularPrima(poliza);
		poliza.setValorPrima(prima);
		
		Float derechoEmision = gestorParametrosPoliza.getUltimoParametrosPoliza().getValorDerechoEmision();
		poliza.setValorDerechoEmison(derechoEmision);
		
		Float premio = 0f;
		//TODO calcular premio
		poliza.setValorPremio(premio);
		return premio;
	}
	
	public Float calcularDescuento(Poliza poliza, Boolean semestral) {
		Float valorDescuento = 0f;
		Float valorDescuentoPorUnidadAdicional = 0f;
		Float valorBonificacionPagoSemestral = 0f;

		
		//TODO calcular descuento bonificacion etc
		
		poliza.setValorDescuentoPorUnidadAdicional(valorDescuentoPorUnidadAdicional);
		poliza.setValorBonificacionPagoSemestral(valorBonificacionPagoSemestral);
		poliza.setValorDescuento(valorDescuento);
		
		return valorDescuento;
	}

	public Boolean validarMotor(String textoPatente) {
		// TODO implementar validarMotor(String textoPatente)
		return false;
	}
	
	public Boolean validarChasis(String textoChasis) {
		// TODO implementar validarChasis(String textoChasis)
		return false;
	}

	public Boolean validarPatente(String textoPatente/*, String errores, Integer errorNumero*/) {
		//TODO implementar validarPatente(String textoPatente)
		/*switch (textoPatente.length()) {
	        case 6: //para patente longitud 6
	        	for(int i = 0; i < 6; i++) {
	        		
		        	switch(i) {
		        		case 0:
		        		case 1:
		        		case 2:
		    				if(!Character.isLetter(textoPatente.charAt(i))) {
		    					errores = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					return true;
		    				}
		        		break;
		        		
		        		case 3:
		        		case 4:
		        		case 5:
		    				if(!Character.isDigit(textoPatente.charAt(i))) {
		    					errores = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					return true;
		    				}
		        		break;
		        	}
	        	}
	        break;     
	        
	        case 7:  //para patente longitud 7
	        	for(int j = 0; j < 7; j++) {
	        		
		        	switch(j) {
		        		case 0:
		        		case 1:
		        		case 5:
		        		case 6:
		    				if(Character.isDigit(textoPatente.charAt(j))) {
		    					errores = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
		    					errorNumero++;
		    					return true;
		    				}
		        		break;
		        
		        		case 2:
		        		case 3:
		        		case 4:
		    				if(Character.isLetter(textoPatente.charAt(j))) {
		    					errores = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
		    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";	
		    					errorNumero++;
		    					return true;
		    				}
		        		break;
		        	}
	        	}
	        break;
	
	        default:
	        	errores = errorNumero+") La definición de una patente debe ser de longitud 6 o 7.\n";
				errorNumero++;
				return true;
			
		}		*/
		return false;	
	}

}
