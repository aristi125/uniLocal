package co.org.uniquindio.unilocal.repositorios;

import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoRevision;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HistorialRevisionRepo extends MongoRepository<HistorialRevision, String> {
    List<HistorialRevision> findAllByEstadoAndFechaBefore(EstadoRevision estadoRevision, LocalDateTime fecha);
}
