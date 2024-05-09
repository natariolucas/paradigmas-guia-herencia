package edu.unlam.paradigmas.herencia.ej01;

import java.util.ArrayList;
import java.util.Date;

public class Cuenta {
	public static final String MOTIVO_TRANSACCION_DEPOSITO = "deposito";
	public static final String MOTIVO_TRANSACCION_EXTRACCION = "extraccion";
	public static final String MOTIVO_TRANSACCION_TRANSFERENCIA_DEBITO = "transferencia_debito";
	public static final String MOTIVO_TRANSACCION_TRANSFERENCIA_ACREDITACION = "transferencia_acreditacion";
	public static final String MOTIVO_TRANSACCION_COMPRA_DEBITO = "compra_debito";
	public static final String MOTIVO_TRANSACCION_PAGO_TARJETA_CREDITO= "pago_tarjeta_credito";
	
	protected double saldo = 0;
	protected ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
	
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
			this.acreditar(montoADepositar, MOTIVO_TRANSACCION_DEPOSITO);
			return true;
		}
		
		return false;
	}
	
	protected void acreditar (double monto, String motivo) {
		this.saldo+= monto;
		this.pushTransaccion(monto, motivo);
	}
	
	protected void debitar (double monto, String motivo) {
		this.saldo -= monto;
		this.pushTransaccion(monto, motivo);
	}
	
	private void pushTransaccion(double monto, String motivo) {
		this.transacciones.add(
				new Transaccion(
						monto,
						motivo,
						new Date()
				)
		);
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
		return this.DebitarDineroDisponible(montoAExtraer, MOTIVO_TRANSACCION_EXTRACCION);
	}
	
	public boolean CompraDebito(double montoAExtraer) {
		return this.DebitarDineroDisponible(montoAExtraer, MOTIVO_TRANSACCION_COMPRA_DEBITO);
	}
	
	public boolean PagarTarjetaCredito(double monto) {
		return this.DebitarDineroDisponible(monto, MOTIVO_TRANSACCION_PAGO_TARJETA_CREDITO);
	}
	
	protected boolean DebitarDineroDisponible(double monto, String motivo) {
		if (this.saldoSuficienteParaDebitar(monto)) {
			this.debitar(monto, motivo);
			
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
		
		this.debitar(montoATransferir, MOTIVO_TRANSACCION_TRANSFERENCIA_DEBITO);
		cuentaDestino.acreditar(montoATransferir, MOTIVO_TRANSACCION_TRANSFERENCIA_ACREDITACION);
		
		return true;
	}
	
	public ArrayList<Transaccion> getTransacciones() {
		return this.transacciones;
	}
}
