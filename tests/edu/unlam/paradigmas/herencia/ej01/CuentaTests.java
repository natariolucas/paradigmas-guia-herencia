package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CuentaTests {

	@Test
	void TestInicializarcuenta() {
		Cuenta cuenta = new Cuenta();
		
		assertEquals(0, cuenta.saldo);
	}
	
	@Test
	void TestAcreditacionInicial() {
		Cuenta cuenta = new Cuenta();
		
		cuenta.saldo += 1000; // Acreditar saldo
		
		assertEquals(1000, cuenta.saldo);
	}
	
	@Test
	void TestAcreditacionYRetiro() {
		Cuenta cuenta = new Cuenta();
		
		cuenta.saldo += 1000; // Acreditar saldo
		cuenta.saldo -= 100; // Descontar saldo

		
		assertEquals(900, cuenta.saldo);
	}
	
	@Test
	void TestDescuentoMasDelDisponible() { // Por ahora permite negativo
		Cuenta cuenta = new Cuenta();
		
		cuenta.saldo += 500; // Acreditar saldo
		cuenta.saldo -= 800; // Descontar saldo

		
		assertEquals(-300, cuenta.saldo);
	}

}
