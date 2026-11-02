package com.utn.desarrollosoft.contable.entidades.catalogo;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articulos")
public class Articulo extends AuditoriaApp {
    private String codigo;
    private String denominacion;

    // Una marca puede tener varios artículos
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    // Un rubro puede tener varios artículos
    @ManyToOne
    @JoinColumn(name = "rubro_id")
    private Rubro rubro;
}