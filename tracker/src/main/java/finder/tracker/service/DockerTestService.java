package finder.tracker.service;

import finder.tracker.domain.Monday;
import finder.tracker.repository.DockerTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DockerTestService {
    @Autowired
    private DockerTestRepository dockerTestRepository;

    public Optional<Monday> findOne(String id) {
        return dockerTestRepository.findById(id);
    }
}