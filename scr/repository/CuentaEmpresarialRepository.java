package com.sistemafinanciero.repository;

import com.sistemafinanciero.model.CuentaEmpresarial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaEmpresarialRepository extends JpaRepository<CuentaEmpresarial, Long> {
}
