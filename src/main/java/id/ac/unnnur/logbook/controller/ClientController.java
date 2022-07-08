package id.ac.unnnur.logbook.controller;

import id.ac.unnnur.logbook.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }
}
