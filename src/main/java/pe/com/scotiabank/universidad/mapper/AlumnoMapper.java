package pe.com.scotiabank.universidad.mapper;

import lombok.NoArgsConstructor;
import pe.com.scotiabank.universidad.dto.AlumnoDTO;
import pe.com.scotiabank.universidad.dto.Estado;
import pe.com.scotiabank.universidad.entity.Alumno;

@NoArgsConstructor
public class AlumnoMapper {

    public static  AlumnoDTO alumnoToAlumnoDTO(Alumno alumno){
        return AlumnoDTO.builder()
                .nombre(alumno.getNombre())
                .apellido(alumno.getApellido())
                .estado(alumno.getEstado())
                .edad(alumno.getEdad())
                .build();
    }

    public static Alumno alumnoDTOToAlumno(AlumnoDTO alumnoDTO) {
        return Alumno.builder()
                .nombre(alumnoDTO.getNombre())
                .apellido(alumnoDTO.getApellido())
                .estado(alumnoDTO.getEstado() != null ? alumnoDTO.getEstado() : Estado.ACTIVO)
                .edad(alumnoDTO.getEdad())
                .build();
    }
}
