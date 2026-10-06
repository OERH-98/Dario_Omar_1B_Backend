package Dario_Omar_1B.FINALBOSS.modules.salones.service;

import Dario_Omar_1B.FINALBOSS.exceptions.DataNotFoundException;
import Dario_Omar_1B.FINALBOSS.modules.salones.model.dto.SalonResponseDTO;
import Dario_Omar_1B.FINALBOSS.modules.salones.model.entity.Salon;
import Dario_Omar_1B.FINALBOSS.modules.salones.repository.SalonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SalonService {

    private final SalonRepository repo;

    private SalonResponseDTO convertirADTOResponse(Salon e){
        SalonResponseDTO dto = new SalonResponseDTO();
        dto.setId_salon(e.getId_salon());
        dto.setNombre_salon(e.getNombre_salon());
        dto.setCapacidad(e.getCapacidad());
        dto.setPrecio_renta(e.getPrecio_renta());
        dto.setUbicacion(e.getUbicacion());

        return dto;
    }

    public List<SalonResponseDTO> obtenerTodos(){
        List<Salon> entidades = repo.findAll();

        List<SalonResponseDTO> dtos = new ArrayList<>();

        for(Salon entity : entidades){
            dtos.add(convertirADTOResponse(entity));
        }

        return dtos;
    }

    public SalonResponseDTO obtenerPorId (Long id){
        Salon entidad = repo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("No se encontro el salon con id " + id));
        return convertirADTOResponse(entidad);
    }
}
