package com.sistemafinanciero.controller;

import com.sistemafinanciero.model.CuentaPersonal;
import com.sistemafinanciero.service.CuentaPersonalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas-personales")
public class CuentaPersonalController {

    private final CuentaPersonalService cuentaPersonalService;

    public CuentaPersonalController(CuentaPersonalService cuentaPersonalService) {
        this.cuentaPersonalService = cuentaPersonalService;
    }

    @GetMapping
    public List<CuentaPersonal> obtenerTodasLasCuentas() {
        return cuentaPersonalService.obtenerTodasLasCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaPersonal> obtenerCuentaPorId(@PathVariable Long id) {
        return cuentaPersonalService.obtenerCuentaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CuentaPersonal crearCuenta(@RequestBody CuentaPersonal cuenta) {
        return cuentaPersonalService.guardarCuenta(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaPersonal> actualizarCuenta(@PathVariable Long id, @RequestBody CuentaPersonal cuentaActualizada) {
        return cuentaPersonalService.actualizarCuenta(id, cuentaActualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaPersonalService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}