package org.example.salario;

import java.math.BigDecimal;

public enum Cargo {

    //            limite       desconto >= limite   desconto < limite
    DESENVOLVEDOR("3000.00", 20, 10),
    DBA("2000.00", 25, 15),
    TESTADOR("2000.00", 25, 15),
    GERENTE("5000.00", 30, 20);

    private final BigDecimal limite;
    private final int descontoAcimaDoLimite;
    private final int descontoAbaixoDoLimite;

    Cargo(String limite, int descontoAcimaDoLimite, int descontoAbaixoDoLimite) {
        this.limite = new BigDecimal(limite);
        this.descontoAcimaDoLimite = descontoAcimaDoLimite;
        this.descontoAbaixoDoLimite = descontoAbaixoDoLimite;
    }

    /** Percentual de desconto (0-100) aplicável ao salário-base informado. */
    public int percentualDesconto(BigDecimal salarioBase) {
        return salarioBase.compareTo(limite) >= 0 ? descontoAcimaDoLimite : descontoAbaixoDoLimite;
    }
}