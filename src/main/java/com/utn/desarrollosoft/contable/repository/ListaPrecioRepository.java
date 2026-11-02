package com.utn.desarrollosoft.contable.repository;

import com.utn.desarrollosoft.contable.entidades.catalogo.ListaPrecio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListaPrecioRepository extends JpaRepository<ListaPrecio, Long> {
    Optional<ListaPrecio> findByDenominacion(String denominacion);
}
