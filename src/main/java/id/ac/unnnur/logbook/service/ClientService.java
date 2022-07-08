package id.ac.unnnur.logbook.service;

import id.ac.unnnur.logbook.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }
}
