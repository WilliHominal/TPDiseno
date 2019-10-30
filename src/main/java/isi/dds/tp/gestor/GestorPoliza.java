package isi.dds.tp.gestor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.dao.DAOPoliza;
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
    
    public void altaPoliza(Poliza p) {
    	DAOPoliza.getDAO().addPoliza(p);
    }
    
    public void addSolicitudPoliza(SolicitudPoliza s) {
    	DAOPoliza.getDAO().addSolicitudPoliza(s);
    }
    
    public void generarNumeroPoliza(Poliza P) {
    	//TODO implementar
    }
    
    public SolicitudPoliza generarSolicitud(Poliza P){
    	//TODO implementar
    	return null;
    }
    
    public void actualizarSolictud(SolicitudPoliza s){
    	//TODO implementar
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
    
    public void actualizarPoliza(Poliza poliza, Cliente cliente){
    	//TODO fijarse cuando agregar poliza a cliente
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

    
    public void addHijo(Poliza poliza, HijoDeclarado hijo){
		hijo.setPoliza(poliza);
		poliza.getHijosDeclarado().add(hijo);
    }
    
    public void removeHijo(Poliza poliza, int indexHijo){
    	poliza.getHijosDeclarado().remove(indexHijo);
    }
    
    public Poliza getPoliza(Long numeroPoliza) {
    	//TODO implementar
		return null;    	
    }
    
    public SolicitudPoliza getSolicitud(Long numeroPoliza) {
    	//TODO implementar o borrar
    	return null;
    }

	public void cargarPolizas() {
		DAOPoliza.getDAO().cargarPolizas();
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
		Float premio = 0f;
		Float derechoEmision = gestorParametrosPoliza.getUltimoParametrosPoliza().getValorDerechoEmision();
		poliza.setValorPrima(prima);
		poliza.setValorDerechoEmison(derechoEmision);
		//TODO calcular premio
		poliza.setValorPremio(premio);
		return premio;
	}

	public void actualizarPoliza(Poliza poliza, TipoCobertura tipoCobertura, LocalDate inicioVigencia, LocalDate finVigencia) {
		poliza.setTipoCobertura(tipoCobertura);
		poliza.setFechaEmision(LocalDate.now());
		poliza.setInicioVigencia(inicioVigencia);
		poliza.setFinVigencia(finVigencia);
	}
}
