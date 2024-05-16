package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.producto.ProductoDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Producto;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoProducto;
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
        Producto producto = buscarProducto(productoDTO.codigo());
        // Creamos el producto
        producto.setCodigo(producto.getCodigo());
        producto.setTipoProducto(productoDTO.tipoProducto());
        producto.setNombre(productoDTO.nombre());
        producto.setPrecio(productoDTO.precio());
        productoRepo.save(producto);
    }

    // Preguntar este método
    @Override
    public void actualizarProducto(ProductoDTO productoDTO) throws Exception {
        clienteServicio.buscarCliente(productoDTO.idCliente());
        // También se debe llamar al NegocioServicio y verificar que el negocio exista
        negocioServicio.buscarNegocio(productoDTO.idNegocio());
        Producto producto = buscarProducto(productoDTO.codigo());
        // Actualizamos los datos del producto
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
        // Buscar el prodcuto por su ID
        Producto producto = buscarProducto(productoDTO.codigo());
        producto.setEstadoProducto(EstadoProducto.INACTIVO);
        // Guardar los cambios en la base de datos
        productoRepo.save(producto);
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
    @Override
    public Producto buscarProducto(String codigoProducto) throws Exception {
        Optional<Producto> optionalProducto = productoRepo.findById( codigoProducto );
        if(optionalProducto.isEmpty()){
            throw new Exception("No se encontró el producto con el id "+ codigoProducto);
        }
        Producto producto = optionalProducto.get();

        if(producto.getEstadoProducto() == EstadoProducto.INACTIVO){
            throw  new Exception("El producto no se encuentra registrado");
        }
        return producto;
    }
}
