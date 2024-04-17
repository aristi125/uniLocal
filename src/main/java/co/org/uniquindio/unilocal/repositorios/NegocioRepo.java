package co.org.uniquindio.unilocal.repositorios;

import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String> {
    Negocio findByCodigo(String codigo);
    List<Negocio> findAllByCodigo(String codigo);
    List<Negocio> getHistorialRevisiones(String codigo);
    List<Negocio> findByNombre(String nombre);
    List<Negocio> findByCategoriaNegocio(Categoria categoriaNegocio);

    List<Negocio> findByUbicacion(Ubicacion ubicacion);
}
