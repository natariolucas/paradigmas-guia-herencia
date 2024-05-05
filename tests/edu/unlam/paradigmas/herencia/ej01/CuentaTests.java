package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

class CuentaTests {

	@Test
	void TestInicializarCuenta() {
		Cuenta cuenta = new Cuenta();
		
		assertEquals(0, cuenta.getSaldo());
		assertEquals(0, cuenta.getTransacciones().size());
	}
	
	@Test
	void TestDepositoPositivo() {
		Cuenta cuenta = new Cuenta();
		
		boolean depositoMil = cuenta.Depositar(1000);
		Transaccion transaccionDeposito = ObtenerTransaccionDeposito(1000);
		ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
		transacciones.add(transaccionDeposito);
		
		assertTrue(depositoMil);
		assertEquals(1000, cuenta.getSaldo());
		assertEquals(transacciones, cuenta.getTransacciones());
	}
	
	@Test
	void TestDepositoCeroONegativo() {
		Cuenta cuenta = new Cuenta();
		ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
		
		boolean depositoCien = cuenta.Depositar(100);
		Transaccion transaccionDepositoCien = ObtenerTransaccionDeposito(100);
		transacciones.add(transaccionDepositoCien);
		
		boolean depositoCero = cuenta.Depositar(0);
		
		boolean depositoMenosCincuenta = cuenta.Depositar(-50); 
		
		
		assertTrue(depositoCien);
		assertFalse(depositoCero);
		assertFalse(depositoMenosCincuenta);
		assertEquals(100, cuenta.getSaldo());
		assertEquals(1, cuenta.getTransacciones().size());
		assertEquals(transacciones, cuenta.getTransacciones());
	}
	
	@Test
	void TestExtraerDisponible() {
		Cuenta cuenta = new Cuenta();
		ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
		
		boolean depositoCien = cuenta.Depositar(100);
		Transaccion transaccionDepositoCien = ObtenerTransaccionDeposito(100);
		transacciones.add(transaccionDepositoCien);
		
		boolean extraigoDiez = cuenta.Extraer(10);
		Transaccion transaccionesExtraigoDiez = ObtenerTransaccionExtraccion(10);
		transacciones.add(transaccionesExtraigoDiez);
		
		assertTrue(depositoCien);
		assertTrue(extraigoDiez);
		assertEquals(90, cuenta.getSaldo());
		assertEquals(2, cuenta.getTransacciones().size());
		assertEquals(transacciones, cuenta.getTransacciones());
	}
	
	@Test
	void TestExtraerDisponiblePrecision() {
		Cuenta cuenta = new Cuenta();
		ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
		
		boolean depositoCien = cuenta.Depositar(100);
		Transaccion transaccionDepositoCien = ObtenerTransaccionDeposito(100);
		transacciones.add(transaccionDepositoCien);
		
		boolean extraigoDiezPrecision = cuenta.Extraer(10.99993);
		Transaccion transaccionExtraigoDiezPrecision = ObtenerTransaccionExtraccion(10.99993);
		transacciones.add(transaccionExtraigoDiezPrecision);
		
		assertTrue(depositoCien);
		assertTrue(extraigoDiezPrecision);
		assertEquals(89.00007, cuenta.getSaldo());
		assertEquals(transacciones, cuenta.getTransacciones());
	}
	
	@Test
	void TestExtraerCeroONegativo() {
		Cuenta cuenta = new Cuenta();
		ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();

		boolean depositoCien = cuenta.Depositar(100);
		Transaccion transaccionDepositoCien = ObtenerTransaccionDeposito(100);
		transacciones.add(transaccionDepositoCien);
		
		boolean extraigoCero = cuenta.Extraer(0);
		
		boolean extraigoMenosDiez = cuenta.Extraer(-10);
		
		assertTrue(depositoCien);
		assertFalse(extraigoCero);
		assertFalse(extraigoMenosDiez);
		assertEquals(100, cuenta.getSaldo());
		assertEquals(transacciones, cuenta.getTransacciones());
	}
	
	@Test
	void TestExtraerMasDelDisponible() {
		Cuenta cuenta = new Cuenta();
		ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
		
		boolean depositoCien = cuenta.Depositar(100);
		Transaccion transaccionDepositoCien = ObtenerTransaccionDeposito(100);
		transacciones.add(transaccionDepositoCien);
		
		boolean extraigoNoventa = cuenta.Extraer(90);
		Transaccion transaccionExtraigoNoventa = ObtenerTransaccionExtraccion(90);
		transacciones.add(transaccionExtraigoNoventa);
		
		boolean extraigoVeinte = cuenta.Extraer(20);
		
		assertTrue(depositoCien);
		assertTrue(extraigoNoventa);
		assertFalse(extraigoVeinte);
		assertEquals(10	, cuenta.getSaldo());
		assertEquals(transacciones, cuenta.getTransacciones());
	}
	
