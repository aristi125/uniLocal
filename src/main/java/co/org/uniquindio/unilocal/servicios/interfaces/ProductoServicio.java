package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.producto.ProductoDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Producto;
import co.org.uniquindio.unilocal.repositorios.ProductoRepo;

import java.util.List;
public interface ProductoServicio {

    String registrarProducto(ProductoDTO productoDTO)throws Exception;
    void actualizarProducto(ProductoDTO productoDTO)throws Exception;
    void eliminarProducto(ProductoDTO productoDTO)throws Exception;
    //List<Producto> listarProductos()throws Exception;
    //Producto buscarProducto(String codigoProducto)throws Exception;
}
