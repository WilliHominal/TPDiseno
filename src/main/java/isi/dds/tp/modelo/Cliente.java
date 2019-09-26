package isi.dds.tp.modelo;

import java.sql.Date;
import java.util.List;
import isi.dds.tp.enums.*;

public class Cliente {
	
	private Ciudad ciudad;
	private List<Poliza> polizas;
	
	private Long numeroCliente;
	private EnumCondicion condicion; 
	private String apellido;
	private String nombre;
	private EnumTipoDocumento tipoDocumento;
	private Integer numeroDocumento; 
	private Long numeroCuil;
	private EnumSexo sexo;
	private Date fechaNacimiento; 
	private String calle;
	private Integer numeroCalle; 
	private Integer piso; 
	private String departamento; 
	private Integer codigoPostal;
	private EnumCondicionIVA condicionIva; 
	private String correoElectronico;
	private EnumEstadoCivil estadoCivil; 
	private String profesion;
	private Integer anioRegistro;
	
	public Ciudad getCiudad() {
		return ciudad;
	}
	public List<Poliza> getPolizas() {
		return polizas;
	}
	public Long getNumeroCliente() {
		return numeroCliente;
	}
	public EnumCondicion getCondicion() {
		return condicion;
	}
	public String getApellido() {
		return apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public EnumTipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}
	public Long getNumeroCuil() {
		return numeroCuil;
	}
	public EnumSexo getSexo() {
		return sexo;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getCalle() {
		return calle;
	}
	public Integer getNumeroCalle() {
		return numeroCalle;
	}
	public Integer getPiso() {
		return piso;
	}
	public String getDepartamento() {
		return departamento;
	}
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public EnumCondicionIVA getCondicionIva() {
		return condicionIva;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public EnumEstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public String getProfesion() {
		return profesion;
	}
	public Integer getAnioRegistro() {
		return anioRegistro;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public void setPolizas(List<Poliza> polizas) {
		this.polizas = polizas;
	}
	public void setNumeroCliente(Long numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	public void setCondicion(EnumCondicion condicion) {
		this.condicion = condicion;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTipoDocumento(EnumTipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public void setNumeroCuil(Long numeroCuil) {
		this.numeroCuil = numeroCuil;
	}
	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public void setNumeroCalle(Integer numeroCalle) {
		this.numeroCalle = numeroCalle;
	}
	public void setPiso(Integer piso) {
		this.piso = piso;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public void setCondicionIva(EnumCondicionIVA condicionIva) {
		this.condicionIva = condicionIva;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public void setEstadoCivil(EnumEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public void setAnioRegistro(Integer anioRegistro) {
		this.anioRegistro = anioRegistro;
	} 
}
