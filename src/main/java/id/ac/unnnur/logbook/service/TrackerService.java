package id.ac.unnnur.logbook.service;

import id.ac.unnnur.logbook.model.Tracker;
import id.ac.unnnur.logbook.payload.CommonResponse;
import id.ac.unnnur.logbook.payload.ErrorMessage;
import id.ac.unnnur.logbook.payload.ErrorSchema;
import id.ac.unnnur.logbook.payload.request.TrackerRequest;
import id.ac.unnnur.logbook.repository.TrackerRepository;
import id.ac.unnnur.logbook.util.CommonUtil;
import id.ac.unnnur.logbook.util.Constant;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackerService {

    private final TrackerRepository repository;
    private final ProjectService projectService;

    public TrackerService(TrackerRepository repository, ProjectService projectService) {
        this.repository = repository;
        this.projectService = projectService;
    }

    public CommonResponse insertDataTracker(TrackerRequest request) {
        Tracker tracker = new Tracker();
        tracker.setTrackerActivity(request.getTrackerActivity());
        tracker.setTrackerDate(request.getTrackerDate() == null ? LocalDate.now(): request.getTrackerDate());
        tracker.setStartHour(request.getStartHour() == null ? LocalTime.now() : request.getStartHour());
        tracker.setEndHour(request.getEndHour());
        tracker.setTrackerBy(request.getTrackerBy());
        tracker.setProject(projectService.getDataProjectById(request.getProjectId()));
        tracker.setTrackerStatus(true);
        repository.save(tracker);

        ErrorSchema errorSchema = new ErrorSchema();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setEnglish("Success Login");
        errorMessage.setBahasa("Berhasil Masuk");
        errorSchema.setErrorCode(Constant.SUCCESS_CODE);
        errorSchema.setErrorMessage(errorMessage);

        CommonResponse response = new CommonResponse();
        response.setOutputSchema(tracker);
        response.setErrorSchema(errorSchema);
        return response;
    }

    public CommonResponse getAllDataTrackers() {
        List<Tracker> trackers = new ArrayList<>();
        repository.findAllByTrackerStatus(true).forEach(trackers::add);

        ErrorSchema errorSchema = new ErrorSchema();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setEnglish("Success");
        errorMessage.setBahasa("Berhasil");
        errorSchema.setErrorCode(Constant.SUCCESS_CODE);
        errorSchema.setErrorMessage(errorMessage);

        CommonResponse response = new CommonResponse();
        response.setOutputSchema(trackers);
        response.setErrorSchema(errorSchema);
        return response;
    }

    public CommonResponse getDataByUser() {
        List<Tracker> trackers = new ArrayList<>();
        repository.findByTrackerBy(CommonUtil.getUserLogin()).forEach(trackers::add);

        ErrorSchema errorSchema = new ErrorSchema();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setEnglish(Constant.SUCCESS_EN);
        errorMessage.setBahasa(Constant.SUCCESS_ID);
        errorSchema.setErrorCode(Constant.SUCCESS_CODE);
        errorSchema.setErrorMessage(errorMessage);

        CommonResponse response = new CommonResponse();
        response.setOutputSchema(trackers);
        response.setErrorSchema(errorSchema);
        return response;
    }
}
