package com.utn.desarrollosoft.contable.entidades.venta;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import com.utn.desarrollosoft.contable.entidades.cliente.Cliente;
import com.utn.desarrollosoft.contable.entidades.cliente.CondicionIva;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "factura_ventas")
public class FacturaVenta extends AuditoriaApp {
    private Long numero;

    private LocalDateTime fechaEmision;

    @ManyToOne()
    @JoinColumn(name = "punto_venta_id", nullable = false)
    private PuntoVenta puntoVenta;

    @ManyToOne()
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(name = "condicion_iva_id", nullable = false)
    private CondicionIva condicionIva;

    @ManyToOne()
    @JoinColumn(name = "tipo_moneda_id", nullable = false)
    private TipoMoneda tipoMoneda;

    private double importeCobrado;
    private double importeSaldo;
    private double importeTotal;

    // Campos de AFIP
    private String cae;

    private LocalDate caeFechaVencimiento;

    private String resultadoAfip;
    private String motivoRechazo;
    private String estado;

    private LocalDateTime fechaAnulacion;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    // Relación Uno a Muchos con el Detalle
    // Cascade.ALL + orphanRemoval asegura que si guardas/borras la factura, sus ítems se guarden/borren solos.
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacturaVentaDetalle> detalles = new ArrayList<>();
}