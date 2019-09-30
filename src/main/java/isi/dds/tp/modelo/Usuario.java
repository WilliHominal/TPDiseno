package isi.dds.tp.modelo;

import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.enums.EnumTipoUsuario;
import javax.persistence.*;


public class Usuario {
	private List <BitacoraSolicitudPoliza> bitacorasSolicitudPoliza;
	private List <BitacoraParametrosPoliza> bitacoraParametrosPoliza;
	
	@Id
	private Integer idUsuario;
	private String contrasenia;
	private EnumTipoUsuario tipoUsuario;

	public Usuario() {
		
	}
	
	public Usuario(Integer idUsuario, String contrasenia,
			EnumTipoUsuario tipoUsuario) {
		super();
		this.bitacorasSolicitudPoliza = new ArrayList<BitacoraSolicitudPoliza>();
		this.bitacoraParametrosPoliza = new ArrayList<BitacoraParametrosPoliza>();
		this.idUsuario = idUsuario;
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
