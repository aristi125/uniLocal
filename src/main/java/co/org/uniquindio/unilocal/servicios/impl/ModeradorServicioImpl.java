package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.Moderador.DetalleModeradorDTO;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.cuenta.LinkRecuperacionDTO;
import co.org.uniquindio.unilocal.modelo.documentos.*;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import co.org.uniquindio.unilocal.repositorios.ModeradorRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.EmailServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ModeradorServicio;
import co.org.uniquindio.unilocal.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;
    private final JWTUtils jwtUtils;
    private final EmailServicio emailServicio;;

    @Override
    public DetalleModeradorDTO obtenerModerador(String idCuenta) throws Exception {
        Moderador moderador = buscarModerador(idCuenta);
        return new DetalleModeradorDTO(moderador.getCodigo(), moderador.getNombre(), moderador.getFotoPerfil(),
                moderador.getNickname() ,moderador.getEmail(), moderador.getCiudadResidencia());
    }

    @Override
    public void enviarLinkRecuperacionModerador(LinkRecuperacionDTO linkRecuperacionDTO) throws Exception {
        Moderador moderador = buscarModerador(linkRecuperacionDTO.idCuenta());
        String token = jwtUtils.generarToken(linkRecuperacionDTO.email(), null);

        EmailDTO emailDTO = new EmailDTO(
                "Recuperación de contraseña",
                "Hola "+moderador.getNombre()+"! \n\n"+
                        "Hemos recibido una solicitud para recuperar tu contraseña. \n\n"+
                        "Si no has solicitado este cambio, por favor ignora este mensaje. \n\n"+
                        "Si deseas cambiar tu contraseña, haz clic en el siguiente enlace: \n\n"+
                        "http://localhost:8081/recuperar-password?codigo="+token+"\n\n"+
                        "Gracias por confiar en nosotros!",
                linkRecuperacionDTO.email()
        );

        emailServicio.enviarCorreo(emailDTO);

    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Moderador moderador = buscarModerador(cambioPasswordDTO.idCuenta());

        String token = cambioPasswordDTO.token();
        jwtUtils.parseJwt(token);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // Encriptar la nueva contraseña
        String nuevaPasswordEncriptada = passwordEncoder.encode(cambioPasswordDTO.passwordNueva());

        // Verificar si la nueva contraseña es igual a la anterior
        if (passwordEncoder.matches(cambioPasswordDTO.passwordNueva(), moderador.getPassword())) {
            throw new Exception("La nueva contraseña no puede ser igual a la anterior.");
        }
        // Asignar la nueva contraseña encriptada al cliente
        moderador.setPassword(nuevaPasswordEncriptada);

        // Guardar el cliente actualizado en la base de datos
        moderadorRepo.save(moderador);
    }

    @Override
    public Moderador buscarModerador(String idCuenta) throws Exception {
        //Buscamos el moderador que se quiere por su id
        Optional<Moderador> optionalModerador = moderadorRepo.findById( idCuenta );
        if(optionalModerador.isEmpty()){
            throw new Exception("No se encontró el moderador a con el id "+idCuenta);
        }
        Moderador moderador = optionalModerador.get();

        if(moderador.getEstado() == EstadoCuenta.INACTIVO){
            throw  new Exception("El moderador está inactivo");
        }
        return moderador;
    }
}
