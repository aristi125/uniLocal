package co.org.uniquindio.unilocal;

import co.org.uniquindio.unilocal.dto.Cuenta.SesionDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.AutentificacionServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Scanner;


@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteServicio clienteServicio;
    private AutentificacionServicio autentificacionServicio;


    //-------------------------Prueba unitaria de metodos DTO en carpeta CLIENTE-----------------------
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
    public void iniciarSesionTest() throws Exception {
        //Creamos un objeto de tipo SesionDTO
        SesionDTO sesionDTO = new SesionDTO("aleja@gmail.com", "mypassword");
        //Iniciamos sesion con el objeto creado anteriormente
        autentificacionServicio.iniciarSesionCliente(sesionDTO);
    }

    /**
     * Test que prueba el metodo de favoritos
     */
    //@Test
    public void favoritosTest() throws Exception {
        //Agregamos un negocio a favoritos
        clienteServicio.favoritos("Negocio1", "Cliente1");
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

    //-------------------------Prueba unitaria de metodos en carpeta -----------------------




}
