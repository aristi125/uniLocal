package co.org.uniquindio.unilocal.dto.Cuenta;

public record CambioPasswordDTO(
      @NotNull  String id,
      @NotNull @Length(min=8, message="La contraseña debe tener minim 8 caracteres") String passwordNueva,
      @NotNull  String email,
                String token
) {
}
