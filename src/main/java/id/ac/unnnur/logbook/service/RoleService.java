package id.ac.unnnur.logbook.service;

import id.ac.unnnur.logbook.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }
}
