package com.sistemafinanciero.model;

import com.sistemafinanciero.util.Rol;
import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String rut;
    private String tipoCuenta;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private String empresa;

    public Usuario() {
    }

    // Constructor corregido con el punto y coma faltante
    public Usuario(String nombre, String apellido, String rut, String tipoCuenta, Rol rol, String empresa) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.tipoCuenta = tipoCuenta;
        this.rol = rol;
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Método getNombre corregido (no es necesario usar value() para un String)
    public String getNombre() {
        return nombre;  // Retornamos directamente el valor de 'nombre' ya que es un String
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;  // Asignamos el valor directamente
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Object getEmail() {
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }

    public void setEmail(Object email) {
        throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
    }
}