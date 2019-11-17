package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.dao.DAOPoliza;
import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.enums.EnumEstadoPoliza;
import isi.dds.tp.enums.EnumFormaPago;
import isi.dds.tp.enums.EnumSiniestros;
import isi.dds.tp.modelo.Poliza;
import isi.dds.tp.modelo.HijoDeclarado;
import isi.dds.tp.modelo.ParametrosPoliza;
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
	
    public Poliza newPoliza(Cliente cliente, String numeroSiniestros){
    	Poliza poliza = new Poliza();
    	poliza.setCliente(cliente);
    	poliza.setNumerosSiniestrosUltimoAnios(EnumSiniestros.getEnum(numeroSiniestros));
    	poliza.setHijosDeclarado(new ArrayList<HijoDeclarado>());
    	poliza.setCuotas(new ArrayList<Cuota>());
    	return poliza;
    }
    
    public Boolean altaPoliza(Poliza poliza) {
    	generarNumeroPoliza(poliza);
    	poliza.setEstado(EnumEstadoPoliza.GENERADA);
    	Boolean valido = DAOPoliza.getDAO().addPoliza(poliza);
    	
    	if(valido) {    		
    		GestorCliente.get().actualizarCliente(poliza.getCliente(), poliza);    		
    	}
    	
    	return valido;    	
    }
    
    private void generarNumeroPoliza(Poliza poliza) {
    	String numeroSucursal = "3528";
    	String cadenaRelacionClientePoliza = DAOPoliza.getDAO().getNumeroClientePoliza().toString();
    	String numeroRenovacionPoliza = "00";
    	try {
    		cadenaRelacionClientePoliza = cadenaRelacionClientePoliza.substring(1, cadenaRelacionClientePoliza.length());
		}catch(StringIndexOutOfBoundsException ex){ }
      	
    	Long numeroPoliza = Long.parseLong(numeroSucursal + cadenaRelacionClientePoliza + numeroRenovacionPoliza);
    	poliza.setNumeroPoliza(numeroPoliza);
	}

	public void addHijo(Poliza poliza, HijoDeclarado hijo){
		hijo.setPoliza(poliza);
		poliza.getHijosDeclarado().add(hijo);
    }
    
    public void addCuota(Poliza poliza, Float monto, LocalDate ultimoDiaPago) {
    	Cuota cuota = new Cuota();
    	cuota.setPoliza(poliza);
    	poliza.getCuotas().add(cuota);
    	cuota.setMonto(monto);
    	cuota.setEstado(EnumEstadoCuota.IMPAGO);
    	cuota.setUltimoDiaPago(ultimoDiaPago);
    }
    
	public void actualizarPoliza(Poliza poliza, Ciudad ciudad, AnioModelo anioModelo, String motor, String chasis, 
			String patente, Float sumaAsegurada, String kmRealizadosPorAnio, Boolean guardaGarage, Boolean tieneAlarma,
			Boolean tieneRastreoVehicular, Boolean tieneTuercasAntirobo) {
		poliza.setCiudad(ciudad);
		poliza.setAnioModelo(anioModelo);
		poliza.setMotor(motor);
		poliza.setChasis(chasis);
		poliza.setPatente(patente);
		poliza.setSumaAsegurada(sumaAsegurada);
		poliza.setKmRealizadosPorAnio(kmRealizadosPorAnio);
		poliza.setGuardaGarage(guardaGarage);
		poliza.setTieneAlarma(tieneAlarma);
		poliza.setTieneRastreoVehicular(tieneRastreoVehicular);
		poliza.setTieneTuercasAntirobo(tieneTuercasAntirobo);
	}
	
	public void actualizarPoliza(Poliza poliza, TipoCobertura tipoCobertura, LocalDate inicioVigencia, EnumFormaPago formaPago) {
		poliza.setTipoCobertura(tipoCobertura);
		poliza.setFechaEmision(LocalDate.now());
		poliza.setInicioVigencia(inicioVigencia);
		LocalDate finVigencia = inicioVigencia.plusMonths(6);
		poliza.setFinVigencia(finVigencia);
		poliza.setFormaPago(formaPago);
	}
	
	public void calcularPremio(Poliza poliza, Boolean semestral) {
		Float prima = calcularPrima(poliza);
		Float derechoEmision = poliza.getParametrosPoliza().getValorDerechoEmision();
		Float premio = prima + derechoEmision;
		poliza.setValorPrima(prima);
		poliza.setValorPremio(premio);
		calcularDescuento(poliza, semestral);
	}
	
	private Float calcularPrima(Poliza poliza) {
		ParametrosPoliza param = gestorParametrosPoliza.getUltimoParametrosPoliza();
		Float riesgoModelo = gestorVehiculo.getUltimoRiesgoModelo(poliza.getAnioModelo().getModelo().getIdModelo());
		Float riesgoCiudad  = gestorDomicilio.getUltimoRiesgoCiudad(poliza.getCiudad().getIdCiudad());
		Float riesgoTipoCobertura = gestorTipoCobertura.getUltimoRiesgoTipoCobertura(poliza.getTipoCobertura().getTipoCobertura());
		Float ajusteKm = param.getPorcentajeAjusteKm(), ajusteTuerca = 0f, ajusteAlarma = 0f, ajusteGarage = 0f, ajusteRastreo = 0f, ajusteSiniestros = 0f, ajusteHijosRegistrados = 0f;
		
		String kilometraje = poliza.getKmRealizadosPorAnio();
		//el rango va de 0 - 29 - al guardarse como se guardo se tiene que hacer esto
		Integer valorRangoKilometraje = 31;
		try {
			valorRangoKilometraje = Integer.parseInt( kilometraje.substring( 0, kilometraje.indexOf(" - ") ).replace(".", "") ) / 10000;
		}catch(StringIndexOutOfBoundsException ex){ }
		
		if(!poliza.getGuardaGarage()) {
			ajusteGarage = param.getPorcentajeGuardaEnGarage();
		}
		
		if(!poliza.getTieneAlarma()) {
			ajusteAlarma = param.getPorcentajeAlarma();
		}
		
		if(!poliza.getTieneTuercasAntirobo()) {
			ajusteTuerca = param.getPorcentajeTuercasAntirobo();
		}
		
		if(!poliza.getTieneRastreoVehicular()) {
			ajusteRastreo = param.getPorcentajeRastreoVehicular();
		}
		
		switch(poliza.getNumerosSiniestrosUltimoAnios()) {
			case NINGUNO:
				ajusteSiniestros = param.getPorcentajeNingunSiniestro();
			break;
			
			case UNO:
				ajusteSiniestros = param.getPorcentajeNingunSiniestro();
			break;
				
			case DOS:
				ajusteSiniestros = param.getPorcentajeDosSiniestros();
			break;
				
			case MAS_DE_DOS:
				ajusteSiniestros = param.getPorcentajeMayorADosSiniestros();
			break;
		}
		
		ajusteHijosRegistrados = poliza.getHijosDeclarado().size() * param.getPorcentajePorHijoRegistrado();
		
		Float prima = poliza.getSumaAsegurada() * riesgoModelo
				+ poliza.getSumaAsegurada() * riesgoCiudad 
				+ poliza.getSumaAsegurada() * riesgoTipoCobertura
				+ poliza.getSumaAsegurada() * valorRangoKilometraje.floatValue() * ajusteKm
				+ poliza.getSumaAsegurada() * ajusteGarage
				+ poliza.getSumaAsegurada() * ajusteAlarma
				+ poliza.getSumaAsegurada() * ajusteTuerca
				+ poliza.getSumaAsegurada() * ajusteRastreo
				+ poliza.getSumaAsegurada() * ajusteSiniestros
				+ poliza.getSumaAsegurada() * ajusteHijosRegistrados * poliza.getHijosDeclarado().size();

		poliza.setValorRiesgoCiudad(riesgoCiudad);
		poliza.setValorRiesgoModelo(riesgoModelo);
		poliza.setValorRiesgoTipoCobertura(riesgoTipoCobertura);
		poliza.setParametrosPoliza(param);
		return prima;
	}
	
	private void calcularDescuento(Poliza poliza, Boolean semestral) {
		Float valorDescuentoPorUnidadAdicional = poliza.getParametrosPoliza().getDescuentoUnidadAdicional();
		Float valorBonificacionPagoSemestral = 0f;

		if(semestral) {
			valorBonificacionPagoSemestral = GestorSistemaFinanciero.get().getTasaInteresAnual() / 2; //divide por 2 porque el interes es anual
		}
		
		Float valorDescuento = (valorDescuentoPorUnidadAdicional * getPolizas(poliza.getCliente().getNumeroCliente()).size() + valorBonificacionPagoSemestral ) * poliza.getValorPremio() ;
		poliza.setValorBonificacionPagoSemestral(valorBonificacionPagoSemestral);
		poliza.setValorDescuento(valorDescuento);
	}

	public Boolean validarMotor(String textoMotor) {
		if(DAOPoliza.getDAO().getCantPolizaPorMotor(textoMotor)>0) {
			return true;
		}
		return false;
	}
	
	public Boolean validarChasis(String textoChasis) {
		if(DAOPoliza.getDAO().getCantPolizaPorChasis(textoChasis)>0) {
			return true;
		}
		return false;
	}

	public Boolean validarPatente(String textoPatente) {
		if(DAOPoliza.getDAO().getCantPolizaPorPatente(textoPatente)>0) {
			return true;
		}
		return false;	
	}

	public Boolean validarFechaNacimiento(LocalDate fechaNacLocalDate) {
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
	
	public Boolean vigenciaFechaInicioVigencia(LocalDate fechaInicioVigenciaLocalDate) {		
		int anioActual = LocalDate.now().getYear();
		int mesActual = LocalDate.now().getMonthValue();
		int diaActual = LocalDate.now().getDayOfMonth();
		
		int anioInicioVigencia = fechaInicioVigenciaLocalDate.getYear();
		int mesInicioVigencia = fechaInicioVigenciaLocalDate.getMonthValue();
		int diaInicioVigencia = fechaInicioVigenciaLocalDate.getDayOfMonth();
		
		if (!(
				(anioActual == anioInicioVigencia && mesActual == mesInicioVigencia && diaActual < diaInicioVigencia)
			||  (anioActual == anioInicioVigencia && mesActual == mesInicioVigencia-1 && diaActual > diaInicioVigencia-1)
			||  (anioActual == anioInicioVigencia-1 && mesActual == 12 && mesInicioVigencia == 1 && diaActual >= diaInicioVigencia)
			)){
			return true;
		}
		return false;		
	}

	public Boolean vigenciaPolizas(Long numeroCliente) {
		if(DAOPoliza.getDAO().getCantidadPolizasVigente(numeroCliente) == 0) {
			return false;
		}
		return true;
	}
	
	public Boolean omisionPago(Long numeroCliente) {
		if(DAOPoliza.getDAO().getCantCuotasImpagas(numeroCliente) > 0) {
			return true;
		}
		return false;
	}

	public Boolean esModeloViejo(Poliza poliza) {
		Integer anioActual = LocalDate.now().getYear();
		return poliza.getAnioModelo().getAnio() <= (anioActual - 10); 
	}
	
	public List<Poliza> getPolizas(Long numeroCliente) {
		return DAOPoliza.getDAO().getPolizas(numeroCliente);
    }
	
	public void removeHijo(Poliza poliza, int indexHijo){
    	poliza.getHijosDeclarado().remove(indexHijo);
	}
	
	public Poliza buscarPoliza(Long numeroPoliza) {
		return DAOPoliza.getDAO().getPoliza(numeroPoliza);
	}
}
