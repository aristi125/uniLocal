package co.org.uniquindio.unilocal.dto;

import jakarta.validation.constraints.NotBlank;

public record ImagenDTO(
        @NotBlank String id,
        @NotBlank String url
) {
}
