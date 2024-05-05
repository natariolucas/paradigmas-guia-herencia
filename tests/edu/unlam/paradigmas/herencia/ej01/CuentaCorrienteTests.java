package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CuentaCorrienteTests {

	@Test
	void TestDescubiertoInicial() {
		CuentaCorriente cuenta = new CuentaCorriente(200);
		
		assertEquals(200, cuenta.getDescubierto());
	}
	
	@Test
	void TestExtraerSinDescubierto() {
		CuentaCorriente cuenta = new CuentaCorriente(0);
		
		assertTrue(cuenta.Depositar(100));
		assertFalse(cuenta.Extraer(101));
		assertEquals(100, cuenta.getSaldo());
	}
	
	@Test
	void TestExtraerConDescubierto() {
		CuentaCorriente cuenta = new CuentaCorriente(10);
		
		assertTrue(cuenta.Depositar(100));
		assertTrue(cuenta.Extraer(101));
		assertEquals(-1, cuenta.getSaldo());
		assertEquals(10, cuenta.getDescubierto());
	}
	
	@Test
	void TestExtraerMayorAlDescubierto() {
		CuentaCorriente cuenta = new CuentaCorriente(10);
		
		// T1
		assertTrue(cuenta.Depositar(100));
		assertTrue(cuenta.Extraer(101));
		assertEquals(-1, cuenta.getSaldo());
		
		assertFalse(cuenta.Extraer(10)); // Daria un saldo de -11
		assertEquals(-1, cuenta.getSaldo());
		assertEquals(10, cuenta.getDescubierto());
	}
	
	@Test
	void TestTransferenciaMismoTipoExitosa() {
		CuentaCorriente cuentaOrigen = new CuentaCorriente(0);
		CuentaCorriente cuentaDestino = new CuentaCorriente(0);
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertTrue(cuentaOrigen.Transferir(100, cuentaDestino));
		assertEquals(200, cuentaDestino.getSaldo());
		assertEquals(900, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaMismoTipoExacta() {
		CuentaCorriente cuentaOrigen = new CuentaCorriente(0);
		CuentaCorriente cuentaDestino = new CuentaCorriente(0);
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertTrue(cuentaOrigen.Transferir(1000, cuentaDestino));
		assertEquals(1100, cuentaDestino.getSaldo());
	}
	
	@Test
	void TestTransferenciaMismoTipoConPrecision() {
		CuentaCorriente cuentaOrigen = new CuentaCorriente(0);
		CuentaCorriente cuentaDestino = new CuentaCorriente(0);
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertTrue(cuentaOrigen.Transferir(999.0001, cuentaDestino));
		assertEquals(1099.0001, cuentaDestino.getSaldo());
		assertEquals(0.0009, cuentaOrigen.getSaldo(), 3);
	}
	
	@Test
	void TestTransferenciaMismoTipoCero() {
		CuentaCorriente cuentaOrigen = new CuentaCorriente(0);
		CuentaCorriente cuentaDestino = new CuentaCorriente(0);
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(0, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaMismoTipoNegativa() {
		CuentaCorriente cuentaOrigen = new CuentaCorriente(0);
		CuentaCorriente cuentaDestino = new CuentaCorriente(0);
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(-200, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaMismoTipoMayorAlDisponible() {
		CuentaCorriente cuentaOrigen = new CuentaCorriente(0);
		CuentaCorriente cuentaDestino = new CuentaCorriente(0);
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(99999, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaMismoTipoConDescubierto() {
		CuentaCorriente cuentaOrigen = new CuentaCorriente(100);
		CuentaCorriente cuentaDestino = new CuentaCorriente(0);
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertTrue(cuentaOrigen.Transferir(1100, cuentaDestino));
		assertEquals(1200, cuentaDestino.getSaldo());
		assertEquals(-100, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaMismoTipoMayorAlDescubierto() {
		CuentaCorriente cuentaOrigen = new CuentaCorriente(100);
		CuentaCorriente cuentaDestino = new CuentaCorriente(0);
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(1101, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaACajaDeAhorroConDescubierto() {
		CuentaCorriente cuentaOrigen = new CuentaCorriente(100);
		CuentaDeAhorro cuentaDestino = new CuentaDeAhorro();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(1101, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
	}
	
	@Test
	void TestTransferenciaACuentaDeAhorroMayorAlDescubierto() {
		CuentaCorriente cuentaOrigen = new CuentaCorriente(100);
		CuentaDeAhorro cuentaDestino = new CuentaDeAhorro();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(1101, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
	}


}
