package com.sistemafinanciero.app;

import com.sistemafinanciero.modelo.*;
import com.sistemafinanciero.usuario.*;
import com.sistemafinanciero.reporte.*;
import com.sistemafinanciero.util.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.info("===== SISTEMA CONTABLE - DEMO PRINCIPAL =====");

        // Cuentas
        CuentaPersonal cuentaPersonal = new CuentaPersonal(
                "Juan Pérez", "CP001", new BigDecimal("15000.00"), new BigDecimal("10"));

        CuentaEmpresarial cuentaEmpresarial = new CuentaEmpresarial(
                "Ana Gómez", "CE001", new BigDecimal("100000.00"), "TechCorp", new BigDecimal("16"));

        // Transacciones
        Transaccion ingreso1 = new Transaccion(
                "T001", "Ingreso", MetodoPago.EFECTIVO, new BigDecimal("5000.00"),
                TipoTransaccion.INGRESO, new Date(), "Venta producto A");

        Transaccion gasto1 = new Transaccion(
                "T002", "Gasto", MetodoPago.TARJETA, new BigDecimal("2000.00"),
                TipoTransaccion.GASTO, new Date(), "Compra insumos");

        Transaccion ingresoEmp = new Transaccion(
                "T003", "Ingreso", MetodoPago.TRANSFERENCIA, new BigDecimal("20000.00"),
                TipoTransaccion.INGRESO, new Date(), "Proyecto X");

        Transaccion gastoEmp = new Transaccion(
                "T004", "Gasto", MetodoPago.PAYPAL, new BigDecimal("8000.00"),
                TipoTransaccion.GASTO, new Date(), "Publicidad");

        // Asociar transacciones a cuentas
        cuentaPersonal.agregarTransaccion(ingreso1);
        cuentaPersonal.agregarTransaccion(gasto1);

        cuentaEmpresarial.agregarTransaccion(ingresoEmp);
        cuentaEmpresarial.agregarTransaccion(gastoEmp);

        // Usuario básico
        List<Cuenta> cuentasUsuario1 = new ArrayList<>();
        cuentasUsuario1.add(cuentaPersonal);

        Usuario usuario1 = new Usuario(
                1, "Juan Pérez", "juan@mail.com", "1234", "usuario", cuentasUsuario1);
        usuario1.iniciarSesion("juan@mail.com", "1234");

        // Administrador
        List<Cuenta> cuentasAdmin = new ArrayList<>();
        cuentasAdmin.add(cuentaEmpresarial);

        Administrador admin = new Administrador(
                2, "Admin", "admin@mail.com", "adminpass", "admin", cuentasAdmin,
                List.of("GESTIONAR_USUARIOS", "GESTIONAR_CUENTAS"));

        admin.administrarUsuarios();
        logger.info(admin.verEstadisticasSistema());

        // Contador
        Contador contador = new Contador(
                3, "Carlos", "carlos@mail.com", "1234", "contador", cuentasAdmin, "TP123456");

        logger.info(contador.generarReporteMensual());
        contador.auditarCuentas();
        contador.exportarInformes();

        // Gestor empresarial
        GestorEmpresarial gestor = new GestorEmpresarial(
                4, "Laura", "laura@mail.com", "1234", "gestor", cuentasAdmin, "EmprendimientoX");

        gestor.registrarCuenta(new CuentaPersonal("Laura", "CP002", new BigDecimal("12000"), new BigDecimal("12")));
        logger.info("Balance total del gestor: " + gestor.consultarBalance());

        // Verificar alertas y cálculos
        logger.info("¿Cuenta personal con exceso de gastos?: " + cuentaPersonal.alertarExcesoGastos());
        logger.info("Impuestos aplicables a cuenta personal: " + cuentaPersonal.calcularImpuestos());

        // Reporte de balance
        ReporteBalanceGeneral reporte = new ReporteBalanceGeneral();
        reporte.agregarTransaccion(ingreso1);
        reporte.agregarTransaccion(gasto1);
        logger.info(reporte.generarBalanceGeneral());

        logger.info("===== FIN DEL SISTEMA =====");
    }
}