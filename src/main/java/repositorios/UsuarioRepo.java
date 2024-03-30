package repositorios;

import co.org.uniquindio.unilocal.modelo.documentos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {
}
