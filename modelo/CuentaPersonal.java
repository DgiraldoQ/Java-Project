package com.sistemafinanciero.modelo;

import java.math.BigDecimal;

public class CuentaPersonal extends Cuenta {
    private BigDecimal tasaImpuesto;

    public CuentaPersonal(String titular, String idCuenta, BigDecimal saldo, BigDecimal tasaImpuesto) {
        super(titular, idCuenta, saldo);
        this.tasaImpuesto = tasaImpuesto;
    }

    @Override
    public BigDecimal calcularTotalIngresos() {
        return getTransacciones().stream()
            .filter(Transaccion::esIngreso)
            .map(Transaccion::getMonto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal calcularTotalGastos() {
        return getTransacciones().stream()
            .filter(t -> !t.esIngreso())
            .map(Transaccion::getMonto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularImpuestos() {
        return saldo.multiply(tasaImpuesto.divide(BigDecimal.valueOf(100)));
    }

    public BigDecimal calcularSaldoDisponible() {
        return saldo.subtract(calcularImpuestos());
    }

    public boolean alertarExcesoGastos() {
        return calcularTotalGastos().compareTo(calcularTotalIngresos()) > 0;
    }

    // Getters y setters
    public BigDecimal getTasaImpuesto() { return tasaImpuesto; }
    public void setTasaImpuesto(BigDecimal tasaImpuesto) { this.tasaImpuesto = tasaImpuesto; }
}