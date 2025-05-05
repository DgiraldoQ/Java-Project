package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CuentaEmpresarial extends Cuenta {
    private String nombreEmpresa;
    private List<Empleado> empleados;
    private BigDecimal tasaIVA;

    // Constructor
    public CuentaEmpresarial(String titular, String idCuenta, BigDecimal saldo, String nombreEmpresa, BigDecimal tasaIVA) {
        super(titular, idCuenta, saldo);
        this.nombreEmpresa = nombreEmpresa;
        this.tasaIVA = tasaIVA != null ? tasaIVA : BigDecimal.ZERO;
        this.empleados = new ArrayList<>();
    }

    @Override
    public BigDecimal calcularTotalIngresos() {
        BigDecimal totalIngresos = BigDecimal.ZERO;
        for (Transaccion transaccion : getTransacciones()) {
            if (transaccion.getCategoria().equalsIgnoreCase("Ingreso")) {
                totalIngresos = totalIngresos.add(transaccion.getMonto());
            }
        }
        return totalIngresos;
    }

    @Override
    public BigDecimal calcularTotalGastos() {
        BigDecimal totalGastos = BigDecimal.ZERO;
        for (Transaccion transaccion : getTransacciones()) {
            if (transaccion.getCategoria().equalsIgnoreCase("Gasto")) {
                totalGastos = totalGastos.add(transaccion.getMonto());
            }
        }
        return totalGastos;
    }

    @Override
    public BigDecimal obtenerBalance() {
        return getSaldo().subtract(getSaldo().multiply(tasaIVA.divide(BigDecimal.valueOf(100))));
    }

    // Getters y Setters
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados != null ? empleados : new ArrayList<>();
    }

    public BigDecimal getTasaIVA() {
        return tasaIVA;
    }

    public void setTasaIVA(BigDecimal tasaIVA) {
        this.tasaIVA = tasaIVA != null ? tasaIVA : BigDecimal.ZERO;
    }
}

