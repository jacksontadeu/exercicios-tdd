package org.example.salario;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraSalario {

    private static final BigDecimal CEM = BigDecimal.valueOf(100);

    public BigDecimal calcularSalarioLiquido(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não informado");
        }
        if (funcionario.getCargo() == null) {
            throw new IllegalArgumentException("Cargo não informado");
        }
        BigDecimal salarioBase = funcionario.getSalarioBase();
        if (salarioBase == null || salarioBase.signum() < 0) {
            throw new IllegalArgumentException("Salário-base inválido");
        }

        int desconto = funcionario.getCargo().percentualDesconto(salarioBase);
        return salarioBase
                .multiply(BigDecimal.valueOf(100 - desconto))
                .divide(CEM, 2, RoundingMode.HALF_UP);
    }
}