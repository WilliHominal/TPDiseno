package isi.dds.tp.modelo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bitacora_solicitud_poliza")
public class BitacoraSolicitudPoliza {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="numero_solicitud")
	private SolicitudPoliza solicitudPoliza;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo_bitacora_solicitud")
	@SequenceGenerator(name="codigo_bitacora_solicitud", sequenceName = "codigo_bitacora_solicitud_seq", initialValue = 45, allocationSize = 3)
	@Column(nullable = false, name = "codigo_bitacora")
	private Integer codigoBitacora;
	
	@Column(nullable = false, name = "fecha_modificacion")
	private LocalDate fechaModificacion;
	
	@Column(nullable = false, name = "mod_patenete")
	private Boolean modificoPatente = false;
	
	@Column(nullable = false, name = "mod_motor")
	private Boolean modificoMotor = false;
	
	@Column(nullable = false, name = "mod_chasis")
	private Boolean modificoChasis = false;
	
	@Column(nullable = false, name = "mod_km_realizados")
	private Boolean modificoKmRealizados = false;
	
	@Column(nullable = false, name = "mod_num_siniestros")
	private Boolean modificoNumerosSiniestros = false;
		
	@Column(nullable = false, name = "mod_tiene_alarma")
	private Boolean modificoTieneAlarma = false;
	
	@Column(nullable = false, name = "mod_guarda_garage")
	private Boolean modificoGuardaGarage = false;
	
	@Column(nullable = false, name = "mod_tiene_tuercas")
	private Boolean modificoTieneTuercas = false;
	
	@Column(nullable = false, name = "mod_tiene_rastreo")
	private Boolean modificoTieneRastreo = false;
	
	@Column(nullable = false, name = "mod_tipo_cobertura")
	private Boolean modificoTipoCobertura = false; 
	
	public BitacoraSolicitudPoliza() { }
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public SolicitudPoliza getSolicitudPoliza() {
		return solicitudPoliza;
	}
	
	public Integer getCodigoBitacora() {
		return codigoBitacora;
	}
	
	public LocalDate getFechaModificacion() {
		return fechaModificacion;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void setSolicitudPoliza(SolicitudPoliza solicitudPoliza) {
		this.solicitudPoliza = solicitudPoliza;
	}
	
	public void setCodigoBitacora(Integer codigoBitacora) {
		this.codigoBitacora = codigoBitacora;
	}
	
	public void setFechaModificacion(LocalDate fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Boolean getModificoPatente() {
		return modificoPatente;
	}

	public Boolean getModificoMotor() {
		return modificoMotor;
	}

	public Boolean getModificoChasis() {
		return modificoChasis;
	}

	public Boolean getModificoKmRealizados() {
		return modificoKmRealizados;
	}

	public Boolean getModificoNumerosSiniestros() {
		return modificoNumerosSiniestros;
	}

	public Boolean getModificoTieneAlarma() {
		return modificoTieneAlarma;
	}

	public Boolean getModificoGuardaGarage() {
		return modificoGuardaGarage;
	}

	public Boolean getModificoTieneTuercas() {
		return modificoTieneTuercas;
	}

	public Boolean getModificoTieneRastreo() {
		return modificoTieneRastreo;
	}

	public Boolean getModificoTipoCobertura() {
		return modificoTipoCobertura;
	}

	public void setModificoPatente(Boolean modificoPatente) {
		this.modificoPatente = modificoPatente;
	}

	public void setModificoMotor(Boolean modificoMotor) {
		this.modificoMotor = modificoMotor;
	}

	public void setModificoChasis(Boolean modificoChasis) {
		this.modificoChasis = modificoChasis;
	}

	public void setModificoKmRealizados(Boolean modificoKmRealizados) {
		this.modificoKmRealizados = modificoKmRealizados;
	}

	public void setModificoNumerosSiniestros(Boolean modificoNumerosSiniestros) {
		this.modificoNumerosSiniestros = modificoNumerosSiniestros;
	}

	public void setModificoTieneAlarma(Boolean modificoTieneAlarma) {
		this.modificoTieneAlarma = modificoTieneAlarma;
	}

	public void setModificoGuardaGarage(Boolean modificoGuardaGarage) {
		this.modificoGuardaGarage = modificoGuardaGarage;
	}

	public void setModificoTieneTuercas(Boolean modificoTieneTuercas) {
		this.modificoTieneTuercas = modificoTieneTuercas;
	}

	public void setModificoTieneRastreo(Boolean modificoTieneRastreo) {
		this.modificoTieneRastreo = modificoTieneRastreo;
	}

	public void setModificoTipoCobertura(Boolean modificoTipoCobertura) {
		this.modificoTipoCobertura = modificoTipoCobertura;
	}
}
