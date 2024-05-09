package edu.unlam.paradigmas.herencia.ej01;

public class PlazoFijo extends CuentaDeAhorro {
	private static final double INTERES_ANUAL = 36;
	private static final double INTERES_MENSUAL = INTERES_ANUAL/12;
	private static final double FACTOR_INTERES_MENSUAL = INTERES_MENSUAL/100 + 1;
	
	private static final String MOTIVO_TRANSACCION_RESERVA = "reserva_plazo_fijo";
	private static final String MOTIVO_TRANSACCION_ACREDITACION= "acreditacion_plazo_fijo";

	private Cuenta cuenta; 
	private double monto = 0;
	
	public PlazoFijo(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public boolean SetearMonto(double monto) {
		if (this.monto > 0) {
			return false;
		}
		
		if(!this.cuenta.DebitarDineroDisponible(monto, MOTIVO_TRANSACCION_RESERVA)) {
			return false;
		}
		
		this.monto = monto;
		return true;
	}
	
	public void AcreditarMensual() {
		this.cuenta.acreditar(monto * FACTOR_INTERES_MENSUAL, MOTIVO_TRANSACCION_ACREDITACION);
	}

}
