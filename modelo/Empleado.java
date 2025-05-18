package com.sistemafinanciero.modelo;

import java.math.BigDecimal;

public class Empleado {
    private String idEmpleado;
    private String nombre;
    private String apellido;
    private BigDecimal salario;
    private String estadoContrato;

    public Empleado(String idEmpleado, String nombre, String apellido, BigDecimal salario, String estadoContrato) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario != null ? salario : BigDecimal.ZERO;
        this.estadoContrato = estadoContrato;
    }

    public String obtenerDetalles() {
        return nombre + " " + apellido + ", Estado del contrato: " + estadoContrato;
    }

    public BigDecimal calcularSalarioAnual() {
        return salario.multiply(BigDecimal.valueOf(12));
    }

    // Getters y setters
    public String getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(String idEmpleado) { this.idEmpleado = idEmpleado; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
    public String getEstadoContrato() { return estadoContrato; }
    public void setEstadoContrato(String estadoContrato) { this.estadoContrato = estadoContrato; }
}