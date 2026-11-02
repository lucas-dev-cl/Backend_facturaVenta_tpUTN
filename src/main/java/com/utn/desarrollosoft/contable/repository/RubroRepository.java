package com.utn.desarrollosoft.contable.repository;

import com.utn.desarrollosoft.contable.entidades.catalogo.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroRepository extends JpaRepository<Rubro, Long> {
}
