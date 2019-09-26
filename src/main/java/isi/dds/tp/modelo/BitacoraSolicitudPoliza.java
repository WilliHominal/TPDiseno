package isi.dds.tp.modelo;

import java.sql.Date;

public class BitacoraSolicitudPoliza {

	private Usuario usuario;
	private SolicitudPoliza solicitudPoliza;
	
	private Integer codigoBitacora;
	private Date fechaModificacion;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public SolicitudPoliza getSolicitudPoliza() {
		return solicitudPoliza;
	}
	public Integer getCodigoBitacora() {
		return codigoBitacora;
	}
	public Date getFechaModificacion() {
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
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
}
