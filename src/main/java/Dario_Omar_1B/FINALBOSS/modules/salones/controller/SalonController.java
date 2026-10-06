package Dario_Omar_1B.FINALBOSS.modules.salones.controller;

import Dario_Omar_1B.FINALBOSS.exceptions.DataNotFoundException;
import Dario_Omar_1B.FINALBOSS.modules.salones.model.dto.SalonResponseDTO;
import Dario_Omar_1B.FINALBOSS.modules.salones.service.SalonService;
import Dario_Omar_1B.FINALBOSS.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/salones")
public class SalonController {

    private final SalonService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SalonResponseDTO>>> obtenerDatos (){
        try{
            List<SalonResponseDTO> lista = service.obtenerTodos();
            return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido los datos con exito", lista));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "No se pudieron obtener los datos", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SalonResponseDTO>> obtenerDatosPorId(@PathVariable Long id){
        try{
            SalonResponseDTO dto = service.obtenerPorId(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido los datos con exito", dto));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "No se pudieron obtener los datos", null));
        }
    }
}
