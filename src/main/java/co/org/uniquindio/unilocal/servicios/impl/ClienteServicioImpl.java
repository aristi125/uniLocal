package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.cuenta.LinkRecuperacionDTO;
import co.org.uniquindio.unilocal.dto.negocio.IDClienteYNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.RegistroNegocioDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.*;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.EmailServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import co.org.uniquindio.unilocal.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final NegocioRepo negocioRepo;
    private final EmailServicio emailServicio;
    private final JWTUtils jwtUtils;

    @Override
    public String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception {

        if( existeEmail(registroClienteDTO.email())){
            throw new Exception("El correo ya se encuentra registrado");
        }
        if( existeNickname(registroClienteDTO.nickname()) ){
            throw new Exception("El nickname ya se encuentra registrado por otro usuario");
        }
        //Se crea el objeto Cliente
        Cliente cliente = new Cliente();
        //Se le asignan sus campos
        cliente.setNombre( registroClienteDTO.nombre() );
        cliente.setNickname( registroClienteDTO.nickname() );
        cliente.setCiudad( registroClienteDTO.ciudadResidencia() );
        cliente.setFotoPerfil( registroClienteDTO.fotoPerfil() );
        cliente.setEmail( registroClienteDTO.email() );
        cliente.setPassword( registroClienteDTO.password() );
        // Encriptar la password antes de guardar al cliente en la base de datos
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(registroClienteDTO.password());
        cliente.setPassword(passwordEncriptada);
        cliente.setEstado(EstadoCuenta.ACTIVO);

        //Se guarda en la base de datos y obtenemos el objeto registrado
        Cliente clienteGuardado = clienteRepo.save(cliente);
        //Retornamos el id (código) del cliente registrado
        return clienteGuardado.getCodigo();
    }

    @Override
    public void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        Cliente cliente = buscarCliente(actualizarClienteDTO.id());
        //Obtenemos el cliente que se quiere actualizar y le asignamos los nuevos valores (elvnickname no se puede cambiar)
        cliente.setNombre( actualizarClienteDTO.nombre() );
        cliente.setFotoPerfil( actualizarClienteDTO.fotoPerfil() );
        Ciudades ciudad = actualizarClienteDTO.ciudadResidencia();
        cliente.setCiudad( ciudad );

        if (cliente.getNickname().equals(actualizarClienteDTO.nickname())) {
            cliente.setNickname(actualizarClienteDTO.nickname());
        }else throw new Exception("El nickname no se puede cambiar");

        if(cliente.getEmail().equals(actualizarClienteDTO.email())){
            cliente.setEmail(actualizarClienteDTO.email());
        }else if (existeEmail(actualizarClienteDTO.email())){
            throw new Exception("El correo ya se encuentra registrado");
        }

        //Asignamos el nuevo email
        cliente.setEmail( actualizarClienteDTO.email() );
        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        clienteRepo.save(cliente);
    }

    @Override
    public DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception {
        Cliente cliente = buscarCliente(idCuenta);
        return new DetalleClienteDTO(cliente.getCodigo(), cliente.getNombre(),
                cliente.getFotoPerfil(), cliente.getNickname(), cliente.getEmail(), cliente.getCiudad());
    }

    @Override
    public Cliente buscarCliente(String idCuenta) throws Exception {
        //Buscamos el cliente que se quiere eliminar
        Optional<Cliente> optionalCliente = clienteRepo.findById( idCuenta );
        if(optionalCliente.isEmpty()){
            throw new Exception("No se encontró el cliente a con el id "+idCuenta);
        }
        Cliente cliente = optionalCliente.get();

        if(cliente.getEstado() == EstadoCuenta.INACTIVO || cliente.getEstado() == EstadoCuenta.BLOQUEADO){
            throw  new Exception("El cliente no se encuentra en uso");
        }
        return cliente;
    }

    @Override
    public void eliminarCliente(String idCuenta) throws Exception {
        Cliente cliente = buscarCliente(idCuenta);
        //Obtenemos el cliente que se quiere eliminar y le asignamos el estado inactivo
        cliente.setEstado(EstadoCuenta.INACTIVO);
        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino queactualiza el que ya existe
        clienteRepo.save(cliente);
    }

    @Override
    public List<ItemDetalleClienteDTO> listarClientes( ) {
        //Obtenemos todos los clientes de la base de datos
        List<Cliente> clientes = clienteRepo.findAllByEstado(EstadoCuenta.ACTIVO);
        //Creamos una lista de DTOs de clientes
        List<ItemDetalleClienteDTO> items = new ArrayList<>();
        //Recorremos la lista de clientes y por cada uno creamos un DTO y lo agregamos a la lista
        for (Cliente cliente : clientes) {
            items.add(new ItemDetalleClienteDTO(cliente.getCodigo(), cliente.getNombre(),
                    cliente.getFotoPerfil(),cliente.getEmail(), cliente.getCiudad()));
        }
        return items;
    }

    @Override
    public List<Ciudades> listarCiudades() throws Exception {
        return Arrays.asList(Ciudades.values());
    }

    @Override
    public List<CategoriaNegocio> listarCategoriaNegocio() throws Exception {
        return Arrays.asList(CategoriaNegocio.values());
    }

    @Override
    public void enviarLinkRecuperacionCliente(LinkRecuperacionDTO linkRecuperacionDTO) throws Exception {
        Cliente cliente = buscarCliente(linkRecuperacionDTO.idCuenta());
        String token = jwtUtils.generarToken(linkRecuperacionDTO.email(), null);

        EmailDTO emailDTO = new EmailDTO(
                "Recuperación de contraseña",
                "Hola "+cliente.getNombre()+"! \n\n"+
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
        Cliente cliente = buscarCliente(cambioPasswordDTO.idCuenta());

        String token = cambioPasswordDTO.token();
        jwtUtils.parseJwt(token);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String nuevaPasswordEncriptada = passwordEncoder.encode(cambioPasswordDTO.passwordNueva());

        // Verificar si la nueva contraseña es igual a la anterior
        if (passwordEncoder.matches(cambioPasswordDTO.passwordNueva(), cliente.getPassword())) {
            throw new Exception("La nueva contraseña no puede ser igual a la anterior.");
        }

        // Asignar la nueva contraseña encriptada al cliente
        cliente.setPassword(nuevaPasswordEncriptada);

        // Guardar el cliente actualizado en la base de datos
        clienteRepo.save(cliente);
    }

    @Override
    public void bloquearUsuario(String codigo) throws Exception {
        Cliente cliente = buscarCliente(codigo);
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
    public void agregarFavoritos(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception{
        Cliente cliente = buscarCliente(idClienteYNegocioDTO.idCliente());

        List<Negocio> favoritosCliente = cliente.getAgregarFavoritos();

        for(Negocio negociofav: favoritosCliente){
            if (negociofav.getCodigo().equals(idClienteYNegocioDTO.idNegocio())) {
                throw new Exception("El negocio ya se encuentra en los favoritos del cliente");
            }
        }
        Optional<Negocio> optionalNegocio = Optional.ofNullable(negocioRepo.findByCodigo(idClienteYNegocioDTO.idNegocio()));
        if (optionalNegocio.isEmpty()) {
            throw new Exception("No se encontró el negocio");
        }
        Negocio negocio = optionalNegocio.get();
        if (!negocio.getEstado().equals(EstadoNegocio.ACTIVO)) {
            throw new Exception("El negocio no está activo y no se puede agregar a favoritos");
        }

        favoritosCliente.add(optionalNegocio.get());
        clienteRepo.save(cliente);
    }

    @Override
    public List<FavoritoDTO> mostrarFavoritos(String idCliente) throws Exception {
        Cliente cliente = buscarCliente(idCliente);
        List<Negocio> favoritoCliente = cliente.getAgregarFavoritos();
        List<FavoritoDTO> favoritos = new ArrayList<>();

        for (Negocio negocio : favoritoCliente) {
            if (negocio.getEstado() == EstadoNegocio.ACTIVO) {
                FavoritoDTO favoritoDTO = new FavoritoDTO(
                        negocio.getCodigo(),
                        negocio.getImagenes().isEmpty() ? null : negocio.getImagenes().get(0),
                        negocio.getNombre(),
                        negocio.getUbicacion()
                );
                favoritos.add(favoritoDTO);
            }
        }
        return favoritos;
    }

    @Override
    public void eliminarFavoritos(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {
        // Buscar al cliente por su ID
        Cliente cliente = buscarCliente(idClienteYNegocioDTO.idCliente());

        // Obtener la lista de negocios favoritos del cliente
        List<Negocio> favoritosCliente = cliente.getAgregarFavoritos();

        // Buscar el negocio por su código
        Optional<Negocio> optionalNegocio = Optional.ofNullable(negocioRepo.findByCodigo(idClienteYNegocioDTO.idNegocio()));

        // Verificar si se encontró el negocio
        if (optionalNegocio.isEmpty()) {
            throw new Exception("No se encontró el negocio");
        }

        // Obtener el negocio del Optional
        Negocio negocio = optionalNegocio.get();

        // Verificar si el negocio está activo
        if (!negocio.getEstado().equals(EstadoNegocio.ACTIVO)) {
            throw new Exception("El negocio no está activo y no se puede eliminar de favoritos");
        }

        // Eliminar el negocio de la lista de favoritos del cliente
        if (!favoritosCliente.remove(negocio)) {
            throw new Exception("El negocio no se encuentra en los favoritos del cliente");
        }

        // Guardar los cambios en el cliente
        clienteRepo.save(cliente);
    }

    // Validaciones
    private boolean existeEmail(String email){
        return clienteRepo.findByEmail(email).isPresent();
    }

    private boolean existeNickname(String nickname){
        return clienteRepo.findByNickname(nickname).isPresent();
    }

}
