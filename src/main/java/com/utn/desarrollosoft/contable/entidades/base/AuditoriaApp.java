package com.utn.desarrollosoft.contable.entidades.base;

import com.utn.desarrollosoft.contable.entidades.usuario.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AuditoriaApp extends EntityId {
    protected Date fechaAlta;
    protected Date fechaBaja;
    protected Date fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_carga_id")
    protected Usuario usuarioCarga;

    @ManyToOne
    @JoinColumn(name = "usuario_baja_id")
    protected Usuario usuarioBaja;

    @ManyToOne
    @JoinColumn(name = "usuario_modificacion_id")
    protected Usuario usuarioModificacion;
}