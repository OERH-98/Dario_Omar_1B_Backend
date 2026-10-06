package Dario_Omar_1B.FINALBOSS.modules.eventos.service;

import Dario_Omar_1B.FINALBOSS.exceptions.BusinessRuleException;
import Dario_Omar_1B.FINALBOSS.exceptions.DataNotFoundException;
import Dario_Omar_1B.FINALBOSS.modules.clientes.model.entity.Cliente;
import Dario_Omar_1B.FINALBOSS.modules.clientes.repository.ClienteRepository;
import Dario_Omar_1B.FINALBOSS.modules.eventos.model.dto.EventoRequestDTO;
import Dario_Omar_1B.FINALBOSS.modules.eventos.model.dto.EventoResponseDTO;
import Dario_Omar_1B.FINALBOSS.modules.eventos.model.entity.Evento;
import Dario_Omar_1B.FINALBOSS.modules.eventos.repository.EventoRepository;
import Dario_Omar_1B.FINALBOSS.modules.salones.model.entity.Salon;
import Dario_Omar_1B.FINALBOSS.modules.salones.repository.SalonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventoService {

    private final EventoRepository repo;
    private final ClienteRepository clienteRepo;
    private final SalonRepository salonRepo;

    private static final String ESTADO_INICIAL = "CONFIRMADO";
    private static final List<String> ESTADOS_ACTIVOS = List.of("PENDIENTE", "CONFIRMADO");

    private void copiarDatos(EventoRequestDTO dto, Evento entity){
        Cliente cliente = clienteRepo.findById(dto.getId_cliente())
                .orElseThrow(() -> new DataNotFoundException("No se encontro el cliente con id " + dto.getId_cliente()));
        Salon salon = salonRepo.findById(dto.getId_salon())
                .orElseThrow(() -> new DataNotFoundException("No se encontro el salon con id " + dto.getId_salon()));

        if (dto.getCantidad_personas() > salon.getCapacidad()) {
            throw new BusinessRuleException("La cantidad de personas (" + dto.getCantidad_personas()
                    + ") supera la capacidad maxima del salon (" + salon.getCapacidad() + ")");
        }

        String estado = dto.getEstado() != null ? dto.getEstado()
                : (entity.getEstado() != null ? entity.getEstado() : ESTADO_INICIAL);

        if (ESTADOS_ACTIVOS.contains(estado)
                && repo.existeEventoActivo(salon.getId_salon(), dto.getFecha_evento(), ESTADOS_ACTIVOS, entity.getId_evento())) {
            throw new BusinessRuleException("El salon ya tiene un evento activo en la fecha " + dto.getFecha_evento());
        }

        entity.setCliente(cliente);
        entity.setSalon(salon);
        entity.setNombre_evento(dto.getNombre_evento());
        entity.setFecha_evento(dto.getFecha_evento());
        entity.setCantidad_personas(dto.getCantidad_personas());
        entity.setCantidad_horas(dto.getCantidad_horas());
        entity.setEstado(estado);
        entity.setTotal_pago(salon.getPrecio_renta().multiply(BigDecimal.valueOf(dto.getCantidad_horas())));
    }

    private EventoResponseDTO convertirADTOResponse(Evento e){
        EventoResponseDTO dto = new EventoResponseDTO();
        dto.setId_evento(e.getId_evento());
        dto.setId_cliente(e.getCliente().getId_cliente());
        dto.setNombre_cliente(e.getCliente().getNombre() + " " + e.getCliente().getApellido());
        dto.setId_salon(e.getSalon().getId_salon());
        dto.setNombre_salon(e.getSalon().getNombre_salon());
        dto.setNombre_evento(e.getNombre_evento());
        dto.setFecha_evento(e.getFecha_evento());
        dto.setCantidad_personas(e.getCantidad_personas());
        dto.setCantidad_horas(e.getCantidad_horas());
        dto.setEstado(e.getEstado());
        dto.setTotal_pago(e.getTotal_pago());

        return dto;
    }

    @Transactional(readOnly = true)
    public List<EventoResponseDTO> obtenerTodos(){
        List<Evento> entidades = repo.findAll();

        List<EventoResponseDTO> dtos = new ArrayList<>();

        for(Evento entity : entidades){
            dtos.add(convertirADTOResponse(entity));
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public EventoResponseDTO obtenerPorId (Long id){
        Evento entidad = repo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("No se encontro el evento con id " + id));
        return convertirADTOResponse(entidad);
    }

    public boolean eliminar (Long id){
        if (repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public EventoResponseDTO nuevoEvento (EventoRequestDTO dto){
        Evento entidad = new Evento();
        copiarDatos(dto, entidad);
        return convertirADTOResponse(repo.saveAndFlush(entidad));
    }

    @Transactional
    public EventoResponseDTO actualizarData(EventoRequestDTO dto, Long id) {
        Evento entidad = repo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("No se encontro el evento con id " + id));
        copiarDatos(dto, entidad);
        return convertirADTOResponse(repo.saveAndFlush(entidad));
    }
}
