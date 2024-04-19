package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.cliente.ItemListaLugaresCreadosDTO;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.CuentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cuenta")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaServicio cuentaServicio;
    private final ClienteServicio clienteServicio;
    private final ComentarioServicio comentarioServicio;

    @GetMapping("/enviar-link-recuperacion-password")
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacion(@PathVariable String email) throws Exception {
        cuentaServicio.enviarLinkRecuperacion(email);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha enviado un link de recuperación a su correo" ));
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

    @GetMapping("/listar-comentarios-negocio")
    public ResponseEntity<MensajeDTO<List<ItemListaComentariosDTO>>> listarComentariosNegocio(@PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentariosNegocio(idNegocio)));
    }

    @GetMapping("/filtar-estado")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> filtrarPorEstado(@PathVariable EstadoNegocio estadoNegocio)throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.filtrarPorEstado(estadoNegocio)));
    }
}
