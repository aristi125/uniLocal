package co.org.uniquindio.unilocal;

import co.org.uniquindio.unilocal.dto.BusquedaDistanciaDTO;
import co.org.uniquindio.unilocal.dto.agenda.AgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;
import co.org.uniquindio.unilocal.dto.cuenta.*;
import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.comentario.*;
import co.org.uniquindio.unilocal.dto.negocio.*;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Moderador;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoAgenda;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.servicios.interfaces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private EmailServicio emailServicio;

    @Autowired
    private AutentificacionServicio autentificacionServicio;

    @Autowired
    private NegocioServicio negocioServicio;


    //-------------------------Prueba unitaria de metodos en AutentificacionServicioIMPL-----------------------


    /**
     * Test que prueba el metodo de iniciar sesion para el cliente
     */
    //@Test
    public void iniciarSesionTestCliente() throws Exception {
        //Creamos un objeto de tipo SesionDTO
        SesionDTO sesionDTO = new SesionDTO("aleja@gmail.com", "mypassword");
        //Iniciamos sesion con el objeto creado anteriormente
        autentificacionServicio.iniciarSesionCliente(sesionDTO);
    }

    /**
     * Test que prueba el metodo de iniciar sesion moderador
     */

    // @Test
    public void iniciarSesionTestModerador() throws Exception {
        //Creamos un objeto de tipo SesionDTO
        SesionDTO sesionDTO = new SesionDTO("aleja@gmail.com", "mypassword");
        //Iniciamos sesion con el objeto creado anteriormente
        autentificacionServicio.iniciarSesionModerador(sesionDTO);
    }


    //-------------------------Prueba unitaria de metodos en ClienteServicioIMPL-----------------------
    /**
     * Test que prueba el metodo de registrar cliente
     */
   // @Test
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
   // @Test
    public void actualizarClienteTest() throws Exception {

        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO(
                "Cliente1",
                "Juan",
                "otraFoto",
                "juanito",
                "juanIS123@gmail.com",
                Ciudades.BOGOTA
        );

        clienteServicio.actualizarCliente(actualizarClienteDTO);
    }


    /**
     * Test que prueba el metodo de eliminar cliente
     */
   // @Test
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
     * Test que prueba el metodo de enviar link de recuperacion
     */
    //@Test
    public void enviarLinkRecuperacionClienteTest() throws Exception {
        LinkRecuperacionDTO linkRecuperacionDTO = new LinkRecuperacionDTO("Cliente1", "juan@email.com");
        //Enviamos un link de recuperacion a un correo
        clienteServicio.enviarLinkRecuperacionCliente(linkRecuperacionDTO);
    }

    //@Test
    public void cambiarPasswordClienteTest() throws Exception {
        //Cambiamos la contraseña de un cliente
        CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("Cuenta1", "nuevaContraseña", "ana@gmail.com", "1" );
        clienteServicio.cambiarPassword(cambioPasswordDTO);
    }

    //@Test
    public void bloquearUsuarioTest() throws Exception {
        //Bloqueamos un usuario
        clienteServicio.bloquearUsuario("Cliente2");
    }

    //-------------------------Prueba unitaria de metodos en ModeradorServicioIMPL-----------------------

    //@Test
    public void obtenerModeradorTest() throws Exception {
        //Obtenemos el moderador con el id creado anteriormente
        Moderador moderador = moderadorServicio.buscarModerador("Moderador1");
        //Imprimimos el moderador
        System.out.println(moderador);
        //Verificamos que el moderador no sea nulo
        Assertions.assertNotNull(moderador);
    }


    /**
     * Test que prueba el metodo de cambiar contraseña
     */
    //@Test
    public void enviarLinkRecuperacionModeradorTest() throws Exception {
        LinkRecuperacionDTO linkRecuperacionDTO = new LinkRecuperacionDTO("Moderador1", "moderador@email.com");
        moderadorServicio.enviarLinkRecuperacionModerador(linkRecuperacionDTO);
    }


    /**
     * Test que prueba el metodo de cambiar contraseña
     */
    //@Test
    public void cambiarPasswordModeradorTest() throws Exception {
        //Cambiamos la contraseña de un moderador
        CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("Cuenta1", "nuevaContraseña", "moderador@email.com", "1");
        moderadorServicio.cambiarPassword(cambioPasswordDTO);

    }


    //-------------------------Prueba unitaria de metodos en ComentarioServicio -----------------------

    /**
     * Test que prueba el metodo de crear comentario
     */
    //@Test
    public void crearComentarioTest() throws Exception {
        //Creamos un comentario
        RegistroComentarioDTO registroComentarioDTO = new RegistroComentarioDTO( 5, "Cliente1", "Negocio1", "Excelente");
        //Creamos el comentario
        comentarioServicio.crearComentario(registroComentarioDTO);
    }

    /**
     * Test que prueba el metodo de responder comentario
     */
    //@Test
    public void responderComentarioTest() throws Exception {
        //Creamos un comentario
        RespuestaComentarioDTO respuestaComentarioDTO = new RespuestaComentarioDTO("Comentario1", "Cliente1","Negocio1", "Gracias por tu comentario" );
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
        //Assertions.assertEquals(1, comentarios.size());
    }

    /**
     * Test que prueba el metodo de listar comentarios de una categoria de negocio
     */
    //@Test
    public void listarComentariosCategoriaTest() throws Exception {
        //Listamos los comentarios de una categoria de negocio
        List<ItemListaComentariosDTO> comentarios = comentarioServicio.listarComentariosTipoNegocio(CategoriaNegocio.HOTEL);
        //Imprimimos los comentarios
        comentarios.forEach(System.out::println);
        //Verificamos que solo exista un comentario
        //Assertions.assertEquals(1, comentarios.size());
    }



    //-------------------------Prueba unitaria de metodos en EmailServicioImpl-----------------------
    /**
     * Test que prueba el metodo de enviar correo
     */
    //@Test
    public void enviarCorreoTest() throws Exception {
        //Creamos un objeto de tipo EmailDTO
        EmailDTO emailDTO = new EmailDTO("Prueba", "Este es un correo de prueba", "ana@gmail.com");
        //Enviamos el correo
        emailServicio.enviarCorreo(emailDTO);
    }

    //-------------------------Prueba unitaria de metodos en NegocioServicioIMPL-----------------------

    /**
     * Test que prueba el metodo de registrar negocio
     */
    //@Test
    public void registroNegocioTest() throws Exception {
        Horario horario = new Horario("Lunes a viernes", LocalTime.now(), LocalTime.now());
        List<Horario> horarios = List.of(horario);
        String telefono = "123456";
        List<String> telefonos = List.of(telefono);
        String urlFoto = "urlFoto";
        List<String> urlFotos = List.of(urlFoto);

        Ubicacion ubicacion = new Ubicacion(5.0, 5.0);

        RegistroNegocioDTO registroNegocioDTO = new RegistroNegocioDTO(
                "Negocio6",
                "Hotel calido y acogedor",
                horarios,
                telefonos,
                CategoriaNegocio.HOTEL,
                urlFotos,
                ubicacion,
                "Cliente1"
        );

        String codigo = negocioServicio.crearNegocio(registroNegocioDTO);
        Assertions.assertNotNull(codigo);

    }

    /**
     * Test que prueba el metodo de actualizar negocio
     */
    //@Test
    public void actualizarNegocioTest() throws Exception {
        Horario horario = new Horario("Lunes a viernes", LocalTime.now(), LocalTime.now());
        List<Horario> horarios = List.of(horario);
        String telefono = "123456";
        List<String> telefonos = List.of(telefono);
        String urlFoto = "urlFoto";

        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(
                "Negocio5",
                "Hotel Premium",
                "Hotel de 5 estrellas",
                horarios,
                telefonos,
                CategoriaNegocio.HOTEL,
                urlFoto,
                "Cliente1"
        );

        negocioServicio.actualizarNegocio(actualizarNegocioDTO);

    }

    /**
     * Test que prueba el metodo de eliminar negocio
     */
    //@Test
    public void eliminarNegocioTest() throws Exception {
        //cambiamos el estado del negocio con el id creado anteriormente
        //de activo a inactivo
        IDClienteYNegocioDTO eliminacionNegocioDTO = new IDClienteYNegocioDTO("Negocio5", "Cliente1");
        negocioServicio.eliminarNegocio(eliminacionNegocioDTO);
    }

    /**
     * Test que prueba el metodo de obtener negocio
     */
    //@Test
    public void obtenerNegocioTest() throws Exception {
        //Obtenemos el negocio con el id creado anteriormente
        DetalleNegocioDTO detalleNegocioDTO = negocioServicio.obtenerNegocio("Negocio1");
        //Imprimimos el negocio
        System.out.println(detalleNegocioDTO);

    }

    /**
     * Test que prueba el metodo de listar negocios
     */
    //@Test
    public void listarNegociosTest() throws Exception {
        //Obtenemos la lista de todos los negocios (por ahora solo tenemos 1)
        List<Negocio> negocios = negocioServicio.listarNegociosPropietario("Cliente1");
        //Imprimimos los negocios, se hace uso de una función lambda
        negocios.forEach(System.out::println);
        //Verificamos que solo exista un negocio
        Assertions.assertEquals(1, negocios.size());
    }

    /**
     * Test que prueba el metodo de generar pdf
     */
    @Test
    public void generarPDFTest() throws Exception {
        //Creamos un objeto de tipo ReporteDTO
        ReporteDTO reporteDTO = new ReporteDTO("Cliente1","Negocio1", "Hotel Premium",4);
        //Generamos el pdf
        negocioServicio.generarPDF(reporteDTO);
    }

    /**
     * Test que prueba el metodo de registrar reserva
     */
    //@Test
    public void registrarReservaTest() throws Exception {
        //Creamos un objeto de tipo RegistroReservaDTO
        DetalleReservaDTO registroReservaDTO = new DetalleReservaDTO(

                "Cliente1",
                "Negocio1",
                5
        );
        //Registramos la reserva
        negocioServicio.registrarReserva(registroReservaDTO);
    }

    /**
     * Test que prueba el metodo de actualizar reserva
     */
    //@Test
    public void actualizarReservaTest() throws Exception {
        //Creamos un objeto de tipo ActualizarReservaDTO
        DetalleReservaDTO actualizarReservaDTO = new DetalleReservaDTO(

                "Cliente1",
                "Negocio1",
                5
        );
        //Actualizamos la reserva
        negocioServicio.actualizarReserva(actualizarReservaDTO);
    }

    /**
     * Test que prueba el metodo de obtener reserva
     */
    //@Test
    public void obtenerReservaTest() throws Exception {
        //Obtenemos la reserva
        DetalleReservaDTO reserva = negocioServicio.obtenerReserva("Negocio1", "Cliente1");
        //Imprimimos la reserva
        System.out.println(reserva);
        //Verificamos que la reserva no sea nula
        Assertions.assertNotNull(reserva);
    }

    /**
     * Test que prueba el metodo de eliminar reserva
     */
    //@Test
    public void eliminarReservaTest() throws Exception {
        //Eliminamos la reserva
        negocioServicio.eliminarReserva("Negocio1", "Cliente1");
    }

    /**
     * Test que prueba el metodo de listar reservas
     */
    //@Test
    public void listarReservasTest() throws Exception {
        //Listamos las reservas
        List<DetalleReservaDTO> reservas = negocioServicio.listarReservas("Negocio1");
        //Imprimimos las reservas
        reservas.forEach(System.out::println);
        //Verificamos que solo exista una reserva
        Assertions.assertEquals(1, reservas.size());
    }

    /**
     * Test que prueba el metodo de registrar agenda
     */
    //@Test
    public void registrarAgendaTest() throws Exception {
        //Creamos un objeto de tipo RegistroAgendaDTO
        RegistroAgendaDTO registroAgendaDTO = new RegistroAgendaDTO(
                "cliente1",
                "negocio1",
                "Historia",
                "Viaje por la historia del hombre"
        );
        //Registramos la agenda
        negocioServicio.registrarAgenda(registroAgendaDTO);
    }

    /**
     * Test que prueba el metodo de actualizar agenda
     */
    //@Test
    public void actualizarAgendaTest() throws Exception {
        //Creamos un objeto de tipo RegistroAgendaDTO
        RegistroAgendaDTO registroAgendaDTO = new RegistroAgendaDTO(
                "cliente1",
                "negocio1",
                "Historia de América",
                "Viaje por la historia del hombre en el continente americano"
        );
        //Actualizamos la agenda
        negocioServicio.actualizarAgenda(registroAgendaDTO);
    }

    /**
     * Test que prueba el metodo de eliminar agenda
     */
    //@Test
    public void eliminarAgendaTest() throws Exception {
        //Eliminamos la agenda
        negocioServicio.eliminarAgenda(new AgendaDTO("123", "123", EstadoAgenda.ACTIVA));
    }

    /**
     * Test que prueba el metodo de obtener agenda
     */
    //@Test
    public void obtenerAgendaTest() throws Exception {
        //Obtenemos la agenda
        DetalleAgendaDTO agenda = negocioServicio.obtenerAgenda("Negocio1");
        //Imprimimos la agenda
        System.out.println(agenda);
        //Verificamos que la agenda no sea nula
        Assertions.assertNotNull(agenda);

    }


    /**
     * Test que prueba el metodo de agregar favoritos
     */
    //@Test
    public void agregarFavoritosTest() throws Exception {
        //Agregamos un negocio a favoritos
        negocioServicio.agregarFavoritos(new IDClienteYNegocioDTO("negocio1", "cliente1"));
    }

    /**
     * Test que prueba el metodo de eliminar favoritos
     *

    //@Test
    public void removerFavoritosTest() throws Exception {
        //Removemos un negocio de favoritos
        negocioServicio.removerFavoritos("Negocio1", "Cliente1");
    }

    /**
     * Test que prueba el metodo de mostrar favoritos
     */
    //@Test
    public void mostrarFavoritosTest() throws Exception {
        //Obtenemos la lista de favoritos de un cliente
        List<FavoritoDTO> favoritos = negocioServicio.mostrarFavoritos("Cliente1");
        //Imprimimos los favoritos
        favoritos.forEach(System.out::println);
        //Verificamos que solo exista un favorito
        //Assertions.assertEquals(1, favoritos.size());
    }


    /**
     * Test que prueba el metodo ItemListaLugaresCreados
     */
    //@Test
    public void listarLugaresCreadosTest() throws Exception {
        //Obtenemos la lista de lugares creados por un cliente
        List<ItemListaLugaresCreadosDTO> lugares = negocioServicio.listarLugaresCreados(new IDClienteYNegocioDTO("123", "123"));
        //Imprimimos los lugares creados
        lugares.forEach(System.out::println);
        //Verificamos que solo exista un lugar creado
        Assertions.assertEquals(1, lugares.size());
    }

    /**
     * Test que prueba el metodo de filtrar lugar por nombre
     */
    //@Test
    public void filtrarLugarPorNombreTest() throws Exception {
        //Obtenemos la lista de lugares creados por un cliente
        List<ItemListaLugaresCreadosDTO> lugares = negocioServicio.buscarNegocioNombre(new BusquedaNombreDTO("123", "Las delicias"));
        //Imprimimos los lugares creados
        lugares.forEach(System.out::println);
        //Verificamos que solo exista un lugar creado
        //Assertions.assertEquals(1, lugares.size());
    }

    /**
     * Test que prueba el metodo de filtrar lugar por categoria
     */
    //@Test
    public void filtrarLugarPorCategoriaTest() throws Exception {
        //Obtenemos la lista de lugares creados por un cliente
        List<ItemListaLugaresCreadosDTO> lugares = negocioServicio.buscarNegocioCategoria(CategoriaNegocio.HOTEL);
        //Imprimimos los lugares creados
        lugares.forEach(System.out::println);
        //Verificamos que solo exista un lugar creado
        //Assertions.assertEquals(1, lugares.size());
    }

    /**
     * Test que prueba el metodo de filtrar lugar por distancia
     */
    //@Test
    public void filtrarLugarPorDistanciaTest() throws Exception {
        Ubicacion ubicacion = new Ubicacion(5.0, 5.0);
        //Obtenemos la lista de lugares filtrados
        List<ItemListaLugaresCreadosDTO> lugares = negocioServicio.buscarNegocioDistancia(new BusquedaDistanciaDTO(new Ubicacion(12.4, 23.8), 200));
        //Imprimimos los lugares
        lugares.forEach(System.out::println);
        //Verificamos que solo exista un lugar
        //Assertions.assertEquals(1, lugares.size());
    }

    /**
     * Test que prueba el metodo de filtrar recomendaciones de lugares
     */
    //@Test
    public void filtrarRecomendacionesTest() throws Exception {
        //Obtenemos la lista de lugares recomendados
        List<ItemListaLugaresCreadosDTO> lugares = negocioServicio.recomendarNegocio(new IDClienteYNegocioDTO("123", "123"));
        //Imprimimos los lugares
        lugares.forEach(System.out::println);
        //Verificamos que solo exista un lugar
        //Assertions.assertEquals(1, lugares.size());
    }

    /**
     * Test que prueba el metodo de filtrar lugares por estado
     */
    //@Test
    public void filtrarLugarPorEstadoTest() throws Exception {
        //Obtenemos la lista de lugares filtrados
        List<DetalleNegocioDTO> lugares = negocioServicio.filtrarPorEstado(EstadoNegocio.ACTIVO);
        //Imprimimos los lugares
        lugares.forEach(System.out::println);
        //Verificamos que solo exista un lugar
        //Assertions.assertEquals(1, lugares.size());
    }

}
