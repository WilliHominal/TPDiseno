package isi.dds.tp.modelo;

import java.time.*;
import java.util.*;
import javax.persistence.*;

public class BitacoraParametrosPoliza {

	private Usuario usuario;
	private List<RiesgoTipoCobertura> riesgosTipoCobertura;
	private List<RiesgoModelo> riesgosModelo;
	private List<RiesgoCiudad> riesgosCiudad;
	private List<ParametroPoliza> parametrosPoliza;
	
	private Integer codigoBitacora;
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
