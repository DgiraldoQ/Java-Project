package com.sistemafinanciero.service;

import com.sistemafinanciero.model.CuentaEmpresarial;
import com.sistemafinanciero.repository.CuentaEmpresarialRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaEmpresarialService {

    private final CuentaEmpresarialRepository cuentaEmpresarialRepository;

    public CuentaEmpresarialService(CuentaEmpresarialRepository cuentaEmpresarialRepository) {
        this.cuentaEmpresarialRepository = cuentaEmpresarialRepository;
    }

    public List<CuentaEmpresarial> obtenerTodasLasCuentas() {
        return cuentaEmpresarialRepository.findAll();
    }

    public Optional<CuentaEmpresarial> obtenerCuentaPorId(Long id) {
        return cuentaEmpresarialRepository.findById(id);
    }

    public CuentaEmpresarial guardarCuenta(CuentaEmpresarial cuenta) {
        return cuentaEmpresarialRepository.save(cuenta);
    }

    public void eliminarCuenta(Long id) {
        cuentaEmpresarialRepository.deleteById(id);
    }

    // Implementación del método actualizarCuenta (puedes ajustarlo según tu lógica)
    public Optional<CuentaEmpresarial> actualizarCuenta(Long id, CuentaEmpresarial cuentaActualizada) {
        return cuentaEmpresarialRepository.findById(id).map(cuenta -> {
            cuenta.setNombreEmpresa(cuentaActualizada.getNombre());
            cuenta.setSaldo(cuentaActualizada.getSaldo());
            cuenta.setNombreEmpresa(cuentaActualizada.getNombre());
            cuenta.setImpuesto(cuentaActualizada.getImpuesto());
            return cuentaEmpresarialRepository.save(cuenta);
        });
    }

    // Método para guardar cuenta empresarial (usa guardarCuenta para no duplicar lógica)
    public void guardarCuentaEmpresarial(CuentaEmpresarial nuevaCuenta) {
        guardarCuenta(nuevaCuenta);
    }

    // Método que retorna ObservableList para JavaFX
    public ObservableList<CuentaEmpresarial> obtenerTodasCuentasEmpresariales() {
        List<CuentaEmpresarial> lista = cuentaEmpresarialRepository.findAll();
        return FXCollections.observableArrayList(lista);
    }
}