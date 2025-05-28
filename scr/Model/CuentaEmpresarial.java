package com.sistemafinanciero.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class CuentaEmpresarial extends Cuenta {

    private String tipo;
    private BigDecimal impuesto;

    public CuentaEmpresarial() {
        super();
    }

    public CuentaEmpresarial(String titular, BigDecimal saldo, String tipo, BigDecimal impuesto) {
        super(titular, saldo);
        this.tipo = tipo;
        this.impuesto = impuesto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
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

    public void setNombreEmpresa(Object nombre) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setNombreEmpresa'");
    }
}
