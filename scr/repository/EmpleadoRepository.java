package com.sistemafinanciero.repository;

import com.sistemafinanciero.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByEstadoContrato(String estadoContrato);  // No necesita cambios
}