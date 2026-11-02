package com.utn.desarrollosoft.contable.servicios;

import com.utn.desarrollosoft.contable.entidades.catalogo.Articulo;
import com.utn.desarrollosoft.contable.entidades.catalogo.ListaPrecio;
import com.utn.desarrollosoft.contable.entidades.catalogo.ListaPrecioArticulo;
import com.utn.desarrollosoft.contable.excepciones.ListaPrecioArticuloNoExistenteException;
import com.utn.desarrollosoft.contable.repository.ListaPrecioArticuloRepository;
import org.springframework.stereotype.Service;

@Service
public class ListaPrecioArticuloService {
    private final ListaPrecioArticuloRepository listaPrecioArticuloRepository;

    public ListaPrecioArticuloService(ListaPrecioArticuloRepository listaPrecioArticuloRepository) {
        this.listaPrecioArticuloRepository = listaPrecioArticuloRepository;
    }

    /**
     * Obtenemos la lista de precios de un artículo por el ID del artículo y el ID de la lista de precio
     * @param articulo
     * @param listaPrecio
     * @return
     */
    public ListaPrecioArticulo getListaPrecioArticulo(Articulo articulo, ListaPrecio listaPrecio){
        return listaPrecioArticuloRepository.findByArticuloIdAndListaPrecioId(articulo.getId(), listaPrecio.getId())
                .orElseThrow(() -> new ListaPrecioArticuloNoExistenteException("No existe o no se encontró la lista de precios del articulo con el ID: " + articulo.getId() + " la lista de precio con el ID: " + listaPrecio.getId()));
    }
}
