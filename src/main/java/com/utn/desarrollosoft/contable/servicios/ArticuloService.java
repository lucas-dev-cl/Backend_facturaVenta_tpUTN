package com.utn.desarrollosoft.contable.servicios;

import com.utn.desarrollosoft.contable.entidades.catalogo.Articulo;
import com.utn.desarrollosoft.contable.entidades.dto.ArticuloResponse;
import com.utn.desarrollosoft.contable.entidades.dto.MarcaResponse;
import com.utn.desarrollosoft.contable.entidades.dto.PrecioArticuloResponse;
import com.utn.desarrollosoft.contable.entidades.dto.RubroResponse;
import com.utn.desarrollosoft.contable.excepciones.ProductoNoExistenteException;
import com.utn.desarrollosoft.contable.repository.ArticuloRepository;
import com.utn.desarrollosoft.contable.repository.ListaPrecioArticuloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // Garantiza que la sesión de Hibernate permanezca abierta durante las consultas relacionadas (lazy loading)
public class ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final ListaPrecioArticuloRepository listaPrecioArticuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository, ListaPrecioArticuloRepository listaPrecioArticuloRepository) {
        this.articuloRepository = articuloRepository;
        this.listaPrecioArticuloRepository = listaPrecioArticuloRepository;
    }

    /**
     * Obtiene una entidad Artículo por su ID.
     */
    public Articulo getArticulo(Integer productoId) {
        return articuloRepository.findById(Long.valueOf(productoId))
                .orElseThrow(() -> new ProductoNoExistenteException("El producto no existe"));
    }

    /**
     * Busca artículos por denominación e incluye sus precios en las distintas listas de precios.
     * Consulta el repositorio de precios por artículo para poblar la información de costos/precios unitarios para el frontend.
     */
    public List<ArticuloResponse> buscarArticuloPorDenominacion(String denominacion){
        if(denominacion == null || denominacion.trim().isEmpty()){
            return List.of();
        }

        return articuloRepository.findByDenominacion(denominacion).stream()
                .map(articulo -> {
                    // Obtenemos los precios asociados a este artículo en cada lista de precios
                    List<PrecioArticuloResponse> precios = listaPrecioArticuloRepository
                            .findByArticuloIdWithListaPrecio(articulo.getId()).stream()
                            .map(lpa -> PrecioArticuloResponse.builder()
                                    .listaPrecioDenominacion(lpa.getListaPrecio().getDenominacion())
                                    .precioVenta(lpa.getPrecioVenta())
                                    .build()
                            )
                            .toList();

                    return ArticuloResponse.builder()
                            .id(articulo.getId())
                            .codigo(articulo.getCodigo())
                            .denominacion(articulo.getDenominacion())
                            .marcaResponse(new MarcaResponse(articulo.getMarca().getDenominacion(), articulo.getMarca().getCodigo()))
                            .rubroResponse(new RubroResponse(articulo.getRubro().getDenominacion(), articulo.getRubro().getCodigo()))
                            .precios(precios)
                            .build();
                })
                .toList();
    }
}
