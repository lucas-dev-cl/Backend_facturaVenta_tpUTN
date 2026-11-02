package com.utn.desarrollosoft.contable.entidades.catalogo;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "marcas")
public class Marca extends AuditoriaApp {
    private String denominacion;
    private Integer codigo;
}