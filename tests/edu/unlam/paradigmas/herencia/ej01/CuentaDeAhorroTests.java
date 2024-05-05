package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CuentaDeAhorroTests {
	@Test
	void TestInicializarCuenta() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertEquals(0, cuenta.getSaldo());
		assertEquals(0, cuenta.getSaldoSecundario());
	}
	
	@Test
	void TestDepositoPositivo() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(1000));
		assertEquals(1000, cuenta.getSaldo());
		assertEquals(0, cuenta.getSaldoSecundario());
	}
	
	@Test
	void TestDepositoCeroONegativo() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertFalse(cuenta.Depositar(0));
		assertFalse(cuenta.Depositar(-50));
		assertEquals(100, cuenta.getSaldo());
		assertEquals(0, cuenta.getSaldoSecundario());
	}
	
	@Test
	void TestExtraerDisponible() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertTrue(cuenta.Extraer(10));
		assertEquals(90, cuenta.getSaldo());
		assertEquals(0, cuenta.getSaldoSecundario());
	}
	
	@Test
	void TestExtraerDisponiblePrecision() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertTrue(cuenta.Extraer(10.99993));
		assertEquals(89.00007, cuenta.getSaldo());
		assertEquals(0, cuenta.getSaldoSecundario());
	}
	
	@Test
	void TestExtraerCeroONegativo() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertFalse(cuenta.Extraer(0));
		assertFalse(cuenta.Extraer(-10));
		assertEquals(100, cuenta.getSaldo());
		assertEquals(0, cuenta.getSaldoSecundario());
	}
	
	@Test
	void TestExtraerMasDelDisponible() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertTrue(cuenta.Extraer(90));
		assertFalse(cuenta.Extraer(20));
		assertEquals(10	, cuenta.getSaldo());
		assertEquals(0, cuenta.getSaldoSecundario());
	}
	
	@Test
	void TestTransferenciaMismoTipoExitosa() {
		CuentaDeAhorro cuentaOrigen = new CuentaDeAhorro();
		CuentaDeAhorro cuentaDestino = new CuentaDeAhorro();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertTrue(cuentaOrigen.Transferir(100, cuentaDestino));
		assertEquals(200, cuentaDestino.getSaldo());
		assertEquals(900, cuentaOrigen.getSaldo());
		assertEquals(0, cuentaOrigen.getSaldoSecundario());
		assertEquals(0, cuentaDestino.getSaldoSecundario());
	}
	
	@Test
	void TestTransferenciaMismoTipoExacta() {
		CuentaDeAhorro cuentaOrigen = new CuentaDeAhorro();
		CuentaDeAhorro cuentaDestino = new CuentaDeAhorro();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertTrue(cuentaOrigen.Transferir(1000, cuentaDestino));
		assertEquals(1100, cuentaDestino.getSaldo());
		assertEquals(0, cuentaOrigen.getSaldo());
		assertEquals(0, cuentaDestino.getSaldoSecundario());
	}
	
	@Test
	void TestTransferenciaMismoTipoConPrecision() {
		CuentaDeAhorro cuentaOrigen = new CuentaDeAhorro();
		CuentaDeAhorro cuentaDestino = new CuentaDeAhorro();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertTrue(cuentaOrigen.Transferir(999.0001, cuentaDestino));
		assertEquals(1099.0001, cuentaDestino.getSaldo());
		assertEquals(0.0009, cuentaOrigen.getSaldo(), 3);
		assertEquals(0, cuentaOrigen.getSaldoSecundario());
		assertEquals(0, cuentaDestino.getSaldoSecundario());
	}
	
	@Test
	void TestTransferenciaMismoTipoCero() {
		CuentaDeAhorro cuentaOrigen = new CuentaDeAhorro();
		CuentaDeAhorro cuentaDestino = new CuentaDeAhorro();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(0, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
		assertEquals(0, cuentaOrigen.getSaldoSecundario());
		assertEquals(0, cuentaDestino.getSaldoSecundario());
	}
	
	@Test
	void TestTransferenciaMismoTipoNegativa() {
		CuentaDeAhorro cuentaOrigen = new CuentaDeAhorro();
		CuentaDeAhorro cuentaDestino = new CuentaDeAhorro();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(-200, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
		assertEquals(0, cuentaOrigen.getSaldoSecundario());
		assertEquals(0, cuentaDestino.getSaldoSecundario());
	}
	
	@Test
	void TestTransferenciaMismoTipoMayorAlDisponible() {
		CuentaDeAhorro cuentaOrigen = new CuentaDeAhorro();
		CuentaDeAhorro cuentaDestino = new CuentaDeAhorro();
		
		assertTrue(cuentaOrigen.Depositar(1000));
		assertTrue(cuentaDestino.Depositar(100));
		
		assertFalse(cuentaOrigen.Transferir(99999, cuentaDestino));
		assertEquals(100, cuentaDestino.getSaldo());
		assertEquals(1000, cuentaOrigen.getSaldo());
		assertEquals(0, cuentaOrigen.getSaldoSecundario());
		assertEquals(0, cuentaDestino.getSaldoSecundario());
	}
	
	@Test
	void TestReservarSaldo() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertEquals(100, cuenta.getSaldo());
		
		assertTrue(cuenta.ReservarSaldo(10));
		assertEquals(90, cuenta.getSaldo());
		assertEquals(10, cuenta.getSaldoSecundario());
		
	}
	
	@Test
	void TestReservarSaldoCero() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertEquals(100, cuenta.getSaldo());
		
		assertFalse(cuenta.ReservarSaldo(0));
		assertEquals(100, cuenta.getSaldo());
		assertEquals(0, cuenta.getSaldoSecundario());
		
	}
	
	@Test
	void TestReservarSaldoNegativo() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertEquals(100, cuenta.getSaldo());
		
		assertFalse(cuenta.ReservarSaldo(-10));
		assertEquals(100, cuenta.getSaldo());
		assertEquals(0, cuenta.getSaldoSecundario());
		
	}
	
	@Test
	void TestReservarSaldoMayorAlDisponible() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertEquals(100, cuenta.getSaldo());
		
		assertFalse(cuenta.ReservarSaldo(200));
		assertEquals(100, cuenta.getSaldo());
		assertEquals(0, cuenta.getSaldoSecundario());
	}
	
	@Test
	void TestReintegrarSaldoExitoso() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertEquals(100, cuenta.getSaldo());
		
		assertTrue(cuenta.ReservarSaldo(10));
		assertTrue(cuenta.ReintegrarSaldo(6));
		
		assertEquals(96, cuenta.getSaldo());
		assertEquals(4, cuenta.getSaldoSecundario());	
	}
	
	@Test
	void TestReintegrarSaldoCeroONegativo() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertEquals(100, cuenta.getSaldo());
		
		assertTrue(cuenta.ReservarSaldo(10));
		assertFalse(cuenta.ReintegrarSaldo(0));
		assertFalse(cuenta.ReintegrarSaldo(-8));
		
		assertEquals(90, cuenta.getSaldo());
		assertEquals(10, cuenta.getSaldoSecundario());
	}
	
	@Test
	void TestReintegrarSaldoMayorAlDisponible() {
		CuentaDeAhorro cuenta = new CuentaDeAhorro();
		
		assertTrue(cuenta.Depositar(100));
		assertEquals(100, cuenta.getSaldo());
		
		assertTrue(cuenta.ReservarSaldo(10));
		assertFalse(cuenta.ReintegrarSaldo(20));
		
		assertEquals(90, cuenta.getSaldo());
		assertEquals(10, cuenta.getSaldoSecundario());
	}
	
	
}
