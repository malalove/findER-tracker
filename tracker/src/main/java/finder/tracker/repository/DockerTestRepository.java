package finder.tracker.repository;

import finder.tracker.domain.Monday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DockerTestRepository extends JpaRepository<Monday, String> {
}