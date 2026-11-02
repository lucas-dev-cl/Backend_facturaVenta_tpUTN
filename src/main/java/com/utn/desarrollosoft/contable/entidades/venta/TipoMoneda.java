package com.utn.desarrollosoft.contable.entidades.venta;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipos_monedas")
public class TipoMoneda extends AuditoriaApp {
    private String codigoAfip;
    private String denominacion;
    private String simbolo;
}