package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CuentaTests {

	@Test
	void TestInicializarcuenta() {
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
		assertEquals(10, cuenta.getSaldo());
	}

}
