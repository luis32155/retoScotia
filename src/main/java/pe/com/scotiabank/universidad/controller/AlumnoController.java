package pe.com.scotiabank.universidad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.scotiabank.universidad.dto.AlumnoDTO;
import pe.com.scotiabank.universidad.service.AlumnoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @PostMapping
    public Mono<ResponseEntity<Void>> createAlumno(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.saveAlumno(alumnoDTO)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().<Void>build()));
    }

    @GetMapping("/activos")
    public Flux<AlumnoDTO> getAlumnosActivos() {
        return alumnoService.getAlumnosActivos();
    }
}
