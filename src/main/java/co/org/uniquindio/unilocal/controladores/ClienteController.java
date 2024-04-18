package co.org.uniquindio.unilocal.controladores;


import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.comentario.RegistroComentarioDTO;
import co.org.uniquindio.unilocal.dto.comentario.RespuestaComentarioDTO;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.servicios.interfaces.AgendaServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.CuentaServicio;
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
    private final AgendaServicio agendaServicio;
    private final CuentaServicio cuentaServicio;
    private final ComentarioServicio comentarioServicio;

    @PutMapping("/actualizar-perfil-cliente")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@Valid @RequestBody ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente actualizado exitosamente"));
    }

    @GetMapping("/obtener/{idCuenta}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@PathVariable String idCuenta) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.obtenerCliente(idCuenta)));
    }

    @DeleteMapping("/eliminar/{idCuenta}")
    public ResponseEntity<MensajeDTO<String>> eliminarCliente(@PathVariable String idCuenta) throws Exception {
        clienteServicio.eliminarCliente(idCuenta);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente eliminado satisfactoriamente"));
    }

    @PostMapping("/sitios-favoritos")
    public ResponseEntity<MensajeDTO<String>> agregarFavoritos(@Valid @RequestBody String idNegocio, String idCliente) throws Exception {
        clienteServicio.agregarFavoritos(idNegocio, idCliente);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Sitio agregado a favoritos"));
    }

    @GetMapping("/obtener-favoritos-cliente")
    public ResponseEntity<MensajeDTO<List<FavoritoDTO>>> mostrarFavoritos(@PathVariable String idCliente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.mostrarFavoritos(idCliente)));
    }

    @DeleteMapping("eliminar-favoritos")
    public ResponseEntity<MensajeDTO<String>> removerFavoritos(@PathVariable String idNegocio, @PathVariable String idCliente) throws Exception {
        clienteServicio.removerFavoritos(idNegocio, idCliente);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio eliminado de favoritos"));
    }

    @GetMapping("lugares-creados-cliente")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> listaLugaresCreados(@PathVariable String idCliente, @PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.listaLugaresCreados(idCliente, idNegocio)));
    }

    @GetMapping("/buscar-negocio-nombre")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioNombre(@PathVariable String nombre) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.buscarNegocioNombre(nombre)));
    }

    @GetMapping("/buscar-negocio-categoria")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioCategoria(@PathVariable CategoriaNegocio categoria) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.buscarNegocioCategoria(categoria)));
    }

    @GetMapping("/buscar-negocio-distancia")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioDistancia(@PathVariable double distancia, @PathVariable Ubicacion ubicacionCliente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.buscarNegocioDistancia(distancia, ubicacionCliente)));
    }

    @GetMapping("/recomendar-negocio")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> recomendarNegocio(@PathVariable String busqueda) throws Exception {
        return  ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.recomendarNegocio(busqueda)));
    }

    @GetMapping("/filtar-estado")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> filtrarPorEstado(@PathVariable EstadoNegocio estadoNegocio)throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.filtrarPorEstado(estadoNegocio)));
    }

    @PostMapping("/registrar-agenda")
    public ResponseEntity<MensajeDTO<String>> registroAgenda(@Valid @RequestBody RegistroAgendaDTO registroAgendaDTO) throws Exception {
        agendaServicio.registrarAgenda(registroAgendaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "La agenda ha sido registrada"));
    }

    @PutMapping("/actualizar-agenda")
    public ResponseEntity<MensajeDTO<String>> actualizarAgenda(@Valid @RequestBody RegistroAgendaDTO registroAgendaDTO) throws Exception {
        agendaServicio.actualizarAgenda(registroAgendaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Agenda actualiza correctamente"));
    }

    @DeleteMapping("/eliminar-agenda")
    public ResponseEntity<MensajeDTO<String>> eliminarAgenda(@PathVariable String codigoNegocio) throws Exception {
        agendaServicio.eliminarAgenda(codigoNegocio);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Agenda eliminada satisfactoriamente"));
    }

    @GetMapping("/obtener-agenda")
    public ResponseEntity<MensajeDTO<DetalleAgendaDTO>> obtenerAgenda(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, agendaServicio.obtenerAgenda(codigoNegocio)));
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@Valid @RequestBody CambioPasswordDTO cambioPasswordDTO) throws Exception {
        cuentaServicio.cambiarPassword(cambioPasswordDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha cambiado su contraseña" ));
    }

    @GetMapping("/enviar-link-recuperacion-password")
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacion(@PathVariable String email) throws Exception {
        cuentaServicio.enviarLinkRecuperacion(email);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha enviado un link de recuperación a su correo" ));
    }

    @PostMapping("/crear-comentario")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@Valid @RequestBody RegistroComentarioDTO registroComentarioDTO) throws Exception {
        comentarioServicio.crearComentario(registroComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario creado exitosamente"));
    }

    @PostMapping("/responder-comentario")
    public ResponseEntity<MensajeDTO<String>> responderComentario(@Valid @RequestBody RespuestaComentarioDTO respuestaComentarioDTO) {
        comentarioServicio.responderComentario(respuestaComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "El comentario ha sido respondido de manera exitosa"));
    }

    @GetMapping("/listar-comentarios-negocio")
    public ResponseEntity<MensajeDTO<List<ItemListaComentariosDTO>>> listarComentariosNegocio(@PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentariosNegocio(idNegocio)));
    }

    @GetMapping("/calcular-promedio-negocio")
    public ResponseEntity<MensajeDTO<Integer>> calcularPromedioCalificaciones(@PathVariable String codigoNegocio) throws Exception {
        comentarioServicio.calcularPromedioCalificaciones(codigoNegocio);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.calcularPromedioCalificaciones(codigoNegocio)));
    }
}
