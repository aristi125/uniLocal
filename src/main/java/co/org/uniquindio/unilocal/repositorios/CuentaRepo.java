package co.org.uniquindio.unilocal.repositorios;

import co.org.uniquindio.unilocal.modelo.documentos.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CuentaRepo extends MongoRepository<Cuenta, String>{
    Optional<Cuenta> findByEmail(String email);
}
