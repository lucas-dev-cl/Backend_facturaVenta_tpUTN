package com.utn.desarrollosoft.contable.repository;

import com.utn.desarrollosoft.contable.entidades.cliente.CondicionIva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondicionIvaRepository extends JpaRepository<CondicionIva, Long> {
    Optional<CondicionIva> findByDenominacion(String denominacion);
}
