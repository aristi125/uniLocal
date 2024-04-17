package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.producto.ProductoDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.documentos.Producto;
import co.org.uniquindio.unilocal.repositorios.ProductoRepo;
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

    @Override
    public String registrarProducto(ProductoDTO productoDTO) throws Exception {
        return null;
    }

    @Override
    public void actualizarProducto(ProductoDTO productoDTO) throws Exception {

    }

    @Override
    public void eliminarProducto(ProductoDTO productoDTO) throws Exception {
       // Optional<Producto> negocioOptional = productoRepo.findById(idNegocio);

    }

   /* @Override
    public List<Producto> listarProductos()throws Exception {
        List<Producto> productos = productoRepo.findAllProducto();
        if(productos.isEmpty()){
            throw  new Exception("No existen historiales para este negocio");
        }
        return productos;
    }
    @Override
    public Producto buscarProducto(String codigoProducto) throws Exception {
        Producto producto = productoRepo.findByCodigoProducto(codigoProducto);
        if(producto==null)
        {
            throw new Exception("No hay ningun producto con esa identificacion");
        }
        return producto;
    }

    */
}
