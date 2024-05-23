package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.cuenta.SesionDTO;
import co.org.uniquindio.unilocal.dto.TokenDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.entidades.Cuenta;
import co.org.uniquindio.unilocal.modelo.documentos.Moderador;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.ModeradorRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.AutentificacionServicio;
import co.org.uniquindio.unilocal.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AutentificacionServicioImpl implements AutentificacionServicio {
    // Variables
    private final ClienteRepo clienteRepo;
    private final ModeradorRepo moderadorRepo;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO iniciarSesion(SesionDTO sesionDTO) throws Exception {

        Optional<Cliente> clienteoptional = clienteRepo.findByEmail(sesionDTO.email());

        if(clienteoptional.isPresent()) {
            return iniciarSesionCliente(sesionDTO);
        }

        Optional<Moderador> moderadoroptional = moderadorRepo.findByEmail(sesionDTO.email());

        if(moderadoroptional.isPresent()) {
            return iniciarSesionModerador(sesionDTO);
        }

        return null;
    }
    // Metodo para iniciar sesion como cliente
    @Override
    public TokenDTO iniciarSesionCliente(SesionDTO sesionDTO) throws Exception {

        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(sesionDTO.email());

        if (clienteOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado en la Base de Datos");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cliente cliente = clienteOptional.get();

        if (!passwordEncoder.matches(sesionDTO.password(), cliente.getPassword())) {
            throw new Exception("La contraseña es incorrecta, inténtelo de nuevo");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "CLIENTE");
        map.put("nombre", cliente.getNombre());
        map.put("codigo", cliente.getCodigo());

        return new TokenDTO(jwtUtils.generarToken(cliente.getEmail(), map));
    }

    @Override
    public TokenDTO iniciarSesionModerador(SesionDTO sesionDTO) throws Exception {

        Optional<Moderador> moderadorOptional = moderadorRepo.findByEmail(sesionDTO.email());

        if (moderadorOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado en la Base de Datos");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Moderador moderador = moderadorOptional.get();
        if (!passwordEncoder.matches(sesionDTO.password(), moderador.getPassword())) {
            throw new Exception("La contraseña es incorrecta, inténtelo de nuevo");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "MODERADOR");
        map.put("nombre", moderador.getNombre());
        map.put("codigo", moderador.getCodigo());

        return new TokenDTO(jwtUtils.generarToken(moderador.getEmail(), map));
    }
}
