package co.org.uniquindio.unilocal;

import co.org.uniquindio.unilocal.dto.Cuenta.*;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.comentario.*;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.servicios.interfaces.AutentificacionServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.CuentaServicio;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

    private AutentificacionServicio autentificacionServicio;

    private CuentaServicio cuentaServicio;


    //-------------------------Prueba unitaria de metodos en ClienteServicioIMPL-----------------------
    /**
     * Test que prueba el metodo de registrar cliente
     */
    //@Test
    public void registroClienteTest() throws Exception{
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(

                "sofia",
                "mifoto",
                        "sofi",
                "sofia@gmail.com",
                "contraseña",
                Ciudades.ARMENIA
        );

        String codigo = clienteServicio.registrarCliente(registroClienteDTO);
        Assertions.assertNotNull(codigo);
    }

    /**
     * Test que prueba el metodo de actualizar cliente
     */
    //@Test
    public void actualizarClienteTest() throws Exception {

        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO(
                "Cliente1",
                "Juan",
                "otraFoto",
                "Juanito",
                "juan123@gmail.com",
                Ciudades.BOGOTA
        );

        clienteServicio.actualizarCliente(actualizarClienteDTO);
    }


    /**
     * Test que prueba el metodo de eliminar cliente
     */
    //@Test
    public void eliminarClienteTest() throws Exception {
        //cambiamos el estado del cliente con el id creado anteriormente
        //de activo a inactivo
        clienteServicio.eliminarCliente("Cliente2");

    }

    /**
     * Test que prueba el metodo de obtener cliente
     */
    //@Test
    public void obtenerClienteTest() throws Exception {
        //Obtenemos el cliente con el id creado anteriormente
        DetalleClienteDTO cliente = clienteServicio.obtenerCliente("Cliente3");
        //Imprimimos el cliente
        System.out.println(cliente);
        //Verificamos que el cliente no sea nulo
        Assertions.assertNotNull(cliente);
    }

    /**
     * Test que prueba el metodo de listar clientes
     */
    //@Test
    public void listarClienteTest() throws Exception {
        //Obtenemos la lista de todos los clientes (por ahora solo tenemos 1)
        List<ItemDetalleClienteDTO> clientes = clienteServicio.listarClientes();
        //Imprimimos los clientes, se hace uso de una función lambda
        clientes.forEach(System.out::println);
        //Verificamos que solo exista un cliente
        Assertions.assertEquals(6, clientes.size());
    }


    /**
     * Test que prueba el metodo de iniciar sesion
     */
    //@Test
    /*
    public void iniciarSesionTest() throws Exception {
        //Creamos un objeto de tipo SesionDTO
        SesionDTO sesionDTO = new SesionDTO("aleja@gmail.com", "mypassword");
        //Iniciamos sesion con el objeto creado anteriormente
        autentificacionServicio.iniciarSesionCliente(sesionDTO);
    }
    */

    /**
     * Test que prueba el metodo de agregar favoritos
     */
    //@Test
    public void agregarFavoritosTest() throws Exception {
        //Agregamos un negocio a favoritos
        clienteServicio.agregarFavoritos("Negocio1", "Cliente1");
    }

    /**
     * Test que prueba el metodo de eliminar favoritos
     */

    //@Test
    public void removerFavoritosTest() throws Exception {
        //Removemos un negocio de favoritos
        clienteServicio.removerFavoritos("Negocio1", "Cliente1");
    }

    /**
     * Test que prueba el metodo de mostrar favoritos
     */
    //@Test
    public void mostrarFavoritosTest() throws Exception {
        //Obtenemos la lista de favoritos de un cliente
        List<FavoritoDTO> favoritos = clienteServicio.mostrarFavoritos("Cliente1");
        //Imprimimos los favoritos
        favoritos.forEach(System.out::println);
        //Verificamos que solo exista un favorito
        Assertions.assertEquals(1, favoritos.size());
    }


    /**
     * Test que prueba el metodo ItemListaLugaresCreados
     */
    //@Test
    public void listaLugaresCreadosTest() throws Exception {
        //Obtenemos la lista de lugares creados por un cliente
        List<ItemListaLugaresCreadosDTO> lugares = clienteServicio.listaLugaresCreados("Cliente1", "Negocio1");
        //Imprimimos los lugares creados
        lugares.forEach(System.out::println);
        //Verificamos que solo exista un lugar creado
        Assertions.assertEquals(1, lugares.size());
    }

    /**
     * Test que prueba el metodo de enviar link de recuperacion
     */
    //@Test
    public void enviarLinkRecuperacionTest() throws Exception {
        //Enviamos un link de recuperacion a un correo
        cuentaServicio.enviarLinkRecuperacion("ana@gmail.com");
    }

    /**
     * Test que prueba el metodo de cambiar contraseña
     */
    //@Test
    public void cambiarPasswordTest() throws Exception {
        //Cambiamos la contraseña de un cliente
        CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("Cliente1", "nuevaContraseña", "ana@gmail.com", "1" );
        cuentaServicio.cambiarPassword(cambioPasswordDTO);
    }

    //-------------------------Prueba unitaria de metodos en ComentarioServicio -----------------------

    /**
     * Test que prueba el metodo de crear comentario
     */
    //@Test
    public void crearComentarioTest() throws Exception {
        //Creamos un comentario
        RegistroComentarioDTO registroComentarioDTO = new RegistroComentarioDTO(LocalDateTime.now(), 5, "Cliente1", "Negocio1", "Excelente", "" );
        //Creamos el comentario
        comentarioServicio.crearComentario(registroComentarioDTO);
    }

    /**
     * Test que prueba el metodo de responder comentario
     */
    //@Test
    public void responderComentarioTest() throws Exception {
        //Creamos un comentario
        RespuestaComentarioDTO respuestaComentarioDTO = new RespuestaComentarioDTO("Comentario1", "Negocio1", "Gracias por tu comentario" );
        //Respondemos el comentario
        comentarioServicio.responderComentario(respuestaComentarioDTO);
    }

    /**
     * Test que prueba el metodo de listar comentarios de un negocio
     */
    //@Test
    public void listarComentariosNegocioTest() throws Exception {

        //Listamos los comentarios de un negocio
        List<ItemListaComentariosDTO> comentarios = comentarioServicio.listarComentariosNegocio("Negocio1");
        //Imprimimos los comentarios
        comentarios.forEach(System.out::println);
        //Verificamos que solo exista un comentario
        Assertions.assertEquals(1, comentarios.size());
    }

    /**
     * Test que prueba el metodo de calcular promedio de calificaciones
     */
    //@Test
    public void calcularPromedioCalificacionesTest() throws Exception {
        //Calculamos el promedio de calificaciones de un negocio
        int promedio = comentarioServicio.calcularPromedioCalificaciones("Negocio1");
        //Imprimimos el promedio
        System.out.println("El promedio de calificacion del negocio es: "+ promedio);

    }







}
