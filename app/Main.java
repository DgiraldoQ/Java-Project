package app;

import modelo.*;
import usuario.*;
import util.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear cuentas
        CuentaPersonal cuentaPersonal = new CuentaPersonal(
                "Juan Pérez", "CP123", BigDecimal.valueOf(15000), BigDecimal.valueOf(10));

        CuentaEmpresarial cuentaEmpresarial = new CuentaEmpresarial(
                "Ana Gómez", "CE456", BigDecimal.valueOf(100000), "TechCorp", BigDecimal.valueOf(16));

        // Crear transacciones
        Transaccion ingreso1 = new Transaccion(
                "T001", "Ingreso", MetodoPago.EFECTIVO, BigDecimal.valueOf(5000),
                TipoTransaccion.INGRESO, new Date(), "Pago freelance");

        Transaccion gasto1 = new Transaccion(
                "T002", "Gasto", MetodoPago.TARJETA, BigDecimal.valueOf(2000),
                TipoTransaccion.GASTO, new Date(), "Compra de suministros");

        // Agregar transacciones a las cuentas
        cuentaPersonal.agregarTransaccion(ingreso1);
        cuentaPersonal.agregarTransaccion(gasto1);

        cuentaEmpresarial.agregarTransaccion(new Transaccion(
                "T003", "Ingreso", MetodoPago.TRANSFERENCIA, BigDecimal.valueOf(15000),
                TipoTransaccion.INGRESO, new Date(), "Pago cliente X"));

        cuentaEmpresarial.agregarTransaccion(new Transaccion(
                "T004", "Gasto", MetodoPago.PAYPAL, BigDecimal.valueOf(8000),
                TipoTransaccion.GASTO, new Date(), "Publicidad"));

        // Crear usuarios
        List<Cuenta> cuentasUsuario1 = new ArrayList<>();
        cuentasUsuario1.add(cuentaPersonal);

        Usuario usuario1 = new Usuario(
                1, "Juan Pérez", "juan.perez@mail.com", "password123", "usuario", cuentasUsuario1);

        List<Cuenta> cuentasUsuario2 = new ArrayList<>();
        cuentasUsuario2.add(cuentaEmpresarial);

        Administrador admin = new Administrador(
                2, "Admin1", "admin@mail.com", "adminpass", "admin", cuentasUsuario2,
                List.of("GESTIONAR_USUARIOS", "GESTIONAR_CUENTAS"));

        Contador contador = new Contador(
                3, "Carlos Ramírez", "carlos@mail.com", "contador123", "contador", cuentasUsuario2,
                "TP123456");

        // Flujo principal
        System.out.println("==== SISTEMA DE GESTIÓN FINANCIERA ====");
        
        // Inicio de sesión (simulado)
        usuario1.iniciarSesion("juan.perez@mail.com", "password123");

        // Consultar balance de la cuenta personal
        System.out.println("Balance de la cuenta personal de " + usuario1.getNombre() + ": "
                + cuentaPersonal.obtenerBalance());

        // Generar reporte del contador
        System.out.println(contador.generarReporteMensual());
        contador.auditarCuentas();

        // Administrador gestiona usuarios
        admin.administrarUsuarios();
        admin.verificarEstadosSistema();

        // Consultar balance total del gestor empresarial
        GestorEmpresarial gestor = new GestorEmpresarial(
                4, "Luis Torres", "luis@mail.com", "gestor123", "gestor",
                cuentasUsuario2, "StartupTech");

        System.out.println("Balance total del emprendimiento: " + gestor.consultarBalance());

        // Registrar nueva cuenta para el gestor empresarial
        CuentaPersonal nuevaCuenta = new CuentaPersonal(
                "Luis Torres", "CP789", BigDecimal.valueOf(30000), BigDecimal.valueOf(15));

        gestor.registrarCuenta(nuevaCuenta);

        System.out.println("Nueva cuenta registrada. Total de cuentas del gestor: " + gestor.getCuentas().size());

        System.out.println("==== FIN DEL SISTEMA ====");
    }
}
