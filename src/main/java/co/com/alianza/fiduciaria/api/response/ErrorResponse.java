package co.com.alianza.fiduciaria.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private int code;
    private String message;
}