	@Test
	void TestTransferenciaExitosa() {
		Cuenta cuentaOrigen = new Cuenta();
		ArrayList<Transaccion> transaccionesOrigen = new ArrayList<Transaccion>();

		Cuenta cuentaDestino = new Cuenta();
		ArrayList<Transaccion> transaccionesDestino = new ArrayList<Transaccion>();

		boolean depositoMilOrigen = cuentaOrigen.Depositar(1000);
		Transaccion transaccionDepositoMilOrigen = ObtenerTransaccionDeposito(1000);
		transaccionesOrigen.add(transaccionDepositoMilOrigen);
		
		boolean depositoCienDestino = cuentaDestino.Depositar(100);
		Transaccion transaccionDepositoCienDestino = ObtenerTransaccionDeposito(100);
		transaccionesDestino.add(transaccionDepositoCienDestino);
		
		boolean transfieroCien = cuentaOrigen.Transferir(100, cuentaDestino);
		Transaccion transaccionTransferenciaDebito = ObtenerTransaccionTransferenciaDebito(100);
		Transaccion transaccionTransferenciaAcreditacion = ObtenerTransaccionTransferenciaAcreditacion(100);
		transaccionesOrigen.add(transaccionTransferenciaDebito);
		transaccionesDestino.add(transaccionTransferenciaAcreditacion);
		
		assertTrue(depositoMilOrigen);
		assertTrue(depositoCienDestino);
		assertTrue(transfieroCien);
		assertEquals(200, cuentaDestino.getSaldo());
		assertEquals(900, cuentaOrigen.getSaldo());
		assertEquals(transaccionesOrigen, cuentaOrigen.getTransacciones());
		assertEquals(transaccionesDestino, cuentaDestino.getTransacciones());
	}
	
	@Test
	void TestTransferenciaExacta() {
		Cuenta cuentaOrigen = new Cuenta();
		ArrayList<Transaccion> transaccionesOrigen = new ArrayList<Transaccion>();

		Cuenta cuentaDestino = new Cuenta();
		ArrayList<Transaccion> transaccionesDestino = new ArrayList<Transaccion>();

		boolean depositoMilOrigen = cuentaOrigen.Depositar(1000);
		Transaccion transaccionDepositoMilOrigen = ObtenerTransaccionDeposito(1000);
		transaccionesOrigen.add(transaccionDepositoMilOrigen);
		
		boolean depositoCienDestino = cuentaDestino.Depositar(100);
		Transaccion transaccionDepositoCienDestino = ObtenerTransaccionDeposito(100);
		transaccionesDestino.add(transaccionDepositoCienDestino);
		
		boolean transfieroMil = cuentaOrigen.Transferir(1000, cuentaDestino);
		Transaccion transaccionTransferenciaDebito = ObtenerTransaccionTransferenciaDebito(1000);
		Transaccion transaccionTransferenciaAcreditacion = ObtenerTransaccionTransferenciaAcreditacion(1000);
		transaccionesOrigen.add(transaccionTransferenciaDebito);
		transaccionesDestino.add(transaccionTransferenciaAcreditacion);
		
		
		assertTrue(depositoMilOrigen);
		assertTrue(depositoCienDestino);
		assertTrue(transfieroMil);
		assertEquals(1100, cuentaDestino.getSaldo());
		assertEquals(0, cuentaOrigen.getSaldo());
		assertEquals(transaccionesOrigen, cuentaOrigen.getTransacciones());
		assertEquals(transaccionesDestino, cuentaDestino.getTransacciones());
	}
	
	@Test
	void TestTransferenciaConPrecision() {
		Cuenta cuentaOrigen = new Cuenta();
		ArrayList<Transaccion> transaccionesOrigen = new ArrayList<Transaccion>();

		Cuenta cuentaDestino = new Cuenta();
		ArrayList<Transaccion> transaccionesDestino = new ArrayList<Transaccion>();
		
		boolean depositoMilOrigen = cuentaOrigen.Depositar(1000);
		Transaccion transaccionDepositoMilOrigen = ObtenerTransaccionDeposito(1000);
		transaccionesOrigen.add(transaccionDepositoMilOrigen);
		
		boolean depositoCienDestino = cuentaDestino.Depositar(100);
		Transaccion transaccionDepositoCienDestino = ObtenerTransaccionDeposito(100);
		transaccionesDestino.add(transaccionDepositoCienDestino);
		
		boolean transfieroConPrecision = cuentaOrigen.Transferir(999.0001, cuentaDestino);
		Transaccion transaccionTransferenciaDebito = ObtenerTransaccionTransferenciaDebito(999.0001);
		Transaccion transaccionTransferenciaAcreditacion = ObtenerTransaccionTransferenciaAcreditacion(999.0001);
		transaccionesOrigen.add(transaccionTransferenciaDebito);
		transaccionesDestino.add(transaccionTransferenciaAcreditacion);
		
		assertTrue(depositoMilOrigen);
		assertTrue(depositoCienDestino);
		assertTrue(transfieroConPrecision);
		assertEquals(1099.0001, cuentaDestino.getSaldo());
		assertEquals(0.0009, cuentaOrigen.getSaldo(), 3);
		assertEquals(transaccionesOrigen, cuentaOrigen.getTransacciones());
		assertEquals(transaccionesDestino, cuentaDestino.getTransacciones());
	}
	
