package com.utn.desarrollosoft.contable.servicios;

import com.utn.desarrollosoft.contable.entidades.catalogo.ListaPrecio;
import com.utn.desarrollosoft.contable.entidades.dto.ListaPrecioResponse;
import com.utn.desarrollosoft.contable.excepciones.ListaPrecioNoExistenteException;
import com.utn.desarrollosoft.contable.repository.ListaPrecioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaPrecioService {
    private final ListaPrecioRepository listaPrecioRepository;

    public ListaPrecioService(ListaPrecioRepository listaPrecioRepository) {
        this.listaPrecioRepository = listaPrecioRepository;
    }

    /**
     * Obtenemos la lista de precios de un producto por su ID
     * @param listaPrecioId
     * @return lista de precios
     */
    public ListaPrecio getListaPrecio(Integer listaPrecioId) {
        return listaPrecioRepository.findById(Long.valueOf(listaPrecioId))
                .orElseThrow(() -> new ListaPrecioNoExistenteException("La lista de precio con el ID: " + listaPrecioId + " no existe"));
    }

    /**
     * Obtenemos la lista de precios
     * @return lista de precios response
     */
    public List<ListaPrecioResponse> getListaPrecios() {
        return listaPrecioRepository.findAll().stream()
                .map(listaPrecio ->  ListaPrecioResponse.builder()
                        .id(Math.toIntExact(listaPrecio.getId()))
                        .denominacion(listaPrecio.getDenominacion())
                        .build())
                .collect(Collectors.toList());
    }

}
