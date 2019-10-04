package isi.dds.tp.modelo;

import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.enums.EnumTipoUsuario;
import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Usuario {
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="id_usuario")
	@IndexColumn(name ="idx")
	private List <BitacoraSolicitudPoliza> bitacorasSolicitudPoliza;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="id_usuario")
	@IndexColumn(name ="idx")
	private List <BitacoraParametrosPoliza> bitacoraParametrosPoliza;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_usuario")
	@SequenceGenerator(name = "id_usuario", sequenceName = "id_usuario_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "id_usuario")
	private Integer idUsuario;
	
	@Column(nullable = false)
	private String contrasenia;
	
	@Column(nullable = false, name = "tipo_usuario")
	private EnumTipoUsuario tipoUsuario;

	public Usuario() {
		
	}
	
	public Usuario(String contrasenia, EnumTipoUsuario tipoUsuario) {
		this.bitacorasSolicitudPoliza = new ArrayList<BitacoraSolicitudPoliza>();
		this.bitacoraParametrosPoliza = new ArrayList<BitacoraParametrosPoliza>();
		this.contrasenia = contrasenia;
		this.tipoUsuario = tipoUsuario;
	}
	
	public List<BitacoraSolicitudPoliza> getBitacorasSolicitudPoliza() {
		return bitacorasSolicitudPoliza;
	}
	
	public List<BitacoraParametrosPoliza> getBitacoraParametrosPoliza() {
		return bitacoraParametrosPoliza;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public EnumTipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	public void setBitacorasSolicitudPoliza(List<BitacoraSolicitudPoliza> bitacorasSolicitudPoliza) {
		this.bitacorasSolicitudPoliza = bitacorasSolicitudPoliza;
	}
	
	public void setBitacoraParametrosPoliza(List<BitacoraParametrosPoliza> bitacoraParametrosPoliza) {
		this.bitacoraParametrosPoliza = bitacoraParametrosPoliza;
	}
	
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public void setTipoUsuario(EnumTipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	} 
}
