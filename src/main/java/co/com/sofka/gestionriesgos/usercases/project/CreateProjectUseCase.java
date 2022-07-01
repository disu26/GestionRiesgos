package co.com.sofka.gestionriesgos.usercases.project;

import co.com.sofka.gestionriesgos.collections.Project;
import co.com.sofka.gestionriesgos.mappers.ProjectMapper;
import co.com.sofka.gestionriesgos.model.ProjectDTO;
import co.com.sofka.gestionriesgos.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateProjectUseCase implements SaveProject {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public CreateProjectUseCase(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public Mono<String> apply(ProjectDTO projectDTO){
        if (projectDTO.getEndingDate() == null) return guardar(projectDTO);
        if (projectDTO.getStartDate().isBefore(projectDTO.getEndingDate()) || projectDTO.getStartDate().isEqual(projectDTO.getEndingDate())) return guardar(projectDTO);
        return Mono.error(new IllegalArgumentException("La fecha de inicio debe ser anterior o igual a la fecha de finalización"));
    }

    public Mono<String> guardar(ProjectDTO newProject) {
        return projectRepository.save(projectMapper.mapperToProject(null).apply(newProject))
                .map(Project::getId);
    }

}
