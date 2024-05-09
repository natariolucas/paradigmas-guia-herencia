package edu.unlam.paradigmas.herencia.ej01;

public class TarjetaDebito {
	private Cuenta cuenta;
	
	public TarjetaDebito(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public boolean Comprar(double monto) {
		return this.cuenta.CompraDebito(monto);
	}

}
