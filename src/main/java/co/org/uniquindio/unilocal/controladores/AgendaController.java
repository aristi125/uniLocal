package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistrarAgendaDTO;
import co.org.uniquindio.unilocal.servicios.interfaces.AgendaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaServicio agendaServicio;

    @PostMapping("/registrar-agenda")
    public ResponseEntity<MensajeDTO<String>> registrarAgenda(@Valid @RequestBody RegistrarAgendaDTO registrarAgendaDTO) throws Exception {
        agendaServicio.registrarAgenda(registrarAgendaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "La agenda ha sido registrada"));
    }

    @PutMapping("/actualizar-agenda")
    public ResponseEntity<MensajeDTO<String>> actualizarAgenda(@Valid @RequestBody RegistrarAgendaDTO registrarAgendaDTO) throws Exception {
        agendaServicio.actualizarAgenda(registrarAgendaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Agenda actualiza correctamente"));
    }

    @DeleteMapping("/eliminar-agenda")
    public ResponseEntity<MensajeDTO<String>> eliminarAgenda(@PathVariable String codigoNegocio) throws Exception {
        agendaServicio.eliminarAgenda(codigoNegocio);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Agenda eliminada satisfactoriamente"));
    }

    @GetMapping("/obtener-agenda")
    public ResponseEntity<MensajeDTO<DetalleAgendaDTO>> obtenerAgenda(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, agendaServicio.obtenerAgenda(codigoNegocio)));
    }
}
