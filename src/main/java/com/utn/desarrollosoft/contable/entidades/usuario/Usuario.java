package com.utn.desarrollosoft.contable.entidades.usuario;

import com.utn.desarrollosoft.contable.entidades.base.EntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity
public class Usuario extends EntityId {
    private String nombre;
    private String apellido;
    private String usuario;
    private String clave;
}
