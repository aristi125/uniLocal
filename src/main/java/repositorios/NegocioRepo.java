package repositorios;

import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String> {
}
