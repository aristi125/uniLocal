package co.org.uniquindio.unilocal.controladores;


import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.comentario.RegistroComentarioDTO;
import co.org.uniquindio.unilocal.dto.comentario.RespuestaComentarioDTO;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.negocio.ActualizarNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.RegistroNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.ReporteDTO;
import co.org.uniquindio.unilocal.dto.producto.ProductoDTO;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.documentos.Producto;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.servicios.interfaces.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteServicio clienteServicio;
    private final ComentarioServicio comentarioServicio;
    private final NegocioServicio negocioServicio;
    private final ProductoServicio productoServicio;

    @PutMapping("/actualizar-perfil-cliente")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@Valid @RequestBody ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cliente actualizado exitosamente"));
    }

    @GetMapping("/obtener/{idCuenta}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@PathVariable String idCuenta) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.obtenerCliente(idCuenta)));
    }

    @DeleteMapping("/eliminar/{idCuenta}")
    public ResponseEntity<MensajeDTO<String>> eliminarCliente(@PathVariable String idCuenta) throws Exception {
        clienteServicio.eliminarCliente(idCuenta);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cliente eliminado satisfactoriamente"));
    }

    @PostMapping("/sitios-favoritos")
    public ResponseEntity<MensajeDTO<String>> agregarFavoritos(@Valid @RequestBody String idNegocio, String idCliente) throws Exception {
        negocioServicio.agregarFavoritos(idNegocio, idCliente);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Sitio agregado a favoritos"));
    }

    @GetMapping("/obtener-favoritos-cliente/{idCliente}") // concatener el idCliente
    public ResponseEntity<MensajeDTO<List<FavoritoDTO>>> mostrarFavoritos(@PathVariable String idCliente) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.mostrarFavoritos(idCliente)));
    }

    @DeleteMapping("eliminar-favoritos/{idNegocio}/{idCliente}")
    public ResponseEntity<MensajeDTO<String>> removerFavoritos(@PathVariable String idNegocio, @PathVariable String idCliente) throws Exception {
        negocioServicio.removerFavoritos(idNegocio, idCliente);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio eliminado de favoritos"));
    }

    @GetMapping("lugares-creados-cliente/{idCliente}/{idNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> listaLugaresCreados(@PathVariable String idCliente, @PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listaLugaresCreados(idCliente, idNegocio)));
    }

    @GetMapping("/buscar-negocio-nombre/{nombre}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioNombre(@PathVariable String nombre) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocioNombre(nombre)));
    }

    @GetMapping("/buscar-negocio-categoria/{categoria}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioCategoria(@PathVariable CategoriaNegocio categoria) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocioCategoria(categoria)));
    }

    @GetMapping("/buscar-negocio-distancia/{distancia}/{ubicacionCliente}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioDistancia(@PathVariable double distancia, @PathVariable Ubicacion ubicacionCliente) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocioDistancia(distancia, ubicacionCliente)));
    }

    @GetMapping("/recomendar-negocio/{busqueda}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> recomendarNegocio(@PathVariable String busqueda) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.recomendarNegocio(busqueda)));
    }

    @GetMapping("/filtar-estado/{estadoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> filtrarPorEstado(@PathVariable EstadoNegocio estadoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtrarPorEstado(estadoNegocio)));
    }

    @PostMapping("/registrar-agenda")
    public ResponseEntity<MensajeDTO<String>> registroAgenda(@Valid @RequestBody RegistroAgendaDTO registroAgendaDTO) throws Exception {
        negocioServicio.registrarAgenda(registroAgendaDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "La agenda ha sido registrada"));
    }

    @PutMapping("/actualizar-agenda")
    public ResponseEntity<MensajeDTO<String>> actualizarAgenda(@Valid @RequestBody RegistroAgendaDTO registroAgendaDTO) throws Exception {
        negocioServicio.actualizarAgenda(registroAgendaDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Agenda actualiza correctamente"));
    }

    @DeleteMapping("/eliminar-agenda/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<String>> eliminarAgenda(@PathVariable String codigoNegocio) throws Exception {
        negocioServicio.eliminarAgenda(codigoNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Agenda eliminada satisfactoriamente"));
    }

    @GetMapping("/obtener-agenda")
    public ResponseEntity<MensajeDTO<DetalleAgendaDTO>> obtenerAgenda(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerAgenda(codigoNegocio)));
    }

    @PutMapping("/cambiar-password/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@Valid @RequestBody CambioPasswordDTO cambioPasswordDTO) throws Exception {
        clienteServicio.cambiarPassword(cambioPasswordDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se ha cambiado su contraseña"));
    }

    @PostMapping("/crear-comentario")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@Valid @RequestBody RegistroComentarioDTO registroComentarioDTO) throws Exception {
        comentarioServicio.crearComentario(registroComentarioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Comentario creado exitosamente"));
    }

    @PostMapping("/responder-comentario")
    public ResponseEntity<MensajeDTO<String>> responderComentario(@Valid @RequestBody RespuestaComentarioDTO respuestaComentarioDTO) throws Exception {
        comentarioServicio.responderComentario(respuestaComentarioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "El comentario ha sido respondido de manera exitosa"));
    }

    @GetMapping("/listar-comentarios-negocio/{idNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemListaComentariosDTO>>> listarComentariosNegocio(@PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, comentarioServicio.listarComentariosNegocio(idNegocio)));
    }

    @GetMapping("/calcular-promedio-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<Integer>> calcularPromedioCalificaciones(@PathVariable String codigoNegocio) throws Exception {
        comentarioServicio.calcularPromedioCalificaciones(codigoNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, comentarioServicio.calcularPromedioCalificaciones(codigoNegocio)));
    }

    @PostMapping("/crear-negocio")
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@Valid @RequestBody RegistroNegocioDTO registroNegocioDTO) throws Exception {
        negocioServicio.crearNegocio(registroNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio creado exitosamente"));
    }

    @PutMapping("/actualizar-negocio")
    public ResponseEntity<MensajeDTO<String>> actualizarNegocio(@Valid @RequestBody ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {
        negocioServicio.actualizarNegocio(actualizarNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio actualizado de forma correcta"));
    }

    @DeleteMapping("/eliminar-negocio/{idNegocio}")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@PathVariable String idNegocio) throws Exception {
        negocioServicio.eliminarNegocio(idNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio eliminado satisfactoriamente"));
    }

    @GetMapping("/buscar-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<Negocio>> buscarNegocio(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocio(codigoNegocio)));
    }

    @PostMapping("/generar.PDF/{rutaArchivo}")
    public ResponseEntity<MensajeDTO<String>> generarPDF(@Valid @RequestBody ReporteDTO reporteDTO, @PathVariable String rutaArchivo) throws IOException {
        negocioServicio.generarPDF(reporteDTO, rutaArchivo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "PDF generado exitosamente"));
    }

    @PostMapping("/registrar-producto")
    public ResponseEntity<MensajeDTO<String>> registrarProducto(@Valid @RequestBody ProductoDTO productoDTO) throws Exception {
        productoServicio.registrarProducto(productoDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Producto creado exitosamente"));
    }

    @PutMapping("/actualizar-producto")
    public ResponseEntity<MensajeDTO<String>> actualizarProducto(@Valid @RequestBody ProductoDTO productoDTO) throws Exception {
        productoServicio.actualizarProducto(productoDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Producto actualizado exitosamente"));
    }

    @DeleteMapping("/eliminar-producto")
    public ResponseEntity<MensajeDTO<String>> eliminarProducto(@RequestBody ProductoDTO productoDTO) throws Exception {
        productoServicio.eliminarProducto(productoDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Producto eliminado"));
    }

    @GetMapping("/listar-productos")
    public ResponseEntity<MensajeDTO<List<Producto>>> listarProductos(@RequestBody ProductoDTO productoDTO) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, productoServicio.listarProductos(productoDTO)));
    }

    @PostMapping("/registrar-reserva")
    public ResponseEntity<MensajeDTO<String>> registrarReserva(@Valid @RequestBody DetalleReservaDTO detalleReservaDTO) throws Exception {
        negocioServicio.registrarReserva(detalleReservaDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Reserva creada satisfactoriamente"));
    }

    @PutMapping("/actualizar-reserva")
    public ResponseEntity<MensajeDTO<String>> actualizarReserva(@Valid @RequestBody DetalleReservaDTO detalleReservaDTO) throws Exception {
        negocioServicio.actualizarReserva(detalleReservaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Reserva actualizada con éxito"));
    }

    @GetMapping("/obtener-reserva/{idNegocio}/{idCliente}")
    public ResponseEntity<MensajeDTO<DetalleReservaDTO>> obtenerReserva(@PathVariable String idNegocio, @PathVariable String idCliente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO(false, negocioServicio.obtenerReserva(idNegocio, idCliente)));
    }

    @DeleteMapping("/eliminar-reserva/{idNegocio}/{idCliente}")
    public ResponseEntity<MensajeDTO<String>> eliminarReserva(@PathVariable String idNegocio, @PathVariable String idCliente) throws Exception {
        negocioServicio.eliminarReserva(idNegocio, idCliente);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Reserva eliminada correctamente"));
    }

    @GetMapping("/listar-reservas/{idNegocio}")
    public ResponseEntity<MensajeDTO<List<DetalleReservaDTO>>> listarReservas(@PathVariable String idNegocio) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarReservas(idNegocio)));
    }

    @GetMapping("/listar-negocio-propietario/{codigoPropietario}")
    public ResponseEntity<MensajeDTO<List<Negocio>>> listarNegociosPropietario(@PathVariable String codigoPropietario) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarNegociosPropietario(codigoPropietario)));
    }

}

