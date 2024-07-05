package pe.com.scotiabank.universidad.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import pe.com.scotiabank.universidad.dto.Estado;

@Data
@Builder
@Table("alumnos")
public class Alumno {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String apellido;
    private Estado estado;
    private Integer edad;
}