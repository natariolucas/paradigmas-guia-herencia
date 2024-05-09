package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlazoFijoTests {

	@Test
	void TestReservarSaldoSuficiente() {
		Cuenta cuenta = new Cuenta();
		cuenta.Depositar(300);
		
		PlazoFijo plazo = new PlazoFijo(cuenta);
		
		assertTrue(plazo.SetearMonto(200));
		assertEquals(100, cuenta.getSaldo());
	}
	
	@Test
	void TestReservarSaldoInsuficiente() {
		Cuenta cuenta = new Cuenta();
		cuenta.Depositar(150);
		
		PlazoFijo plazo = new PlazoFijo(cuenta);
		
		assertFalse(plazo.SetearMonto(200));
		assertEquals(150, cuenta.getSaldo());
	}
	
	@Test
	void TestAcreditarSaldo() {
		Cuenta cuenta = new Cuenta();
		cuenta.Depositar(100);
		
		PlazoFijo plazo = new PlazoFijo(cuenta);
		
		assertTrue(plazo.SetearMonto(100));
		plazo.AcreditarMensual();
		
		assertEquals(103, cuenta.getSaldo());
	}

}
