package com.sistemafinanciero.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected String titular;
    protected String idCuenta;
    protected BigDecimal saldo;
    protected List<Transaccion> transacciones;

    public Cuenta(String titular, String idCuenta, BigDecimal saldo) {
        this.titular = titular;
        this.idCuenta = idCuenta;
        this.saldo = saldo != null ? saldo : BigDecimal.ZERO;
        this.transacciones = new ArrayList<>();
    }

    public void agregarTransaccion(Transaccion t) {
        if (t != null) {
            transacciones.add(t);
            saldo = saldo.add(t.getMonto());
        }
    }

    public void eliminarTransaccion(Transaccion t) {
        if (transacciones.remove(t)) {
            saldo = saldo.subtract(t.getMonto());
        }
    }

    public abstract BigDecimal calcularTotalIngresos();
    public abstract BigDecimal calcularTotalGastos();

    public Transaccion buscarTransaccion(String id) {
        for (Transaccion t : transacciones) {
            if (t.getIdTransaccion().equals(id)) return t;
        }
        return null;
    }

    public BigDecimal obtenerBalance() {
        return saldo;
    }

    // Getters y setters
    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }

    public String getIdCuenta() { return idCuenta; }
    public void setIdCuenta(String idCuenta) { this.idCuenta = idCuenta; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public List<Transaccion> getTransacciones() { return transacciones; }
}