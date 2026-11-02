package com.utn.desarrollosoft.contable.entidades.venta;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "puntos_venta")
public class PuntoVenta extends AuditoriaApp {
    private int numero;
    private String descripcion;
    private String tipoEmision;
    private String domicilioComercial;
}