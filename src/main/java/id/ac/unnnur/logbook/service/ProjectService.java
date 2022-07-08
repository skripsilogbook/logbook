package id.ac.unnnur.logbook.service;

import id.ac.unnnur.logbook.model.Project;
import id.ac.unnnur.logbook.payload.CommonResponse;
import id.ac.unnnur.logbook.payload.ErrorMessage;
import id.ac.unnnur.logbook.payload.ErrorSchema;
import id.ac.unnnur.logbook.payload.request.ProjectRequest;
import id.ac.unnnur.logbook.repository.ProjectRepository;
import id.ac.unnnur.logbook.util.CommonUtil;
import id.ac.unnnur.logbook.util.Constant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Project getDataProjectById(Long projectId) {
        Optional<Project> dataProject = repository.findById(projectId);
        AtomicReference<Project> project = new AtomicReference<>(new Project());

        dataProject.ifPresentOrElse(data -> project.set(data), () -> {
            System.out.println("NOT FOUND");
        });

        return project.get();
    }

    public CommonResponse insertDataProject(ProjectRequest request) {
        Project project = new Project();
        project.setProjectName(request.getProjectName());
        project.setProjectStatus(true);
        repository.save(project);

        ErrorSchema errorSchema = new ErrorSchema();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setEnglish(Constant.SUCCESS_EN);
        errorMessage.setBahasa(Constant.SUCCESS_ID);
        errorSchema.setErrorCode(Constant.SUCCESS_CODE);
        errorSchema.setErrorMessage(errorMessage);

        CommonResponse response = new CommonResponse();
        response.setOutputSchema(project);
        response.setErrorSchema(errorSchema);
        return response;
    }

    public CommonResponse getAllData() {
        List<Project> projects = new ArrayList<>();
        repository.findAllByProjectStatus(true).forEach(projects::add);
        ErrorSchema errorSchema = new ErrorSchema();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setEnglish(Constant.SUCCESS_EN);
        errorMessage.setBahasa(Constant.SUCCESS_ID);
        errorSchema.setErrorCode(Constant.SUCCESS_CODE);
        errorSchema.setErrorMessage(errorMessage);

        CommonResponse response = new CommonResponse();
        response.setOutputSchema(projects);
        response.setErrorSchema(errorSchema);
        return response;
    }

    public CommonResponse getDataByUser() {
        List<Project> projects = new ArrayList<>();
        repository.findAllByCreatedBy(CommonUtil.getUserLogin()).forEach(projects::add);
        ErrorSchema errorSchema = new ErrorSchema();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setEnglish(Constant.SUCCESS_EN);
        errorMessage.setBahasa(Constant.SUCCESS_ID);
        errorSchema.setErrorCode(Constant.SUCCESS_CODE);
        errorSchema.setErrorMessage(errorMessage);

        CommonResponse response = new CommonResponse();
        response.setOutputSchema(projects);
        response.setErrorSchema(errorSchema);
        return response;
    }
}
