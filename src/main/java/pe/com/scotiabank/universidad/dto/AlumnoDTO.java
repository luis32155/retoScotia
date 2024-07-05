package pe.com.scotiabank.universidad.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlumnoDTO {


    private Long id;

    @NotEmpty(message = "No debe estar vacio")
    @NotNull(message = "No debe ser Null")
    private String nombre;

    @NotEmpty(message = "No debe estar vacio")
    @NotNull(message = "No debe ser Null")
    private String apellido;

    private Estado estado;

    @NotNull
    @Min(value = 1, message = "La edad debe ser mayor o igual a 1")
    @Max(value = 120, message = "La edad debe ser menor o igual a 120")
    private Integer edad;
}
