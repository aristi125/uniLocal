package co.org.uniquindio.unilocal.repositorios;

import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocioModerador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HistorialRevisionRepo extends MongoRepository<HistorialRevision, String> {
    List<HistorialRevision> findAllByEstadoAndFechaBefore(EstadoNegocioModerador estadoNegocioModerador, LocalDateTime fecha);
}
