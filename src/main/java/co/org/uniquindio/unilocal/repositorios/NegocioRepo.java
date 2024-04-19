package co.org.uniquindio.unilocal.repositorios;

import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String> {
    Negocio findByCodigo(String codigo);
    List<Negocio> findAllByCodigoCliente(String codigoCliente);
    List<Negocio> findAllByCodigo(String codigo);
    List<Negocio> findAllByEstado(EstadoNegocio estadoNegocio);
    List<Negocio> findByCategoriaNegocio(CategoriaNegocio categoria);
    List<Negocio> findByEstado(EstadoNegocio estadoNegocio);

}
