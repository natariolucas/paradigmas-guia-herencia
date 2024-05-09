package edu.unlam.paradigmas.herencia.ej01;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarjetaCredito {
	private static final String MOTIVO_COMPRA_TRANSACCION = "compra";
	private static final double FACTOR_INTERES = 1.03;
	
	private List<Transaccion> transacciones;
	private Cuenta cuenta;
	private double limite;
	private double saldo;
	
	public TarjetaCredito(Cuenta cuenta, double limite) {
		this.cuenta = cuenta;
		this.limite = limite;
		this.saldo = limite;
		this.transacciones = new ArrayList<Transaccion>();
	}
	
	public double getLimite() {return this.limite;}
	
	public boolean Comprar(double monto) {
		if ( !this.SaldoSuficiente(monto) ) {
			return false;
		}
		
		this.DescontarSaldo(monto);
		this.AgregarTransaccion(monto);
		return true;
	}
	
	public boolean DebitarDeCuenta() {
		if (this.transacciones.size() == 0) {
			return false;
		}
		
		double total = this.ObtenerTotalDeTransacciones();
		total = total * FACTOR_INTERES;
		if (!this.cuenta.saldoSuficienteParaDebitar(total)) {
			return false;
		}
		
		if (!this.cuenta.PagarTarjetaCredito(total)) {
			return false;
		}
		
		return true;
		
	}
	
	private double ObtenerTotalDeTransacciones() {
		double total = 0;
		
		for(Transaccion t : this.transacciones) {
			total += t.getMonto();
		}
		
		return total;
	}
	
	private boolean SaldoSuficiente(double monto) {
		return saldo - monto >= 0;
	}
	
	private void DescontarSaldo(double monto) {
		this.saldo -= monto;
	}
	
	private void AgregarTransaccion(double monto) {
		this.transacciones.add(new Transaccion(monto, MOTIVO_COMPRA_TRANSACCION, new Date()));
	}

}
