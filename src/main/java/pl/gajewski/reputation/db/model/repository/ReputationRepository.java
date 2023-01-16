package pl.gajewski.reputation.db.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gajewski.reputation.db.model.ReputationOB;

@Repository
public interface ReputationRepository extends JpaRepository<ReputationOB, Long> {
}
