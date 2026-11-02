package com.utn.desarrollosoft.contable.repository;

import com.utn.desarrollosoft.contable.entidades.catalogo.ListaPrecioArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListaPrecioArticuloRepository extends JpaRepository<ListaPrecioArticulo, Long> {
    Optional<ListaPrecioArticulo> findByArticuloIdAndListaPrecioId(Long articuloId, Long listaPrecioId);

    /**
     * Consulta con JOIN FETCH para traer el artículo y su lista de precios asociada en la misma consulta,
     * evitando problemas de consultas N+1 al mapear los precios en el servicio.
     */
    @Query("SELECT lpa FROM ListaPrecioArticulo lpa JOIN FETCH lpa.listaPrecio WHERE lpa.articulo.id = :articuloId")
    List<ListaPrecioArticulo> findByArticuloIdWithListaPrecio(@Param("articuloId") Long articuloId);
}
