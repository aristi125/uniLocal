package co.org.uniquindio.unilocal.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Producto extends MongoRepository<Producto, String> {
}
