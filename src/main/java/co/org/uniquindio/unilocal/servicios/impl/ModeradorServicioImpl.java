package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.Moderador.RevisionesModeradorDTO;
import co.org.uniquindio.unilocal.dto.comentario.RevisarComentariosDTO;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.negocio.ItemNegociosRevisionDTO;
import co.org.uniquindio.unilocal.modelo.documentos.*;
import co.org.uniquindio.unilocal.modelo.entidades.Cuenta;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocioModerador;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.ComentarioRepo;
import co.org.uniquindio.unilocal.repositorios.ModeradorRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
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
    private final NegocioRepo negocioRepo;
    private final ComentarioRepo comentarioRepo;
    private final ClienteRepo clienteRepo;
    private final EmailServicioImpl emailServicio;
    private final ModeradorRepo moderadorRepo;
    private final JWTUtils jwtUtils;


    @Override
    public List<HistorialRevision> obtenerHistorialRevisiones(String idNegocio) throws Exception{
        List<HistorialRevision> historiales = negocioRepo.findByCodigo(idNegocio).getHistorialRevisiones();
        if(historiales.isEmpty()){
            throw  new Exception("No existen historiales para este negocio");
        }
        return historiales;
    }

    @Override
    public List<RevisarComentariosDTO> revisarComentarios(String codigo) throws Exception {
        if(codigo.isEmpty()){
            throw new Exception("El codigo no puede estar nulo");
        }
        List<Comentario> comentarioList = comentarioRepo.findAllByCodigoNegocio(codigo);
        List<RevisarComentariosDTO> comentariosDTO = new ArrayList<>();
        for(Comentario comentario : comentarioList){
            Optional<Cliente> clienteOptional = clienteRepo.findById(comentario.getCodigoCliente());
            if(clienteOptional.isEmpty()){
                throw new Exception("No existe el cliente con el codigo " + comentario.getCodigoCliente());
            }
            else{
                Cliente cliente = clienteOptional.get();
                comentariosDTO.add(new RevisarComentariosDTO(comentario.getMensaje(), comentario.getCodigoCliente(), cliente.getNombre(), cliente.getEmail(),
                        comentario.getFecha()));
            }
        }
        return comentariosDTO;
    }

    @Override
    public List<ItemNegociosRevisionDTO> listarRevisiones(EstadoNegocio estadoNegocio) throws Exception {
        List<Negocio> negocios = negocioRepo .findByEstado(estadoNegocio);
        List<ItemNegociosRevisionDTO> itemNegociosRevisionDTOSList = new ArrayList<>();
        for (Negocio negocio : negocios) {
            itemNegociosRevisionDTOSList.add(new ItemNegociosRevisionDTO(negocio.getNombre(),negocio.getDescripcion(),negocio.getTelefonos(),negocio.getUbicacion(),negocio.getHorarios(),negocio.getCategoriaNegocio(),negocio.getImagenes()));
        }
        return itemNegociosRevisionDTOSList;

    }

    @Override
    public void bloquearUsuario(String codigo) throws Exception {
    Optional<Cliente> clienteOptional = clienteRepo.findById(codigo);
    if(clienteOptional.isEmpty()){
        throw new Exception("No existe el cliente con el codigo " + codigo);
    }
    Cliente cliente = clienteOptional.get();
    cliente.setEstado(EstadoCuenta.BLOQUEADO);
        EmailDTO emailDTO = new EmailDTO(
                "Bloqueo de cuenta",
                "Hola " + cliente.getNombre() + "! \n\n" +
                        "Su Cuenta fue bloqueada\n\n" +
                        "La cuenta incumple con las politicas de la aplicacion." + "\n\n" +
                        "Si se trata de un error, por favor comuníquese vía correo para solucionarlo. \n\n" +
                        "Gracias por confiar en nosotros!",
                cliente.getEmail()
        );
        emailServicio.enviarCorreo(emailDTO);
        clienteRepo.save(cliente);
    }

    @Override
    public void revisarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {

    }

    @Override
    public void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        Optional<Negocio> optionalNegocio = negocioRepo.findById(revisionesModeradorDTO.codigoNegocio());
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no se ecnuentra registrado");
        }

        Negocio negocio = optionalNegocio.get();
        HistorialRevision revision = new HistorialRevision();

        revision.setDescripcion(revisionesModeradorDTO.descripcion());
        revision.setCodigoModerador(revisionesModeradorDTO.codigoModerador());
        revision.setCodigoNegocio(revisionesModeradorDTO.codigoNegocio());
        revision.setEstado(EstadoNegocioModerador.RECHAZADO);
        revision.setFecha(revisionesModeradorDTO.fecha());

        negocio.setEstado(EstadoNegocio.INACTIVO);
        negocio.getHistorialRevisiones().add(revision);

        Optional<Cliente> optionalCliente = clienteRepo.findById(optionalNegocio.get().getCodigoCliente());
        if (optionalCliente.isEmpty()) {
            throw new Exception("El cliente no se ecnuentra registrado");
        }

        EmailDTO emailDTO = new EmailDTO(
                "Negocio Rechazado",
                "Hola " + optionalCliente.get().getNombre() + "! \n\n" +
                        "Su negocio : "+negocio.getNombre()+"\n\n"+
                        "Identificado con: "+negocio.getCodigo()+"\n\n" +
                        "Fue rechazado debido a que no cumple con nuestras politicas" + "\n\n" +
                        "Por favor haz cambios y vuelve a hacer la solicitud antes de 5 dias habiles" + "\n\n" +
                        "Si se trata de un error, por favor comuníquese vía correo para solucionarlo. \n\n" +
                        "Gracias por confiar en nosotros!",
                optionalCliente.get().getEmail()
        );
        emailServicio.enviarCorreo(emailDTO);


        negocioRepo.save(negocio);
    }

    @Override
    public void aprobarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        Optional<Negocio> optionalNegocio = negocioRepo.findById(revisionesModeradorDTO.codigoNegocio());
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no se ecnuentra registrado");
        }

        Negocio negocio = optionalNegocio.get();
        HistorialRevision revision = new HistorialRevision();

        revision.setDescripcion(revisionesModeradorDTO.descripcion());
        revision.setCodigoModerador(revisionesModeradorDTO.codigoModerador());
        revision.setCodigoNegocio(revisionesModeradorDTO.codigoNegocio());
        revision.setEstado(EstadoNegocioModerador.APROBADO);
        revision.setFecha(revisionesModeradorDTO.fecha());

        negocio.setEstado(EstadoNegocio.INACTIVO);
        negocio.getHistorialRevisiones().add(revision);

        Optional<Cliente> optionalCliente = clienteRepo.findById(optionalNegocio.get().getCodigoCliente());
        if (optionalCliente.isEmpty()) {
            throw new Exception("El cliente no se ecnuentra registrado");
        }

        EmailDTO emailDTO = new EmailDTO(
                "Negocio Aceptado",
                "Hola " + optionalCliente.get().getNombre() + "! \n\n" +
                        "Su negocio : "+negocio.getNombre()+"\n\n"+
                        "Identificado con: "+negocio.getCodigo()+"\n\n" +
                        "Fue aceptado debido a que cumple con nuestras politicas" + "\n\n" +
                        "Felicidades!" + "\n\n" +
                        "Si se trata de un error, porfavor comuniquese via correo para solucionarlo. \n\n" +
                        "Gracias por confiar en nosotros!",
                optionalCliente.get().getEmail()
        );
        emailServicio.enviarCorreo(emailDTO);


        negocioRepo.save(negocio);
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {
        //Buscamos el cliente con el email
        Optional<Moderador> optionalModerador = moderadorRepo.findByEmail(email);
        //Si no se encontró el cliente, lanzamos una excepción
        if(optionalModerador.isEmpty()){
            throw new Exception("No se encontró el cliente con el email "+email);
        }
        //Obtenemos el cliente
        Cuenta cuenta = optionalModerador.get();
        String token = jwtUtils.generarToken(email, null);

        EmailDTO emailDTO = new EmailDTO(
                "Recuperación de contraseña",
                "Hola "+cuenta.getNombre()+"! \n\n"+
                        "Hemos recibido una solicitud para recuperar tu contraseña. \n\n"+
                        "Si no has solicitado este cambio, por favor ignora este mensaje. \n\n"+
                        "Si deseas cambiar tu contraseña, haz clic en el siguiente enlace: \n\n"+
                        "http://localhost:8081/recuperar-password?codigo="+token+"\n\n"+
                        "Gracias por confiar en nosotros!",
                email
        );

    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Optional<Moderador> optional =moderadorRepo.findById(cambioPasswordDTO.id());

        if(optional.isEmpty()){
            throw new Exception("No existe el clienete con el codigo "+ cambioPasswordDTO.id());
        }

        //BUSCAMOS AL CLIENTE CON EL GET
        Moderador registro = optional.get();

        if( registro.getEstado() == EstadoCuenta.INACTIVO ){
            throw new Exception("No existe el clienete con el codigo "+ cambioPasswordDTO.id());
        }

        String token = cambioPasswordDTO.token();
        jwtUtils.parseJwt(token);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // Encriptar la nueva contraseña
        String nuevaPasswordEncriptada = passwordEncoder.encode(cambioPasswordDTO.passwordNueva());

        // Asignar la nueva contraseña encriptada al cliente
        registro.setPassword(nuevaPasswordEncriptada);

        // Guardar el cliente actualizado en la base de datos
        moderadorRepo.save(registro);
    }
}
