package Dario_Omar_1B.FINALBOSS.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResponse <T>{
    public ApiResponse(boolean success, String messaje, T data) {
        this.success = success;
        this.messaje = messaje;
        this.data = data;
    }

    private boolean success;
    private String messaje;
    private T data;
}
