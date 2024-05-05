package edu.unlam.paradigmas.herencia.ej01;

public class Cuenta {
	protected double saldo = 0;
	
	public static String MOTIVO_TRANSACCION_TRANSFERENCIA = "Transferencia";
	public static String MOTIVO_TRANSACCION_DEPOSITO = "Deposito";
	public static String MOTIVO_TRANSACCION_EXTRACCION = "Extraccion";
	
	public Cuenta() {
		
	}
	
	/**
	 * 
	 * No permite depositar negativo o cero
	 * 
	 * Retorna true en caso de deposito exitoso, 
	 * 	falso si no se pudo depositar (negativo o cero)
	 * 
	 * 
	 * */
	public boolean Depositar(double montoADepositar) {
		if (montoADepositar > 0) {
			this.acreditar(montoADepositar);
			return true;
		}
		
		return false;
	}
	
	protected void acreditar (double monto) {
		this.saldo+= monto;
	}
	
	protected void debitar (double monto) {
		this.saldo -= monto;
	}
	
	protected boolean saldoSuficienteParaDebitar(double monto) {
		return monto > 0 && this.saldo >= monto;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	/**
	 * 
	 * Recibe monto a extraer. No puede ser negativo.
	 * No se permite extraer mayor al disponible.
	 * 
	 * Retorna true en caso de extracción exitosa, 
	 * 	falso si no se pudo extraer (input negativo o mayor al disponible)
	 * 
	 * El deposito de cero se considera exitoso (no negativo)
	 * 
	 * */
	public boolean Extraer(double montoAExtraer) {
		if (this.saldoSuficienteParaDebitar(montoAExtraer)) {
			this.debitar(montoAExtraer);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * No permite transferir ni cero ni negativo, en tal caso devuelve false.
	 * 
	 * El monto a transferir se acredita en la cuenta destino
	 * */
	public boolean Transferir(double montoATransferir, Cuenta cuentaDestino) {
		if(!this.saldoSuficienteParaDebitar(montoATransferir)) {
			return false;
		}
		
		this.debitar(montoATransferir);
		cuentaDestino.acreditar(montoATransferir);
		
		return true;
	}
	

}
