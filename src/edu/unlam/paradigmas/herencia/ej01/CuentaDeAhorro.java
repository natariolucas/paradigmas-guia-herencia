package edu.unlam.paradigmas.herencia.ej01;

public class CuentaDeAhorro extends Cuenta {

	private double saldoSecundario = 0;
	
	public CuentaDeAhorro() {
		super();
	}
	
	/**
	 * No permite reservar cero ni negativo
	 * El monto a reservar se descuenta del saldo y se suma al saldo secundario
	 * */
	public boolean ReservarSaldo(double montoAReservar) {
		if (montoAReservar > 0 && this.saldo >= montoAReservar) {
			this.saldo -= montoAReservar;
			this.saldoSecundario += montoAReservar;
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * No permite reintegrar cero ni negativo
	 * El monto a reintegrar se descuenta del secundario y se suma al saldo.
	 * 
	 * No se puede reintegrar un monto mayor al saldo secundario
	 * */
	public boolean ReintegrarSaldo(double montoAReintegrar) {
		if (montoAReintegrar > 0 && this.saldoSecundario >= montoAReintegrar) {
			this.saldoSecundario -= montoAReintegrar;
			this.saldo += montoAReintegrar;
			
			return true;
		}
		
		return false;
	}
	
	public double getSaldoSecundario() {
		return this.saldoSecundario;
	}
}
