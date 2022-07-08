package id.ac.unnnur.logbook.repository;

import id.ac.unnnur.logbook.model.Tracker;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerRepository extends PagingAndSortingRepository<Tracker, Long> {
    List<Tracker> findAllByTrackerStatus(Boolean status);
    List<Tracker> findByTrackerBy(String createdBy);
}
