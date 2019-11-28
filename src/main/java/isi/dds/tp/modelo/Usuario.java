package isi.dds.tp.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import isi.dds.tp.enums.EnumTipoUsuario;

@Entity
@Table
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_usuario")
	@SequenceGenerator(name = "id_usuario", sequenceName = "id_usuario_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "id_usuario")
	private Integer idUsuario;
	
	@Column(nullable = false)
	private String contrasenia;
	
	@Column(nullable = false, name = "tipo_usuario")
	private EnumTipoUsuario tipoUsuario;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String apellido;

	public Usuario() { }
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public EnumTipoUsuario getTipoUsuario() {
		return tipoUsuario;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	} 
}
