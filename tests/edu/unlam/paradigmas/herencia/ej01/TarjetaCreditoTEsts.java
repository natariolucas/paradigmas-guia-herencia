package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TarjetaCreditoTEsts {

	@Test
	void TestCompraLimiteSuficiente() {
		Cuenta cuenta = new CuentaDeAhorro();
		TarjetaCredito tarjeta = new TarjetaCredito(cuenta, 100);
		
		assertTrue(tarjeta.Comprar(100));
	}
	
	@Test
	void TestCompraLimiteInsuficiente() {
		Cuenta cuenta = new CuentaDeAhorro();
		TarjetaCredito tarjeta = new TarjetaCredito(cuenta, 100);
		
		assertFalse(tarjeta.Comprar(200));
	}
	
	@Test
	void TestPagoTarjetaSaldoSuficiente() {
		Cuenta cuenta = new CuentaDeAhorro();
		cuenta.Depositar(500);
		
		TarjetaCredito tarjeta = new TarjetaCredito(cuenta, 300);
		assertTrue(tarjeta.Comprar(50));
		assertTrue(tarjeta.Comprar(50));
		assertTrue(tarjeta.Comprar(80));
		
		assertTrue(tarjeta.DebitarDeCuenta());
		assertEquals(314.6, cuenta.getSaldo());
	}
	
	@Test
	void TestPagoTarjetaSaldoInSuficiente() {
		Cuenta cuenta = new CuentaDeAhorro();
		cuenta.Depositar(50);
		
		TarjetaCredito tarjeta = new TarjetaCredito(cuenta, 300);
		assertTrue(tarjeta.Comprar(50));
		assertTrue(tarjeta.Comprar(50));
		assertTrue(tarjeta.Comprar(80));
		
		assertFalse(tarjeta.DebitarDeCuenta());
		assertEquals(50, cuenta.getSaldo());
	}

}
