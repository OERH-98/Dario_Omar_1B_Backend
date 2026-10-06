package Dario_Omar_1B.FINALBOSS.response;

public class ApiResponse {
    public ApiResponse(boolean success, String messaje, T data) {
        this.success = success;
        this.messaje = messaje;
        this.data = data;
    }

    private boolean success;
    private String messaje;
    private T data;
}
