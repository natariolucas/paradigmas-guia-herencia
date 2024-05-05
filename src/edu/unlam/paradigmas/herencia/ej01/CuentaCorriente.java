package edu.unlam.paradigmas.herencia.ej01;

public class CuentaCorriente extends Cuenta {

	private double descubierto;

	public CuentaCorriente(double descubierto) {
		super();

		this.descubierto = descubierto;
	}

	public double getDescubierto() {
		return this.descubierto;
	}

	@Override
	public boolean saldoSuficienteParaDebitar(double monto) {
		return monto > 0 && this.saldo - monto >= -this.descubierto;
	}
}
