package co.org.uniquindio.unilocal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record ValidationDTO(
        String campo,
        String error
) {
}
