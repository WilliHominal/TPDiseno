package isi.dds.tp.modelo;


import java.time.LocalDate;
import isi.dds.tp.enums.EnumEstadoCuota;
import isi.dds.tp.enums.EnumPagoCuota;

import javax.persistence.*;

@Entity
@Table
public class Cuota{
	
	@ManyToOne
	@JoinColumn(name = "numero_poliza")
	private Poliza poliza;
	
	@ManyToOne
	@JoinColumn(name="numero_recibo")
	private Pago pago;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_cuota")
	@SequenceGenerator(name="id_cuota", sequenceName = "id_cuota_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer idCuota;
	
	@Column(nullable = false)
	private Float monto;
	
	@Column(nullable = false, name = "ultimo_dia_pago")
	private LocalDate ultimoDiaPago;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumEstadoCuota estado;
	
	@Column(name = "bonificacion_pago_adelantado")
	private Float bonificacionPagoAdelantado;
	
	@Column(name = "recargo_por_mora")
	private Float recargoPorMora;
	
	@Column(name = "estado_pago_cuota")
	@Enumerated(EnumType.STRING)
	private EnumPagoCuota estadoPagoCuota;

	public Cuota() {
		
	}
	
	public Poliza getPoliza() {
		return poliza;
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
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public void setUltimoDiaPago(LocalDate ultimoDiaPago) {
		this.ultimoDiaPago = ultimoDiaPago;
	}
	public void setEstado(EnumEstadoCuota estado) {
		this.estado = estado;
	}
	public Pago getPago() {
		return pago;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	public Integer getIdCuota() {
		return idCuota;
	}
	public void setIdCuota(Integer idCuota) {
		this.idCuota = idCuota;
	}
	public Float getBonificacionPagoAdelantado() {
		return bonificacionPagoAdelantado;
	}
	public void setBonificacionPagoAdelantado(Float bonificacionPagoAdelantado) {
		this.bonificacionPagoAdelantado = bonificacionPagoAdelantado;
	}
	public Float getRecargoPorMora() {
		return recargoPorMora;
	}
	public void setRecargoPorMora(Float recargoPorMora) {
		this.recargoPorMora = recargoPorMora;
	}
	public EnumPagoCuota getEstadoPagoCuota() {
		return estadoPagoCuota;
	}
	public void setEstadoPagoCuota(EnumPagoCuota estadoPagoCuota) {
		this.estadoPagoCuota = estadoPagoCuota;
	}
}
