package finder.tracker.repository;

import finder.tracker.domain.Bed;
import finder.tracker.idclass.BedId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Bed, BedId> {
}