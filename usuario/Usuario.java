package com.sistemafinanciero.usuario;

import com.sistemafinanciero.modelo.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    protected int idUsuario;
    protected String nombre;
    protected String correo;
    protected String contrasena;
    protected String rol;
    protected List<Cuenta> cuentas;

    public Usuario(int idUsuario, String nombre, String correo, String contrasena, String rol, List<Cuenta> cuentas) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.cuentas = cuentas != null ? cuentas : new ArrayList<>();
    }

    public void iniciarSesion(String correoElectronico, String contrasena) {
        if (this.correo.equals(correoElectronico) && this.contrasena.equals(contrasena)) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    public void cerrarSesion() {
        System.out.println("Cierre de sesión exitoso.");
    }

    public List<Cuenta> obtenerCuentas() {
        return cuentas;
    }

    public void administrarCuenta(Cuenta cuenta) {
        System.out.println("Gestionando cuenta: " + cuenta.getIdCuenta());
    }

    // Getters
    public int getIdUsuario() { return idUsuario; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getRol() { return rol; }
    public List<Cuenta> getCuentas() { return cuentas; }
}