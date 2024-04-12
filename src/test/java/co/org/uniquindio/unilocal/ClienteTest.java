package co.org.uniquindio.unilocal;

import co.org.uniquindio.unilocal.dto.cliente.ActualizarClienteDTO;
import co.org.uniquindio.unilocal.dto.cliente.RegistroClienteDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
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
    ClienteRepo clienteRepo;
    @Autowired
    private ClienteServicio clienteServicio;

    /**
     * Test que prueba el metodo de registrar cliente
     * @throws Exception
     */

    public void RegistroClienteTest() throws Exception{
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
     * @throws Exception
     */

    //@Test
    public void ActualizarClienteTest() throws Exception {

        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO(
                "6618e3e48c3ebb652fc230ab",
                "sofia",
                "otraFoto",
                "sofi",
                "sofia123@gmail.com",
                Ciudades.BOGOTA
        );

        clienteServicio.actualizarCliente(actualizarClienteDTO);
    }


    //hacer el metodo usando las clases que creamos,
    //no de manera directa
    @Test
    public void listarClienteTest(){
//Obtenemos la lista de todos los clientes (por ahora solo tenemos 1)
        List<Cliente> clientes = clienteRepo.findAll();
//Imprimimos los clientes, se hace uso de una función lambda
        clientes.forEach(System.out::println);
//Verificamos que solo exista un cliente
        Assertions.assertEquals(1, clientes.size());
    }

/*
    @Test
    public void eliminarClienteTest(){
//Borramos el cliente con el id XXXXXXX
        clienteRepo.deleteById("XXXXXXX");
        //Obtenemos el cliente con el id XXXXXXX
        Cliente cliente = clienteRepo.findById("XXXXXXX").orElse(null);
//Verificamos que el cliente no exista
        Assertions.assertNull(cliente);
    }*/
}
