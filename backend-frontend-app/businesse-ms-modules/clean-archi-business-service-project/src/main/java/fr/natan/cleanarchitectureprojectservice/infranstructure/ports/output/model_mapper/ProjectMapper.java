package fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.model_mapper;

import fr.natan.cleanarchitectureprojectservice.domain.entity.Project;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.models.ProjectDto;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.models.ProjectModel;
import org.springframework.beans.BeanUtils;

public class ProjectMapper {
    public static Project mapToClass(ProjectModel projectModel) {
        Project project = new Project();
        BeanUtils.copyProperties(projectModel, project);
        return project;
    }

    public static ProjectModel mapToModel(Project project) {
        ProjectModel projectModel = new ProjectModel();
        BeanUtils.copyProperties(project, projectModel);
        return projectModel;
    }

    public static Project dtoToClass(ProjectDto projectDto) {
        Project project = new Project();
        BeanUtils.copyProperties(projectDto, project);
        return project;
    }
}
