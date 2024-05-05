package edu.unlam.paradigmas.herencia.ej01;

import java.util.Date;
import java.util.Objects;

public class Transaccion {

	private double monto;
	private String motivo;
	private Date fecha;
	
	public Transaccion(double monto, String motivo, Date fecha) {
		this.monto = monto;
		this.motivo = motivo;
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public String getMotivo() {
		return motivo;
	}

	public Date getFecha() {
		return fecha;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fecha, monto, motivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaccion other = (Transaccion) obj;
		return Objects.equals(fecha, other.fecha)
				&& Double.doubleToLongBits(monto) == Double.doubleToLongBits(other.monto)
				&& Objects.equals(motivo, other.motivo);
	}

}
