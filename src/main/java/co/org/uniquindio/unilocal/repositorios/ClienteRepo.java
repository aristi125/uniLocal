package co.org.uniquindio.unilocal.repositorios;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {
    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByNickname(String nickname);
}