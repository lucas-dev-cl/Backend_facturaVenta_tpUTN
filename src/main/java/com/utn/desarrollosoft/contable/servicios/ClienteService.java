package com.utn.desarrollosoft.contable.servicios;

import com.utn.desarrollosoft.contable.entidades.dto.ClienteResponse;
import com.utn.desarrollosoft.contable.entidades.dto.CondicionIvaResponse;
import com.utn.desarrollosoft.contable.entidades.dto.ContactoResponse;
import com.utn.desarrollosoft.contable.entidades.dto.DomicilioResponse;
import com.utn.desarrollosoft.contable.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteResponse> getClientes() {
        return clienteRepository.findAllConRelaciones().stream()
                .map(cliente -> ClienteResponse.builder()
                        .id(Math.toIntExact(cliente.getId()))
                        .condicionIva(new CondicionIvaResponse(
                                Math.toIntExact(cliente.getCondicionIva().getId()),
                                cliente.getCondicionIva().getDenominacion(),
                                cliente.getCondicionIva().getCodigoAfip())
                        )
                        .domicilio(
                                cliente.getDomicilio() != null ? new DomicilioResponse(
                                        cliente.getDomicilio().getNombreCalle(),
                                        cliente.getDomicilio().getNumeroCalle()) : null
                        )
                        .contacto(
                                cliente.getContacto() != null ? new ContactoResponse(
                                        cliente.getContacto().getEmail(),
                                        cliente.getContacto().getTelefono(),
                                        cliente.getContacto().getCelular()) : null
                        )
                        .cuitCuil(cliente.getCuitCuil())
                        .denominacion(cliente.getDenominacion())
                        .build())
                .toList();
    }

    public List<ClienteResponse> searchClientesPorDenominacion(String denominacion){
        if (denominacion == null || denominacion.trim().isEmpty()) {
            return List.of();
        }

        return clienteRepository.findTop7ByDenominacionContainingIgnoreCase(denominacion).stream()
                .map(cliente -> ClienteResponse.builder()
                        .id(Math.toIntExact(cliente.getId()))
                        .condicionIva(new CondicionIvaResponse(
                                Math.toIntExact(cliente.getCondicionIva().getId()),
                                cliente.getCondicionIva().getDenominacion(),
                                cliente.getCondicionIva().getCodigoAfip())
                        )
                        .domicilio(
                                cliente.getDomicilio() != null ? new DomicilioResponse(
                                        cliente.getDomicilio().getNombreCalle(),
                                        cliente.getDomicilio().getNumeroCalle()) : null
                        )
                        .contacto(
                                cliente.getContacto() != null ? new ContactoResponse(
                                        cliente.getContacto().getEmail(),
                                        cliente.getContacto().getTelefono(),
                                        cliente.getContacto().getCelular()) : null
                        )
                        .cuitCuil(cliente.getCuitCuil())
                        .denominacion(cliente.getDenominacion())
                        .build())
                .toList();
    }
}