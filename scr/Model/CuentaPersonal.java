package com.sistemafinanciero.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class CuentaPersonal extends Cuenta {

    private String tipo;

    public CuentaPersonal() {
        super();
    }

    public CuentaPersonal(String titular, BigDecimal saldo, String tipo) {
        super(titular, saldo);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public BigDecimal calcularTotalIngresos() {
        return getTransacciones().stream()
            .filter(t -> "INGRESO".equalsIgnoreCase(t.getTipo()))
            .map(Transaccion::getMonto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal calcularTotalGastos() {
        return getTransacciones().stream()
            .filter(t -> "GASTO".equalsIgnoreCase(t.getTipo()))
            .map(Transaccion::getMonto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Object getNombre() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }

    public void setNombreTitular(Object nombre) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setNombreTitular'");
    }
}