package com.sistemafinanciero.usuario;

import com.sistemafinanciero.modelo.Cuenta; 

import java.util.List;

public class Administrador extends Usuario {
    private List<String> permisosEspeciales;

    public Administrador(int idUsuario, String nombre, String correo, String contrasena, String rol,
                         List<Cuenta> cuentas, List<String> permisosEspeciales) {
        super(idUsuario, nombre, correo, contrasena, rol, cuentas);
        this.permisosEspeciales = permisosEspeciales;
    }

    public void administrarUsuarios() {
        System.out.println("Administrando usuarios...");
    }

    public String verEstadisticasSistema() {
        return "Estadísticas del sistema generadas.";
    }

    public void gestionarCuentas() {
        System.out.println("Gestionando todas las cuentas del sistema...");
    }

    public List<String> getPermisosEspeciales() { return permisosEspeciales; }
}