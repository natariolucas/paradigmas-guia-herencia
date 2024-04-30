package edu.unlam.paradigmas.herencia.ej01;

public class Cuenta {
	protected double saldo = 0;
	
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
			this.saldo += montoADepositar;
			return true;
		}
		
		return false;
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
		if (montoAExtraer > 0 && this.saldo >= montoAExtraer) {
			this.saldo -= montoAExtraer;
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
		if (!this.Extraer(montoATransferir) ) {
			return false;
		}
		
		cuentaDestino.Depositar(montoATransferir);
		
		return true;
	}

}
