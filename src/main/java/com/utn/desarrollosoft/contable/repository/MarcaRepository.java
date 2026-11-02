package com.utn.desarrollosoft.contable.repository;

import com.utn.desarrollosoft.contable.entidades.catalogo.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
