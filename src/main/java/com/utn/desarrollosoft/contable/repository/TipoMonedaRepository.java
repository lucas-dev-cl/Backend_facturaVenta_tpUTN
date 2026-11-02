package com.utn.desarrollosoft.contable.repository;

import com.utn.desarrollosoft.contable.entidades.venta.TipoMoneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMonedaRepository extends JpaRepository<TipoMoneda, Long> {
}
