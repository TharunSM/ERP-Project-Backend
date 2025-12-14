package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.ProjectAlreadyExistsException;
import com.examly.springapp.exception.ProjectNotFoundException;
import com.examly.springapp.model.Project;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.ProjectRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class ProjectServiceImpl implements ProjectService {

   @Autowired
   private ProjectRepo projectRepo;
   @Autowired
   private UserRepo userRepo;

   @Override
   public Project addProject(Project project) {
      Optional<Project> optional = projectRepo.findByProjectTitle(project.getProjectTitle());
      if (optional.isPresent()) {
         throw new ProjectAlreadyExistsException();
      }
      return projectRepo.save(project);
   }

   @Override
   public List<Project> getAllProjects() {
      return projectRepo.findAll();
   }

   @Override
   public Project editProject(Long projectId, Project updatedProject) {
      updatedProject.setProjectId(projectId);
      Optional<Project> optional = projectRepo.findById(projectId);
      if (optional.isPresent()) {
         updatedProject = projectRepo.save(updatedProject);
         return updatedProject;
      } else {
         throw new ProjectNotFoundException();
      }
   }

   @Override
   public Project deleteProject(Long projectId) {
      Project project = projectRepo.findById(projectId).get();
      if (project == null) {
         throw new ProjectNotFoundException();
      } else {
         projectRepo.deleteById(projectId);
         return project;
      }
   }

   @Override
   public Project getProjectById(Long projectId) {
      Project project = projectRepo.findById(projectId).get();
      if (project == null) {
         throw new ProjectNotFoundException();
      } else {
         return projectRepo.save(project);
      }
   }

   @Override
   public User addUserToProject(Long projectId, Long userId) {
      Project project = projectRepo.findById(projectId).get();
      User user = userRepo.findById(userId).get();
      user.getProject().add(project);
      userRepo.save(user);
      return user;
   }

   @Override
   public User removeUserFromProject(Long projectId, Long userId) {
      Project project = projectRepo.findById(projectId).get();
      User user = userRepo.findById(userId).get();
      user.getProject().remove(project);
      userRepo.save(user);
      return user;
   }
}
