package finder.tracker.repository;

import finder.tracker.domain.Monday;
import finder.tracker.idclass.MondayId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MondayRepository extends JpaRepository<Monday, MondayId> {
}