package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.Usuario.*;
import co.org.uniquindio.unilocal.exceptions.ExisteEmailException;
import co.org.uniquindio.unilocal.exceptions.ExisteNicknameException;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.documentos.Usuario;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import co.org.uniquindio.unilocal.repositorios.UsuarioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.UsuarioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public String registarUsuario(RegistroUsuarioDTO registroUsuarioDTO) throws Exception {
        if (existeEmail(registroUsuarioDTO.email())) {
            throw new ExisteEmailException("El correo ya se encuentra registrado");
        }

        if (existeNickname(registroUsuarioDTO.nickname())) {
            throw new ExisteNicknameException("El nickname ya se encuentra en uso por otro usuario");
        }

        // Se crea el objeto Usuario
        Usuario usuario = new Usuario();

        // Se agregan los campos al Usuario
        usuario.setNombre(registroUsuarioDTO.nombre());
        usuario.setNickname(registroUsuarioDTO.nickname());
        usuario.setCiudad(registroUsuarioDTO.ciudadResidencia());
        usuario.setFotoPerfil(registroUsuarioDTO.fotoPerfil());
        usuario.setEmail(registroUsuarioDTO.email());
        usuario.setPassword(registroUsuarioDTO.password());
        usuario.setEstadoCuenta(EstadoCuenta.ACTIVO);

        // Se guardan los datos en DB y se obtiene el registro del objeto
        Usuario usuarioRegistrado = usuarioRepo.save(usuario);

        // Se retorna el codigo del cliente registrado
        return usuarioRegistrado.getCodigo();
    }

    @Override
    public void actualizarUsuario(ActualizarUsuarioDTO actualizarUsuarioDTO) throws Exception {
        Optional<Usuario> optionalUsuario = usuarioRepo.findById( actualizarUsuarioDTO.codigo() );

        // Lanza una excepcion al no encontrar el usuario
        if (optionalUsuario.isEmpty()) {
            throw new Exception("No existe usuario");
        }

        // Obtiene el cliente y actualiza los datos, el nickname no se pueda actualizar
        Usuario usuario = optionalUsuario.get();
        usuario.setNombre( actualizarUsuarioDTO.nombre() );
        usuario.setFotoPerfil( actualizarUsuarioDTO.fotoPerfil() );
        usuario.setCiudad( actualizarUsuarioDTO.ciudadResidencia() );
        usuario.setEmail( actualizarUsuarioDTO.email() );

        // Actualiza el objeto en la DB
        usuarioRepo.save(usuario);
    }

    @Override
    public DetalleUsuarioDTO obtenerUsuario(String codigo) throws Exception {
       Optional<Usuario> optionalUsuario = usuarioRepo.findById(codigo);

       if (optionalUsuario.isEmpty()) {
           throw new Exception("No se encuentra el usuario con código " +  codigo);
       }

       //Obtiene el usuario
       Usuario usuario = optionalUsuario.get();

       // Retorna el usuario en formato DTO
       return new DetalleUsuarioDTO(usuario.getCodigo(), usuario.getNombre(), usuario.getFotoPerfil(), usuario.getNickname(),
               usuario.getEmail(), usuario.getCiudad());
    }

    @Override
    public void eliminarUsuario(String codigo) throws Exception {
        // Busca al cliente que desea eliminar
        Optional<Usuario> optionalUsuario = usuarioRepo.findById( codigo );

        if (optionalUsuario.isEmpty()) {
            throw new Exception("No se encuentra el cliente que desea eliminar");
        }

        // Se obtiene el usuario a eliminar y se cambia su estado a INACTIVO
        Usuario usuario = optionalUsuario.get();
        usuario.setEstadoCuenta(EstadoCuenta.INACTIVO);

        // Actualiza el registro
        usuarioRepo.save(usuario);
    }

    @Override
    public List<ItemUsuarioDTO> listarUsuario() {
        // Se obtiene todos los usuarios de la CD
        List<Usuario> usuarios = usuarioRepo.findAll();

        // Crea la lista de DTOs de usuarios
        List<ItemUsuarioDTO> items = new ArrayList<>();

        // Recorre la lista de usuarios y crea un DTO por cada uno
        for (Usuario usuario: usuarios) {
            items.add(new ItemUsuarioDTO(usuario.getCodigo(), usuario.getNombre(), usuario.getNickname(), usuario.getFotoPerfil(), usuario.getCiudad()));
        }

        return items;
    }

    @Override
    public void recuperarPassword(cambioPasswordDTO cambioPasswordDTO) throws Exception {

    }

    @Override
    public List<Negocio> listarLugaresCercanos() {
        return null;
    }

    @Override
    public List<Negocio> listarLugaresCreados() {
        return null;
    }

    @Override
    public void comentarNegocio() throws Exception {

    }

    @Override
    public void calificarNegocio() throws Exception {

    }

    private boolean existeNickname(String nickname) throws ExisteNicknameException {
        Optional<Usuario> usuarioExistente = usuarioRepo.findByNickname(nickname);
        if (usuarioExistente.isPresent()) {
            throw new ExisteNicknameException("El nickname ya se encuentra en uso por otro usuario");
        }
        return usuarioRepo.findByNickname(nickname).isPresent();
    }

    private boolean existeEmail(String email) throws ExisteEmailException {
        Optional<Usuario> usuarioExistente = usuarioRepo.findByEmail(email);
        if (usuarioExistente.isPresent()) {
            throw new ExisteEmailException("El correo ya se encuentra registrado");
        }
        return usuarioRepo.findByEmail(email).isPresent();
    }
}
