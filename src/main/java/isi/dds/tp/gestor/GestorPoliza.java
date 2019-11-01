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
    	poliza.setEstado(EnumEstadoPoliza.GENERADA);
    	GestorCliente.get().actualizarCliente(poliza.getCliente(), poliza);
    	DAOPoliza.getDAO().addPoliza(poliza);
    }
    
    private void generarNumeroPoliza(Poliza poliza) {
		Random r = new Random();
		Long randomNum = r.nextLong();	
		poliza.setNumeroPoliza(randomNum);
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
    
    public Poliza actualizarPoliza(Poliza poliza, Cliente cliente){
    	poliza = new Poliza();
    	poliza.setCliente(cliente);
    	poliza.setHijosDeclarado(new ArrayList<HijoDeclarado>());
    	poliza.setCuotas(new ArrayList<Cuota>());
    	return poliza;
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
		Float prima = 285400f;
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
		
		Float premio = 325000f;
		//TODO calcular premio
		poliza.setValorPremio(premio);
		return premio;
	}
	
	public Float calcularDescuento(Poliza poliza, Boolean semestral) {
		Float valorDescuento = 1000f;
		Float valorDescuentoPorUnidadAdicional = 220f;
		Float valorBonificacionPagoSemestral = 1220f;

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
		return false;	
	}

	public Boolean validadEdadHijo(LocalDate fechaNacLocalDate) {
		int anioHoy = LocalDate.now().getYear();
		int diaDelAnioHoy = LocalDate.now().getDayOfYear();
		int anioDeclarado = fechaNacLocalDate.getYear();
		int diaDelAnioDeclarado = fechaNacLocalDate.getDayOfYear();
		
		Boolean valido1 = false;
		Boolean valido2 = false;
		
		if(anioHoy > (anioDeclarado+18)) {
			valido1 = true;
		}
		else {
			if(anioHoy < (anioDeclarado+18)) {
				valido1 = false;
			}
			else {
				if(diaDelAnioHoy >= diaDelAnioDeclarado) {
					valido1 = true;
				}
				else {
					valido1 = false;
				}
			}
		}
		
		if(anioHoy < (anioDeclarado+30)) {
			valido2 = true;
		}
		else {
			if(anioHoy > (anioDeclarado+30)) {
				valido2 = false;
			}
			else {
				if(diaDelAnioHoy < diaDelAnioDeclarado) {
					valido2 = true;
				}
				else {
					valido2 = false;
				}
			}
		}
		
		return (valido1 && valido2);
	}
}
