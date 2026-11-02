package com.utn.desarrollosoft.contable.config;

import com.utn.desarrollosoft.contable.entidades.catalogo.*;
import com.utn.desarrollosoft.contable.entidades.cliente.Cliente;
import com.utn.desarrollosoft.contable.entidades.cliente.CondicionIva;
import com.utn.desarrollosoft.contable.entidades.cliente.Domicilio;
import com.utn.desarrollosoft.contable.entidades.cliente.Contacto;
import com.utn.desarrollosoft.contable.entidades.usuario.Usuario;
import com.utn.desarrollosoft.contable.entidades.venta.FacturaVenta;
import com.utn.desarrollosoft.contable.entidades.venta.FacturaVentaDetalle;
import com.utn.desarrollosoft.contable.entidades.venta.PuntoVenta;
import com.utn.desarrollosoft.contable.entidades.venta.TipoMoneda;
import com.utn.desarrollosoft.contable.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            FacturaVentaRepository facturaRepository,
            ClienteRepository clienteRepository,
            CondicionIvaRepository condicionIvaRepository,
            PuntoVentaRepository puntoVentaRepository,
            TipoMonedaRepository tipoMonedaRepository,
            UsuarioRepository usuarioRepository,
            MarcaRepository marcaRepository,
            RubroRepository rubroRepository,
            ArticuloRepository articuloRepository,
            ListaPrecioRepository listaPrecioRepository,
            ListaPrecioArticuloRepository listaPrecioArticuloRepository) {
        return args -> {
            // Verificar si ya hay datos para no duplicarlos cada vez que reinicies la app
            if (
                    facturaRepository.count() > 0 ||
                    clienteRepository.count() > 0 ||
                    condicionIvaRepository.count() > 0 ||
                    puntoVentaRepository.count() > 0 ||
                    tipoMonedaRepository.count() > 0 ||
                    usuarioRepository.count() > 0 ||
                    marcaRepository.count() > 0 ||
                    rubroRepository.count() > 0 ||
                    articuloRepository.count() > 0 ||
                    listaPrecioRepository.count() > 0 ||
                    listaPrecioArticuloRepository.count() > 0) {
                return;
            }
            else{
                // 0. Crear Usuarios para auditoría
                Usuario usuarioAdmin = new com.utn.desarrollosoft.contable.entidades.usuario.Usuario();
                usuarioAdmin.setNombre("Admin");
                usuarioAdmin.setApellido("Sistema");
                usuarioAdmin.setUsuario("admin");
                usuarioAdmin.setClave("admin123");
                usuarioRepository.save(usuarioAdmin);

                Usuario usuarioCajero = new com.utn.desarrollosoft.contable.entidades.usuario.Usuario();
                usuarioCajero.setNombre("Carlos");
                usuarioCajero.setApellido("Gómez");
                usuarioCajero.setUsuario("cajero1");
                usuarioCajero.setClave("cajero123");
                usuarioRepository.save(usuarioCajero);

                // 1. Crear datos de prueba o buscar dependencias (PuntoVenta, Cliente, CondicionIva, etc.)
                PuntoVenta puntoCentral = new PuntoVenta();
                puntoCentral.setNumero(1);
                puntoCentral.setDescripcion("Sucursal Central");
                puntoCentral.setTipoEmision("Electronica");

                puntoVentaRepository.save(puntoCentral);

                PuntoVenta puntoNorte = new PuntoVenta();
                puntoNorte.setNumero(2);
                puntoNorte.setDescripcion("Sucursal Norte");
                puntoNorte.setTipoEmision("Electronica");

                puntoVentaRepository.save(puntoNorte);

                CondicionIva condRespInscripto = new CondicionIva();
                condRespInscripto.setCodigoAfip(1);
                condRespInscripto.setDenominacion("IVA Responsable Inscripto");

                condicionIvaRepository.save(condRespInscripto);

                CondicionIva condConsumidorFinal = new CondicionIva();
                condConsumidorFinal.setCodigoAfip(5);
                condConsumidorFinal.setDenominacion("Consumidor Final");

                condicionIvaRepository.save(condConsumidorFinal);

                CondicionIva condMonotributo = new CondicionIva();
                condMonotributo.setCodigoAfip(6);
                condMonotributo.setDenominacion("Monotributo");

                condicionIvaRepository.save(condMonotributo);

                // Crear Domicilios y Contactos para los clientes
                Cliente cliente1 = new Cliente();
                cliente1.setDenominacion("Consumidor Final");
                cliente1.setCondicionIva(condConsumidorFinal);

                clienteRepository.save(cliente1);

                Domicilio domicilio2 = new Domicilio();
                domicilio2.setNombreCalle("Calle San Martín");
                domicilio2.setNumeroCalle("567");

                Contacto contacto2 = new Contacto();
                contacto2.setEmail("juan.perez@example.com");
                contacto2.setTelefono("11-2345-6789");
                contacto2.setCelular("11-8765-4321");

                Cliente cliente2 = new Cliente();
                cliente2.setCuitCuil("20-87654321-4");
                cliente2.setDenominacion("Juan Pérez");
                cliente2.setCondicionIva(condRespInscripto);
                cliente2.setDomicilio(domicilio2);
                cliente2.setContacto(contacto2);

                clienteRepository.save(cliente2);

                Domicilio domicilio3 = new Domicilio();
                domicilio3.setNombreCalle("Av. Libertador");
                domicilio3.setNumeroCalle("890");

                Contacto contacto3 = new Contacto();
                contacto3.setEmail("maria.gonzalez@example.com");
                contacto3.setTelefono("11-3456-7890");
                contacto3.setCelular("11-7654-3210");

                Cliente cliente3 = new Cliente();
                cliente3.setCuitCuil("27-11223344-5");
                cliente3.setDenominacion("María González");
                cliente3.setCondicionIva(condMonotributo);
                cliente3.setDomicilio(domicilio3);
                cliente3.setContacto(contacto3);

                clienteRepository.save(cliente3);

                // 3. Crear Tipo de Moneda
                TipoMoneda pesoArgentino = new TipoMoneda();
                pesoArgentino.setCodigoAfip("PES");
                pesoArgentino.setDenominacion("Peso Argentino");
                tipoMonedaRepository.save(pesoArgentino);

                // 4. Crear Marcas
                Marca marcaCocaCola = new Marca();
                marcaCocaCola.setCodigo(1);
                marcaCocaCola.setDenominacion("Coca-Cola");
                marcaRepository.save(marcaCocaCola);

                Marca marcaPepsi = new Marca();
                marcaPepsi.setCodigo(2);
                marcaPepsi.setDenominacion("PepsiCo");
                marcaRepository.save(marcaPepsi);

                // 5. Crear Rubros
                Rubro rubroBebidas = new Rubro();
                rubroBebidas.setCodigo(1);
                rubroBebidas.setDenominacion("Bebidas");
                rubroRepository.save(rubroBebidas);

                Rubro rubroAlimentos = new Rubro();
                rubroAlimentos.setCodigo(2);
                rubroAlimentos.setDenominacion("Alimentos");
                rubroRepository.save(rubroAlimentos);

                // 6. Crear Artículos
                Articulo articulo1 = new Articulo();
                articulo1.setCodigo("ART-001");
                articulo1.setDenominacion("Coca Cola 500ml");
                articulo1.setMarca(marcaCocaCola);
                articulo1.setRubro(rubroBebidas);
                articuloRepository.save(articulo1);

                Articulo articulo2 = new Articulo();
                articulo2.setCodigo("ART-002");
                articulo2.setDenominacion("Pepsi 500ml");
                articulo2.setMarca(marcaPepsi);
                articulo2.setRubro(rubroBebidas);
                articuloRepository.save(articulo2);

                Articulo articulo3 = new Articulo();
                articulo3.setCodigo("ART-003");
                articulo3.setDenominacion("Galletitas Oreo");
                articulo3.setMarca(marcaPepsi);
                articulo3.setRubro(rubroAlimentos);
                articuloRepository.save(articulo3);

                // 7. Crear Lista de Precios
                ListaPrecio listaPrecios2026 = new ListaPrecio();
                listaPrecios2026.setCodigo("LP-2026-01");
                listaPrecios2026.setDenominacion("Lista de Precios Enero 2026");
                listaPrecioRepository.save(listaPrecios2026);

                // 8. Crear ListaPrecioArticulo (relación entre lista y artículos con precios)
                ListaPrecioArticulo lpa1 = new ListaPrecioArticulo();
                lpa1.setArticulo(articulo1);
                lpa1.setListaPrecio(listaPrecios2026);
                lpa1.setPrecioVenta(1200.0);
                listaPrecioArticuloRepository.save(lpa1);

                ListaPrecioArticulo lpa2 = new ListaPrecioArticulo();
                lpa2.setArticulo(articulo2);
                lpa2.setListaPrecio(listaPrecios2026);
                lpa2.setPrecioVenta(1100.0);
                listaPrecioArticuloRepository.save(lpa2);

                ListaPrecioArticulo lpa3 = new ListaPrecioArticulo();
                lpa3.setArticulo(articulo3);
                lpa3.setListaPrecio(listaPrecios2026);
                lpa3.setPrecioVenta(2500.0);
                listaPrecioArticuloRepository.save(lpa3);

                // 9. Crear Factura 1
                FacturaVenta factura1 = new FacturaVenta();
                factura1.setNumero(1001L);
                factura1.setFechaEmision(LocalDate.of(2026, 3, 15).atStartOfDay());
                factura1.setPuntoVenta(puntoCentral);
                factura1.setCliente(cliente1);
                factura1.setCondicionIva(condConsumidorFinal);
                factura1.setTipoMoneda(pesoArgentino);
                factura1.setUsuarioCarga(usuarioCajero);

                List<FacturaVentaDetalle> detalles1 = new ArrayList<>();

                FacturaVentaDetalle detalle1 = new FacturaVentaDetalle();
                detalle1.setFactura(factura1);
                detalle1.setListaPrecioArticulo(lpa1);
                detalle1.setDescripcion("Coca Cola 500ml");
                detalle1.setCantidad(2);
                detalle1.setPrecioUnitario(1200.0);
                detalle1.setPorcentajeBonificacion(0.0);
                detalle1.setImporteNeto(2400.0);
                detalle1.setImporteIva(0.0);
                detalle1.setImporteSubtotal(2400.0);
                detalles1.add(detalle1);

                FacturaVentaDetalle detalle2 = new FacturaVentaDetalle();
                detalle2.setFactura(factura1);
                detalle2.setListaPrecioArticulo(lpa3);
                detalle2.setDescripcion("Galletitas Oreo");
                detalle2.setCantidad(1);
                detalle2.setPrecioUnitario(2500.0);
                detalle2.setPorcentajeBonificacion(0.0);
                detalle2.setImporteNeto(2500.0);
                detalle2.setImporteIva(0.0);
                detalle2.setImporteSubtotal(2500.0);
                detalles1.add(detalle2);

                factura1.setDetalles(detalles1);
                factura1.setImporteTotal(4900.0);
                factura1.setEstado("AUTORIZADA");

                // Guardar en la base de datos (gracias al CascadeType.ALL, guardará los detalles automáticamente)
                facturaRepository.save(factura1);

                System.out.println("¡Datos iniciales de prueba cargados exitosamente en la base de datos!");
            }
        };
    }
}
