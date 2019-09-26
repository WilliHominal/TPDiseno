package isi.dds.tp.modelo;

import isi.dds.tp.enums.EnumPagoCuota;

public class DatosPago {
	
	private Cuota cuota;
	private Pago pago;

	private Float bonificacionPagoAdelantado;
	private Float recargoPorMora;
	private EnumPagoCuota estadoPagoCuota;
	
	public Cuota getCuota() {
		return cuota;
	}
	public Pago getPago() {
		return pago;
	}
	public Float getBonificacionPagoAdelantado() {
		return bonificacionPagoAdelantado;
	}
	public Float getRecargoPorMora() {
		return recargoPorMora;
	}
	public EnumPagoCuota getEstadoPagoCuota() {
		return estadoPagoCuota;
	}
	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	public void setBonificacionPagoAdelantado(Float bonificacionPagoAdelantado) {
		this.bonificacionPagoAdelantado = bonificacionPagoAdelantado;
	}
	public void setRecargoPorMora(Float recargoPorMora) {
		this.recargoPorMora = recargoPorMora;
	}
	public void setEstadoPagoCuota(EnumPagoCuota estadoPagoCuota) {
		this.estadoPagoCuota = estadoPagoCuota;
	}	
}
