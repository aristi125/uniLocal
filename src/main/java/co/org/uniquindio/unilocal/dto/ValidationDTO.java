package co.org.uniquindio.unilocal.dto;


import jakarta.validation.constraints.NotBlank;

public record ValidationDTO(
        @NotBlank String campo,
        @NotBlank String error
) {
}
