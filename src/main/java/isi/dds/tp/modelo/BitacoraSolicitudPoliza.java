package isi.dds.tp.modelo;

import java.util.*;
import java.time.*;
import javax.persistence.*;

@Entity
@Table
public class BitacoraSolicitudPoliza {

	private Usuario usuario;
	private SolicitudPoliza solicitudPoliza;
	
	private Integer codigoBitacora;
	private LocalDate fechaModificacion;
	private Map<String, Object> atributosModificados;
	
	public BitacoraSolicitudPoliza() {
		
	}
	
	public BitacoraSolicitudPoliza(Usuario usuario, SolicitudPoliza solicitudPoliza, Integer codigo){
		this.usuario = usuario;
		this.solicitudPoliza = solicitudPoliza;
		this.codigoBitacora = codigo;
		this.fechaModificacion = LocalDate.now();
		this.atributosModificados = new HashMap<String, Object>();
	}
	
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
	public Map<String, Object> getAtributosModificados() {
		return atributosModificados;
	}
	public void setFechaModificacion(LocalDate fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/*METODO AÑADIR AL MAP*/
	
}
