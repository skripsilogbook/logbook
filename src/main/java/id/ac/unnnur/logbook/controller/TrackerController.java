package id.ac.unnnur.logbook.controller;

import id.ac.unnnur.logbook.payload.CommonResponse;
import id.ac.unnnur.logbook.payload.request.TrackerRequest;
import id.ac.unnnur.logbook.service.TrackerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/trackers")
@CrossOrigin(origins = "*")
public class TrackerController {

    private final Logger logger = LoggerFactory.getLogger(TrackerController.class);

    private final TrackerService service;

    public TrackerController(TrackerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CommonResponse> insertDataProject(@Valid @RequestBody TrackerRequest request) {
        CommonResponse response = service.insertDataTracker(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAllDataTrackers() {
        CommonResponse response = service.getAllDataTrackers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-user")
    public ResponseEntity<CommonResponse> getDataByUser() {
        CommonResponse response = service.getDataByUser();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
