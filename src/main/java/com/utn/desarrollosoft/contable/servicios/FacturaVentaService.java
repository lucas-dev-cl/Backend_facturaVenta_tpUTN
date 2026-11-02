package com.utn.desarrollosoft.contable.servicios;

import com.utn.desarrollosoft.contable.entidades.cliente.Cliente;
import com.utn.desarrollosoft.contable.entidades.cliente.CondicionIva;
import com.utn.desarrollosoft.contable.entidades.dto.FacturaVentaDTO;
import com.utn.desarrollosoft.contable.entidades.dto.FacturaVentaDetalleDTO;
import com.utn.desarrollosoft.contable.entidades.venta.FacturaVenta;
import com.utn.desarrollosoft.contable.entidades.venta.FacturaVentaDetalle;
import com.utn.desarrollosoft.contable.entidades.venta.PuntoVenta;
import com.utn.desarrollosoft.contable.repository.*;
import com.utn.desarrollosoft.contable.repository.specification.FacturaVentaSpecifications;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacturaVentaService {

    private final FacturaVentaRepository facturaVentaRepository;
    private final ClienteRepository clienteRepository;
    private final CondicionIvaRepository condicionIvaRepository;
    private final TipoMonedaRepository tipoMonedaRepository;
    private final PuntoVentaRepository puntoVentaRepository;
    private final ArticuloService articuloService;
    private final ListaPrecioService listaPrecioService;
    private final ListaPrecioArticuloService listaPrecioArticuloService;

    public FacturaVentaService(FacturaVentaRepository facturaVentaRepository,
                               ClienteRepository clienteRepository,
                               PuntoVentaRepository puntoVentaRepository,
                               CondicionIvaRepository condicionIvaRepository,
                               TipoMonedaRepository tipoMonedaRepository,
                               ArticuloService articuloService,
                               ListaPrecioService listaPrecioService,
                               ListaPrecioArticuloService listaPrecioArticuloService
                               ) {
        this.facturaVentaRepository = facturaVentaRepository;
        this.puntoVentaRepository = puntoVentaRepository;
        this.clienteRepository = clienteRepository;
        this.condicionIvaRepository = condicionIvaRepository;
        this.tipoMonedaRepository = tipoMonedaRepository;
        this.articuloService = articuloService;
        this.listaPrecioService = listaPrecioService;
        this.listaPrecioArticuloService = listaPrecioArticuloService;
    }

    /**
     * Filtra las facturas de venta según múltiples criterios opcionales.
     *
     * @param numeroFactura       Número específico de la factura a buscar (opcional).
     * @param clienteDenominacion Texto parcial o total para buscar por la denominación del cliente (opcional).
     * @param condicionIva        Texto para filtrar por la condición de IVA (opcional).
     * @param fechaDesde          Fecha y hora de inicio para el rango de emisión (opcional).
     * @param fechaHasta          Fecha y hora de fin para el rango de emisión (opcional).
     * @return Una lista de {@link FacturaVentaDTO} que coinciden con los filtros aplicados.
     */
    public Page<FacturaVentaDTO> filtrar(Long numeroFactura, String clienteDenominacion,
                                         String condicionIva, String puntoVenta, LocalDate fechaDesde, LocalDate fechaHasta, LocalTime horaDesde, LocalTime horaHasta, Pageable pageable) {
        // Componemos la especificación dinámicamente
        Specification<FacturaVenta> spec = Specification.where(
                        FacturaVentaSpecifications.contieneNumeroFactura(numeroFactura)
                )
                .and(FacturaVentaSpecifications.contienePuntoVentaDescripcion(puntoVenta))
                .and(FacturaVentaSpecifications.contieneClienteDenominacion(clienteDenominacion))
                .and(FacturaVentaSpecifications.contieneIvaDenominacion(condicionIva))
                // Usando la opción combinada de fecha y hora:
                .and(FacturaVentaSpecifications.entreFechasYHoras(fechaDesde, fechaHasta, horaDesde, horaHasta));

        Page<FacturaVenta> entidadesPage = facturaVentaRepository.findAll(spec, pageable);
        return entidadesPage.map(this::convertEntityToDto);
    }

    /**
     * Busca una factura específica a partir de su número identificatorio.
     *
     * @param numeroFactura El número de la factura a buscar.
     * @return Un {@link Optional} que contiene el {@link FacturaVentaDTO} si es encontrado, o vacío en caso contrario.
     */
    public Optional<FacturaVentaDTO> buscarPorNumero(Long numeroFactura) {
        return facturaVentaRepository.findByNumero(numeroFactura)
                .map(this::convertEntityToDto);
    }

    /**
     * Recalcula los subtotales y el total de la factura, y la almacena de forma persistente en la base de datos.
     *
     * @param facturaDto El objeto {@link FacturaVentaDTO} con los datos de la factura a guardar.
     * @return El {@link FacturaVentaDTO} guardado, incluyendo los identificadores generados por la base de datos.
     */
    @Transactional
    public FacturaVentaDTO guardar(FacturaVentaDTO facturaDto) {

        recalcularTotalesDto(facturaDto);

        // Convertimos el DTO entrante a Entidad JPA para guardarlo
        FacturaVenta entidad = convertDtoToEntity(facturaDto);

        // La BD o JPA manejan los IDs automáticamente (GenerationType.IDENTITY)
        FacturaVenta guardada = facturaVentaRepository.save(entidad);

        return convertEntityToDto(guardada);
    }

    /**
     * Actualiza los datos de una factura de venta existente en la base de datos.
     *
     * @param numeroFactura           El número de la factura que se desea actualizar.
     * @param facturaActualizadaDto El DTO con los nuevos datos a aplicar.
     * @return Un {@link Optional} con el {@link FacturaVentaDTO} actualizado, o vacío si la factura no existe.
     */
    @Transactional
    public Optional<FacturaVentaDTO> actualizar(Long numeroFactura, FacturaVentaDTO facturaActualizadaDto) {
        Optional<FacturaVenta> existenteOpt = facturaVentaRepository.findByNumero(numeroFactura);

        if (existenteOpt.isEmpty()) {
            return Optional.empty();
        }

        FacturaVenta existente = existenteOpt.get();
        recalcularTotalesDto(facturaActualizadaDto);

        // Actualizamos los campos necesarios de la entidad existente
        existente.setImporteTotal(facturaActualizadaDto.getImporteTotal());
        // (Actualiza el resto de campos que necesites...)

        FacturaVenta actualizada = facturaVentaRepository.save(existente);
        return Optional.of(convertEntityToDto(actualizada));
    }

    /**
     * Elimina una factura de venta de la base de datos según su número.
     *
     * @param numeroFactura El número de la factura a eliminar.
     * @return {@code true} si la factura fue encontrada y eliminada con éxito, {@code false} si no existía.
     */
    @Transactional
    public boolean eliminar(Long numeroFactura) {
        Optional<FacturaVenta> existente = facturaVentaRepository.findByNumero(numeroFactura);
        if (existente.isPresent()) {
            facturaVentaRepository.delete(existente.get());
            return true;
        }
        return false;
    }

    // --- Métodos auxiliares de lógica y conversión ---

    /**
     * Recalcula los subtotales de cada ítem y el importe total general de la factura en formato DTO.
     *
     * @param factura El objeto {@link FacturaVentaDTO} cuyos totales serán recalculados.
     */
    private void recalcularTotalesDto(FacturaVentaDTO factura) {
        if (factura.getDetalles() == null) {
            factura.setDetalles(new ArrayList<>());
        }

        for (FacturaVentaDetalleDTO detalle : factura.getDetalles()) {
            detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecioUnitario());
        }

        double total = factura.getDetalles().stream()
                .mapToDouble(d -> d.getSubtotal() != null ? d.getSubtotal() : 0.0)
                .sum();

        factura.setImporteTotal(total);
    }

    /**
     * Convierte una Entidad JPA {@link FacturaVenta} a su respectivo objeto de transferencia {@link FacturaVentaDTO}.
     *
     * @param entidad La entidad de base de datos a convertir.
     * @return El DTO resultante con los datos mapeados.
     */
    private FacturaVentaDTO convertEntityToDto(FacturaVenta entidad) {
        FacturaVentaDTO dto = new FacturaVentaDTO();
        dto.setNumeroFactura(entidad.getNumero());
        dto.setFechaEmision(entidad.getFechaEmision());
        dto.setClienteId(Math.toIntExact(entidad.getCliente().getId()));
        dto.setClienteDenominacion(entidad.getCliente().getDenominacion());
        dto.setCondicionIvaId(Math.toIntExact(entidad.getCondicionIva().getId()));
        dto.setCondicionIvaDenominacion(entidad.getCondicionIva().getDenominacion());
        dto.setPuntoVentaId(Math.toIntExact(entidad.getPuntoVenta().getId()));
        dto.setPuntoVentaDescripcion(entidad.getPuntoVenta().getDescripcion());
        dto.setImporteTotal(entidad.getImporteTotal());
        dto.setDetalles(convertFacturaVentaDetalletoDto(entidad.getDetalles()));
        return dto;
    }

    /**
     * Convierte un objeto {@link FacturaVentaDTO} en una Entidad JPA {@link FacturaVenta} para su persistencia.
     *
     * @param dto El objeto DTO con los datos de entrada.
     * @return La entidad JPA resultante lista para ser guardada en el repositorio.
     */
    private FacturaVenta convertDtoToEntity(FacturaVentaDTO dto) {
        // Validar y obtener el Punto de Venta desde la BD
        PuntoVenta puntoVenta = puntoVentaRepository.findById(Long.valueOf(dto.getPuntoVentaId()))
                .orElseThrow(() -> new EntityNotFoundException("El punto de venta seleccionado no es válido o no existe."));

        // Validar y obtener la Condición IVA desde la BD
        CondicionIva condicionIva = condicionIvaRepository.findById(Long.valueOf(dto.getCondicionIvaId()))
                .orElseThrow(() -> new EntityNotFoundException("La condición de IVA seleccionada no es válida."));

        // Validar y obtener el Cliente desde la BD
        Cliente cliente = clienteRepository.findById(Long.valueOf(dto.getClienteId()))
                .orElseThrow(() -> new EntityNotFoundException("El cliente seleccionado no existe."));

        // Mapeamos los datos del DTO a la entidad
        FacturaVenta entidad = new FacturaVenta();
        entidad.setNumero(dto.getNumeroFactura());
        entidad.setImporteTotal(dto.getImporteTotal());
        entidad.setCliente(cliente);
        entidad.setPuntoVenta(puntoVenta);
        entidad.setCondicionIva(condicionIva);


        entidad.setDetalles(convertFacturaVentaDetalleDtoToEntity(dto.getDetalles()));
        return entidad;
    }

    /**
     * Convertimos el dto de factura venta detalle a entidad
     *
     * @param dtos
     * @return
     */
    @Transactional
    List<FacturaVentaDetalle> convertFacturaVentaDetalleDtoToEntity(List<FacturaVentaDetalleDTO> dtos) {
        return dtos.stream()
                .map(dto -> FacturaVentaDetalle.builder()
                        .cantidad(dto.getCantidad())
                        .precioUnitario(dto.getPrecioUnitario())
                        .importeSubtotal(dto.getPrecioUnitario() * dto.getCantidad())
                        .listaPrecioArticulo(listaPrecioArticuloService.getListaPrecioArticulo(articuloService.getArticulo(dto.getProductoId()), listaPrecioService.getListaPrecio(dto.getListaPrecioId())))
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * Convertimos la entidad de factura detalle venta al dto
     *
     * @param entities
     * @return
     */
    List<FacturaVentaDetalleDTO> convertFacturaVentaDetalletoDto(List<FacturaVentaDetalle> entities) {
        return entities.stream()
                        .map(facturaVentaDetalle -> FacturaVentaDetalleDTO.builder()
                                .id(facturaVentaDetalle.getId())
                                .listaPrecioId(Math.toIntExact(facturaVentaDetalle.getListaPrecioArticulo().getListaPrecio().getId()))
                                .subtotal(facturaVentaDetalle.getCantidad() * facturaVentaDetalle.getPrecioUnitario())
                                .precioUnitario(facturaVentaDetalle.getPrecioUnitario())
                                .cantidad((int) facturaVentaDetalle.getCantidad())
                                .productoDenominacion(facturaVentaDetalle.getListaPrecioArticulo().getArticulo().getDenominacion())
                                .productoId(Math.toIntExact(facturaVentaDetalle.getListaPrecioArticulo().getArticulo().getId()))
                                .build()
                        )
                .toList();
    }

}
