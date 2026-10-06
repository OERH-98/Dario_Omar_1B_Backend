package Dario_Omar_1B.FINALBOSS.modules.clientes.controller;

import Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto.ClientesResponseDTO;
import Dario_Omar_1B.FINALBOSS.modules.clientes.service.ClienteService;
import Dario_Omar_1B.FINALBOSS.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClientesController {

    private final ClienteService service;

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
