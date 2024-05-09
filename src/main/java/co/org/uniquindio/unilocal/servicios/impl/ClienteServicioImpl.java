package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.entidades.Cuenta;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import co.org.uniquindio.unilocal.utils.JWTUtils;
import lombok.AllArgsConstructor;
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
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    //private final NegocioRepo negocioRepo;
    private final NegocioServicio negocioServicio;
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
        //Buscamos el cliente que se quiere actualizar
        Optional<Cliente> optionalCliente = clienteRepo.findById( actualizarClienteDTO.id() );
        //Si no se encontró el cliente, lanzamos una excepción
        if(optionalCliente.isEmpty()){
            throw new Exception("No se encontró el cliente a actualizar");
        }
        //Obtenemos el cliente que se quiere actualizar y le asignamos los nuevos valores (elvnickname no se puede cambiar)
        Cliente cliente = optionalCliente.get();
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
        //Buscamos el cliente que se quiere eliminar
        Optional<Cliente> optionalCliente = clienteRepo.findById( idCuenta );
        //Si no se encontró el cliente, lanzamos una excepción
        if(optionalCliente.isEmpty()){
            throw new Exception("No se encontró el cliente a con el id "+idCuenta);
        }
        //Obtenemos el cliente
        Cliente cliente = optionalCliente.get();
        //Retornamos el cliente en formato DTO
        return new DetalleClienteDTO(cliente.getCodigo(), cliente.getNombre(),
                cliente.getFotoPerfil(), cliente.getNickname(), cliente.getPassword(), cliente.getEmail(), cliente.getCiudad());
    }

    @Override
    public void eliminarCliente(String idCuenta) throws Exception {
        //Buscamos el cliente que se quiere eliminar
        Optional<Cliente> optionalCliente = clienteRepo.findById( idCuenta );
        //Si no se encontró el cliente, lanzamos una excepción
        if(optionalCliente.isEmpty()){
            throw new Exception("No se encontró el cliente a eliminar");
        }
        //Obtenemos el cliente que se quiere eliminar y le asignamos el estado inactivo
        Cliente cliente = optionalCliente.get();
        cliente.setEstado(EstadoCuenta.INACTIVO);
        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino queactualiza el que ya existe
        clienteRepo.save(cliente);
    }

    @Override
    public List<ItemDetalleClienteDTO> listarClientes( ) {
        //Obtenemos todos los clientes de la base de datos
        List<Cliente> clientes = clienteRepo.findAll();
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
    public void agregarFavoritos(String idNegocio, String idCliente) throws Exception{

        Negocio negocio = negocioServicio.buscarNegocio(idNegocio);

        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);
        if (optionalCliente.isEmpty()) {
            throw new Exception("No existe el cliente con el ID " + idCliente);
        }
        Cliente cliente = optionalCliente.get();

        cliente.getAgregarFavoritos().add(negocio);

        clienteRepo.save(cliente);
    }

    @Override
    public List<FavoritoDTO> mostrarFavoritos(String idCliente) throws Exception{
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);
        if (optionalCliente.isEmpty()) {
            throw new Exception("No existe el cliente con el ID " + idCliente);
        }
        Cliente cliente = optionalCliente.get();
        List<Negocio> favoritoCliente = cliente.getAgregarFavoritos();
        List<FavoritoDTO> favoritos = new ArrayList<>();
        Negocio negocio = null;
        FavoritoDTO favoritoDTO = null;
        for (Negocio favorito : favoritoCliente) {
            negocio = negocioServicio.buscarNegocio(favorito.getCodigo());
            favoritoDTO = new FavoritoDTO(
                    negocio.getCodigo(),
                    negocio.getImagenes().get(0),
                    negocio.getNombre(),
                    negocio.getUbicacion()
            );
            favoritos.add(favoritoDTO);
        }
        return favoritos;
    }

    @Override
    public void removerFavoritos(String idNegocio, String idCliente) throws Exception{

        Negocio negocio = negocioServicio.buscarNegocio(idNegocio);

        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);
        if (optionalCliente.isEmpty()) {
            throw new Exception("No existe el cliente con el ID " + idCliente);
        }
        Cliente cliente = optionalCliente.get();

        cliente.getAgregarFavoritos().remove(negocio);

        clienteRepo.save(cliente);
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {
        //Buscamos el cliente con el email
        Optional<Cliente> optionalCliente = clienteRepo.findByEmail(email);
        //Si no se encontró el cliente, lanzamos una excepción
        if(optionalCliente.isEmpty()){
            throw new Exception("No se encontró el cliente con el email "+email);
        }
        //Obtenemos el cliente
        Cliente cliente = optionalCliente.get();
        String token = jwtUtils.generarToken(email, null);

        EmailDTO emailDTO = new EmailDTO(
                "Recuperación de contraseña",
                "Hola "+cliente.getNombre()+"! \n\n"+
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
        Optional<Cliente> optional =clienteRepo.findById(cambioPasswordDTO.id());

        if(optional.isEmpty()){
            throw new Exception("No existe el clienete con el codigo "+ cambioPasswordDTO.id());
        }

        //BUSCAMOS AL CLIENTE CON EL GET
        Cliente registro = optional.get();

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
        clienteRepo.save(registro);
    }

    // Validaciones
    private boolean existeEmail(String email){
        return clienteRepo.findByEmail(email).isPresent();
    }

    private boolean existeNickname(String nickname){
        return clienteRepo.findByNickname(nickname).isPresent();
    }

}
