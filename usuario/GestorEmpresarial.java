package com.sistemafinanciero.usuario;

import com.sistemafinanciero.modelo.Cuenta;

import java.util.List;

public class GestorEmpresarial extends Usuario {
    private String nombreEmprendimiento;

    public GestorEmpresarial(int idUsuario, String nombre, String correo, String contrasena, String rol,
                             List<Cuenta> cuentas, String nombreEmprendimiento) {
        super(idUsuario, nombre, correo, contrasena, rol, cuentas);
        this.nombreEmprendimiento = nombreEmprendimiento;
    }

    public void registrarCuenta(Cuenta nuevaCuenta) {
        cuentas.add(nuevaCuenta);
        System.out.println("Cuenta registrada exitosamente.");
    }

    public float consultarBalance() {
        return (float) cuentas.stream()
                .map(c -> c.obtenerBalance().floatValue())
                .reduce(0f, Float::sum);
    }

    public void gestionarCuenta(Cuenta cuenta) {
        System.out.println("Gestión de cuenta empresarial: " + cuenta.getIdCuenta());
    }

    public String getNombreEmprendimiento() { return nombreEmprendimiento; }
}
