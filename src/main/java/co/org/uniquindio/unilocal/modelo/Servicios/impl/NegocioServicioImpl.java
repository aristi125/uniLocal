package co.org.uniquindio.unilocal.modelo.Servicios.impl;

import co.org.uniquindio.unilocal.dto.NegocioDTO.ActualizarNegocioDTO;
import co.org.uniquindio.unilocal.dto.NegocioDTO.RegistroNegocioDTO;
import co.org.uniquindio.unilocal.modelo.Servicios.interfaces.NegocioServicio;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocioEliminar;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NegocioServicioImpl implements NegocioServicio {

    private final ClienteRepo clienteRepo;
    private final NegocioRepo negocioRepo;
    @Override
    public String crearNegocio(RegistroNegocioDTO registroNegocioDTO, String id) throws Exception {
        // Obtener la información del usuario autenticado

        // Verificar si el usuario autenticado existe en la base de datos
        Cliente cliente = clienteRepo.findByEmail(id)
                .orElseThrow(() -> new Exception("El usuario autenticado no está registrado"));

        // Crear un nuevo negocio con la información proporcionada en el DTO
        Negocio negocio = new Negocio();
        negocio.setNombre(registroNegocioDTO.nombre());
        negocio.setDescripcion(registroNegocioDTO.descripcion());
        //PORQUE NO FUNCIONA
        negocio.setHorarios(registroNegocioDTO.horarios());

        negocio.setTelefonos((registroNegocioDTO.telefono()));
        negocio.setCategoriaNegocio(registroNegocioDTO.categoria());
        negocio.setUrlfoto((registroNegocioDTO.urlFoto()));
        //FALTA QUE AGREGUE LA UBICACION OSEA LOS DATOS X y Y
        negocio.getUbicacion().setLongitud(registroNegocioDTO.longitud());
        negocio.getUbicacion().setLatitud(registroNegocioDTO.latitud());


        // Asignar el cliente como propietario del negocio
        negocio.setCodigoCliente(id);

        // Guardar el negocio en la base de datos
        negocioRepo.save(negocio);

        return negocio.getCodigo(); // Devolver el ID del negocio creado
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO, String id) throws Exception {
        // Verificar si el usuario autenticado existe en la base de datos
        Cliente cliente = clienteRepo.findByEmail(id)
                .orElseThrow(() -> new Exception("El usuario autenticado no está registrado"));

        // Buscar el negocio por su ID
        Optional<Negocio> optionalNegocio = negocioRepo.findById(actualizarNegocioDTO.id());

        if (optionalNegocio.isEmpty()) {
            throw new Exception("El usuario no existe");
        }

        Negocio negocio = optionalNegocio.get();

        // Verificar si el usuario autenticado es propietario del negocio
        if (!negocio.getCodigoCliente().equals(id)) {
            throw new Exception("El negocio no pertenece al usuario autenticado");
        }

        // Actualizar los campos del negocio con los valores proporcionados en el DTO
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setDescripcion(actualizarNegocioDTO.descripcion());

        negocio.setHorarios(actualizarNegocioDTO.horarios());
        negocio.setTelefonos(actualizarNegocioDTO.telefonos());
        negocio.setCategoriaNegocio(actualizarNegocioDTO.categoria());
        negocio.setUrlfoto(Collections.singletonList(actualizarNegocioDTO.urlFoto()));

        // Guardar los cambios en la base de datos
        negocioRepo.save(negocio);

    }

    @Override
    public void eliminarNegocio(String idNegocio) throws Exception{
        Optional<Negocio> negocioOptional = negocioRepo.findById(idNegocio);
        if (negocioOptional.isEmpty()){
            throw new Exception("No existe un negocio con el codigo: "+ idNegocio);
        }

        Negocio negocio = negocioOptional.get();

        if (negocio.getEstadoNegocio().equals(EstadoNegocioEliminar.ACTIVO)){
            negocio.setEstadoNegocio(EstadoNegocioEliminar.INACTIVO);
        }
        else {
            throw new Exception(("No se que esta pasando"));
        }
    }

    @Override
    public void buscarNegocios() {

    }

    @Override
    public void filtrarPorEstado() {

    }

    @Override
    public void listarNegociosPropietario() {

    }

    @Override
    public void cambiarEstado() {

    }

    @Override
    public void registrarRevision() {

    }
}
