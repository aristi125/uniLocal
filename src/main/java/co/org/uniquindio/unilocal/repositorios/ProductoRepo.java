package co.org.uniquindio.unilocal.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import co.org.uniquindio.unilocal.modelo.documentos.Producto;
import java.util.List;
public interface ProductoRepo extends MongoRepository<Producto, String> {
   List<Producto> findAll();

}
