package pe.com.scotiabank.universidad.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.scotiabank.universidad.dto.Estado;
import pe.com.scotiabank.universidad.entity.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface AlumnoRepository extends ReactiveCrudRepository<Alumno, Long> {
    Flux<Alumno> findByEstado(Estado estado);
}