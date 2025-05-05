package usuario;

import modelo.Cuenta;

import java.util.List;

public class Contador extends Usuario {
    private String numTarjetaProfesional;

    // Constructor
    public Contador(int idUsuario, String nombre, String correo, String contrasena, String rol, 
                    List<Cuenta> cuentas, String numTarjetaProfesional) {
        super(idUsuario, nombre, correo, contrasena, rol, cuentas);
        this.numTarjetaProfesional = numTarjetaProfesional;
    }

    // Métodos
    public String generarReporteMensual() {
        return "Generando reporte mensual del contador...";
    }

    public void auditarCuentas() {
        System.out.println("Auditando cuentas...");
    }

    public boolean exportarInforme() {
        System.out.println("Exportando informe del contador...");
        return true;
    }

    // Getters y Setters
    public String getNumTarjetaProfesional() {
        return numTarjetaProfesional;
    }

    public void setNumTarjetaProfesional(String numTarjetaProfesional) {
        this.numTarjetaProfesional = numTarjetaProfesional;
    }
}

