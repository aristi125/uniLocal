package co.org.uniquindio.unilocal.controladores;


import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
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

    @GetMapping("/listar-clientes")
    public ResponseEntity<MensajeDTO<List<ItemDetalleClienteDTO>>> listarClientes() throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.listarClientes() ));
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
}
