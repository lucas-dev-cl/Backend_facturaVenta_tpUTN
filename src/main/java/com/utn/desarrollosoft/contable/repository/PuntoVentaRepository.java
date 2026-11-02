package com.utn.desarrollosoft.contable.repository;

import com.utn.desarrollosoft.contable.entidades.venta.PuntoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PuntoVentaRepository extends JpaRepository<PuntoVenta, Long> {
    Optional<PuntoVenta> findByDescripcion(String descripcion);
}
