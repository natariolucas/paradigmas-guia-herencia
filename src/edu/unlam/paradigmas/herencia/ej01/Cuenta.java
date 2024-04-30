package edu.unlam.paradigmas.herencia.ej01;

public class Cuenta {
	private float saldo = 0;
	
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
	public boolean Depositar(float montoADepositar) {
		if (montoADepositar > 0) {
			this.saldo += montoADepositar;
			return true;
		}
		
		return false;
	}
	
	public float getSaldo() {
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
	public boolean Extraer(float montoAExtraer) {
		if (montoAExtraer > 0 && this.saldo >= montoAExtraer) {
			this.saldo -= montoAExtraer;
			return true;
		}
		
		return false;
	}

}
