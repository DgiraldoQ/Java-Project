package com.sistemafinanciero.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "reporte", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaccion> transacciones;

    private Float ingresos;
    private Float gastos;
    private Float saldoFinal;
    private String fechaInicio;
    private String fechaFin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public Float getIngresos() {
        return ingresos;
    }

    public void setIngresos(Float ingresos) {
        this.ingresos = ingresos;
    }

    public Float getGastos() {
        return gastos;
    }

    public void setGastos(Float gastos) {
        this.gastos = gastos;
    }

    public Float getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(Float saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Object fechaProperty() {
        throw new UnsupportedOperationException("Unimplemented method 'fechaProperty'");
    }

    public Object ingresosProperty() {
        throw new UnsupportedOperationException("Unimplemented method 'ingresosProperty'");
    }

    public Object gastosProperty() {
        throw new UnsupportedOperationException("Unimplemented method 'gastosProperty'");
    }

    public Object saldoFinalProperty() {
        throw new UnsupportedOperationException("Unimplemented method 'saldoFinalProperty'");
    }
}