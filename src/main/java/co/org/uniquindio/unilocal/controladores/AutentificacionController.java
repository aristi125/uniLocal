package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.TokenDTO;
import co.org.uniquindio.unilocal.dto.cliente.RegistroClienteDTO;
import co.org.uniquindio.unilocal.dto.cuenta.SesionDTO;
import co.org.uniquindio.unilocal.servicios.interfaces.AutentificacionServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.el.parser.Token;
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


    // Consultar al profesor sobre esta modificacion
    @PostMapping("/registrar-cliente")
    public ResponseEntity<MensajeDTO<String>> registrarCliente(@Valid @RequestBody RegistroClienteDTO registroClienteDTO) throws Exception {
        clienteServicio.registrarCliente(registroClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "El cliente se ha registrado satisfactoriamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesion(@Valid @RequestBody SesionDTO sesionDTO) throws Exception {
        TokenDTO tokenDTO = autentificacionServicio.iniciarSesion((sesionDTO));
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }
}
