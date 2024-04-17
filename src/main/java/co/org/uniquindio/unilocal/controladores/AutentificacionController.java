package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.Cuenta.SesionDTO;
import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.TokenDTO;
import co.org.uniquindio.unilocal.dto.cliente.RegistroClienteDTO;
import co.org.uniquindio.unilocal.servicios.interfaces.AutentificacionServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutentificacionController {
    private final AutentificacionServicio autentificacionServicio;
    private final ClienteServicio clienteServicio;

    @PostMapping("/login-cliente")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionCliente(@Valid @RequestBody SesionDTO sesionDTO) throws Exception {
        TokenDTO tokenDTO = autentificacionServicio.iniciarSesionCliente((sesionDTO));
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/login-moderador")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionModerador(@Valid @RequestBody SesionDTO sesionDTO) throws Exception {
        TokenDTO tokenDTO = autentificacionServicio.iniciarSesionModerador((sesionDTO));
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    // Consultar al profesor sobre esta modificacion
    @PostMapping("/registrar-cliente")
    public ResponseEntity<MensajeDTO<String>> registrarCliente(@Valid @RequestBody RegistroClienteDTO registroClienteDTO) throws Exception {
        clienteServicio.registrarCliente(registroClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "El cliente se ha registrado satisfactoriamente"));
    }
}
