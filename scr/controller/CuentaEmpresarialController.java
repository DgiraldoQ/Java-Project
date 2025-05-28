package com.sistemafinanciero.controller;

import com.sistemafinanciero.model.CuentaEmpresarial;
import com.sistemafinanciero.service.CuentaEmpresarialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas-empresariales")
public class CuentaEmpresarialController {

    private final CuentaEmpresarialService service;

    public CuentaEmpresarialController(CuentaEmpresarialService service) {
        this.service = service;
    }

    @GetMapping
    public List<CuentaEmpresarial> listar() {
        return service.obtenerTodasLasCuentas();
    }

    @PostMapping
    public CuentaEmpresarial crear(@RequestBody CuentaEmpresarial cuenta) {
        return service.guardarCuenta(cuenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaEmpresarial> obtenerPorId(@PathVariable Long id) {
        return service.obtenerCuentaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaEmpresarial> actualizar(@PathVariable Long id, @RequestBody CuentaEmpresarial cuentaActualizada) {
        return service.actualizarCuenta(id, cuentaActualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}