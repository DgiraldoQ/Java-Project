package com.sistemafinanciero.service;

import com.sistemafinanciero.model.CuentaPersonal;
import com.sistemafinanciero.repository.CuentaPersonalRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaPersonalService {

    private final CuentaPersonalRepository cuentaPersonalRepository;

    public CuentaPersonalService(CuentaPersonalRepository cuentaPersonalRepository) {
        this.cuentaPersonalRepository = cuentaPersonalRepository;
    }

    public List<CuentaPersonal> obtenerTodasLasCuentas() {
        return cuentaPersonalRepository.findAll();
    }

    public Optional<CuentaPersonal> obtenerCuentaPorId(Long id) {
        return cuentaPersonalRepository.findById(id);
    }

    public CuentaPersonal guardarCuenta(CuentaPersonal cuenta) {
        return cuentaPersonalRepository.save(cuenta);
    }

    public void eliminarCuenta(Long id) {
        cuentaPersonalRepository.deleteById(id);
    }

    // Método para actualizar una cuenta personal
    public Optional<CuentaPersonal> actualizarCuenta(Long id, CuentaPersonal cuentaActualizada) {
        return cuentaPersonalRepository.findById(id).map(cuenta -> {
            // Aquí actualizas los campos que quieres modificar
            cuenta.setNombreTitular(cuentaActualizada.getNombre());
            cuenta.setSaldo(cuentaActualizada.getSaldo());
            // Agrega más campos si es necesario
            return cuentaPersonalRepository.save(cuenta);
        });
    }

    // Método para guardar una cuenta personal (ya tienes guardarCuenta, pero si quieres este como alias)
    public void guardarCuentaPersonal(CuentaPersonal nuevaCuenta) {
        cuentaPersonalRepository.save(nuevaCuenta);
    }

    // Método que devuelve ObservableList para JavaFX
    public ObservableList<CuentaPersonal> obtenerTodasCuentasPersonales() {
        List<CuentaPersonal> lista = cuentaPersonalRepository.findAll();
        return FXCollections.observableArrayList(lista);
    }

    public void eliminarCuentaPersonal(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCuentaPersonal'");
    }
}