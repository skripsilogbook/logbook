package id.ac.unnnur.logbook.controller;

import id.ac.unnnur.logbook.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }
}
