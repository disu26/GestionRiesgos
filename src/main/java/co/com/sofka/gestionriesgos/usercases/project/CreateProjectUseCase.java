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

    @Override
    public Mono<String> apply(ProjectDTO newProject) {
        return projectRepository.save(projectMapper.mapperToProject(null).apply(newProject))
                .map(Project::getId);
    }

}