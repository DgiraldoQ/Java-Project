package com.sistemafinanciero.reporte;

import java.util.Date;
import java.util.List;

import com.sistemafinanciero.modelo.Transaccion;

import java.util.ArrayList;

public abstract class Reporte {
    protected List<Transaccion> transacciones = new ArrayList<>();
    protected float ingresos;
    protected float gastos;
    protected float saldoFinal;
    protected Date fechaInicio;
    protected Date fechaFin;

    public void agregarTransaccion(Transaccion t) {
        transacciones.add(t);
    }

    public void eliminarTransaccion(Transaccion t) {
        transacciones.remove(t);
    }

    public List<Transaccion> obtenerTransacciones() {
        return transacciones;
    }

    public float calcularIngresos() { return ingresos; }
    public float calcularGastos() { return gastos; }
    public float calcularSaldo() { return saldoFinal; }

    public String generarReporte() {
        return "Reporte general generado.";
    }

    public boolean exportarPDF() { return true; }
    public boolean exportarCSV() { return true; }
}

