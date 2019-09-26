package isi.dds.tp.modelo;

public class ParamSolicituMod {

	private BitacoraSolicitudPoliza bitacoraSolicitud;
	
	private Integer clave;

	public BitacoraSolicitudPoliza getBitacoraSolicitud() {
		return bitacoraSolicitud;
	}

	public Integer getClave() {
		return clave;
	}

	public void setBitacoraSolicitud(BitacoraSolicitudPoliza bitacoraSolicitud) {
		this.bitacoraSolicitud = bitacoraSolicitud;
	}

	public void setClave(Integer clave) {
		this.clave = clave;
	}
	
}
