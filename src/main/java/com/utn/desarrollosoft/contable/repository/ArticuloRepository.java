package com.utn.desarrollosoft.contable.repository;

import com.utn.desarrollosoft.contable.entidades.catalogo.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    @Query("SELECT a FROM Articulo a " +
            "LEFT JOIN FETCH a.marca " +
            "LEFT JOIN FETCH a.rubro " +
            "WHERE LOWER(a.denominacion) LIKE LOWER(CONCAT('%', :denominacion, '%'))")
    List<Articulo> findByDenominacion(String denominacion);
}
