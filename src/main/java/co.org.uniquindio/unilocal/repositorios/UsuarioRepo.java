package co.org.uniquindio.unilocal.repositorios;

import co.org.uniquindio.unilocal.modelo.documentos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {

    static String findByCodigo(String codigo) {
        return codigo;
    }

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNickname(String nickname);
}
