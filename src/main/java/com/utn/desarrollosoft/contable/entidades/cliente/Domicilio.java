package com.utn.desarrollosoft.contable.entidades.cliente;

import com.utn.desarrollosoft.contable.entidades.base.EntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "domicilios")
public class Domicilio extends EntityId {
    private String nombreCalle;
    private String numeroCalle;
}