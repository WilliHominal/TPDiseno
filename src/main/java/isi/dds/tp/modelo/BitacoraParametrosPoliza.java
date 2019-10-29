package isi.dds.tp.modelo;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "bitacora_parametros_poliza")
public class BitacoraParametrosPoliza {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name ="codigo_bitacora")
	@IndexColumn(name ="idx")
	private List<RiesgoTipoCobertura> riesgosTipoCobertura;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name ="codigo_bitacora")
	@IndexColumn(name ="idx")
	private List<RiesgoModelo> riesgosModelo;
	
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name ="codigo_bitacora")
	@IndexColumn(name ="idx")
	private List<RiesgoCiudad> riesgosCiudad;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name ="codigo_bitacora")
	@IndexColumn(name ="idx")
	private List<ParametrosPoliza> parametrosPoliza;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo_bitacora_parametro")
	@SequenceGenerator(name = "codigo_bitacora_parametro", sequenceName = "codigo_bitacora_parametro_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "codigo_bitacora")
	private Integer codigoBitacora;
	
	@Column(nullable = false, name = "fecha_modificacion")
	private LocalDate fechaModificacion;
	
	public BitacoraParametrosPoliza(){ }
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public List<RiesgoTipoCobertura> getRiesgosTipoCobertura() {
		return riesgosTipoCobertura;
	}
	
	public List<RiesgoModelo> getRiesgosModelo() {
		return riesgosModelo;
	}
	
	public List<RiesgoCiudad> getRiesgosCiudad() {
		return riesgosCiudad;
	}
	
	public List<ParametrosPoliza> getParametrosPoliza() {
		return parametrosPoliza;
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
	
	public void setRiesgosTipoCobertura(List<RiesgoTipoCobertura> riesgosTipoCobertura) {
		this.riesgosTipoCobertura = riesgosTipoCobertura;
	}
	
	public void setRiesgosModelo(List<RiesgoModelo> riesgosModelo) {
		this.riesgosModelo = riesgosModelo;
	}
	
	public void setRiesgosCiudad(List<RiesgoCiudad> riesgosCiudad) {
		this.riesgosCiudad = riesgosCiudad;
	}
	
	public void setParametrosPoliza(List<ParametrosPoliza> parametrosPoliza) {
		this.parametrosPoliza = parametrosPoliza;
	}
	
	public void setCodigoBitacora(Integer codigoBitacora) {
		this.codigoBitacora = codigoBitacora;
	}
	
	public void setFechaModificacion(LocalDate fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
}
