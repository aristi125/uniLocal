package co.org.uniquindio.unilocal.servicios.interfaces;


import co.org.uniquindio.unilocal.modelo.entidades.HistorialRevision;

import java.util.List;

public interface ModeradorServicio  {
    List<HistorialRevision> getHistorialRevisiones(String idNegocio) throws Exception;
}
