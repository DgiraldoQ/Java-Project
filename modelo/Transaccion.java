package com.sistemafinanciero.modelo;

import com.sistemafinanciero.util.MetodoPago;
import com.sistemafinanciero.util.TipoTransaccion;

import java.math.BigDecimal;
import java.util.Date;

public class Transaccion {
    private String idTransaccion;
    private String categoria;
    private MetodoPago metodoPago;
    private BigDecimal monto;
    private TipoTransaccion tipoTransaccion;
    private Date fecha;
    private String descripcion;

    public Transaccion(String idTransaccion, String categoria, MetodoPago metodoPago, BigDecimal monto,
                       TipoTransaccion tipoTransaccion, Date fecha, String descripcion) {
        this.idTransaccion = idTransaccion;
        this.categoria = categoria;
        this.metodoPago = metodoPago;
        this.monto = monto != null ? monto : BigDecimal.ZERO;
        this.tipoTransaccion = tipoTransaccion;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public String obtenerDetalles() {
        return "Transacción: " + idTransaccion + ", Monto: " + monto + ", Fecha: " + fecha;
    }

    public boolean esIngreso() {
        return tipoTransaccion == TipoTransaccion.INGRESO;
    }

    // Getters y setters
    public String getIdTransaccion() { return idTransaccion; }
    public void setIdTransaccion(String idTransaccion) { this.idTransaccion = idTransaccion; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public TipoTransaccion getTipoTransaccion() { return tipoTransaccion; }
    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
