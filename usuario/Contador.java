package com.sistemafinanciero.usuario;

import com.sistemafinanciero.modelo.Cuenta;

import java.util.List;

public class Contador extends Usuario {
    private String numTarjetaProfesional;

    public Contador(int idUsuario, String nombre, String correo, String contrasena, String rol,
                    List<Cuenta> cuentas, String numTarjetaProfesional) {
        super(idUsuario, nombre, correo, contrasena, rol, cuentas);
        this.numTarjetaProfesional = numTarjetaProfesional;
    }

    public String generarReporteMensual() {
        return "Reporte mensual generado por contador.";
    }

    public void auditarCuentas() {
        System.out.println("Cuentas auditadas correctamente.");
    }

    public boolean exportarInformes() {
        System.out.println("Informes exportados por contador.");
        return true;
    }

    public String getNumTarjetaProfesional() { return numTarjetaProfesional; }
}
