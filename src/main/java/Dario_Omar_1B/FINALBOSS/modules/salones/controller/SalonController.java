package Dario_Omar_1B.FINALBOSS.modules.salones.controller;

import Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto.ClientesResponseDTO;
import Dario_Omar_1B.FINALBOSS.modules.salones.service.SalonService;
import Dario_Omar_1B.FINALBOSS.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class SalonController {

    private final SalonService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientesResponseDTO>>> obtenerDatos (){
        try{
            List<ClientesResponseDTO> lista = service.obtenerTodos();
            ApiResponse<List<ClientesResponseDTO>> respuestaExito = new ApiResponse<>(true,"Se ha obtenido los datos con exito muchacho",lista);
            return ResponseEntity.ok(respuestaExito);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<ClientesResponseDTO>> respuestaError = new ApiResponse<>(false,"No se pudo obtener los datos muchacho",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
