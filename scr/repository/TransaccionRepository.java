package com.sistemafinanciero.repository;

import com.sistemafinanciero.model.Transaccion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByUsuarioId(Long id);
}