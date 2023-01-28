package ie.pt.springrestwithjwtsecurity.repo;

import ie.pt.springrestwithjwtsecurity.model.SalesRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRecordRepo extends JpaRepository<SalesRecord, Integer> {

}
