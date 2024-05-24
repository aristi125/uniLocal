package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.Moderador.DetalleModeradorDTO;
import co.org.uniquindio.unilocal.dto.Moderador.RevisionesModeradorDTO;
import co.org.uniquindio.unilocal.dto.cliente.ItemDetalleClienteDTO;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.negocio.IDClienteYNegocioDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ModeradorServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderadores")
@RequiredArgsConstructor
public class ModeradorController {

    private final ClienteServicio clienteServicio;
    private final ModeradorServicio moderadorServicio;
    private final NegocioServicio negocioServicio;


    @GetMapping("/listar-clientes")
    public ResponseEntity<MensajeDTO<List<ItemDetalleClienteDTO>>> listarClientes() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.listarClientes()));
    }

    @GetMapping("/obtener-historial-revisiones/{idNegocio}")
    public ResponseEntity<MensajeDTO<List<HistorialRevision>>> obtenerHistorialRevisiones(@PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.obtenerHistorialRevisiones(idNegocio)));
    }

    @PutMapping("/bloquear-usuario/{codigo}")
        public ResponseEntity<MensajeDTO<String>> bloquearUsuario(@PathVariable String codigo) throws Exception {
        clienteServicio.bloquearUsuario(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "El Usuario ha sido bloqueado"));
    }

    @PutMapping("/rechazar-negocio")
    public ResponseEntity<MensajeDTO<String>> rechazarNegocio(@Valid @RequestBody RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        negocioServicio.rechazarNegocio(revisionesModeradorDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "El Negocio ha sido rechazado"));
    }
    @PutMapping("/aprobar-negocio")
    public ResponseEntity<MensajeDTO<String>> aprobarNegocio(@Valid @RequestBody RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        negocioServicio.aprobarNegocio(revisionesModeradorDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "El Negocio ha sido aprobado"));
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@Valid @RequestBody CambioPasswordDTO cambioPasswordDTO) throws Exception {
        moderadorServicio.cambiarPassword(cambioPasswordDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha cambiado su contraseña" ));
    }

    @GetMapping("/listar-negocio-propietario/{codigoPropietario}")
    public ResponseEntity<MensajeDTO<List<Negocio>>> listarNegociosPropietario(@PathVariable String codigoPropietario) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarNegociosPropietario(codigoPropietario)));
    }

    @GetMapping("/buscar-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<Negocio>> buscarNegocio(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocio(codigoNegocio)));
    }

    @GetMapping("/buscar-cliente/{idCuenta}")
    public ResponseEntity<MensajeDTO<Cliente>> buscarCliente(@PathVariable String idCuenta) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.buscarCliente(idCuenta)));
    }

    @GetMapping("/obtener-moderador/{idCuenta}")
    public ResponseEntity<MensajeDTO<DetalleModeradorDTO>> obtenerModerador(@PathVariable String idCuenta) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, moderadorServicio.obtenerModerador(idCuenta)));
    }

    @GetMapping("/obtener-cliente-por-idNegocio/{idNegocio}")
    public ResponseEntity<MensajeDTO<Cliente>> ontenerClienteIdNegocio(@PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerClienteNegocio(idNegocio)));
    }
}
