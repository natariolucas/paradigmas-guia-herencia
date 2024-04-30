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

	/**
	 * 
	 * Recibe monto a extraer. No puede ser negativo.
	 * No se permite extraer mayor al disponible+descubierto.
	 * 
	 * Retorna true en caso de extracción exitosa, 
	 * 	falso si no se pudo extraer (input negativo o mayor al disponible)
	 * 
	 * El deposito de cero se considera exitoso (no negativo)
	 * 
	 * @Override
	 * 
	 * */
	public boolean Extraer(double montoAExtraer) {
		if (montoAExtraer > 0 && this.saldo-montoAExtraer >= -this.descubierto) {
			this.saldo -= montoAExtraer;
			return true;
		}
		
		return false;
	}
}
