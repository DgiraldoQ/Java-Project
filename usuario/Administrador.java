package usuario;

import modelo.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {
    private List<String> permisosEspeciales;

    // Constructor
    public Administrador(int idUsuario, String nombre, String correo, String contrasena, String rol, 
                         List<Cuenta> cuentas, List<String> permisosEspeciales) {
        super(idUsuario, nombre, correo, contrasena, rol, cuentas);
        this.permisosEspeciales = permisosEspeciales != null ? permisosEspeciales : new ArrayList<>();
    }

    // Métodos
    public void administrarUsuarios() {
        System.out.println("Administrando usuarios...");
    }

    public void verificarEstadosSistema() {
        System.out.println("Verificando estado del sistema...");
    }

    public void gestionarCuentas() {
        System.out.println("Gestionando cuentas...");
    }

    // Getters y Setters
    public List<String> getPermisosEspeciales() {
        return permisosEspeciales;
    }

    public void setPermisosEspeciales(List<String> permisosEspeciales) {
        this.permisosEspeciales = permisosEspeciales;
    }
}