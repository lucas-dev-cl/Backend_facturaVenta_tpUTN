package com.utn.desarrollosoft.contable.repository;

import com.utn.desarrollosoft.contable.entidades.venta.FacturaVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaVentaRepository extends JpaRepository<FacturaVenta, Long>, JpaSpecificationExecutor<FacturaVenta> {
    // Buscar por el campo 'numero' de la entidad
    Optional<FacturaVenta> findByNumero(Long numero);
}
