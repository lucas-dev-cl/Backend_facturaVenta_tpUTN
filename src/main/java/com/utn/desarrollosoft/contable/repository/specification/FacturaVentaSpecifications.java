package com.utn.desarrollosoft.contable.repository.specification;

import com.utn.desarrollosoft.contable.entidades.cliente.Cliente;
import com.utn.desarrollosoft.contable.entidades.cliente.CondicionIva;
import com.utn.desarrollosoft.contable.entidades.venta.FacturaVenta;
import com.utn.desarrollosoft.contable.entidades.venta.PuntoVenta;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FacturaVentaSpecifications {

    public static Specification<FacturaVenta> contieneNumeroFactura(Long numeroFactura){
        return ((root, query, criteriaBuilder) ->
                numeroFactura == null ? null :
                criteriaBuilder.equal(root.get("numero"), numeroFactura)
        );
    }

    public static Specification<FacturaVenta> contieneClienteDenominacion(String clienteDenominacion){
        return (root, query, criteriaBuilder) -> {
            if (clienteDenominacion == null || clienteDenominacion.isBlank()) {
                return null;
            }

            // Unimos la relación cliente para obtener la denominación
            Join<FacturaVenta, Cliente> clienteJoin = root.join("cliente");

            return criteriaBuilder.like(
                    criteriaBuilder.lower(clienteJoin.get("denominacion")),
                    "%" + clienteDenominacion.toLowerCase() + "%"
            );
        };
    }

    public static Specification<FacturaVenta> contienePuntoVentaDescripcion(String puntoVentaDescripcion){
        return (root, query, criteriaBuilder) -> {
            if (puntoVentaDescripcion == null || puntoVentaDescripcion.isBlank()) {
                return null;
            }

            // Unimos la relación cliente para obtener la denominación
            Join<FacturaVenta, PuntoVenta> clienteJoin = root.join("puntoVenta");

            return criteriaBuilder.like(
                    criteriaBuilder.lower(clienteJoin.get("descripcion")),
                    "%" + puntoVentaDescripcion.toLowerCase() + "%"
            );
        };
    }

    public static Specification<FacturaVenta> contieneIvaDenominacion(String ivaDenominacion){
        return (root, query, criteriaBuilder) -> {
            if (ivaDenominacion == null || ivaDenominacion.isBlank()) {
                return null;
            }

            // Unimos la relación cliente para obtener la denominación
            Join<FacturaVenta, CondicionIva> ivaJoin = root.join("condicionIva");

            return criteriaBuilder.like(
                    criteriaBuilder.lower(ivaJoin.get("denominacion")),
                    "%" + ivaDenominacion.toLowerCase() + "%"
            );
        };
    }

    public static Specification<FacturaVenta> entreFechasYHoras(
            LocalDate fechaDesde,
            LocalDate fechaHasta,
            LocalTime horaDesde,
            LocalTime horaHasta
    ) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Si la fecha desde no es null, entonces procedemos con ese filtro
            if (fechaDesde != null) {

                // Verificamos si hay 'horaDesde' si es asi entonces lo obtenemos
                LocalDateTime desde = fechaDesde.atTime(
                        horaDesde != null ? horaDesde : LocalTime.MIN
                );

                // Aplicamos el horario a la fecha
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("fechaEmision"), desde
                        )
                );
            }

            // Si la fecha hasta no es null, entonces procedemos con ese filtro
            if (fechaHasta != null) {

                // Verificamos si hay 'horaHasta' si es asi entonces lo obtenemos
                LocalDateTime hasta = fechaHasta.atTime(
                        horaHasta != null ? horaHasta : LocalTime.MAX
                );

                // Aplicamos el horario a la fecha
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("fechaEmision"), hasta
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
