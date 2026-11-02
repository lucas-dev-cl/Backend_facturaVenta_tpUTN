package com.utn.desarrollosoft.contable.repository;

import com.utn.desarrollosoft.contable.entidades.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // El JOIN FETCH obliga a PostgreSQL a traer al cliente, su domicilio,
    // su contacto y su condición de IVA en un solo viaje a la base de datos.
    @Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.domicilio LEFT JOIN FETCH c.contacto JOIN FETCH c.condicionIva")
    List<Cliente> findAllConRelaciones();

    Optional<Cliente> findByDenominacion(String denominacion);


    // Devuelve un máximo de 7 resultados que coincidan con el nombre
    @Query("SELECT c FROM Cliente c " +
            "LEFT JOIN FETCH c.domicilio " +
            "LEFT JOIN FETCH c.contacto " +
            "JOIN FETCH c.condicionIva " +
            "WHERE LOWER(c.denominacion) LIKE LOWER(CONCAT('%', :denominacion, '%'))")
    List<Cliente> findTop7ByDenominacionContainingIgnoreCase(@Param("denominacion") String denominacion);
}
