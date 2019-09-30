package isi.dds.tp.modelo;


import java.time.LocalDate;
import isi.dds.tp.enums.EnumEstadoCuota;
import javax.persistence.*;

@Entity
@Table
public class Cuota {
	
	@ManyToOne
	@JoinColumn(name = "numero_poliza")
	private Poliza poliza;
	
    @OneToOne
    @PrimaryKeyJoinColumn(name = "datos_pago")
	private DatosPago datosPago;
	
	@Id
	@Column(nullable = false, name = "numero_cuota")
	/*TODO  clave compuesta cuota con poliza*/
	private Integer numeroCuota; 
	
	@Column(nullable = false)
	private Float monto;
	
	@Column(nullable = false, name = "ultimo_dia_pago")
	private LocalDate ultimoDiaPago;
	
	@Column(nullable = false)
	private EnumEstadoCuota estado;

	public Cuota() {
		
	}
	
	public Cuota(Poliza poliza, DatosPago datosPago, Integer numeroCuota, Float monto, LocalDate ultimoDiaPago,
			EnumEstadoCuota estado, Boolean fuePagada) {
		this.poliza = poliza;
		this.datosPago = datosPago;
		this.numeroCuota = numeroCuota;
		this.monto = monto;
		this.ultimoDiaPago = ultimoDiaPago;
		this.estado = estado;
	}
	
	public Poliza getPoliza() {
		return poliza;
	}
	public DatosPago getDatosPago() {
		return datosPago;
	}
	public Integer getNumeroCuota() {
		return numeroCuota;
	}
	public Float getMonto() {
		return monto;
	}
	public LocalDate getUltimoDiaPago() {
		return ultimoDiaPago;
	}
	public EnumEstadoCuota getEstado() {
		return estado;
	}
	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}
	public void setDatosPago(DatosPago datosPago) {
		this.datosPago = datosPago;
	}
	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public void setUltimoDiaPago(LocalDate ultimoDiaPago) {
		this.ultimoDiaPago = ultimoDiaPago;
	}
	public void setEstado(EnumEstadoCuota estado) {
		this.estado = estado;
	}
}
