package co.org.uniquindio.unilocal.servicios.impl;


import co.org.uniquindio.unilocal.dto.Cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.Cuenta.SesionDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import lombok.RequiredArgsConstructor;
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
    private final NegocioRepo negocioRepo;
    //@Autowired
    //private PasswordEncoder passwordEncoder;

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
        cliente.setEstado(EstadoCuenta.ACTIVO);
        //Se guarda en la base de datos y obtenemos el objeto registrado
        Cliente clienteGuardado = clienteRepo.save(cliente);
        //Retornamos el id (código) del cliente registrado
        return clienteGuardado.getCodigo();
    }
    //VALIDACIONES
    private boolean existeEmail(String email){
        return clienteRepo.findByEmail(email).isPresent();
    }

    private boolean existeNickname(String nickname){
        return clienteRepo.findByNickname(nickname).isPresent();
    }

    //PORQUE NO RETORNA NADA
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


        //miramos que no exte utilizado el email nuevo
        if(existeEmail(actualizarClienteDTO.email())){
            throw new Exception("El correo ya se encuentra registrado");
        }

        if (cliente.getEmail().equals(actualizarClienteDTO.email())) {
            cliente.setEmail(actualizarClienteDTO.email());
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
    public void iniciarSesion(SesionDTO sesionDTO) throws Exception {
        Optional<Cliente> optional = clienteRepo.findByEmail(sesionDTO.email());

        if (optional.isEmpty()) {
            throw new Exception("El correo electrónico "+ sesionDTO.email()+" no está registrado");
        }

        Cliente cliente = optional.get();

        // Aquí puedes verificar la contraseña utilizando PasswordEncoder o simplemente comparando la contraseña en texto plano
        // Por ejemplo, si usas PasswordEncoder:
        // if (!passwordEncoder.matches(sesionDTO.password(), cliente.getPassword())) {
        //     throw new Exception("La contraseña es incorrecta");
        // }

        // Si solo quieres verificar la contraseña en texto plano
        if (!sesionDTO.password().equals(cliente.getPassword())) {
            throw new Exception("La contraseña es incorrecta");
        }

        // Aquí puedes realizar cualquier lógica adicional que necesites para la sesión
        // Por ejemplo, generar un token de sesión, configurar la sesión del usuario, etc.

        System.out.println("!sesion iniciada¡");

    }


    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {

        Optional<Cliente> optional = clienteRepo.findById(idCuenta);

        if (optional.isEmpty()) {
            throw new Exception("No existe el cliente con el ID " + idCuenta);
        }

        Cliente cliente = optional.get();

        cliente.setEstado(EstadoCuenta.INACTIVO);

        clienteRepo.save(cliente);
    }

    //falta
    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Optional<Cliente> optional =clienteRepo.findById(cambioPasswordDTO.id());

        if(optional.isEmpty()){
            throw new Exception("No existe el paciente con el codigo "+ cambioPasswordDTO.id());
        }

        //BUSCAMOS AL CLIENTE CON EL GET
        Cliente buscado = optional.get();

        // Encriptar la nueva contraseña
        String nuevaPasswordEncriptada = "";//passwordEncoder.encode(cambioPasswordDTO.passwordNueva());

        // Asignar la nueva contraseña encriptada al cliente
        buscado.setPassword(nuevaPasswordEncriptada);

        // Guardar el cliente actualizado en la base de datos
        clienteRepo.save(buscado);
    }

    @Override
   public void favoritos(String idNegocio, String idCliente) throws Exception{
        Optional<Negocio> optionalNegocio = negocioRepo.findById( idNegocio );
        if(optionalNegocio.isEmpty()){
            throw new Exception("No existe el negocio con el id "+idNegocio);
        }

        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);
        if (optionalCliente.isEmpty()) {
            throw new Exception("No existe el cliente con el ID " + idCliente);
        }
        Cliente cliente = optionalCliente.get();

        cliente.getAgregarFavoritos().add(idNegocio);

        clienteRepo.save(cliente);
    }

    @Override
    public List<FavoritoDTO> mostrarFavoritos(String idCliente) throws Exception{
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);
        if (optionalCliente.isEmpty()) {
            throw new Exception("No existe el cliente con el ID " + idCliente);
        }
        Cliente cliente = optionalCliente.get();
        List<String> favoritoCliente = cliente.getAgregarFavoritos();
        List<FavoritoDTO> favoritos = new ArrayList<>();
        Negocio negocio = null;
        FavoritoDTO favoritoDTO = null;
        for (String favorito : favoritoCliente) {
            negocio = negocioRepo.findByCodigo(favorito);
            favoritoDTO = new FavoritoDTO(
                    negocio.getCodigo(),
                    negocio.getUrlfoto().get(0),
                    negocio.getNombre(),
                    negocio.getUbicacion()
            );
            favoritos.add(favoritoDTO);
        }
        return favoritos;
    }


    @Override
    public void removerFavoritos(String idNegocio, String idCliente) throws Exception{
        Optional<Negocio> optionalNegocio = negocioRepo.findById( idNegocio );
        if(optionalNegocio.isEmpty()){
            throw new Exception("No existe el negocio con el id "+idNegocio);
        }

        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);
        if (optionalCliente.isEmpty()) {
            throw new Exception("No existe el cliente con el ID " + idCliente);
        }
        Cliente cliente = optionalCliente.get();

        cliente.getAgregarFavoritos().remove(idNegocio);

        clienteRepo.save(cliente);
    }

    @Override
    public List<ItemListaLugaresCreadosDTO> listaLugaresCreados(String idCliente, String idNegocio) throws Exception{
        Optional<Cliente> clientes = clienteRepo.findById(idCliente);
        List<Negocio> historialNegocio = negocioRepo.findAllByCodigo(idNegocio);
        List<ItemListaLugaresCreadosDTO> respuesta = new ArrayList<>();

        if (clientes.isEmpty()) {
            throw new Exception("No ha creado una cuenta");
        }
        if (historialNegocio.isEmpty()){
            throw new Exception("No ha creado ningun negocio");
        }
        for(Negocio n: historialNegocio){
            respuesta.add( new ItemListaLugaresCreadosDTO(
                            n.getCodigo(),
                            n.getNombre(),
                            n.getTelefonos(),
                            n.getCategoriaNegocio(),
                            n.getUrlfoto()
                    )
            );
        }
        return respuesta;
    }
}
