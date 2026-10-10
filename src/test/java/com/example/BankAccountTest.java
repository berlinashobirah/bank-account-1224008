package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private static final double DELTA = 0.001;
    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
    }

    @Test
    void saldoAwal_adalahNol() {
        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    void constructorDenganSaldoAwal_saldoSesuai() {
        BankAccount acc = new BankAccount(50000);
        assertEquals(50000.0, acc.getBalance(), DELTA);
    }

    @Test
    void constructorSaldoAwalNegatif_throwException() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount(-1));
    }

    @Test
    void deposit_menambahSaldo() {
        account.deposit(100000);
        assertEquals(100000.0, account.getBalance(), DELTA);
    }

    @Test
    void deposit_beberapaKali_saldoAkumulasi() {
        account.deposit(50000);
        account.deposit(25000);
        assertEquals(75000.0, account.getBalance(), DELTA);
    }

    @Test
    void deposit_nol_saldoTidakBerubah() {
        account.deposit(0);
        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    void deposit_negatif_throwException() {
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(-100));
    }

    @Test
    void withdraw_mengurangiSaldo() {
        account.deposit(100000);
        account.withdraw(40000);
        assertEquals(60000.0, account.getBalance(), DELTA);
    }

    @Test
    void withdraw_seluruhSaldo_saldoMenjadiNol() {
        account.deposit(100000);
        account.withdraw(100000);
        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    void withdraw_melebihiSaldo_throwException_saldoTetap() {
        account.deposit(50000);
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(60000));
        assertEquals(50000.0, account.getBalance(), DELTA);
    }

    @Test
    void withdraw_negatif_throwException() {
        account.deposit(50000);
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(-1));
    }
}
