package com.sistemafinanciero.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity 
public class Empleado {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nombre;
    private String apellido;
    private String puesto;
    private Double sueldo;
    
    private String estadoContrato;  // Aquí agregamos el nuevo campo `estadoContrato`

    @ManyToOne // Relación con la clase Usuario, muchas instancias de Empleado pueden tener un Usuario asociado
    private Usuario usuario;

    // Constructor
    public Empleado(String nombre, String apellido, String puesto, Double sueldo, String estadoContrato, Usuario usuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.sueldo = sueldo;
        this.estadoContrato = estadoContrato;  // Inicializamos el nuevo atributo
        this.usuario = usuario;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getEstadoContrato() {
        return estadoContrato;  // Método corregido para retornar `estadoContrato`
    }

    public void setEstadoContrato(String estadoContrato) {
        this.estadoContrato = estadoContrato;  // Método corregido para establecer `estadoContrato`
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Método vacío con un comentario explicativo
    public void metodoSinImplementacion() {
        // Este método está vacío intencionalmente porque se implementará en el futuro, o no es necesario para esta clase.
    }

    // Método para calcular el sueldo del empleado (Ejemplo de implementación)
    public BigDecimal calcularSueldo() {
        // Aquí podrías hacer los cálculos con el sueldo base y otros factores como bonificaciones
        BigDecimal sueldoBase = BigDecimal.valueOf(sueldo);
        // Por ejemplo, agregar un bono fijo de 100
        BigDecimal bono = BigDecimal.valueOf(100);
        return sueldoBase.add(bono);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", puesto='" + puesto + '\'' +
                ", sueldo=" + sueldo +
                ", estadoContrato='" + estadoContrato + '\'' +  // Incluyendo estadoContrato en la cadena
                ", usuario=" + usuario +
                '}';
    }

    // Métodos no implementados (pueden estar aquí como métodos pendientes)
    public Object getCuentaEmpresarial() {
        throw new UnsupportedOperationException("Unimplemented method 'getCuentaEmpresarial'");
    }

    public void setCuentaEmpresarial(Object cuentaEmpresarial) {
        throw new UnsupportedOperationException("Unimplemented method 'setCuentaEmpresarial'");
    }
}