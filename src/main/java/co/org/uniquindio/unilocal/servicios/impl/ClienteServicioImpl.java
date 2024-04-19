package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
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
    private final NegocioRepo negocioRepo;

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
    public void agregarFavoritos(String idNegocio, String idCliente) throws Exception{
        Optional<Negocio> optionalNegocio = negocioRepo.findById( idNegocio);
        if(optionalNegocio.isEmpty()){
            throw new Exception("No existe el negocio con el id "+idNegocio);
        }

        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);
        if (optionalCliente.isEmpty()) {
            throw new Exception("No existe el cliente con el ID " + idCliente);
        }
        Cliente cliente = optionalCliente.get();

        cliente.getAgregarFavoritos().add(optionalNegocio.get());

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
            negocio = negocioRepo.findByCodigo(favorito.getCodigo());
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
        Optional<Negocio> optionalNegocio = negocioRepo.findById( idNegocio );
        if(optionalNegocio.isEmpty()){
            throw new Exception("No existe el negocio con el id "+idNegocio);
        }

        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);
        if (optionalCliente.isEmpty()) {
            throw new Exception("No existe el cliente con el ID " + idCliente);
        }
        Cliente cliente = optionalCliente.get();

        cliente.getAgregarFavoritos().remove(optionalNegocio.get());

        clienteRepo.save(cliente);
    }

    @Override
    public List<ItemListaLugaresCreadosDTO> listaLugaresCreados(String idCliente, String idNegocio) throws Exception{
        Optional<Cliente> clientes = clienteRepo.findById(idCliente);
        List<Negocio> historialNegocio = negocioRepo.findAllByCodigo(idNegocio);
        ArrayList<ItemListaLugaresCreadosDTO> respuesta = new ArrayList<>();

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
                            n.getImagenes()
                    )
            );
        }
        return respuesta;
    }

    @Override
    public List<ItemListaLugaresCreadosDTO> buscarNegocioNombre(String nombre) throws Exception {

        List<Negocio> negocios = negocioRepo.findAll();
        List<ItemListaLugaresCreadosDTO> lugares = new ArrayList<>();
        if (negocios.isEmpty()) {
            throw new Exception("No se encontraron negocios con el nombre " + nombre);
        }
        for (Negocio n : negocios) {

            if (n.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                lugares.add(new ItemListaLugaresCreadosDTO(
                                n.getCodigo(),
                                n.getNombre(),
                                n.getTelefonos(),
                                n.getCategoriaNegocio(),
                                n.getImagenes()
                        )
                );
        }
        return lugares;

    }

    @Override
    public List<ItemListaLugaresCreadosDTO> buscarNegocioCategoria(CategoriaNegocio categoria) throws Exception {

        List<Negocio> negocios = negocioRepo.findByCategoriaNegocio(categoria);
        List<ItemListaLugaresCreadosDTO> lugares = new ArrayList<>();
        if (negocios.isEmpty()) {
            throw new Exception("No se encontraron negocios con la categoria " + categoria);
        }
        for (Negocio n : negocios) {
            lugares.add(new ItemListaLugaresCreadosDTO(
                            n.getCodigo(),
                            n.getNombre(),
                            n.getTelefonos(),
                            n.getCategoriaNegocio(),
                            n.getImagenes()
                    )
            );
        }
        return lugares;
    }

    @Override
    public List<ItemListaLugaresCreadosDTO> buscarNegocioDistancia(double distancia, Ubicacion ubicacionCliente) throws Exception {

        List<Negocio> negocios = negocioRepo.findAll();
        List<ItemListaLugaresCreadosDTO> lugaresCercanos = new ArrayList<>();

        for (Negocio negocio : negocios) {
            double distanciaCalculada = calcularDistancia(ubicacionCliente, negocio.getUbicacion());

            if (distanciaCalculada <= distancia) {
                // Agregar el lugar a la lista de lugares dentro de la distancia especificada
                lugaresCercanos.add(new ItemListaLugaresCreadosDTO(
                        negocio.getCodigo(),
                        negocio.getNombre(),
                        negocio.getTelefonos(),
                        negocio.getCategoriaNegocio(),
                        negocio.getImagenes())
                );
            }
        }

        return lugaresCercanos;

    }


    public double calcularDistancia(Ubicacion ubicacionCliente, Ubicacion ubicacionNegocio) {
        double radioTierra = 6371; //en kilómetros
        double dLat = Math.toRadians(ubicacionNegocio.getLatitud() - ubicacionCliente.getLatitud());
        double dLng = Math.toRadians(ubicacionNegocio.getLongitud() - ubicacionCliente.getLongitud());
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(ubicacionCliente.getLatitud())) * Math.cos(Math.toRadians(ubicacionNegocio.getLatitud()));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;
        return distancia;
    }

    @Override
    public List<ItemListaLugaresCreadosDTO> recomendarNegocio(String busqueda) throws Exception {

        List<Negocio> negocios = negocioRepo.findAll();
        List<ItemListaLugaresCreadosDTO> lugares = new ArrayList<>();
        if (negocios.isEmpty()) {
            throw new Exception("No se encontraron negocios");
        }
        for (Negocio n : negocios) {
            if (n.getNombre().toLowerCase().contains(busqueda) || n.getCategoriaNegocio().toString().toLowerCase().contains(busqueda)
                    || n.getDescripcion().toLowerCase().contains(busqueda) || n.getEstado().toString().toLowerCase().contains(busqueda)) {

                lugares.add(new ItemListaLugaresCreadosDTO(
                                n.getCodigo(),
                                n.getNombre(),
                                n.getTelefonos(),
                                n.getCategoriaNegocio(),
                                n.getImagenes()
                        )
                );
            }
        }
        return lugares;
    }


    @Override
    public List<ItemListaLugaresCreadosDTO> filtrarPorEstado(EstadoNegocio estadoNegocio)throws Exception {
        List<Negocio> negocios =negocioRepo.findByEstado(estadoNegocio);
        if(negocios.isEmpty()){
            throw  new Exception("No existen negocios con ese estado");
        }

        List<ItemListaLugaresCreadosDTO> lugares = new ArrayList<>();

        for (Negocio n : negocios) {
            lugares.add(new ItemListaLugaresCreadosDTO(
                            n.getCodigo(),
                            n.getNombre(),
                            n.getTelefonos(),
                            n.getCategoriaNegocio(),
                            n.getImagenes()
                    )
            );
        }

        return lugares;
    }
    // Validaciones
    private boolean existeEmail(String email){
        return clienteRepo.findByEmail(email).isPresent();
    }

    private boolean existeNickname(String nickname){
        return clienteRepo.findByNickname(nickname).isPresent();
    }

}
