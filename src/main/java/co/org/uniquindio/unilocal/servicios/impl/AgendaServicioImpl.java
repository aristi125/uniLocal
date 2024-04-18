package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.entidades.Agenda;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.AgendaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class AgendaServicioImpl implements AgendaServicio {

    NegocioRepo negocioRepositorio;
    @Override
    public void registrarAgenda(RegistroAgendaDTO registrarAgendaDTO) throws Exception {

        Optional<Negocio> negocioOptional = negocioRepositorio.findById(registrarAgendaDTO.codigoNegocio());
        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = negocioOptional.get();

        Agenda agendaNueva = new Agenda(
                registrarAgendaDTO.tematica(),
                registrarAgendaDTO.descripcion()
        );

        negocio.setAgenda(agendaNueva);
        negocioRepositorio.save(negocio);

    }

    @Override
    public void actualizarAgenda(RegistroAgendaDTO registrarAgendaDTO) throws Exception {

        Optional<Negocio> negocioOptional = negocioRepositorio.findById(registrarAgendaDTO.codigoNegocio());
        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = negocioOptional.get();

        Agenda agenda = negocio.getAgenda();

        if (agenda.getTematica().equals(registrarAgendaDTO.tematica())) {
            throw new Exception("Ya existe una agenda con esa tematica");
        }

        Agenda agendaNueva = new Agenda(
                registrarAgendaDTO.tematica(),
                registrarAgendaDTO.descripcion()
        );

        negocio.setAgenda(agendaNueva);
        negocioRepositorio.save(negocio);

    }

    @Override
    public void eliminarAgenda(String codigoNegocio) throws Exception {

            Optional<Negocio> negocioOptional = negocioRepositorio.findById(codigoNegocio);
            if(negocioOptional.isEmpty()){
                throw new Exception("El negocio no existe");
            }

            Negocio negocio = negocioOptional.get();

        Agenda agendaNueva = new Agenda(
                " ",
                " "
        );

        negocio.setAgenda(agendaNueva);
        negocioRepositorio.save(negocio);

    }

    @Override
    public DetalleAgendaDTO obtenerAgenda(String codigoNegocio) throws Exception {

        Optional<Negocio> negocioOptional = negocioRepositorio.findById(codigoNegocio);
        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = negocioOptional.get();

        Agenda agenda = negocio.getAgenda();

        DetalleAgendaDTO detalleAgendaDTO = new DetalleAgendaDTO(
                                            agenda.getTematica(),
                                            agenda.getDescripcion());

        return detalleAgendaDTO;
    }
}
