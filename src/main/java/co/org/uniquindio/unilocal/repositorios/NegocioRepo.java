package co.org.uniquindio.unilocal.repositorios;

import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String> {
    Negocio findByCodigo(String codigo);
    List<Negocio> findAllByCodigo(String codigo);




}
