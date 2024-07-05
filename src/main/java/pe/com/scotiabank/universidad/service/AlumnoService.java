package pe.com.scotiabank.universidad.service;


import lombok.RequiredArgsConstructor;
import pe.com.scotiabank.universidad.dto.AlumnoDTO;
import pe.com.scotiabank.universidad.dto.Estado;
import pe.com.scotiabank.universidad.entity.Alumno;
import pe.com.scotiabank.universidad.exception.BadRequestException;
import pe.com.scotiabank.universidad.exception.ResourceNotFoundException;
import pe.com.scotiabank.universidad.mapper.AlumnoMapper;
import pe.com.scotiabank.universidad.repository.AlumnoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AlumnoService {


    private final AlumnoRepository alumnoRepository;


    public Mono<Void> saveAlumno(AlumnoDTO alumnoDTO) {
        Alumno alumno = AlumnoMapper.alumnoDTOToAlumno(alumnoDTO);
        return alumnoRepository.save(alumno).onErrorMap(e -> new BadRequestException("Error al guardar el alumno: " + e.getMessage())).then();
    }

    public Flux<AlumnoDTO> getAlumnosActivos() {
        return alumnoRepository.findByEstado(Estado.ACTIVO)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No se encontraron alumnos activos")))
                .map(AlumnoMapper::alumnoToAlumnoDTO);
    }
}
