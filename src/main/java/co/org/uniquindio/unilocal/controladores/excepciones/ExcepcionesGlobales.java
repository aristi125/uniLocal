package co.org.uniquindio.unilocal.controladores.excepciones;

import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.ValidationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExcepcionesGlobales {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO<String>> generarExcepciones(Exception e){
        return ResponseEntity.internalServerError().body( new MensajeDTO<>(true, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO<List<ValidationDTO>>> validationExcepcion(MethodArgumentNotValidException ex ) {
        List<ValidationDTO> errores = new ArrayList<>();
        BindingResult result = ex.getBindingResult();

        for (FieldError e: result.getFieldErrors()) {
            errores.add( new ValidationDTO(e.getField(), e.getDefaultMessage()));
        }

        return ResponseEntity.badRequest().body( new MensajeDTO<>(true, errores));
    }
}
