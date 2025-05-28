package com.sistemafinanciero.repository;

import com.sistemafinanciero.model.CuentaPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaPersonalRepository extends JpaRepository<CuentaPersonal, Long> {
}
