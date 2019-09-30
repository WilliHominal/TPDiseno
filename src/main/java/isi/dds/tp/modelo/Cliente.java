package isi.dds.tp.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import isi.dds.tp.enums.*;
import javax.persistence.*;


public class Cliente {
	
	private Ciudad ciudad;
	private List<Poliza> polizas;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_cliente_ciudad")
	@SequenceGenerator(name="id_cliente_ciudad", sequenceName = "id_cliente_ciudadd_seq", initialValue = 1, allocationSize = 1)
	@Column(nullable = false)
	private Long numeroCliente;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumCondicion condicion; 
	
	@Column(nullable = false)
	private String apellido;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumTipoDocumento tipoDocumento;
	
	@Column(nullable = false)
	private Integer numeroDocumento;
	
	@Column(nullable = false)
	private Long numeroCuil;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumSexo sexo;
	
	@Column(nullable = false)
	private Date fechaNacimiento;
	
	@Column(nullable = false)
	private String calle;
	private Integer numeroCalle; 
	private Integer piso; 
	private String departamento; 
	private Integer codigoPostal;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumCondicionIVA condicionIva;
	
	private String correoElectronico;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumEstadoCivil estadoCivil; 
	private String profesion;
	private Integer anioRegistro;
	
	public Cliente() {

	}
	
	public Cliente(Ciudad ciudad, Long numeroCliente, EnumCondicion condicion, String apellido,
			String nombre, EnumTipoDocumento tipoDocumento, Integer numeroDocumento, Long numeroCuil, EnumSexo sexo,
			Date fechaNacimiento, String calle, Integer numeroCalle, Integer piso, String departamento,
			Integer codigoPostal, EnumCondicionIVA condicionIva, String correoElectronico, EnumEstadoCivil estadoCivil,
			String profesion, Integer anioRegistro) {
		this.ciudad = ciudad;
		this.polizas = new ArrayList<Poliza>();
		
		this.numeroCliente = numeroCliente;
		this.condicion = condicion;
		this.apellido = apellido;
		this.nombre = nombre;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.numeroCuil = numeroCuil;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.calle = calle;
		this.numeroCalle = numeroCalle;
		this.piso = piso;
		this.departamento = departamento;
		this.codigoPostal = codigoPostal;
		this.condicionIva = condicionIva;
		this.correoElectronico = correoElectronico;
		this.estadoCivil = estadoCivil;
		this.profesion = profesion;
		this.anioRegistro = anioRegistro;
	}
	
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
