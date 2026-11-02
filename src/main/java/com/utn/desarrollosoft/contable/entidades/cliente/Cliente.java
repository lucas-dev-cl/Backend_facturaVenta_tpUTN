package com.utn.desarrollosoft.contable.entidades.cliente;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente extends AuditoriaApp {
    @Column(nullable = true, unique = true)
    private String cuitCuil;

    @Column(nullable = false)
    private String denominacion;

    // Relación Muchos a Uno: Muchos clientes tienen una Condición IVA
    @ManyToOne
    @JoinColumn(name = "condicion_iva_id", nullable = false)
    private CondicionIva condicionIva;

    // Relación Uno a Uno con Domicilio (con Cascade para que se guarde solo al guardar el cliente)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", nullable = true)
    private Domicilio domicilio;

    // Relación Uno a Uno con Contacto
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacto_id", nullable = true)
    private Contacto contacto;
}