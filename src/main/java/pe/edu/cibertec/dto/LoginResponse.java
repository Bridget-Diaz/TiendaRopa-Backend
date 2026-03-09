package pe.edu.cibertec.dto;

public class LoginResponse {
    private String token;
    private String rol;
    private Integer idUsuario;

    public LoginResponse(String token, String rol, Integer idUsuario) {
        this.token = token;
        this.rol = rol;
        this.idUsuario = idUsuario;
    }

    public String getToken() { return token; }
    public String getRol() { return rol; }
    public Integer getIdUsuario() { return idUsuario; }
}