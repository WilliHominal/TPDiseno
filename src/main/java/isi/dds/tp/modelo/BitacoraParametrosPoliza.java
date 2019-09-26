package isi.dds.tp.modelo;

import java.sql.Date;
import java.util.List;

public class BitacoraParametrosPoliza {

	private Usuario usuario;
	private List<RiesgoTipoCobertura> riesgosTipoCobertura;
	private List<RiesgoModelo> riesgosModelo;
	private List<RiesgoCiudad> riesgosCiudad;
	private List<ParametroPoliza> parametrosPoliza;
	
	private Integer codigoBitacora;
	private Date fechaModificacion;
	
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
	public Date getFechaModificacion() {
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
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
}
