package co.org.uniquindio.unilocal.config;


import co.org.uniquindio.unilocal.servicios.impl.NegocioServicioImpl;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
@Component
public class SpringConfig {
    private NegocioServicioImpl negocioServicioimpl;

    @Scheduled(cron = "0 0 0 * * ?") // Se ejecuta a medianoche todos los días
    public void EjecutarTareaEliminarRechazadosAntiguos() throws Exception {
    negocioServicioimpl.eliminarNegocioRechazado();
    }
}
