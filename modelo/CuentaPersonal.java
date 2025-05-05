package modelo;

import java.math.BigDecimal;

public class CuentaPersonal extends Cuenta {
    private BigDecimal tasaImpuesto;

    // Constructor
    public CuentaPersonal(String titular, String idCuenta, BigDecimal saldo, BigDecimal tasaImpuesto) {
        super(titular, idCuenta, saldo);
        this.tasaImpuesto = tasaImpuesto != null ? tasaImpuesto : BigDecimal.ZERO;
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
        return getSaldo().subtract(getSaldo().multiply(tasaImpuesto.divide(BigDecimal.valueOf(100))));
    }

    // Getters y Setters
    public BigDecimal getTasaImpuesto() {
        return tasaImpuesto;
    }

    public void setTasaImpuesto(BigDecimal tasaImpuesto) {
        this.tasaImpuesto = tasaImpuesto != null ? tasaImpuesto : BigDecimal.ZERO;
    }
}
