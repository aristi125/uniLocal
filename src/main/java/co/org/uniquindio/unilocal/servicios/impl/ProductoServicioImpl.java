package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.producto.ProductoDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Comentario;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.documentos.Producto;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.ProductoRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ProductoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductoServicioImpl implements ProductoServicio {
    private final ProductoRepo productoRepo;
    private final ClienteRepo clienteRepo;
    @Override
    public void registrarProducto( ProductoDTO productoDTO,String id) throws Exception {
        Optional<Producto> optionalProducto = Optional.ofNullable(productoRepo.findByCodigo(productoDTO.codigo()));
        if (!optionalProducto.isEmpty()) {
            throw new RuntimeException("El producto ya existe");
        }

        Optional<Cliente> cliente = clienteRepo.findById(id);
        if (cliente.isEmpty()) {
            throw new RuntimeException("No existe cliente");
        }

        Producto producto = new Producto();
        producto.setCodigo(producto.getCodigo());
        producto.setTipoProducto(productoDTO.tipoProducto());
        producto.setNombre(productoDTO.nombre());
        producto.setPrecio(productoDTO.precio());
        productoRepo.save(producto);
    }

    @Override
    public void actualizarProducto(ProductoDTO productoDTO,String id) throws Exception {
        // Verificar si el usuario autenticado existe en la base de datos
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new Exception("El usuario autenticado no está registrado"));

        // Buscar el negocio por su ID
        Optional<Producto> optionalProducto = Optional.ofNullable(productoRepo.findByCodigo(id));

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

    @Override
    public void eliminarProducto(String codigoProducto,String id) throws Exception {
        // Verificar si el usuario autenticado existe en la base de datos
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new Exception("El usuario autenticado no está registrado"));

        // Buscar el negocio por su ID
        Optional<Producto> optionalProducto = Optional.ofNullable(productoRepo.findByCodigo(id));

        if (optionalProducto.isEmpty()) {
            throw new Exception("El producto no existe");
        }

        // Guardar los cambios en la base de datos
        //productoRepo.save(producto);
        // Verificar si el usuario autenticado existe en la base de datos


        productoRepo
                .delete(optionalProducto.get());

    }

   @Override
    public List<Producto> listarProductos()throws Exception {
        List<Producto> productos = productoRepo.findAll();
        if(productos.isEmpty()){
            throw  new Exception("No existen historiales para este negocio");
        }
        return productos;
    }
}
