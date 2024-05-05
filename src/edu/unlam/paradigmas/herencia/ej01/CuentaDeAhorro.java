package edu.unlam.paradigmas.herencia.ej01;

public class CuentaDeAhorro extends Cuenta {

	private static final String MOTIVO_TRANSACCION_RESERVA_DE_SALDO = "reserva";
	private static final String MOTIVO_TRANSACCION_REINTEGRO_DE_SALDO = "reintegro de reserva";
	
	private double saldoSecundario = 0;
	
	/**
	 * No permite reservar cero ni negativo
	 * El monto a reservar se descuenta del saldo y se suma al saldo secundario
	 * */
	public boolean ReservarSaldo(double montoAReservar) {
		if (this.saldoSuficienteParaDebitar(montoAReservar)) {
			this.debitar(montoAReservar, MOTIVO_TRANSACCION_RESERVA_DE_SALDO);
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
			this.acreditar(montoAReintegrar, MOTIVO_TRANSACCION_REINTEGRO_DE_SALDO);
			
			return true;
		}
		
		return false;
	}
	
	public double getSaldoSecundario() {
		return this.saldoSecundario;
	}
}
