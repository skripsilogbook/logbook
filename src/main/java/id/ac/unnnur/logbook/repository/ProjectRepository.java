package id.ac.unnnur.logbook.repository;

import id.ac.unnnur.logbook.model.Project;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    List<Project> findAllByProjectStatus(Boolean projectStatus);
    List<Project> findAllByCreatedBy(String createdBy);
}
