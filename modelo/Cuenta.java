package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    private String titular;
    private String idCuenta;
    private BigDecimal saldo;
    private List<Transaccion> transacciones;

    // Constructor
    public Cuenta(String titular, String idCuenta, BigDecimal saldo) {
        this.titular = titular;
        this.idCuenta = idCuenta;
        this.saldo = saldo != null ? saldo : BigDecimal.ZERO; // Manejo de saldo nulo
        this.transacciones = new ArrayList<>();
    }

    // Métodos principales
    public void agregarTransaccion(Transaccion transaccion) {
        if (transaccion != null) {
            transacciones.add(transaccion);
            saldo = saldo.add(transaccion.getMonto()); // Actualizar saldo
        }
    }

    public BigDecimal obtenerBalance() {
        return saldo;
    }

    public abstract BigDecimal calcularTotalIngresos();
    public abstract BigDecimal calcularTotalGastos();

    // Getters y Setters
    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo != null ? saldo : BigDecimal.ZERO;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        if (transacciones != null) {
            this.transacciones = transacciones;
        }
    }
}