	@Test
	void TestTransferenciaCero() {
		Cuenta cuentaOrigen = new Cuenta();
		ArrayList<Transaccion> transaccionesOrigen = new ArrayList<Transaccion>();

		Cuenta cuentaDestino = new Cuenta();
		ArrayList<Transaccion> transaccionesDestino = new ArrayList<Transaccion>();
		
		boolean depositoMilOrigen = cuentaOrigen.Depositar(1000);
		Transaccion transaccionDepositoMilOrigen = ObtenerTransaccionDeposito(1000);
		transaccionesOrigen.add(transaccionDepositoMilOrigen);
		
		boolean depositoCienDestino = cuentaDestino.Depositar(100);
		Transaccion transaccionDepositoCienDestino = ObtenerTransaccionDeposito(100);
		transaccionesDestino.add(transaccionDepositoCienDestino);
		
		boolean transfieroCero = cuentaOrigen.Transferir(0, cuentaDestino);
		
		assertTrue(depositoMilOrigen);
		assertTrue(depositoCienDestino);
		
		assertFalse(transfieroCero);
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
		assertEquals(transaccionesOrigen, cuentaOrigen.getTransacciones());
		assertEquals(transaccionesDestino, cuentaDestino.getTransacciones());
	}
	
	@Test
	void TestTransferenciaNegativa() {
		Cuenta cuentaOrigen = new Cuenta();
		ArrayList<Transaccion> transaccionesOrigen = new ArrayList<Transaccion>();

		Cuenta cuentaDestino = new Cuenta();
		ArrayList<Transaccion> transaccionesDestino = new ArrayList<Transaccion>();
		
		boolean depositoMilOrigen = cuentaOrigen.Depositar(1000);
		Transaccion transaccionDepositoMilOrigen = ObtenerTransaccionDeposito(1000);
		transaccionesOrigen.add(transaccionDepositoMilOrigen);
		
		boolean depositoCienDestino = cuentaDestino.Depositar(100);
		Transaccion transaccionDepositoCienDestino = ObtenerTransaccionDeposito(100);
		transaccionesDestino.add(transaccionDepositoCienDestino);
		
		boolean transfieroNegativo = cuentaOrigen.Transferir(-200, cuentaDestino);
		
		assertTrue(depositoMilOrigen);
		assertTrue(depositoCienDestino);
		assertFalse(transfieroNegativo);
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
		assertEquals(transaccionesOrigen, cuentaOrigen.getTransacciones());
		assertEquals(transaccionesDestino, cuentaDestino.getTransacciones());
	}
	
	@Test
	void TestTransferenciaMayorAlDisponible() {
		Cuenta cuentaOrigen = new Cuenta();
		ArrayList<Transaccion> transaccionesOrigen = new ArrayList<Transaccion>();

		Cuenta cuentaDestino = new Cuenta();
		ArrayList<Transaccion> transaccionesDestino = new ArrayList<Transaccion>();
		
		boolean depositoMilOrigen = cuentaOrigen.Depositar(1000);
		Transaccion transaccionDepositoMilOrigen = ObtenerTransaccionDeposito(1000);
		transaccionesOrigen.add(transaccionDepositoMilOrigen);
		
		boolean depositoCienDestino = cuentaDestino.Depositar(100);
		Transaccion transaccionDepositoCienDestino = ObtenerTransaccionDeposito(100);
		transaccionesDestino.add(transaccionDepositoCienDestino);
		
		boolean transfieroConExceso = cuentaOrigen.Transferir(99999, cuentaDestino);
		
		assertTrue(depositoMilOrigen);
		assertTrue(depositoCienDestino);
		
		assertFalse(transfieroConExceso);
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
		assertEquals(transaccionesOrigen, cuentaOrigen.getTransacciones());
		assertEquals(transaccionesDestino, cuentaDestino.getTransacciones());
	}

	private static Transaccion ObtenerTransaccionDeposito(double monto) {
		return new Transaccion(monto, Cuenta.MOTIVO_TRANSACCION_DEPOSITO, new Date());
	}
	
	private static Transaccion ObtenerTransaccionExtraccion(double monto) {
		return new Transaccion(monto, Cuenta.MOTIVO_TRANSACCION_EXTRACCION, new Date());
	}
	
	private static Transaccion ObtenerTransaccionTransferenciaDebito(double monto) {
		return new Transaccion(monto, Cuenta.MOTIVO_TRANSACCION_TRANSFERENCIA_DEBITO, new Date());
	}
	
	private static Transaccion ObtenerTransaccionTransferenciaAcreditacion(double monto) {
		return new Transaccion(monto, Cuenta.MOTIVO_TRANSACCION_TRANSFERENCIA_ACREDITACION, new Date());
	}
}
