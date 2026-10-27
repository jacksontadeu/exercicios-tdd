package org.example.salario;

import java.math.BigDecimal;

public class Funcionario {

    private final String nome;
    private final String email;
    private final BigDecimal salarioBase;
    private final Cargo cargo;

    public Funcionario(String nome, String email, BigDecimal salarioBase, Cargo cargo) {
        this.nome = nome;
        this.email = email;
        this.salarioBase = salarioBase;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public Cargo getCargo() {
        return cargo;
    }
}