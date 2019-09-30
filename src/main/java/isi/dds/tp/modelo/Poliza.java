package isi.dds.tp.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.enums.*;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Poliza {
	
	@ManyToOne
	@JoinColumn(name = "numero_cliente")
	private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "numero_poliza")
	@IndexColumn(name = "idx")
	/*TODO configuar unidireccionalidad*/
	private List<HijoDeclarado> hijosDeclarado;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "numero_poliza")
	@IndexColumn(name ="idx")
	/*TODO configuar unidireccionalidad*/
	private List<Cuota> cuotas;
	
    @OneToOne
    @PrimaryKeyJoinColumn(name = "tipo_cobertura")
	private TipoCobertura tipoCobertura;

	@OneToOne
    @PrimaryKeyJoinColumn(name = "anio_modelo")
	private AnioModelo anioModelo;
	
	@OneToOne
    @PrimaryKeyJoinColumn
	private Ciudad ciudad;
	
    @OneToOne
    @PrimaryKeyJoinColumn(name = "parametros_poliza")
	private ParametroPoliza parametroPoliza;
    
    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "solicitud_poliza")
	private SolicitudPoliza solicitudPoliza;
	
	@Id
	@Column(name = "numero_poliza")
	/*TODO definir numero poliza*/
	private Long numeroPoliza;
	
	@Column(nullable = false, name = "suma_asegurada")
	private Float sumaAsegurada;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumEstadoPoliza estado;
	
	@Column(nullable = false, unique = true)
	private String motor;
	
	@Column(nullable = false, unique = true)
	private String chasis;
	
	@Column(unique = true)
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
	
	@Column(nullable = false, name = "forma_pPago")
	@Enumerated(EnumType.STRING)
	private EnumFormaPago formaPago; 
	
	@Column(name = "es_propuesta")
	private Boolean esPropuesta;
	
	@Column(name = "esta_emitida")
	private Boolean estaEmitida;
	
	@Column(nullable = false, name = "valor_descuento_por_unidad_adicional")
	private Float valorDescuentoPorUnidadAdicional;
	
	@Column(nullable = false, name = "valor_premio")
	private Float valorPremio;
	
	@Column(nullable = false, name = "valor_prima")
	private Float valorPrima;
	
	@Column(nullable = false, name = "valor_descuento")
	private Float valorDescuento;
	
	@Column(nullable = false, name = "valor_bonificacion_pago_semestral")
	private Float valorBonificacionPagoSemestral;
	
	@Column(nullable = false, name = "valor_interesGenero")
	private Float valorInteresGenero;
	
	@Column(nullable = false, name = "valor_riesgo_ciudad")
	private Float valorRiesgoCiudad;
	
	@Column(nullable = false, name = "valor_riesgo_modelo")
	private Float valorRiesgoModelo;

	@Column(nullable = false, name = "valor_riesgo_cobertura")
	private Float valorRiesgoCobertura;
	
	@Column(nullable = false, name = "porcentaje_valor_asegurado")
	private Float porcentajeValorAsegurado;
	
	public Poliza() {
		
	}

	public Poliza(Cliente cliente, TipoCobertura tc, AnioModelo am, Ciudad ciudad, ParametroPoliza p, SolicitudPoliza sp,
			Long numeroPoliza, Float sumaAsegurada, EnumEstadoPoliza estado, String motor, String chasis,
			String patente, String kmRealizadosPorAnio, Boolean guardaGarage, Boolean tieneAlarma,
			Boolean tieneRastreoVehicular, Boolean tt, EnumSiniestros ns) {
		this.cliente = cliente;
		this.hijosDeclarado = new ArrayList<HijoDeclarado>();
		this.tipoCobertura = tc;
		this.anioModelo = am;
		this.ciudad = ciudad;
		this.parametroPoliza = p;
		this.solicitudPoliza = sp;
		this.numeroPoliza = numeroPoliza;
		this.sumaAsegurada = sumaAsegurada;
		this.estado = estado;
		this.motor = motor;
		this.chasis = chasis;
		this.patente = patente;
		this.kmRealizadosPorAnio = kmRealizadosPorAnio;
		this.guardaGarage = guardaGarage;
		this.tieneAlarma = tieneAlarma;
		this.tieneRastreoVehicular = tieneRastreoVehicular;
		this.tieneTuercasAntirobo = tt;
		this.numerosSiniestrosUltimoAnios = ns;
		this.inicioVigencia = null;
		this.fechaEmision = null;
		this.finVigencia = null;
		this.formaPago = null;
		this.esPropuesta = null;
		this.estaEmitida = null;
		this.valorDescuentoPorUnidadAdicional = null;
		this.valorPremio = null;
		this.valorPrima = null;
		this.valorDescuento = null;
		this.valorBonificacionPagoSemestral = null;
		this.valorInteresGenero = null;
		this.valorRiesgoCiudad = null;
		this.valorRiesgoModelo = null;
		this.porcentajeValorAsegurado = null;
	}
	
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
	public ParametroPoliza getParametroPoliza() {
		return parametroPoliza;
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
	public Boolean getEsPropuesta() {
		return esPropuesta;
	}
	public Boolean getEstaEmitida() {
		return estaEmitida;
	}
	public Float getValorDescuentoPorUnidadAdicional() {
		return valorDescuentoPorUnidadAdicional;
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
	public Float getValorInteresGenero() {
		return valorInteresGenero;
	}
	public Float getValorRiesgoCiudad() {
		return valorRiesgoCiudad;
	}
	public Float getValorRiesgoModelo() {
		return valorRiesgoModelo;
	}
	public Float getPorcentajeValorAsegurado() {
		return porcentajeValorAsegurado;
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
	public void setParametroPoliza(ParametroPoliza parametroPoliza) {
		this.parametroPoliza = parametroPoliza;
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
	public void setEsPropuesta(Boolean esPropuesta) {
		this.esPropuesta = esPropuesta;
	}
	public void setEstaEmitida(Boolean estaEmitida) {
		this.estaEmitida = estaEmitida;
	}
	public void setValorDescuentoPorUnidadAdicional(Float valorDescuentoPorUnidadAdicional) {
		this.valorDescuentoPorUnidadAdicional = valorDescuentoPorUnidadAdicional;
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
	public void setValorInteresGenero(Float valorInteresGenero) {
		this.valorInteresGenero = valorInteresGenero;
	}
	public void setValorRiesgoCiudad(Float valorRiesgoCiudad) {
		this.valorRiesgoCiudad = valorRiesgoCiudad;
	}
	public void setValorRiesgoModelo(Float valorRiesgoModelo) {
		this.valorRiesgoModelo = valorRiesgoModelo;
	}
	public void setPorcentajeValorAsegurado(Float porcentajeValorAsegurado) {
		this.porcentajeValorAsegurado = porcentajeValorAsegurado;
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
}
