package isi.dds.tp.modelo;

import java.time.*;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "bitacora_parametros_poliza")
public class BitacoraParametrosPoliza {

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="codigo_bitacora")
	@IndexColumn(name ="idx")
	private List<RiesgoTipoCobertura> riesgosTipoCobertura;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="codigo_bitacora")
	@IndexColumn(name ="idx")
	private List<RiesgoModelo> riesgosModelo;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name ="codigo_bitacora")
	@IndexColumn(name ="idx")
	private List<RiesgoCiudad> riesgosCiudad;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="codigo_bitacora")
	@IndexColumn(name ="idx")
	private List<ParametroPoliza> parametrosPoliza;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo_bitacora_parametro")
	@SequenceGenerator(name = "codigo_bitacora_parametro", sequenceName = "codigo_bitacora_parametro_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "codigo_bitacora")
	private Integer codigoBitacora;
	
	@Column(nullable = false, name = "fecha_modificacion")
	private LocalDate fechaModificacion;
	
	public BitacoraParametrosPoliza(){
		
	}
	
	public BitacoraParametrosPoliza(Usuario usuario, Integer codigo){
		this.usuario = usuario;
		this.codigoBitacora = codigo;
		this.fechaModificacion = LocalDate.now();
		this.riesgosTipoCobertura = new ArrayList<RiesgoTipoCobertura>();
		this.riesgosModelo = new ArrayList<RiesgoModelo>();
		this.riesgosCiudad = new ArrayList<RiesgoCiudad>();
	}
	
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
	public List<ParametroPoliza> getParametrosPoliza() {
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
	public void setParametrosPoliza(List<ParametroPoliza> parametrosPoliza) {
		this.parametrosPoliza = parametrosPoliza;
	}
	public void setCodigoBitacora(Integer codigoBitacora) {
		this.codigoBitacora = codigoBitacora;
	}
	public void setFechaModificacion(LocalDate fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
}
