package pe.com.scotiabank.universidad;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.com.scotiabank.universidad.dto.AlumnoDTO;
import pe.com.scotiabank.universidad.dto.Estado;
import pe.com.scotiabank.universidad.entity.Alumno;
import pe.com.scotiabank.universidad.repository.AlumnoRepository;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UniversidadApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Test
    public void testCreateAlumno() {
        AlumnoDTO alumnoDTO = AlumnoDTO.builder()
                .nombre("Juan")
                .apellido("Perez")
                .estado(Estado.ACTIVO)
                .edad(20)
                .build();

        HttpEntity<AlumnoDTO> request = new HttpEntity<>(alumnoDTO);
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + "/api/alumnos", request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCreateAlumnoWithInvalidName() {
        AlumnoDTO alumnoDTO = AlumnoDTO.builder()
                .nombre("")
                .apellido("Perez")
                .estado(Estado.ACTIVO)
                .edad(20)
                .build();

        HttpEntity<AlumnoDTO> request = new HttpEntity<>(alumnoDTO);
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + "/api/alumnos", request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateAlumnoWithInvalidAge() {
        AlumnoDTO alumnoDTO = AlumnoDTO.builder()

                .nombre("Juan")
                .apellido("Perez")
                .estado(Estado.ACTIVO)
                .edad(200)  // Edad inválida
                .build();

        HttpEntity<AlumnoDTO> request = new HttpEntity<>(alumnoDTO);
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + "/api/alumnos", request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetAlumnosActivos() {
        Alumno alumno1 = Alumno.builder()

                .nombre("Juan")
                .apellido("Perez")
                .estado(Estado.ACTIVO)
                .edad(20)
                .build();
        alumnoRepository.save(alumno1).block();

        Alumno alumno2 = Alumno.builder()
                .nombre("Ana")
                .apellido("Gomez")
                .estado(Estado.INACTIVO)
                .edad(22)
                .build();
        alumnoRepository.save(alumno2).block();

        List<AlumnoDTO> alumnos = restTemplate.getForObject("http://localhost:" + port + "/api/alumnos/activos", List.class);
        assertThat(alumnos.size()).isEqualTo(1);
    }
}
