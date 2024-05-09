package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TarjetaDebitoTests {

	@Test
	void TestCompraAceptada() {
		Cuenta cuenta = new CuentaDeAhorro();
		cuenta.Depositar(100);
		
		TarjetaDebito tarjeta = new TarjetaDebito(cuenta);
		assertTrue(tarjeta.Comprar(20));
	}
	
	@Test
	void TestCompraRechazada() {
		Cuenta cuenta = new CuentaDeAhorro();
		cuenta.Depositar(100);
		
		TarjetaDebito tarjeta = new TarjetaDebito(cuenta);
		assertFalse(tarjeta.Comprar(200));
	}

}
