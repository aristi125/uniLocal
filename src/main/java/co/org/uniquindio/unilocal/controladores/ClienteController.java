package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.Cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
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
    private final CuentaServicio cuentaServicio;

    @PutMapping("/actualizar-perfil-cliente")
    public void actualizarCliente(@Valid @RequestBody ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        clienteServicio.actualizarCliente(actualizarClienteDTO);
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

    @GetMapping("/enviar-link-recuperacion-password")
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacion(String email) throws Exception {
        cuentaServicio.enviarLinkRecuperacion(email);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha enviado un link de recuperación a su correo" ));
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        cuentaServicio.cambiarPassword(cambioPasswordDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha cambiado su contraseña" ));
    }

    @PostMapping("/sitios-favoritos")
    public ResponseEntity<MensajeDTO<String>> favoritos(String idNegocio, String idCliente) throws Exception {
        return null;
    }

    @GetMapping("/obtener-favoritos-cliente")
    public List<FavoritoDTO> mostrarFavoritos(String idCliente) throws Exception {
        return null;
    }

    @DeleteMapping("eliminar-favoritos")
    public void removerFavoritos(String idNegocio, String idCliente) throws Exception {

    }

    @GetMapping("lugares-creados-cliente")
    public List<ItemListaLugaresCreadosDTO> listaLugaresCreados(String idCliente, String idNegocio) throws Exception {
        return null;
    }
}
