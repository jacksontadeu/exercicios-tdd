package org.example;

import static org.example.salario.Cargo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.example.salario.CalculadoraSalario;
import org.example.salario.Cargo;
import org.example.salario.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraSalarioTest {

    private CalculadoraSalario calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraSalario();
    }

    // ---------- helper ----------

    private void verifica(Cargo cargo, String salarioBase, String esperado) {
        Funcionario f = new Funcionario("Maria Souza", "maria@empresa.com", new BigDecimal(salarioBase), cargo);
        assertEquals(new BigDecimal(esperado), calculadora.calcularSalarioLiquido(f));
    }

    // ---------- DESENVOLVEDOR (limite 3.000,00: 20% / 10%) ----------

    @Test
    @DisplayName("CT01 - Desenvolvedor com salário abaixo do limite (1000,00) tem 10%")
    void ct01_desenvolvedorAbaixo() {
        verifica(DESENVOLVEDOR, "1000.00", "900.00");
    }

    @Test
    @DisplayName("CT02 - Desenvolvedor logo abaixo do limite (2999,99) tem 10%")
    void ct02_desenvolvedorLogoAbaixo() {
        verifica(DESENVOLVEDOR, "2999.99", "2699.99");
    }

    @Test
    @DisplayName("CT03 - Desenvolvedor exatamente no limite (3000,00) tem 20%")
    void ct03_desenvolvedorNoLimite() {
        verifica(DESENVOLVEDOR, "3000.00", "2400.00");
    }

    @Test
    @DisplayName("CT04 - Desenvolvedor logo acima do limite (3000,01) tem 20%")
    void ct04_desenvolvedorLogoAcima() {
        verifica(DESENVOLVEDOR, "3000.01", "2400.01");
    }

    @Test
    @DisplayName("CT05 - Desenvolvedor com salário bem acima do limite (5000,00) tem 20%")
    void ct05_desenvolvedorAcima() {
        verifica(DESENVOLVEDOR, "5000.00", "4000.00");
    }

    // ---------- DBA (limite 2.000,00: 25% / 15%) ----------

    @Test
    @DisplayName("CT06 - DBA com salário abaixo do limite (1000,00) tem 15%")
    void ct06_dbaAbaixo() {
        verifica(DBA, "1000.00", "850.00");
    }

    @Test
    @DisplayName("CT07 - DBA logo abaixo do limite (1999,99) tem 15%")
    void ct07_dbaLogoAbaixo() {
        verifica(DBA, "1999.99", "1699.99");
    }

    @Test
    @DisplayName("CT08 - DBA exatamente no limite (2000,00) tem 25%")
    void ct08_dbaNoLimite() {
        verifica(DBA, "2000.00", "1500.00");
    }

    @Test
    @DisplayName("CT09 - DBA logo acima do limite (2000,01) tem 25%")
    void ct09_dbaLogoAcima() {
        verifica(DBA, "2000.01", "1500.01");
    }

    @Test
    @DisplayName("CT10 - DBA com salário bem acima do limite (4000,00) tem 25%")
    void ct10_dbaAcima() {
        verifica(DBA, "4000.00", "3000.00");
    }

    // ---------- TESTADOR (limite 2.000,00: 25% / 15%) ----------

    @Test
    @DisplayName("CT11 - Testador com salário abaixo do limite (1000,00) tem 15%")
    void ct11_testadorAbaixo() {
        verifica(TESTADOR, "1000.00", "850.00");
    }

    @Test
    @DisplayName("CT12 - Testador logo abaixo do limite (1999,99) tem 15%")
    void ct12_testadorLogoAbaixo() {
        verifica(TESTADOR, "1999.99", "1699.99");
    }

    @Test
    @DisplayName("CT13 - Testador exatamente no limite (2000,00) tem 25%")
    void ct13_testadorNoLimite() {
        verifica(TESTADOR, "2000.00", "1500.00");
    }

    @Test
    @DisplayName("CT14 - Testador logo acima do limite (2000,01) tem 25%")
    void ct14_testadorLogoAcima() {
        verifica(TESTADOR, "2000.01", "1500.01");
    }

    @Test
    @DisplayName("CT15 - Testador com salário bem acima do limite (4000,00) tem 25%")
    void ct15_testadorAcima() {
        verifica(TESTADOR, "4000.00", "3000.00");
    }

    // ---------- GERENTE (limite 5.000,00: 30% / 20%) ----------

    @Test
    @DisplayName("CT16 - Gerente com salário abaixo do limite (3000,00) tem 20%")
    void ct16_gerenteAbaixo() {
        verifica(GERENTE, "3000.00", "2400.00");
    }

    @Test
    @DisplayName("CT17 - Gerente logo abaixo do limite (4999,99) tem 20%")
    void ct17_gerenteLogoAbaixo() {
        verifica(GERENTE, "4999.99", "3999.99");
    }

    @Test
    @DisplayName("CT18 - Gerente exatamente no limite (5000,00) tem 30%")
    void ct18_gerenteNoLimite() {
        verifica(GERENTE, "5000.00", "3500.00");
    }

    @Test
    @DisplayName("CT19 - Gerente logo acima do limite (5000,01) tem 30%")
    void ct19_gerenteLogoAcima() {
        verifica(GERENTE, "5000.01", "3500.01");
    }

    @Test
    @DisplayName("CT20 - Gerente com salário bem acima do limite (10000,00) tem 30%")
    void ct20_gerenteAcima() {
        verifica(GERENTE, "10000.00", "7000.00");
    }

    // ---------- entradas inválidas (complementares ao enunciado) ----------

    @Test
    @DisplayName("CT21 - Funcionário nulo lança IllegalArgumentException")
    void ct21_funcionarioNulo() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcularSalarioLiquido(null));
    }

    @Test
    @DisplayName("CT22 - Cargo nulo lança IllegalArgumentException")
    void ct22_cargoNulo() {
        Funcionario f = new Funcionario("Maria Souza", "maria@empresa.com", new BigDecimal("3000.00"), null);
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcularSalarioLiquido(f));
    }

    @Test
    @DisplayName("CT23 - Salário-base nulo lança IllegalArgumentException")
    void ct23_salarioNulo() {
        Funcionario f = new Funcionario("Maria Souza", "maria@empresa.com", null, DBA);
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcularSalarioLiquido(f));
    }

    @Test
    @DisplayName("CT24 - Salário-base negativo lança IllegalArgumentException")
    void ct24_salarioNegativo() {
        Funcionario f = new Funcionario("Maria Souza", "maria@empresa.com", new BigDecimal("-1.00"), DBA);
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcularSalarioLiquido(f));
    }
}