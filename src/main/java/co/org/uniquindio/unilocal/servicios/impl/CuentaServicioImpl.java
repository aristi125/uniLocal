package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cuenta;
import co.org.uniquindio.unilocal.repositorios.CuentaRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.CuentaServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CuentaServicioImpl implements CuentaServicio {

    private final CuentaRepo cuentaRepo;

    public CuentaServicioImpl(CuentaRepo cuentaRepo) {
        this.cuentaRepo = cuentaRepo;
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {
        //Buscamos el cliente con el email
        Optional<Cuenta> optionalCuenta = cuentaRepo.findByEmail(email);
        //Si no se encontró el cliente, lanzamos una excepción
        if(optionalCuenta.isEmpty()){
            throw new Exception("No se encontró el cliente con el email "+email);
        }
        //Obtenemos el cliente
        Cuenta cuenta = optionalCuenta.get();

        EmailDTO emailDTO = new EmailDTO(
                "Recuperación de contraseña",
                "Hola "+cuenta.getNombre()+"! \n\n"+
                        "Hemos recibido una solicitud para recuperar tu contraseña. \n\n"+
                        "Si no has solicitado este cambio, por favor ignora este mensaje. \n\n"+
                        "Si deseas cambiar tu contraseña, haz clic en el siguiente enlace: \n\n"+
                        "http://localhost:8081/recuperar-password?codigo="+cuenta.getCodigo()+"\n\n"+
                        "Gracias por confiar en nosotros!",
                email
        );

    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Optional<Cuenta> optional =cuentaRepo.findById(cambioPasswordDTO.id());

        if(optional.isEmpty()){
            throw new Exception("No existe el paciente con el codigo "+ cambioPasswordDTO.id());
        }

        //BUSCAMOS AL CLIENTE CON EL GET
        Cuenta registro = optional.get();

        // Encriptar la nueva contraseña
        String nuevaPasswordEncriptada = "";//passwordEncoder.encode(cambioPasswordDTO.passwordNueva());

        // Asignar la nueva contraseña encriptada al cliente
        registro.setPassword(nuevaPasswordEncriptada);

        // Guardar el cliente actualizado en la base de datos
        cuentaRepo.save(registro);
    }
}
