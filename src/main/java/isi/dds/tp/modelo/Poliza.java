package isi.dds.tp.modelo;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;
import isi.dds.tp.enums.EnumEstadoPoliza;
import isi.dds.tp.enums.EnumFormaPago;
import isi.dds.tp.enums.EnumSiniestros;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Poliza {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "numero_cliente")
	private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "numero_poliza")
	@IndexColumn(name = "idx")
	private List<HijoDeclarado> hijosDeclarado;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "numero_poliza")
	@IndexColumn(name ="idx")
	private List<Cuota> cuotas;
    
	@JoinColumn(name = "tipo_cobertura")
    @OneToOne
	private TipoCobertura tipoCobertura;

	@JoinColumn(name = "anio_modelo")
    @OneToOne
	private AnioModelo anioModelo;
	
	@JoinColumn(name = "id_ciudad")
    @OneToOne
	private Ciudad ciudad;
	
	@JoinColumn(name = "parametros_poliza")
    @OneToOne
	private ParametrosPoliza parametrosPoliza;
    
	@JoinColumn(name = "solicitud_poliza")
    @OneToOne(cascade=CascadeType.ALL)
	private SolicitudPoliza solicitudPoliza;
	
	@Id
	@Column(name = "numero_poliza")
	private Long numeroPoliza;
	
	@Column(nullable = false, name = "suma_asegurada")
	private Float sumaAsegurada;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumEstadoPoliza estado;
	
	@Column(nullable = false)
	private String motor;
	
	@Column(nullable = false)
	private String chasis;
	
	@Column
	private String patente;
	
	@Column(nullable = false, name = "km_realizados_por_anio")
	private String kmRealizadosPorAnio;
	
	@Column(nullable = false, name = "guarda_garage")
	private Boolean guardaGarage;
	
	@Column(nullable = false, name = "tiene_alarma")
	private Boolean tieneAlarma;
	
	@Column(nullable = false, name = "tiene_rastreo_vehicular")
	private Boolean tieneRastreoVehicular;
	
	@Column(nullable = false, name = "tiene_tuercas_antirobo")
	private Boolean tieneTuercasAntirobo;
	
	@Column(nullable = false, name = "numeros_siniestros_ultimo_anios")
	@Enumerated(EnumType.STRING)
	private EnumSiniestros numerosSiniestrosUltimoAnios;
	
	@Column(nullable = false, name = "inicio_vigencia")
	private LocalDate inicioVigencia; 
	
	@Column(nullable = false, name = "fecha_emision")
	private LocalDate fechaEmision;
	
	@Column(nullable = false, name = "fin_vigencia")
	private LocalDate finVigencia;
	
	@Column(nullable = false, name = "forma_pago")
	@Enumerated(EnumType.STRING)
	private EnumFormaPago formaPago; 
	
	@Column(nullable = false, name = "valor_premio")
	private Float valorPremio;
	
	@Column(nullable = false, name = "valor_prima")
	private Float valorPrima;
	
	@Column(nullable = false, name = "valor_descuento")
	private Float valorDescuento;
	
	@Column(nullable = false, name = "valor_bonificacion_pago_semestral")
	private Float valorBonificacionPagoSemestral;
	
	@Column(nullable = false, name = "valor_riesgo_ciudad")
	private Float valorRiesgoCiudad;
	
	@Column(nullable = false, name = "valor_riesgo_modelo")
	private Float valorRiesgoModelo;

	@Column(nullable = false, name = "valor_riesgo_cobertura")
	private Float valorRiesgoCobertura;

	public Poliza() { }
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public TipoCobertura getTipoCobertura() {
		return tipoCobertura;
	}
	
	public AnioModelo getAnioModelo() {
		return anioModelo;
	}
	
	public Ciudad getCiudad() {
		return ciudad;
	}
	
	public ParametrosPoliza getParametrosPoliza() {
		return parametrosPoliza;
	}
	
	public Long getNumeroPoliza() {
		return numeroPoliza;
	}
	
	public List<HijoDeclarado> getHijosDeclarado() {
		return hijosDeclarado;
	}
	
	public void setHijosDeclarado(List<HijoDeclarado> hijosDeclarado) {
		this.hijosDeclarado = hijosDeclarado;
	}
	
	public Float getSumaAsegurada() {
		return sumaAsegurada;
	}
	
	public EnumEstadoPoliza getEstado() {
		return estado;
	}
	
	public String getMotor() {
		return motor;
	}
	
	public String getChasis() {
		return chasis;
	}
	
	public String getPatente() {
		return patente;
	}
	
	public String getKmRealizadosPorAnio() {
		return kmRealizadosPorAnio;
	}
	
	public Boolean getGuardaGarage() {
		return guardaGarage;
	}
	
	public Boolean getTieneAlarma() {
		return tieneAlarma;
	}
	
	public Boolean getTieneRastreoVehicular() {
		return tieneRastreoVehicular;
	}
	
	public Boolean getTieneTuercasAntirobo() {
		return tieneTuercasAntirobo;
	}
	
	public EnumSiniestros getNumerosSiniestrosUltimoAnios() {
		return numerosSiniestrosUltimoAnios;
	}
	
	public LocalDate getInicioVigencia() {
		return inicioVigencia;
	}
	
	public LocalDate getFechaEmision() {
		return fechaEmision;
	}
	
	public LocalDate getFinVigencia() {
		return finVigencia;
	}
	
	public EnumFormaPago getFormaPago() {
		return formaPago;
	}
	
	public Float getValorPremio() {
		return valorPremio;
	}
	
	public Float getValorPrima() {
		return valorPrima;
	}
	
	public Float getValorDescuento() {
		return valorDescuento;
	}
	
	public Float getValorBonificacionPagoSemestral() {
		return valorBonificacionPagoSemestral;
	}
	
	public Float getValorRiesgoCiudad() {
		return valorRiesgoCiudad;
	}
	
	public Float getValorRiesgoModelo() {
		return valorRiesgoModelo;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setTipoCobertura(TipoCobertura tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}
	
	public void setAnioModelo(AnioModelo anioModelo) {
		this.anioModelo = anioModelo;
	}
	
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	public void setParametrosPoliza(ParametrosPoliza parametroPoliza) {
		this.parametrosPoliza = parametroPoliza;
	}
	
	public void setNumeroPoliza(Long numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}
	
	public void setSumaAsegurada(Float sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}
	
	public void setEstado(EnumEstadoPoliza estado) {
		this.estado = estado;
	}
	
	public void setMotor(String motor) {
		this.motor = motor;
	}
	
	public void setChasis(String chasis) {
		this.chasis = chasis;
	}
	
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	public void setKmRealizadosPorAnio(String kmRealizadosPorAnio) {
		this.kmRealizadosPorAnio = kmRealizadosPorAnio;
	}
	
	public void setGuardaGarage(Boolean guardaGarage) {
		this.guardaGarage = guardaGarage;
	}
	
	public void setTieneAlarma(Boolean tieneAlarma) {
		this.tieneAlarma = tieneAlarma;
	}
	
	public void setTieneRastreoVehicular(Boolean tieneRastreoVehicular) {
		this.tieneRastreoVehicular = tieneRastreoVehicular;
	}
	
	public void setTieneTuercasAntirobo(Boolean tieneTuercasAntirobo) {
		this.tieneTuercasAntirobo = tieneTuercasAntirobo;
	}
	
	public void setNumerosSiniestrosUltimoAnios(EnumSiniestros numerosSiniestrosUltimoAnios) {
		this.numerosSiniestrosUltimoAnios = numerosSiniestrosUltimoAnios;
	}
	
	public void setInicioVigencia(LocalDate inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	
	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	public void setFinVigencia(LocalDate finVigencia) {
		this.finVigencia = finVigencia;
	}
	public void setFormaPago(EnumFormaPago formaPago) {
		this.formaPago = formaPago;
	}
	
	public void setValorPremio(Float valorPremio) {
		this.valorPremio = valorPremio;
	}
	
	public void setValorPrima(Float valorPrima) {
		this.valorPrima = valorPrima;
	}
	
	public void setValorDescuento(Float valorDescuento) {
		this.valorDescuento = valorDescuento;
	}
	
	public void setValorBonificacionPagoSemestral(Float valorBonificacionPagoSemestral) {
		this.valorBonificacionPagoSemestral = valorBonificacionPagoSemestral;
	}
	
	public void setValorRiesgoCiudad(Float valorRiesgoCiudad) {
		this.valorRiesgoCiudad = valorRiesgoCiudad;
	}
	
	public void setValorRiesgoModelo(Float valorRiesgoModelo) {
		this.valorRiesgoModelo = valorRiesgoModelo;
	}
	
	public SolicitudPoliza getSolicitudPoliza() {
		return solicitudPoliza;
	}
	
	public void setSolicitudPoliza(SolicitudPoliza solicitudPoliza) {
		this.solicitudPoliza = solicitudPoliza;
	}
	
	public List<Cuota> getCuotas() {
		return cuotas;
	}
	
	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}	
	
	public Float getValorRiesgoCobertura() {
		return valorRiesgoCobertura;
	}
	
	public void setValorRiesgoTipoCobertura(Float valorRiesgoCobertura) {
		this.valorRiesgoCobertura = valorRiesgoCobertura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroPoliza == null) ? 0 : numeroPoliza.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poliza other = (Poliza) obj;
		if (numeroPoliza == null) {
			if (other.numeroPoliza != null)
				return false;
		} else if (!numeroPoliza.equals(other.numeroPoliza))
			return false;
		return true;
	} 
}
