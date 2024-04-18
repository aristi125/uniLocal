package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.cliente.DetalleClienteDTO;
import co.org.uniquindio.unilocal.dto.cliente.ItemDetalleClienteDTO;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/moderadores")
@RequiredArgsConstructor
public class ModeradorController {

    private final ClienteServicio clienteServicio;

    @GetMapping("/listar-clientes")
    public ResponseEntity<MensajeDTO<List<ItemDetalleClienteDTO>>> listarClientes() throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.listarClientes() ));
    }
}
