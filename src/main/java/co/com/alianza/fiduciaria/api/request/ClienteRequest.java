package co.com.alianza.fiduciaria.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClienteRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String telefono;

    @NotBlank
    private String email;

    @NotBlank
    private String fechaIni;

    @NotBlank
    private String fechaFin;

}
