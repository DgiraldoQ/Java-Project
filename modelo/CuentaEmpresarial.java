package com.sistemafinanciero.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CuentaEmpresarial extends Cuenta {
    private String nombreEmpresa;
    private List<Empleado> empleados;
    private BigDecimal tasaIVA;

    public CuentaEmpresarial(String titular, String idCuenta, BigDecimal saldo, String nombreEmpresa, BigDecimal tasaIVA) {
        super(titular, idCuenta, saldo);
        this.nombreEmpresa = nombreEmpresa;
        this.tasaIVA = tasaIVA;
        this.empleados = new ArrayList<>();
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

    public BigDecimal calcularIVA() {
        return saldo.multiply(tasaIVA.divide(BigDecimal.valueOf(100)));
    }

    public BigDecimal calcularNomina() {
        return empleados.stream()
            .map(Empleado::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularDeducciones() {
        return calcularTotalGastos().add(calcularIVA());
    }

    public String generarEstadoFinanciero() {
        return "Ingresos: " + calcularTotalIngresos() + ", Gastos: " + calcularTotalGastos();
    }

    public String generarBalanceGeneral() {
        return "Saldo disponible con IVA: " + obtenerBalance();
    }

    // Getters y setters
    public String getNombreEmpresa() { return nombreEmpresa; }
    public List<Empleado> getEmpleados() { return empleados; }
    public void setEmpleados(List<Empleado> empleados) { this.empleados = empleados; }
    public BigDecimal getTasaIVA() { return tasaIVA; }
    public void setTasaIVA(BigDecimal tasaIVA) { this.tasaIVA = tasaIVA; }
}
