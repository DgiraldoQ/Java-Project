package usuario;

import modelo.Cuenta;

import java.util.List;

public class GestorEmpresarial extends Usuario {
    private String nombreEmprendimiento;

    // Constructor
    public GestorEmpresarial(int idUsuario, String nombre, String correo, String contrasena, String rol, 
                             List<Cuenta> cuentas, String nombreEmprendimiento) {
        super(idUsuario, nombre, correo, contrasena, rol, cuentas);
        this.nombreEmprendimiento = nombreEmprendimiento;
    }

    // Métodos
    public void registrarCuenta(Cuenta nuevaCuenta) {
        if (nuevaCuenta != null) {
            getCuentas().add(nuevaCuenta);
            System.out.println("Cuenta registrada con éxito.");
        }
    }

    public float consultarBalance() {
        float balance = 0f;
        for (Cuenta cuenta : getCuentas()) {
            balance += cuenta.obtenerBalance().floatValue();
        }
        return balance;
    }

    public void gestionarCuenta(Cuenta cuenta) {
        if (cuenta != null) {
            System.out.println("Gestionando cuenta del emprendimiento...");
            administrarCuenta(cuenta);
        }
    }

    private void administrarCuenta(Cuenta cuenta) {
        System.out.println("Detalles de la cuenta: " + cuenta.getIdCuenta());
    }

    // Getters y Setters
    public String getNombreEmprendimiento() {
        return nombreEmprendimiento;
    }

    public void setNombreEmprendimiento(String nombreEmprendimiento) {
        this.nombreEmprendimiento = nombreEmprendimiento;
    }
}
