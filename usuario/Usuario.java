package usuario;

import modelo.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;
    private List<Cuenta> cuentas;

    // Constructor
    public Usuario(int idUsuario, String nombre, String correo, String contrasena, String rol, List<Cuenta> cuentas) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.cuentas = cuentas != null ? cuentas : new ArrayList<>();
    }

    // Métodos
    public void iniciarSesion(String correoElectronico, String contrasena) {
        if (this.correo.equals(correoElectronico) && this.contrasena.equals(contrasena)) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas != null ? cuentas : new ArrayList<>();
    }
}



