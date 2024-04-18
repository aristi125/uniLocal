package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.producto.ProductoDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Producto;
import co.org.uniquindio.unilocal.repositorios.ProductoRepo;

import java.util.List;
public interface ProductoServicio {

    void registrarProducto(ProductoDTO productoDTO,String id)throws Exception;

    void actualizarProducto(ProductoDTO productoDTO, String id)throws Exception;

    void eliminarProducto(String codigoProducto, String id)throws Exception;

    List<Producto> listarProductos()throws Exception;

}
