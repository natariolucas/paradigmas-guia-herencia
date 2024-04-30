package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CuentaTests {

	@Test
	void TestInicializarCuenta() {
		Cuenta cuenta = new Cuenta();
		
		assertEquals(0, cuenta.getSaldo());
	}
	
	@Test
	void TestDepositoPositivo() {
		Cuenta cuenta = new Cuenta();
		
		assertTrue(cuenta.Depositar(1000));
		assertEquals(1000, cuenta.getSaldo());
	}
	
	@Test
	void TestDepositoCeroONegativo() {
		Cuenta cuenta = new Cuenta();
		
		assertTrue(cuenta.Depositar(100));
		assertFalse(cuenta.Depositar(0));
		assertFalse(cuenta.Depositar(-50));
		assertEquals(100, cuenta.getSaldo());
	}
	
	@Test
	void TestExtraerDisponible() {
		Cuenta cuenta = new Cuenta();
		
		assertTrue(cuenta.Depositar(100));
		assertTrue(cuenta.Extraer(10));
		assertEquals(90, cuenta.getSaldo());
	}
	
	@Test
	void TestExtraerDisponiblePrecision() {
		Cuenta cuenta = new Cuenta();
		
		assertTrue(cuenta.Depositar(100));
		assertTrue(cuenta.Extraer(10.99993));
		assertEquals(89.00007, cuenta.getSaldo());
	}
	
	@Test
	void TestExtraerCeroONegativo() {
		Cuenta cuenta = new Cuenta();
		
		assertTrue(cuenta.Depositar(100));
		assertFalse(cuenta.Extraer(0));
		assertFalse(cuenta.Extraer(-10));
		assertEquals(100, cuenta.getSaldo());
	}
	
	@Test
	void TestExtraerMasDelDisponible() {
		Cuenta cuenta = new Cuenta();
		
		assertTrue(cuenta.Depositar(100));
		assertTrue(cuenta.Extraer(90));
		assertFalse(cuenta.Extraer(20));
		assertEquals(10	, cuenta.getSaldo());
	}
	
	@Test
	void TestTransferenciaExitosa() {
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertTrue(cuentaOrigen.Transferir(100, cuentaDestino));
		assertEquals(200, cuentaDestino.getSaldo());
		assertEquals(900, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaExacta() {
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertTrue(cuentaOrigen.Transferir(1000, cuentaDestino));
		assertEquals(1100, cuentaDestino.getSaldo());
		assertEquals(0, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaConPrecision() {
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertTrue(cuentaOrigen.Transferir(999.0001, cuentaDestino));
		assertEquals(1099.0001, cuentaDestino.getSaldo());
		assertEquals(0.0009, cuentaOrigen.getSaldo(), 3); // TODO: Otro tipo de dato que solucione la precisión?
	}
	
	@Test
	void TestTransferenciaCero() {
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(0, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaNegativa() {
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(-200, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaMayorAlDisponible() {
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(99999, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
	}

}
