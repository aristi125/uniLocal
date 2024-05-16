package co.org.uniquindio.unilocal;

import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;
import co.org.uniquindio.unilocal.dto.cuenta.*;
import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.comentario.*;
import co.org.uniquindio.unilocal.dto.negocio.ActualizarNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.RegistroNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.ReporteDTO;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
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
    private ComentarioServicio comentarioServicio;

    @Autowired
    private EmailServicio emailServicio;

    @Autowired
    private AutentificacionServicio autentificacionServicio;

    @Autowired
    private NegocioServicio negocioServicio;

    @Autowired
    private ReservaServicio reservaServicio;


    //-------------------------Prueba unitaria de metodos en AutentificacionServicioIMPL-----------------------


    /**
     * Test que prueba el metodo de iniciar sesion para el cliente
     */
    @Test
    public void iniciarSesionTestCliente() throws Exception {
        //Creamos un objeto de tipo SesionDTO
        SesionDTO sesionDTO = new SesionDTO("aleja@gmail.com", "mypassword");
        //Iniciamos sesion con el objeto creado anteriormente
        autentificacionServicio.iniciarSesionCliente(sesionDTO);
    }

    /**
     * Test que prueba el metodo de iniciar sesion moderador
     */

    @Test
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
     * Test que prueba el metodo de agregar favoritos
     */
    //@Test
    public void agregarFavoritosTest() throws Exception {
        //Agregamos un negocio a favoritos
        negocioServicio.agregarFavoritos("Negocio1", "Cliente1");
    }

    /**
     * Test que prueba el metodo de eliminar favoritos
     */

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
        Assertions.assertEquals(1, favoritos.size());
    }


    /**
     * Test que prueba el metodo ItemListaLugaresCreados
     */
    //@Test
    public void listaLugaresCreadosTest() throws Exception {
        //Obtenemos la lista de lugares creados por un cliente
        List<ItemListaLugaresCreadosDTO> lugares = negocioServicio.listaLugaresCreados("Cliente1", "Negocio1");
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
        List<ItemListaLugaresCreadosDTO> lugares = negocioServicio.buscarNegocioNombre("Hotel");
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
        List<ItemListaLugaresCreadosDTO> lugares = negocioServicio.buscarNegocioDistancia(5.0, ubicacion);
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
        List<ItemListaLugaresCreadosDTO> lugares = negocioServicio.recomendarNegocio("comida");
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
        List<ItemListaLugaresCreadosDTO> lugares = negocioServicio.filtrarPorEstado(EstadoNegocio.ACTIVO);
        //Imprimimos los lugares
        lugares.forEach(System.out::println);
        //Verificamos que solo exista un lugar
        //Assertions.assertEquals(1, lugares.size());
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

    //-------------------------Prueba unitaria de metodos en CuentaServicio -----------------------
    /**
     * Test que prueba el metodo de enviar link de recuperacion
     */
    //@Test
    public void enviarLinkRecuperacionTest(LinkRecuperacionDTO linkRecuperacionDTO) throws Exception {
        //Enviamos un link de recuperacion a un correo
        clienteServicio.enviarLinkRecuperacionCliente(linkRecuperacionDTO);
    }

    /**
     * Test que prueba el metodo de cambiar contraseña
     */
    //@Test
    public void cambiarPasswordTest() throws Exception {
        //Cambiamos la contraseña de un cliente
        CambioPasswordDTO cambioPasswordDTO = new CambioPasswordDTO("Cliente1", "nuevaContraseña", "ana@gmail.com", "1" );
        clienteServicio.cambiarPassword(cambioPasswordDTO);
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
        Horario horario = new Horario("Lunes a viernes", LocalDate.now(), LocalDate.now());
        List<Horario> horarios = List.of(horario);
        String telefono = "123456";
        List<String> telefonos = List.of(telefono);
        String urlFoto = "urlFoto";
        List<String> urlFotos = List.of(urlFoto);

        RegistroNegocioDTO registroNegocioDTO = new RegistroNegocioDTO(
                "Negocio6",
                "Hotel calido y acogedor",
                horarios,
                telefonos,
                CategoriaNegocio.HOTEL,
                urlFotos,
                new Ubicacion(12,12),
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
        Horario horario = new Horario("Lunes a viernes", LocalDate.now(), LocalDate.now());
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
//    public void eliminarNegocioTest() throws Exception {
//        //cambiamos el estado del negocio con el id creado anteriormente
//        //de activo a inactivo
//        negocioServicio.eliminarNegocio("Negocio5");
//    }

    /**
     * Test que prueba el metodo de obtener negocio
     */
    //@Test
    public void obtenerNegocioTest() throws Exception {
        //Obtenemos el negocio con el id creado anteriormente
        Negocio negocio = negocioServicio.buscarNegocio("Negocio5");
        //Imprimimos el negocio
        System.out.println(negocio);

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
    //@Test
    public void generarPDFTest() throws Exception {
        LocalDateTime fecha = LocalDateTime.of(2024, 4, 18, 15, 30, 45);
        //Creamos un objeto de tipo ReporteDTO
        ReporteDTO reporteDTO = new ReporteDTO("Negocio1", "Hotel Premium",fecha,4);
        //Generamos el pdf
        negocioServicio.generarPDF(reporteDTO, "rutaArchivo");
    }


    //-------------------------Prueba unitaria de metodos en ReservaServicioIMPL-----------------------
    /**
     * Test que prueba el metodo de registrar reserva
     */
    //@Test
    public void registrarReservaTest() throws Exception {
        //Creamos un objeto de tipo RegistroReservaDTO
        DetalleReservaDTO registroReservaDTO = new DetalleReservaDTO(
                LocalDate.now(),
                LocalTime.now(),
                5,
                "Cliente1",
                "Negocio1"
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
                LocalDate.now(),
                LocalTime.now(),
                5,
                "Cliente1",
                "Negocio1"
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

    //-------------------------Prueba unitaria de metodos en AgenteServicioIMPL-----------------------

    /**
     * Test que prueba el metodo de registrar agenda
     */
    //@Test
    public void registrarAgendaTest() throws Exception {
        //Creamos un objeto de tipo RegistroAgendaDTO
        RegistroAgendaDTO registroAgendaDTO = new RegistroAgendaDTO(
                "Negocio1",
                "Tematica",
                "Descripcion"
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
                "Negocio1",
                "Tematica",
                "Descripcion"
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
        negocioServicio.eliminarAgenda("Negocio1");
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
}

