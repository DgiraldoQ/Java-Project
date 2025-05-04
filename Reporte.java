package reporte;

import java.util.Date;
import java.util.List;

import modelo.Transaccion;


public abstract class Reporte {
    protected List<Transaccion> transacciones;
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

    public float calcularIngresos() {
        return ingresos;
    }

    public float calcularGastos() {
        return gastos;
    }

    public float calcularSaldo() {
        return saldoFinal;
    }

    public String generarReporte() {
        return "Reporte generado";
    }

    public boolean exportarPDF() {
        // Exportar a PDF
        return true;
    }

    public boolean exportarCSV() {
        // Exportar a CSV
        return true;
    }
}

