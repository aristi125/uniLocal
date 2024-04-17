package co.org.uniquindio.unilocal.dto.Cuenta;

public record CambioPasswordDTO(
      @NotBlank String id,
      @NotBlank @Length (min=8, message="La contraseña debe tener minim 8 caracteres") String passwordNueva,
      @Email    String email,
                String token
) {
}
