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
		if (this.saldoSuficienteParaDebitar(montoAReservar)) {
			this.debitar(montoAReservar);
			this.acreditarSaldoSecundario(montoAReservar);;
			
			return true;
		}
		
		return false;
	}
	
	private void acreditarSaldoSecundario(double monto) {
		this.saldoSecundario += monto;
	}
	
	private void debitarSaldoSecundario(double monto) {
		this.saldoSecundario -= monto;
	}
	
	protected boolean saldoSecundarioSuficienteParaDebitar(double monto) {
		return monto > 0 && this.saldoSecundario >= monto;
	}
	
	/**
	 * No permite reintegrar cero ni negativo
	 * El monto a reintegrar se descuenta del secundario y se suma al saldo.
	 * 
	 * No se puede reintegrar un monto mayor al saldo secundario
	 * */
	public boolean ReintegrarSaldo(double montoAReintegrar) {
		if (this.saldoSecundarioSuficienteParaDebitar(montoAReintegrar)) {
			this.debitarSaldoSecundario(montoAReintegrar);;
			this.acreditar(montoAReintegrar);
			
			return true;
		}
		
		return false;
	}
	
	public double getSaldoSecundario() {
		return this.saldoSecundario;
	}
}
