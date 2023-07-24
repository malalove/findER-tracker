package finder.tracker.repository;

import finder.tracker.domain.DockerTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DockerTestRepository extends JpaRepository<DockerTest, String> {
}