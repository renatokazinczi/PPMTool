package com.renato.ppmtool.service;

import com.renato.ppmtool.domain.Project;
import com.renato.ppmtool.exception.ProjectIdException;
import com.renato.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException(String.format("Project ID '%s' already exists", project.getProjectIdentifier().toUpperCase()));
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null) {
            throw new ProjectIdException(String.format("Project ID '%s' doesn't exist", projectId.toUpperCase()));
        }
        return project;
    }

    public Iterable<Project> findAllProject() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null) {
            throw new ProjectIdException(String.format("Cannot delete Project with ID '%s'. This Project doesn't exist.", projectId.toUpperCase()));
        }

        projectRepository.delete(project);
    }
}
