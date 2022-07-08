package id.ac.unnnur.logbook.controller;

import id.ac.unnnur.logbook.payload.CommonResponse;
import id.ac.unnnur.logbook.payload.request.ProjectRequest;
import id.ac.unnnur.logbook.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CommonResponse> inserDataProject(@Valid ProjectRequest request) {
        CommonResponse response = service.insertDataProject(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAllDataProjects() {
        CommonResponse response = service.getAllData();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-user")
    public ResponseEntity<CommonResponse> getDataByUser() {
        CommonResponse response = service.getDataByUser();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
