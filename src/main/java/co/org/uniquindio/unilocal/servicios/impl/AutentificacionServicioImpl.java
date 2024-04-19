package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.cuenta.SesionDTO;
import co.org.uniquindio.unilocal.dto.TokenDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Cuenta;
import co.org.uniquindio.unilocal.modelo.documentos.Moderador;
import co.org.uniquindio.unilocal.modelo.enumeracion.RolUsuario;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.CuentaRepo;
import co.org.uniquindio.unilocal.repositorios.ModeradorRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.AutentificacionServicio;
import co.org.uniquindio.unilocal.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AutentificacionServicioImpl implements AutentificacionServicio {
    // Variables
    private final ClienteRepo clienteRepo;
    private final ModeradorRepo moderadorRepo;
    private final JWTUtils jwtUtils;
    private final CuentaRepo cuentaRepo;

    // Metodo para iniciar sesion como cliente
    @Override
    public TokenDTO iniciarSesionCliente(SesionDTO sesionDTO) throws Exception {

        Optional<Cuenta> cuentaOptional = cuentaRepo.findByEmail(sesionDTO.email());

        if (cuentaOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado en la Base de Datos");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cuenta cuenta = cuentaOptional.get();

        if (!passwordEncoder.matches(sesionDTO.password(), cuenta.getPassword())) {
            throw new Exception("La contraseña es incorrecta, inténtelo de nuevo");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rol", RolUsuario.CLIENTE);
        map.put("nombre", cuenta.getNombre());
        map.put("codigo", cuenta.getCodigo());

        return new TokenDTO(jwtUtils.generarToken(cuenta.getEmail(), map));
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
        map.put("rol", RolUsuario.MODERADOR);
        map.put("nombre", moderador.getNombre());
        map.put("codigo", moderador.getCodigo());

        return new TokenDTO(jwtUtils.generarToken(moderador.getEmail(), map));
    }
}
