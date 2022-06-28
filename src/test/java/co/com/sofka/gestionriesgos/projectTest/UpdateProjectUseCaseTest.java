package co.com.sofka.gestionriesgos.projectTest;

import co.com.sofka.gestionriesgos.collections.Project;
import co.com.sofka.gestionriesgos.mappers.ProjectMapper;
import co.com.sofka.gestionriesgos.repositories.ProjectRepository;
import co.com.sofka.gestionriesgos.usercases.project.UpdateProjectUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UpdateProjectUseCaseTest {

    @Mock
    ProjectRepository projectRepository;


    UpdateProjectUseCase updateProjectUseCase;

    ProjectMapper projectMapper;

    @BeforeEach
    public void setup(){
        projectMapper = new ProjectMapper();
        updateProjectUseCase = new UpdateProjectUseCase(projectRepository, projectMapper);
    }

    @Test
    void updateProjectUseCaseTest(){
        var project = new Project();
        project.setId("1");
        project.setName("Proyecto 1");
        project.setStartDate(LocalDate.now());
        project.setEndingDate(LocalDate.now());
        project.setDescription("Descripcion");
        project.setEmails(List.of("correo@gmail.com"));
        project.setLabels(List.of("label1", "label2"));
        project.setStatus("Creado");

        var projectReturn = new Project();
        projectReturn.setId("1");
        projectReturn.setName("Proyecto 1");
        projectReturn.setStartDate(LocalDate.now());
        projectReturn.setEndingDate(LocalDate.of(2022,7,4));
        projectReturn.setDescription("Descripcion modificada");
        projectReturn.setEmails(List.of("correo@gmail.com","correo1@gmail.com"));
        projectReturn.setLabels(List.of("label1", "label2", "label3"));
        projectReturn.setStatus("Creado");

        var projectDTO = projectMapper.entityToProjectDTO().apply(project);

        when(projectRepository.findById("1")).thenReturn(Mono.just(project));
        when(projectRepository.save(project)).thenReturn(Mono.just(projectReturn));

        StepVerifier.create(updateProjectUseCase.apply(projectDTO))
                .expectNext("1")
                .verifyComplete();

                verify(projectRepository).save(project);


    }

}