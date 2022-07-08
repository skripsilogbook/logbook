package id.ac.unnnur.logbook.repository;

import id.ac.unnnur.logbook.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
}
