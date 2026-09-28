package visitorpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import visitorpass.entity.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

}