package co.com.alianza.fiduciaria.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

    private String sharedKey;

    private String idNegocio;

    private String telefono;

    private String email;

    private String fechaIni;

}
