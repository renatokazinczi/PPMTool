package com.renato.ppmtool.service;

import com.renato.ppmtool.domain.Project;
import com.renato.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project) {

        //LOGIC

        return projectRepository.save(project);
    }
}
