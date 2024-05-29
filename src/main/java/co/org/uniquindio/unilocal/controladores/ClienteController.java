package co.org.uniquindio.unilocal.controladores;


import co.org.uniquindio.unilocal.dto.*;
import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.comentario.RegistroComentarioDTO;
import co.org.uniquindio.unilocal.dto.comentario.RespuestaComentarioDTO;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.negocio.*;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.servicios.interfaces.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteServicio clienteServicio;
    private final ComentarioServicio comentarioServicio;
    private final NegocioServicio negocioServicio;

    @PutMapping("/actualizar-perfil-cliente")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@Valid @RequestBody ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cliente actualizado exitosamente"));
    }

    @GetMapping("/obtener-cliente/{idCuenta}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@PathVariable String idCuenta) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.obtenerCliente(idCuenta)));
    }

    @DeleteMapping("/eliminar/{idCuenta}")
    public ResponseEntity<MensajeDTO<String>> eliminarCliente(@PathVariable String idCuenta) throws Exception {
        clienteServicio.eliminarCliente(idCuenta);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cliente eliminado satisfactoriamente"));
    }

    @PostMapping("/agregar-favoritos")
    public ResponseEntity<MensajeDTO<String>> agregarFavoritos(@Valid @RequestBody IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {
        clienteServicio.agregarFavoritos(idClienteYNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Sitio agregado a favoritos"));
    }

    @GetMapping("/mostrar-favoritos/{idCliente}")
    public ResponseEntity<MensajeDTO<List<FavoritoDTO>>> mostrarFavoritos(@PathVariable String idCliente) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.mostrarFavoritos(idCliente)));
    }

    @DeleteMapping("/eliminar-favoritos")
    public ResponseEntity<MensajeDTO<String>> eliminarFavoritos(@Valid @RequestBody IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {
        clienteServicio.eliminarFavoritos(idClienteYNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio eliminado de favoritos"));
    }

    @GetMapping("/lugares-creados-cliente/{idCliente}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> listarLugaresCreados(@Valid @RequestBody IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarLugaresCreados(idClienteYNegocioDTO)));
    }

    @GetMapping("/buscar-negocio-nombre/{nombre}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegocioNombre(@Valid @RequestBody String nombre) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocioNombre(nombre)));
    }

    @GetMapping("/buscar-negocio-categoria/{categoriaNegocioDTO}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegocioCategoria(@PathVariable CategoriaNegocio categoriaNegocioDTO) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocioCategoria(categoriaNegocioDTO)));
    }

    @GetMapping("/buscar-negocio-distancia")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioDistancia(@Valid @RequestBody BusquedaDistanciaDTO busquedaDistanciaDTO) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocioDistancia(busquedaDistanciaDTO)));
    }

    @GetMapping("/recomendar-negocio")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> recomendarNegocio(@Valid @RequestBody IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.recomendarNegocio(idClienteYNegocioDTO)));
    }

    @GetMapping("/filtrar-estado/{estadoNegocioDTO}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorEstado(@Valid @RequestBody EstadoNegocio estadoNegocioDTO) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtrarPorEstado(estadoNegocioDTO)));
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

    @DeleteMapping("/eliminar-agenda")
    public ResponseEntity<MensajeDTO<String>> eliminarAgenda(@Valid @RequestBody IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {
        negocioServicio.eliminarAgenda(idClienteYNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Agenda eliminada satisfactoriamente"));
    }

    @GetMapping("/obtener-agenda")
    public ResponseEntity<MensajeDTO<DetalleAgendaDTO>> obtenerAgenda(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerAgenda(codigoNegocio)));
    }

    @PutMapping("/cambiar-password")
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

    @DeleteMapping("/eliminar-negocio")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@Valid @RequestBody IDClienteYNegocioDTO eliminacionNegocioDTO) throws Exception {
        negocioServicio.eliminarNegocio(eliminacionNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio eliminado satisfactoriamente"));
    }

    @GetMapping("/buscar-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<Negocio>> buscarNegocio(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocio(codigoNegocio)));
    }

    @PostMapping("/generar-PDF")
    public ResponseEntity<MensajeDTO<String>> generarPDF(@Valid @RequestBody ReporteDTO reporteDTO) throws Exception {
        negocioServicio.generarPDF(reporteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "El PDF se ha enviado a su correo"));
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

    @GetMapping("/obtener-reserva")
    public ResponseEntity<MensajeDTO<DetalleReservaDTO>> obtenerReserva(@Valid @RequestBody IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO(false, negocioServicio.obtenerReserva(idClienteYNegocioDTO)));
    }

    @DeleteMapping("/eliminar-reserva")
    public ResponseEntity<MensajeDTO<String>> eliminarReserva(@Valid @RequestBody IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {
        negocioServicio.eliminarReserva(idClienteYNegocioDTO);
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

    @GetMapping("/listar-ciudades")
    public ResponseEntity<MensajeDTO<List<Ciudades>>> listarCiudades() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.listarCiudades()));
    }

    @GetMapping("/listar-categoria-negocio")
    public ResponseEntity<MensajeDTO<List<Ciudades>>> listarCategoriaNegocio() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.listarCiudades()));
    }

    @GetMapping("/ver-detalle-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<ItemNegocioDTO>> verDetalleNegocio(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.verDetalleNegocio(codigoNegocio)));
    }
}

