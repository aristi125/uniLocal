package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.EmailArchivoDTO;
import co.org.uniquindio.unilocal.dto.EmailDTO;

public interface EmailServicio {

    /**
     * Metodo que envia un correo electronico
     * @param emailDTO
     * @throws Exception
     */
    void enviarCorreo(EmailDTO emailDTO) throws Exception;

    void enviarCorreoArchivo(EmailArchivoDTO emailDTO) throws Exception;
}
