package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.producto.ProductoDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Producto;
import co.org.uniquindio.unilocal.repositorios.ProductoRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ProductoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductoServicioImpl implements ProductoServicio {
    private final ProductoRepo productoRepo;
    private final ClienteServicio clienteServicio;
    private final NegocioServicio negocioServicio;


    @Override
    public void registrarProducto( ProductoDTO productoDTO) throws Exception {

        clienteServicio.buscarCliente(productoDTO.idCliente());
        // También se debe llamar al NegocioServicio y verificar que el negocio exista
        negocioServicio.buscarNegocio(productoDTO.idNegocio());
        Optional<Producto> optionalProducto = Optional.ofNullable(productoRepo.findByCodigo(productoDTO.codigo()));
        if (!optionalProducto.isEmpty()) {
            throw new RuntimeException("El producto ya existe");
        }

        Producto producto = new Producto();
        producto.setCodigo(producto.getCodigo());
        producto.setTipoProducto(productoDTO.tipoProducto());
        producto.setNombre(productoDTO.nombre());
        producto.setPrecio(productoDTO.precio());
        productoRepo.save(producto);
    }

    @Override
    public void actualizarProducto(ProductoDTO productoDTO) throws Exception {

        clienteServicio.buscarCliente(productoDTO.idCliente());
        // También se debe llamar al NegocioServicio y verificar que el negocio exista
        negocioServicio.buscarNegocio(productoDTO.idNegocio());
        Optional<Producto> optionalProducto = Optional.ofNullable(productoRepo.findByCodigo(productoDTO.codigo()));

        if (optionalProducto.isEmpty()) {
            throw new Exception("El usuario no existe");
        }

        Producto producto = optionalProducto.get();

        producto.setTipoProducto(productoDTO.tipoProducto());
        producto.setNombre(productoDTO.nombre());
        producto.setPrecio(productoDTO.precio());


        // Guardar los cambios en la base de datos
        productoRepo.save(producto);
    }

    // Consultar con el profesor sobre este método
    @Override
    public void eliminarProducto(ProductoDTO productoDTO) throws Exception {

        clienteServicio.buscarCliente(productoDTO.idCliente());
        // También se debe llamar al NegocioServicio y verificar que el negocio exista
        negocioServicio.buscarNegocio(productoDTO.idNegocio());
        // Buscar el negocio por su ID
        Optional<Producto> optionalProducto = Optional.ofNullable(productoRepo.findByCodigo(productoDTO.codigo()));

        if (optionalProducto.isEmpty()) {
            throw new Exception("El producto no existe");
        }

        // Guardar los cambios en la base de datos
        //productoRepo.save(producto);
        // Verificar si el usuario autenticado existe en la base de datos

        productoRepo.delete(optionalProducto.get());

    }

   @Override
    public List<Producto> listarProductos(ProductoDTO productoDTO)throws Exception {
       clienteServicio.buscarCliente(productoDTO.idCliente());
       // También se debe llamar al NegocioServicio y verificar que el negocio exista
       negocioServicio.buscarNegocio(productoDTO.idNegocio());
        List<Producto> productos = productoRepo.findAll();
        if(productos.isEmpty()){
            throw  new Exception("No existen historiales para este negocio");
        }
        return productos;
    }

    // Obtener todos los productos de un negocio dado su Id (Hacer Metodo)
}
